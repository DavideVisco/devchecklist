package org.davideviscogliosi.devchecklist.mapper;

import org.davideviscogliosi.devchecklist.dto.ChecklistItemTemplateDTO;
import org.davideviscogliosi.devchecklist.dto.ChecklistTemplateDTO;
import org.davideviscogliosi.devchecklist.model.ChecklistItemTemplate;
import org.davideviscogliosi.devchecklist.model.ChecklistTemplate;

import java.util.ArrayList;
import java.util.List;

public class ChecklistTemplateMapper {

    public static ChecklistTemplate toEntity(ChecklistTemplateDTO dto){

        ChecklistTemplate entity =  new ChecklistTemplate();

        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setName(dto.getName());
        entity.setItems(CheckListItemTemplateMapper.toEntityList(dto.getItems()));

        return entity;
    }

    public static ChecklistTemplateDTO toDto(ChecklistTemplate entity){

        ChecklistTemplateDTO dto = new ChecklistTemplateDTO();

        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setName(dto.getName());
        dto.setItems(CheckListItemTemplateMapper.toDtoList(entity.getItems()));

        return dto;
    }

    public static List<ChecklistTemplateDTO> toDtoList(List<ChecklistTemplate> entityList){

        List<ChecklistTemplateDTO> dtos = new ArrayList<>();

        for(ChecklistTemplate checklistItemTemplate : entityList){
            dtos.add(toDto(checklistItemTemplate));
        }

        return dtos;
    }

}
