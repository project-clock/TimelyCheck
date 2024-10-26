package com.americinn.timelycheck.controller;

import com.americinn.timelycheck.entity.PasswordResetToken;
import com.americinn.timelycheck.entity.Staff;
import com.americinn.timelycheck.model.StaffModel;
import com.americinn.timelycheck.service.StaffService;
import com.americinn.timelycheck.utility.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*
  @Author Sandeep
 */
@RestController
public class StaffOnboardController {

  @Autowired
  private StaffService staffService;

  @Autowired
  private TokenService tokenService;

    @PostMapping("/staff/onBoarding")
    public ResponseEntity<Staff>  createStaff(@RequestBody StaffModel staffmodel){
return  ResponseEntity.ok().body(staffService.onboardStaff(staffmodel) );
    }

    @GetMapping("/staff")
    public ResponseEntity<List<Staff>>  createStaff(){
        return  ResponseEntity.ok().body(staffService.getOnboardStaff() );
    }


    @PostMapping("staff/reset-password/submit")
    public String resetPassword(@RequestParam String token, @RequestParam String newPassword) {

        return  staffService.resetPasscode(token,newPassword);


    }
}
