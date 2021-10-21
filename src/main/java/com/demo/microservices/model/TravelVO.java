package com.demo.microservices.model;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TravelVO {
	private int trvlId; // 여행 pk   
	private String trvlName; // 해당 여행 제목 
	private Date trvlStartDt; // 여행시작날짜 
	private Date trvlEndDt; // 여행종료날짜 
	private int cntryId; // 나라pk
	private int cityId; // 도시pk
	private String cntryName; // 나라 이름
	private String cityName; // 도시 이름 
	private String imgUrl; // 썸네일 사진 
	private int cmpnCnt; // 인구수 
	private int trvlPd; // 여행일수
	private long totalPayAmt; // 총 사용금액
	private String cmpnType; // 동행타입 
	private String trvlMainFctr; // 여행중요도 
	private String clstrLabel; // 클러스터링 라벨 
	private String useYn; // 정보제공 동의여부 
	private int userId; // 사용자 아이디 
	private String userName; // 유저 이름 
	private long budgetAmt; // 목표 금액 
	private float totalRoomRate; // 총 숙소 비율 
	private float totalFoodRate; // 총 식비 비율
	private float totalTrffRate; // 총 교통 비율 
	private float totalActRate; // 총 엑티비티 비율
	private float totalEtcRate; // 총 기타 비율
	private int inputId; // 작성자 
	private Date inputDt; // 최초 작성일자
	private int modifyId; // 수정자 
	private Date modifyDt; // 수정일자 
	private List<TravelReviewVO> trvVO; // 해당 여행 후기글
}
