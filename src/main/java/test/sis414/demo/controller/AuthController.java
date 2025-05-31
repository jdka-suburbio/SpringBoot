package test.sis414.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.sis414.demo.model.User;
import test.sis414.demo.services.TokenBlackListService;
import test.sis414.demo.util.JwtUtil;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TokenBlackListService blackListService;

    private static final Logger logger = LoggerFactory.getLogger(TokenBlackListService.class);

    @PostMapping("/login")
    public String login(@RequestBody User userRequest)
    {
        if(userRequest.getUsername().equals("sis414"))
        {
            return jwtUtil.generateToken(userRequest.getUsername());
        }
        else
        {
            return "No exite usuario";
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request)
    {
        String token = jwtUtil.getRequestToken(request);
        if(token != null){
            blackListService.addToken(token);
            logger.info("The token was adding to the blacklist: " + token);
        }
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("Logout Successful");
    }
}
