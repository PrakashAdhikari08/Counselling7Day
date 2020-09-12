package com.sevendays.counselling.services.mail;

import com.sevendays.counselling.model.booking.Booking;
import com.sevendays.counselling.model.user.Counselor;
import com.sevendays.counselling.model.user.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Properties;

/**
 * Created by useless on 7/05/20.
 */
@Service
@EnableAsync
@Configuration
public class SendEmail{

    @Autowired
    public JavaMailSender javaMailSender;

    @Async
    public void sendBookingEmail(Booking booking) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(booking.getPatient().getUsername(),booking.getCounselor().getUsername(),"saaannneeel@gmail.com");
        mailMessage.setSubject("Details of Appointment in 7 days Counselling ");
        mailMessage.setText("Your Appointment has been booked with following details\n \n" +
                "Counsellor: "+booking.getCounselor().getFirstName()+" "+booking.getCounselor().getLastName()+"\n\n"
                +"Service Type: "+booking.getServiceType()+"\n\n"
                +"Date: "+booking.getDate()+"\n\n"
                +"Shift: "+booking.getShift()+"\n\n"
                +"Be there in time \n\n"
                +"Thank you\n"
                +"7 Days Counselling"
         );

        javaMailSender.send(mailMessage);
    }

    @Async
    public void sendDeleteOfBookingEmail(Booking booking) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(booking.getPatient().getUsername(),booking.getCounselor().getUsername(),"saaannneeel@gmail.com");
        mailMessage.setSubject("Booking 7 days Counselling for is deleted ");
        mailMessage.setText("Your Appointment has been deleted with following details\n \n" +
                "Counsellor: "+booking.getCounselor().getFirstName()+" "+booking.getCounselor().getLastName()+"\n\n"
                +"Service Type: "+booking.getServiceType()+"\n\n"
                +"Date: "+booking.getDate()+"\n\n"
                +"Shift: "+booking.getShift()+"\n\n"
                +"Sorry for the inconvenience caused \n\n"
                +"Thank you\n"
                +"7 Days Counselling"
        );

        javaMailSender.send(mailMessage);
    }
    @Async
    public boolean sendContactMail( Map<String, String> details){
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo("prakashadhikarijhapali@gmail.com");
        mailMessage.setSubject("Contact mail from "+details.get("name")+ "with email " +details.get("email"));
        mailMessage.setText("Subject from user \n" + details.get("subject")+"\n\n"+
                "Message from user \n " + details.get("message")
        );

        javaMailSender.send(mailMessage);
        return true;

    }

    @Async
    public void sendPasswordResendCode(String username,String code){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(username);
        mailMessage.setSubject("Request To Resend Password");
        mailMessage.setText("Please use this code to reset the password  "+code);
        javaMailSender.send(mailMessage);
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("aappassignment2@gmail.com");
        mailSender.setPassword("Assignment2");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "false");

        return mailSender;
    }

}
