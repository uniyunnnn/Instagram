package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.Users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileDto {
	
	    private boolean pageOwnerState; 
	    
	    private Integer imageCount;
	    
	    private Users user; 
}
