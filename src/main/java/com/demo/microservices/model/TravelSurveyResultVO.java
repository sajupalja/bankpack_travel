package com.demo.microservices.model;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TravelSurveyResultVO {
	private int cntryId; // 나라pk
	private int cityId; // 도시pk
	private String cntryName; // 나라이름
	private String cityName; // 도시이름
	private int cnt; // 클러스터에 해당하는 동일 도시 갯수
	private int clstrLabel; // 클러스터 라벨
	private String imgUrl; // 썸네일 이미지 url
}
