package com.member.feign;

import com.member.service.MemberLoginService;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient("app-mayikt-member")
public interface MemberLoginServiceFeign extends MemberLoginService {

}
