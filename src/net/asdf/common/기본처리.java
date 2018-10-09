package net.asdf.common;

import java.util.List;
import java.util.Map;

import net.asdf.core.model.StatefullModel;

public interface 기본처리<P extends Object, M extends StatefullModel> {

	Map<String, Object> 목록(P 검색조건);

	Map<String, Object> 조회(P 검색조건);

	Map<String, Object> 신규(M 신규데이터);

	Map<String, Object> 수정(M 수정된데이터);

	Map<String, Object> 삭제(M 삭제된데이터);

	Map<String, Object> 저장(List<M> 저장할데이터목록, boolean useAndGet);

}
