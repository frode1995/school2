package com.frode.system.shiro;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.frode.common.config.ApplicationContextRegister;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.frode.common.utils.ShiroUtils;
import com.frode.system.dao.UserDao;
import com.frode.system.domain.UserDO;
import com.frode.system.service.MenuService;

public class UserRealm extends AuthorizingRealm {

	/*首先调用Subject.login(token)进行登录，其会自动委托给Security Manager，调用之前必
须通过SecurityUtils. setSecurityManager()设置；
			2、SecurityManager负责真正的身份验证逻辑；它会委托给Authenticator进行身份验证；
			3、Authenticator才是真正的身份验证者，Shiro API中核心的身份认证入口点，此处可以自
	定义插入自己的实现；
			4、Authenticator可能会委托给相应的AuthenticationStrategy进行多Realm身份验证，默认
	ModularRealmAuthenticator会调用AuthenticationStrategy进行多Realm身份验证；
			5、Authenticator 会把相应的token 传入Realm，从Realm 获取身份验证信息，如果没有返
	回/抛出异常表示身份验证失败了。此处可以配置多个Realm，将按照相应的顺序及策略进
	行访问。*/
	@Autowired
	UserDao userMapper;
	@Autowired
	MenuService menuService;
	//用户名密码校验功能  重写确定哪张表
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		Long userId = ShiroUtils.getUserId();
		MenuService menuService = ApplicationContextRegister.getBean(MenuService.class);
		Set<String> perms = menuService.listPerms(userId);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(perms);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		Map<String, Object> map = new HashMap<>(16);
		map.put("username", username);
		String password = new String((char[]) token.getCredentials());

		UserDao userMapper = ApplicationContextRegister.getBean(UserDao.class);
		// 查询用户信息
		UserDO user = userMapper.list(map).get(0);

		// 账号不存在
		if (user == null) {
			throw new UnknownAccountException("账号或密码不正确");
		}

		// 密码错误
		if (!password.equals(user.getPassword())) {
			throw new IncorrectCredentialsException("账号或密码不正确");
		}

		// 账号锁定
		if (user.getStatus() == 0) {
			throw new LockedAccountException("账号已被锁定,请联系管理员");
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
		return info;
	}

}
