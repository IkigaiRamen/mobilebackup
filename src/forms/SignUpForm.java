package forms;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import forms.LoginForm;
import models.User;

import java.io.IOException;
import java.util.List;

public class SignUpForm extends Form {
    public SignUpForm(Resources res){

        super(BoxLayout.y());
        //AuthentificationService authentificationService=new AuthentificationService();
        Container container= new Container(BoxLayout.y());
        Container contUsername= (FlowLayout.encloseIn(
                new TextField("","Username"),
                new TextField("","Email"),
                new TextField("","Password",20,TextArea.PASSWORD),
                new TextField("","Confirm password",20,TextArea.PASSWORD)

        ));
        Button signUp=new Button("Sign up");
        contUsername.add(signUp);
        signUp.addActionListener(evt -> {
            User user=new User();
            /*try {
                List<Component> list=contUsername.getChildrenAsList(false);
                for(Component comp : list){
                    if (comp instanceof TextField) {
                        System.out.println(((TextField) comp).getHint());
                        switch (((TextField) comp).getHint()){
                            case "Username": user.setUsername(((TextField) comp).getText());break;
                            case "Email": user.setEmail(((TextField) comp).getText());break;
                            case "Password": user.setPassword(((TextField) comp).getText());break;
                            case "Confirm password": user.setConfirm_password(((TextField) comp).getText());break;
                            default:
                                throw new IllegalStateException("Unexpected value: " + ((TextField) comp).getHint());
                        }
                    }
                }
                if (authentificationService.signUp(user)){
                    new LoginForm(res).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        });
        add(contUsername);
    }
}
