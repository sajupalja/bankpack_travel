package com.demo.microservices.ai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL; 
import org.json.simple.JSONObject;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;

import com.demo.microservices.dao.TravelDao;
import com.demo.microservices.model.TravelSurveyVO;

public class predict {
	@Autowired
	TravelDao travelDao;
	
	public String predict(TravelSurveyVO vo) throws IOException, ParseException {
		int id = vo.getUserId();
		int flag = travelDao.selectTravelRevwForId(id); 

		if(flag!=0) { // user가 작성한 후기가 없을 때
			String factor = vo.getTrvlMainFctr();
			if(factor.equals("1")) {
				vo.getTravelSurveyRateVO().setTotalFoodRate(0.4f);
				vo.getTravelSurveyRateVO().setTotalRoomRate(0.15f);
				vo.getTravelSurveyRateVO().setTotalActRate(0.15f);
				vo.getTravelSurveyRateVO().setTotalTrffRate(0.15f);
			}else if(factor.equals("2")) {
				vo.getTravelSurveyRateVO().setTotalRoomRate(0.4f);
				vo.getTravelSurveyRateVO().setTotalFoodRate(0.15f);
				vo.getTravelSurveyRateVO().setTotalActRate(0.15f);
				vo.getTravelSurveyRateVO().setTotalTrffRate(0.15f);
			}else if(factor.equals("3")) {
				vo.getTravelSurveyRateVO().setTotalActRate(0.4f);
				vo.getTravelSurveyRateVO().setTotalFoodRate(0.15f);
				vo.getTravelSurveyRateVO().setTotalRoomRate(0.15f);
				vo.getTravelSurveyRateVO().setTotalTrffRate(0.15f);
			}else { // factor == 4
				vo.getTravelSurveyRateVO().setTotalTrffRate(0.4f);
				vo.getTravelSurveyRateVO().setTotalFoodRate(0.15f);
				vo.getTravelSurveyRateVO().setTotalActRate(0.15f);
				vo.getTravelSurveyRateVO().setTotalRoomRate(0.15f);
			}
		}else { // user가 작성한 후기가 있을 때
			vo.setTravelSurveyRateVO(travelDao.selectAvgRate(flag));
		}
		// VO를 줘서 예측값 리턴하기
		return predictCluster(vo);
	}
	
	
	// Cluster 값 돌려주는 예측 메소드
	public String predictCluster(TravelSurveyVO vo) throws IOException, ParseException {
        // NOTE: you must manually set API_KEY below using information retrieved from your IBM Cloud account.

        String API_KEY = "3qTd-4IHD1LJ2kvGPXG10-j3Ujr29eoq1DNnQxd8VkFm";
        String cluster = null;

        HttpURLConnection tokenConnection = null;
        HttpURLConnection scoringConnection = null;
        BufferedReader tokenBuffer = null;
        BufferedReader scoringBuffer = null;
        try {
            // Getting IAM token
            URL tokenUrl = new URL("https://iam.cloud.ibm.com/identity/token?grant_type=urn:ibm:params:oauth:grant-type:apikey&apikey=" + API_KEY);
            tokenConnection = (HttpURLConnection) tokenUrl.openConnection();
            tokenConnection.setDoInput(true);
            tokenConnection.setDoOutput(true);
            tokenConnection.setRequestMethod("POST");
            tokenConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            tokenConnection.setRequestProperty("Accept", "application/json");
            tokenBuffer = new BufferedReader(new InputStreamReader(tokenConnection.getInputStream()));
            StringBuffer jsonString = new StringBuffer();
            String line;
            while ((line = tokenBuffer.readLine()) != null) {
                jsonString.append(line);
            }
            // Scoring request
//            URL scoringUrl = new URL("https://us-south.ml.cloud.ibm.com/ml/v4/deployments/824d5b2e-0101-40d3-b5c1-8dbf2009b975/predictions?version=2021-10-22&version=2021-10-22");
            URL scoringUrl = new URL("https://us-south.ml.cloud.ibm.com/ml/v4/deployments/6acfbf87-6439-420e-948b-5b5d2727a8a5/predictions?version=2021-10-22&version=2021-10-22");
            String iam_token = "Bearer " + jsonString.toString().split(":")[1].split("\"")[1];
            scoringConnection = (HttpURLConnection) scoringUrl.openConnection();
            scoringConnection.setDoInput(true);
            scoringConnection.setDoOutput(true);
            scoringConnection.setRequestMethod("POST");
            scoringConnection.setRequestProperty("Accept", "application/json");
            scoringConnection.setRequestProperty("Authorization", iam_token);
            scoringConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            OutputStreamWriter writer = new OutputStreamWriter(scoringConnection.getOutputStream(), "UTF-8");

            // NOTE: manually define and pass the array(s) of values to be scored in the next line
            String payload = "{" +
                    "\"input_data\": [\n" +
                    "{" +
                    "\"fields\": [\"cmpn_cnt\", \"trvl_pd\", \"total_pay_amt\", \"cmpn_type\", \"trvl_main_fctr\", \"total_room_rate\", \"total_food_rate\", \"total_trff_rate\", \"total_act_rate\", \"total_etc_rate\"]," +
                    "\"values\": [[" + 
                    vo.getCmpnCnt() +
                     "," + vo.getTrvlPd() +
                     "," + vo.getBudgetAmt() +
                     "," + vo.getCmpnType() +
                     "," + vo.getTrvlMainFctr() +
                     "," + vo.getTravelSurveyRateVO().getTotalRoomRate() +
                     "," + vo.getTravelSurveyRateVO().getTotalFoodRate() +
                     "," + vo.getTravelSurveyRateVO().getTotalTrffRate() +
                     "," + vo.getTravelSurveyRateVO().getTotalActRate() +
                     "," + vo.getTravelSurveyRateVO().getTotalEtcRate() +
                     "]]\n" +
                    "}" +
                    "]" +
                    "}";
            writer.write(payload);
            writer.close();


            scoringBuffer = new BufferedReader(new InputStreamReader(scoringConnection.getInputStream()));
            StringBuffer jsonStringScoring = new StringBuffer();
            String lineScoring;
            while ((lineScoring = scoringBuffer.readLine()) != null) {
                jsonStringScoring.append(lineScoring);
        		JSONObject jsonVar2 = new JSONObject();
        		JSONParser parser = new JSONParser(jsonStringScoring.toString());
        		jsonVar2 = (JSONObject) parser.parse();
        		cluster = (String) jsonVar2.get("values");
            }	
 //           System.out.println(jsonStringScoring);

        } catch (IOException e) {
            System.out.println("The URL is not valid.");
            System.out.println(e.getMessage());
        }
        finally {
            if (tokenConnection != null) {
                tokenConnection.disconnect();
            }
            if (tokenBuffer != null) {
                tokenBuffer.close();
            }
            if (scoringConnection != null) {
                scoringConnection.disconnect();
            }
            if (scoringBuffer != null) {
                scoringBuffer.close();
            }
        }
        
		return cluster;
	}
	
}
