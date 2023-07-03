package com.cos.photogramstart.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.web.dto.subscribe.SubscribeDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SubscribeService {

	private final SubscribeRepository subscribeRepository;
	private final EntityManager em; //REpository는 EntityManager를 구현해서 만들어져 있는 구현체
	@Transactional(readOnly = true)
	public List<SubscribeDto> 구독리스트(Integer principalId,Integer pageUserId){
		
		StringBuffer sb = new StringBuffer();
		    sb.append("SELECT u.id, u.username, u.profileImageUrl, ");
	        sb.append("CASE WHEN EXISTS (SELECT 1 FROM subscribe WHERE fromUserId = ? AND toUserId = u.id) THEN 1 ELSE 0 END AS subscribeState, ");
	        sb.append("CASE WHEN ? = u.id THEN 1 ELSE 0 END AS equalUserState ");
	        sb.append("FROM users u INNER JOIN subscribe s ON u.id = s.toUserId ");
	        sb.append("WHERE s.fromUserId = ? ");//세미콜론 첨부하면 안됨

	        //1.물음표 principalled
	        //2.물음표 principalled
	        //3.마지막 물음표 PageUserId
	        Query query = em.createNativeQuery(sb.toString())
	        		.setParameter(1, principalId)
	        		.setParameter(2, principalId)
	        		.setParameter(3, pageUserId);
	        
	        //쿼리실행(qlrm 라이브러리 필요 = DTO
	        JpaResultMapper result = new JpaResultMapper();
	        List<SubscribeDto> subscribeDtos = result.list(query, SubscribeDto.class);
		
	        return subscribeDtos;
	};
	
	@Transactional
	  public void 구독하기(Integer fromUserId, Integer toUserId) {
		  try {
			  subscribeRepository.mSubscribe(fromUserId, toUserId);
		  }catch(Exception e) {
			  throw new CustomApiException("이미 구독을 하였습니다.");
		  }
	  }
	
	@Transactional
     public void 구독취소하기(Integer fromUserId, Integer toUserId) {
		
		 subscribeRepository.mUnSubscribe(fromUserId, toUserId);
		  

	  }
}
