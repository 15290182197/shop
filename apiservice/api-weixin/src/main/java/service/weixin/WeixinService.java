package service.weixin;

import entity.AppEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
@Api(tags = "会员服务")
public interface WeixinService {
    @ApiOperation("查询微信app")
    @GetMapping("/getApp")
    public AppEntity getApp();
}
