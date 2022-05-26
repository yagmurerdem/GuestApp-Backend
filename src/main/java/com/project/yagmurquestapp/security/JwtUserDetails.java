package com.project.yagmurquestapp.security;

import com.project.yagmurquestapp.entities.Kisi;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class JwtUserDetails implements UserDetails {

    public Long id;
    private String userName;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUserDetails(Long id, String userName, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.authorities = authorities;
    }

    public static  JwtUserDetails create (Kisi kisi){ //JwtUserDetails authenticate için kullanacağımız user objemiz //bize gelen Kisi classını UserDetails classına çevirir
        List<GrantedAuthority> authoritiesList =new ArrayList<>();
        authoritiesList.add(new SimpleGrantedAuthority("user"));
        return new JwtUserDetails(kisi.getId(),kisi.getUserName(),kisi.getPassword(),authoritiesList);
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
