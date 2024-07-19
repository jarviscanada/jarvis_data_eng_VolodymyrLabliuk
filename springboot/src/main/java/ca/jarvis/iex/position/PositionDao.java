package ca.jarvis.iex.position;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PositionDao extends JpaRepository<Position, Integer> {
    Optional<Position> findByAccountId(Integer integer);
    Position findByAccountIdAndTicker(Integer accountId, String ticker);
}

