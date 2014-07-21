package com.onedeveloperstudio.jobskills.common.util;

import com.onedeveloperstudio.core.common.util.UploadFile;

import javax.mail.MessagingException;

/**
 * @author Yuri Zakharov, "Integrated Information Solutions Ltd"
 */
public interface EmailSender {
  public void sendFeedback(String fio, String email, String phone, String message) throws MessagingException;
  public void sendResume(String fio, String position, String email, String phone, String message, UploadFile file) throws MessagingException;
}
