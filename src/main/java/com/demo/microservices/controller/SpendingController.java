package com.demo.microservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.microservices.dao.SpendingDao;
import com.demo.microservices.model.SampleUser;
import com.demo.microservices.model.SpendingVO;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class SpendingController {
	
	@Autowired
	SpendingDao spendingDao;
	

	
	@ApiOperation(value="나의 결제내역 전체 조회 pay_info")
	@GetMapping(value="/spending/payment/list")
	public ResponseEntity <List<SpendingVO>> selectSpendingPaymentAll(@PathVariable String userId){
		
		List<SpendingVO> list = null;
		try {
			log.info("나의 결제내역 전체 조회 :: userId => "+userId);
			list = spendingDao.selectSpendingPaymentAll(userId);
			
		} catch (Exception e) {
			
			log.error("Error",e);
			throw new RuntimeException(e);
		}
		
		return new ResponseEntity<List<SpendingVO>> (list, HttpStatus.OK);
	}
	
	
	
	
	@ApiOperation(value="나의 결제내역 중 한개 상세조회 pay_info")
	@GetMapping(value="/spending/payment/{payId}")
	public ResponseEntity <SpendingVO> selectSpendingPayment(@PathVariable String payId){
		SpendingVO spend = null;
		try {
			log.info("나의 결제내역 중 한개 상세조회 :: payId => "+payId);
			spend = spendingDao.selectSpendingPayment(payId);
		}catch(Exception e) {
			log.error("ERROR",e);
			throw new RuntimeException(e);
		}
		
		return new ResponseEntity<SpendingVO> (spend, HttpStatus.OK);

	}
	
	

	@ApiOperation(value="나의 여행별 결제내역 전체 조회 pay_trvl_info")
	@GetMapping(value="/spending/travel/list")
	public ResponseEntity <List<SpendingVO>> selectSpendingTravelAll(@PathVariable String trvlId){
		
		List<SpendingVO> list = null;
		try {
			log.info("나의 여행별 결제내역 전체 조회 :: trvlId => "+trvlId);
			list = spendingDao.selectSpendingTravelAll(trvlId);
			
		} catch (Exception e) {
			
			log.error("Error",e);
			throw new RuntimeException(e);
		}
		
		return new ResponseEntity<List<SpendingVO>> (list, HttpStatus.OK);
	}
	
	
	

	@ApiOperation(value="여행별 결제내역 중 한개 상세조회 trvl_pay_info")
	@GetMapping(value="/spending/travel/{trvlPayId}")
	public ResponseEntity <SpendingVO> selectSpendingTravel(@PathVariable String trvlPayId){
		SpendingVO spend = null;
		try {
			log.info("여행별 결제내역 중 한개 상세조회 :: trvlPayId => "+trvlPayId);
			spend = spendingDao.selectSpendingTravel(trvlPayId);
		}catch(Exception e) {
			log.error("ERROR",e);
			throw new RuntimeException(e);
		}
		
		return new ResponseEntity<SpendingVO> (spend, HttpStatus.OK);
		
	}
	
	/*


//	@PostMapping(value="/spending/payment")
	int insertSpending(SpendingVO spending); // 결제내역 등록

//	@PutMapping(value="/spending/payment")
	int updateSpending(SpendingVO spending); // 결제내역 수정

//	@DeleteMapping(value="/spending/payment")
	int deleteSpending(String trvlPayId); //결제내역 삭제
	*/

	@ApiOperation(value="결제내역 등록 pay_info")
	@PostMapping(value="/spending/payment")
	public ResponseEntity <String> insertSpendingPayment(@RequestBody SpendingVO spending){
		int rc = 0;
		String msg = null;
		
		try {
			log.info("결제내역 등록 pay_info");
			spendingDao.insertSpendingPayment(spending);
		} catch (Exception e) {
			log.error("ERROR",e);
			throw new RuntimeException(e);
		}
		log.info("add payment rc:"+rc);
			
		if (rc>0) {
			msg = "등록 성공";
		}
		return new ResponseEntity<String> (msg, HttpStatus.OK);
	}
	
	@ApiOperation(value="결제내역 수정 pay_info")
	@PutMapping(value="/spending/payment")
	public ResponseEntity <String> updateSpendingPayment(@RequestBody SpendingVO spending){
		int rc = 0;
		String msg = null;
		
		try {
			log.info("결제내역 수정 pay_info");
			rc = spendingDao.updateSpendingPayment(spending);
		} catch (Exception e) {
			log.error("ERROR",e);
			throw new RuntimeException(e);
		}
		log.info("update spending rc:"+rc);
			
		if (rc>0) {
			msg = "등록 성공";
		}
		return new ResponseEntity<String> (msg, HttpStatus.OK);
	}
	
	@ApiOperation(value="결제내역 삭제 pay_info")
	@DeleteMapping(value="/spending/payment/{payId}")
	public ResponseEntity <String> deleteSpendingPayment(@PathVariable String payId){
		int rc = 0;
		String msg = null;
		
		try {
			log.info("결제내역 삭제 pay_info");
			rc = spendingDao.deleteSpendingPayment(payId);
		} catch (Exception e) {
			log.error("ERROR",e);
			throw new RuntimeException(e);
		}
		log.info("delete spending rc:"+rc);
			
		if (rc>0) {
			msg = "삭제 성공";
		}
		return new ResponseEntity<String> (msg, HttpStatus.OK);
	}
	

	@ApiOperation(value="여행별 결제내역 등록 pay_trvl_info")
	@PostMapping(value="/spending/travel")
	public ResponseEntity <String> insertSpendingTravel(@RequestBody SpendingVO spending){
		int rc = 0;
		String msg = null;
		
		try {
			log.info("여행별 결제내역 등록 pay_trvl_info");
			spendingDao.insertSpendingTravel(spending);
		} catch (Exception e) {
			log.error("ERROR",e);
			throw new RuntimeException(e);
		}
		log.info("add payment rc:"+rc);
			
		if (rc>0) {
			msg = "등록 성공";
		}
		return new ResponseEntity<String> (msg, HttpStatus.OK);
	}
	
	@ApiOperation(value="여행별 결제내역 수정 pay_trvl_info")
	@PutMapping(value="/spending/travel")
	public ResponseEntity <String> updateSpendingTravel(@RequestBody SpendingVO spending){
		int rc = 0;
		String msg = null;
		
		try {
			log.info("여행별 결제내역 수정 pay_trvl_info");
			rc = spendingDao.updateSpendingTravel(spending);
		} catch (Exception e) {
			log.error("ERROR",e);
			throw new RuntimeException(e);
		}
		log.info("update spending rc:"+rc);
			
		if (rc>0) {
			msg = "등록 성공";
		}
		return new ResponseEntity<String> (msg, HttpStatus.OK);
	}
	
	@ApiOperation(value="여행별 결제내역 삭제 pay_trvl_info")
	@DeleteMapping(value="/spending/travel/{trvlPayId}")
	public ResponseEntity <String> deleteSpendingTravel(@PathVariable String trvlPayId){
		int rc = 0;
		String msg = null;
		
		try {
			log.info("여행별 결제내역 삭제 pay_trvl_info");
			rc = spendingDao.deleteSpendingTravel(trvlPayId);
		} catch (Exception e) {
			log.error("ERROR",e);
			throw new RuntimeException(e);
		}
		log.info("delete spending rc:"+rc);
			
		if (rc>0) {
			msg = "삭제 성공";
		}
		return new ResponseEntity<String> (msg, HttpStatus.OK);
	}
	
}
	
	
