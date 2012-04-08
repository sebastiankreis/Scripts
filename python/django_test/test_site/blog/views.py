from django.views.generic.list_detail import object_list
from models import Post


def date_view(request, year, month=None, day=None):
    posts = Post.objects.filter(created__year=year)

    if month:
        posts = posts.filter(created__month=month)

    if day:
        posts = posts.filter(created__day=day)

    return object_list(request, template_name='blog/index.html',
         queryset=posts, paginate_by=2)
