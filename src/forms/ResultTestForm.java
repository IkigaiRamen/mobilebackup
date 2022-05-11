/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import forms.SideMenuBaseForm;
import forms.StatsForm;
import models.ChoixEntity;
import models.QuestionEntity;
import models.TestEntity;
import forms.AllCertifsForm;
import services.ReponseService;
import java.util.ArrayList;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author zewaf
 */
public class ResultTestForm extends SideMenuBaseForm {

    public ResultTestForm(Resources res, Map<String,Object> map) {
        super(BoxLayout.y());
        
       
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        
        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label("Resultats", "Title")
                                    //new Label(String.valueOf(test.getDuree()), "SubTitle")
                                )
                            )
                        //GridLayout.encloseIn(2, remainingTasks, completedTasks)
                );
        
        tb.setTitleComponent(titleCmp);
        boolean success = Boolean.parseBoolean(map.get("success").toString());
        String txt1;
        String txt2;
        if(success){
            txt1 = "Felicitation Vous avez Réussi";
            
            
        }else{
            txt1 = "Malheureusement Vous n'avez pas Réussi";
        }
        txt2 = "votre score est : " + map.get("score") + "/" + map.get("nbrQuestion");
        
        
        Label lblTxt1 = new Label(txt1, "Title");              
        Label lblTxt2 = new Label(txt2, "Title");
        add(lblTxt1).add(lblTxt2);
        
        Button btnOk = new Button("d'accord");
        btnOk.addActionListener((e)->{
            System.out.println("btnOk PRESSED");
            new AllCertifsForm(res).show();
        });
        add(btnOk);
        /*addButtonBottom("Design app illustrations", 0x5ae29d, false);
        addButtonBottom("Javascript training ", 0x4dc2ff, false);
        addButtonBottom("Surprise Party for Matt", 0xffc06f, false);*/
        setupSideMenu(res);
        
        
}    



    @Override
    protected void showOtherForm(Resources res) {
        new StatsForm(res).show();
    }
}
