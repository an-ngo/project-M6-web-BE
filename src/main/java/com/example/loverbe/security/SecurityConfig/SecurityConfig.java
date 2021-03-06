package com.example.loverbe.security.SecurityConfig;

import com.example.loverbe.enums.EnumRoleName;
import com.example.loverbe.model.entity.user.Role;
import com.example.loverbe.model.entity.user.User;
import com.example.loverbe.security.jwt.JwtEntryPoint;
import com.example.loverbe.security.jwt.JwtTokenFilter;
import com.example.loverbe.service.IRoleService;
import com.example.loverbe.service.IUserService;
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

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    private JwtEntryPoint jwtEntryPoint;

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @PostConstruct
    public void init(){
        List<User> users = (List<User>) userService.findAll();
        List<Role> roles = (List<Role>) roleService.findAll();
        if (roles.isEmpty()){
            Role roleAdmin = new Role();
            roleAdmin.setId(1L);
            roleAdmin.setName(EnumRoleName.ROLE_ADMIN);
            roleService.save(roleAdmin);
            Role roleUser = new Role();
            roleUser.setId(2L);
            roleUser.setName(EnumRoleName.ROLE_USER);
            roleService.save(roleUser);
            Role roleProvider = new Role();
            roleProvider.setId(3L);
            roleProvider.setName(EnumRoleName.ROLE_PROVIDER);
            roleService.save(roleProvider);
        }
        if (users.isEmpty()){
            User userAdmin = new User();
            Set<Role> rolesSet = new HashSet<>();
            rolesSet.add(new Role(1L, EnumRoleName.ROLE_ADMIN));
            userAdmin.setUsername("admin");
            userAdmin.setPassword(passwordEncoder.encode("123456"));
            userAdmin.setRoles(rolesSet);
            userService.save(userAdmin);
        }
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable()
                .authorizeRequests().antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling()
                .authenticationEntryPoint(jwtEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().ignoringAntMatchers("/**");
//        http.httpBasic().authenticationEntryPoint(restServicesEntryPoint());
//        http.authorizeRequests()
//                .antMatchers("/**", "/", "/register", "/login", "/friends/**", "/notifications/**", "/files/**").permitAll()
//                .anyRequest().authenticated()
//                .and().csrf().disable()
//                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
//        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
//                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
//        http.sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.cors();
//    }
}
