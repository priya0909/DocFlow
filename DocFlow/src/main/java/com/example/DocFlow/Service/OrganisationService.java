package com.example.DocFlow.Service;

import com.example.DocFlow.DTOs.OrganisationDTO;
import com.example.DocFlow.Entity.Organisation;
import com.example.DocFlow.Entity.User;
import com.example.DocFlow.Exceptions.DatabaseConnectionException;
import com.example.DocFlow.Repository.OrganisationRepository;
import com.example.DocFlow.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrganisationService {

    @Autowired
    OrganisationRepository organisationRepository;

    @Autowired
    UserRepository userRepository;
    public ResponseEntity addOrg(Organisation organisation) throws DatabaseConnectionException {
       try{
           Organisation org = organisationRepository.save(organisation);
       }
       catch(CannotCreateTransactionException e) {
           throw new DatabaseConnectionException("Database connection lost!, please contact Tech team!");
       }
//        OrganisationDTO organisationDTO = new OrganisationDTO();
//        organisationDTO.setName(org.getName());
//        organisationDTO.setDescription(org.getDescription());
        return new ResponseEntity("Organisation Added Successfully!",HttpStatus.OK);

    }
    public ResponseEntity getOrg(Long orgId) throws Exception {
        Organisation getOrgDetails;
        try {
            getOrgDetails = organisationRepository.findById(orgId).get();
            organisationRepository.save(getOrgDetails);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
        }
        catch(CannotCreateTransactionException e){
            throw new DatabaseConnectionException ("Database connection lost!, please contact Tech team!");
        }
        OrganisationDTO organisationDTO = new OrganisationDTO();
        organisationDTO.setName(getOrgDetails.getName());
//        organisationDTO.setDescription(getOrgDetails.getDescription());

        return new ResponseEntity(organisationDTO,HttpStatus.OK);

    }

   public ResponseEntity updateOrg(Long orgId,String name) throws DatabaseConnectionException {
       Organisation org;
       try {
           org = organisationRepository.findById(orgId).get();
           org.setName(name);
           organisationRepository.save(org);
       }
       catch(NoSuchElementException e){
           return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
       }
        catch(CannotCreateTransactionException e){
           throw new DatabaseConnectionException("Database connection lost!, please contact Tech team!");
        }
        return new ResponseEntity("Details updated successfully",HttpStatus.OK);

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
        catch(CannotCreateTransactionException e){
            return new ResponseEntity("Database connection lost!, please contact Tech team!",HttpStatus.BAD_REQUEST);
        }
        organisation.getUsers().add(user);

        organisationRepository.save(organisation);

        return new ResponseEntity("User added to list", HttpStatus.OK);
    }

    public ResponseEntity getOrgName(String name){
        Organisation organisation;

        try{
            organisation = organisationRepository.findByName(name);


        }
        catch(NoSuchElementException e){
            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);

        }
        catch(CannotCreateTransactionException e){
            return new ResponseEntity("Database connection lost!, please contact Tech team!",HttpStatus.BAD_REQUEST);
        }
        OrganisationDTO organisationDTO= new OrganisationDTO();
        organisationDTO.setName(organisation.getName());
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
    catch(CannotCreateTransactionException e){
        return new ResponseEntity("Database connection lost!, please contact Tech team!",HttpStatus.BAD_REQUEST);
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
