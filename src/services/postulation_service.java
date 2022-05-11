/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import models.offre;
import utils.DataSource;
import utils.Statics;

/**
 *
 * @author Wassef
 */
public class postulation_service  {
         private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<offre> offres;
       public postulation_service() {
        request = DataSource.getInstance().getRequest();
    }
       
       
       
                public ArrayList<offre> add_postulation(int id_o,String contenu,String email,String psuedo)  {
        String url = Statics.BASE_URL + "postulation/"+id_o+"/"+email+"/"+contenu+"/"+psuedo;

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                offres = parseoffres(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return offres;
    }
       
       
       
       
       
       
       
       
       
       
     public ArrayList<offre> parseoffres(String jsonText) {
        
        try {
            offres = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) 
            {  
                
              int id = (int)Float.parseFloat(obj.get("id").toString());    
                
            String titre = obj.get("titre").toString(); 
             
            String contenu = obj.get("contenu").toString();     
          
            String created_at = obj.get("created_at").toString();  
            
            String update_at = obj.get("update_at").toString();
        
            


            
            

            
       //     offres.add(new offre( id,  titre,  contenu,  created_at,  update_at));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return offres;
    }
}
