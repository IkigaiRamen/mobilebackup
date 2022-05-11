/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
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
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import forms.SideMenuBaseForm;
import forms.StatsForm;
import models.ChoixEntity;
import models.QuestionEntity;
import models.ReponseEntity;
import models.TestEntity;
import services.ReponseService;
import java.util.ArrayList;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author zewaf
 */
public class PasserTestForm extends SideMenuBaseForm{
    private int m;
    private int s = 59;
    Timer timer;
    TestEntity test;
    Label lblTimer; 
    ArrayList<ButtonGroup> allRadioGroups = new ArrayList();
    ArrayList<ChoixEntity> allChoix = new ArrayList();    
    Resources res;

    
    public PasserTestForm(Resources ress, TestEntity t) {
        super(BoxLayout.y());
        res=ress;
        test = t;
        m = test.getDuree()-1;
        timer = new Timer();
        TimerTask task = new Helper();
        timer.schedule(task, 1000, 1000);
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image mask = res.getImage("round-mask.png");
        lblTimer = new Label(String.valueOf(test.getDuree()-1) + ":59", "Title");
        //lblTitre.setMask(mask.createMask());
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        
        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label(test.getTitre(), "Title")
                                    //new Label(String.valueOf(test.getDuree()), "SubTitle")
                                )
                            ).add(BorderLayout.EAST, lblTimer)
                        //GridLayout.encloseIn(2, remainingTasks, completedTasks)
                );
        
        //FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        //fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
        //fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
        tb.setTitleComponent(titleCmp);
                        
        ArrayList<QuestionEntity> questions = test.getQuestions();
        for(int i=0; i< questions.size();i++)
        {
            addQuestion( questions.get(i), i+1, 0xd997f1, i==0);
        }
        Button btnTerminer = new Button("Terminer");
        btnTerminer.addActionListener((e)->{
            System.out.println("btnTerminer PRESSED");
            traiterReponses();
        });
        add(btnTerminer);
        /*addButtonBottom("Design app illustrations", 0x5ae29d, false);
        addButtonBottom("Javascript training ", 0x4dc2ff, false);
        addButtonBottom("Surprise Party for Matt", 0xffc06f, false);*/
        setupSideMenu(res);
        
        
}    
    class Helper extends TimerTask {

    // TimerTask.run() method will be used to perform the action of the task
     
    public void run() {
        String time;
        if (m >= 0) {
            if(m == test.getDuree())
                m -= 1;
            
            if(s>0)
                s-= 1;
            else{
                s = 59;
                m -=1;
                if(m == -1){
                    tempsFini();
                }
            }
            time = m + ":" + s;
            
            if(s<10)
                time = m + ":0" + s;
            if(m<10)
                time = "0" + time;
            
            final String timeFinal = time;
            lblTimer.setText(timeFinal);
            //lblTimer.setText( m + ":" + s);
            //System.out.println(m + ":" + s);
            
        }    
    }
    }
    private void addQuestion(QuestionEntity q, int compteur,int color, boolean first) {
        //MultiButton finishLandingPage = new MultiButton("Question N°" +compteur + ":" );
        /*finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(),  first));
        finishLandingPage.setIconUIID("Container");*/
        
        Label lblQues = new Label("Question N°" +compteur + ":" );
        add(FlowLayout.encloseIn(lblQues));
        
        TextArea lblEnonce = new TextArea(q.getEnonce());
        lblEnonce.setEditable(false);
        add(lblEnonce);
        
        
        ArrayList<ChoixEntity> choices = q.getChoices();
        allChoix.addAll(choices);
        
        RadioButton r1 = new RadioButton(choices.get(0).getContenu());
        r1.setUIID(String.valueOf(choices.get(0).getIdChoix()));
        RadioButton r2 = new RadioButton(choices.get(1).getContenu());
        r2.setUIID(String.valueOf(choices.get(1).getIdChoix()));
        RadioButton r3 = new RadioButton(choices.get(2).getContenu());
        r3.setUIID(String.valueOf(choices.get(2).getIdChoix()));
        ButtonGroup btnGrp = new ButtonGroup(r1, r2, r3);
       allRadioGroups.add(btnGrp);
       add(r1).add(r2).add(r3);
    }
    
    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if(first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
    }

    @Override
    protected void showOtherForm(Resources res) {
        new StatsForm(res).show();
    }
    public void traiterReponses(){
        ArrayList<String> reponses = new ArrayList();
        for(ButtonGroup grp: allRadioGroups){
            
            if(grp.getSelected() != null){
                reponses.add(grp.getSelected().getUIID());
                System.out.println(grp.getSelected().getUIID());
            }
        }
        int userId = 7;   //HARDCODED GET CURRENT USER
        Map<String,Object> map = ReponseService.getInstance().addReponse(7,test.getIdTest(),reponses);
        //get the results from symfony then give data to the next page succeed or fail
            new ResultTestForm(res,map).show();
        
    }
    
    public void tempsFini(){
        traiterReponses();
    }

    
}

















/*public void traiterReponses(){  BRFORE CHANGE
        int score = 0;
        for(ButtonGroup grp: allRadioGroups){
            
            if(grp.getSelected() != null){
                System.out.println(grp.getSelected().getUIID());
                ChoixEntity ch = new ChoixEntity();
                ch.setIdChoix( Integer.parseInt(grp.getSelected().getUIID()));
                ChoixEntity choice = allChoix.get(allChoix.indexOf(ch));
                ReponseEntity reponse = new ReponseEntity();
                reponse.setChoix(choice);
                reponse.setTest(test);
                reponse.setIdUser(7);               ///////////////////////////////////////////////////INSERT CURRENT USER ID
                reponse.setCorrect(choice.isCorrect());
                
                
                //ReponseService rs = new ReponseService();
                //rs.ajouterReponse(reponse);
                
                
                //System.out.println(rSelected.getId()  + " rrrrrrrrrrrrrrrrr "+rSelected.getText());
                if(reponse.isCorrect())
                    score++;
            }
        }
    }*/
    

