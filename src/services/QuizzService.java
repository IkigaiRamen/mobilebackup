package services;


import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import models.ChoixEntity;
import models.QuestionEntity;
import models.TestEntity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;



/**
 *
 * @author zewaf
 */
public class QuizzService {
    
    String test;
    TestEntity testEntity = new TestEntity();
    ArrayList<TestEntity> tests = new ArrayList<>();
    //ArrayList<QuestionEntity>  questions = new ArrayList<>();




    public static QuizzService instance = null;
    public boolean resultOK;
    //private URLConnection con ;
    private ConnectionRequest req ;

    private QuizzService() {
        req = new ConnectionRequest();
    }

    public static QuizzService getInstance() {
        if (instance == null) {
            instance = new QuizzService();
        }
        return instance;
    }

    public  ArrayList<TestEntity> getAllTests(){
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"alltest";

        req.setUrl(url);
        req.setPost(false);
        //req.addArgument("Sender", "mobile");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                 tests = parseTests( new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });


        NetworkManager.getInstance().addToQueueAndWait(req);
        return tests;
    }
    public  ArrayList<TestEntity> getAllCertifs(){
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"certifs";

        req.setUrl(url);
        req.setPost(false);
        //req.addArgument("Sender", "mobile");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                 tests = parseTests( new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });


        NetworkManager.getInstance().addToQueueAndWait(req);
        return tests;
    }
    
    public ArrayList<TestEntity> parseTests(String jsonText){
        ArrayList<TestEntity> testsList = new ArrayList<>();
        //ArrayList<QuestionEntity>   questionsList =new ArrayList<>();
        //ArrayList<ChoixEntity> choicesList =new ArrayList<>();
        try {

            JSONParser j = new JSONParser();
            Map<String,Object> testsListJson =j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)testsListJson.get("root");

            for(Map<String,Object> obj : list){
                ArrayList<QuestionEntity>   questionsList =new ArrayList<>();
                TestEntity t = new TestEntity();

                t.setIdTest((int) Double.parseDouble(obj.get("id").toString()));
                t.setTitre(obj.get("titre").toString());
                t.setType(obj.get("type").toString());
                t.setDuree((int) Double.parseDouble(obj.get("duree").toString()));
                t.setNbrTentative((int) Double.parseDouble(obj.get("nbrtentative").toString()));
                //t.setUserId((int) Double.parseDouble(obj.get("price").toString()));

                
                List<Map<String,Object>> listQuestions = (List<Map<String,Object>>)obj.get("questions");
                    //System.out.println("aaaaaaaaaaaa" +listQuestions.toString());

                for(Map<String,Object> quest : listQuestions){
                    
                    ArrayList<ChoixEntity> choicesList =new ArrayList<>();
                    QuestionEntity q = new QuestionEntity();
                    q.setIdQuestion((int) Double.parseDouble(quest.get("id").toString()));
                    q.setEnonce(quest.get("enonce").toString());
                    q.setTest(t);
                    //System.out.println("bbbbbbbbbbb" +q.toString());
                    List<Map<String,Object>> listChoix = (List<Map<String,Object>>)quest.get("choices");
                    //System.out.println("ccccccccccc" +quest.toString());
                    for(Map<String,Object> choix : listChoix){
                        ChoixEntity ch = new ChoixEntity();
                        ch.setIdChoix((int) Double.parseDouble(choix.get("id").toString()));
                        ch.setContenu(choix.get("contenu").toString());
                        ch.setCorrect(Boolean.parseBoolean(choix.get("correct").toString()));
                        ch.setQuestion(q);
                        choicesList.add(ch);
                    }
                    q.setChoices(choicesList);
                    questionsList.add(q);
                }
                t.setQuestions(questionsList);
                testsList.add(t);
            }


        } catch (IOException ex) {

        }
        //System.out.println(testsList.toString());
        return testsList;
    }
    

}
