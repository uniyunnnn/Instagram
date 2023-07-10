package com.cos.photogramstart.domain.comment;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comments, Integer>{
	
	@Modifying
    @Query(value = "INSERT INTO comments(content, imageId, userId, createDate) VALUES(:content, :imageId, :userId,SYSDATE)", nativeQuery = true)
    Comments save(String content, Integer imageId, Integer userId);
}