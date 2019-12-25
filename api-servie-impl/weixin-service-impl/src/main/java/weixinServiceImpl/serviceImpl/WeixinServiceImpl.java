package weixinServiceImpl.serviceImpl;

import com.common.base.BaseApiService;
import com.common.base.BaseResponse;
import entity.AppEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import service.weixin.WeixinService;

@RestController
public class WeixinServiceImpl extends BaseApiService implements WeixinService {

    public BaseResponse<AppEntity> getApp() {
        return setResultSuccess(new AppEntity("1","微信"));
    }

}
