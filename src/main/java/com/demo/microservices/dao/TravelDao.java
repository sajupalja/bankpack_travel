package com.demo.microservices.dao;

import java.util.List;

import com.demo.microservices.model.TravelReviewVO;
import com.demo.microservices.model.TravelVO;

public interface TravelDao {
	
	List<TravelVO> recommendTravel(TravelVO travel);
	
	List<TravelVO> searchAllTravel(); // 전체 
	
	List<TravelVO> searchTravel(String keyword); // 이름 검색
	
	List<TravelVO> searchTravel(TravelVO travel); // 상세 검색
	
	TravelVO selectTravel(int travelID); // 여행게시글 선택 
	
	TravelVO insertTravel(TravelVO travel); // 여행게시글 작성 
	
	TravelVO updateTravel(TravelVO travel); // 여행게시글 수정 
	
	int deleteTravel(int travelID); // 여행게시글 삭제
	
	List<TravelReviewVO> selectTravelRevw(int travelID); // 해당 여행 게시글 전체 후기
	
	TravelReviewVO insertTravelRevw(TravelReviewVO travelRevw); // 여행후기 작성 
	
	TravelReviewVO updateTravelRevw(TravelReviewVO travelRevws); // 여행후기 수정 
	
	int deleteTravelRevw(int travelRevwID); // 여행후기 삭제
}
