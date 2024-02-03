package ru.gb.tasks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.tasks.dto.PerformerDto;
import ru.gb.tasks.model.Performer;
import ru.gb.tasks.service.PerformerService;
import ru.gb.tasks.util.Converter;

import java.util.List;

@RestController
@RequestMapping("/api/performer")
public class PerformerController {

    private final PerformerService performerService;
    private final Converter converter;

    @Autowired
    public PerformerController(PerformerService performerService,
                               Converter converter) {
        this.performerService = performerService;
        this.converter = converter;
    }

    @GetMapping()
    public ResponseEntity<List<PerformerDto>> getAll() {
        return ResponseEntity.ok(performerService.findAll().stream()
                .map(converter::getDto).toList());
    }

    @PostMapping()
    public ResponseEntity<PerformerDto> create(@RequestBody Performer performer) {
        return ResponseEntity.ok(converter.getDto(performerService.create(performer)));
    }

    @PatchMapping("/{id}/task")
    public ResponseEntity<HttpStatus> addTask(@PathVariable Long id,
                                              @RequestParam Long taskId) {
        performerService.addTask(id, taskId);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
