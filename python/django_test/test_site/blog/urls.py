from django.conf.urls.defaults import patterns, url, include
from django.views.generic import ListView, DetailView

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

    url(r'^archive/(?P<year>\d+)/(?P<month>\d+)/(?P<day>\d+)/$', 'blog.views.date_view'),
    url(r'^archive/(?P<year>\d+)/(?P<month>\d+)/$', 'blog.views.date_view'),
    url(r'^archive/(?P<year>\d+)/$', 'blog.views.date_view'),

    url(r'^feed/$', BlogRss()),

    url(r'^comments/', include('django.contrib.comments.urls')),

)
