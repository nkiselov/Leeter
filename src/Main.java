import org.json.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    private static final int START = 10;
    private static final int SKIP = 10;
    private static final int EASY = 0;
    private static final int MEDIUM = 2;
    private static final int HARD = 4;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("result.json"));
        StringBuilder b = new StringBuilder();
        String ln;
        while((ln=br.readLine())!=null){
            b.append(ln);
        }
        String json = b.toString();
        JSONObject obj = new JSONObject(json);
        JSONArray qarr = obj.getJSONObject("data").getJSONObject("problemsetQuestionList").getJSONArray("questions");
        Question[] questionsOrig = new Question[qarr.length()];
        for(int i=0; i<qarr.length(); i++){
            questionsOrig[i] = new Question(qarr.getJSONObject(i));
        }
        Arrays.sort(questionsOrig, (o1, o2) -> (int)Math.signum(o2.score-o1.score));
        Question[][] questions = new Question[][]{
                select(questionsOrig,EASY,SKIP,START,Difficulty.EASY),
                select(questionsOrig,MEDIUM,SKIP,START,Difficulty.MEDIUM),
                select(questionsOrig,HARD,SKIP,START,Difficulty.HARD)
        };
        for(int i=0; i<questions.length; i++){
            for(int j=0; j<questions[i].length; j++){
                openQuestion(questions[i][j]);
            }
        }
    }

    private static void openQuestion(Question q) throws IOException {
        String url = slug2url(q.titleSlug);
        System.out.println(url+"  ->  "+q.acRate+"%  "+q.likes+"/"+q.dislikes);
        execShell("open -a Safari "+url);
    }

    private static Question[] select(Question[] questions, int problems, int skip, int start, Difficulty difficulty){
        Question[] ret = new Question[problems];
        int r = 0;
        int j = 0;
        for(int i=0; i<questions.length && j<problems; i++){
            Question q = questions[i];
            if(q.difficulty == difficulty && !q.topicTags.contains("Database") && q.status != Status.AC && !q.paidOnly){
                r++;
                if(r>=start && (r-start)%skip==0){
                    ret[j] = q;
                    j++;
                }
            }
        }
        return ret;
    }

    private static String slug2url(String slug){
        return "https://leetcode.com/problems/"+slug;
    }

    private static void execShell(String cmd) throws IOException {
        Runtime.getRuntime().exec(new String[]{"/bin/bash","-c",cmd});
    }
}