package net.asdf.common;

import java.util.List;
import java.util.Map;

import org.springframework.core.GenericTypeResolver;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.asdf.core.exception.FrameworkException;
import net.asdf.core.model.Model;
import net.asdf.core.model.StatefullModel;
import net.asdf.core.web.controller.CommonCont;

public abstract class 기본처리기<P extends Model, M extends StatefullModel> extends CommonCont implements 기본처리<P, M> {

	private String 클래스요청매핑;

	private final Class<M> 모델클래스;

	@SuppressWarnings("unchecked")
	public 기본처리기() {
		RequestMapping rm = getClass().getAnnotation(RequestMapping.class);
		if(rm == null) {
			throw new FrameworkException("기본처리기를 상속하려면 클래스 단위 RequestMapping이 정의되어야 합니다.");
		}
		클래스요청매핑 = rm.path()[0];
		클래스요청매핑 = 클래스요청매핑.charAt(0) == '/' ? 클래스요청매핑.substring(1) : 클래스요청매핑;
		클래스요청매핑 = 클래스요청매핑.replace('/', '.');

		Class<?>[] classes = GenericTypeResolver.resolveTypeArguments(getClass(), 기본처리.class);
		this.모델클래스 = (Class<M>) classes[1];
	}

	@Override
	@RequestMapping("목록")
	public @ResponseBody Map<String, Object> 목록(@RequestBody P 검색조건) {
		return ok(commonService.listSimple(검색조건, this.모델클래스));
	}

	@Override
	@RequestMapping("조회")
	public @ResponseBody Map<String, Object> 조회(@RequestBody P 검색조건) {
		return ok(commonService.oneSimple(검색조건, this.모델클래스));
	}

	@Override
	@RequestMapping("/신규")
	public @ResponseBody Map<String, Object> 신규(@RequestBody M 신규데이터) {
		return ok(commonService.insertSimple(신규데이터));
	}

	@Override
	@RequestMapping("/수정")
	public @ResponseBody Map<String, Object> 수정(@RequestBody M 수정된데이터) {
		return ok(commonService.updateSimple(수정된데이터));
	}

	@Override
	@RequestMapping("/삭제")
	public @ResponseBody Map<String, Object> 삭제(@RequestBody M 삭제된데이터) {
		return ok(commonService.updateSimple(삭제된데이터));
	}

	@Override
	@RequestMapping("/저장")
	public @ResponseBody Map<String, Object> 저장(@RequestBody List<M> 저장할데이터목록, boolean useAndGet) {
		return ok(commonService.saveModel(클래스요청매핑, 저장할데이터목록, useAndGet));
	}

	protected String get클래스요청매핑() {
		return 클래스요청매핑;
	}

	protected void set클래스요청매핑(String 클래스요청매핑) {
		this.클래스요청매핑 = 클래스요청매핑;
	}

}