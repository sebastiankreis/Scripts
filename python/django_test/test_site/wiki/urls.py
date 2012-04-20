from django.conf.urls import patterns, url
from django.views.generic import TemplateView


urlpatterns = patterns('',
    url(r'^$', TemplateView.as_view(template_name='wiki/index.html')),
    url(r'^(?P<page_name>[^/]+)/$', 'wiki.views.view_page', name="view_page"),
    url(r'^(?P<page_name>[^/]+)/edit$', 'wiki.views.edit_page', name="edit_page"),
    url(r'^(?P<page_name>[^/]+)/save$', 'wiki.views.save_page', name="save_page"),
)
