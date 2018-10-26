
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class MouseInput implements MouseListener {

	private Display display;
	
	public MouseInput(Display display) {
        this.display = display;
        display.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        int x = e.getX() / 80;
        int y = e.getY() / 80;
        System.out.println("mouse click: " + e.getX() + ", " + e.getY());
        System.out.println("highlightX, highlightY: " + x + " " + y);

        //display.drawHighlight(x, y);

        display.repaint();    //after everyclick repaint
    }

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
}
