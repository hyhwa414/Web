package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;

import com.ssafy.happyhouse.model.dto.MemberDto;

public interface MemberService {
	public MemberDto getPwd(String id, String name, String phone) throws SQLException;
	public void join(MemberDto memberDto) throws Exception;
	public MemberDto login(String userid, String userpwd) throws Exception;
	public void modifyInfo(MemberDto memberDto) throws SQLException;
	public MemberDto getInfo(String userid) throws Exception;
	public void delete(String userid) throws Exception;
}
