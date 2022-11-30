package com.dksys.biz.user.ar.ar14.service.impl;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dksys.biz.admin.cm.cm08.service.CM08Svc;
import com.dksys.biz.user.ar.ar14.mapper.AR14Mapper;
import com.dksys.biz.user.ar.ar14.service.AR14Svc;
import com.dksys.biz.util.ObjectUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.dksys.biz.user.ar.ar14.mapper.AR14Mapper;

@Service
@Transactional(rollbackFor = Exception.class)
public class AR14SvcImpl implements AR14Svc {
	
    @Autowired
    AR14Mapper ar14Mapper;
    
    @Autowired
    CM08Svc cm08Svc;
    
	@Override
	public int selectDebtCount(Map<String, String> paramMap) {
		return ar14Mapper.selectDebtCount(paramMap);
	}

	@Override
	public List<Map<String, String>> selectDebtList(Map<String, String> paramMap) {
		
		List<Map<String, String>> resultListMap = new ArrayList<Map<String,String>>(); 
		resultListMap = ar14Mapper.selectDebtList(paramMap);
		
		
		int balance = 0;
		
		if(resultListMap != null) {
			for(int i=0; i<resultListMap.size(); i++) {
				String assortCd = resultListMap.get(i).get("assortCd");
				int debtAmt = MapUtils.getInteger(resultListMap.get(i), "debtAmt"); 
				
				// 부채일시
				if(assortCd.equals("ASSORTCD1")) {
					balance += debtAmt;
					resultListMap.get(i).put("DEBT_AMT", Integer.toString(debtAmt));
					resultListMap.get(i).put("REPAY_AMT1", Integer.toString(0));
					resultListMap.get(i).put("REPAY_AMT2", Integer.toString(0));
					resultListMap.get(i).put("REPAY_AMT3", Integer.toString(0));
					resultListMap.get(i).put("REPAY_AMT4", Integer.toString(0));
				// 상환일시
				}else if(assortCd.equals("ASSORTCD2")) {
					balance -= debtAmt;
					resultListMap.get(i).put("DEBT_AMT", Integer.toString(0));
					resultListMap.get(i).put("REPAY_AMT1", Integer.toString(debtAmt));
					resultListMap.get(i).put("REPAY_AMT2", Integer.toString(0));
					resultListMap.get(i).put("REPAY_AMT3", Integer.toString(0));
					resultListMap.get(i).put("REPAY_AMT4", Integer.toString(0));
				}else if(assortCd.equals("ASSORTCD3")) {
					balance -= debtAmt;
					resultListMap.get(i).put("DEBT_AMT", Integer.toString(0));
					resultListMap.get(i).put("REPAY_AMT1", Integer.toString(0));
					resultListMap.get(i).put("REPAY_AMT2", Integer.toString(debtAmt));
					resultListMap.get(i).put("REPAY_AMT3", Integer.toString(0));
					resultListMap.get(i).put("REPAY_AMT4", Integer.toString(0));
				}else if(assortCd.equals("ASSORTCD4")) {
					balance -= debtAmt;
					resultListMap.get(i).put("DEBT_AMT", Integer.toString(0));
					resultListMap.get(i).put("REPAY_AMT1", Integer.toString(0));
					resultListMap.get(i).put("REPAY_AMT2", Integer.toString(0));
					resultListMap.get(i).put("REPAY_AMT3", Integer.toString(debtAmt));
					resultListMap.get(i).put("REPAY_AMT4", Integer.toString(0));
				}else if(assortCd.equals("ASSORTCD5")) {
					balance -= debtAmt;
					resultListMap.get(i).put("DEBT_AMT", Integer.toString(0));
					resultListMap.get(i).put("REPAY_AMT1", Integer.toString(0));
					resultListMap.get(i).put("REPAY_AMT2", Integer.toString(0));
					resultListMap.get(i).put("REPAY_AMT3", Integer.toString(0));
					resultListMap.get(i).put("REPAY_AMT4", Integer.toString(debtAmt));
				}
				
				resultListMap.get(i).put("balance", Integer.toString(balance));
			}
		}
		
		return resultListMap;
	}
	
	@Override
	// 매입 / 반입 / 매출 / 반품
	public void insertDebt(Map<String, String> paramMap, MultipartHttpServletRequest mRequest) {
		ar14Mapper.insertDebt(paramMap);
		
		// 파일 업로드
		cm08Svc.uploadFile("TB_AR14M01", paramMap.get("debtNo"), mRequest);
	}
	
	@Override
	// 매입 / 반입 / 매출 / 반품
	public void updateDebt(Map<String, String> paramMap, MultipartHttpServletRequest mRequest) {
		Gson gson = new Gson();
		Type stringList = new TypeToken<ArrayList<String>>() {}.getType();
		Type mapList = new TypeToken<ArrayList<Map<String, String>>>() {}.getType();
		
		ar14Mapper.updateDebt(paramMap);
		
		// 파일 업로드
		cm08Svc.uploadFile("TB_AR14M01", paramMap.get("debtNo"), mRequest);
		// 파일 삭제
		List<String> deleteFileList = gson.fromJson(paramMap.get("deleteFileArr"), stringList);
		for(String fileKey : deleteFileList) {
			cm08Svc.deleteFile(fileKey);
		}
	}
	
	@Override
	public int selectDebtGroupCount(Map<String, String> paramMap) {
		return ar14Mapper.selectDebtGroupCount(paramMap);
	}
	
	@Override
	public List<Map<String, String>> selectDebtGroupList(Map<String, String> paramMap) {
		return ar14Mapper.selectDebtGroupList(paramMap);
	}
	
	@Override
	// 부실채권 삭제
	public void deleteDebt(Map<String, String> paramMap) {
		ar14Mapper.deleteDebt(paramMap);
	}

	@Override
	public Map<String, Object> selectDebtInfo(Map<String, String> paramMap) {
		return ar14Mapper.selectDebtInfo(paramMap);
	}

}