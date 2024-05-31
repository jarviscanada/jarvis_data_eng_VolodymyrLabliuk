package ca.jarvis.iex;

import org.springframework.data.jpa.repository.JpaRepository;
import ca.jarvis.iex.Position;

import java.util.List;
import java.util.Optional;

public interface PositionDao extends JpaRepository<Position, Integer> {
    Optional<List<Position>> findByAccountId(Integer integer);
}

