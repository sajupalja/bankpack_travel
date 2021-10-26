package com.demo.microservices.model;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TravelSurveyRateVO {
	
	private float totalRoomRate; // 총 숙소 비율 
	private float totalFoodRate; // 총 식비 비율
	private float totalTrffRate; // 총 교통 비율 
	private float totalActRate; // 총 엑티비티 비율
	private float totalEtcRate; // 총 기타 비율
}
