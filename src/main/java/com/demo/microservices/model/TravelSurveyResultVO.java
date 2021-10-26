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
	private int cnt; // 클러스터에 해당하는 동일 도시 갯수
}
