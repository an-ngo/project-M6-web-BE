package com.example.loverbe.service;

import com.example.loverbe.model.email.MailObject;

public interface IEmailService {

        void sendSimpleMessage(MailObject mail);
}
