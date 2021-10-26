package com.demo.microservices.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestTravelReviewVO {
	private int trvlRevwId; // 후기내용 pk
	private int trvlId; // 여행지 후기 pk
	private String revwText; // 후기 내용
	private Date trvlDt; // 여행일자
	private String imgUrl; // 해당 후기의 사진
	private int userId; // 작성자 pk
}
