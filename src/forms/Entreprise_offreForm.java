/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.codename1.components.MultiButton;
import com.codename1.ui.Component;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import models.offre;
import services.Offre_service;
/**
 *
 * @author Wassef
 */
public class Entreprise_offreForm  extends Form  {
Resources theme = UIManager.initFirstTheme("/theme");
    public Entreprise_offreForm(Form previous) {
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
        // rechherhchhe
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
        
        
        
        
        
        
        
             this.getToolbar().addCommandToOverflowMenu("Add Offre", null, ev -> {
               Form ajout = new Form( "Add Offre", BoxLayout.y());
             
               TextField titre = new TextField("", "titre", 20, TextArea.ANY);
               TextField description = new TextField("", "description", 20, TextArea.ANY);
                 TextField edu = new TextField("", "edu", 20, TextArea.ANY);
                TextField responsibilities = new TextField("", "responsibilities", 20, TextArea.ANY);
               TextField type = new TextField("", "type", 20, TextArea.ANY);
                TextField exp = new TextField("", "exp", 20, TextArea.ANY);
               TextField qualifcation = new TextField("", "qualifcation", 20, TextArea.ANY);
               TextField city = new TextField("", "city", 20, TextArea.ANY);
               Button submit = new Button("Submit");
               //control saisir
    submit.addActionListener(aj
                    -> {
                    offre f=new offre(titre.getText(),description.getText(),edu.getText(),responsibilities.getText(),type.getText(),exp.getText(),qualifcation.getText(),city.getText());
                       System.out.println(f.toString());

                    new Offre_service().add_offre(f);
                new Entreprise_offreForm(this).show();
              
                
            }
            );
ajout.add("titre").add(titre).add("description").add(description).add("education").add(edu).add("responsibilities").add(responsibilities)
        .add("type").add(type).add("exp").add(exp).add("qualification").add(qualifcation).add("city").add(city).add(submit);
            ajout.show();
             });
        
        
        
        
        
        
        
    }
        public MultiButton addIteam_offre(offre c) {
        MultiButton m = new MultiButton();
       
  
        m.setTextLine1(c.getTitre());
        m.setTextLine2(c.getDescription());
          
        m.setTextLine3(c.getType());
          
        m.setEmblem(theme.getImage("round.png"));
       
        m.addActionListener(l
                -> {

            Form f2 = new Form("DETAILS",BoxLayout.y());
                   f2.getToolbar().addCommandToLeftBar("back",null, (evt) -> {
                this.showBack();
            });
                   
                   Button Supprimer = new Button("Supprimer");
                   Button Modifier = new Button("Modifier");
                        Supprimer.addActionListener(ev
                    -> {
                new Offre_service().delete_offre(c.getId());
                new Entreprise_offreForm(this).showBack();
            }
            );
                        
                        
                        Modifier.addActionListener(mod
                    -> {
                            Form fmodifier = new Form("Modifer", BoxLayout.y());  
                             Button submit = new Button("Submit");
                              AutoCompleteTextField titre = new AutoCompleteTextField(c.getTitre());
                titre.setMinimumElementsShownInPopup(1);
                  AutoCompleteTextField contenu = new AutoCompleteTextField(c.getDescription());
                contenu.setMinimumElementsShownInPopup(1);
                fmodifier.add("titre").add(titre).add("description").add(contenu).add(submit);
                    submit.addActionListener(sub
                        -> {
                    new Offre_service().edit_offre(c.getId(), titre.getText(), contenu.getText());
                    new Entreprise_offreForm(this).showBack();

                }
                );

                fmodifier.show();
                        });
                        
                        
                        
              f2.add("titre").add(c.getTitre()).add("description").add(c.getDescription()).add("experience educationelle").add(c.getEduexp()).add("responsabilités: ").add(c.getResponsibilities()).add("modifier").add(Modifier).add("supprimer").add(Supprimer);
f2.show();
            
        }
        );
        return m;
    }
}
