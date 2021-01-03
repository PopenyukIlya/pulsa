package pulse.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pulse.domain.quiz.Pulse;
import pulse.domain.repos.PulseRepo;

import java.util.Random;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/pulse")
public class PulseController {

    @Autowired
    PulseRepo pulseRepo;

    @RequestMapping(method = RequestMethod.GET)
    public int getPulse() {
        Random rand = new Random();
        int randomNum = rand.nextInt((80-40) + 1) + 40;
        //pulseRepo.findAll().get(0);
        return randomNum;
    }

    @GetMapping("/{pulse}")
    public void save(@PathVariable("pulse") int id) {
        pulseRepo.deleteAll();
        Pulse pulse = new Pulse();
        pulse.setPulse(id);
        pulseRepo.save(pulse);
    }

}
