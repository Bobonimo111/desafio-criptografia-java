# Criptografy challenge
This API is developed as a solution for a  [challenge](https://github.com/backend-br/desafios) offered by [Back-End Brasil](https://github.com/backend-br), the official community hub for Brazilian backenders.

## Technologies Used
- Java 21
- Spring boot 
- Spring data JPA 
- java Cipher to criptografy AES 
- H2 database in memory

# Challenge description

Your challenge will be to implement encryption in a service transparently for both the API and the service layers of your application. The goal is to ensure that sensitive fields of entity objects are not directly visible. This will involve performing encryption at runtime during the conversion of entities to the corresponding columns in the database, and vice versa.

# Results description 
I have use AES criptografy, because is are more simple by RSA criptography

**First input**

![post01](https://github.com/Bobonimo111/desafio-criptografia-java/blob/main/assets/Envio.PNG)

**In database are encrypted**

![post03](https://github.com/Bobonimo111/desafio-criptografia-java/blob/main/assets/Banco%20de%20dados.PNG)

**API returned**

![post02](https://github.com/Bobonimo111/desafio-criptografia-java/blob/main/assets/Retorno.PNG)


