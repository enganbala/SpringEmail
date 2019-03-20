package com.bala.java.spring.model;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;

public class Attachment implements Serializable {


    private static final long serialVersionUID = 21323232l;

    private String fileName;
    private byte[] bytes;

    private String contentType;


    public Attachment(String name, byte[] fileContent, String contentType) {
        this.fileName = name;
        this.bytes = fileContent;
        this.contentType = contentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public byte[] getAttachment() {
        return bytes;
    }

    public void setAttachment(byte[] attachement) {
        this.bytes = bytes;
    }


    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }


    public InputStream getInputStream() {
        return new ByteArrayInputStream(bytes);
    }
}
