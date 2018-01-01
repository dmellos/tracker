package io.egen.service;

import io.egen.entity.Alert;
import io.egen.entity.Reading;
import io.egen.entity.Vehicle;
import io.egen.exception.MailNotSentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(Vehicle vehicle, Reading reading, Alert alert) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage);

        String mailSubject = "Alert from Car Tracker: " + alert.getPriority();
        String messageBody = "\n" + alert.getAlertType() + ":\t" + alert.getPriority() + "\n\n" + vehicle + "\n\n"
                + reading;

        try {
            mailMsg.setFrom("kmkrishna1madhur@gmail.com");
            mailMsg.setTo("kmkrishna1madhur@gmail.com");
            mailMsg.setSubject(mailSubject);
            mailMsg.setText(messageBody);
            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new MailNotSentException("There is a problem while sending alerts to customer");
        }

    }
    }

