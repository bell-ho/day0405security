package com.example.demo.db;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.MemberVo;

public class MemberManager {
	private static SqlSessionFactory factory;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("com/example/demo/db/sqlMapConfig.xml");
			factory = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	public static MemberVo login(String username) {
		SqlSession session = factory.openSession();
		MemberVo m = session.selectOne("member.login",username);
		session.close();
		System.out.println("매니저 작동");
		return m;
	}
	
	public static int insert(MemberVo m) {
		SqlSession session = factory.openSession();
		int re =session.insert("member.insert",m);
		session.close();
		return re;
	}
}
