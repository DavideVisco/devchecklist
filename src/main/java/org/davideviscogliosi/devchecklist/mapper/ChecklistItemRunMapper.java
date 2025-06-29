package org.davideviscogliosi.devchecklist.mapper;

import org.davideviscogliosi.devchecklist.dto.ChecklistItemRunDTO;
import org.davideviscogliosi.devchecklist.dto.ChecklistRunDTO;
import org.davideviscogliosi.devchecklist.model.ChecklistItemRun;
import org.davideviscogliosi.devchecklist.model.ChecklistRun;

import java.util.ArrayList;
import java.util.List;

public class ChecklistItemRunMapper {

    public static ChecklistItemRun toEntity(ChecklistItemRunDTO dto){

        ChecklistItemRun entity =  new ChecklistItemRun();

        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setRun(dto.getRun());
        entity.setItemOrder(dto.getItemOrder());
        entity.setDone(dto.getDone());

        return entity;
    }

    public static List<ChecklistItemRun> toEntityList(List<ChecklistItemRunDTO> dtoList){

        List<ChecklistItemRun> entities = new ArrayList<>();

        for(ChecklistItemRunDTO checklistItemRunDTO : dtoList){
            entities.add(toEntity(checklistItemRunDTO));
        }

        return entities;
    }

    public static ChecklistItemRunDTO toDto(ChecklistItemRun entity){

        ChecklistItemRunDTO dto = new ChecklistItemRunDTO();

        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setRun(entity.getRun());
        dto.setItemOrder(entity.getItemOrder());
        dto.setDone(entity.getDone());

        return dto;
    }

    public static List<ChecklistItemRunDTO> toDtoList(List<ChecklistItemRun> entityList){

        List<ChecklistItemRunDTO> dtos = new ArrayList<>();

        for(ChecklistItemRun checklistRun : entityList){
            dtos.add(toDto(checklistRun));
        }

        return dtos;
    }
}
