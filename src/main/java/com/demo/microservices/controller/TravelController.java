package com.demo.microservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.microservices.dao.TravelDao;
import com.demo.microservices.model.CountryVO;
import com.demo.microservices.model.TravelReviewVO;
import com.demo.microservices.model.TravelVO;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class TravelController {
	@Autowired
	TravelDao travelDao;
	
	@ApiOperation(value="여행지 추천 API 입니다.")
	@GetMapping(value="/recommendation")
	public ResponseEntity<List<TravelVO>> travelRecomm(TravelVO t) {
		return null;
	}
	
	@ApiOperation(value="여행지 후기 전체 검색 API 입니다.")
	@GetMapping(value="/reviews")
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
	
	@ApiOperation(value="여행지 키워드 검색 API 입니다. 현재 제목 검색만 가능")
	@GetMapping(value="/reviews/detail/{keyword}")
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
	
	@ApiOperation(value="여행지 상세 검색 API 입니다. 현재 사용X")
	@PostMapping(value="/reviews/detail")
	public ResponseEntity<List<TravelVO>> searchTravel(@RequestBody TravelVO t) {
		List<TravelVO> list = null;
		try {
			log.info("Start");
			list = travelDao.searchDetailTravel(t);
		} catch (Exception e) {
			log.info("ERROR", e);
			throw new RuntimeException(e);
		}
		log.info("travel list cnt : " + list.size());
		return new ResponseEntity<List<TravelVO>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value="여행지 게시글 선택 API 입니다.") 
	@GetMapping(value="/reviews/{trvlId}")
	public ResponseEntity<TravelVO> selectTravel(@PathVariable int trvlId) {
		TravelVO travel = null;
		List<TravelReviewVO> travelRevw = null;
		try {
			log.info("Start");
			travel = travelDao.selectTravel(trvlId);
			travelRevw = travelDao.selectAllTravelRevw(trvlId);
			travel.setTrvVO(travelRevw);
		} catch (Exception e) {
			log.info("ERROR", e);
			throw new RuntimeException(e);
		}
		log.info("select travel id : " + travel.getTrvlId());
		return new ResponseEntity<TravelVO>(travel, HttpStatus.OK);
	}
	
	@ApiOperation(value="여행지 게시글 작성 API 입니다.")
	@PostMapping(value="/reviews")
	public ResponseEntity<TravelVO> insertTravel(@RequestBody TravelVO t) {
		int result = -1;
		TravelVO travel = null;
		
		try {
			log.info("Start");
			result = travelDao.insertTravel(t);
			log.info("Success" + result);
			if(result > 0) {
				travel = travelDao.selectTravel(t.getTrvlId());
			}
		} catch (Exception e) {
			log.info("ERROR", e);
			throw new RuntimeException(e);
		}
		log.info("create travel id : " + travel.getTrvlId());
		return new ResponseEntity<TravelVO>(travel, HttpStatus.OK);
	}
	
	@ApiOperation(value="여행지 게시글 수정 API 입니다.")
	@PutMapping(value="/reviews/{trvlId}")
	public ResponseEntity<TravelVO> updateTravel(@RequestBody TravelVO t) {
		int result = -1;
		TravelVO travel = null;
		try {
			log.info("Start");
			result = travelDao.updateTravel(t);
			if(result > 0) {
				travel = travelDao.selectTravel(t.getTrvlId());
			}
		} catch (Exception e) {
			log.info("ERROR", e);
			throw new RuntimeException(e);
		}
		log.info("update travel id : " + travel.getTrvlId());
		return new ResponseEntity<TravelVO>(travel, HttpStatus.OK);
	}
	
	@ApiOperation(value="여행지 게시글 삭제 API 입니다.")
	@DeleteMapping(value="/reviews/{trvlId}")
	public ResponseEntity<Integer> deleteTravel(@PathVariable int trvlId) {
		int result = -1;
		try {
			log.info("Start");
			result = travelDao.deleteTravel(trvlId);
		} catch (Exception e) {
			log.info("ERROR", e);
			throw new RuntimeException(e);
		}
		if(result > 0) {
			log.info("delete travel id : " + trvlId);
		}
		log.info("delete travel id ; " + trvlId);
		return new ResponseEntity<Integer>(trvlId, HttpStatus.OK);
	}
	
	@ApiOperation(value="여행지 후기 작성 API 입니다.")
	@PostMapping(value="/reviews/review-entry")
	public ResponseEntity<TravelReviewVO> insertTravelRevw(@RequestBody TravelReviewVO tr) {
		int result = -1;
		TravelReviewVO travelRevw = null;
		try {
			log.info("Start");
			result = travelDao.insertTravelRevw(tr);
			if(result > 0) {
				travelRevw = travelDao.selectTravelRevw(tr.getTrvlRevwId());
			}
		} catch (Exception e) {
			log.info("ERROR", e);
			throw new RuntimeException(e);
		}
		log.info("create travel review id : " + travelRevw.getTrvlId());
		return new ResponseEntity<TravelReviewVO>(travelRevw, HttpStatus.OK);
	}
	
	@ApiOperation(value="여행지 후기 수정 API 입니다.")
	@PutMapping(value="/reviews/review-entry/{trvlId}")
	public ResponseEntity<TravelReviewVO> updateTravelRevw(@RequestBody TravelReviewVO tr) {
		int result = -1;
		TravelReviewVO travelRevw = null;
		try {
			log.info("Start");
			result = travelDao.updateTravelRevw(tr);
			if(result > 0) {
				travelRevw = travelDao.selectTravelRevw(tr.getTrvlRevwId());
			}
		} catch (Exception e) {
			log.info("ERROR", e);
			throw new RuntimeException(e);
		}
		log.info("update travel review id : " + travelRevw.getTrvlRevwId());
		return new ResponseEntity<TravelReviewVO>(travelRevw, HttpStatus.OK);
	}
	
	@ApiOperation(value="여행지 후기 삭제 API 입니다.")
	@DeleteMapping(value="/reviews/review-entry/{trvlRevwId}")
	public ResponseEntity<Integer> deleteTravelRevw(@PathVariable int trvlRevwId) {
		int result = -1;
		try {
			log.info("Start");
			result = travelDao.deleteTravelRevw(trvlRevwId);
		} catch (Exception e) {
			log.info("ERROR", e);
			throw new RuntimeException(e);
		}
		if(result > 0) {
			log.info("delete travel review id : " + trvlRevwId);
		}
		return new ResponseEntity<Integer>(trvlRevwId, HttpStatus.OK);
	}
	
	@ApiOperation(value="나라/도시 선택 API 입니다.")
	@PostMapping(value="/destination")
	public ResponseEntity<CountryVO> searchCnC(@RequestBody CountryVO cntry) {
		CountryVO country = null;
		List<TravelVO> travels = null;
		
		try {
			log.info("Start");
			country = travelDao.selectCountry(cntry);
			travels = travelDao.selectTravelByClstr(cntry);
			country.setTravels(travels);
		} catch (Exception e) {
			log.info("ERROR", e);
			throw new RuntimeException(e);
		}
		
		return new ResponseEntity<CountryVO>(country, HttpStatus.OK); 
	}
	
	@ApiOperation(value="회원 id 검색 API입니다.")
	@GetMapping(value="/reviews/user/{userId}")
	public ResponseEntity<List<TravelVO>> searchMyReview(@PathVariable int userId) {
		List<TravelVO> list = null;
		
		try {
			log.info("Start");
			list = travelDao.searchTravelByUserId(userId);
		} catch (Exception e) {
			log.info("ERROR", e);
			throw new RuntimeException(e);
		}
		return new ResponseEntity<List<TravelVO>>(list, HttpStatus.OK);
	}
}
