package com.dksys.biz.admin.standard.users.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CodeUsersMapper {
	
	List<Map<String, String>> selectCodeList();

	int insertCode(Map<String, String> param);

	int deleteCode(Map<String, String> param);

	int updateCode(Map<String, String> param);
	
}
