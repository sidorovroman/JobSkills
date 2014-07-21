package com.onedeveloperstudio.core.common.util;

import java.io.Serializable;

/**
 * @author Yuri Zakharov, "Integrated Information Solutions Ltd"
 */
public class UploadFile implements Serializable {

  private static final long serialVersionUID = 1L;

  private String fileName;

  private String tmpFile;

  private byte[] fileData;

  /**
   * Наименование файла
   *
   * @return
   */
  public String getFileName() {
    return fileName;
  }

  /**
   *
   * @param fileName
   */
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  /**
   * Сам файл
   *
   * @return
   */
  public byte[] getFileData() {
    return fileData;
  }

  /**
   *
   * @param fileData
   */
  public void setFileData(byte[] fileData) {
    this.fileData = fileData;
  }

  public String getTmpFile() {
    return tmpFile;
  }

  public void setTmpFile(String tmpFile) {
    this.tmpFile = tmpFile;
  }
}
