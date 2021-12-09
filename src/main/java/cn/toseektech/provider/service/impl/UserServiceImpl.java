package cn.toseektech.provider.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.toseektech.common.utils.CommonUtil;
import cn.toseektech.provider.dao.UserMapper;
import cn.toseektech.provider.dto.UserDto;
import cn.toseektech.provider.entity.User;
import cn.toseektech.provider.service.UserService;


@Service
public class UserServiceImpl implements UserService{
	
	@Resource
	private UserMapper userMapper;

	@Override
	public UserDto getUserInfo(Long id) {
		User user=userMapper.selectByPrimaryKey(id);
		return CommonUtil.copy(user, UserDto.class);
	}

	@Override
	public List<UserDto> getUserList() {
		List<User> list = userMapper.queryAll();
		return CommonUtil.listCopy(list, UserDto.class);
	}

}
