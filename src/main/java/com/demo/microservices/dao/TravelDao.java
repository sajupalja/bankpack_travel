package com.demo.microservices.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.demo.microservices.model.TravelReviewVO;
import com.demo.microservices.model.TravelVO;

@Mapper
public interface TravelDao {
	
	List<TravelVO> recommendTravel(TravelVO travel);
	
	List<TravelVO> searchAllTravel(); // 전체 
	
	List<TravelVO> searchTravel(String keyword); // 이름 검색
	
	List<TravelVO> searchDetailTravel(TravelVO travel); // 상세 검색
	
	TravelVO selectTravel(int travelID); // 여행게시글 선택 
	
	int insertTravel(TravelVO travel); // 여행게시글 작성 
	
	int updateTravel(TravelVO travel); // 여행게시글 수정 
	
	int deleteTravel(int travelID); // 여행게시글 삭제
	
	List<TravelReviewVO> selectAllTravelRevw(int travelID); // 해당 여행 게시글 전체 후기
	
	TravelReviewVO selectTravelRevw(int travelRevwID); // 여행게시글 선택 
	
	int insertTravelRevw(TravelReviewVO travelRevw); // 여행후기 작성 
	
	int updateTravelRevw(TravelReviewVO travelRevws); // 여행후기 수정 
	
	int deleteTravelRevw(int travelRevwID); // 여행후기 삭제
	
	int selectTravelRevwForId(int id); // 유저ID로 작성한 후기가 있는지 없는지 검색
	
}
