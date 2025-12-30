package sb_you_e8.sb_you_e8.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/demo")
    @PreAuthorize("hasAuthority('read')")
    public String demo(){
        return "Demo !!";
    }

    @GetMapping("/demo2")
    @PreAuthorize("hasAuthority('write')")
    public String demo2(){
        return "Demo @@2";
    }
}
