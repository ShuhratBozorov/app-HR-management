package uz.pdp.apphrmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditingConfig {
    @Bean
    SpringSecurityAuditAware auditorAware(){
        return new SpringSecurityAuditAware();
    }
}
