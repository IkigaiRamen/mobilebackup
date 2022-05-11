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
public class Offre_service {
         private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<offre> offres;
       public Offre_service() {
        request = DataSource.getInstance().getRequest();
    }
         public ArrayList<offre> getAllOffres()  {
        String url = Statics.BASE_URL + "offre/mobile/all";

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
            public ArrayList<offre> delete_offre(int id)  {
        String url = Statics.BASE_URL + "offres/mobile/"+id;

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
                public ArrayList<offre> add_offre(offre t) {
        String url = Statics.BASE_URL + "offre/mobile/new";

        request.setUrl(url);
        request.setPost(true);
        request.addArgument("titre", String.valueOf(t.getTitre()));
        request.addArgument("eduexp", String.valueOf(t.getEduexp()));
        request.addArgument("description",t.getDescription());
        request.addArgument("responsibilities", t.getResponsibilities());          
        request.addArgument("type", String.valueOf(t.getType()));
        request.addArgument("exp", String.valueOf(t.getExp()));
        request.addArgument("qualification",t.getQualification());
        request.addArgument("city", String.valueOf(t.getCity()));

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
                      public ArrayList<offre> edit_offre(int id,String titre,String description)  {
        String url = Statics.BASE_URL + "offre/mobile/"+id+"/edit/"+titre+"/"+description;
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
            
            String eduexp = obj.get("eduexp").toString(); 

            String description = obj.get("description").toString();     
          
            String responsibilities = obj.get("responsibilities").toString();  
            
            String type = obj.get("type").toString();
            
            String exp = obj.get("exp").toString();
            
            String qualification = obj.get("qualification").toString();

            String city = obj.get("city").toString();

                offres.add(new offre(titre,eduexp,  description,  responsibilities,  type,  exp,  qualification,  city));
            }
        } catch (IOException ex) {
                System.out.println("test");
            System.out.println(ex.getMessage());
        }

        return offres;
    }
    
}
