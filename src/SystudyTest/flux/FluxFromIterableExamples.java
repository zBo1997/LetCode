package SystudyTest.flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class FluxFromIterableExamples {
    public static void main(String[] args) {
        Mono<List<String>> collect =
            Flux.just("A", "B", "C", "D").map(String::toLowerCase).subscribeOn(Schedulers.boundedElastic())
                .collect(Collectors.toList());
        Flux.merge(collect).toStream().forEach(System.out::println);

    }
}
