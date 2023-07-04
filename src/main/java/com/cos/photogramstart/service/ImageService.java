package com.cos.photogramstart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class ImageService {
    
    private final ImageRepository imageRepository;
    
    @Transactional(readOnly = true) // 영속성 컨텍스트 변경 감지를 해서 더티체킹, flush(반영)
    public List<Image> 이미지스토리(Integer principalId, Pageable pageable) {
        List<Image> images = imageRepository.mStory(principalId,pageable);
        return images;
    }
    
    @Value("${file.path}")
    private String uploadFolder;

    @Transactional 
    public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
        
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" + imageUploadDto.getFile().getOriginalFilename();
        log.info(imageFileName);
        
        Path imageFilePath = Paths.get(uploadFolder+imageFileName);
        
        // 파일 업로드하기: 통신, I/O 예외 발생잡기
        try {
            Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // image 파일경로를 DB에 INSERT하기
        Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser());
        imageRepository.save(image);
        
        //log.info("imageEntity : {}",imageEntity);
        
    }
}