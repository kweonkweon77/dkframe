package com.dksys.biz.user.od.od01.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OD01Mapper {

	int insertOrder(Map<String, String> paramMap);

	int selectOrderCount(Map<String, String> paramMap);

	List<Map<String, String>> selectOrderList(Map<String, String> paramMap);

	int deleteOrderDetail(Map<String, String> paramMap);

	int insertOrderDetail(Map<String, String> detailMap);

	int deleteOrder(Map<String, String> paramMap);
	
}