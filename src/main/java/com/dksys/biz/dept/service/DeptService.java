package com.dksys.biz.dept.service;

import java.util.List;
import java.util.Map;

public interface DeptService {
	
	List<Map<String, String>> selectDeptTree();
	
}