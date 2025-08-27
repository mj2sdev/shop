package io.mj2sdev.shop.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseDTO {
	private String message;
	private Boolean result;

	/** 
	 * 생성을 편하게 해주는 문법적 설탕
	 * result는 매개변수 생략가능을 구현하기 위해 ...으로 되어 있으므로
	 * 여러개를 넣어도 한개만 쓰이고 버려집니다.
	 * @param message 표시할 메시지
	 * @param result 생략 할 경우 true
	 * @return ResponseDTO
	 */
	public static ResponseDTO of(String message, Boolean... results) {
		Boolean result = results.length > 0 ? results[0] : true;
		return ResponseDTO.builder()
			.result(result)
			.message(message)
			.build();
	}
}
