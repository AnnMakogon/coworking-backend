package dev.coworking.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "attachments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "att_seq", sequenceName = "att_seq", allocationSize = 1)
public class AttachmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "att_seq")
    private Long id;

    private String fileName;
    private String fileType;
    private String filePath;

    public AttachmentEntity(String fileName, String fileType, String filePath) {
        this.id = null;
        this.fileName = fileName;
        this.fileType = fileType;
        this.filePath = filePath;
    }
}
