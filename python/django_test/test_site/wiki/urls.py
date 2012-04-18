from django.conf.urls.defaults import patterns, url
from django.views.generic.simple import direct_to_template


urlpatterns = patterns('',
    url(r'^$', direct_to_template, {'template': 'wiki/index.html'}),

    url(r'^(?P<page_name>[^/]+)/$', 'wiki.views.view_page', name="view_page"),
    url(r'^(?P<page_name>[^/]+)/edit$', 'wiki.views.edit_page', name="edit_page"),
    url(r'^(?P<page_name>[^/]+)/save$', 'wiki.views.save_page', name="save_page"),
)
