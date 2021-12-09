package cn.toseektech.provider.service;

import java.util.List;

import cn.toseektech.provider.dto.UserDto;

public interface UserService {
	
	/**
	 * id查询User
	 * @param id
	 * @return
	 */
	UserDto getUserInfo(Long id);
	
	/**
	 * 查询所有User列表
	 * @return
	 */
	List<UserDto> getUserList();
}
