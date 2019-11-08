package com.glacier.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-10-30 15:06
 */
@Component
@Slf4j
public class MyPreFilter extends ZuulFilter {
    /**
     * 定义filter的类型，有pre、route、post、error四种
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 定义filter的顺序，数字越小表示顺序越高，越先执行
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 表示是否需要执行该filter，true表示执行，false表示不执行
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * filter需要执行的具体操作
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//        String token = request.getParameter("token");
//        System.out.println(token);
//        if (token == null) {
//            log.warn("there is no request token");
//            ctx.setSendZuulResponse(false);
//            ctx.setResponseStatusCode(401);
//            try {
//                ctx.getResponse().getWriter().write("there is no request token");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//        log.info("ok");
        return null;
    }
}
