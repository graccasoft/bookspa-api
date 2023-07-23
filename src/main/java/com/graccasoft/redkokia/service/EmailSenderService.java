package com.graccasoft.redkokia.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailSenderService {
        private final JavaMailSender emailSender;

        @Value("${redkokia.bccEmail}")
        private String bccEmailAddress;

        @Value("${redkokia.senderEmail}")
        private String senderEmailAddress;


        @Async
        public void sendEmail(String emailDest,
                              String subject,
                              String body) {
                sendEmailViaGmail(emailDest,subject,body, null);

        }
        public void sendEmail(String emailDest,
                              String subject,
                              String body,
                              ArrayList<String> attachments) {
                sendEmailViaGmail(emailDest,subject,body, attachments);

        }

        @SneakyThrows
        private void sendEmailViaGmail(String emailDest,
                                 String subject,
                                 String body,
                                 ArrayList<String> attachments){

                MimeMessage mimeMessage = emailSender.createMimeMessage();
                var simpleMailMessage = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                simpleMailMessage.setBcc(bccEmailAddress);
                simpleMailMessage.setFrom(senderEmailAddress);
                simpleMailMessage.setTo(emailDest);
                simpleMailMessage.setSubject(subject);
                simpleMailMessage.setText(body,true);

                if( attachments != null ){
                        attachments.forEach(attachment -> {
                                try {
                                        File attachmentFile = new File(attachment);
                                        simpleMailMessage.addAttachment(attachmentFile.getName(), attachmentFile);

                                }catch(  MessagingException ex ){
                                        log.info("Failed to add attachment", ex);
                                }
                        });

                }
                emailSender.send(mimeMessage);
        }
}
