package undp.judgment.list.judgment.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import undp.judgment.list.judgment.services.UserSecurityService;

import java.util.Locale;

@Configuration
public class WebConfiguration extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    @Autowired
    private UserSecurityService userSecurityService;



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/api/counts/**","/pdf/**","/rest/**","/international**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/")
                .failureUrl("/?error=true")
                .successForwardUrl("/upload/home")
                .defaultSuccessUrl("/upload/home")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/?logout=true")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**","/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userSecurityService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }




}
