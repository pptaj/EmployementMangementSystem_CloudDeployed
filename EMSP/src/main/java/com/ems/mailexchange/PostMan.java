package com.ems.mailexchange;

/**
 * Created by Christopher Dsouza on 3/18/2017.
 */
public interface PostMan {
    void setFrom(String from);
    void setTo(String to);
    void setSubject(String subject);
    void setBody(String body);
    void send();
    PostMan withFrom(String from);
    PostMan withTo(String to);
    PostMan withSubject(String subject);
    PostMan withBody(String body);
}
