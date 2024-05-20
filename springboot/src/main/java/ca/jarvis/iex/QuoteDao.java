//package ca.jarvis.iex;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import javax.transaction.Transactional;
//
//@Repository
//public class QuoteDao {
//
//    private final SessionFactory sessionFactory;
//    @Autowired
//    IexQuoteJpaRepository iexQuoteJpaRepository;
//
//    @Autowired
//    public QuoteDao(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Transactional
//    public void save(IexQuote quote){
//        iexQuoteJpaRepository.save(quote);
////        Session session = sessionFactory.getCurrentSession();
////        session.saveOrUpdate(quote);
//    }
//
//    @Transactional
//    public void saveAll(Iterable<IexQuote> quotes){
//        iexQuoteJpaRepository.saveAll(quotes);
////        Session session = sessionFactory.getCurrentSession();
////        session.saveOrUpdate(quote);
//    }
//}

package ca.jarvis.iex;
import ca.jarvis.iex.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuoteDao extends JpaRepository<Quote, String> {
//    Quote save(Quote quote);
    //List<Quote> saveAll(List<Quote> quotes);
//    List<Quote> findAll();
    Optional<Quote> findQuoteByTicker(String ticker);
//    boolean existsById(String ticker);
//    void deleteById(String ticker);
//    long count();
//    void deleteAll();
}