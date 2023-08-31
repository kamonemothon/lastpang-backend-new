package app.lastpang.hour.domain.schedule.presentation;

import app.lastpang.hour.domain.schedule.presentation.dto.request.ScheduleSaveRequest;
import app.lastpang.hour.domain.schedule.presentation.dto.response.ScheduleFindAllResponse;
import app.lastpang.hour.domain.schedule.presentation.dto.response.ScheduleSaveResponse;
import app.lastpang.hour.domain.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("")
    public ResponseEntity<ScheduleSaveResponse> saveSchedule(
            @RequestParam(value="common", required = false) Long commonScheduleId,
            @RequestBody ScheduleSaveRequest request) {

        if(commonScheduleId == null) {
            return ResponseEntity.ok().body(scheduleService.saveFirstSchedule(request));
        }
        return ResponseEntity.ok().body(scheduleService.saveExistSchedule(request, commonScheduleId));
    }

    @GetMapping("")
    public ResponseEntity<List<ScheduleFindAllResponse>> findAllSchedule() {

        return ResponseEntity.ok().body(scheduleService.findAllSchedule());
    }

}
