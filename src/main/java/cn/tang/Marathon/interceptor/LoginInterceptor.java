/**
 * 
 */
package cn.tang.Marathon.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.tang.Marathon.pojo.User;
import net.sf.json.JSONObject;

/**
 * @author Tang
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		// System.out.println("进入拦截器，url="+uri);
		User user = (User) request.getSession().getAttribute("user");

		if (user == null) {
			System.out.println("未登录或登录失效，uri=" + uri);
			if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
				// ajax请求
				Map<String, String> ret = new HashMap<String, String>();

				ret.put("type", "error");
				ret.put("msg", "登录状态已失效，请重新登录");
				response.getWriter().write(JSONObject.fromObject(ret).toString());
				return false;
			}
			response.sendRedirect(request.getContextPath() + "/system/login");
			return false;
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
