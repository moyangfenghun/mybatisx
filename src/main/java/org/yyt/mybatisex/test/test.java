package org.yyt.mybatisex.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.yyt.mybatisex.bean.User;
import org.yyt.mybatisex.dao.Usermapper;

public class test {
	@Test
	public void tesa() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);

		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		// ExecutorType.BATCH:批量执行sql
		SqlSession session = sqlSessionFactory.openSession();

		Usermapper usermapper = session.getMapper(Usermapper.class);

		User user = usermapper.selectByPrimaryKey(1000);
		System.out.println(user);
		// 分页查询
		// List<User> users=usermapper.selectuserlist(5,2);
		// for (Iterator iterator = users.iterator(); iterator.hasNext();) {
		// User userx = (User) iterator.next();
		// System.out.println(userx);
		// }

		// 清楚一级缓存
		// session.clearCache();

		/*添加事务的影响
		 * 在使用事务时必须有这句否则会更新操作会回滚session.commit();
		 * 对UPDATE操作的影响主要表现在UPDATE操作后如果没有进行事务提交则会子啊会话关闭时进行数据库回滚；
		 * 对SELECT操作的影响主要表现在二级缓存上，执行SELECT操作后如果未进行事务提交则缓存会被放在临时缓存中，
		 * 后续的SELECT操作无法使用该缓存，直到进行commit()事务提交，临时缓存中的数据才会被转移到正式缓存中；
		 */
		session.close();
	}
}
