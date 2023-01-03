package iqabiloglu.employeems.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static iqabiloglu.employeems.model.enums.UserRoles.ADMIN_ROLES;
import static iqabiloglu.employeems.model.enums.UserRoles.USER_ROLES;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/v1/positions").permitAll()
                .antMatchers("/v1/departments").hasRole(USER_ROLES.name())
                .antMatchers("/v1/**").hasRole(ADMIN_ROLES.name())
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService( ) {

        UserDetails ibrahim = User.builder()
                                  .username("ibm")
                                  .password(passwordEncoder.encode("ibm123"))
                                  .roles(ADMIN_ROLES.name())
                                  .build();

        UserDetails kamil = User.builder()
                                .username("kamil")
                                .password(passwordEncoder.encode("km123"))
                                .roles(USER_ROLES.name())
                                .build();

        return new InMemoryUserDetailsManager(ibrahim, kamil);
    }

}
