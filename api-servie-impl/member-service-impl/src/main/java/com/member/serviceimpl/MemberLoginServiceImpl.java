package com.member.serviceimpl;

import com.common.base.BaseApiService;
import com.common.base.BaseResponse;
import com.common.constants.Constants;
import com.common.coreUtils.MD5Util;
import com.common.token.GenerateToken;
import com.common.transaction.RedisDataSoureceTransaction;
import com.member.input.dto.UserLoginInpDTO;
import com.member.mapper.UserMapper;
import com.member.mapper.UserTokenMapper;
import com.member.mapper.entity.UserDo;
import com.member.mapper.entity.UserTokenDo;
import com.member.service.MemberLoginService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;


@RestController
public class MemberLoginServiceImpl extends BaseApiService<JSONObject> implements MemberLoginService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private GenerateToken generateToken;
	@Autowired
	private UserTokenMapper userTokenMapper;

	@Autowired
	private RedisDataSoureceTransaction redisDataSoureceTransaction;
	@Override
	public BaseResponse<JSONObject> login(@RequestBody UserLoginInpDTO userLoginInpDTO) {
		// 1.验证参数
		String mobile = userLoginInpDTO.getMobile();
		if (StringUtils.isEmpty(mobile)) {
			return setResultError("手机号码不能为空!");
		}
		String password = userLoginInpDTO.getPassword();
		if (StringUtils.isEmpty(password)) {
			return setResultError("密码不能为空!");
		}
		// 判断登陆类型
		String loginType = userLoginInpDTO.getLoginType();
		if (StringUtils.isEmpty(loginType)) {
			return setResultError("登陆类型不能为空!");
		}
		// 目的是限制范围
		if (!(loginType.equals(Constants.MEMBER_LOGIN_TYPE_ANDROID) || loginType.equals(Constants.MEMBER_LOGIN_TYPE_IOS)
				|| loginType.equals(Constants.MEMBER_LOGIN_TYPE_PC))) {
			return setResultError("登陆类型出现错误!");
		}
		TransactionStatus transactionStatus=null;

		try {
		// 设备信息
		String deviceInfor = userLoginInpDTO.getDeviceInfor();
		/*if (StringUtils.isEmpty(deviceInfor)) {
			return setResultError("设备信息不能为空!");
		}*/


		// 2.对登陆密码实现加密
		String newPassWord = MD5Util.MD5(password);
		// 3.使用手机号码+密码查询数据库 ，判断用户是否存在
		UserDo userDo = userMapper.login(mobile, newPassWord);
		if (userDo == null) {
			return setResultError("用户名称或者密码错误!");
		}
		// 用户登陆Token Session 区别
		// 用户每一个端登陆成功之后，会对应生成一个token令牌（临时且唯一）存放在redis中作为rediskey value userid
		// 4.获取userid
		Long userId = userDo.getUserId();
		// 5.根据userId+loginType 查询当前登陆类型账号之前是否有登陆过，如果登陆过 清除之前redistoken
		UserTokenDo userTokenDo = userTokenMapper.selectByUserIdAndLoginType(userId, loginType);
			transactionStatus = redisDataSoureceTransaction.begin();
			if (userTokenDo != null) {
				// 如果登陆过 清除之前redistoken
				String token = userTokenDo.getToken();
				generateToken.removeToken(token);
				int updateToken=userTokenMapper.updateTokenAvailability(token);
				if (!toDaoResult(updateToken)){
					redisDataSoureceTransaction.rollback(transactionStatus);
				}
			}

			// openid关联用户账号信息
			String qqOpenId = userLoginInpDTO.getQqOpenId();
			if (!StringUtils.isEmpty(qqOpenId)) {
				userMapper.updateUserOpenId(qqOpenId, userId);
			}

			// .生成对应用户令牌存放在redis中
			String keyPrefix = Constants.MEMBER_TOKEN_KEYPREFIX + loginType;
			String  newToken = generateToken.createToken(keyPrefix, userId + "");
			// 1.插入新的token
			UserTokenDo userToken = new UserTokenDo();
			userToken.setUserId(userId);
			userToken.setLoginType(userLoginInpDTO.getLoginType());
			userToken.setToken(newToken);
			//userToken.setDeviceInfor(deviceInfor);
			int insertResultToken=userTokenMapper.insertUserToken(userToken);

			if (!toDaoResult(insertResultToken)){
            	redisDataSoureceTransaction.rollback(transactionStatus);
            	return setResultSuccess("系统错误");
			}

		JSONObject data = new JSONObject();
		data.put("token", newToken);
		//提交事务
		redisDataSoureceTransaction.commit(transactionStatus);
		return setResultSuccess(data);
		}catch (Exception e){

			try {
				redisDataSoureceTransaction.rollback(transactionStatus);
				return setResultError("系统错误");
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			return setResultError("系统错误");
		}



	}

	@Override
	public BaseResponse<JSONObject> loginOut(String token) {

		TransactionStatus transactionStatus=null;
		try{
			transactionStatus = redisDataSoureceTransaction.begin();
			generateToken.removeToken(token);
			userTokenMapper.updateTokenAvailability(token);
			redisDataSoureceTransaction.commit(transactionStatus);
			return setResultSuccess();
		}catch (Exception e){
			try {
				redisDataSoureceTransaction.rollback(transactionStatus);
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			return setResultError("系统异常");
		}

	}


}
