# HashTranslator. An application for hash decryption.

## Description

The application should consist of two interacting services: an authentication service and a hash decryption service.

The authentication service is responsible for user authentication and provides an API for creating (deleting) users.
User creation (deletion) should be performed by a pre-configured administrator of the service, who provides
authentication data to users. User information should contain an email and password.

The hash decryption service provides an API for submitting an application for decryption of MD5 hashes. It interacts
with the authentication service and returns a unique application ID. The application includes an array of 1+ hashes.

The user should be able to send a request (specifying the application ID) and receive the result of processing the
request. HashTranslator itself does not perform hash decryption, but delegates this operation to public free services.