package jtf.tutorial;

import org.eclipse.swt.graphics.RGB;

/**
 * Color RGB constant, used in syntax highlight
 */
public interface IColorConstants
{
	// default color
	RGB DEFAULT = new RGB(0, 0, 0);
	
	// number color
	RGB INTEGER = new RGB(0x0, 0x0, 0xFF);
	
	// variable color
	RGB VARIABLE = new RGB(0x84, 0x00, 0x0);
}
