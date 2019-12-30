package com.member.feign;

import com.member.service.QQAuthoriService;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient("app-mayikt-member")
public interface QQAuthoriFeign extends QQAuthoriService {

}
