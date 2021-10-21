package com.demo.microservices.controller;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.microservices.dao.TravelDao;
import com.demo.microservices.model.TravelReviewVO;
import com.demo.microservices.model.TravelVO;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TravelController {
	@Autowired
	TravelDao travelDao;
	
	@ApiOperation(value="여행지 추천 API 입니다.")
	@GetMapping(value="/travel/recommendation")
	public ResponseEntity<List<TravelVO>> travelRecomm(TravelVO t) {
		return null;
	}
	
	@ApiOperation(value="여행지 후기 전체 검색 API 입니다.")
	@GetMapping(value="/travel/reviews")
	public ResponseEntity<List<TravelVO>> searchAllTravel() {
		List<TravelVO> list = null;
		try {
			log.info("Start");
			list = travelDao.searchAllTravel();
		} catch (Exception e) {
			log.info("ERROR", e);
			throw new RuntimeException(e);
		}
		log.info("travel list cnt : " + list.size());
		return new ResponseEntity<List<TravelVO>> (list, HttpStatus.OK);
	}
	
	@ApiOperation(value="여행지 키워드 검색 API 입니다.")
	@GetMapping(value="/travel/reviews/detail/{keyword}")
	public ResponseEntity<List<TravelVO>> searchTravel(@PathVariable String keyword) {
		List<TravelVO> list = null;
		try {
			log.info("Start");
			list = travelDao.searchTravel(keyword);
		} catch (Exception e) {
			log.info("ERROR", e);
			throw new RuntimeException(e);
		}
		log.info("travel list cnt : " + list.size());
		return new ResponseEntity<List<TravelVO>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value="여행지 상세 검색 API 입니다.")
	@GetMapping(value="/travel/reviews/detail")
	public ResponseEntity<List<TravelVO>> searchTravel(@RequestBody TravelVO t) {
		List<TravelVO> list = null;
		try {
			log.info("Start");
			list = travelDao.searchTravel(t);
		} catch (Exception e) {
			log.info("ERROR", e);
			throw new RuntimeException(e);
		}
		log.info("travel list cnt : " + list.size());
		return new ResponseEntity<List<TravelVO>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value="여행지 선택 API 입니다.") 
	@GetMapping(value="/travel/reviews/{trvlId}")
	public ResponseEntity<TravelVO> selectTravel(@PathVariable int trvlId) {
		TravelVO travel = null;
		try {
			log.info("Start");
			travel = travelDao.selectTravel(trvlId);
		} catch (Exception e) {
			log.info("ERROR", e);
			throw new RuntimeException(e);
		}
		log.info("select travel id : " + travel.getTrvlId());
		return new ResponseEntity<TravelVO>(travel, HttpStatus.OK);
	}
	
	@ApiOperation(value="여행지 작성 API 입니다.")
	@PostMapping(value="/travel/reviews")
	public ResponseEntity<TravelVO> insertTravel(@RequestBody TravelVO t) {
		TravelVO travel = null;
		try {
			log.info("Start");
			travel = travelDao.insertTravel(t);
		} catch (Exception e) {
			log.info("ERROR", e);
			throw new RuntimeException(e);
		}
		log.info("create travel id : " + travel.getTrvlId());
		return new ResponseEntity<TravelVO>(travel, HttpStatus.OK);
	}
	
	@ApiOperation(value="여행지 수정 API 입니다.")
	@PutMapping(value="/travel/reviews/{trvlId}")
	public ResponseEntity<TravelVO> updateTravel(@RequestBody TravelVO t) {
		TravelVO travel = null;
		try {
			log.info("Start");
			travel = travelDao.updateTravel(t);
		} catch (Exception e) {
			log.info("ERROR", e);
			throw new RuntimeException(e);
		}
		log.info("update travel id : " + travel.getTrvlId());
		return new ResponseEntity<TravelVO>(travel, HttpStatus.OK);
	}
	
	@ApiOperation(value="여행지 삭제 API 입니다.")
	@DeleteMapping(value="/travel/reviews/{trvlId}")
	public ResponseEntity<Integer> deleteTravel(@PathVariable int trvlId) {
		int result = -1;
		try {
			log.info("Start");
			result = travelDao.deleteTravel(trvlId);
		} catch (Exception e) {
			log.info("ERROR", e);
			throw new RuntimeException(e);
		}
		log.info("delete travel id ; " + result);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
