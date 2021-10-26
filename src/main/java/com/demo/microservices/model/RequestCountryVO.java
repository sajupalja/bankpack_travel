package com.demo.microservices.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestCountryVO {
	private int cntryId;
	private int cityId;
	private String clstrLabel;
}
