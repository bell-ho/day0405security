package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.dao.MemberDao;
import com.example.demo.vo.MemberVo;

public class MemberService implements UserDetailsService {
	@Autowired
	private MemberDao dao;
	
	
	public void setDao(MemberDao dao) {
		this.dao = dao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		MemberVo m = dao.login(username);
		
		if(m==null) {
			throw new UsernameNotFoundException(username);
		}
		return User.builder()
				.username(username)
				.password(m.getMember_pwd())
				.roles(m.getMember_role())
				.build();
	}

}
