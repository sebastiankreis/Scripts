from django.conf.urls import patterns, url


urlpatterns = patterns('',
    url(r'^$', 'wiki.views.landing_page', name="landing_page"),
    url(r'^page/(?P<page_name>[\w\-\s\\/]+)/$', 'wiki.views.view_page', name="view_page"),
    url(r'^page/(?P<page_name>[\w\-\s\\/]+)/edit$', 'wiki.views.edit_page', name="edit_page"),
    url(r'^page/(?P<page_name>[\w\-\s\\/]+)/save$', 'wiki.views.save_page', name="save_page"),
    url(r'^search/$', 'wiki.views.search', name="search"),
)
