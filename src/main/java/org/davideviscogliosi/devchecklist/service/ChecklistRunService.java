package org.davideviscogliosi.devchecklist.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.davideviscogliosi.devchecklist.dto.ChecklistItemRunDTO;
import org.davideviscogliosi.devchecklist.dto.ChecklistRunDTO;
import org.davideviscogliosi.devchecklist.exception.ChecklistException;
import org.davideviscogliosi.devchecklist.mapper.ChecklistRunMapper;
import org.davideviscogliosi.devchecklist.model.ChecklistItemRun;
import org.davideviscogliosi.devchecklist.model.ChecklistRun;
import org.davideviscogliosi.devchecklist.model.ChecklistTemplate;
import org.davideviscogliosi.devchecklist.model.User;
import org.davideviscogliosi.devchecklist.repository.ChecklistRunRepository;
import org.davideviscogliosi.devchecklist.repository.ChecklistTemplateRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChecklistRunService {

    private final ChecklistRunRepository runRepository;
    private final ChecklistTemplateRepository templateRepository;

    @Transactional
    public ChecklistRunDTO createRun(Long templateId, String runName, User user) {

        ChecklistTemplate template = templateRepository.findById(templateId)
                .orElseThrow(() -> new ChecklistException("Template not found", HttpStatus.NOT_FOUND));

        List<ChecklistItemRun> itemRuns = template.getItems().stream()
                .map(itemTemplate -> ChecklistItemRun.builder()
                        .description(itemTemplate.getDescription())
                        .itemOrder(itemTemplate.getItemOrder())
                        .done(false)
                        .build())
                .collect(Collectors.toList());

        ChecklistRun run = ChecklistRun.builder()
                .name(runName)
                .template(template)
                .items(itemRuns)
                .createdAt(LocalDateTime.now())
                .owner(user)
                .build();

        itemRuns.forEach(itemRun -> itemRun.setRun(run));

        return ChecklistRunMapper.toDto(runRepository.save(run));
    }

    @Transactional
    public ChecklistRunDTO getRun(Long runId) {
        return ChecklistRunMapper.toDto(runRepository.findById(runId)
                .orElseThrow(() -> new ChecklistException("Run not found", HttpStatus.NOT_FOUND)));
    }

    @Transactional
    public ChecklistRunDTO toggleItem(Long runId, Long itemId) {
        ChecklistRunDTO run = getRun(runId);

        ChecklistItemRunDTO itemRun = run.getItems().stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new ChecklistException("Item not found", HttpStatus.NOT_FOUND));

        itemRun.setDone(!itemRun.getDone());
        return ChecklistRunMapper.toDto(runRepository.save(ChecklistRunMapper.toEntity(run)));
    }
}
