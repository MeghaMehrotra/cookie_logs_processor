package de.quantcast.tech.cookie_logs_processor.service;

import de.quantcast.tech.cookie_logs_processor.model.Cookie;
import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author mmehrotra
 *
 * This service finds the most active cookie from the cookieList for a specified date
 */

@Service
public class CookieService {

    private static final Logger logger = LogManager.getLogger(CookieService.class);

    Integer maxValue = 0;
    public List<String> findMostActiveCookies(LocalDate date,List<Cookie> cookieList){
        List<String> cookies  = new ArrayList<>();
        Map<String,Integer> activeCookiesMap = new HashMap<>();
        for(Cookie cookie: cookieList){
            if(cookie.getDate().equals(date)){
                activeCookiesMap.put(cookie.getCookieName(), activeCookiesMap.getOrDefault(cookie.getCookieName(),0)+1);
            }
        }
        for(Map.Entry<String,Integer> entry:activeCookiesMap.entrySet()){
            if(entry.getValue() > maxValue){
                maxValue = entry.getValue();
                cookies.clear();
                cookies.add(entry.getKey());
            }
            if(entry.getValue() == maxValue){
                cookies.add(entry.getKey());
            }
        }
        return cookies;
    }
}
