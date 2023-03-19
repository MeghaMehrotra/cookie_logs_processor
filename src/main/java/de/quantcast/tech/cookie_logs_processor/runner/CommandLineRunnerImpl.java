package de.quantcast.tech.cookie_logs_processor.runner;

import de.quantcast.tech.cookie_logs_processor.model.Cookie;
import de.quantcast.tech.cookie_logs_processor.service.CSVFileParser;
import de.quantcast.tech.cookie_logs_processor.service.CookieService;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.swing.text.Style;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * @author mmehrotra
 */
@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private static final Logger logger = LogManager.getLogger(CommandLineRunnerImpl.class);

    private final CookieService cookieService;
    private final CSVFileParser csvFileParser;

    @Autowired
    public CommandLineRunnerImpl(CookieService cookieService, CSVFileParser csvFileParser) {
        this.cookieService = cookieService;
        this.csvFileParser = csvFileParser;
    }

    @Override
    public void run(String... args) {
            Options options = new Options();
            options.addOption(Option.builder("f")
                    .longOpt("file")
                    .required(true)
                    .hasArg(true)
                    .desc("CSV file containing cookie data")
                    .build());
            options.addOption(Option.builder("d")
                    .longOpt("date")
                    .required(true)
                    .hasArg(true)
                    .desc("Date to find most active cookie for (yyyy-MM-dd)")
                    .build());

            CommandLineParser parser = new DefaultParser();
            CommandLine cmd;
            try {
                cmd = parser.parse(options, args);
            } catch (ParseException e) {
                logger.error("Error parsing command line arguments: " + e.getMessage());
                return;
            }

            String fileName = cmd.getOptionValue("file");
            String dateString = cmd.getOptionValue("date");

            LocalDate date;
            try {
                date = LocalDate.parse(dateString);
            } catch (DateTimeParseException e) {
                logger.error("Error parsing date: " + e.getMessage());
                return;
            }

            String currentDir = System.getProperty("user.dir");
            System.out.println(currentDir);
            // get all cookies from the csv file
            List<Cookie> cookieList = csvFileParser.getCookiesFromFile(currentDir+"/src/main/resources/"+fileName);

            //get most active cookies
            List<String> cookies = cookieService.findMostActiveCookies(date,cookieList);

            cookies.forEach(cookie -> System.out.println(cookie));
        };

}
