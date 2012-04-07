from django.contrib import admin
from models import Post, Comment


class PostAdmin(admin.ModelAdmin):
    search_fields = ['title']


class CommentAdmin(admin.ModelAdmin):
    display_fields = ["post", "author", "created"]


admin.site.register(Comment, CommentAdmin)
admin.site.register(Post, PostAdmin)
