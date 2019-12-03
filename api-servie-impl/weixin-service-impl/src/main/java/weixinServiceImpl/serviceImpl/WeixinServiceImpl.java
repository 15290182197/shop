package weixinServiceImpl.serviceImpl;

import entity.AppEntity;
import org.springframework.web.bind.annotation.RestController;
import service.weixin.WeixinService;

@RestController
public class WeixinServiceImpl implements WeixinService {

    public AppEntity getApp() {
        return new AppEntity("1234","微信服务");
    }
}
