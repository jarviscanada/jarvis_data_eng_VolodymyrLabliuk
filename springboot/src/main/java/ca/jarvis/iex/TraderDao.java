package ca.jarvis.iex;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraderDao extends JpaRepository<Trader, Integer> {

}
