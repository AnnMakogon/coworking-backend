package dev.coworking.service;

import io.minio.*;
import io.minio.errors.MinioException;
import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
@Slf4j
public class MinioService {

    private MinioClient minioClient;
    private final String bucketName;

    private final String minioUrl;
    private final String accessKey;
    private final String secretKey;

    @Autowired
    public MinioService(@Value("${minio.url}") String minioUrl,
                        @Value("${minio.access-key}") String accessKey,
                        @Value("${minio.secret-key}") String secretKey,
                        @Value("${minio.bucket.name}") String bucketName) {
        /*try{
        this.minioClient = MinioClient.builder()
                .endpoint(minioUrl)
                .credentials(accessKey, secretKey)
                .build();
        this.bucketName = bucketName;

         // создание бакета (раздела), если его не существует
        createBucket();
        }catch (Exception e){
            log.error("Error initializing MinioService", e);
            throw new RuntimeException("Failed to initialize MinioService", e);
        }*/
        this.bucketName = bucketName;
        this.minioUrl = minioUrl;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }
    @PostConstruct
    public void init() {
        try {
            this.minioClient = MinioClient.builder()
                    .endpoint(minioUrl)
                    .credentials(accessKey, secretKey)
                    .build();
            log.info("MinioClient initialized successfully.");
            createBucket();
        } catch (Exception e) {
            log.error("Error initializing MinioService", e);
            throw new RuntimeException("Failed to initialize MinioService", e);
        }
    }

    private void createBucket() {
        try {
            boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!exists) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
        } catch (MinioException e) {
            e.printStackTrace();
        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }


    // загрузка
    public void uploadImage(String objectName, InputStream inputStream, Long size) throws Exception {
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .stream(inputStream, size, -1)
                        .build());
    }

    //получение
    public InputStream downloadImage(String objectName) throws  Exception {
        return minioClient.getObject(GetObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build());
    }

    //удаление
    public void deleteImage(String objectName) throws Exception {
        minioClient.removeObject(RemoveObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build());
    }
}

