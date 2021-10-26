package com.demo.microservices.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestTravelVO {
	private int trvlId; // 여행 pk 
	private String trvlName; // 해당 여행 제목 
	private Date trvlStartDt; // 여행시작날짜 
	private Date trvlEndDt; // 여행종료날짜
	private int cntryId; // 나라pk
	private int cityId; // 도시pk
	private String imgUrl; // 썸네일 사진 
	private int cmpnCnt; // 인구수 
	private int trvlPd; // 여행일수
	private long totalPayAmt; // 총 사용금액
	private String cmpnType; // 동행타입 
	private String trvlMainFctr; // 여행중요도 
	private String clstrLabel; // 클러스터링 라벨 
	private String useYn; // 정보제공 동의여부 
	private int userId; // 사용자 아이디 
	private long budgetAmt; // 목표 금액
}
