package member.serviceimpl;

import entity.AppEntity;
import io.swagger.annotations.Api;
import member.feign.WinxinServcieFetgn;
import member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberServiceImpl implements MemberService {
    @Autowired
    private WinxinServcieFetgn winxinServcieFetgn;

    @GetMapping("/memberToWeixin")
    public AppEntity memberToWeixin() {
        System.err.println("memberToWeixin");

        return winxinServcieFetgn.getApp();
    }

    @Override
    public String Weixin() {
        return "我是会员服务";
    }


}
