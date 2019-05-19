package GUI;

import Person.Person;
import javafx.scene.input.KeyCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Myperson extends JPanel{
    private JCheckBox select;
    private JTextField name,comment;
    private JButton in;
    private Person person;
    private String out;

    public Myperson(Person person){

        this.person = person;

        Init();
        Add();
        Connect();
    }


    private void Init(){
        select = new JCheckBox("选择");
        select.setSelected(person.getSelect());
        in = new JButton();
        in.setEnabled(select.isSelected());
        in.setSize(50,50);
        if(person.getIn()){
            in.setText("退出");
        }else{
            in.setText("加入");
        }
        name = new JTextField(person.getName());
        name.setColumns(8);
        comment = new JTextField(person.getComment());
        comment.setColumns(15);
        name.setEditable(false);
        comment.setEditable(false);
        this.setSize(200,100);
    }
    private void Add(){
        this.add(name,BorderLayout.NORTH);
        this.add(comment,BorderLayout.NORTH);
        this.add(select,BorderLayout.SOUTH);
        this.add(in,BorderLayout.EAST);
    }
    private void Connect(){
        select.addActionListener(new Monitor());
        in.addActionListener(new Monitor());
        name.addMouseListener(new MouseMonitor());
        comment.addMouseListener(new MouseMonitor());
        name.addKeyListener(new KeyMonitor());
        comment.addKeyListener(new KeyMonitor());
    }

    public boolean equals(Myperson p){
        if(p.getIn().equals(this.getIn()) && p.select.equals(this.select) &&
        p.person.equals(this.person) && p.name.equals(name) && p.comment.equals(comment)){
            return true;
        }else return false;
    }

    public String person_out(){
        return out;
    }

    public Person getPerson(){
        return this.person;
    }
    public JButton getIn(){return this.in;}
    public JCheckBox getSelect(){return this.select;}

    class Monitor implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == in){
                person.setIn(!person.getIn());
                if(person.getIn()){
                    in.setText("退出");
                }else{
                    in.setText("加入");
                }
            }else if(e.getSource()==select){
                person.setSelect(!select.isSelected());
                in.setEnabled(select.isSelected());
                if(!select.isSelected()){
                    person.setIn(false);
                    in.setText("加入");
                }
            }
        }
    }
    class MouseMonitor implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getClickCount() == 2){
                if(e.getSource()  == name){
                    name.setEditable(true);
                }else if(e.getSource() == comment) {
                    comment.setEditable(true);
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
    class KeyMonitor implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                name.setEditable(false);
                comment.setEditable(false);
                person.setName(name.getText());
                person.setComment(comment.getText());
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
