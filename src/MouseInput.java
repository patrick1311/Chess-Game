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
    }

	@Override
	public void mousePressed(MouseEvent e) {
		if(!display.isAnimating() && display.getGame().isRunning()) {
			int x = e.getX() / Display.TILE_SIZE;
	        int y = e.getY() / Display.TILE_SIZE;
	        //System.out.println("mouse click: " + e.getX() + ", " + e.getY());
	        //System.out.println("highlightX, highlightY: " + x + " " + y);

	        display.getGame().selectTile(new BoardCoordinate(x, y), display);
	        display.repaint();    //after everyclick repaint
    	}
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