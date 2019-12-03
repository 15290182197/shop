package member.serviceimpl;

import entity.AppEntity;
import io.swagger.annotations.Api;
import member.feign.WinxinServcieFetgn;
import member.service.MemberService;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class MemberServiceImpl implements MemberService {
    private WinxinServcieFetgn winxinServcieFetgn;

    @Override
    public AppEntity memberToWeixin() {

        return winxinServcieFetgn.getApp();
    }
}
