package code.shubham.commons.configurations;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "code.shubham.demo.pagination.cursor.dao.repositories.read",
        entityManagerFactoryRef = "replicaEntityManagerFactory",
        transactionManagerRef = "replicaTransactionManager"
)
public class ReplicaDataSourceConfig {

    @Bean(name = "replicaDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.replica")
    public DataSource replicaDataSource() {
        return new HikariDataSource();
    }

    @Bean(name = "replicaEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean replicaEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("replicaDataSource") DataSource replicaDataSource) {
        return builder
                .dataSource(replicaDataSource)
                .packages("code.shubham") // Replace with your replica entity package
                .persistenceUnit("replica")
                .build();
    }

    @Bean(name = "replicaTransactionManager")
    public JpaTransactionManager replicaTransactionManager(
            @Qualifier("replicaEntityManagerFactory") EntityManagerFactory replicaEntityManagerFactory) {
        return new JpaTransactionManager(replicaEntityManagerFactory);
    }
}
