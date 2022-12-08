package com.mundotaci.projetotaci.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.mundotaci.projetotaci.security.jwt.UtilCriptografia;
import com.mundotaci.projetotaci.security.jwt.CustomAuthenticationProvider;


@EnableWebSecurity
public class SpringSecurityConfig extends org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    
    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    // roles admin allow to access /admin/**
    // roles user allow to access /user/**
    // custom 403 access denied handler
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
					.antMatchers("/store/delete/**").hasAnyRole("ADMIN")
					.antMatchers("/store/**", "/index", "/").authenticated()
                .and()
                .formLogin()
                	.defaultSuccessUrl("/index")
					.loginPage("/store/login")
					.permitAll()
					.and()
                .logout()
					.permitAll()
					.and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	 auth.authenticationProvider(customAuthenticationProvider);
    }
    
    @Bean
	public PasswordEncoder getEncoder() {
		return new PasswordEncoder() {
	        @Override
	        public String encode(CharSequence charSequence) {
	        	return UtilCriptografia.md5( charSequence.toString() );

	        }

	        @Override
	        public boolean matches(CharSequence charSequence, String s) {	            
	        	return encode(charSequence).equals(s);
	        }
	    };	
	}
}