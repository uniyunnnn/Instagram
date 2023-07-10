package com.cos.photogramstart.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.comment.CommentRepository;
import com.cos.photogramstart.domain.comment.Comments;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.domain.user.Users;
import com.cos.photogramstart.handler.ex.CustomApiException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    
    @Transactional
    public Comments 댓글쓰기(String content, Integer imageId, Integer userId) {
    	
    	//Tip(객체를 만들 때 id값만 담아서 insert할 수 있다. )
    	//대신 return시에 image객체와 user객체는 id값만 가지고 잇는 빈 객체를 리턴받는다.
    	Image image = new Image();
        image.setId(imageId);
        
        Users userEntity = userRepository.findById(userId).orElseThrow(() -> {
            throw new CustomApiException("유저 아이디를 찾을 수 없습니다.");
        });
    	
    	
    	Comments comment = new Comments();
    	comment.setContent(content);
    	comment.setImage(image);
    	comment.setUser(userEntity);
    	
    	return commentRepository.save(comment);
    }

    @Transactional
    public void 댓글삭제(int id) {
    	try {
    		commentRepository.deleteById(id);
    	}catch(Exception e) {
    		throw new CustomApiException(e.getMessage());
    	}
    	
    }
    
}