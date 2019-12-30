package com.member.controller;

import com.alibaba.fastjson.JSONObject;
import com.common.base.BaseResponse;
import com.common.constants.Constants;
import com.common.web.base.BaseWebController;
import com.common.web.bean.MeiteBeanUtils;
import com.common.web.utils.CookieUtils;
import com.common.web.utils.RandomValidateCodeUtil;
import com.member.controller.req.vo.LoginVo;
import com.member.feign.MemberLoginServiceFeign;
import com.member.input.dto.UserLoginInpDTO;
import com.member.output.dto.UserOutDTO;
import com.web.constants.WebConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController extends BaseWebController {
    @Autowired
    private MemberLoginServiceFeign memberLoginServiceFeign;

    private static final String LOGIN_FTL="login";
    @GetMapping("/login.html")
    public String getLogin(){
        System.err.println("login");

        return "login";
    }


    @PostMapping("/login")
    public String postLogin(@ModelAttribute("loginVo") LoginVo loginVo, Model model, HttpServletRequest request,
                            HttpServletResponse response, HttpSession httpSession){
        // 1.图形验证码判断
        String graphicCode = loginVo.getGraphicCode();
        if (!RandomValidateCodeUtil.checkVerify(graphicCode, httpSession)) {
            setErrorMsg(model, "图形验证码不正确!");
            return "login";
        }

        // 2.将vo转换dto调用会员登陆接口
        UserLoginInpDTO userLoginInpDTO = MeiteBeanUtils.voToDto(loginVo, UserLoginInpDTO.class);
        userLoginInpDTO.setLoginType(Constants.MEMBER_LOGIN_TYPE_PC);
        String info = webBrowserInfo(request);
        //userLoginInpDTO.setDeviceInfor(info);
        BaseResponse<JSONObject> login = memberLoginServiceFeign.login(userLoginInpDTO);
        if (!isSuccess(login)) {
            setErrorMsg(model, login.getMsg());
            return "login";
        }

        JSONObject data = login.getData();
        String token = data.getString("token");
        CookieUtils.setCookie(request, response, WebConstants.LOGIN_TOKEN_COOKIENAME, token);


        return "redirect:/";

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        String token = CookieUtils.getCookieValue(request, WebConstants.LOGIN_TOKEN_COOKIENAME, true);
        BaseResponse<JSONObject> result=null;
        if (!StringUtils.isEmpty(token)) {
          result  =   memberLoginServiceFeign.loginOut(token);
        }
     return "index";

     //   return isSuccess(result);
    }

}
