# Getting Started

## Setup Cassandra Database

### Start Cassandra Cluster in Docker
```
docker pull cassandra:latest

docker run -d -p9042:9042 --name cass_cluster cassandra:latest
```

### Start CQL Shell
```
docker exec -it cass_cluster cqlsh
```

### Create Cassandra keyspace
```
create keyspace bezkoder with replication={'class':'SimpleStrategy', 'replication_factor':1};
```

### Create tables and index in keyspace
```
use bezkoder;
 
CREATE TABLE monopoly(
   id uuid PRIMARY KEY,
   pot int
); 
 
CREATE TABLE entrepreneur(
   id uuid PRIMARY KEY,
   name text,
   tokentype text,
   funds double,
   monopolyid uuid,
   squareid int
);

CREATE INDEX monopoly_idx ON entrepreneur ( monopolyid );
```

### CQL
```cql
DROP TABLE <TableName>;

DESC[RIBE] keyspaces;
```

## Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.5/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.5/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.5/reference/htmlsingle/#web)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.7.5/reference/htmlsingle/#data.sql.jpa-and-spring-data)
* [Spring Data for Apache Cassandra](https://docs.spring.io/spring-boot/docs/2.7.5/reference/htmlsingle/#data.nosql.cassandra)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Spring Data for Apache Cassandra](https://spring.io/guides/gs/accessing-data-cassandra/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

