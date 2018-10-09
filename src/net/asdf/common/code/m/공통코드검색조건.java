package net.asdf.common.code.m;

import net.asdf.core.model.Model;

public class 공통코드검색조건 implements Model {

	private String 코드;
	/**
	 *
	 * <code>
	 * 행포함구분자[코드지정구분자]
	 * </code>
	 * <p>
	 * 구분자
	 * <ol>
	 * <li>행포함 구분자 1자리</li>
	 * <li>코드지정구분자 행포함 구분자를 통해 생성된 행의 코드지정 구분자('V') 1자리. 지정 시 기본코드로 주어진 코드를 부여. 생략 시 값은 빈문자열('')로 부여</li>
	 * </ol>
	 * </p>
	 * <p>행포함 구분자
	 * <ul>
	 * <li>S : '선택' 행 포함</li>
	 * <li>A : '전체' 행 포함</li>
	 * <li>E : 공백 행 포함</li>
	 * <li>X : 코드만 (결과형태 생략 시 기본 값)</li>
	 * <li>T : 지정된 명칭의 행 포함</li>
	 * </ul>
	 * </p>
	 * <code><pre>
	 * 예시)
	 *
	 * 1. 조건에 [{ 코드: 'P00' }] 지정 시
	 *
	 * 코드만 포함한 결과가 반환됨
	 *
	 * 2. 조건에 [{ 코드: 'P00', 결과형태: 'S' }] 지정 시
	 *
	 * 결과 목록의 제일 첫번째에 아래 데이터 포함
	 *
	 * { 코드: '', 코드명: '선택' }
	 *
	 * 3. 조건에 [{ 코드: 'P00', 결과형태: 'E' }] 지정 시
	 *
	 * 결과 목록의 제일 첫번째에 아래 데이터 포함
	 *
	 * { 코드: '', 코드명: '' }
	 *
	 * 4. 조건에 [{ 코드: 'P00', 결과형태: 'T', 기본코드명: '지정한코드명' }] 지정 시
	 *
	 * 결과 목록의 제일 첫번째에 아래 데이터 포함
	 *
	 * { 코드: '', 코드명: '지정한코드명' }
	 *
	 * 5. 조건에 [{ 코드: 'P00', 결과형태: 'AV', 기본코드: 'ALL' }] 지정 시
	 *
	 * 결과 목록의 제일 첫번째에 아래 데이터 포함
	 *
	 * { 코드: 'ALL', 코드명: '전체' }
	 *
	 * </pre></code>
	 *
	 */
	private String 결과형태;

	private String 기본코드명;

	private boolean 코드포함여부;

	private String 기본코드;

	private String 별칭;

	private String 모델명;

	private String 속성명;

	public String get코드() {
		return 코드;
	}

	public void set코드(String 코드) {
		this.코드 = 코드;
		if(this.코드 != null) {
			int i = 0;
			if((i = this.코드.indexOf(".")) > -1){
				this.모델명 = this.코드.substring(0, i);
				this.속성명 = this.코드.substring(i+1, this.코드.length());
			}
		}
	}

	public String get결과형태() {
		return 결과형태;
	}

	public void set결과형태(String 결과형태) {
		this.결과형태 = 결과형태;
	}

	public String get기본코드명() {
		return 기본코드명;
	}

	public void set기본코드명(String 기본코드명) {
		this.기본코드명 = 기본코드명;
	}

	public String get기본코드() {
		return 기본코드;
	}

	public void set기본코드(String 기본코드) {
		this.기본코드 = 기본코드;
	}

	public boolean is코드포함여부() {
		return 코드포함여부;
	}

	public void set코드포함여부(boolean 코드포함여부) {
		this.코드포함여부 = 코드포함여부;
	}

	public String get별칭() {
		return 별칭;
	}

	public void set별칭(String 별칭) {
		this.별칭 = 별칭;
	}

	public String get모델명() {
		return this.모델명;
	}

	public String get속성명() {
		return this.속성명;
	}
}

