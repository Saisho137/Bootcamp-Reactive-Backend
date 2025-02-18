package co.pragma.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HealthCheckController {
    @GetMapping(path = "/validate-service")
    public String validateServiceOn() {
        return "The microservice is up";
    }
}
