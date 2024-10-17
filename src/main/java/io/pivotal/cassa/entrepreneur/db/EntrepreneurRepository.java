package io.pivotal.cassa.entrepreneur.db;

import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;
import java.util.UUID;

public interface EntrepreneurRepository extends CassandraRepository<EntrepreneurEntity, UUID> {
    List<EntrepreneurEntity> findAllByMonopolyId(UUID monopolyId);
}
