package com.example.DocFlow.Controller;

import com.example.DocFlow.Entity.OrgClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class OrgClientController {

    HashMap<Long, OrgClient> orgClientMap = new HashMap<>();
    @PostMapping("/addOrgClient")
    public OrgClient addOrgClient(@RequestBody OrgClient orgClient){

        long key = orgClient.getOrgClientId();

        orgClientMap.put(key,orgClient);

        return orgClientMap.get(key);

    }

    @GetMapping("/getOrgClientInfo")
    public String getOrgClient(@RequestParam("orgClientId") Integer orgClientId){

        OrgClient orgClient = orgClientMap.get(orgClientId);

        return "Successful";
    }


}
