package com.project.yagmurquestapp.services;

import com.project.yagmurquestapp.entities.Kisi;
import com.project.yagmurquestapp.repos.KisiRepository;
import com.project.yagmurquestapp.security.JwtUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private KisiRepository kisiRepository;

    public UserDetailsServiceImpl(KisiRepository kisiRepository) {
        this.kisiRepository = kisiRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Kisi kisi =kisiRepository.findByUserName(userName);
        return JwtUserDetails.create(kisi);
    }
    public UserDetails loadUserById(Long id) //repodan alıp userdetails objesine çeviricez
    {
        Kisi kisi=kisiRepository.findById(id).get();
        return JwtUserDetails.create(kisi);

    }
}
