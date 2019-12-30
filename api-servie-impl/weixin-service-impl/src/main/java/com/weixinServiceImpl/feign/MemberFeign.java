package com.weixinServiceImpl.feign;

import com.member.service.MemberService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="app-mayikt-member")
public interface MemberFeign extends MemberService {
}
