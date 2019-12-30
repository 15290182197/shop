package com.member.serviceimpl;

import com.alibaba.fastjson.JSONObject;
import com.common.base.BaseApiService;
import com.common.base.BaseResponse;
import com.common.bean.DtoBeanUtils;
import com.common.constants.Constants;
import com.common.coreUtils.MD5Util;

import com.member.feign.VerificaCodeServiceFeign;
import com.member.input.dto.UserInpDTO;
import com.member.mapper.UserMapper;
import com.member.mapper.entity.UserDo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.member.service.MemberRegisterService;

@RestController
public class MemberRegisterServiceImpl extends BaseApiService<JSONObject> implements MemberRegisterService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private VerificaCodeServiceFeign verificaCodeServiceFeign;

	@Override
	public BaseResponse<JSONObject> register(@RequestBody UserInpDTO userInpDTO,@RequestParam String registCode) {
		// 1.验证参数
		String userName = userInpDTO.getUserName();

		String mobile = userInpDTO.getMobile();
		if (StringUtils.isEmpty(mobile)) {
			return setResultError("手机号码不能为空!");
		}
		String password = userInpDTO.getPassword();
		if (StringUtils.isEmpty(password)) {
			return setResultError("密码不能为空!");
		}
		String newPassWord = MD5Util.MD5(password);
		// 将密码采用MD5加密
		userInpDTO.setPassword(newPassWord);
		// 调用微信接口,验证注册码是否正确

		BaseResponse<JSONObject> resultVerificaWeixinCode = verificaCodeServiceFeign.verificaWeixinCode(mobile, registCode);

		if (!resultVerificaWeixinCode.getRtnCode().equals(Constants.HTTP_RES_CODE_200)) {
			return setResultError(resultVerificaWeixinCode.getMsg());
		}
		UserDo user= DtoBeanUtils.dtoToDo(userInpDTO,UserDo.class);
		int registerResult = userMapper.register(user);
		return registerResult > 0 ? setResultSuccess("注册成功") : setResultSuccess("注册失败");

	}

}
