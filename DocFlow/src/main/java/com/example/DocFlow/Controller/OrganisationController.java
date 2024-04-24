package com.example.DocFlow.Controller;

import com.example.DocFlow.Entity.Organisation;
import com.example.DocFlow.Repository.OrganisationRepository;
import com.example.DocFlow.Service.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Organisation")

public class OrganisationController {
    @Autowired
    OrganisationService organisationService;
    @Autowired
    OrganisationRepository organisationRepository;



    @PostMapping("/addOrg")
     public ResponseEntity addOrg(@RequestBody Organisation organisation){
        return organisationService.addOrg(organisation);
    }

    @GetMapping("/getOrgInfo")
    public ResponseEntity getOrg(@RequestParam("orgId")Long orgId){
       return  organisationService.getOrg(orgId);
    }

    @PutMapping("/updateOrg")
    public ResponseEntity updateOrg(@RequestParam("orgId")Long orgId, @RequestParam("name")String name){
       return organisationService.updateOrg(orgId,name);

    }

    @PostMapping("/addUser")
    public ResponseEntity addUser(@RequestParam("orgId")Long orgId, @RequestParam("userId")Long userId){
        return organisationService.addUser(orgId,userId);
    }
}

