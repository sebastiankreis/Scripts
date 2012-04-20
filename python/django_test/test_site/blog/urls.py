from django.conf.urls import patterns, url, include
from django.views.generic import ListView, DetailView
from blog.views import FilteredListView

from models import Post
from feed import BlogRss

urlpatterns = patterns('',
    url(r'^$',
        ListView.as_view(
            queryset=Post.objects.order_by('-created'),
            context_object_name='posts',
            template_name="blog/index.html",
            paginate_by=12
            ),
        name="poll_list"
    ),

    url(r'^article/(?P<slug>[\w\d\-]+)/$',
        DetailView.as_view(
            model=Post,
            template_name='blog/article.html'
            ),
        name="slug_view"
    ),

    url(r'^archive/(?P<year>\d+)/(?P<month>\d+)/(?P<day>\d+)/$',
        FilteredListView.as_view(
            model=Post,
            template_name="blog/index.html"
            )
    ),

    url(r'^archive/(?P<year>\d+)/(?P<month>\d+)/$', FilteredListView.as_view(
        model=Post,
        template_name="blog/index.html"
        )
    ),

    url(r'^archive/(?P<year>\d+)/$', FilteredListView.as_view(
        model=Post,
        template_name="blog/index.html"
        )
    ),

    url(r'^feed/$', BlogRss()),

    url(r'^comments/', include('django.contrib.comments.urls')),

)
