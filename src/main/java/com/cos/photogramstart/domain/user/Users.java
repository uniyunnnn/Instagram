package com.cos.photogramstart.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor // 모든 생성자를 자동으로 만들어주는 어노테이션
@NoArgsConstructor // 빈 생성자를 자동으로 만들어주는 어노테이션
@Data // 자동으로 Getter, Setter, toString을 만들어주는 어노테이션
@Entity // DB에 테이블을 생성해주는 어노테이션
@Builder
public class Users {

	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 DB설정값(MySQL/Oracle)으로 따라간다.
	@Id // Primary Key를 지정해주는 어노테이션
	private Integer id; // 데이터가 들어갈 때 마다 번호를 매겨줄것임.

	@Column(length = 100, unique = true)
	private String username; // 아이디
	@Column(nullable = false)
	private String password; // 비밀번호
	@Column(nullable = false)
	private String name; // 별명

	private String website; // 개인 웹사이트 주소

	private String bio; // 자기소개

	@Column(nullable = false)
	private String email; // 이메일

	private String phone; // 전화번호

	private String gender; // 성별

	private String profileImageUrl; // 프로필 사진

	private String role; // 권한

	// 나는 연관관계의 주인이 아니다. 그러므로 테이블에 칼럼을 만들지마라
	// User를 Select할 때 해당 User id로 등록된 image들을 다 가져와
	// Lazy 일때는 User를 Selcet할때 해당 User id로 등록된 Image들을 가져오지마!! -> 대신 getImages() 함수가
	// 호출 될때~
	// Eager = User를 Select할 때 해당 User id로 등록된 image들을 전부 조인해서 가져와
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"user"})
	private List<Image> images;

	private LocalDateTime createDate; // 데이터가 입력된 시간.

	@PrePersist
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", website="
				+ website + ", bio=" + bio + ", email=" + email + ", phone=" + phone + ", gender=" + gender
				+ ", profileImageUrl=" + profileImageUrl + ", role=" + role + ", createDate="
				+ createDate + "]";
	}

}