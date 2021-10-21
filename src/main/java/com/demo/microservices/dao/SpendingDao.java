package com.demo.microservices.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.demo.microservices.model.SpendingVO;

@Mapper
public interface SpendingDao {
	
	List<SpendingVO> selectSpendingPaymentAll(String userId); //나의 결제내역 전체 조회 pay_info

	List<SpendingVO> selectSpendingTravelAll(String trvlId); //여행별 결제내역 조회 trvl_pay_info

	SpendingVO selectSpendingPayment(String payId); //나의 결제내역 중 한개 상세조회 pay_info

	SpendingVO selectSpendingTravel(String trvlPayId); //여행별 결제내역 조회 trvl_pay_info

	int insertSpendingPayment(SpendingVO spending); //결제내역 등록

	int updateSpendingPayment(SpendingVO spending); //결제내역 수정

	int deleteSpendingPayment(String payId); //결제내역 삭제
	
	int insertSpendingTravel(SpendingVO spending); //여행별 결제내역 등록
	
	int updateSpendingTravel(SpendingVO spending); //여행별 결제내역 수정
	
	int deleteSpendingTravel(String trvlPayId); //여행별 결제내역 삭제

}