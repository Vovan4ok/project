package project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import project.security.CustomUserDetails;
import project.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = CustomUserDetails.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll().antMatchers("/home").access("hasRole('DEFAULT') or hasRole('ADMIN')").
                antMatchers("/userFaculties", "/application", "/listOfApplicants").access("hasRole('DEFAULT')")
                .antMatchers("/adminFaculties", "/acceptApplication", "/addFaculty", "/update", "/delete").access("hasRole('ADMIN')")
                .anyRequest().permitAll().and().formLogin().loginPage("/login").defaultSuccessUrl("/home")
                .usernameParameter("email").passwordParameter("password").and().logout()
                .logoutSuccessUrl("/login?logout").and().exceptionHandling().accessDeniedPage("/403").and().csrf();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
