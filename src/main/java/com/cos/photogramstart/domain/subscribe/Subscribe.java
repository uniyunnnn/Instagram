package com.cos.photogramstart.domain.subscribe;

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

import com.cos.photogramstart.domain.user.Users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(uniqueConstraints = { @UniqueConstraint(name = "subscribe_uk", columnNames = { "fromUserId","toUserId" }) })
public class Subscribe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JoinColumn(name = "fromUserId")//이렇게 컬럼명 만들어
	@ManyToOne
	private Users fromUser;

	@JoinColumn(name = "toUserId")//이렇게 컬럼명 만들어
	@ManyToOne
	private Users toUser;

	private LocalDateTime createDate; // 데이터가 입력된 시간.

	@PrePersist
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
}
