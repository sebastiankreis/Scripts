from django.contrib.syndication.views import Feed
from django.core.urlresolvers import reverse
from models import Post


class BlogRss(Feed):
    """Rss feed stream for our blog entries"""

    title = "Test Site blog feed"
    description = "Simple ramblings of a fool"
    link = "/feed/"

    def items(self):
        return Post.objects.all().order_by("-created")[:2]

    def item_title(self, item):
        return item.title

    def item_description(self, item):
        return item.body

    def item_link(self, item):
        return reverse('slug_view', args=(item.slug,))
