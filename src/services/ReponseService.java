/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.File;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionListener;
import utils.Statics;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
//import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author zewaf
 */
public class ReponseService {
    
    public static ReponseService instance = null;
    public boolean resultOK;
    //private URLConnection con ;
    private ConnectionRequest req ;
    Map<String,Object> map;
    
    
    private ReponseService() {
        req = new ConnectionRequest();
    }

    public static ReponseService getInstance() {
        if (instance == null) {
            instance = new ReponseService();
        }
        return instance;
    }
    public Map<String,Object> addReponse(int userId, int testId, ArrayList<String> reponses) {
        
        String url = Statics.BASE_URL + "newreponse";

        req.setUrl(url);
        req.addArgument("user", String.valueOf(userId));
        req.addArgument("test", String.valueOf(testId));
        for(int i =0; i<reponses.size();i++){
            req.addArgument(i+"", reponses.get(i));
        }
        req.addArgument("nbrQuestion",String.valueOf(reponses.size()));


        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               //resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                map = parseResult( new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return map;
    }
    
    
    
    public Map<String,Object> parseResult(String jsonText){
        Map<String,Object> jsonData = new HashMap<>();
        //ArrayList<QuestionEntity>   questionsList =new ArrayList<>();
        //ArrayList<ChoixEntity> choicesList =new ArrayList<>();
        try {

            JSONParser j = new JSONParser();
            jsonData =j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
                //success score nbrQuestion data?
        } catch (IOException ex) {
            System.out.println(ex.getStackTrace());
        }
        return jsonData;
    }
}
