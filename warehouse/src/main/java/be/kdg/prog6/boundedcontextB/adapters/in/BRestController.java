package be.kdg.prog6.boundedcontextB.adapters.in;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BRestController {


    @GetMapping("/hellob")
    public void sayHelloB(){
        System.out.println("Hello BoundedContext B");
    }

}
