package io.pivotal.cassa.property;

import org.springframework.data.cassandra.repository.CassandraRepository;

public interface PropertyRepository extends CassandraRepository<PropertyEntity, PropertyKey> {
}
