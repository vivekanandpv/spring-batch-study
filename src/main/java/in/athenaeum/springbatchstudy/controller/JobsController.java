package in.athenaeum.springbatchstudy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/jobs")
public class JobsController {
    @GetMapping
    public String get() {
        return "Working...";
    }
}
