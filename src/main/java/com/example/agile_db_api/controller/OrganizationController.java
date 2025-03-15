package com.example.agile_db_api.controller;

import com.example.agile_db_api.models.Organization;
import com.example.agile_db_api.repository.OrgRepository;
import com.example.agile_db_api.requestDTO.LoginRequestDTO;
import com.example.agile_db_api.requestDTO.OrgRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("api/db/org")
public class OrganizationController {

    @Autowired
    OrgRepository orgRepository;


    @PostMapping("/register")
    public ResponseEntity<String> orgRegister(@RequestBody Organization organization){
        System.out.println(organization.getEmail());
        System.out.println(organization.getId());
        System.out.println(organization.getName());
        System.out.println(organization.getPassword());

        try{
            System.out.println("inside database");
            orgRepository.save(organization);
            System.out.println("org created successful");
            return new ResponseEntity<>("Organization registered successfully", HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>("registration failed", HttpStatus.INTERNAL_SERVER_ERROR)
        }

    }

    @GetMapping("/get")
    public ResponseEntity<Organization> getOrganization(@RequestParam String orgEmail) {
        System.out.println("🔹 Fetching Organization Data for: " + orgEmail);

        Optional<Organization> org = orgRepository.findByEmail(orgEmail);

        if (org.isPresent()) {
            Organization organization = org.get();
            organization.setUsers(new ArrayList<>());
            organization.setProjects(new ArrayList<>());
            return new ResponseEntity<>(organization, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    //  Check if organization exists
    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkOrganizationExists(@RequestParam String email) {
        boolean exists = orgRepository.findByEmail(email).isPresent();
        return ResponseEntity.ok(exists);
    }

    //  Login validation
    @PostMapping("/login")
    public ResponseEntity<Boolean> userLogin(@RequestBody LoginRequestDTO loginRequestDTO) {
        Organization org = orgRepository.findByEmail(loginRequestDTO.getUsername()).orElse(null);

        System.out.println("Checking login for: " + loginRequestDTO.getUsername());

        if (org == null) {
            System.out.println("Organization not found!");
            return ResponseEntity.ok(false);
        }

        boolean isValid = org.getPassword().equals(loginRequestDTO.getPassword());
        System.out.println("Password Match: " + isValid);

        return ResponseEntity.ok(isValid);
    }



}
