package org.davideviscogliosi.devchecklist;


import org.davideviscogliosi.devchecklist.dto.ChecklistItemRunDTO;
import org.davideviscogliosi.devchecklist.dto.ChecklistRunDTO;
import org.davideviscogliosi.devchecklist.exception.ChecklistException;
import org.davideviscogliosi.devchecklist.model.ChecklistItemRun;
import org.davideviscogliosi.devchecklist.model.ChecklistItemTemplate;
import org.davideviscogliosi.devchecklist.model.ChecklistRun;
import org.davideviscogliosi.devchecklist.model.ChecklistTemplate;
import org.davideviscogliosi.devchecklist.repository.ChecklistTemplateRepository;
import org.davideviscogliosi.devchecklist.service.ChecklistRunService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


@SpringBootTest
public class ChecklistRunServiceTest {

    @Autowired
    private ChecklistRunService runService;

    @Autowired
    private ChecklistTemplateRepository templateRepository;


    @Test
    public void testCreateRun() {
        ChecklistItemTemplate item1 = ChecklistItemTemplate.builder()
                .description("Item 1")
                .itemOrder(1)
                .build();

        ChecklistItemTemplate item2 = ChecklistItemTemplate.builder()
                .description("Item 2")
                .itemOrder(2)
                .build();

        ChecklistTemplate template = ChecklistTemplate.builder()
                .name("Test Template")
                .description("A test template")
                .items(List.of(item1, item2))
                .build();

        item1.setTemplate(template);
        item2.setTemplate(template);

        template = templateRepository.save(template);

        ChecklistRunDTO run = runService.createRun(template.getId(), "My First Run");

        assertThat(run.getId()).isNotNull();
        assertThat(run.getItems()).hasSize(2);
        assertThat(run.getItems().getFirst().getDescription()).isEqualTo("Item 1");
        assertThat(run.getItems().getFirst().getDone()).isFalse();
    }

    @Test
    public void testGetRun() {
        ChecklistItemTemplate item1 = ChecklistItemTemplate.builder()
                .description("Build passes")
                .itemOrder(1)
                .build();
        ChecklistTemplate template = ChecklistTemplate.builder()
                .name("Test Template")
                .description("A test template")
                .items(List.of(item1))
                .build();
        item1.setTemplate(template);
        template = templateRepository.save(template);

        ChecklistRunDTO run = runService.createRun(template.getId(), "Check release");

        ChecklistRunDTO fetched = runService.getRun(run.getId());

        assertThat(fetched.getId()).isEqualTo(run.getId());
        assertThat(fetched.getItems()).hasSize(1);
        assertThat(fetched.getItems().getFirst().getDescription()).isEqualTo("Build passes");
    }

    @Test
    public void testToggleItem() {
        ChecklistItemTemplate item = ChecklistItemTemplate.builder()
                .description("Push tag")
                .itemOrder(1)
                .build();
        ChecklistTemplate template = ChecklistTemplate.builder()
                .name("Deploy Template")
                .description("Deploy test")
                .items(List.of(item))
                .build();
        item.setTemplate(template);
        template = templateRepository.save(template);

        ChecklistRunDTO run = runService.createRun(template.getId(), "Deploy 1.0");
        ChecklistItemRunDTO itemRun = run.getItems().getFirst();

        ChecklistRunDTO updatedRun = runService.toggleItem(run.getId(), itemRun.getId());

        ChecklistItemRunDTO updatedItem = updatedRun.getItems().getFirst();
        assertThat(updatedItem.getDone()).isTrue();

        updatedRun = runService.toggleItem(run.getId(), itemRun.getId());
        updatedItem = updatedRun.getItems().getFirst();
        assertThat(updatedItem.getDone()).isFalse();
    }

    @Test
    public void testCreateRunFailsWithInvalidTemplate() {
        assertThatThrownBy(() -> runService.createRun(999L, "Bad Run"))
                .isInstanceOf(ChecklistException.class)
                .hasMessageContaining("Template not found");
    }

    @Test
    public void testToggleItemFailsIfItemNotFound() {
        ChecklistItemTemplate item = ChecklistItemTemplate.builder()
                .description("Dummy")
                .itemOrder(1)
                .build();
        ChecklistTemplate template = ChecklistTemplate.builder()
                .name("Template")
                .description("X")
                .items(List.of(item))
                .build();
        item.setTemplate(template);
        template = templateRepository.save(template);

        ChecklistRunDTO run = runService.createRun(template.getId(), "Run");

        assertThatThrownBy(() -> runService.toggleItem(run.getId(), 999L))
                .isInstanceOf(ChecklistException.class)
                .hasMessageContaining("Item not found");

    }
}