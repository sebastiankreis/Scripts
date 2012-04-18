from django.contrib import admin

from models import Page


class WikiAdmin(admin.ModelAdmin):
    prepopulated_fields = {'slug': ("title",)}

admin.site.register(Page, WikiAdmin)
