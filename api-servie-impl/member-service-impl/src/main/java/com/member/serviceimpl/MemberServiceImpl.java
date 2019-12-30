package com.member.serviceimpl;

import com.common.base.BaseApiService;
import com.common.base.BaseResponse;
import com.common.bean.DtoBeanUtils;
import com.common.constants.Constants;
import com.common.token.GenerateToken;
import com.common.type.TypeCastHelper;
import com.member.mapper.UserMapper;
import com.member.mapper.entity.UserDo;
import com.member.output.dto.UserOutDTO;
import com.member.service.MemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberServiceImpl extends BaseApiService implements MemberService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GenerateToken generateToken;

    @Override
    public BaseResponse<UserOutDTO> existMobile(String mobile) {

        // 1.验证参数
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号码不能为空!");
        }
        UserDo userEntity = userMapper.existMobile(mobile);
        if (userEntity == null) {
            return setResultError(Constants.HTTP_RES_CODE_EXISTMOBILE_202, "用户不存在");
        }
        // 注意需要将敏感数据进行脱敏
        userEntity.setPassword(null);
        UserOutDTO userOutDTO= DtoBeanUtils.doToDto(userEntity,UserOutDTO.class);
        return setResultSuccess(userOutDTO);
    }

    @Override
    public BaseResponse<UserOutDTO> getInfo(String token) {
        // 验证参数
        if(StringUtils.isEmpty(token)){
            return setResultError("token不能为空");
        }
        // 用token从redis取得userId
         String  redisUserId  = generateToken.getToken(token);
         if (StringUtils.isEmpty(redisUserId)){
             return setResultError("token已经失效，或者token错误");
         }
         //从user表查询信息
         Long userId =TypeCastHelper.toLong(redisUserId);
         UserDo userDo=userMapper.findByUserId(userId);
         if (userDo==null){
             return setResultError("用户不存在");
         }

         return setResultSuccess(DtoBeanUtils.doToDto(userDo,UserOutDTO.class));

    }


}
