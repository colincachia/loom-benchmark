package loombenchmark.task;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.concurrent.Callable;

@Slf4j
public class IOTask implements Task<HttpResponse<String>> {

    private final HttpClient httpClient;

    public IOTask() {
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
    }

    @Override
    public Callable<HttpResponse<String>> work() {
        return () -> {
            final HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("https://httpbin.org/get"))
                    .setHeader("User-Agent", "Loom HttpClient Bot")
                    .build();
            return execute(request);
        };
    }

    @SneakyThrows
    private HttpResponse<String> execute(final HttpRequest request) {
        final ZonedDateTime start = ZonedDateTime.now();
        final HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        final ZonedDateTime end = ZonedDateTime.now();
        log.debug("Received Response - StatusCode:{} - ResponseTime:{}ms", response.statusCode(), Duration.between(start, end).toMillis());
        return response;
    }

}
