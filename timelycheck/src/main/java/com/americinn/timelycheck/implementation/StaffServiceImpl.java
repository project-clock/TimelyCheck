package com.americinn.timelycheck.implementation;
import com.americinn.timelycheck.entity.Staff;
import com.americinn.timelycheck.mapper.StaffMapper;
import com.americinn.timelycheck.model.EmailDetails;
import com.americinn.timelycheck.model.StaffModel;
import com.americinn.timelycheck.repository.StaffRepository;
import com.americinn.timelycheck.service.EmailService;
import com.americinn.timelycheck.service.StaffService;
import com.americinn.timelycheck.utility.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private StaffMapper staffMapper;
    @Autowired private EmailService emailService;
    private  final StaffRepository staffRepository;
    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Autowired
    private TokenService tokenService;



    @Override
    public Staff onboardStaff(StaffModel staffModel) {
         Staff staff=  staffMapper.toEntity(staffModel);
         staff.setDepartmentId(1l);
         staff.setCreatedBy("Admin");
         staff.setModifiedBy("Admin");
         staff.setEmail(staffModel.getEmail());
         staff.setDigitalPin(1432);
       sendResetPasswordEmail(staffModel.getEmail(),tokenService.generateToken());
         return  staffRepository.save(staff);
    }



    // optimize it later
    public void sendResetPasswordEmail(String email, String token) {
        String resetUrl = "http://localhost:8080/reset-password?token=" + token; // Adjust URL as needed
        emailService.sendSimpleMail(new EmailDetails(email,resetUrl,
                "Clockify -- Click the link to reset your password: ",null));
    }





    public void save(Staff user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash the password
        staffRepository.save(user);
    }




}
