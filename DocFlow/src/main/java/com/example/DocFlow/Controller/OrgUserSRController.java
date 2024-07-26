package com.example.DocFlow.Controller;


import com.example.DocFlow.Entity.OrgUserSR;
import com.example.DocFlow.Repository.OrgUserSRRepository;
import com.example.DocFlow.Service.OrgUserSRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/SR")
@RestController
class OrgUserSRController {

    @Autowired
    OrgUserSRService orgusersrService;

    @Autowired
    OrgUserSRRepository org_user_srRepository;

    @PostMapping("/CreateSR")
    public ResponseEntity addOrgUserSR (@RequestBody OrgUserSR org_user_sr) {

        try {
            return orgusersrService.CreateSR(org_user_sr);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getDetails")
    public ResponseEntity getDetails(@RequestParam("orgUserId") Long orgUserId) {
        try {
            return orgusersrService.getSRDetails(orgUserId);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getBySerialNo")

    public ResponseEntity getDetailsBySR (@RequestParam("serialNo") Long serialNo) throws Exception {
        OrgUserSR getDet;
        try {
          return orgusersrService.getDetailsBySR(serialNo);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

}