# aplikasi 2

## PRE REQUIREMENTS

- PosgresSQL
- Java Springboot
- Open Feign

## APPLICATION DEFINITION

- Project aplikasi 1 as Rest Server running on port 8081
- Project aplikasi 2 as Rest Client running on port 8082

## EXAMPLE API REQUEST

### POST DATA

Method: POST

uri: /v1/pegawai

payloads:

{ "name":"John Doe", "alamat":"Jakarta"}

response: {"id":1, "name":"John Doe", "alamat":"Jakarta"}

### GET DATA

Method: GET

uri:  /v1/pegawai/findById?id={id}

example uri:  /v1/pegawai/findById?id=1

response: {"id":1, "name":"John Doe", "alamat":"Jakarta"}

