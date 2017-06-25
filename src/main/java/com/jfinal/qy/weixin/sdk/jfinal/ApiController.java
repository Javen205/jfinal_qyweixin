package com.jfinal.qy.weixin.sdk.jfinal;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * 所有使用 Api 的 controller 需要继承此类
 */
@Before(ApiInterceptor.class)
public abstract class ApiController extends Controller {

}
