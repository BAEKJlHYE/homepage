package anonymous.homepage.login.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@Slf4j
//@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Bean
    // BCryptPasswordEncoder는 Spring Security에서 제공하는 비밀번호 암호화 객체입니다.
    // Service에서 비밀번호를 암호화할 수 있도록 Bean으로 등록합니다.
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .mvcMatchers("/", "/main/main.do", "/companyInfo/**", "/board/select**","/inquiry/**", "/error/**", "/js/**", "/css/**", "/image/**", "/font/**").permitAll() // 해당 경로들은 접근을 허용
                .antMatchers("/register**","/insert**","/modify**","/update**","/delete**").hasRole("ADMIN")
//                .antMatchers("/manager").hasRole("MANAGER")
//                .antMatchers("/admin").hasRole("ADMIN")
            .anyRequest().authenticated()
        .and()
            .formLogin()
            .usernameParameter("userId")
            .passwordParameter("userPw")
            .loginPage("/login/getLoginPage.do")             // 로그인 페이지
            .loginProcessingUrl("/login/loginCheck.do")      // th:action="@{/login_proc}"
            .defaultSuccessUrl("/main/main.do")              // 로그인 성공시 이동
            .successHandler(authenticationSuccessHandler) // 성공 handler
            .failureHandler(authenticationFailureHandler) // 실패 handler
            .permitAll();
    }
}
