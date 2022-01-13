package com.example.loverbe.service.Implement;

import com.example.loverbe.model.email.MailObject;
import com.example.loverbe.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service("mailService")
public class EmailServiceImpl implements IEmailService {
    @Autowired
    private JavaMailSender emailSender;


    @Override
    public void sendSimpleMessage(MailObject mail) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

//            mimeMessageHelper.setSubject(mail.getMailSubject());
//            mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom(), "TinderWindy"));
//            mimeMessageHelper.setTo(mail.getMailTo());
//            mimeMessageHelper.setText(mail.getMailContent());

            mimeMessageHelper.setSubject("Xac nhan");
            mimeMessageHelper.setFrom(new InternetAddress("thainho18011998@gmail.com", "App Hen Ho Team"));
            mimeMessageHelper.setTo("phowflowf135@gmail.com");
            mimeMessageHelper.setText("thai dui");

            emailSender.send(mimeMessageHelper.getMimeMessage());

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
