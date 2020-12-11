package org.example.api;

import io.jsonwebtoken.JwtException;
import org.example.mapper.UserMapper;
import org.example.security.JWTFilter;
import org.example.security.JWTGenerator;
import org.example.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

@Transactional
@RestController
@RequestMapping("api/security")
public class SecurityResource {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JWTGenerator jwtGenerator;

    @PostMapping("/auth")
    public ResponseEntity auth(@RequestBody @Valid UserMapper.UserToAuth userToAuth) {
        return userService.findUserByUsername(userToAuth.getUsername())
                .map(user -> BCrypt.checkpw(userToAuth.getPassword(), user.getPassword())
                        ? ResponseEntity.ok().header(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwtGenerator.generate(user.getId())).build()
                        : ResponseEntity.status(HttpStatus.FORBIDDEN).build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/refresh")
    public ResponseEntity refresh(HttpServletRequest request) {
        try {
            return ResponseEntity.ok().header(JWTFilter.AUTHORIZATION_HEADER,
                    "Bearer " + jwtGenerator.generate(jwtGenerator.parse(
                            request.getHeader(JWTFilter.AUTHORIZATION_HEADER).split(" ")[1])))
                    .build();
        } catch (JwtException jwtException) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
