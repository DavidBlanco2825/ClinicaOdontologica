# Sistema de Reserva de Turnos para Clínica Odontológica

## Descripción del Proyecto

El proyecto consiste en un sistema de reserva de turnos para una clínica odontológica que permite la administración de datos de odontólogos y pacientes, así como la asignación de turnos. Este sistema está desarrollado en capas utilizando Spring Boot y sigue una arquitectura bien definida para separar las responsabilidades de cada componente.

## Funcionalidades
- **Administración de odontólogos**: Permite listar, agregar, modificar y eliminar odontólogos. Cada odontólogo tiene un apellido, nombre y matrícula.
- **Administración de pacientes**: Permite listar, agregar, modificar y eliminar pacientes. Cada paciente tiene un nombre, apellido, domicilio, DNI y fecha de alta.
- **Registro de turnos**: Permite asignar un turno a un paciente con un odontólogo en una fecha y hora específicas.
- **Login**: Permite la validación de usuarios mediante login con usuario y contraseña. Usuarios con rol `ROLE_USER` pueden registrar turnos, mientras que usuarios con rol `ROLE_ADMIN` pueden gestionar odontólogos y pacientes.

## Ejecución del Proyecto

### Requisitos Previos
- Docker instalado en el sistema.

### Instrucciones para Ejecutar el Proyecto

#### En Windows:
```sh
docker compose -f docker-compose.yml up --build -d
```

#### En Linux:
```sh
sudo docker compose -f docker-compose.yml up --build -d
```

#### En Mac:
```sh
docker compose -f docker-compose.yml up --build -d
```

## Acceso a la Documentación

La documentación del proyecto, generada con Swagger, puede encontrarse en la siguiente URL una vez que el proyecto esté en ejecución:
```sh
http://localhost:8080/swagger-ui.html
```

## Usuarios de Prueba

Para ingresar a la aplicación, puedes utilizar las siguientes credenciales:

### Credenciales de Usuario (ROLE_USER):
- **Username**: avril@user.com
- **Password**: user

### Credenciales de Administrador (ROLE_ADMIN):
- **Username**: david@admin.com
- **Password**: admin

### Credenciales de Administrador (ROLE_ADMIN):
- **Username**: victor@admin.com
- **Password**: admin

## Estructura del Proyecto

El proyecto está organizado en las siguientes capas:

- **Entidades de negocio**: Clases Java que representan los modelos del negocio.
- **Acceso a datos**: Repositorios que gestionan la persistencia de los datos.
- **Negocio**: Servicios que contienen la lógica de negocio.
- **Presentación**: Controladores y vistas para la interacción con el usuario.
- **DTO y Mappers**: Uso de Data Transfer Objects (DTO) para transferir datos entre la capa de presentación y la capa de negocio, y mappers (MapStruct) para transformar entre entidades y DTOs.

## Manejo de Excepciones

El sistema maneja las excepciones de forma centralizada, logueando cualquier excepción que se pueda generar para facilitar la depuración y mantenimiento del sistema.

## Test Unitarios

Se han implementado test unitarios para garantizar la calidad de los desarrollos y asegurar que cada componente funcione correctamente.

---
