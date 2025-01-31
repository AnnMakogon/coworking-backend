package dev.coworking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WorkspaceCreate {

    private Long id;

    private String name;
    private String description;
    private String schedule;

    private Double latitude;
    private Double longitude;
    private String address;

    private List<Attachment> attachments;

    private List<Table> tables;
    private Long credentialId;
}