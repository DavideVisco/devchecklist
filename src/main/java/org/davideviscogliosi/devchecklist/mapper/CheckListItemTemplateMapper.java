package org.davideviscogliosi.devchecklist.mapper;

import org.davideviscogliosi.devchecklist.dto.ChecklistItemTemplateDTO;
import org.davideviscogliosi.devchecklist.model.ChecklistItemTemplate;

import java.util.ArrayList;
import java.util.List;

public class CheckListItemTemplateMapper {

    public static ChecklistItemTemplate toEntity(ChecklistItemTemplateDTO dto){

        ChecklistItemTemplate entity =  new ChecklistItemTemplate();

        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setItemOrder(dto.getItemOrder());

        return entity;
    }

    public static List<ChecklistItemTemplate> toEntityList(List<ChecklistItemTemplateDTO> dtoList){

        List<ChecklistItemTemplate> entities = new ArrayList<>();

        for(ChecklistItemTemplateDTO checklistItemTemplate : dtoList){
            entities.add(toEntity(checklistItemTemplate));
        }

        return entities;
    }

    public static ChecklistItemTemplateDTO toDto(ChecklistItemTemplate entity){

        ChecklistItemTemplateDTO dto = new ChecklistItemTemplateDTO();

        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setItemOrder(entity.getItemOrder());

        return dto;
    }

    public static List<ChecklistItemTemplateDTO> toDtoList(List<ChecklistItemTemplate> entityList){

        List<ChecklistItemTemplateDTO> dtos = new ArrayList<>();

        for(ChecklistItemTemplate checklistItemTemplate : entityList){
            dtos.add(toDto(checklistItemTemplate));
        }

        return dtos;
    }
}
