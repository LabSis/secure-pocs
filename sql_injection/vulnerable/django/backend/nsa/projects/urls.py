from django.conf.urls import url
from .views import index, proyectos_ajax, vulnerable_ajax


urlpatterns = [
    url(r'^$', index, name='index'),
    url(r'^projects$', proyectos_ajax, name='projects'),
    url(r'^projects/filter$', vulnerable_ajax, name='vulnerable'),
]
