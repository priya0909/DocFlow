package com.example.DocFlow.Service;

import com.example.DocFlow.Entity.User_Org_SR_File;
import com.example.DocFlow.Repository.Org_User_SR_File_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;
import java.util.zip.DataFormatException;

@Service
public class Org_User_SR_File_Service {

    @Autowired
    Org_User_SR_File_Repository org_User_SRFileRepository;

    public ResponseEntity store(MultipartFile file) throws IOException {

        var fileToSave = new User_Org_SR_File(file.getContentType(), StringUtils.cleanPath(file.getOriginalFilename()), false,file.getBytes());

        //  User_Org_SR_File user_Org_SR_File = new User_Org_SR_File(file.getContentType(), file.getOriginalFilename(),false, file.getBytes());
        return new ResponseEntity<>(org_User_SRFileRepository.save(fileToSave), HttpStatus.CREATED);
    }

    public ResponseEntity getFile(String id) throws DataFormatException, IOException {

        User_Org_SR_File userOrgSrFile = org_User_SRFileRepository.findById(id).get();
        //byte[] fileData = ImageUtils.decompressImage(org_User_SRFileRepository.findByName(name).getData());

        //return new ResponseEntity((ImageUtils.decompressImage(fileData)), HttpStatus.ACCEPTED);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + userOrgSrFile.getName() + "\"")
                .body(userOrgSrFile.getData());

//        return ResponseEntity.status(HttpStatus.OK)
//                .contentType(MediaType.valueOf(IMAGE_PNG_VALUE))
//                .body(fileData);
    }

    public Stream<User_Org_SR_File> getAllFiles() {
        return org_User_SRFileRepository.findAll().stream();
    }
}
