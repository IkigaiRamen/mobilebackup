/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import static com.codename1.ui.CN.CENTER;
import static com.codename1.ui.CN.LEFT;
import static com.codename1.ui.CN.RIGHT;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import models.TestEntity;
import models.User;
import services.QuizzService;
import java.util.ArrayList;
/**
 *
 * @author zewaf
 */
public class AllCertifsForm extends Form {
    public AllCertifsForm(Resources res){
        
        super (new BorderLayout());
        
        Container item = new Container(BoxLayout.y());
        item.setScrollableY(true);

        ArrayList<TestEntity> tests = QuizzService.getInstance().getAllCertifs();
        Dimension d = new Dimension(Display.getInstance().getDisplayHeight(), Display.getInstance().getDisplayWidth());
        item.setSize(d);
        Label lblHeader = new Label("Les certifications Disponibles");
        //lblHeader.;
        item.add(lblHeader);
        try {
            for (TestEntity t : tests) {
               // System.out.println(p);
                item.add(addRow(res,t));
                Container line = new Container(new FlowLayout(CENTER));
                Dimension dim = new Dimension(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight()/120);
                
               line.setSize(dim); //setPreferredSize(dim);
               item.add(line);
            }
        } catch (Exception e) {
            System.out.println(e);
        }


        this.add(CENTER, item);

        Label spacer1 = new Label();
        Label spacer2 = new Label();


    }

    private Component addRow(Resources res, TestEntity t ) {

        String title = t.getTitre();
        int duree = t .getDuree();
        int nbrTentative = t.getNbrTentative();
        
        int height = Display.getInstance().convertToPixels(20f);
        int width = Display.getInstance().convertToPixels(20f);
        //cnt.setLeadComponent(image);
        Label lblTitre = new Label(title);
        lblTitre.setUIID("NewsTopLine");
        lblTitre.setAlignment(Component.CENTER);

        Label lDuree = new Label(String.valueOf(t.getDuree()));
        lDuree.setUIID("NewsBottomLine");
        FontImage.setMaterialIcon(lDuree, FontImage.MATERIAL_HOURGLASS_BOTTOM);
        Container cnt = BorderLayout.west(lDuree);

        //lDuree.setTextPosition(RIGHT);
        Label lblTentative = new Label( String.valueOf(t.getNbrTentative()), "NewsBottomLine");
        //FontImage.setMaterialIcon(lblTentative, FontImage.MATERIAL_CHAT);

        Button btnPasser = new Button("Passer");

        Container L1 = new Container(new BorderLayout());
        Container centerPart = new Container(new BorderLayout());
        //centerPart.set // make width max or expanded or chfamam fel khra adhaya 
        centerPart.add(RIGHT, lblTentative);
        centerPart.add(LEFT, lblTitre);
        
        L1.add(CENTER, centerPart);
        L1.add(RIGHT, btnPasser);

        cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(
                L1
        ));

      //  cnt.add(s);
//       Container line = new Container();
////        line.setSize(Display.getInstance().convertToPixels(1f),Display.getInstance().convertToPixels(100f));
////        line.setBackground(Color.GRAY);
//        //cnt.add(line);
//        line.setSize(new Dimension(Display.getInstance().convertToPixels(1f),Display.getInstance().convertToPixels(100f)));
        

        btnPasser.addActionListener((e)->{
            System.out.println(t.getTitre());
             new PasserTestForm(res,t).show();
        });

        return cnt;
    }
    
    
    
    
}



/*
        super(BoxLayout.y());
        QuizzService qService= QuizzService.getInstance();
        ArrayList<TestEntity> tests = qService.getAllTests();
        
        Container container= new Container(BoxLayout.y());
        Container contUsername= (FlowLayout.encloseIn(
                new TextField("","Quizz"),
                new TextField("","Question")

        ));
        Button passer =new Button("Passer");
        contUsername.add(passer);
        passer.addActionListener(evt -> {

        });
        add(contUsername);*/