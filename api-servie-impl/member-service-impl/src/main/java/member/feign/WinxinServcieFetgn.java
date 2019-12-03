package member.feign;


import org.springframework.cloud.openfeign.FeignClient;
import service.weixin.WeixinService;

@FeignClient("app-mayikt-weixin")
public interface WinxinServcieFetgn extends WeixinService {


}

