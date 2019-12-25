package member.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import service.weixin.WeixinService;

@FeignClient(name="app-mayikt-weixin")
public interface WinxinServcieFetgn extends WeixinService {


}

