package weixinServiceImpl.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.common.base.BaseApiService;
import com.common.base.BaseResponse;
import com.common.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import service.weixin.VerificaCodeService;
import weixinServiceImpl.mp.utils.RedisUtil;

@RestController
public class VerificaCodeServiceImpl extends BaseApiService implements VerificaCodeService {
    @Autowired
    private RedisUtil redisUtil;
    @Override
    public BaseResponse<JSONObject> verificaWeixinCode(String phone, String weixinCode) {
       //验证参数是否为
        if(StringUtils.isEmpty(phone)){
            return setResultError("参数为空");
        }
        // 比对验证码
        if(StringUtils.isEmpty(weixinCode)){
            return setResultError("验证码不能为空");
        }
        String redisCode=redisUtil.getString(Constants.WEIXINCODE_KEY+phone);
        if(!weixinCode.equals(weixinCode)){
            return setResultError("验证码错误，或者已失效");
        }else {
            redisUtil.delKey(redisCode);
            return setResultSuccess();
        }




    }
}
