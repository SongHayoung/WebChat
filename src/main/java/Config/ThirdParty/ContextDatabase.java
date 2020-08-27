package Config.ThirdParty;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:/properties/dataSource.properties")
@Import({ContextMyBatis.class, ContextHibernate.class})
public class ContextDatabase {
    @Value("${datasource.driverClass}")String driver;
    @Value("${datasource.username}") String user;
    @Value("${datasource.password}") String password;
    @Value("${datasource.url}") String url;
    @Value("${datasource.initialSize}") int initialSize;
    @Value("${datasource.maxTotal}") int maxTotal;
    @Value("${datasource.maxIdle}") int maxIdle;
    @Value("${datasource.minIdle}") int minIdle;
    @Value("${datasource.maxWaitMillis}") int maxWaitMillis;
    @Value("${datasource.testOnBorrow}") boolean testOnBorrow;
    @Value("${datasource.testWhileIdle}") boolean testWhileIdle;
    @Value("${datasource.validationQuery}") String validationQuery;
    @Value("${datasource.minEvictableIdleTimeMillis}") int minEvictableIdleTimeMillis;
    @Value("${datasource.timeBetweenEvictionRunsMillis}") int timeBetweenEvictionRunsMillis;
    @Value("${datasource.numTestsPerEvictionRun}") int numTestsPerEvictionRun;
    @Value("${datasource.poolPreparedStatements}") boolean poolPreparedStatements;
    @Value("${datasource.maxOpenPreparedStatements}") int maxOpenPreparedStatements;

    @Bean
    public DataSource dataSource() {
        BasicDataSource source = new BasicDataSource();
        source.setDriverClassName(driver);
        source.setUrl(url);
        source.setUsername(user);
        source.setPassword(password);
        source.setInitialSize(initialSize);
        source.setMaxTotal(maxTotal);
        source.setMaxIdle(maxIdle);
        source.setMinIdle(minIdle);
        source.setMaxWaitMillis(maxWaitMillis);
        source.setTestOnBorrow(testOnBorrow);
        source.setTestWhileIdle(testWhileIdle);
        source.setValidationQuery(validationQuery);
        source.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        source.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        source.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        source.setPoolPreparedStatements(poolPreparedStatements);
        source.setMaxOpenPreparedStatements(maxOpenPreparedStatements);

        return source;
    }
/*
    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager tm = new DataSourceTransactionManager();
        tm.setDataSource(dataSource());
        return tm;
    }*/
}
