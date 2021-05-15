package hikingBuddy.joinevents;

import hikingBuddy.joinevents.JoinEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class JoinEventController {

    JoinEventService joinEventService;

    @Autowired
    public JoinEventController(JoinEventService joinEventService) {
        this.joinEventService = joinEventService;
    }

    @PostMapping("/{eventId}/join")
    public ResponseEntity<JoinEvent> addJoin(@PathVariable Long EventId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(joinEventService.addJoin(EventId));
    }

    @GetMapping("/{eventId}/join")
    public ResponseEntity<List<JoinEvent>> getAllJoin(@PathVariable Long eventId) {
        return ResponseEntity.ok(JoinEventService.getAllJoin(eventId));
    }

    @DeleteMapping("/{eventId}/join")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeJoin(@PathVariable Long eventId) {
        joinEventService.removeJoin(eventId);
    }
}