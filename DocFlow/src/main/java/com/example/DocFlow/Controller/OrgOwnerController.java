package com.example.DocFlow.Controller;

import com.example.DocFlow.Entity.OrgOwner;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class OrgOwnerController {

    HashMap<Integer, OrgOwner> orgOwnerMap = new HashMap<>();

    @PostMapping("/addOrgOwner")
    public  OrgOwner addOrgOwner(@RequestBody OrgOwner orgOwner){
//        int key = orgOwner.getOrgOwnerId ();
//        orgOwnerMap.put(key,orgOwner);
//        return orgOwnerMap.get(key);
        return null;
    }

    @GetMapping("/getOrgOwnerInfo")
    public OrgOwner getOrgOwner(@RequestParam("orgOwnerId") Integer orgOwnerId){
        OrgOwner orgOwner = orgOwnerMap.get(orgOwnerId);
        return orgOwner;
    }


}
