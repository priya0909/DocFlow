package com.example.DocFlow.Controller;

import com.example.DocFlow.Service.Org_User_SR_File_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.zip.DataFormatException;


@RequestMapping("/sr_file")
@RestController

public class Org_User_SR_Files_Controller {

    @Autowired
    private Org_User_SR_File_Service org_User_SR_File_Service;

    @PostMapping("/upload")
    public ResponseEntity upload(@RequestParam("file") MultipartFile file) throws IOException {
        return org_User_SR_File_Service.store(file);
    }

    @GetMapping("/get/{name}")
    public  ResponseEntity getFile(@PathVariable String name) throws DataFormatException, IOException {
        return org_User_SR_File_Service.getFile(name);
    }
//    @GetMapping("/files")
//    public ResponseEntity getListFiles() {
//        List<User_Org_SR_File> files = org_User_SR_File_Service.getAllFiles().map(dbFile -> {
//            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
//                    .path(dbFile.getId()).toUriString();
//
//            return new ResponseFile(dbFile.getName(), fileDownloadUri, dbFile.getType(), dbFile.getData().length);
//        }).collect(Collectors.toList());
//
//        return ResponseEntity.status(HttpStatus.OK).body(files);
//    }

}

