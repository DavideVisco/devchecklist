package org.davideviscogliosi.devchecklist.controller;


import org.davideviscogliosi.devchecklist.controller.handler.ChecklistRunHandler;
import org.davideviscogliosi.devchecklist.dto.ChecklistRunDTO;
import org.davideviscogliosi.devchecklist.dto.OutcomeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ChecklistRunController {

    private final ChecklistRunHandler checklistRunHandler;

    public ChecklistRunController(ChecklistRunHandler checklistRunHandler) {
        this.checklistRunHandler = checklistRunHandler;
    }

    @PostMapping("/templates/{templateId}/runs")
    public ResponseEntity<OutcomeDTO<ChecklistRunDTO>> createRun(@PathVariable Long templateId, @RequestParam String name) {
        return checklistRunHandler.createRun(templateId,name);
    }

    @GetMapping("/runs/{runId}")
    public ResponseEntity<OutcomeDTO<ChecklistRunDTO>> getRun(@PathVariable Long runId) {
        return checklistRunHandler.getRun(runId);
    }

    @PostMapping("/runs/{runId}/items/{itemId}/toggle")
    public ResponseEntity<OutcomeDTO<ChecklistRunDTO>> toggleItem(@PathVariable Long runId, @PathVariable Long itemId) {
        return checklistRunHandler.toggleItem(runId,itemId);
    }
}
