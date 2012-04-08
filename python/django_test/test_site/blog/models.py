from django.template.defaultfilters import slugify
from datetime import datetime, timedelta
from django.db import models


# Create your models here.
class Post(models.Model):
    title = models.CharField(max_length=60)
    body = models.TextField()
    created = models.DateTimeField(default=datetime.now())
    slug = models.SlugField()

    def save(self, *args, **kwargs):
        self.slug = slugify(self.title)
        super(Post, self).save(*args, **kwargs)

    def get_absolute_url(self):
        return self.created.strftime("%Y/%b/%d").lower()

    def __unicode__(self):
        return self.title

    def published_recently(self):
        return self.created >= (datetime.now() - timedelta(days=1))

    published_recently.admin_order_field = 'created'
    published_recently.boolean = True
    published_recently.short_description = "Published Recently?"
