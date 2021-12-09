package cn.toseektech.provider.remote;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.toseektech.common.utils.CommonUtil;
import cn.toseektech.provider.dto.UserDto;
import cn.toseektech.provider.service.UserService;
import cn.toseektech.remote.provider.api.UserRemote;
import cn.toseektech.remote.provider.dto.UserRemoteDto;

@RestController
@RequestMapping(path="/user")
public class UserRemoteImpl implements UserRemote {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private UserService userService;
     
	@GetMapping("/getUserList")
	public List<UserRemoteDto> getUserList() {
		logger.info("getUserList");
		List<UserDto> list = userService.getUserList();
	    //throw new BussinessException("没有查到用户");
		return  CommonUtil.listCopy(list, UserRemoteDto.class);
	}

}
