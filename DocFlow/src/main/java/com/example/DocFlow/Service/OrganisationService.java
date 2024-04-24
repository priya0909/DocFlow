package com.example.DocFlow.Service;

import com.example.DocFlow.DTOs.OrganisationDTO;
import com.example.DocFlow.Entity.Organisation;
import com.example.DocFlow.Entity.User;
import com.example.DocFlow.Repository.OrganisationRepository;
import com.example.DocFlow.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class OrganisationService {

    @Autowired
    OrganisationRepository organisationRepository;

    @Autowired
    UserRepository userRepository;
    public ResponseEntity addOrg(Organisation organisation){
        Organisation org = organisationRepository.save(organisation);
        return new ResponseEntity(org,HttpStatus.OK);

    }
    public ResponseEntity getOrg(Long orgId) {
        Organisation getOrgDetails;
        try {
            getOrgDetails = organisationRepository.findById(orgId).get();
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
        }
        OrganisationDTO organisationDTO = new OrganisationDTO();
        organisationDTO.setName(getOrgDetails.getName());
        organisationDTO.setDescription(getOrgDetails.getDescription());

        return new ResponseEntity(organisationDTO,HttpStatus.OK);

    }

   public ResponseEntity updateOrg(Long orgId,String name){
       Organisation org;
       try {
           org = organisationRepository.findById(orgId).get();
       }
       catch(NoSuchElementException e){
           return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
       }

       OrganisationDTO organisationDTO = new OrganisationDTO();
       organisationDTO.setName(org.getName());
       organisationDTO.setDescription(org.getDescription());

        return new ResponseEntity("user updated",HttpStatus.OK);

   }

    public ResponseEntity addUser (Long orgId, Long userId) {
        Organisation organisation;
        User user;

        try{
          organisation = organisationRepository.findById(orgId).get();
           user = userRepository.findById(userId).get();
       }
        catch(NoSuchElementException e) {
            return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
        }

        OrganisationDTO organisationDTO = new OrganisationDTO();
        organisationDTO.setName(organisation.getName());
        organisationDTO.setDescription(organisation.getDescription());
        organisation.getUsers().add(user);


        return new ResponseEntity("user added to list", HttpStatus.OK);
    }
}
