package com.jwtauthentication.api;

import com.jwtauthentication.dto.AuthRequest;
import com.jwtauthentication.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class Controller {

    private final AuthenticationManager authenticationManager ;
    private final JwtUtil jwtUtil ;

    @GetMapping("/")
    public String welcome()
    {
        return "Welcome to programming world sowji !! ";
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        }catch (Exception e)
        {
            throw new Exception("invalid username / password");
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }
}
