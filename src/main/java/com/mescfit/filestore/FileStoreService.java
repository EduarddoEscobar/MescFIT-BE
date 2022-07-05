package com.mescfit.filestore;

import com.mescfit.buckets.BucketName;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FileStoreService {

    private final FileStore fileStore;

    public String uploadFile(BucketName bucket, UUID id, MultipartFile file) {
        isFileEmpty(file);
        isImageOrVideo(file);

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        String path = String.format("%s/%s", bucket.getBucketName(), id);
        String fileName = String.format("%s-%s", file.getOriginalFilename(), id);

        try{
            fileStore.save(path, fileName, Optional.of(metadata), file.getInputStream());
            return fileName;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public byte[] downloadFile(BucketName bucket,
                               UUID id,
                               String key) {
        String path = String.format("%s%s",
                bucket.getBucketName(),
                id);
        return fileStore.download(path, key);
    }

    private void isImageOrVideo(MultipartFile file) {
        if(!file.getContentType().contains("image") || !file.getContentType().contains("video"))
            throw new IllegalStateException("File must be an image or video");
    }

    private void isFileEmpty(MultipartFile file) {
        if(file.isEmpty()) {
            throw new IllegalStateException("File is empty");
        }
    }
}
