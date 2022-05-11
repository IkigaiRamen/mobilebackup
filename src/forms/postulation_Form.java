/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import static com.sun.javafx.scene.control.skin.FXVK.Type.EMAIL;
import models.offre;
import services.Offre_service;
import services.postulation_service;

/**
 *
 * @author Wassef
 */
public class postulation_Form  extends Form  {
Resources theme = UIManager.initFirstTheme("/theme");
private String imageqr="";
    public postulation_Form(Form previous) {
        super( "Offre", BoxLayout.y());
          this.add(new InfiniteProgress());
        Display.getInstance().scheduleBackgroundTask(() -> {
            // this will take a while...

            Display.getInstance().callSerially(() -> {
                this.removeAll();
         

                for (offre c : new Offre_service().getAllOffres()) {

                    this.add(addIteam_offre(c));
                }
               

                this.revalidate();
            });
        });
         this.getToolbar().addSearchCommand(e -> {
    String text = (String)e.getSource();
    if(text == null || text.length() == 0) {
        // clear search
        for(Component cmp : this.getContentPane()) {
            cmp.setHidden(false);
            cmp.setVisible(true);
        }
        this.getContentPane().animateLayout(150);
    } else {
        text = text.toLowerCase();
        for(Component cmp : this.getContentPane()) {
            MultiButton mb = (MultiButton)cmp;
            String line1 = mb.getTextLine1();
            String line2 = mb.getTextLine2();
            boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
                    line2 != null && line2.toLowerCase().indexOf(text) > -1;
            mb.setHidden(!show);
            mb.setVisible(show);
        }
        this.getContentPane().animateLayout(150);
    }
}, 4);
    }
     public MultiButton addIteam_offre(offre c) {
        MultiButton m = new MultiButton();
       
  
       // m.setTextLine1(c.getTitre());
      //  m.setTextLine2(c.getContenu());
          
     //   m.setTextLine3(c.getCreated_at());
          
        m.setEmblem(theme.getImage("round.png"));
       
        m.addActionListener(l
                -> {

            Form f2 = new Form("DETAILS",BoxLayout.y());
                   f2.getToolbar().addCommandToLeftBar("back",null, (evt) -> {
                this.showBack();
            });
                   
                   String urlab = "http://localhost/qrcode/qrcode.php";

                                ConnectionRequest cnreq = new ConnectionRequest();
                                cnreq.setPost(false);
                      //          String data = "Titre : " + c.getTitre() + "<br>  Contenu : " + c.getContenu() + " <br>  Created at :" + c.getCreated_at() + "  <br> Max : " + c.getUpdate_at()  + "<br> Merci pour votre confiance &#128525;";

                              //  cnreq.addArgument("data", data);
                                cnreq.setUrl(urlab);

                                cnreq.addResponseListener(evx
                                        -> {
                                    imageqr = new String(cnreq.getResponseData());
                                

                                }
                                );
                                NetworkManager.getInstance().addToQueueAndWait(cnreq);
                   
                     String url = "http://localhost/qrcode/" +imageqr;
                   
                  ImageViewer imgv;
                        Image img;
                        EncodedImage enc;
                        enc = EncodedImage.createFromImage(theme.getImage("round.png"), false);
                        img = URLImage.createToStorage(enc, url, url);
                        imgv = new ImageViewer(img);       
       //       f2.add("titre").add(c.getTitre()).add("contenu").add(c.getContenu()).add("Created at").add(c.getCreated_at()).add("max : ").add(c.getUpdate_at()).add(imgv).add("---------------");

              
                           TextField EMAIL = new TextField("", "E-Mail", 20, TextArea.EMAILADDR);
                                   TextField Psuedo = new TextField("", "Psuedo", 20, TextArea.ANY);
   TextField Contenu = new TextField("", "Contenu", 20, TextArea.ANY);

  String text_mail="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                
// val mail   
                            Validator val_mail = new Validator();
                            val_mail.addConstraint(EMAIL, new LengthConstraint(8));
                            val_mail.addConstraint(EMAIL, new RegexConstraint(text_mail, ""));
              
              
                
              Button submit = new Button("Submit");
                 submit.addActionListener(aj
                    -> {
                if (Psuedo.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Psuedo ", "OK", null);

                            }
                 else if (EMAIL.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de email ", "OK", null);

                            } else if (!val_mail.isValid()) {
                                Dialog.show("Erreur EMAIL !", "email incorrect", "OK", null);

                            } 
                 else if (Contenu.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Contenu ", "OK", null);

                            }  else {
                 new postulation_service().add_postulation(c.getId(),Contenu.getText(),EMAIL.getText(),Psuedo.getText());
                new postulation_Form(this).show();
                 Dialog.show("Postulation", "Postulation", "OK", null);
                }
                
            }
            );
            f2.add("postulation").add("psuedo").add(Psuedo).add("email").add(EMAIL).add("contenu").add(Contenu).add(submit);  
              
                  
              
              
              
              
              
              f2.show();
            
        }
        );
        return m;
    }
    
}
