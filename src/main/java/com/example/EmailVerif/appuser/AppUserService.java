package com.example.EmailVerif.appuser;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException(String.format("User with email %s not found",email)));
    }

    public String signUpUser(AppUser appUser){
        boolean userExists=appUserRepository.findByEmail(appUser.getEmail()).isPresent();

        if (userExists){
            throw new IllegalStateException("email already registered");
        }
        String encodedPassword=bCryptPasswordEncoder.encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);
//        TODO:Send confirmation token
        appUserRepository.save(appUser);

        return "it works";
    }
}
