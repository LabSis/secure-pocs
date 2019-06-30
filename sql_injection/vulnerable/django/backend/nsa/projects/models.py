from django.db import models


class Agentes(models.Model):
    nombre = models.CharField(max_length=50, blank=True, null=True)
    apellido = models.CharField(max_length=50, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'agentes'


class Niveles(models.Model):
    nombre = models.CharField(max_length=50)

    class Meta:
        managed = False
        db_table = 'niveles'


class Proyectos(models.Model):
    codigo = models.CharField(unique=True, max_length=8)
    nombre = models.CharField(max_length=50, blank=True, null=True)
    id_agente = models.ForeignKey(Agentes, models.SET_NULL, db_column='id_agente', blank=True, null=True)
    id_tipo = models.ForeignKey('Tipos', models.SET_NULL, db_column='id_tipo', blank=True, null=True)
    id_nivel = models.ForeignKey(Niveles, models.SET_NULL, db_column='id_nivel', blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'proyectos'


class Tipos(models.Model):
    nombre = models.CharField(max_length=50)

    class Meta:
        managed = False
        db_table = 'tipos'
