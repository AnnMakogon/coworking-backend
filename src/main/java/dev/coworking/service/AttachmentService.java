package dev.coworking.service;

import dev.coworking.entity.AttachmentEntity;
import dev.coworking.entity.WorkspaceEntity;
import dev.coworking.repository.AttachmentRepository;
import dev.coworking.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AttachmentService {

    private final MinioService minioService;
    private final WorkspaceRepository workspaceRepository;
    private final AttachmentRepository attachmentRepository;

    // создание/сохранение
    public AttachmentEntity createAttachment(Long workspaceId, MultipartFile file) throws Exception {
        WorkspaceEntity workspace = workspaceRepository.findById(workspaceId).orElseThrow(() -> new RuntimeException("Workspace not found"));

        // загрузка в минио
        String fileName = file.getOriginalFilename();
        minioService.uploadImage(fileName, file.getInputStream(), file.getSize());

        AttachmentEntity attachment = new AttachmentEntity();
        attachment.setFileName(fileName);
        attachment.setFileType(file.getContentType());

        //todo application.yml
        attachment.setFilePath("http://minio:9000/" + fileName);

        workspace.getAttachments().add(attachment);

        workspaceRepository.save(workspace);

        return attachment;
    }

    // получение по id
    public AttachmentEntity getAttachmentById(Long attachmentId) {
        return attachmentRepository.findById(attachmentId)
                .orElseThrow(() -> new RuntimeException("Attachment not found"));
    }

    //получение всех по id Workspace
    public List<AttachmentEntity> getAllAttachmentsForWorkspace(Long workspaceId) {
        WorkspaceEntity workspace = workspaceRepository.findById(workspaceId).orElseThrow(() -> new RuntimeException("Workspace not found"));
        return workspace.getAttachments();
    }

    //обновление
    public AttachmentEntity updateAttachment(Long attachmentId, MultipartFile file) throws Exception {
        AttachmentEntity attachment = attachmentRepository.findById(attachmentId).orElseThrow(() -> new RuntimeException("Attachment not found"));

        //действия с минио
        minioService.deleteImage(attachment.getFileName());

        String newFileName = file.getOriginalFilename();
        minioService.uploadImage(newFileName, file.getInputStream(), file.getSize());

        attachment.setFileName(newFileName);   //todo переделать через sql запрос в бд на апдейт
        attachment.setFileType(file.getContentType());
        attachment.setFilePath("http://minio:9000/" + newFileName);

        return attachmentRepository.save(attachment);
    }

    public void deleteAttachment(Long attachmentId) throws Exception {
        AttachmentEntity attachment = attachmentRepository.findById(attachmentId).orElseThrow(() -> new RuntimeException("Attachment ot found"));

        minioService.deleteImage(attachment.getFileName());

        attachmentRepository.delete(attachment);
    }




}
