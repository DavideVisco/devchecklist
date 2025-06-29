package org.davideviscogliosi.devchecklist.controller.handler;

import org.davideviscogliosi.devchecklist.dto.ChecklistRunDTO;
import org.davideviscogliosi.devchecklist.dto.OutcomeDTO;
import org.davideviscogliosi.devchecklist.service.ChecklistRunService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class ChecklistRunHandler extends ResponseHandler{

    private final ChecklistRunService checklistRunService;

    public ChecklistRunHandler(ChecklistRunService checklistRunService) {
        this.checklistRunService = checklistRunService;
    }

    public ResponseEntity<OutcomeDTO<ChecklistRunDTO>> createRun(Long templateId, String runName){
        return executeWithResponse("create-run", () -> checklistRunService.createRun(templateId,runName));
    }

    public ResponseEntity<OutcomeDTO<ChecklistRunDTO>> getRun(Long runId){
        return executeWithResponse("get-run", () -> checklistRunService.getRun(runId));
    }

    public ResponseEntity<OutcomeDTO<ChecklistRunDTO>> toggleItem(Long runId, Long itemId) {
        return executeWithResponse("toggle-item",() -> checklistRunService.toggleItem(runId,itemId));
    }


}
