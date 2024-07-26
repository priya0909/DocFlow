package com.example.DocFlow.Service;

import com.example.DocFlow.Entity.OrgUserSR;
import com.example.DocFlow.Repository.OrgUserSRRepository;
import com.example.DocFlow.Repository.Org_User_SR_File_Repository;
import com.example.DocFlow.Repository.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrgUserSRService {

    @Autowired
    OrgUserSRRepository orgUserSRRepository;

    @Autowired
    Org_User_SR_File_Repository org_user_srFileRepository;

    @Autowired
    OrganisationRepository organisationRepository;

    public ResponseEntity CreateSR (OrgUserSR orgUserSR) throws Exception {
        OrgUserSR SavedOrgUserSR;

        try {
            SavedOrgUserSR = orgUserSRRepository.save(orgUserSR);
        } catch (Exception e) {
            throw new Exception(e + " ");
        }
        return new ResponseEntity(SavedOrgUserSR, HttpStatus.CREATED);
    }

    public ResponseEntity getSRDetails(Long orgUserId) throws Exception {
        OrgUserSR getDetails;
        try{
            getDetails = orgUserSRRepository.findById(orgUserId).get();
        }
        catch(Exception e){
            throw new Exception(e + " ");
        }
        return new ResponseEntity(getDetails,HttpStatus.OK);
    }

    public ResponseEntity getDetailsBySR(Long serialNo) throws Exception {
        OrgUserSR getDetailsBySR;
        try{
            getDetailsBySR = orgUserSRRepository.findBySerialNo(serialNo);
        }
        catch(Exception e){
            throw new Exception(e+"");
        }
        return new ResponseEntity(getDetailsBySR,HttpStatus.OK);    }
}







