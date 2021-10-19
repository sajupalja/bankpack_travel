package com.demo.microservices.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.demo.microservices.model.SampleUser;

@Mapper
public interface SampleUserDao {
	
	List<SampleUser> selectUserAll() throws Exception;
	
	SampleUser selectUser(String userId);


	int insertUser(SampleUser user);


	int updateUser(SampleUser user);


	int deleteUser(String userId);
;
}


