package com.americinn.timelycheck.implementation;
import com.americinn.timelycheck.entity.Staff;
import com.americinn.timelycheck.mapper.StaffMapper;
import com.americinn.timelycheck.model.EmailDetails;
import com.americinn.timelycheck.model.StaffModel;
import com.americinn.timelycheck.repository.StaffRepository;
import com.americinn.timelycheck.service.EmailService;
import com.americinn.timelycheck.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffMapper staffMapper;
    @Autowired private EmailService emailService;
    private  final StaffRepository staffRepository;
    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }



    @Override
    public Staff onboardStaff(StaffModel staffModel) {
         Staff staff=  staffMapper.toEntity(staffModel);
         staff.setDepartmentId(1l);
         staff.setCreatedBy("Admin");
         staff.setModifiedBy("Admin");
         staff.setEmail(staffModel.getEmail());
         staff.setDigitalPin(1432);

//   public EmailDetails(String recipient, String msgBody, String subject, String attachment) {
        String status
                = emailService.sendSimpleMail(new EmailDetails("sandeep.rayala14@gmail.com","new employee registered",
                "Employee OnBoarded",null));
         return  staffRepository.save(staff);
    }




}
