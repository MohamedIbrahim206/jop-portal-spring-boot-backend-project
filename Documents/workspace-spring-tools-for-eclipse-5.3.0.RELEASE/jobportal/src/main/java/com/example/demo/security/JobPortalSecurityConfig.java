package com.example.demo.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.demo.security.filter.JwtTokenValidatorFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class JobPortalSecurityConfig {
	
	@Qualifier("publicPaths")
    private final List<String> publicPaths;

    @Qualifier("securedPaths")
    private final List<String> securedPaths;
	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) {
		
		return http.csrf(csrfConfig -> csrfConfig.disable())
				.cors(corsConfig -> corsConfig.configurationSource(corsConfigurationSource()))
                    .authorizeHttpRequests(requests -> {
                        publicPaths.forEach(path -> requests.requestMatchers(path).permitAll());
                        securedPaths.forEach(path -> requests.requestMatchers(path).authenticated());
                        requests.anyRequest().denyAll();
                    })
				
                .addFilterBefore(new JwtTokenValidatorFilter(publicPaths), BasicAuthenticationFilter.class)
				.formLogin(flc -> flc.disable() )
                .httpBasic(withDefaults())
                .build();
	}
	
	
	 @Bean
	    public CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration config = new CorsConfiguration();
	        config.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
	        config.setAllowedMethods(Collections.singletonList("*"));
	        config.setAllowedHeaders(Collections.singletonList("*"));
	        config.setAllowCredentials(true);
	        config.setMaxAge(3600L);

	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", config);
	        return source;
	    }
	 
	 
	 @Bean
	 public UserDetailsService userDetailsService() {
		 	 
		 var user1 = User.builder().username("mohamed").password("$2a$10$l/pBB8k8a0z3blhoVrtnMuVv7SltluTYF9VVR8xeV26gUa4RC47uK")
				 .roles("USER").build();
		 var user2 = User.builder().username("admin").password("$2a$10$at8qs3uj3NwzH3LsQzV8JuCCZIUzaddehoII4XfgX/q92a7AcZKqy")
				 .roles("ADMIN").build();
		  return new InMemoryUserDetailsManager(user1,user2);

	 }
	 
	    @Bean
	    public AuthenticationManager authenticationManager() {
	        var autheticationProvider = new DaoAuthenticationProvider(userDetailsService());
	        autheticationProvider.setPasswordEncoder(passwordEncoder());
	        return new ProviderManager(autheticationProvider);
	    }

	 
	 @Bean
	 public PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	 }
	 
	 
	 

}
