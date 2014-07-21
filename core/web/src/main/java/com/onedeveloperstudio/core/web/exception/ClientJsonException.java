package com.onedeveloperstudio.core.web.exception;

/**
 * @author: Yuri Zakharov
 */
public class ClientJsonException extends Exception {
  public ClientJsonException(String msg) {
    super(msg);
  }

  public ClientJsonException(Throwable cause) {
    super(cause);
  }
}
