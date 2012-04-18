from django.http import HttpResponseRedirect
from django.shortcuts import render_to_response, RequestContext
from django.core.urlresolvers import reverse

from models import Page


def view_page(request, page_name):
    try:
        page = Page.objects.get(title=page_name)
        print page
    except (KeyError, Page.DoesNotExist):
        return render_to_response('wiki/index.html', {'page_name': page_name})

    return render_to_response('wiki/index.html', {'page': page})


def edit_page(request, page_name):
    try:
        page = Page.objects.get(title=page_name)
        content = page.content
    except (KeyError, Page.DoesNotExist):
        content = ""

    return render_to_response('wiki/edit.html',
        {'page_name': page_name, 'content': content},
          context_instance=RequestContext(request)
          )


def save_page(request, page_name):
    content = request.POST['content']

    try:
        page = Page.objects.get(title=page_name)
        page.content = content
    except (KeyError, Page.DoesNotExist):
        page = Page(title=page_name, content=content)

    page.save()

    return HttpResponseRedirect(reverse('view_page', kwargs={'page_name': page_name}))
