# ShoppingCartController API

El controlador `Servicio Carrito de Compras` proporciona endpoints para administrar un Carrito de Compras.

## Para probar el servicio - BD: Postgres

### Query y Postman
Ruta: /utilitarios

## Obtener todos los carros de compras

### Endpoint
GET /shopping-cart

#### Descripción del Request
- No se requiere cuerpo de la solicitud.

#### Descripción del Response
- Tipo de respuesta: JSON
- Ejemplo de respuesta:
```json
[
    {
        "idShoppingCart": 13,
        "codShoppingCard": "b57cbad7-bdc1-4b34-8a7a-17cbd73a6097",
        "creationDate": "2023-09-05T17:52:01.307650",
        "eventDate": null,
        "total": 200.00,
        "state": "1",
        "detailsShoppingCart": [
            {
                "quantity": 10,
                "product": {
                    "idProduct": 1,
                    "codProduct": "prod232",
                    "name": "PRODUCT",
                    "creationDate": "2023-08-08T00:00:00",
                    "price": 20.00,
                    "state": "1"
                },
                "subTotalPrice": 200.00
            }
        ]
    },
    {
        "idShoppingCart": 19,
        "codShoppingCard": "cb16a58c-2db4-4fa7-a12f-b4b8633344e4",
        "creationDate": "2023-09-05T18:16:01.013701",
        "eventDate": "2023-09-05T22:59:27.913748",
        "total": 275.00,
        "state": "1",
        "detailsShoppingCart": [
            {
                "quantity": 10,
                "product": {
                    "idProduct": 1,
                    "codProduct": "prod232",
                    "name": "PRODUCT",
                    "creationDate": "2023-08-08T00:00:00",
                    "price": 20.00,
                    "state": "1"
                },
                "subTotalPrice": 200.00
            },
            {
                "quantity": 5,
                "product": {
                    "idProduct": 2,
                    "codProduct": "prod238",
                    "name": "PRODUCT2",
                    "creationDate": "2023-08-08T00:00:00",
                    "price": 15.00,
                    "state": "1"
                },
                "subTotalPrice": 75.00
            }
        ]
    }
]

```
## Obtener un carro de compras por código

### Endpoint
GET /shopping-cart/{code}

#### Descripción del Request
- `{code}`: El código único del carro de compras que se desea obtener.

#### Descripción del Response
- Tipo de respuesta: JSON
- Ejemplo de respuesta:
```json
{
  "idShoppingCart": 23,
  "codShoppingCard": "e2e44515-e718-4533-a9a4-10739fb0dde7",
  "creationDate": "2023-09-06T16:45:40.759295",
  "eventDate": "2023-09-06T16:45:51.485060",
  "total": 275.00,
  "state": "1",
  "detailsShoppingCart": [
    {
      "quantity": 10,
      "product": {
        "idProduct": 1,
        "codProduct": "prod232",
        "name": "PRODUCT",
        "creationDate": "2023-08-08T00:00:00",
        "price": 20.00,
        "state": "1"
      },
      "subTotalPrice": 200.00
    },
    {
      "quantity": 5,
      "product": {
        "idProduct": 2,
        "codProduct": "prod238",
        "name": "PRODUCT2",
        "creationDate": "2023-08-08T00:00:00",
        "price": 15.00,
        "state": "1"
      },
      "subTotalPrice": 75.00
    }
  ]
}
```
## Crear un nuevo carro de compras

### Endpoint
POST /shopping-cart

#### Descripción del Request
- Tipo de solicitud: JSON 
- Ejemplo de solicitud:

```json
{
  "shoppingCart" : {
    "state": "1"
  },
  "shoppingCartDetail": [
    {
      "quantity": 10,
      "product": {
        "idProduct": 1,
        "codProduct": "prod232",
        "name": "PRODUCT",
        "creationDate": "2023-08-08T00:00:00",
        "price": 20.00,
        "state": "1"
      }
    },
    {
      "quantity": 5,
      "product": {
        "idProduct": 2,
        "codProduct": "prod238",
        "name": "PRODUCT2",
        "creationDate": "2023-08-08T00:00:00",
        "price": 15.00,
        "state": "1"
      }
    }
  ]
}
```
#### Descripción del Response
- Tipo de respuesta: JSON
- Ejemplo de respuesta:

```json
{
    "uuid": "e2e44515-e718-4533-a9a4-10739fb0dde7",
    "message": "Success",
    "code": "00"
}
```

## Agregar o actualizar un elemento en un carro de compras

### Endpoint
PUT /shopping-cart/addOrUpdateItem

#### Descripción del Request
- Tipo de solicitud: JSON
- Ejemplo de solicitud:

```json
{
  "codeShoppingCart" : "e2e44515-e718-4533-a9a4-10739fb0dde7",
  "shoppingCartDetail":
  {
    "quantity": 3,
    "product": {
      "idProduct": 1,
      "codProduct": "prod232",
      "name": "PRODUCT",
      "creationDate": "2023-08-08T00:00:00",
      "price": 20.00,
      "state": "1"
    }
  }

}
```
#### Descripción del Response
- Tipo de respuesta: JSON
- Ejemplo de respuesta:

```json
{
  "uuid": "e2e44515-e718-4533-a9a4-10739fb0dde7",
  "message": "Success",
  "code": "00"
}
```

## Eliminar un carro de compras por UUID

### Endpoint
DELETE /shopping-cart/{uuid}

#### Descripción del Request
- `{uuid}`: El identificador único del carro de compras que se desea eliminar.

#### Descripción del Response
- Tipo de respuesta: JSON
- Ejemplo de respuesta:

```json
{
  "code": "00",
  "message": "success"
}
```

## Eliminar carritos de compras inactivos por 10 minutos

### Funcionamiento
- Es un proceso Scheluder que obtiene la fecha actual donde se ejecuta el proceso (Zona horaria America/Lima) y resta 10 minutos
- Con esta fecha (-10 min) obtiene todos los carritos de compras que tenga el campo eventdate en la tabla shopping_cart
- Esta columna `eventdate` se actualiza cuando se realiza cualquiera de las siguientes operaciones:
    * Consulta de Carrito de Compra por Codigo de Carrito
    * Creacion de Carrito de Compra
    * Agregar o Actualizar Items del Carrito de Compra

- La solución fue pensada de esa forma, porque un servicio rest no debería guardar la sesión o el estado de la entidad.
- Todo evento de inactividad debe estar manejado desde la parte front. 


#### Descripción del Request
- No aplica

### Endpoint
No detalle, proceso programado.


#### Descripción del Response
- No aplica

