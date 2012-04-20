from django.views.generic import ListView
from django.http import HttpResponseRedirect
from django.shortcuts import render_to_response, get_object_or_404
from django.template import RequestContext
from django.core.urlresolvers import reverse

from models import Post


class FilteredListView(ListView):
    paginate_by=2

    def get(self, request, year, month=None, day=None):
        super(FilteredListView, self).__init__()
        posts = Post.objects.filter(created__year=year)

        if month:
            posts = posts.filter(created__month=month)

        if day:
            posts = posts.filter(created__day=day)

        return render_to_response('blog/index.html',
                {'posts': posts}, context=RequestContext(request))

