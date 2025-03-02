package in.albertnegoro.restapi.controller;

import in.albertnegoro.restapi.dto.ProfileDTO;
import in.albertnegoro.restapi.io.AuthRequest;
import in.albertnegoro.restapi.io.AuthResponse;
import in.albertnegoro.restapi.io.ProfileRequest;
import in.albertnegoro.restapi.io.ProfileResponse;
import in.albertnegoro.restapi.service.CustomUserDetailsService;
import in.albertnegoro.restapi.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import in.albertnegoro.restapi.util.JwtTokenUtil;


@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {
    private final ModelMapper modelMapper;
    private final ProfileService profileService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final CustomUserDetailsService userDetailsService;

    /**
     * API endpoint to register new user
     *
     * @param profileRequest
     * @return ProfileResponse
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public ProfileResponse createProfile(@Valid @RequestBody ProfileRequest profileRequest) {
        log.info("API /register is called {}", profileRequest);
        ProfileDTO profileDTO = mapToProfileDTO(profileRequest);
        profileDTO = profileService.createProfile(profileDTO);
        log.info("Printing the ProfileDTO details {}", profileDTO);

        return mapToProfileResponse(profileDTO);
    }

    @PostMapping("/login")
    public AuthResponse authenticateProfile(@RequestBody AuthRequest authRequest) throws Exception {
        log.info("API /login is called {}", authRequest);
        authenticate(authRequest);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);

        return new AuthResponse(token, authRequest.getEmail());
    }

    private void authenticate(AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        } catch (DisabledException ex) {
            throw new Exception("Profile disabled");
        } catch (BadCredentialsException ex) {
            throw new Exception("Bad Credentials");
        }
    }

    /**
     * Mapper method to map values from ProfileRequest to ProfileDTO
     *
     * @param profileRequest
     * @return ProfileDTO
     */
    private ProfileDTO mapToProfileDTO(ProfileRequest profileRequest) {
        return modelMapper.map(profileRequest, ProfileDTO.class);
    }

    /**
     * Mapper method to map values from ProfileDTO to ProfileResponse
     *
     * @param profileDTO
     * @return ProfileResponse
     */
    private ProfileResponse mapToProfileResponse(ProfileDTO profileDTO) {
        return modelMapper.map(profileDTO, ProfileResponse.class);
    }

}
