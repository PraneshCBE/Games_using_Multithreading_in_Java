import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class kr_gui{

    public JFrame frame=new JFrame();
    public JButton b[]= new JButton[6];

    public kr_gui(String name) {
        frame.setTitle(String.format("Klotski : %s",name));
        frame.setSize(300,200);

        frame.setLayout(new GridLayout(2,3,6,6));

        for(int i=0;i<5;i++) {
            b[i]=new JButton(String.valueOf(i+1));
        }
        b[5]=new JButton(String.valueOf(0));

        for(int i=0;i<=5;i++) {
            frame.add(b[i]);
            b[i].setBackground(Color.DARK_GRAY);
            b[i].setForeground(Color.white);
        }


        b[5].setBackground(Color.WHITE);
        frame.setVisible(true);
    }

}
