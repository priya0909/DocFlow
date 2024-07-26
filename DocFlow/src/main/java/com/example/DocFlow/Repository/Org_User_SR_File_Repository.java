package com.example.DocFlow.Repository;

import com.example.DocFlow.Entity.User_Org_SR_File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Org_User_SR_File_Repository extends JpaRepository<User_Org_SR_File, String> {
    User_Org_SR_File findByName(String name);
}
