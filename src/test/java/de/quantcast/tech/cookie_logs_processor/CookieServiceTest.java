package de.quantcast.tech.cookie_logs_processor;

import de.quantcast.tech.cookie_logs_processor.model.Cookie;
import de.quantcast.tech.cookie_logs_processor.service.CSVFileParser;
import de.quantcast.tech.cookie_logs_processor.service.CookieService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * @author mmehrotra
 */

@RunWith(MockitoJUnitRunner.class)
public class CookieServiceTest {

    @Mock
    private CSVFileParser csvFileParser;

    @InjectMocks
    private CookieService cookieService;

    @Test
    public void testFindMostActiveCookie() {
        // Given
        String dateString = "2022-01-01";
        LocalDate date = LocalDate.parse(dateString);
        List<Cookie> cookieList = Arrays.asList(
                new Cookie("cookie1", ZonedDateTime.parse("2023-03-17T01:00:00+00:00", DateTimeFormatter.ISO_DATE_TIME).toLocalDate()),
                new Cookie("cookie2", ZonedDateTime.parse("2023-03-17T02:00:00+00:00", DateTimeFormatter.ISO_DATE_TIME).toLocalDate()),
                new Cookie("cookie1", ZonedDateTime.parse("2023-03-17T03:00:00+00:00", DateTimeFormatter.ISO_DATE_TIME).toLocalDate()),
                new Cookie("cookie3", ZonedDateTime.parse("2023-03-17T04:00:00+00:00", DateTimeFormatter.ISO_DATE_TIME).toLocalDate()),
                new Cookie("cookie1", ZonedDateTime.parse("2023-03-17T05:00:00+00:00", DateTimeFormatter.ISO_DATE_TIME).toLocalDate())
        );

        // When
        Mockito.when(csvFileParser.getCookiesFromFile(Mockito.anyString())).thenReturn(cookieList);
        List<String> result = cookieService.findMostActiveCookies(date, cookieList);

        // Then
        Assert.assertEquals(Arrays.asList("cookie1"), result);
    }


    @Test
    public void testFindMostActiveMultipleCookies() {
        // Given
        String dateString = "2022-01-01";
        LocalDate date = LocalDate.parse(dateString);
        List<Cookie> cookieList = Arrays.asList(
                new Cookie("cookie1", ZonedDateTime.parse("2023-03-17T01:00:00+00:00", DateTimeFormatter.ISO_DATE_TIME).toLocalDate()),
                new Cookie("cookie2", ZonedDateTime.parse("2023-03-17T02:00:00+00:00", DateTimeFormatter.ISO_DATE_TIME).toLocalDate()),
                new Cookie("cookie2", ZonedDateTime.parse("2023-03-17T03:00:00+00:00", DateTimeFormatter.ISO_DATE_TIME).toLocalDate()),
                new Cookie("cookie3", ZonedDateTime.parse("2023-03-17T04:00:00+00:00", DateTimeFormatter.ISO_DATE_TIME).toLocalDate()),
                new Cookie("cookie1", ZonedDateTime.parse("2023-03-17T05:00:00+00:00", DateTimeFormatter.ISO_DATE_TIME).toLocalDate())
        );

        // When
        Mockito.when(csvFileParser.getCookiesFromFile(Mockito.anyString())).thenReturn(cookieList);
        List<String> result = cookieService.findMostActiveCookies(date, cookieList);

        // Then
        Assert.assertEquals(Arrays.asList("cookie1,cookie2"), result);
    }
}
