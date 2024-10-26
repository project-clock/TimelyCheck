package com.americinn.timelycheck.controller;

import com.americinn.timelycheck.entity.Staff;
import com.americinn.timelycheck.model.StaffModel;
import com.americinn.timelycheck.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/*
  @Author Sandeep
 */
@RestController
public class StaffOnboardController {

  @Autowired
  private StaffService staffService;

    @PostMapping("/staff")
    public ResponseEntity<Staff>  createStaff(@RequestBody StaffModel staffmodel){
return  ResponseEntity.ok().body(staffService.onboardStaff(staffmodel) );
    }
}
