package com.demo.microservices.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.demo.microservices.model.TravelSurveyRateVO;
import com.demo.microservices.model.TravelSurveyResultVO;
import com.demo.microservices.model.TravelSurveyVO;


@Mapper
public interface TravelRecommendDao {
	
	String getRecommendCluster(TravelSurveyVO survey); //여행지 설문을 기반으로 cluster 계산
	
	List<TravelSurveyResultVO> getRecommendResults(String cluster); //cluster값과 일치하는 여행지 목록

	TravelSurveyRateVO getSurveyRate(int userId, String trvlMainFctr);
}