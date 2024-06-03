package ca.jarvis.iex;
import ca.jarvis.iex.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuoteDao extends JpaRepository<Quote, String> {;
    Optional<Quote> findQuoteByTicker(String ticker);
}