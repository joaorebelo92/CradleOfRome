package modelo;

import java.awt.event.MouseEvent;

public interface PainelHandler {

	/* (non-Javadoc)
	 * @see modelo.PainelEventHandler#mouseDragged(java.awt.event.MouseEvent, int, int)
	 */
	public void mouseDragged(MouseEvent mouseEvent, int x, int y);

	/* (non-Javadoc)
	 * @see modelo.PainelEventHandler#mousePressed(java.awt.event.MouseEvent, int, int)
	 */
	public void mousePressed(MouseEvent mouseEvent, int x, int y);

	/* (non-Javadoc)
	 * @see modelo.PainelEventHandler#mouseMoved(java.awt.event.MouseEvent, int, int)
	 */
	public void mouseMoved(MouseEvent mouseEvent, int x, int y);

	/* (non-Javadoc)
	 * @see modelo.PainelEventHandler#mouseReleased(java.awt.event.MouseEvent, int, int)
	 */
	public void mouseReleased(MouseEvent mouseEvent, int x, int y);

	/* (non-Javadoc)
	 * @see modelo.PainelEventHandler#mouseExited(java.awt.event.MouseEvent)
	 */
	public void mouseExited(MouseEvent arg0);

}