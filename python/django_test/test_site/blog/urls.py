from django.conf.urls.defaults import patterns, url
from django.views.generic import ListView, DetailView
from django.views.generic.dates import MonthArchiveView

from models import Post

urlpatterns = patterns('',
    url(r'^$',
        ListView.as_view(
            queryset=Post.objects.order_by('-created'),
            context_object_name='posts',
            template_name="blog/index.html",
            paginate_by=2
            ),
        name="poll_list"
    ),

    # url(r'^articles/(?P<year>\d{4})/(?P<month>\d{2})/$',
    #     MonthArchiveView.as_view(
    #         model=Post,
    #         paginate_by=12,
    #         date_field="created",
    #         template_name="blog/monthly.html"
    #         ),
    #     name="monthly"
    # ),

    url(r'^articles/(?P<pk>\d+)/$',
        DetailView.as_view(
            model=Post,
            template_name="blog/article.html"
            ),
        name="post_detail"
    ),
#
)
