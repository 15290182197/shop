package springcloudZuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.protocol.RequestContent;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
@Component
public class TokenFilter  extends ZuulFilter {

    //过滤器类型
    //pre :请求之前执行
    @Override
    public String filterType() {
        return "pre";
    }

    //过滤器执行顺序  多个过滤器
    @Override
    public int filterOrder() {
        return 0;
    }

    //过滤器是否生效 true
    @Override
    public boolean shouldFilter() {
        return true;
    }

    //拦截逻辑
    @Override
    public Object run() throws ZuulException {
        //拦截所有接口 判断是否token
        RequestContext context= RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String userToken = request.getParameter("userToken");
        if(StringUtils.isEmpty(userToken)){
            System.err.println("到达网关");
//            context.setSendZuulResponse(false);
//            context.setResponseBody("userToken is null");
//            context.setResponseStatusCode(401);
    //        return null;
        }

        return null;
    }
}
