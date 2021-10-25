package com.demo.microservices.model;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TravelSurveyVO {
	private int userId; // 사용자 아이디 
	private int cmpnCnt; // 동행인 수
	private int trvlPd; // 여행일수
	private int budgetAmt; // 예산
	private String cmpnType; // 동행타입 
	private String trvlMainFctr; // 여행중요도 
	
	private float totalRoomRate; // 총 숙소 비율 
	private float totalFoodRate; // 총 식비 비율
	private float totalTrffRate; // 총 교통 비율 
	private float totalActRate; // 총 엑티비티 비율
	private float totalEtcRate; // 총 기타 비율
}
