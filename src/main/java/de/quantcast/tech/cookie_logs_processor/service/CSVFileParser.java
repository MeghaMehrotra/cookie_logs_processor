package de.quantcast.tech.cookie_logs_processor.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import de.quantcast.tech.cookie_logs_processor.model.Cookie;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mmehrotra
 */
@Service
public class CSVFileParser {

    private static final Logger logger = LogManager.getLogger(CSVFileParser.class);

    public List<Cookie> getCookiesFromFile(String fileName){


        List<Cookie> cookieList = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            reader.skip(1);
            List<String[]> cookies = reader.readAll();
            for(String[] str :cookies){
                ZonedDateTime zonedDateTime = ZonedDateTime.parse(str[1], DateTimeFormatter.ISO_DATE_TIME);
                Cookie cookie = new Cookie(str[0],zonedDateTime.toLocalDate());
                cookieList.add(cookie);
            }
        } catch (IOException e) {
            logger.error("IOException due to error in network or file does not exist");
            e.printStackTrace();
        } catch (CsvException e) {
            logger.error("Exception while parsing a CSV File");
            e.printStackTrace();
        }
        return cookieList;
   }
}
