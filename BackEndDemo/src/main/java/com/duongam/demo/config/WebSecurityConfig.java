package com.duongam.demo.config;

import com.duongam.demo.config.filters.JWTAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.Cookie;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private String[] swaggerAntPatterns = { "/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
			"/configuration/security", "/swagger-ui.html", "/webjars/**" };

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(swaggerAntPatterns);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				// No need authentication.
				.antMatchers("/user/login").permitAll()
//				.antMatchers( "/user/**","/syllabus/**","/trainingProgram/**","/class/**","/user-permission/**").hasAnyAuthority("ADMIN","TRAINER")
				.antMatchers( "/user/**","/syllabus/**","/trainingProgram/**","/class/**","/user-permission/**").permitAll()
				// Need authentication.
				.anyRequest().authenticated().and()

				// Add Filter - JWTAuthenticationFilter
				//
				.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class).
				logout().logoutUrl("/user/logout")
				.logoutSuccessHandler((request, response, authentication) -> {
					response.setHeader("Authorization","");
					// Thêm Cache-Control header để ngăn chặn trình duyệt lưu trữ cache
					response.setHeader("Cache-Control", "no-store");

					// Xóa cookie (đổi tên và path tùy thuộc vào cách bạn đặt cookie)
					Cookie cookie = new Cookie("token", null);
					cookie.setPath("/");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				});
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
	}

}
