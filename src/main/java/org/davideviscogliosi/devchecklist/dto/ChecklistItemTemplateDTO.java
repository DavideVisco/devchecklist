package org.davideviscogliosi.devchecklist.dto;

import lombok.Data;


@Data
public class ChecklistItemTemplateDTO {

    private Long id;
    private String description;
    private Integer itemOrder;
}
