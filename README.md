<h1 align="center"> Voll Med Api </h1>
Voll Med Api es una aplicación Java desarrollada con Spring Boot que te permite generar consultas, dando de alta médicos y pacientes. Es una REST API, donde se utiliza el programa Insomnia para poder simular la interacción del usuario en una página web, cargando así la información y después pasando esa información a una base de datos en MySQL.

Tecnologías:

- Java
- Spring Boot
- JPA
- Jackson
- MySQL
- FlyWay
- Spring Security
- JWT Token
- Swagger UI (documentación)
- Insomnia

Funcionalidades:

- Protección con Spring Security, requiriendo así de inicar sesión y obtener tu token para poder realizar las diferentes acciones.
- Subir médicos y pacientes a la base de datos.
- Mostrar la lista de médicos y pacientes o alguno en específico.
- Eliminar médicos y pacientes de la base de datos.
- Generar consulta.

Objetivo:

Este proyecto fue desarrollado para aprender a crear Rest API's utilizando el framework Spring Boot, apoyandonos de programas como insomnia y utilizando diferentes extensiones que permitan crear una aplicación más robuzta realizando así también buenas practicas.

Primero debemos de iniciar sesión para poder trabajar

![Screenshot del login](https://github.com/Gasca78/Rest-API-with-Spring-Boot/blob/main/capturas/login.png)

si algún dato es incorrecto, no iniciaras sesión ni obtendras tu token

![Screenshot del login con error](https://github.com/Gasca78/Rest-API-with-Spring-Boot/blob/main/capturas/login_fallido.png)

Después pasamos a registrar un nuevo médico

![Screenshot del médico agregado](https://github.com/Gasca78/Rest-API-with-Spring-Boot/blob/main/capturas/registrar_medico.png)

si no pusiste algún dato, se te notificará

![Screenshot del médico sin un dato](https://github.com/Gasca78/Rest-API-with-Spring-Boot/blob/main/capturas/registrar_medico_fallido.png)

o también si no agregas el token brindado en el login, este no te permitirá realizar ningúna acción

![Screenshot de prohibido el registro](https://github.com/Gasca78/Rest-API-with-Spring-Boot/blob/main/capturas/registrar_medico_forbidden.png)

Proseguimos a mostrar la lista de médicos y también una búsqueda dirigida a este último médico agregado

![Screenshot de la lista de médicos](https://github.com/Gasca78/Rest-API-with-Spring-Boot/blob/main/capturas/lista_medico.png)

![Screenshot del médico recién agregado](https://github.com/Gasca78/Rest-API-with-Spring-Boot/blob/main/capturas/datos_medico.png)

Ahora veamos cómo se agrega un nuevo paciente

![Screenshot del proceso para agregar un paciente](https://github.com/Gasca78/Rest-API-with-Spring-Boot/blob/main/capturas/registrar_paciente.png)

igual si llega a faltar un dato, se hará saber

![Screenshot del proceso para agregar un paciente](https://github.com/Gasca78/Rest-API-with-Spring-Boot/blob/main/capturas/registrar_paciente_fallido.png)

aquí mostramos la lista de los pacientes

![Screenshot de la lista de pacientes](https://github.com/Gasca78/Rest-API-with-Spring-Boot/blob/main/capturas/listar_paciente.png)

Agendamos una consulta con el médico y paciente recién agregados

![Screenshot de la consulta agendada](https://github.com/Gasca78/Rest-API-with-Spring-Boot/blob/main/capturas/agendar_consulta.png)

No se puede agendar consultas en fechas pasadas, ni un mismo horario con el mismo médico o el mismo paciente y tampoco si alguno de los dos no esta registrado con anterioridad

![Screenshot de error de consulta por fecha](https://github.com/Gasca78/Rest-API-with-Spring-Boot/blob/main/capturas/agendar_consulta_fallida.png)

![Screenshot de error de consulta por repetir horario, médico y paciente](https://github.com/Gasca78/Rest-API-with-Spring-Boot/blob/main/capturas/agendar_consulta_fallida_2.png)

![Screenshot de error de consulta por falta del registro de alguno de los implicados](https://github.com/Gasca78/Rest-API-with-Spring-Boot/blob/main/capturas/agendar_consulta_fallida_3.png)

Eliminamos al médico y paciente

![Screenshot del médico recién eliminado](https://github.com/Gasca78/Rest-API-with-Spring-Boot/blob/main/capturas/borrar_medico.png)

![Screenshot del médico recién eliminado](https://github.com/Gasca78/Rest-API-with-Spring-Boot/blob/main/capturas/borrar_paciente.png)

Llegando así al final de la presentación de este proyecto, mostrando las diferentes funciones de la aplicación. ¡Gracias por leerme!

Agradecimientos:

Este proyecto no hubiera sido posible sin la ayuda de los profesores del curso de Spring Boot y la comunidad de Alura Latam. Gracias por su dedicación y por compartir sus conocimientos.
