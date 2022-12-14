package com.works.customerrestapibasicsecurity.services;

import com.works.customerrestapibasicsecurity.entities.Fault;
import com.works.customerrestapibasicsecurity.entities.Role;
import com.works.customerrestapibasicsecurity.entities.User;
import com.works.customerrestapibasicsecurity.repositories.FaultRepository;
import com.works.customerrestapibasicsecurity.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class RepairService implements UserDetailsService {

    final UserRepository uRepo;
    final FaultRepository fRepo;

    public RepairService(UserRepository uRepo, FaultRepository fRepo) {
        this.uRepo = uRepo;
        this.fRepo = fRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        System.out.println( username );
        Optional<User> optionalUser = uRepo.findByEmailEqualsIgnoreCase( username );
        if ( optionalUser.isPresent() ) {
            User vtUser = optionalUser.get();
            System.out.println( vtUser );
            userDetails = new org.springframework.security.core.userdetails.User(
                    username,
                    vtUser.getPassword(),
                    vtUser.isEnabled(),
                    vtUser.isTokenExpired(),
                    true,
                    true,
                    getAuthorities(vtUser.getRoles())
            );
        }else {
            throw new UsernameNotFoundException("B??yle bir kullan??c?? yok");
        }
        return userDetails;
    }

    private List<GrantedAuthority> getAuthorities (List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority( role.getName() ));
        }
        return authorities;
    }

    public ResponseEntity register(User user ) {
        Optional<User> oUser = uRepo.findByEmailEqualsIgnoreCase(user.getEmail());
        Map<String,Object> hm =new LinkedHashMap<>();

        if (oUser.isPresent() ) {
            // bu  kullan??c?? daha ??nce kay??tl??
            //throw new UsernameNotFoundException("User Register Fail");
            hm.put("status", false);
            hm.put("message", "Bu kullan??c?? daha ??nce kay??tl??!");
            hm.put("result", user  );
            return new ResponseEntity( hm, HttpStatus.BAD_REQUEST );
        }else {
            user.setPassword (encoder().encode(user.getPassword()) );
            hm.put("status", true);
            hm.put("result",  uRepo.save( user ) );
            return new ResponseEntity( hm, HttpStatus.OK );
        }
    }

    public ResponseEntity save(Fault fault) {
        Map<String, Object> hm = new LinkedHashMap<>();
        fRepo.save(fault);
        hm.put("status", true);
        hm.put("result", fault);
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity list() {
        Map<String, Object> hm = new HashMap<>();
        hm.put("users", fRepo.findAll());
        return new ResponseEntity(hm, HttpStatus.OK);
    }


@Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
