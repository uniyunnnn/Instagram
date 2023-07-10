package com.cos.photogramstart.domain.image;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

import com.cos.photogramstart.domain.comment.Comments;
import com.cos.photogramstart.domain.likes.Likes;
import com.cos.photogramstart.domain.user.Users;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Image {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    private String caption;
    private String postImageUrl; 
    
    @JsonIgnoreProperties({"images"})
    @JoinColumn(name = "userId")
    @ManyToOne
    private Users user;

    //이미지 좋아요
    @JsonIgnoreProperties({"image"})
    @OneToMany(mappedBy="image")
    private List<Likes>likes;
    
    //댓글
    @OrderBy("id ASC")
    @JsonIgnoreProperties({"image"})
    @OneToMany(mappedBy="image")
    private List<Comments>comments;
    
    @Transient // DB에 해당 컬럼을 생성하지 않게 만드는 어노테이션
    private boolean likeState;
    
    @Transient
    private Integer likeCount; // 이미지 좋아요 카운팅
    
    // 댓글 정보
    
    private LocalDateTime createDate;

    @PrePersist
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

    // 오브젝트를 콘솔에 출력할때 문제가 될 수 있어서 Users부분을 출력되지 않게 함.
//	@Override
//	public String toString() {
//		return "Image [id=" + id + ", caption=" + caption + ", postImageUrl=" + postImageUrl 
//				+ ", createDate=" + createDate + "]";
//	}
	
}
