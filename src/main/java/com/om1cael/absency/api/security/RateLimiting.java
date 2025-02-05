package com.om1cael.absency.api.security;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class RateLimiting {

    private final Bandwidth limit = Bandwidth.classic(10, Refill.greedy(10, Duration.ofSeconds(10)));

    private final Bucket bucket = Bucket.builder()
            .addLimit(limit)
            .build();

    public boolean tryConsume() {
        return bucket.tryConsume(1);
    }

}
