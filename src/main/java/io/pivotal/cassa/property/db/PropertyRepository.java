package io.pivotal.cassa.property.db;

import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;
import java.util.UUID;

public interface PropertyRepository extends CassandraRepository<PropertyEntity, PropertyKey> {
    List<PropertyEntity> findAllByEntrepreneurId(UUID entrepreneurId);
}
