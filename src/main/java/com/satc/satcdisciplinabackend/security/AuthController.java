package com.satc.satcdisciplinabackend.security;

import com.satc.satcdisciplinabackend.enterprise.TokenRefreshException;
import com.satc.satcdisciplinabackend.model.Usuario;
import com.satc.satcdisciplinabackend.repository.UsuarioRepository;
import com.satc.satcdisciplinabackend.security.jwt.JwtUtils;
import com.satc.satcdisciplinabackend.security.payload.request.LoginRequest;
import com.satc.satcdisciplinabackend.security.payload.request.SignupRequest;
import com.satc.satcdisciplinabackend.security.payload.request.TokenRefreshRequest;
import com.satc.satcdisciplinabackend.security.payload.response.JwtResponse;
import com.satc.satcdisciplinabackend.security.payload.response.MessageResponse;
import com.satc.satcdisciplinabackend.security.payload.response.TokenRefreshResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;


    @Autowired
    UsuarioRepository userRepository;


    @Autowired
    RoleRepository roleRepository;


    @Autowired
    PasswordEncoder encoder;


    @Autowired
    RefreshTokenService refreshTokenService;


    @Autowired
    JwtUtils jwtUtils;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);


        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        return ResponseEntity.ok(new JwtResponse(jwt,
                refreshToken.getToken(),
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }


        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }


        // Create new user's account
        Usuario user = new Usuario(
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword())
        );

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        List<Role> all = roleRepository.findAll();
        if (all.isEmpty()) {
            Role role = new Role();
            role.setName(ERole.ADMIN);
            roleRepository.save(role);
            Role role2 = new Role();
            role2.setName(ERole.FUNCIONARIO);
            roleRepository.save(role2);
            Role role3 = new Role();
            role3.setName(ERole.CLIENTE);
            roleRepository.save(role3);
        }


        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.CLIENTE)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "ADMIN":
                        Role adminRole = roleRepository.findByName(ERole.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);


                        break;
                    case "FUNCIONARIO":
                        Role modRole = roleRepository.findByName(ERole.FUNCIONARIO)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);


                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.CLIENTE)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }


        user.setRoles(roles);
        userRepository.save(user);


        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();


        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getUsername());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }
}
