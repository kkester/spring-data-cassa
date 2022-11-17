package io.pivotal.cassa;

import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface EntrepreneurRepository extends CassandraRepository<Entrepreneur, UUID> {
}
