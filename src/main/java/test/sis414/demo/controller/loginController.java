package test.sis414.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.sis414.demo.util.JwtUtil;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class loginController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login()
    {
        return jwtUtil.generateToken("usuario");
    }
}
