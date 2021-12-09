package cn.toseektech.provider.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cn.toseektech.common.configuration.annotations.GlobalExceptionHandler;
import cn.toseektech.common.model.ResponseVO;
import cn.toseektech.provider.dto.UserDto;
import cn.toseektech.provider.service.UserService;

@RestController
@GlobalExceptionHandler
public class UserInfoController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Resource
	private UserService userService;

	@GetMapping("/getUserInfo/{id}")
	public ResponseVO<UserDto> getUserInfo(@PathVariable("id") Long id){

		logger.info("查询的User的id为：{}", id);
		UserDto user = userService.getUserInfo(id);
		ResponseVO<UserDto> responseDto = new ResponseVO<>();
		responseDto.setContent(user);
        return responseDto;
	}
}
