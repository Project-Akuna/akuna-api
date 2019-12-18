package ph.com.adcpp.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ph.com.adcpp.commons.request.EmailRequest;

/**
 * @author Choy
 * @date 12/16/2019.
 */

@Slf4j
@Service
public class EmailService {

    private JavaMailSender sender;

    public EmailService(JavaMailSender sender) {
        this.sender = sender;
    }

    public void sendEmail(EmailRequest request) {
        try {
            log.info("Sending email to [{}] ...", request.getEmail());

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(request.getEmail());
            message.setText(request.getBody());
            message.setSubject(request.getSubject());

            sender.send(message);

            log.info("Email successfully sent to [{}]", request.getEmail());
        } catch (Exception ex) {
            log.error("Error sending email to [{}].", request.getEmail());
        }
    }
}
