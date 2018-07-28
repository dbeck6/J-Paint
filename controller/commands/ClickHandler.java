package controller.commands;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClickHandler implements MouseListener {

    // int coordinate values
    int x1, y1, x2, y2;

    @Override
    public void mouseClicked(MouseEvent e) {

    }


    @Override
    public void mousePressed(MouseEvent e) {
        x1 = e.getX(); y1 = e.getY();
        //System.out.println(x1 + ", " + y1);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        x2 = e.getX(); y2 = e.getY();
     //   System.out.println(x1 + ", " + y1);
     //   System.out.println(x2 + ", " + y2);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
