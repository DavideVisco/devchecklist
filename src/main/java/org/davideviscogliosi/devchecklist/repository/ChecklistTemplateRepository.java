package org.davideviscogliosi.devchecklist.repository;

import org.davideviscogliosi.devchecklist.model.ChecklistTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChecklistTemplateRepository extends JpaRepository<ChecklistTemplate, Long> {
}
