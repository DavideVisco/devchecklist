package org.davideviscogliosi.devchecklist.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "checklist_templates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChecklistTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "template",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ChecklistItemTemplate> items;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User owner;

}
