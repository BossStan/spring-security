package com.dada.kenacPortal.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "kenacPortal/employees/registration")
@AllArgsConstructor
public class RegistrationController {

private RegistrationService registrationService;
    @PostMapping
    public String register(@RequestBody RegistrationRequest  request){

        return registrationService.register(request);
    }
}
