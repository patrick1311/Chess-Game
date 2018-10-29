import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {
	private Display display;
	
	public MouseInput(Display display) {
        this.display = display;
        display.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX() / 80;
        int y = e.getY() / 80;
        //System.out.println("mouse click: " + e.getX() + ", " + e.getY());
        //System.out.println("highlightX, highlightY: " + x + " " + y);
        
        
        //display.drawHighlight(x, y);
        display.getGame().selectTile(new BoardCoordinate(x, y), display);
        display.repaint();    //after everyclick repaint
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