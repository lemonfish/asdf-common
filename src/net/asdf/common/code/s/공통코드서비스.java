package net.asdf.common.code.s;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

import net.asdf.common.code.m.공통코드;
import net.asdf.common.code.m.공통코드검색조건;
import net.asdf.core.data.CommonDao;
import net.asdf.core.exception.FrameworkException;
import net.asdf.core.jdbc.transaction.NoTx;
import net.asdf.core.util.Objectz;

@Service
public class 공통코드서비스 {
	@Resource
	private CommonDao commonDao;

	@Resource
	private Map<String, String> 모델코드맵;

	@NoTx
	public Map<String, List<공통코드>> 조회(List<공통코드검색조건> 코드검색조건목록){
		Map<String, List<공통코드>> 코드목록맵 = new HashMap<String, List<공통코드>>(코드검색조건목록.size() * 5);

		공통코드서비스 $공통코드서비스 = (공통코드서비스) AopContext.currentProxy();
		for(공통코드검색조건 $코드검색조건 : 코드검색조건목록) {
			코드목록맵.put(Objectz.nvls($코드검색조건.get별칭(), $코드검색조건.get속성명(), $코드검색조건.get코드()), $공통코드서비스.조회($코드검색조건));
		}
		return 코드목록맵;
	}

	@NoTx
	public List<공통코드> 조회(공통코드검색조건 $코드검색조건){

		String 코드 = $코드검색조건.get코드();
		if($코드검색조건.get모델명() != null) {
			if(모델코드맵.containsKey(코드)) {
				$코드검색조건.set코드(모델코드맵.get(코드));
			}else {
				new FrameworkException("%s 의  %s 속성에 코드 어노테이션이 누락되었습니다.", $코드검색조건.get모델명(), $코드검색조건.get속성명());
			}
		}

		return commonDao.list("공통코드.조회", $코드검색조건, 공통코드.class);
	}
}
