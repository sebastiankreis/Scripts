from django.db import models
from django.template.defaultfilters import slugify
# Create your models here.


class Page(models.Model):
    title = models.CharField(max_length=30)
    content = models.TextField(blank=True)
    slug = models.SlugField()

    def save(self, *args, **kwargs):
        self.slug = slugify(self.title) + str(self.id)
        super(Page, self).save(*args, **kwargs)

    def __unicode__(self):
        return self.title