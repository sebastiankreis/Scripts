from django.db import models
from datetime import datetime, timedelta


# Create your models here.
class Post(models.Model):
    title = models.CharField(max_length=60)
    body = models.TextField()
    created = models.DateTimeField(default=datetime.now())

    def __unicode__(self):
        return self.title

    def published_recently(self):
        return self.created >= (datetime.now() - timedelta(days=1))

    published_recently.admin_order_field = 'created'
    published_recently.boolean = True
    published_recently.short_description = "Published Recently?"


class Comment(models.Model):
    created = models.DateTimeField(auto_now_add=True)
    author = models.CharField(max_length=60)
    body = models.TextField()
    post = models.ForeignKey(Post)

    def __unicode__(self):
        return unicode("%s: %s" % (self.post, self.body[:60]))
