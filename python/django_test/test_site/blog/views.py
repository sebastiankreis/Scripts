from django.views.generic import ListView
from django.core.urlresolvers import reverse
from django.http import HttpResponseRedirect
from django.shortcuts import render
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


def new_post(request):
    if request.user.is_authenticated():
        username = request.user.username
        email = request.user.email
    else:
        username = "Anonmyous"
        email = "Anon@Anon.com"

    if request.method == 'POST':
        form = NewPostForm(request.POST)

        if form.is_valid():
            title = form.cleaned_data['title']
            body = form.cleaned_data['body']
            created = datetime.now()

            data = {'username': username, 'email': email,
                    'body': body, 'created': created, 'title': title}

            p = Post(**data)

            p.save()

            return HttpResponseRedirect(reverse('slug_view', args=(p.slug,)))
    else:
        form = NewPostForm(initial={'username': username, 'email': email})

    return render(request, 'blog/new_post.html', {'form': form})
