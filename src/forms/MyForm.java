package forms;

import com.codename1.ui.events.ActionEvent;

public class MyForm extends com.codename1.ui.Form {
    public MyForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public MyForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

    public void onButtonActionEvent(ActionEvent ev) {
    }

//////-- DON'T EDIT BELOW THIS LINE!!!
    protected com.codename1.ui.Container gui_Table_Layout = new com.codename1.ui.Container(new com.codename1.ui.table.TableLayout(3, 3));
    protected com.codename1.ui.Label gui_Label = new com.codename1.ui.Label();
    protected com.codename1.ui.TextField gui_TextField = new com.codename1.ui.TextField();
    protected com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    protected com.codename1.ui.TextField gui_TextField_1 = new com.codename1.ui.TextField();
    protected com.codename1.ui.Label gui_Label_2 = new com.codename1.ui.Label();
    protected com.codename1.ui.TextField gui_TextField_2 = new com.codename1.ui.TextField();
    protected com.codename1.ui.Button gui_Button = new com.codename1.ui.Button();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void guiBuilderBindComponentListeners() {
        EventCallbackClass callback = new EventCallbackClass();
        gui_Button.addActionListener(callback);
    }

    class EventCallbackClass implements com.codename1.ui.events.ActionListener, com.codename1.ui.events.DataChangedListener {
        private com.codename1.ui.Component cmp;
        public EventCallbackClass(com.codename1.ui.Component cmp) {
            this.cmp = cmp;
        }

        public EventCallbackClass() {
        }

        public void actionPerformed(com.codename1.ui.events.ActionEvent ev) {
            com.codename1.ui.Component sourceComponent = ev.getComponent();

            if(sourceComponent.getParent().getLeadParent() != null && (sourceComponent.getParent().getLeadParent() instanceof com.codename1.components.MultiButton || sourceComponent.getParent().getLeadParent() instanceof com.codename1.components.SpanButton)) {
                sourceComponent = sourceComponent.getParent().getLeadParent();
            }

            if(sourceComponent == gui_Button) {
                onButtonActionEvent(ev);
            }
        }

        public void dataChanged(int type, int index) {
        }
    }
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        guiBuilderBindComponentListeners();
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
        setScrollableY(true);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("MyForm");
        setName("MyForm");
        gui_Table_Layout.setPreferredSizeStr("84.32103mm 60.108364mm");
                gui_Table_Layout.setInlineStylesTheme(resourceObjectInstance);
        gui_Table_Layout.setName("Table_Layout");
        addComponent(gui_Table_Layout);
        gui_Label.setText("Name");
                gui_Label.setInlineStylesTheme(resourceObjectInstance);
        gui_Label.setName("Label");
                gui_TextField.setInlineStylesTheme(resourceObjectInstance);
        gui_TextField.setName("TextField");
        gui_Label_1.setText("Email");
                gui_Label_1.setInlineStylesTheme(resourceObjectInstance);
        gui_Label_1.setName("Label_1");
                gui_TextField_1.setInlineStylesTheme(resourceObjectInstance);
        gui_TextField_1.setName("TextField_1");
        gui_Label_2.setText("Phone");
                gui_Label_2.setInlineStylesTheme(resourceObjectInstance);
        gui_Label_2.setName("Label_2");
                gui_TextField_2.setInlineStylesTheme(resourceObjectInstance);
        gui_TextField_2.setName("TextField_2");
        gui_Button.setText("Add");
                gui_Button.setInlineStylesTheme(resourceObjectInstance);
        gui_Button.setInlineAllStyles("bgType:none;");
        gui_Button.setName("Button");
        com.codename1.ui.table.TableLayout.Constraint LabelConstraint = ((com.codename1.ui.table.TableLayout)gui_Table_Layout.getLayout()).createConstraint(0, 0);
        gui_Table_Layout.addComponent(LabelConstraint, gui_Label);
        com.codename1.ui.table.TableLayout.Constraint TextFieldConstraint = ((com.codename1.ui.table.TableLayout)gui_Table_Layout.getLayout()).createConstraint(0, 1);
        gui_Table_Layout.addComponent(TextFieldConstraint, gui_TextField);
        com.codename1.ui.table.TableLayout.Constraint Label_1Constraint = ((com.codename1.ui.table.TableLayout)gui_Table_Layout.getLayout()).createConstraint(1, 0);
        gui_Table_Layout.addComponent(Label_1Constraint, gui_Label_1);
        com.codename1.ui.table.TableLayout.Constraint TextField_1Constraint = ((com.codename1.ui.table.TableLayout)gui_Table_Layout.getLayout()).createConstraint(1, 1);
        gui_Table_Layout.addComponent(TextField_1Constraint, gui_TextField_1);
        com.codename1.ui.table.TableLayout.Constraint Label_2Constraint = ((com.codename1.ui.table.TableLayout)gui_Table_Layout.getLayout()).createConstraint(2, 0);
        gui_Table_Layout.addComponent(Label_2Constraint, gui_Label_2);
        com.codename1.ui.table.TableLayout.Constraint TextField_2Constraint = ((com.codename1.ui.table.TableLayout)gui_Table_Layout.getLayout()).createConstraint(2, 1);
        gui_Table_Layout.addComponent(TextField_2Constraint, gui_TextField_2);
        com.codename1.ui.table.TableLayout.Constraint ButtonConstraint = ((com.codename1.ui.table.TableLayout)gui_Table_Layout.getLayout()).createConstraint(2, 2);
        gui_Table_Layout.addComponent(ButtonConstraint, gui_Button);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Table_Layout.getParent().getLayout()).setInsets(gui_Table_Layout, "22.391857% -6.7727737mm auto auto").setReferenceComponents(gui_Table_Layout, "-1 -1 -1 -1").setReferencePositions(gui_Table_Layout, "0.0 0.0 0.0 0.0");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
