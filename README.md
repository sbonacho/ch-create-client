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

- java.lang.NoSuchMethodError: org.springframework.util.Assert.state(ZLjava/util/function/Supplier;)V

Solved: Update to 2.0.0.M7 of spring-boot and 2.1.0.RC1 of spring-kafka adaptor.

- If spring boot starts and kafka is not up
    - 1. There is no error.
    - 2. If after that kafka starts CreateService never gets recovered. Restart service is needed.
- If kafka is stopped timeout return 200 OK

# TODO

- Kafka bus implementation separate to another module (Jar file)
- Events separate to another module (Jar file)


