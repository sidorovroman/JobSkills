package com.onedeveloperstudio.jobskills.server.utils;

import com.onedeveloperstudio.core.common.util.UploadFile;
import com.onedeveloperstudio.jobskills.common.util.EmailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Properties;

/**
 * @author Yuri Zakharov, "Integrated Information Solutions Ltd"
 */
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Component(value = "emailSender")
public class EmailSenderImpl implements EmailSender {
  private static final Logger logger = LoggerFactory.getLogger(EmailSenderImpl.class);
  public Properties props = null;

  {
    try {
      props = new Properties();
      Resource resource = new ClassPathResource("/main.properties");
      props = PropertiesLoaderUtils.loadProperties(resource);
    } catch (Exception e) {
      System.out.println("FILE NOT FOUND");
      e.printStackTrace();
    }
  }

  public EmailSenderImpl() {
  }

  public void sendFeedback(String fio, String email, String phone, String message) throws MessagingException {
    String mainEmail = (String) props.get("mail.smtp.info");
    StringBuilder messageBuilder = new StringBuilder();
    messageBuilder.append("ФИО: ").append(fio).append("\n");
    messageBuilder.append("Телефон: ").append(phone).append("\n");
    messageBuilder.append("Сообщение: " + message);
    Multipart mp = new MimeMultipart();
    MimeBodyPart mbp1 = new MimeBodyPart();
    mbp1.setText(messageBuilder.toString());
    mp.addBodyPart(mbp1);
    send(email, mainEmail, "Обратная связь", mp);
  }

  @Override
  public void sendResume(String fio, String position, String email, String phone, String message, UploadFile upfile) throws MessagingException {
    String mainEmail = (String) props.get("mail.smtp.resume");
    StringBuilder messageBuilder = new StringBuilder();
    messageBuilder.append("ФИО: ").append(fio).append("\n");
    messageBuilder.append("Желаемая позиция: ").append(position).append("\n");
    messageBuilder.append("Телефон: ").append(phone).append("\n");
    messageBuilder.append("Сопроводительное письмо \n").append(message);
    File file = null;
    try {
      file = File.createTempFile(upfile.getFileName(), "");
      FileOutputStream out = new FileOutputStream(file);
      out.write(upfile.getFileData());
      out.flush();
    } catch (Exception e) {
      throw new MessagingException("Не удалось обработать вложение");
    }
    Multipart mp = new MimeMultipart();
    MimeBodyPart mbp1 = new MimeBodyPart();
    FileDataSource fds1 = new FileDataSource(file);
    mbp1.setDataHandler(new DataHandler(fds1));
    mbp1.setFileName(upfile.getFileName());
    mp.addBodyPart(mbp1);
    MimeBodyPart mbp2 = new MimeBodyPart();
    mbp2.setText(messageBuilder.toString());
    mp.addBodyPart(mbp2);
    send(email, mainEmail, "Резюме", mp);
  }

  private void send(String sender, String recipient, String subject, Multipart multipart) throws MessagingException {
    Properties props = new Properties(System.getProperties());
    props.putAll(this.props);
    Session session = Session.getInstance(props);
    MimeMessage msg = new MimeMessage(session);
    if (multipart != null) {
      msg.setContent(multipart);
    }
    msg.setFrom(new InternetAddress(sender));
    msg.setRecipients(Message.RecipientType.TO, recipient);
    msg.setSubject(subject);
    msg.setSentDate(new Date());
    logger.info("Отправлено сообщение");
    Transport.send(msg);
  }
}
