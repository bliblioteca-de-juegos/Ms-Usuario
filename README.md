# Ms-Usuario

Microservicio encargado de administrar usuarios y autenticacion del sistema.

## Responsabilidad

- Registrar usuarios.
- Iniciar sesion.
- Generar JWT.
- Consultar usuarios.
- Actualizar y eliminar usuarios.
- Proteger rutas sensibles con Bearer Token.

## Datos tecnicos

| Item | Valor |
| --- | --- |
| Puerto | `8083` |
| Base de datos | `usuarios_db` |
| Ruta usuarios | `/api/v2/usuarios` |
| Ruta auth | `/api/v2/auth` |
| Swagger | `http://localhost:8083/doc/swagger-ui.html` |
| Eureka name | `ms-usuario` |

## Endpoints principales

- `POST /api/v2/auth/register`
- `POST /api/v2/auth/login`
- `GET /api/v2/usuarios`
- `GET /api/v2/usuarios/{id}`
- `GET /api/v2/usuarios/buscar`
- `GET /api/v2/usuarios/email`
- `POST /api/v2/usuarios`
- `PUT /api/v2/usuarios/{id}`
- `DELETE /api/v2/usuarios/{id}`

## Usuario demo

```json
{
  "nombreUsuario": "D4V1Cyberpunk",
  "password": "password"
}
```

## Seguridad

El login entrega un token JWT que debe usarse en rutas protegidas:

```text
Authorization: Bearer <token>
```

## Ejecucion local

```bash
./mvnw spring-boot:run
```

## Ejecucion con Docker

Desde la repo `Infraestructura`:

```bash
docker compose up -d --build ms-usuario
```

