package org.davideviscogliosi.devchecklist.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "checklist_item_runs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChecklistItemRun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Integer itemOrder;

    private Boolean done = false;

    @ManyToOne
    @JoinColumn(name = "run_id")
    private ChecklistRun run;
}
