package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mychange extends JPanel {
    private JLabel Lname,Lcomment;
    private JTextField name,comment;
    private JButton add,del;

    public Mychange(){

        Init();
        Add();
    }

    public JButton getAdd(){return this.add;}
    public JButton getDel(){return this.del;}
    public JTextField get_Comment(){return this.comment;}
    public JTextField get_Name(){return this.name;}

    public void Init(){
        Lname = new JLabel("name");
        Lcomment = new JLabel("comment");
        name = new JTextField();
        comment = new JTextField();
        name.setColumns(8);
        comment.setColumns(15);
        add = new JButton("+");
        del = new JButton("-");
    }
    public void Add(){
        this.add(add);
        this.add(del);
        this.add(Lname);
        this.add(name);
        this.add(Lcomment);
        this.add(comment);
    }
    public void Clear(){
        this.name.setText("");
        this.comment.setText("");
    }



}
