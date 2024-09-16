package guru.springframework.mongo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@Slf4j
@RequiredArgsConstructor
public class QuoteRunner implements CommandLineRunner {

    private final QuoteGeneratorService quoteGeneratorService;
    private final QuoteServiceHIstory quoteServiceHIstory;

    @Override
    public void run(String... args) throws Exception {
        quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(100l))
                .take(50)
                .log("Set Quote:")
                .flatMap(quoteServiceHIstory::saveQuoteToMongo)
                .subscribe(savedQuote -> {
                    log.debug("Saved Quote: {}", savedQuote);
                }, throwable -> {
                    // handle error here
                    log.debug("Some Error", throwable);
                }, () -> {
                    log.debug("All done");
                });
    }

}
