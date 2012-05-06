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
            template_name="blog/index.html"
            ),
        name="post_list"
    ),

    url(r'^article/(?P<slug>[\w\d\-]+)/$',
        DetailView.as_view(
            model=Post,
            template_name='blog/article.html',
            ),
        name="slug_view"
    ),

    url(r'^archive/(?P<year>\d+)/(?P<month>\d+)/(?P<day>\d+)/$',
        FilteredListView.as_view(model=Post)
    ),

    url(r'^archive/(?P<year>\d+)/(?P<month>\d+)/$',
        FilteredListView.as_view(model=Post)
    ),

    url(r'^archive/(?P<year>\d+)/$',
        FilteredListView.as_view(model=Post)
    ),

    url(r'^feed/$', BlogRss()),

    url(r'article/(?P<slug>[\w\d\-]+)/edit$', 'blog.views.edit_post', name="edit_post"),

    url(r'^post/', 'blog.views.new_post', name="new_post"),

    url(r'^comments/', include('django.contrib.comments.urls')),

)
