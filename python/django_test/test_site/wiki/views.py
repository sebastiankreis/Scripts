from django.http import HttpResponseRedirect
from django.shortcuts import render
from django.contrib.auth.decorators import login_required
from django.core.urlresolvers import reverse

from models import Page


def search(request, search_term=None):
    if not 'search_term' in request.POST or not request.POST['search_term']:
        return render(request, 'wiki/landing.html')

    search_term = request.POST['search_term']

    try:
        page = Page.objects.get(title=search_term)
    except (KeyError, Page.DoesNotExist):
        return HttpResponseRedirect(reverse('view_page',
                    kwargs={'page_name': search_term}))
    else:
        return HttpResponseRedirect(reverse('view_page',
            kwargs={'page_name': page.title}))


def landing_page(request):
    try:
        pages = Page.objects.order_by('-modified')[:5]
    except (KeyError, Page.DoesNotExist):
        return render(request, 'wiki/landing.html')

    return render(request, 'wiki/landing.html', {'recent_entries': pages})


def view_page(request, page_name):
    try:
        print page_name
        page = Page.objects.get(title=page_name)
    except (KeyError, Page.DoesNotExist):
        return render(request, 'wiki/index.html', {'page_name': page_name})

    return render(request, 'wiki/index.html', {'page': page})


@login_required
def edit_page(request, page_name):
    try:
        page = Page.objects.get(title=page_name)
        content = page.content
    except (KeyError, Page.DoesNotExist):
        content = ""

    return render(request, 'wiki/edit.html',
                  {'page_name': page_name, 'content': content})


def save_page(request, page_name):
    content = request.POST['content']

    try:
        page = Page.objects.get(title=page_name)
        page.content = content
    except (KeyError, Page.DoesNotExist):
        page = Page(title=page_name, content=content)

    page.save()

    return HttpResponseRedirect(reverse('view_page', kwargs={'page_name': page_name}))
