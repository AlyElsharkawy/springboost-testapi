# Springboot Test RESTful API

# Installation and Usage:

This is a java springboot application. Thus, installation and usage are quite easy. You must first clone the repo and then run the project. The dependencies will be downloaded when you first run the project. By default, the project's `pom.xml` defaults to Java 24. The project was also tested on Java 17 and should be compatible with all Java version in between.  Installation commands are below

```bash
git clone https://github.com/AlyElsharkawy/springboost-testapi.git
cd ./springboot-testapi
mvn spring-boot:run
```

### Note:

You will need to edit the application.properties file so that it points to a valid postgresql database. Keep in mind that dummy data will be inserted for you and that the default settings are to **drop existing tables** on creation!

# Endpoint Documentation:

## Bill of Lading:

**Get All Bills:** `GET /Bills`

This returns a list containing all of the Bills of Lading  in the database. Each entry in the list contains:

- nbr

- companyName

**Get Bill:** `GET /Bills/:id`

This returns a single Bill of Lading entry whose id is provided in the path parameter.

**Get Complete Bill:** `GET /Bills/Complete/:id`

This returns the information for a single Bill of Lading alongside its assosciated Bills of Lading details in a list for the Bill of Lading whose ID is provided in the path parameter. An output example is below

```json
{
  "nbr": "35747437",
  "companyName": "First Company",
  "details": [
    {
      "serial": 1000,
      "hscodeName": "Fruits",
      "bolNbr": "35747437",
      "weight": 100,
      "count": 2,
      "volume": 3
    },
    {
      "serial": 1100,
      "hscodeName": "Medicine",
      "bolNbr": "35747437",
      "weight": 1000,
      "count": 5,
      "volume": 4
    }
  ]
}
```

**Update Bill of Lading:** `PUT /Bills/:id`

This updates the HS code whose ID is provided in the path parameter. The string `nbr` and integer `companyId` must be provided. If the operation is succesful, then the `updateTimestamp` field will be set or updated.

**Delete Bill of Lading:** `DELETE /Bills/:id`

This deletes the Bill of Lading whose ID is provided in the path parameter.

## Bill of Lading Detail:

**Get All Bill Details:** `GET /Bills/Detail`

This returns a list containing all of the Bill of Lading details in the database. Each entry in the list contains:

- serial

- hscodeName

- bolNbr

- weight

- count

- volume

**Get Bill Detail** `GET /Bills/Detail/:id`

This returns a single Bill of Lading detail entry whose id is provided in the path parameter.

**Create Bill Detail:** `POST /Bills/Detail`

This creates a HS code entry. The integers `serial`, `hscodeId`, `bolId`, and `count` have to be provided alongside the floats `weight` and `volume`

**Delete Bill Detail:** `DELETE /Bill/Detail/:id`

This deletes the Bill of Lading detail whose ID is provided in the path parameter.

**Update Bill Detail:** `PUT /Bills/Detail/:id`

This updates the Bill of Lading detail whose ID is provided in the path parameter. The integers `serial`, `hscodeId`, `bolId`, and `count` have to be provided alongside the floats `weight` and `volume`. If the operation is succesful, then the `updateTimestamp` field will be set or updated.

**Updating Bill Shipping Information:** `PATCH /Bills/Detail/:id`

This updates the Bill of Lading detail's shipping information whose ID is provided in the path parameter. Only the floats `weight` and `volume` have to be provided alongside the integer `count`. If the operation is succesful, then the `updateTimestamp` field will be set or updated.

## HS Code:

**Get All Codes:** `GET /HSCode`

This returns a list containing all of the HS codes in the database. Each entry in the list contains:

- id

- code

- name

- creationTimestamp

- updateTimestamp

**Get Single Code:** `GET /HSCode/:id`

This returns a single HS code entry whose id is provided in the path parameter.

**Create HS code:** `POST /HSCode`

This creates a HS code entry. Only the strings `name` and `code` have to be provided.

**Delete HS code:** `DELETE /HSCode/:id`

This deletes the HS code whose ID is provided in the path parameter.

**Update HS code:** `PUT /HSCode/:id`

This updates the HS code whose ID is provided in the path parameter. The strings `name` and `code` must be provided. If the operation is succesful, then the `updateTimestamp` field will be set or updated.

## Company:

**Get all Companies:** `GET /Company`

This returns a list containing all of the HS codes in the database. Each entry in the list contains:

- name

- taxNumber

**Get Single Company:** `GET /Company/:id`

This returns a single company entry whose id is provided in the path parameter.\

**Create Company:** `POST /Company`

This creates a company entry. Only the strings `name` and `taxNumber` have to be provided.

**Delete HS code:** `DELETE /Company/:id`

This deletes the company whose ID is provided in the path parameter.

**Update Company:** `PUT /Company/:id`

This updates the company whose ID is provided in the path parameter. The strings `name` and `taxNumber` must be provided. If the operation is succesful, then the `updateTimestamp` field will be set or updated.
