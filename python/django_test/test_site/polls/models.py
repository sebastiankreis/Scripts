from django.db import models
from datetime import datetime, timedelta


# Create your models here.
class Poll(models.Model):
    question = models.CharField(max_length=200)
    pub_date = models.DateTimeField('date published', default=datetime.now())

    def __unicode__(self):
        return self.question

    def published_recently(self):
        return self.pub_date >= (datetime.now() - timedelta(days=1))

    published_recently.admin_order_field = 'pub_date'
    published_recently.boolean = True
    published_recently.short_description = "Published Recently?"


class Choice(models.Model):
    poll = models.ForeignKey(Poll)
    choice = models.CharField(max_length=200)
    votes = models.IntegerField(default=0)

    def __unicode__(self):
        return self.choice
