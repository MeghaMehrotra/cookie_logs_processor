package de.quantcast.tech.cookie_logs_processor.model;

import java.time.LocalDate;

/**
 * @author mmehrotra
 */
public class Cookie {

    String cookieName;
    LocalDate date;

    public Cookie(String cookieName,LocalDate date){
        this.cookieName = cookieName;
        this.date = date;
    }

    public String getCookieName() {
        return cookieName;
    }

    public LocalDate getDate() {
        return date;
    }
}
