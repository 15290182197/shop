package com.member.feign;

import com.member.service.MemberService;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient("app-mayikt-member")
public interface MemberServiceFeign extends MemberService {

}
