package org.davideviscogliosi.devchecklist.mapper;

import org.davideviscogliosi.devchecklist.dto.ChecklistItemTemplateDTO;
import org.davideviscogliosi.devchecklist.dto.ChecklistRunDTO;
import org.davideviscogliosi.devchecklist.model.ChecklistItemTemplate;
import org.davideviscogliosi.devchecklist.model.ChecklistRun;

import java.util.ArrayList;
import java.util.List;

public class ChecklistRunMapper {

    public static ChecklistRun toEntity(ChecklistRunDTO dto){

        ChecklistRun entity =  new ChecklistRun();

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setItems(ChecklistItemRunMapper.toEntityList(dto.getItems()));
        entity.setTemplate(dto.getTemplate());
        entity.setCreatedAt(dto.getCreatedAt());

        return entity;
    }

    public static List<ChecklistRun> toEntityList(List<ChecklistRunDTO> dtoList){

        List<ChecklistRun> entities = new ArrayList<>();

        for(ChecklistRunDTO checklistRunDTO : dtoList){
            entities.add(toEntity(checklistRunDTO));
        }

        return entities;
    }

    public static ChecklistRunDTO toDto(ChecklistRun entity){

        ChecklistRunDTO dto = new ChecklistRunDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setItems(ChecklistItemRunMapper.toDtoList(entity.getItems()));
        dto.setTemplate(entity.getTemplate());
        dto.setCreatedAt(entity.getCreatedAt());

        return dto;
    }

    public static List<ChecklistRunDTO> toDtoList(List<ChecklistRun> entityList){

        List<ChecklistRunDTO> dtos = new ArrayList<>();

        for(ChecklistRun checklistRun : entityList){
            dtos.add(toDto(checklistRun));
        }

        return dtos;
    }

}
