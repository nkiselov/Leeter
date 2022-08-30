import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class LoadLeet {
    private static final String cookie = "87b5a3c3f1a55520_gr_cs1=NikitaK20546; 87b5a3c3f1a55520_gr_last_sent_cs1=NikitaK20546; 87b5a3c3f1a55520_gr_last_sent_sid_with_cs1=32b82f29-677b-48e7-885d-fec1e02ba895; 87b5a3c3f1a55520_gr_session_id=32b82f29-677b-48e7-885d-fec1e02ba895; 87b5a3c3f1a55520_gr_session_id_32b82f29-677b-48e7-885d-fec1e02ba895=true; _ga=GA1.2.538346854.1659483729; _gid=GA1.2.859329344.1660756859; gr_user_id=c588bc00-0309-4d0a-b1ec-2fb0f88619b8; csrftoken=Xlop2euJalNzSrnzJ55OV6UlQKdhxVBDLAPyIOOUHCUZXmxfLpNn5JthV6YOeN5t; NEW_PROBLEMLIST_PAGE=1; _gat=1; __atuvc=16%7C31%2C7%7C32%2C11%7C33%2C23%7C34; LEETCODE_SESSION=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfYXV0aF91c2VyX2lkIjoiMzU4MDYwMCIsIl9hdXRoX3VzZXJfYmFja2VuZCI6ImRqYW5nby5jb250cmliLmF1dGguYmFja2VuZHMuTW9kZWxCYWNrZW5kIiwiX2F1dGhfdXNlcl9oYXNoIjoiYmQ2NjRhOWNmMzJmMTIwYWM5MTMxMjRiNGQxYjUyOTdlYmMzZDI1MiIsImlkIjozNTgwNjAwLCJlbWFpbCI6Im5pa2l0YUB2YW1yYWQuY29tIiwidXNlcm5hbWUiOiJOaWtpdGFLMjA1NDYiLCJ1c2VyX3NsdWciOiJOaWtpdGFLMjA1NDYiLCJhdmF0YXIiOiJodHRwczovL3MzLXVzLXdlc3QtMS5hbWF6b25hd3MuY29tL3MzLWxjLXVwbG9hZC9hc3NldHMvZGVmYXVsdF9hdmF0YXIuanBnIiwicmVmcmVzaGVkX2F0IjoxNjYxMzU3Mzc3LCJpcCI6IjE0MS4xNTguNjEuMjA2IiwiaWRlbnRpdHkiOiJjNDBjMGFjMWIzY2VhODQ5NjM5M2JkNjIxNWZkMDVlZCIsInNlc3Npb25faWQiOjI1NDY1MzU2LCJfc2Vzc2lvbl9leHBpcnkiOjEyMDk2MDB9.cUhU5GNf262WZcrv_w-yy7RFmZiY5XnEKplZsBAeyjc";

    public static void main(String[] args) throws IOException{
        URL url = new URL("https://leetcode.com/graphql/");
        HttpsURLConnection http = (HttpsURLConnection) url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Cookie",cookie);
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{\"query\":\"\\n    query problemsetQuestionList($categorySlug: String, $limit: Int, $skip: Int, $filters: QuestionListFilterInput) {\\n  problemsetQuestionList: questionList(\\n    categorySlug: $categorySlug\\n    limit: $limit\\n    skip: $skip\\n    filters: $filters\\n  ) {\\n    total: totalNum\\n    questions: data {\\n      acRate\\n      difficulty\\n      likes\\n    dislikes\\n   frontendQuestionId: questionFrontendId\\n      isFavor\\n      paidOnly: isPaidOnly\\n      status\\n      title\\n      titleSlug\\n      topicTags {\\n        name\\n        id\\n        slug\\n      }\\n      hasSolution\\n      hasVideoSolution\\n    }\\n  }\\n}\\n    \",\"variables\":{\"categorySlug\":\"\",\"skip\":0,\"limit\":3000,\"filters\":{}}}";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        OutputStream os = new FileOutputStream(new File("result.json"));
        http.getInputStream().transferTo(os);
        http.disconnect();
    }
}
