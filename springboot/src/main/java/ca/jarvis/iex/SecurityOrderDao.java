package ca.jarvis.iex;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SecurityOrderDao extends JpaRepository<SecurityOrder, Integer> {
    Optional<Account> deleteByAccountId(Integer integer);
}

