package com.luv2code.springboot.cruddemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.luv2code.springboot.cruddemo.BootAuthenticationEntryPoint;
import com.luv2code.springboot.cruddemo.filter.JwtRequestFilter;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringbootSecurityJWTConfigurer extends WebSecurityConfigurerAdapter {

	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder(12);
	}
	
	@Autowired
	private BootAuthenticationEntryPoint  bootAuthenticationEntryPoint;
	
	@Autowired 
	private UserDetailsService userDetailsService;
	
	@Autowired 
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService);
		
	} // End of configureGlobal()
	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors().and().csrf().disable()
		    .authorizeRequests().antMatchers("/api/users","/api/login","/api/assets/{pageNumber}/{itemsPerPage}","/api/users/{id}","/api/requests/{id}","/api/assets/{id}","/api/requests/{pageNumber}/{itemsPerPage}/{fieldName}","/api/assets/{pageNumber}/{itemsPerPage}/{fieldName}").permitAll()
		    .and()
		    .authorizeRequests().antMatchers("/api/assets","/api/requests","api/applicationapprove/{appId}","api/applicationreject/{appId}","api/approvedrequests","/applicationreject/{appId}").hasRole("ADMIN")
		    .and()
		    .authorizeRequests().antMatchers("/api/Assets","/api/Assets/{id}","/api/myrequests/{id}","/api/myapprovals/{id}").hasRole("MANAGER")
		    .anyRequest().authenticated()
		    .and()
		    .exceptionHandling().authenticationEntryPoint(bootAuthenticationEntryPoint)
		    .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	
		    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
		
	}// End of configure()
	
	
	
}// end of class
