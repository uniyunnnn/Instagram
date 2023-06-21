package com.cos.photogramstart.domain.image;

import java.awt.Image;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer>{
    
}