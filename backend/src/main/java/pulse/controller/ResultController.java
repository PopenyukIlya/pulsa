package pulse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pulse.controller.dto.ResultDto;
import pulse.domain.User;
import pulse.service.ResultService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/results")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @GetMapping
    public List<ResultDto> results(@AuthenticationPrincipal User user) {
        return resultService.results(user);
    }
}
