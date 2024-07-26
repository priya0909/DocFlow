package com.example.DocFlow.Repository;

import com.example.DocFlow.Entity.OrgUserSR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgUserSRRepository extends JpaRepository<OrgUserSR, Long> {

    OrgUserSR findBySerialNo (Long serialNo);
}
