package web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JiraLogWork {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		inputLogWork("SEDDS-1267");

	}

	public static void inputLogWork(String issue){
		
		Map<String,String> postData = new HashMap<>();
		postData.put("timeSpentSeconds", "28800");
		//postData.put("visibility", "{ \"type\": \"group\", \"value\": \"jira-users\" }");
		postData.put("comment", "Construction and Unit Tests");
		postData.put("started", "2019-07-10T17:50:35.082+0000");
		
		String jsonData = 
				"{\"timeSpentSeconds\": 28800, " +
				" \"comment\": \"Development and Unit Tests\"} ";
			
		String base64login = "aGNhcnZhbGhvZjo0SGgyNjQ1Nzgw"; // MDM5MDYzMzI0MDM6aDI2NDU3ODA=
		
		String url = "https://jira.indra.es/rest/api/2/issue/"+issue+"/worklog";
		
		Document doc;
		try {
			doc = Jsoup.connect(url)
					.header("Authorization", "Basic " + base64login)
					//.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.userAgent("Mozilla")
					.ignoreHttpErrors(true)
					.requestBody(jsonData)
					.timeout(10000) 
					.ignoreContentType(true)
					.post();
			
			System.out.println(doc.text());
			
			/*
			 * doc.getElementsByTag("entry").forEach(e ->
			 * System.out.println(e.getElementsByIndexEquals(0).get(0)) );
			 */
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}

/**
curl --request POST \
  --url '/rest/api/3/issue/{issueIdOrKey}/worklog' \
  --header 'Authorization: Bearer <access_token>' \
  --header 'Accept: application/json' \
  --header 'Content-Type: application/json' \
  --data '{
  "timeSpentSeconds": 28800,
  "visibility": {
    "type": "group",
    "value": "jira-users"
  },
  "comment": {
    "type": "doc",
    "version": 1,
    "content": [
      {
        "type": "paragraph",
        "content": [
          {
            "text": "Construction and Unit Tests",
            "type": "text"
          }
        ]
      }
    ]
  },
  "started": "2019-07-10T17:18:47.243+0000"
}' 
*/

/*
 "timeSpentSeconds": 28800, 
	"visibility": {"type": "group", "value": "jira-users" }, 
	"hours": 8,
	"dateIn": "10/07/2019",
	"dateEn": "10/07/2019",
	"time": 16:37,
	"remainigEstimate": "auto",
	"phase": 6,
	"userName":	"De+Carvalho+Fonseca,+Halisson",
	"userKey": "hcarvalhof",
	"started": "2019-07-10T12:46:47.243+0000" 
 */
