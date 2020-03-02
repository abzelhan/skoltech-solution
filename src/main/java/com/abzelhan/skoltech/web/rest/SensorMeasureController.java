package com.abzelhan.skoltech.web.rest;

import com.abzelhan.skoltech.constant.ApiPaths;
import com.abzelhan.skoltech.service.SensorMeasureService;
import com.abzelhan.skoltech.service.dto.SensorMeasureAverageDTO;
import com.abzelhan.skoltech.service.dto.SensorMeasureDTO;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(ApiPaths.API_GENERAL_PATH)
public class SensorMeasureController {

    private final static Logger log = LoggerFactory.getLogger(SensorMeasureController.class);

    private final SensorMeasureService measureService;

    @PostMapping("/save")
    public ResponseEntity<List<SensorMeasureDTO>> save(@RequestBody List<SensorMeasureDTO> dto) {
        log.debug("save, dto: {}", dto);
        return ResponseEntity.ok(this.measureService.save(dto));
    }

    @GetMapping("/history")
    public ResponseEntity<List<SensorMeasureDTO>> history(@RequestParam(name = "id") Long sensorId,
                                                          @RequestParam(name = "from") Long from,
                                                          @RequestParam(name = "to") Long to) {
        log.debug("history, params: {}, {}, {}", sensorId, from, to);
        return ResponseEntity.ok(
                this.measureService.getHistory(sensorId,
                        LocalDateTime.ofInstant(Instant.ofEpochMilli(from), ZoneId.systemDefault()),
                        LocalDateTime.ofInstant(Instant.ofEpochMilli(to), ZoneId.systemDefault()))
        );
    }

    @GetMapping("/latest")
    public ResponseEntity<List<SensorMeasureDTO>> latest(@RequestParam(name = "id") Long objectId) {
        log.debug("latest, params: {}", objectId);
        return ResponseEntity.ok(this.measureService.findAllByObject(objectId));
    }

    @GetMapping("/avg")
    public ResponseEntity<List<SensorMeasureAverageDTO>> average() {
        log.debug("average");
        return ResponseEntity.ok(this.measureService.getAverages());
    }

}
