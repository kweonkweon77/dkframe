package com.dksys.biz.admin.cm.code.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dksys.biz.admin.cm.code.mapper.CodeMapper;
import com.dksys.biz.admin.cm.code.service.CodeService;

@Service
public class CodeServiceImpl implements CodeService {
	
    @Autowired
    CodeMapper codeMapper;
    
    @Override
	public int selectCodeCount(Map<String, String> param) {
		return codeMapper.selectCodeCount(param);
	}
    
	@Override
	public List<Map<String, String>> selectCodeList(Map<String, String> param) {
		return codeMapper.selectCodeList(param);
	}
	
	@Override
	public List<Map<String, String>> selectChildCodeList(Map<String, String> param) {
		return codeMapper.selectChildCodeList(param);
	}
	
	@Override
	public Map<String, String> selectCodeInfo(Map<String, String> param) {
		return codeMapper.selectCodeInfo(param);
	}
	
	@Override
	public List<Map<String, String>> selectCodeTree() {
		return codeMapper.selectCodeTree();
	}

	@Override
	public int insertCode(Map<String, String> param) throws Exception {
		return codeMapper.insertCode(param);
	}

	@Override
	public int deleteCode(Map<String, String> param) {
		return codeMapper.deleteCode(param);
	}

}