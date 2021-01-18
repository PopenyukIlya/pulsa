package pulse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pulse.controller.dto.ResultByQuizDto;
import pulse.controller.dto.ResultDto;
import pulse.domain.User;
import pulse.service.ResultService;

import java.util.List;

@CrossOrigin(origins = "https://pacific-fjord-28473.herokuapp.com", maxAge = 3600)
@RestController
@RequestMapping("/api/results")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @GetMapping
    public List<ResultDto> results(@AuthenticationPrincipal User user) {
        return resultService.results(user);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<ResultByQuizDto> resultByTest(@PathVariable(value = "id") Long id) {
        return resultService.resultByTest(id);
    }
}
