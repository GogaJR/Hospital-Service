package am.initsolutions.config;

import am.initsolutions.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/loginSuccess")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/mainAdmin").hasAuthority("ADMIN")
                .antMatchers("/doctorPage").hasAuthority("DOCTOR")
                .antMatchers("/departmentAdmin").hasAuthority("HOSPITAL_ADMIN")
                .antMatchers("/pharmacyAdmin").hasAuthority("PHARMACY_ADMIN")
                .antMatchers("/moderator").hasAuthority("PHARMACY_MEDICINE_MODERATOR")
                .antMatchers("/patient").hasAuthority("PATIENT")
                .and()
                .sessionManagement().maximumSessions(1)
                .sessionRegistry(sessionRegistry());
        }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

}
