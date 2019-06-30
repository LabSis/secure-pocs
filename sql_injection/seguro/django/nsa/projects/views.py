import json
from django.shortcuts import render
from django.http import HttpResponse
from django.db import connection
from .models import Proyectos


def index(request):
    return render(request, 'projects/index.html')


def proyectos_ajax(request):
    respuesta = {}
    try:
        proyectos = Proyectos.objects.all().exclude(id_nivel__nombre='Top Secret')
        lista_proyectos = []
        for proyecto in proyectos:
            dic = {}
            dic['code'] = proyecto.codigo
            dic['name'] = proyecto.nombre
            dic['type'] = proyecto.id_tipo.nombre
            dic['level'] = proyecto.id_nivel.nombre
            dic['owner'] = proyecto.id_agente.nombre
            lista_proyectos.append(dic)
            respuesta['data'] = {
                'projects': lista_proyectos
            }
        respuesta['status'] = 'ok'
    except Exception as e:
        respuesta['status'] = 'error'

    return HttpResponse(json.dumps(respuesta), content_type='application/json')


def proyectos_filtrado_ajax(request):
    """
    Permite filtrar los proyectos según el tipo.
    Se realiza una consulta pura de SQL con parámetros :).
    """
    respuesta = {}
    tipo = request.GET.get('type', None)
    try:
        with connection.cursor() as cursor:
            sql = """
                SELECT proyectos.codigo, proyectos.nombre, agentes.nombre, agentes.apellido, tipos.nombre, niveles.nombre
                FROM proyectos INNER JOIN agentes ON (proyectos.id_agente = agentes.id)
                INNER JOIN tipos ON (proyectos.id_tipo = tipos.id)
                INNER JOIN niveles ON (proyectos.id_nivel = niveles.id)
                WHERE proyectos.id_nivel != (SELECT id FROM niveles WHERE nombre='Top Secret') AND tipos.id = %s
    
            """
            cursor.execute(sql, [tipo, ])
            rows = cursor.fetchall()
            proyectos = []
            for r in rows:
                proyecto = {}
                proyecto['code'] = r[0]
                proyecto['name'] = r[1]
                proyecto['owner'] = r[2]
                proyecto['type'] = r[4]
                proyecto['level'] = r[5]
                proyectos.append(proyecto)
            respuesta['status'] = 'ok'
            respuesta['data'] = {
                'projects': proyectos
            }
    except Exception as e:
        respuesta['status'] = 'error'
    return HttpResponse(json.dumps(respuesta), content_type='application/json')


def proyectos_filtrados_orm_ajax(request):
    """
    Permite filtrar los proyectos según el tipo.
    Se utiliza el ORM que brinda Django.
    """
    respuesta = {}
    tipo = request.GET.get('type', None)

    proyectos = Proyectos.objects.all().exclude(id_nivel__nombre='Top Secret')
    if tipo:
        proyectos = proyectos.filter(id_tipo=tipo)

    proyectos_list = []
    for p in proyectos:
        proyecto = {}
        proyecto['code'] = p.codigo
        proyecto['name'] = p.nombre
        proyecto['owner'] = p.id_agente.nombre
        proyecto['type'] = p.id_tipo.nombre
        proyecto['level'] = p.id_nivel.nombre
        proyectos_list.append(proyecto)
    respuesta['status'] = 'ok'
    respuesta['data'] = {
        'projects': proyectos_list
    }
    return HttpResponse(json.dumps(respuesta), content_type='application/json')
