package com.cos.photogramstart.domain.likes;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.Users;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder 
@AllArgsConstructor 
@NoArgsConstructor 
@Data 
@Entity 
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "likes_uk", // Unique 제약조건 이름
                columnNames = { // Unique 제약조건을 적용할 컬럼명,(중복이 될 수 없기 때문에)
                        "imageId",
                        "userId"
                })
})
public class Likes {
	 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id 
    private Integer id; 

    @ManyToOne
    @JoinColumn(name = "imageId")
    private Image image; // 좋아요가 된 것이 어떤 이미지인지.

    @ManyToOne
    @JsonIgnoreProperties({"images"}) // 무한참조오류잡기
    @JoinColumn(name = "userId")
    private Users user; // 좋아요를 누가 한 것인지.

    private LocalDateTime createDate; 

    @PrePersist
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
