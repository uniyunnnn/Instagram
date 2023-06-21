package com.cos.photogramstart.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {


	@Modifying
	@Query(value = "INSERT INTO subscribe(fromUserId, toUserId, createDate) VALUES(:fromUserId, :toUserId, SYSDATE)", nativeQuery = true)
	void mSubscribe(Integer fromUserId, Integer toUserId);

	@Modifying
	@Query(value = "DELETE FROM subscribe WHERE fromUserId = :fromUserId AND toUserId = :toUserId", nativeQuery = true)
	void mUnSubscribe(Integer fromUserId, Integer toUserId);


}