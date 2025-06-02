package jp.te4a.spring.boot.myapp13.mybootapp13.config;

import jp.te4a.spring.boot.myapp13.mybootapp13.service.LoginUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private LoginUserDetailsService loginUserDetailsSrevice;

    //サービスクラスにエンコーダを登録し、パスワードを記録する際に暗号化処理をかけるように指定
    public void configureAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginUserDetailsSrevice).passwordEncoder(passwordEncoder());
    }
    //BCryptアルゴリズムによるハッシュ生成(Pbkdf2の方が新しい。本来は複数切り替えられるようにしておくが、今回は１つだけ使用)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception 
    {
        http.formLogin(login -> login
            .loginProcessingUrl("/login") //ログイン処理を行うパス
            .loginPage("/loginForm") //ログインページのパス
            .defaultSuccessUrl("/books", true) //ログイン成功時のパス
            .failureUrl("/loginForm?error") //ログイン失敗時のパス
            .usernameParameter("username").passwordParameter("password") //ログインに使用するパラメータ
            .permitAll() //ログイン関係のパスは認証前でもアクセス可能とする
        ).logout(logout -> logout
            .logoutSuccessUrl("/loginForm")) //ログアウトした時に遷移するパス
            .authorizeHttpRequests(authz -> authz
            .requestMatchers("/webjars/**", "/css/**").permitAll()
            .requestMatchers("/loginForm").permitAll()
            .requestMatchers("/users").permitAll()
            .requestMatchers("/users/create").permitAll()
            .anyRequest().authenticated() //上記以外は認証が必要なパス
        );
        return http.build();
    }

}
