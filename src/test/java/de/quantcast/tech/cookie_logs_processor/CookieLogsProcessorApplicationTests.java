package de.quantcast.tech.cookie_logs_processor;

import de.quantcast.tech.cookie_logs_processor.runner.CookieCommandLineRunner;
import de.quantcast.tech.cookie_logs_processor.service.CSVFileParser;
import de.quantcast.tech.cookie_logs_processor.service.CookieService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class MyCommandLineRunnerTest {

    @Mock
    private CookieCommandLineRunner testCommandLineRunner;

    @InjectMocks
    private CSVFileParser csvFileParser;

    @InjectMocks
    private CookieService cookieService;

    @Test
    public void testMyCommandLineRunner()  {
        String[] args = {"-f", "test.csv", "-d", "2023-03-17"};
        testCommandLineRunner.run(args);
        verify(testCommandLineRunner).run(args);
    }
}