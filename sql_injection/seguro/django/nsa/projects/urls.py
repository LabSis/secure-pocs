from django.conf.urls import url
from .views import index, proyectos_ajax, proyectos_filtrado_ajax, proyectos_filtrados_orm_ajax


urlpatterns = [
    url(r'^$', index, name='index'),
    url(r'^projects$', proyectos_ajax, name='projects'),
    url(r'^projects/filter$', proyectos_filtrado_ajax, name='vulnerable'),
]