package com.demo.microservices.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.PathVariable;

import com.demo.microservices.model.CountryVO;
import com.demo.microservices.model.TravelReviewVO;
import com.demo.microservices.model.TravelSurveyRateVO;
import com.demo.microservices.model.TravelVO;

@Mapper
public interface TravelDao {
	
	List<TravelVO> recommendTravel(TravelVO travel); // 여행 추천 
	
	List<TravelVO> searchAllTravel(); // 여행게시글 전체 검색 
	
	List<TravelVO> searchTravel(String keyword); // 여행게시글 이름 검색
	
	List<TravelVO> searchDetailTravel(TravelVO travel); // 여행게시글 상세 검색
	
	TravelVO selectTravel(int travelID); // 여행게시글 선택 
	
	int insertTravel(TravelVO travel); // 여행게시글 작성 
	
	int updateTravel(TravelVO travel); // 여행게시글 수정 
	
	int deleteTravel(int travelID); // 여행게시글 삭제
	
	List<TravelReviewVO> selectAllTravelRevw(int travelID); // 해당 여행 게시글 전체 후기
	
	TravelReviewVO selectTravelRevw(int travelRevwID); // 여행후기 선택 
	
	int insertTravelRevw(TravelReviewVO travelRevw); // 여행후기 작성 
	
	int updateTravelRevw(TravelReviewVO travelRevws); // 여행후기 수정 
	
	int deleteTravelRevw(int travelRevwID); // 여행후기 삭제
	

	CountryVO selectCountry(CountryVO country); // 클러스터에 맞는 여행지 찾기
	
	List<TravelVO> selectTravelByClstr(CountryVO country); // 클러스터에 맞는 여행 후기 찾기
	
	List<TravelVO> searchTravelByUserId(int userId); // 해당 유저의 후기

}
