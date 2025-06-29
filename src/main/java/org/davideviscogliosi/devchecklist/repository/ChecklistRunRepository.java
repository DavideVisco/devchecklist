package org.davideviscogliosi.devchecklist.repository;


import org.davideviscogliosi.devchecklist.model.ChecklistRun;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChecklistRunRepository extends JpaRepository<ChecklistRun, Long> {
}
