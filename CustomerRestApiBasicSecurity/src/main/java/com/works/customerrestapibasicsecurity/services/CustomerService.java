package com.works.customerrestapibasicsecurity.services;

import com.works.customerrestapibasicsecurity.entities.Fault;
import com.works.customerrestapibasicsecurity.repositories.FaultRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomerService {

    final FaultRepository fRepo;

    public CustomerService(FaultRepository fRepo) {
        this.fRepo = fRepo;
    }


    public ResponseEntity single( String fid ) {
        Map<String, Object> hm = new HashMap<>();
        try {
            int id = Integer.parseInt(fid);
            Optional<Fault> oFault = fRepo.findById(id);
            if (oFault.isPresent() ) {
                // bu user var
                hm.put("status", true);
                hm.put("result", oFault.get() );
            }else {
                hm.put("status", false);
                hm.put("result", "Empty!" );
            }
        }catch (Exception ex) {
            hm.put("message", "id request : " + fid);
            hm.put("status", false);
            return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(hm, HttpStatus.OK);
    }

}
