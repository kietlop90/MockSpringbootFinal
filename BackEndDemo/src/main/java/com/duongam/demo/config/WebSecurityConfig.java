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

    private String[] swaggerAntPatterns = {"/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
            "/configuration/security", "/swagger-ui.html", "/webjars/**"};

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(swaggerAntPatterns);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                // No need authentication.
                .antMatchers("/user/login").permitAll()
                .antMatchers("/user/logout").hasAnyAuthority("SUPERADMIN", "ADMIN", "TRAINER")


                 .antMatchers("/user/list").hasAnyAuthority("SUPERADMIN", "ADMIN", "TRAINER")
                .antMatchers("/trainingProgram/list").hasAnyAuthority("SUPERADMIN", "ADMIN", "TRAINER")
                .antMatchers("/role/list").hasAnyAuthority("SUPERADMIN", "ADMIN", "TRAINER")
                .antMatchers("/class/list").hasAnyAuthority("SUPERADMIN", "ADMIN", "TRAINER")
                .antMatchers("/syllabus/list").hasAnyAuthority("SUPERADMIN", "ADMIN", "TRAINER")


                .antMatchers( "/user/add").hasAnyAuthority("SUPERADMIN", "ADMIN")
                .antMatchers( "/user/update").hasAnyAuthority("SUPERADMIN", "ADMIN")
                .antMatchers( "/user/delete/{id}").hasAnyAuthority("SUPERADMIN", "ADMIN")
                .antMatchers( "/user/get/id/{id}").hasAnyAuthority("SUPERADMIN", "ADMIN")

                .antMatchers( "/trainingProgram/add").hasAnyAuthority("SUPERADMIN", "ADMIN")

                .antMatchers( "/trainingProgram/getAllTrainingUnit/{code}").hasAnyAuthority("SUPERADMIN", "ADMIN")
                .antMatchers( "/trainingProgram/update").hasAnyAuthority("SUPERADMIN", "ADMIN")
                .antMatchers( "/trainingProgram/delete/{id}").hasAnyAuthority("SUPERADMIN", "ADMIN")
                .antMatchers( "/trainingProgram/get/id/{id}").hasAnyAuthority("SUPERADMIN", "ADMIN")
                .antMatchers( "/trainingProgram/getListTagsSearch").hasAnyAuthority("SUPERADMIN", "ADMIN","TRAINER")
                .antMatchers( "/trainingProgram/search/{name}").hasAnyAuthority("SUPERADMIN", "ADMIN", "TRAINER")
                .antMatchers( "/trainingProgram/getDetail/{code}").hasAnyAuthority("SUPERADMIN", "ADMIN", "TRAINER")
                .antMatchers( "/trainingProgram/remove-tag/{tag}").hasAnyAuthority("SUPERADMIN", "ADMIN", "TRAINER")
                .antMatchers( "/trainingProgram/deleteSearchTag/{name}").hasAnyAuthority("SUPERADMIN", "ADMIN", "TRAINER")
                .antMatchers( "/trainingProgram/duplicate/{id}").hasAnyAuthority("SUPERADMIN", "ADMIN")
                .antMatchers( "/trainingProgram/deActive/{id}").hasAnyAuthority("SUPERADMIN", "ADMIN")
                .antMatchers( "/trainingProgram/getAllSyllabus/{id}").hasAnyAuthority("SUPERADMIN", "ADMIN")
                .antMatchers( "/trainingProgram/getOneSyllabus/{id}").hasAnyAuthority("SUPERADMIN", "ADMIN")
                .antMatchers( "/trainingProgram/list-name/{keywords}").hasAnyAuthority("SUPERADMIN", "ADMIN")
                .antMatchers( "/syllabus/delete/{id}").hasAnyAuthority("SUPERADMIN", "ADMIN")
                .antMatchers( "/syllabus/list-all/{syllabusName}").hasAnyAuthority("SUPERADMIN", "ADMIN", "TRAINER")
                .antMatchers( "/syllabus/list-syllabus-program/{keywords}").hasAnyAuthority("SUPERADMIN", "ADMIN")
                .antMatchers( "/role/update").hasAnyAuthority("SUPERADMIN", "ADMIN")
                .antMatchers( "/class/add").hasAnyAuthority("SUPERADMIN", "ADMIN")
                .antMatchers( "/class/update").hasAnyAuthority("SUPERADMIN", "ADMIN")
                .antMatchers( "/class/get/id/{id}").hasAnyAuthority("SUPERADMIN", "ADMIN")
                .antMatchers( "/class/delete/{id}").hasAnyAuthority("SUPERADMIN", "ADMIN")




                // Need authentication.
                .anyRequest().authenticated()
                .and()

                // Add Filter - JWTAuthenticationFilter.
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
//                .exceptionHandling().accessDeniedHandler((request, response, accessDeniedException) -> {
//                    response.sendRedirect("/403");
//                } )
        ; ;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
