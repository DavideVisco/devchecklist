package org.davideviscogliosi.devchecklist.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "checklist_item_templates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChecklistItemTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Integer itemOrder;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private ChecklistTemplate template;
}
