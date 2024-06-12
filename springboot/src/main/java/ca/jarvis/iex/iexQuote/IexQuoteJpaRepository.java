package ca.jarvis.iex.iexQuote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IexQuoteJpaRepository extends JpaRepository<IexQuote, String> {

}
