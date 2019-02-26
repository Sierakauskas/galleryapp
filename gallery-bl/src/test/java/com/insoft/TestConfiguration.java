//package com.insoft;//package com.insoft.practice.bl.exception;
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.context.annotation.PropertySource;
////import org.springframework.core.env.Environment;
////import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
////import org.springframework.jdbc.datasource.DriverManagerDataSource;
////import org.springframework.transaction.annotation.EnableTransactionManagement;
////import javax.sql.DataSource;
////
////@Configuration
////@EnableJpaRepositories(basePackages = "com.insoft.practice.bl.repositories")
////@PropertySource("applicationh2.properties")
////@EnableTransactionManagement
////public class TestConfiguration {
////
////   @Autowired
////    private Environment env;
////
////    @Bean
////    public DataSource dataSource() {
////        DriverManagerDataSource dataSource = new DriverManagerDataSource();
////        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
////        dataSource.setUrl(env.getProperty("jdbc.url"));
////        dataSource.setUsername(env.getProperty("jdbc.user"));
////        dataSource.setPassword(env.getProperty("jdbc.pass"));
////
////        return dataSource;
////    }
////}
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//
///*@Configuration
//@EnableJpaRepositories(basePackages = {
//        "com.insoft.practice.bl.repositories",
//})
//@EnableTransactionManagement*/
//public class TestConfiguration {
//
//    @Bean
//    @Profile("test")
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.h2.Driver");
//        dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
//        dataSource.setUsername("sa");
//        dataSource.setPassword("sa");
//
//        return dataSource;
//    }
//
//    // configure entityManagerFactory
//    // configure transactionManager
//    // configure additional Hibernate properties
//}