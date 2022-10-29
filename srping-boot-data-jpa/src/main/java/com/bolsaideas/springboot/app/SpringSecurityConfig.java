package com.bolsaideas.springboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.bolsaideas.springboot.app.auth.handler.LoginSuccessHandler;

@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
@Configuration
public class SpringSecurityConfig{
	
    @Autowired
    private LoginSuccessHandler successHandler;
	
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService() throws Exception {
		
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("rodrigo").password(passwordEncoder().encode("12345")).roles("USER").build());
		manager.createUser(
				User.withUsername("admin").password(passwordEncoder().encode("12345")).roles("ADMIN", "USER").build());
 
		return manager;
	}
	
	 @Bean
     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/images/**", "/listar**").permitAll()
         .anyRequest().authenticated()
         .and()
         .formLogin()
             .successHandler(successHandler)
             .loginPage("/login")
         .permitAll()
         .and()
         .logout().permitAll()
         .and()
         .exceptionHandling().accessDeniedPage("/error_403");
         
         return http.build();
     }
	
	 @Bean
     public AuthenticationManager authenticationManager() throws Exception {
       return authenticationConfiguration.getAuthenticationManager();
     }
	
}
