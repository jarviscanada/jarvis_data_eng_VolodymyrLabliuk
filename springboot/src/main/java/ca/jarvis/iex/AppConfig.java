package ca.jarvis.iex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class AppConfig {

    private Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.username}")
    private String datasourceUsername;

    @Value("${spring.datasource.password}")
    private String datasourcePassword;

    @Value("${spring.datasource.driver-class-name}")
    private String datasourceDriver;

    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String datasourceDialect;

    @Bean
    public MarketDataConfig marketDataConfig() {
        // Instantiate and configure MarketDataConfig
        return new MarketDataConfig();
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName(datasourceDriver)
                .url(datasourceUrl)
                .username(datasourceUsername)
                .password(datasourcePassword)
                .build();
    }

//    @Bean
//    public DataSource comboPooledDataSource(){
//        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
//        try {
//            comboPooledDataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
//            //comboPooledDataSource.setJdbcUrl("jdbc:mysql://database-1.c6eboyvoj802.us-east-2.rds.amazonaws.com:3306/somedb?useSSL=false");
//            comboPooledDataSource.setJdbcUrl("jdbc:mysql://usersdb-1.c6eboyvoj802.us-east-2.rds.amazonaws.com:3306/somedb?useSSL=false");
//            //comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/somedb?useSSL=false");
//            comboPooledDataSource.setUser("root");
//            comboPooledDataSource.setPassword("admin1990");
//            comboPooledDataSource.setInitialPoolSize(5);
//            comboPooledDataSource.setMinPoolSize(5);
//            comboPooledDataSource.setAcquireIncrement(5);
//            comboPooledDataSource.setMaxPoolSize(20);
//            comboPooledDataSource.setMaxStatements(100);
//        }
//        catch (PropertyVetoException e) {
//            e.printStackTrace();
//        }
//        return comboPooledDataSource;
//    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("ca.jarvis.iex");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        //em.setJpaProperties(additionalProperties());
        return em;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setPackagesToScan("ca.jarvis.iex");
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", datasourceDialect);
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        localSessionFactoryBean.setHibernateProperties(hibernateProperties);

        return localSessionFactoryBean;
    }

    // You can add more beans or configurations as needed
}

