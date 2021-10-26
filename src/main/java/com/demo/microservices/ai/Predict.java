package com.demo.microservices.ai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;

import com.demo.microservices.dao.TravelDao;
import com.demo.microservices.model.TravelSurveyVO;

public class Predict {
	@Autowired
	TravelDao travelDao;
	
	public String jsonParser(String jsonString) throws org.json.simple.parser.ParseException {
		
		JSONParser jsonParser = new JSONParser();
		Object obj = jsonParser.parse(jsonString);
		JSONObject jsonObj = (JSONObject) obj;
		
		JSONArray  jsonObj2 = (JSONArray )jsonObj.get("predictions");
		JSONObject values2 = (JSONObject) jsonObj2.get(0);
		
		JSONArray values3 = (JSONArray) values2.get("values");
		JSONArray result = (JSONArray)values3.get(0);
		Long resultVal = (Long) result.get(0);
		
		return resultVal.toString();
	}
	
	// Cluster 값 돌려주는 예측 메소드
	public String predictCluster(TravelSurveyVO vo) throws IOException, ParseException, org.json.simple.parser.ParseException {
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
            System.out.println(payload);
            writer.write(payload);
            writer.close();


            scoringBuffer = new BufferedReader(new InputStreamReader(scoringConnection.getInputStream()));
            StringBuffer jsonStringScoring = new StringBuffer();
            String lineScoring;
            
            while ((lineScoring = scoringBuffer.readLine()) != null) {
                jsonStringScoring.append(lineScoring);
                
            }	
            System.out.println(jsonStringScoring.toString());
            cluster = jsonParser(jsonStringScoring.toString());
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
