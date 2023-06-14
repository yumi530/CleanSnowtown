package com.project.smartclean.security;

import com.project.smartclean.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final MemberService memberService;
//    private HttpSecurity http;
//    private BCryptPasswordEncoder passwordEncoder;

    @Bean
    UserAuthenticationFailureHandler getFailureHandler() {
        return new UserAuthenticationFailureHandler();
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/test/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/member/login")
                .failureHandler(getFailureHandler())
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .and()
                .exceptionHandling()
                .accessDeniedPage("/error/denied")
                .and()
                .httpBasic().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/member/register","/member/verify","/notice/**","/faq/**")
                .permitAll()
                .antMatchers("/admin/**")
                .hasRole("ADMIN")
                .antMatchers("/center/**")
                .hasRole("CENTER")
                .antMatchers("/order/**","/board/**")
                .hasRole("USER");
    }

    //    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        http
////                .httpBasic().disable()
////                .csrf().disable()
////                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http
//                .csrf().disable();
//
//
//
//        http
//                .httpBasic()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/", "/member/register","member/verify","board/list","notice/**","faq/**")
//                .permitAll();
//
//        http
//                .authorizeRequests()
//                .antMatchers("/admin/**")
//                .hasAuthority("ROLE_ADMIN");
//
////        http
////                .authorizeRequests()
////                .antMatchers("/center/**")
////                .hasAuthority("ROLE_CENTER");
//
//        http
//                .authorizeRequests()
//                .antMatchers("/order/**","/board/**")
//                .hasAuthority("ROLE_USER");
//
//        http
//                .formLogin()
//                .loginPage("/member/login")
//                .failureHandler(getFailureHandler())
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .permitAll();
//
//        http.logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
//                .logoutSuccessUrl("/")
//                .invalidateHttpSession(true);
//
//        http
//                .exceptionHandling()
//                .accessDeniedPage("/error/denied");
//
//        return http.build();
//
//    }

//        @Bean
//        AuthenticationManager authenticationManager(
//                AuthenticationConfiguration authenticationConfiguration) throws Exception {
//
//            return authenticationConfiguration.getAuthenticationManager();

        public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder)
                throws Exception {
            return http.getSharedObject(AuthenticationManagerBuilder.class)
                    .userDetailsService(memberService)
                    .passwordEncoder(bCryptPasswordEncoder)
                    .and()
                    .build();
    }
}
