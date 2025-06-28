package org.davideviscogliosi.devchecklist.dto;

import lombok.Data;


import java.util.List;

@Data
public class ChecklistTemplateDTO {


    private Long id;
    private String name;
    private String description;
    private List<ChecklistItemTemplateDTO> items;

}
