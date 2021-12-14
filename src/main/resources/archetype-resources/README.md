# Plugins de Jakarta Lemon

## Crear Capa de Persistencia JPA y Servicio
```
mvn jakarta-lemon:create-model 
```

El archivo `model.json` indica la estructura de las entidades a utilizar

# Payara Micro

### Agregar Plugin de Payara Micro

Creará hasta los archivos de post-boot-command con la conexión a la base de datos

```
mvn jakarta-lemon:add-payara-micro
```

Para ejecutar Payara Micro desde la Línea de comandos:

```
mvn clean package -Ppayara-micro payara-micro:start
```

Para construir el bundle .jar del proyecto
```
mvn clean package -Ppayara-micro payara-micro:bundle
```

Y ejecutar 
```
java -jar target/${groupId}-${artifactId}-${version}-microbundle.jar --postbootcommandfile post-boot-commands.txt --addLibs target/lib
```