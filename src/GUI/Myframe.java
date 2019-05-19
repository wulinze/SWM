package GUI;

import Person.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;

public class Myframe extends JFrame {
    private JTextArea text = new JTextArea();
    private Vector<Myperson> personlist = new Vector<Myperson>();
    private Vector<Myperson> teamlist = new Vector<Myperson>();
    private Mychange mychange = new Mychange();
    private JPanel Mypanel = new JPanel();
    private JPanel mylist = new JPanel();
    private JScrollPane jsp;
    private MouseMonitor mm= new MouseMonitor();
    private Monitor m = new Monitor();

    public Myframe(){


        Init();
        Add();
        Connect();
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        this.setBounds(dim.width/2,dim.height/2,800,600);
    }

    public void Init(){
        mylist.setLayout(new BoxLayout(mylist,BoxLayout.Y_AXIS));
        mylist.setSize(200,100);
        Mypanel.setLayout(new BorderLayout());
        text = new JTextArea();
        text.setEditable(false);
        text.setForeground(Color.WHITE);
        text.setBackground(Color.BLACK);
        text.setFont(new Font("宋体",Font.BOLD,16));
        text.setSize(500,500);
        jsp = new JScrollPane(text);

        jsp.setBounds(300,300,text.getPreferredSize().width,text.getPreferredSize().height);
    }
    public void Add(){
        for (Myperson p:personlist){
            mylist.add(p);
        }
        Mypanel.add(mylist,BorderLayout.WEST);
        Mypanel.add(mychange,BorderLayout.NORTH);
        Mypanel.add(jsp,BorderLayout.CENTER);

        this.setContentPane(Mypanel);
    }
    public void Connect(){
        mychange.getAdd().addActionListener(m);
        mychange.getDel().addActionListener(m);
    }
    public void ListenAll(){
        for (Myperson p:personlist){
            p.getIn().removeActionListener(mm);
            p.getSelect().removeActionListener(mm);
        }
        for (Myperson p:personlist){
            p.getIn().addActionListener(mm);
            p.getSelect().addActionListener(mm);
        }
    }
    public void Show(){
        System.out.println("\nperson:" + personlist.size());
        System.out.println("teamate:" + teamlist.size() + "\n");
        mylist.removeAll();
        ListenAll();
        for (Myperson p:personlist){
            mylist.add(p);
        }
        Mypanel.add(mylist,BorderLayout.WEST);
        Mypanel.add(mychange,BorderLayout.NORTH);
        Mypanel.add(jsp,BorderLayout.CENTER);

        this.setContentPane(Mypanel);
    }

    public void addPerson(String name,String comment){
        personlist.add(new Myperson(new Person(name,comment)));
    }
    public void delPerson(String name){
        for(Myperson p:personlist){
            if(p.getPerson().getName().equals(name)){
                for(Myperson t:teamlist){
                    if(p.equals(t)){
                        teamlist.remove(t);
                    }
                }
                personlist.remove(p);
                return;
            }
        }
    }
    public void delPerson(String name,String comment){
        for(Myperson p:personlist){
            if(p.getPerson().getName().equals(name) && p.getPerson().getComment().equals(comment)){
                for(Myperson t:teamlist){
                    if(p.equals(t)){
                        teamlist.remove(t);
                    }
                }
                personlist.remove(p);
                return;
            }
        }
    }

    class Monitor implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == mychange.getAdd()){
                addPerson(mychange.get_Name().getText(),mychange.get_Comment().getText());
            }else if(e.getSource() == mychange.getDel()){
                if(mychange.get_Comment().getText().equals("")){
                    delPerson(mychange.get_Name().getText());
                }else{
                    delPerson(mychange.get_Name().getText(),mychange.get_Comment().getText());
                }
            }
            mychange.Clear();
            Show();
        }
    }
    class MouseMonitor implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            for (Myperson myperson : personlist) {
                if (e.getSource() == myperson.getSelect()) {
                    if (myperson.getSelect().isSelected()) {
                        teamlist.add(myperson);
                    } else {
                        for (Myperson t : teamlist) {
                            if (t.equals(myperson)) {
                                teamlist.remove(t);
                                text.append(t.getPerson().getName() + "退出团队\n");
                                break;
                            }
                        }
                    }
                    text.append(teamlist.size() + "人小队\n");
                } else if (e.getSource() == myperson.getIn()) {
                    if (myperson.getPerson().getIn()) {
                        text.append(myperson.getPerson().getName() + "离开了战斗\n");
                    } else {
                        text.append(myperson.getPerson().getName() + "遭受攻击\n");
                        for (Myperson t : teamlist) {
                            if (!t.equals(myperson)) {
                                text.append(t.getPerson().getName() + ':' + t.getPerson().getComment() + "\n");
                            }
                        }
                    }
                }
            }
            Show();
        }
    }
}
