package com.family.portal.config;

import com.family.portal.security.handler.PortalAuthenticationFailureHandler;
import com.family.portal.security.handler.PortalAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {


    @Autowired
    @Qualifier("authSuccessHandler")
    private AuthenticationSuccessHandler authSuccessHandler;

    @Autowired
    @Qualifier("authFailureHandler")
    private AuthenticationFailureHandler authFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/api/login*").permitAll()
                .anyRequest().authenticated()
//                .and().addFilterBefore(new CorsFilter(), UsernamePasswordAuthenticationFilter.class)
                .and()
                .formLogin()
                .loginProcessingUrl("/api/login")
                .successHandler(authSuccessHandler)
                .failureHandler(authFailureHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Autowired
    public void configureAuthManager(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
        authManagerBuilder.inMemoryAuthentication()
                .withUser("wangning")
                .password("{noop}www9825617")
                .roles("ADMIN")
                .authorities("WRITE", "READ");
    }

    @Bean
    public PortalAuthenticationSuccessHandler authSuccessHandler() {
        PortalAuthenticationSuccessHandler handler = new PortalAuthenticationSuccessHandler();
        return handler;
    }

    @Bean
    public PortalAuthenticationFailureHandler authFailureHandler() {
        PortalAuthenticationFailureHandler handler = new PortalAuthenticationFailureHandler();
        return handler;
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//        return source;
//    }

}
