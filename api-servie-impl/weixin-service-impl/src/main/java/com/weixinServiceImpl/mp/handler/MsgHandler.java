package com.weixinServiceImpl.mp.handler;

import com.common.base.BaseResponse;
import com.common.constants.Constants;

import com.common.coreUtils.RegexUtils;
import com.member.output.dto.UserOutDTO;
import com.common.utils.RedisUtil;
import com.weixinServiceImpl.feign.MemberFeign;
import com.weixinServiceImpl.mp.builder.TextBuilder;
import me.chanjar.weixin.common.api.WxConsts.XmlMsgType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.util.Map;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
@SuppressWarnings("static-access")
public class MsgHandler extends AbstractHandler {

	@Value("${mayikt.weixin.registration.code.message}")
	private String registrationCodeMessage;
	/**
	 * 默认回复消息
	 */
	@Value("${mayikt.weixin.default.registration.code.message}")
	private String defaultRegistrationCodeMessage;

	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	private MemberFeign memberFeign;

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService weixinService,
			WxSessionManager sessionManager) {

		if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
			// TODO 可以选择将消息保存到本地
		}

		// 当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
		try {
			if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服")
					&& weixinService.getKefuService().kfOnlineList().getKfOnlineList().size() > 0) {
				return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE().fromUser(wxMessage.getToUser())
						.toUser(wxMessage.getFromUser()).build();
			}
		} catch (WxErrorException e) {
			e.printStackTrace();
		}

		String content = wxMessage.getContent();
		if (RegexUtils.checkPhone(content)){
			//调用会员服务验证手机号是否已经被注册
			BaseResponse<UserOutDTO> userEntityBaseResponse = memberFeign.existMobile(content);
			//返回200 手机号已经被注册
			if (userEntityBaseResponse.getRtnCode().equals(Constants.HTTP_RES_CODE_200)){
				return new TextBuilder().build("手机号已经被注册", wxMessage, weixinService);
			}
			//  非202则返回报错信息
			if (!userEntityBaseResponse.getRtnCode().equals(Constants.HTTP_RES_CODE_EXISTMOBILE_202)){
				return new TextBuilder().build(userEntityBaseResponse.getMsg(), wxMessage, weixinService);
			}
			//返回202 则用户名不存在  则可以继续注册
			int registCode=registCode();
			// 返回给客户端的
		    String formatContent=String.format(registrationCodeMessage, registCode);
		    //存到redis
			redisUtil.setString(Constants.WEIXINCODE_KEY+content,registCode+"",Constants.WEIXINCODE_TIMEOUT);



			return new TextBuilder().build(formatContent, wxMessage, weixinService);

		}else {
			//或者调用第三方人机回答 内容 defaultRegistrationCodeMessage
			return new TextBuilder().build(defaultRegistrationCodeMessage, wxMessage, weixinService);

		}


	}

	// 获取注册码
	private int registCode() {
		int registCode = (int) (Math.random() * 9000 + 1000);
		return registCode;
	}

}
