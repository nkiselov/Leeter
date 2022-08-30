import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashSet;

public class Question {
    double acRate;
    Difficulty difficulty;
    int likes;
    int dislikes;
    boolean isFavor;
    boolean paidOnly;
    Status status;
    String title;
    String titleSlug;
    HashSet<String> topicTags;
    boolean hasSolution;
    boolean hasVideoSolution;
    double score;

    //{"acRate":48.62187685462263,"difficulty":"Easy","freqBar":null,"frontendQuestionId":"1","isFavor":false,"paidOnly":false,"status":"ac","title":"Two Sum","titleSlug":"two-sum",
    //{"topicTags":[{"name":"Array","id":"VG9waWNUYWdOb2RlOjU=","slug":"array"},{"name":"Hash Table","id":"VG9waWNUYWdOb2RlOjY=","slug":"hash-table"}],"hasSolution":true,"hasVideoSolution":true}
    public Question(JSONObject obj){
        acRate = obj.getNumber("acRate").doubleValue();
        difficulty = Difficulty.valueOf(obj.getString("difficulty").toUpperCase());
        likes = obj.getInt("likes");
        dislikes = obj.getInt("dislikes");
        isFavor = obj.getBoolean("isFavor");
        paidOnly = obj.getBoolean("paidOnly");
        if(obj.isNull("status")){
            status = Status.TODO;
        }else{
            status = Status.valueOf(obj.getString("status").toUpperCase());
        }
        title = obj.getString("title");
        titleSlug = obj.getString("titleSlug");
        topicTags = new HashSet<>();
        JSONArray arr = obj.getJSONArray("topicTags");
        for(int i=0; i<arr.length(); i++){
            topicTags.add(arr.getJSONObject(i).getString("name"));
        }
        hasSolution = obj.getBoolean("hasSolution");
        hasVideoSolution = obj.getBoolean("hasVideoSolution");
        score = calcScore();
    }

    private double calcScore(){
        return 2*likes/(double)dislikes+acRate/100;
    }
}
//        query problemsetQuestionList($categorySlug: String, $limit: Int, $skip: Int, $filters: QuestionListFilterInput) {
//            problemsetQuestionList: questionList(
//                    categorySlug: $categorySlug
//            limit: $limit
//            skip: $skip
//            filters: $filters
//  ) {
//                total: totalNum
//                questions: data {
//                    acRate
//                            difficulty
//                    freqBar
//                    frontendQuestionId: questionFrontendId
//                            isFavor
//                    paidOnly: isPaidOnly
//                            status
//                    title
//                            titleSlug
//                    topicTags {
//                        name
//                                id
//                        slug
//                    }
//                    hasSolution
//                            hasVideoSolution
//                }
//            }
//        }
//