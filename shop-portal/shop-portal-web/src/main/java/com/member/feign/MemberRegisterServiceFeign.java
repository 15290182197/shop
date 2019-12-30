package com.member.feign;

import com.member.service.MemberRegisterService;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient("app-mayikt-member")
public interface MemberRegisterServiceFeign extends MemberRegisterService {

}
