package com.demo.microservices.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SpendingVO {
	private String userId ; // 사용자 아이디
	private String trvlPayId; //여행결제내역 아이디
	private String trvlId; //내 여행 아이디
	private String trvlName; //내 여행 명칭
	private String payAmt; //결제금액
	private String payDate; //결제일시
	private String payName; //결제명
	private String payMethod; //카드pk or 현금(0)
	private String cardName; //카드 이름 혹은 현금 여부 표시명
	private String payType; //결제유형 코드 (식비, 숙박비, 등)
	private String payTypeName; //결제유형 이름 (식비, 숙박비, 등)
	private String payId; //내 결제내역 아이디
}
