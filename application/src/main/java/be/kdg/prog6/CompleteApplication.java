package be.kdg.prog6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(excludeFilters = {
        @ComponentScan.Filter(type = FilterType.REGEX, pattern="be.kdg.prog6.*.*Application")
})
public class CompleteApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompleteApplication.class, args);
    }
}
