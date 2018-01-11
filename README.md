# Command Handler - Create Client

This microservice acts as a Command Handler API for the functionality Create Client in a Insurance POC.
  

# Docker Generation

```
mvn install dockerfile:build
```

# Run the service

This command starts the service with pe-proxy name

```
docker run --rm -p 8080:8080 -dit --name ch-create-client soprasteria/ch-create-client
```

Watching logs

```
docker logs ch-create-client -f
```

Stopping the service

```
docker stop ch-create-client
```

# Issues

java.lang.NoSuchMethodError: org.springframework.util.Assert.state(ZLjava/util/function/Supplier;)V

Solved: Update to 2.0.0.M7 of spring-boot and 2.1.0.RC1 of spring-kafka adaptor.

- Problem deserializating object from JSON. (workaround: introducing object into a ArrayList)  

# TODO

- Kafka api separate to another module (Jar file)
