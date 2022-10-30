package com.bolsaideas.springboot.app;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.bolsaideas.springboot.app.auth.handler.LoginSuccessHandler;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class SpringSecurityConfig {

	@Autowired
	private LoginSuccessHandler successHandler;

	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Bean
	public UserDetailsService userDetailsService(AuthenticationManagerBuilder build) throws Exception {

		build.jdbcAuthentication()
				.dataSource(dataSource)
				.passwordEncoder(passwordEncoder)
				.usersByUsernameQuery("select username, password, enabled from users where username=?")
				.authoritiesByUsernameQuery(
						"select u.username, a.authority from authorities a inner join users u on (a.user_id=u.id) where u.username=?");

		return build.getDefaultUserDetailsService();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/images/**", "/listar**").permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.formLogin()
					.usernameParameter("username")
					.passwordParameter("password")
					.successHandler(successHandler)
					.loginPage("/login")
					.permitAll()
					.and()
				.logout()
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
					.permitAll()
					.and()
				.exceptionHandling()
				.accessDeniedPage("/error_403");

		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

}
