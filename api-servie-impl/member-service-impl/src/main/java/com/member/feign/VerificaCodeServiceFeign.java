package com.member.feign;

import org.springframework.cloud.openfeign.FeignClient;
import service.weixin.VerificaCodeService;

@FeignClient(name="app-mayikt-weixin")
public interface VerificaCodeServiceFeign extends VerificaCodeService {

}
