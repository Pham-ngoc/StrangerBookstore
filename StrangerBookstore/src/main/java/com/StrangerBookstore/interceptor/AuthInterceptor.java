package com.StrangerBookstore.interceptor;

import com.StrangerBookstore.model.Customer;
import com.StrangerBookstore.model.Roles;
import com.StrangerBookstore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthInterceptor implements AuthenticationProvider {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email =  authentication.getName();
        System.out.println(email);
        String pwd = authentication.getCredentials().toString();
        System.out.println(pwd);
        Customer customer = customerRepository.readByEmail(email);
        if(customer != null && customer.getCustomerId() > 0 && passwordEncoder.matches(pwd, customer.getPassword()) && customer.getStatus().equals("Open")){
            return new UsernamePasswordAuthenticationToken(
                    email, null, getGrantedAuthority(customer.getRoles()));
//            if this one is successful, Spring will erase the pwd credentials ---- Security
        }else {
            throw new BadCredentialsException("Invalid credentials");
        }
    }

    private List<GrantedAuthority> getGrantedAuthority(Roles roles) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + roles.getRoleName()));
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
        // Unless this one returns true ---> the method in this public Authentication authenticate will be executed
    }
}