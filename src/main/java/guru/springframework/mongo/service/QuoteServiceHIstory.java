package guru.springframework.mongo.service;

import guru.springframework.mongo.domain.QuoteHistory;
import guru.springframework.mongo.model.Quote;
import reactor.core.publisher.Mono;

public interface QuoteServiceHIstory {

    Mono<QuoteHistory> saveQuoteToMongo(Quote quote);
}
