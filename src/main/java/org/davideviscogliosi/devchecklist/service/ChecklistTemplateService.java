package org.davideviscogliosi.devchecklist.service;

import org.davideviscogliosi.devchecklist.dto.ChecklistTemplateDTO;
import org.davideviscogliosi.devchecklist.exception.ChecklistException;
import org.davideviscogliosi.devchecklist.mapper.ChecklistTemplateMapper;
import org.davideviscogliosi.devchecklist.model.ChecklistTemplate;
import org.davideviscogliosi.devchecklist.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.davideviscogliosi.devchecklist.repository.ChecklistTemplateRepository;

import java.util.List;

@Service
public class ChecklistTemplateService {

    private final ChecklistTemplateRepository templateRepository;

    public ChecklistTemplateService(ChecklistTemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    public ChecklistTemplateDTO createTemplate(ChecklistTemplateDTO template, User user) {
        ChecklistTemplate checklistTemplate = ChecklistTemplateMapper.toEntity(template);
        checklistTemplate.setOwner(user);
        return ChecklistTemplateMapper.toDto(templateRepository.save(checklistTemplate));
    }

    public List<ChecklistTemplateDTO> getAllTemplates() {
        return ChecklistTemplateMapper.toDtoList(templateRepository.findAll());
    }

    public ChecklistTemplateDTO getTemplateById(Long id) {
        return ChecklistTemplateMapper.toDto(templateRepository.findById(id)
                .orElseThrow(() -> new ChecklistException("Template not found with id: " + id, HttpStatus.NOT_FOUND)));
    }
}