package com.cos.photogramstart.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SubscribeService {

	private final SubscribeRepository subscribeRepository;
	
	@Transactional
	  public void 구독하기(Integer fromUserId, Integer toUserid) {
		  subscribeRepository.mSubscribe(fromUserId, toUserid);
		  
	  }
	
	@Transactional
     public void 구독취소하기(Integer fromUserId, Integer toUserid) {
		 subscribeRepository.mUnSubscribe(fromUserId, toUserid);
		  

	  }
}
