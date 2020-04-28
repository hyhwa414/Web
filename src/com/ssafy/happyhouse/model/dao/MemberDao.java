package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;

import com.ssafy.happyhouse.model.dto.MemberDto;

public interface MemberDao {
	public MemberDto getPwd(String id, String name, String phone) throws SQLException;
	public void join(MemberDto memberDto);
	public MemberDto login(String userid, String userpwd) throws SQLException;
	public void modifyInfo(MemberDto memberDto) throws SQLException;
	public MemberDto getInfo(String userid) throws SQLException;
	public void delete(String userid) throws SQLException;
}
