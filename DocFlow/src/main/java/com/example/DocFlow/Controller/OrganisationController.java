package com.example.DocFlow.Controller;

import com.example.DocFlow.Entity.Organisation;
import com.example.DocFlow.Exceptions.DatabaseConnectionException;
import com.example.DocFlow.Repository.OrganisationRepository;
import com.example.DocFlow.Service.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
     public ResponseEntity addOrg(@RequestBody Organisation organisation) throws DatabaseConnectionException {
        try {
            return organisationService.addOrg(organisation);
        } catch (DatabaseConnectionException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getOrgInfo")
    public ResponseEntity getOrg(@RequestParam("orgId")Long orgId) throws Exception {

        try {
            return  organisationService.getOrg(orgId);
        } catch (Exception e) {
           return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateNameByOrgId")
    public ResponseEntity updateOrg(@RequestParam("orgId")Long orgId, @RequestParam("name")String name)
    {
        try {
            return organisationService.updateOrg(orgId,name);
        } catch (DatabaseConnectionException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/addUserToOrg")
    public ResponseEntity addUser(@RequestParam("orgId")Long orgId, @RequestParam("userId")Long userId)
    {
        return organisationService.addUserInOrg(orgId,userId);
    }

    @GetMapping("/getOrgName")
    public ResponseEntity getOrgName(@RequestParam("name")String name){
        return organisationService.getOrgName(name);
    }

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        return organisationService.getAllOrgs();
    }


}

