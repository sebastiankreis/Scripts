from django.template.defaultfilters import slugify
from datetime import datetime, timedelta
from django.db import models
from django import forms


# Create your models here.
class Post(models.Model):
    title = models.CharField(max_length=60)
    username = models.CharField(max_length=30)
    email = models.CharField(max_length=30)
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


class NewPostForm(forms.Form):
    username = forms.CharField(max_length=30)
    email = forms.CharField(max_length=30)
    created = forms.DateTimeField(initial=datetime.now)
    title = forms.CharField(max_length=30)
    body = forms.CharField(widget=forms.widgets.Textarea())

    def __init__(self, *args, **kwargs):
        super(NewPostForm, self).__init__(*args, **kwargs)
        self.auto_id = "blog_form_%s"
