package org.davideviscogliosi.devchecklist.dto;

import lombok.Data;
import org.davideviscogliosi.devchecklist.model.ChecklistTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ChecklistRunDTO {

    private Long id;
    private String name;
    private ChecklistTemplate template;
    private List<ChecklistItemRunDTO> items;
    private LocalDateTime createdAt;

}
