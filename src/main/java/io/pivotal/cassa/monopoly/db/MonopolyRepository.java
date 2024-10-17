package io.pivotal.cassa.monopoly.db;

import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface MonopolyRepository extends CassandraRepository<MonopolyEntity, UUID> {
}
