package de.turing85;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;
import org.apache.camel.main.MainConfigurationProperties;

import static org.apache.camel.builder.endpoint.StaticEndpointBuilders.timer;

public class Timer {
  public static void main(String... args) throws Exception {
    Main main = new Main();
    try (MainConfigurationProperties configure = main.configure()) {
      configure.addRoutesBuilder(timerRoute());
      main.run();
    }
  }

  static RouteBuilder timerRoute() {
    return new RouteBuilder() {
      private final AtomicInteger calls = new AtomicInteger();

      @Override
      public void configure() {
        // @formatter:off
        from(
            timer("timer")
                .period(Duration.ofMillis(100).toMillis())
                .fixedRate(true))
            .process(exchange -> {
              log.info("calls: {}", calls.incrementAndGet());
              exchange.getIn().setBody(new LargeObject());
            })
            .log("${body.fieldTwo}");
        // @formatter:on
      }
    };
  }
}
