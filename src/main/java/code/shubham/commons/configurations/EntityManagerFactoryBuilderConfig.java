package code.shubham.commons.configurations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@RequiredArgsConstructor
public class EntityManagerFactoryBuilderConfig {

    private final JpaProperties jpaProperties;

    @Bean
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
        return new EntityManagerFactoryBuilder(
                new HibernateJpaVendorAdapter(),
                jpaProperties.getProperties(),
                null
        );
    }
}
