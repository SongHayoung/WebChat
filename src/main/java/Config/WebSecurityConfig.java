package Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
@ComponentScan(basePackages = {"Chatting.Jwt"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .headers().frameOptions().sameOrigin()
                .and().formLogin()
                .and().authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
                .anyRequest().hasRole("USER");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1")
                .password("{noop}user1")
                .roles("USER")
                .and()
                .withUser("user2")
                .password("{noop}user2")
                .roles("GUEST");
    }
}
