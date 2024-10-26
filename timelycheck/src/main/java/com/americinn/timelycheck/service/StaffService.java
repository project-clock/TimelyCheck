package com.americinn.timelycheck.service;

import com.americinn.timelycheck.entity.Staff;
import com.americinn.timelycheck.model.StaffModel;

import java.util.List;

public interface StaffService {
    Staff onboardStaff(StaffModel staffModel);

    String resetPasscode(String token, String newPassword);

    List<Staff> getOnboardStaff();
}
