package com.americinn.timelycheck.service;

import com.americinn.timelycheck.entity.Staff;
import com.americinn.timelycheck.model.StaffModel;

public interface StaffService {
    Staff onboardStaff(StaffModel staffModel);
}
