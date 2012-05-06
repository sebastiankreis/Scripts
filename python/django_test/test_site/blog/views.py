from django.contrib.auth.decorators import login_required
from django.core.urlresolvers import reverse
from django.views.generic import ListView
from django.shortcuts import render, get_object_or_404
from django.http import HttpResponseRedirect
from datetime import datetime

from models import Post, NewPostForm


class FilteredListView(ListView):
    template_name = "blog/index.html"
    context_object_name = "posts"

    def get_queryset(self):
        year = self.kwargs['year']
        month = self.kwargs.get('month', None)
        day = self.kwargs.get('day', None)

        posts = Post.objects.filter(created__year=year)

        if month:
            posts = posts.filter(created__month=month)

        if day:
            posts = posts.filter(created__day=day)

        return posts


@login_required
def edit_post(request, slug):
    post = get_object_or_404(Post, slug=slug)

    if request.user.username != post.username:
        return HttpResponseRedirect(reverse('slug_view', args=(post.slug,)))

    form = NewPostForm(request.POST or None,
            initial={'username': post.username,
                     'email': post.email,
                     'title': post.title,
                     'body': post.body})

    if form.is_valid():
        post.title = form.cleaned_data['title']
        post.body = form.cleaned_data['body']
        post.created = datetime.now()

        post.save()

        return HttpResponseRedirect(reverse('slug_view', args=(post.slug,)))

    return render(request, 'blog/new_post.html', {'form': form, 'slug': slug})


def new_post(request):
    if request.user.is_authenticated():
        username = request.user.username
        email = request.user.email
    else:
        username = "Anonmyous"
        email = "Anon@Anon.com"

    form = NewPostForm(request.POST or None,
            initial={'username': username, 'email': email})

    if form.is_valid():
        title = form.cleaned_data['title']
        body = form.cleaned_data['body']
        created = datetime.now()

        data = {'username': username, 'email': email,
                'body': body, 'created': created, 'title': title}

        p = Post(**data)

        p.save()

        return HttpResponseRedirect(reverse('slug_view', args=(p.slug,)))

    return render(request, 'blog/new_post.html', {'form': form})
