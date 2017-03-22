package seguridad;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import services.CurrentClientDetailsService;

/**
 * Configura la seguridad de acceso a servicios
 * @author jorge
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private CurrentClientDetailsService clientDetails;
	
	/**
	 * @see org.springframework.security.config.annotation.web.configuration
	 * .WebSecurityConfigurerAdapter#configure
	 * (org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http.csrf().disable().authorizeRequests()
	        .antMatchers("/").permitAll()
	        .antMatchers(HttpMethod.POST, "/auth").permitAll()
	        .anyRequest().authenticated()
	        .and()
	        // We filter the api/login requests
	        .addFilterBefore(new JWTLoginFilter("/auth", authenticationManager()),
	                UsernamePasswordAuthenticationFilter.class)
	        // And filter other requests to check the presence of JWT in header
	        .addFilterBefore(new JWTAuthenticationFilter(),
	                UsernamePasswordAuthenticationFilter.class);
	  }
	
	 @Override
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    
	    auth.userDetailsService(clientDetails)
        .passwordEncoder(new BCryptPasswordEncoder());
	  }
}
