package com.example.EmailVerif.registration;

import com.example.EmailVerif.appuser.AppUser;
import com.example.EmailVerif.appuser.AppUserRole;
import com.example.EmailVerif.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private AppUserService appUserService;
    private EmailValidator emailValidator;
    public String register(RegistrationRequest request){
        boolean isValidEmail=emailValidator.test(request.getEmail());
        if(!isValidEmail){
            throw new IllegalStateException("email not valid");
        }
        return appUserService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                )
        );
    }
}
