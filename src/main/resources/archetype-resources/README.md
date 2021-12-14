# Plugins de Jakarta Lemon

## Crear Capa de Persistencia JPA y Servicio
```
mvn jakarta-lemon:create-model 
```

El archivo `model.json` indica la estructura de las entidades a utilizar

## Agregar Plugin de Payara Micro

Creará hasta los archivos de post-boot-command con la conexión a la base de datos

```
mvn jakarta-lemon:add-payara-micro
```
