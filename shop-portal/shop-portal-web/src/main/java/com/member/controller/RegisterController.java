package com.member.controller;

import com.alibaba.fastjson.JSONObject;
import com.common.base.BaseResponse;
import com.common.web.base.BaseWebController;
import com.common.web.bean.MeiteBeanUtils;
import com.common.web.utils.RandomValidateCodeUtil;
import com.member.controller.req.vo.RegisterVo;
import com.member.feign.MemberRegisterServiceFeign;
import com.member.input.dto.UserInpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class RegisterController extends BaseWebController {
	private static final String MEMBER_REGISTER_PAGE = "register";
	@Autowired
	private MemberRegisterServiceFeign memberRegisterServiceFeign;

	/**
	 * 跳转到注册页面
	 *
	 * @return
	 */
	@GetMapping("/register.html")
	public String getRegister() {
		return MEMBER_REGISTER_PAGE;
	}

	/**
	 * 跳转到注册页面
	 *
	 * @return
	 */
	@PostMapping("/register.html")
	public String postRegister(@ModelAttribute("registerVo") @Validated RegisterVo registerVo, BindingResult bindingResult
			, Model model, HttpSession httpSession) {
		if (bindingResult.hasErrors()){
			// 如果参数有错误的话
			// 获取第一个错误!
			String errorMsg = bindingResult.getFieldError().getDefaultMessage();
			setErrorMsg(model,errorMsg);
			return "register";
		}


		// 建议不要if lese 判断 嵌套判断统一return
		// 2.判断图形验证码是否正确
		String graphicCode = registerVo.getGraphicCode();
		Boolean checkVerify = RandomValidateCodeUtil.checkVerify(graphicCode, httpSession);
		if (!checkVerify) {
			setErrorMsg(model, "图形验证码不正确!");
			return "register";
		}


		// 3.调用会员服务接口实现注册 将前端提交vo 转换dto
		UserInpDTO userInpDTO = MeiteBeanUtils.voToDto(registerVo, UserInpDTO.class);
		BaseResponse<JSONObject> register = memberRegisterServiceFeign.register(userInpDTO, registerVo.getRegistCode());
		if (!isSuccess(register)) {
			setErrorMsg(model, register.getMsg());
			return "register";
		}
		return  "login";
	}

}
