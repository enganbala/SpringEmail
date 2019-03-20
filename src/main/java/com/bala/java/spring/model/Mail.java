package com.bala.java.spring.model;

import java.io.Serializable;
import java.util.List;

public class Mail implements Serializable {

    private static final long serialVersionUID = 21323232l;

    private String to;
    private String subject;
    private String content;
    private List<Attachment> attachments;


    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }


}
