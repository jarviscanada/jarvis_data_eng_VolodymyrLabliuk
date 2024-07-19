package ca.jarvis.iex.quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuoteDao extends JpaRepository<Quote, String> {;
    Optional<Quote> findQuoteByTicker(String ticker);
}