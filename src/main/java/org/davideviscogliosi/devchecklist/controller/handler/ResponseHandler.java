package org.davideviscogliosi.devchecklist.controller.handler;

import lombok.extern.slf4j.Slf4j;
import org.davideviscogliosi.devchecklist.dto.OutcomeDTO;
import org.davideviscogliosi.devchecklist.exception.ChecklistException;
import org.davideviscogliosi.devchecklist.mdc.MdcLogCostants;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Slf4j
public class ResponseHandler {
    public <T> ResponseEntity<OutcomeDTO<T>> executeWithResponse(String processName, ServiceExecutor<T> executor) {
        
        OutcomeDTO<T> outcome = new OutcomeDTO<>();
        HttpStatus status = HttpStatus.OK;
        setupMdcContext(processName);

        try {
            outcome.setBody(executor.execute());
            outcome.setMessage("SUCCESS");
        } catch (ChecklistException ex) {
            status = handleChecklistException(ex, outcome);
        } catch (Exception e) {
            status = handleGenericException(e, outcome);
        }
        return ResponseEntity.status(status).body(outcome);
    }

    private void setupMdcContext(String processName) {
        MDC.put(MdcLogCostants.MDC_PROCESS, processName);
    }

    private HttpStatus handleChecklistException(ChecklistException ex, OutcomeDTO<?> outcome) {
        log.error(ex.getMessage(), ex);
        outcome.setMessage(ex.getMessage());
        return ex.getStatus();
    }

    private HttpStatus handleGenericException(Exception e, OutcomeDTO<?> outcome) {
        log.error("GENERAL INTERNAL ERROR", e);
        outcome.setMessage("GENERAL INTERNAL ERROR");
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @FunctionalInterface
    public interface ServiceExecutor<T> {
        T execute() throws Exception;
    }
}
