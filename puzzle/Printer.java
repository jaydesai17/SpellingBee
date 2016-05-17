package puzzle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.Set;

public class Printer implements Printable {
	
	private Set<String> words;
	
	public Printer(Set<String> words) {
		this.words = words;
	}

	@Override
	public int print(Graphics graphics, PageFormat pf, int page) throws PrinterException {
		graphics.setColor(Color.black);
		int count = 0;
		for (String str: words) {
			graphics.drawString(str, (int) pf.getImageableX() + 10, (int) pf.getImageableY() + 12 * count);
			count++;
		}
	    if (page > 0) {
	         return NO_SUCH_PAGE;
	    }
	    return PAGE_EXISTS;
	}
}
