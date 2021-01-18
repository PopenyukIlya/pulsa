package pulse.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pulse.domain.quiz.Pulse;
import pulse.domain.repos.PulseRepo;

import java.util.Random;

@CrossOrigin(origins = "https://pacific-fjord-28473.herokuapp.com", maxAge = 3600)
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

    @GetMapping("/push")
    public void save(@RequestParam int pulse) {
        Pulse puls = new Pulse();
        puls.setPulse(pulse);
        pulseRepo.save(puls);
    }

}
