package SystudyTest.flux;

import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class TestFlux {

    public static void main(String[] args) {
        List<String> request = Arrays.asList("abc", "abcd", "klg", "klgh", "wold");
        Mono.just(request)
            .flatMapMany(TestFlux::getContext)
            .subscribe(System.out::println);

    }

    private static Flux<Context> getContext(List<String> list){
        // 模拟sink 创建出独立的flux对象，
        return Flux.create(sink -> {
            for (String string : list) {
                sink.next(new Context(string, 0));
            }
            sink.complete();
        });
    }

    @Data
    @AllArgsConstructor
    static class Context{
        String data;
        int status;
    }

}

