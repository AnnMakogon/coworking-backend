package dev.coworking.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="workspaces")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "workspaces_seq", sequenceName = "workspaces_seq", allocationSize = 1)
public class WorkspaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "workspace_seq")
    private Long id;

    private String name;
    private String description;
    private String schedule;

    private Double latitude;
    private Double longitude;
    private String address;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AttachmentEntity> attachments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "workspace", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TableEntity> tables;

}