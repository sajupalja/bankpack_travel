package com.demo.microservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.microservices.dao.TravelRecommendDao;
import com.demo.microservices.model.TravelSurveyRateVO;
import com.demo.microservices.model.TravelSurveyResultVO;
import com.demo.microservices.model.TravelSurveyVO;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class TravelRecommendController {
	
	@Autowired
	TravelRecommendDao travelRecommendDao;
	
	
	@ApiOperation(value="여행지 설문을 기반으로 반환된 cluster값과 일치하는 여행지 목록")
	@GetMapping(value="/recommend/result")
	public ResponseEntity <List<TravelSurveyResultVO>> recommendWithSurvey(@RequestBody TravelSurveyVO survey){

		List<TravelSurveyResultVO> list = null;
		TravelSurveyRateVO rate = null;
		String cluster = null;
		
		try {
			
			rate = travelRecommendDao.getSurveyRate(survey.getUserId(), survey.getTrvlMainFctr());
			log.info("설문 데이터와 유저 기존 데이터를 기반으로 rate값 계산");
			
			survey.setTravelSurveyRateVO(rate); //rate 데이터 만든거 서베이에 넣어서 넘김
			log.info("rate값 survey 데이터에 대입");
			
			log.info("여행지 설문을 기반으로 cluster 계산");
			cluster = travelRecommendDao.getRecommendCluster(survey); //여행지 설문을 기반으로 cluster 계산
			log.info("여행지 설문을 기반으로 cluster 계산한 값 :: "+cluster);
			
			list = travelRecommendDao.getRecommendResults(cluster); //cluster값과 일치하는 여행지 목록
			
		} catch (Exception e) {
			
			log.error("Error",e);
			throw new RuntimeException(e);
		}
		
		return new ResponseEntity<List<TravelSurveyResultVO>> (list, HttpStatus.OK);
	}
	
	
	
	
}
	
	
