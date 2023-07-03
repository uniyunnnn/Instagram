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

	// 구독상태 확인 네이티브 쿼리
    @Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId = :principalId AND toUserId = :pageUserId", nativeQuery = true)
    Integer mSubscribeState(Integer principalId, Integer pageUserId);

    // 구독자수 확인 네이티브 쿼리
    @Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId = :pageUserId", nativeQuery = true)
    Integer mSubscribeCount(Integer pageUserId);

}