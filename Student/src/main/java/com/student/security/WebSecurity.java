package com.student.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception{

		http.csrf().disable().authorizeRequests()
		        .antMatchers(HttpMethod.POST).permitAll()
				.antMatchers(HttpMethod.GET).permitAll()
				.antMatchers(HttpMethod.POST).permitAll()
				.antMatchers(HttpMethod.DELETE).permitAll()
				.antMatchers(HttpMethod.PUT).permitAll()
				.antMatchers(HttpMethod.POST).permitAll()
				.anyRequest()
				.authenticated();
		
		http.headers().frameOptions().disable();
	}

	
}
