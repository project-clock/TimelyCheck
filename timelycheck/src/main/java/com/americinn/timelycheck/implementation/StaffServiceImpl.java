package com.americinn.timelycheck.implementation;
import com.americinn.timelycheck.entity.PasswordResetToken;
import com.americinn.timelycheck.entity.Staff;
import com.americinn.timelycheck.mapper.StaffMapper;
import com.americinn.timelycheck.model.EmailDetails;
import com.americinn.timelycheck.model.StaffModel;
import com.americinn.timelycheck.repository.PasswordResetTokenRepository;
import com.americinn.timelycheck.repository.StaffRepository;
import com.americinn.timelycheck.service.EmailService;
import com.americinn.timelycheck.service.StaffService;
import com.americinn.timelycheck.utility.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffMapper staffMapper;
    @Autowired private EmailService emailService;
    private  final StaffRepository staffRepository;
    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }
    @Autowired
     private PasswordResetTokenRepository tokenRepository;
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
        staff= staffRepository.save(staff);
       sendResetPasswordEmail(staffModel.getEmail(),tokenService.generateToken(staff).getToken());
         return staff;
    }

    @Override
    public String resetPasscode(String token, String newPassword) {
        if (tokenService.validateToken(token)) {
            PasswordResetToken passwordResetToken = tokenRepository.findByToken(token);
             Staff staff= staffRepository.findByStaffId(passwordResetToken.getStaff().getStaffId());
            staff.setPassword(newPassword);
            staffRepository.save(staff);
            return "Password updated successfully";
        } else {
            return "Invalid or expired token.";
        }

    }

    @Override
    public List<Staff> getOnboardStaff() {
        return staffRepository.findAll();
    }


    // optimize it later
    public void sendResetPasswordEmail(String email, String token) {
        String resetUrl = "http://localhost:8080/reset-password?token=" + token; // Adjust URL as needed
        emailService.sendSimpleMail(new EmailDetails(email,resetUrl,
                "Clockify -- Click the link to reset your password: ",null));
    }





    public void saveUserPassword(Staff user) {
       // user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setPassword(user.getPassword());
        staffRepository.save(user);
    }




}
