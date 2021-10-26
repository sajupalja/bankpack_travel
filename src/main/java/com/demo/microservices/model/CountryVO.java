package com.demo.microservices.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CountryVO {
	private int cntryId;
	private String cntryName;
	private String cntryEngName;
	private int cityId;
	private String imgUrl;
	private String cityName;
	private String cityEngName;
	private List<TravelVO> travels;
	private long avgTotalPayAmt;
	private long avgRoomAmt;
	private long avgFoodAmt;
	private long avgTrffAmt;
	private long avgActAmt;
	private long avgEtcAmt;
	private float avgRoomRate;
	private float avgFoodRate;
	private float avgTrffRate;
	private float avgActRate;
	private float avgEtcRate;
	private String clstrLabel;
}
