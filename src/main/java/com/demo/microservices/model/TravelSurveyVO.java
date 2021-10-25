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
	
	private TravelSurveyRateVO travelSurveyRateVO; // 비용 비율
}
