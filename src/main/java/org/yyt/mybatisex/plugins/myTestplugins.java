package org.yyt.mybatisex.plugins;

import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.javassist.tools.reflect.Metaobject;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Statement;

@Intercepts({
	@Signature(type=StatementHandler.class,method="parameterize",args= {Statement.class})
})
public class myTestplugins implements Interceptor {
	

	/**
	 * intercept:拦截目标方法
	 */
	@Override
	public Object intercept(Invocation arg0) throws Throwable {
		// TODO Auto-generated method stub
		//要拦截的目标方法
		System.out.println("拦截目标方法——intercept"+arg0.getMethod());
		//拦截的对象
		System.out.println("拦截的对象:"+arg0.getTarget());
		Object objectTarget=arg0.getTarget();
		//拿到StatementHandler==>parameterHandler==>parameterObject
		MetaObject metaobject=SystemMetaObject.forObject(objectTarget);
		//拿到slq的参数
		Object value=metaobject.getValue("parameterHandler.parameterObject");
		System.out.println("sql参数:"+value);
		//执行目标方法
		Object object=arg0.proceed();
		return object;
	}
	/**
	 * plugin:
	 * 包装目标对象-创建一个代理对象
	 */
	@Override
	public Object plugin(Object arg0) {
		// TODO Auto-generated method stub
		System.out.println("包装目标对象——plugin"+arg0);
		Object object=Plugin.wrap(arg0, this);
		
		return object;
	}
	/**
	 * setProperties:
	 * 注册时的属性配置信息
	 */
	@Override
	public void setProperties(Properties arg0) {
		// TODO Auto-generated method stub
		System.out.println("插件配置信息："+arg0);
	}

}
