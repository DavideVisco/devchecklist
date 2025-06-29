package org.davideviscogliosi.devchecklist.dto;

import lombok.Data;
import org.davideviscogliosi.devchecklist.model.ChecklistRun;

@Data
public class ChecklistItemRunDTO {

    private Long id;

    private String description;

    private Integer itemOrder;

    private Boolean done ;

    private ChecklistRun run;
}
