package xyz.vegaone.andalusiabe.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AndalusiaBasicAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication()
                .withUser("dragos@mail.com").password("{noop}dragos").authorities("ROLE_USER", "ROLE_ADMIN")
                .and()
                .withUser("david@mail.com").password("{noop}david").authorities("ROLE_USER", "ROLE_ADMIN")
                .and()
                .withUser("oana@mail.com").password("{noop}oana").authorities("ROLE_USER", "ROLE_ADMIN")
                .and()
                .withUser("bogdan@mail.com").password("{noop}bogdan").authorities("ROLE_USER", "ROLE_ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/", "/home", "/swagger-ui.html", "/swagger-resources/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }
}
