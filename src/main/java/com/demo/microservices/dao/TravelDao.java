package com.demo.microservices.dao;

import java.util.List;

import com.demo.microservices.model.TravelVO;

public interface TravelDao {
	
	List<TravelVO> recommendTravel(TravelVO travel);
	
	List<TravelVO> searchAllTravel(); // 전체 
	
	List<TravelVO> searchTravel(String keyword); // 이름 검색
	
	List<TravelVO> searchTravel(TravelVO travel); // 상세 검색
	
	TravelVO selectTravel(int travelID); // 후기 선택 
	
	TravelVO insertTravel(TravelVO travel); // 후기 작성 
	
	TravelVO updateTravel(TravelVO travel); // 후기 수정 
	
	int deleteTravel(int travelID); // 후가 삭제 
}
