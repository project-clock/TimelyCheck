package com.americinn.timelycheck.service;

import com.americinn.timelycheck.model.EmailDetails;

public interface EmailService {

    String sendSimpleMail(EmailDetails details);


}
