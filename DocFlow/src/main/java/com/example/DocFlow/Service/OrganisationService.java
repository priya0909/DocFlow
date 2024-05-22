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

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrganisationService {

    @Autowired
    OrganisationRepository organisationRepository;

    @Autowired
    UserRepository userRepository;
    public ResponseEntity addOrg(Organisation organisation){
        Organisation org = organisationRepository.save(organisation);
        OrganisationDTO organisationDTO = new OrganisationDTO();
        organisationDTO.setName(org.getName());
//        organisationDTO.setDescription(org.getDescription());
        return new ResponseEntity(organisationDTO,HttpStatus.OK);

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
//        organisationDTO.setDescription(getOrgDetails.getDescription());

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
       organisationDTO.setName(name);
//       organisationDTO.setDescription(org.getDescription());

        return new ResponseEntity(organisationDTO,HttpStatus.OK);

   }

    public ResponseEntity addUserInOrg (Long orgId, Long userId) {
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
//        organisationDTO.setDescription(organisation.getDescription());
        organisation.getUsers().add(user);

        organisationRepository.save(organisation);

        return new ResponseEntity("user added to list", HttpStatus.OK);
    }

    public ResponseEntity getOrgName(String name){
        Organisation organisation;

        try{
            organisation = organisationRepository.findByName(name);

        }
        catch(NoSuchElementException e){
            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);

        }

        OrganisationDTO organisationDTO= new OrganisationDTO();
        organisationDTO.setName(organisation.getName());
//        organisationDTO.setDescription(organisation.getDescription());
        organisationRepository.save(organisation);

        return  new ResponseEntity(organisationDTO,HttpStatus.OK);
    }

    public ResponseEntity getAllOrgs(){
        Organisation organisation;
        List<Organisation> organisations;
        List<OrganisationDTO>organisationDTOList = new ArrayList<>();
    try{
        organisations = organisationRepository.findAll();
    }
    catch (NoSuchElementException e) {
        return new ResponseEntity<>("Organisation Not found", HttpStatus.NOT_FOUND);
    }

    for(Organisation organisation1 : organisations){
        OrganisationDTO organisationDTO =new OrganisationDTO();
        organisationDTO.setName(organisation1.getName());
        organisationDTOList.add(organisationDTO);

        organisationRepository.saveAll(organisations);

    }
        return new ResponseEntity<>(organisationDTOList, HttpStatus.OK);
    }
}
