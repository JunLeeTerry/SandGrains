package jtf.tutorial;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jface.text.source.ISharedTextColors;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/** 
 * ColorManager is used to cache colors so we don't need to create it every time
 */
public class ColorManager implements ISharedTextColors
{
	protected Map<RGB, Color> colorTable;

	/** singleton */
	private static ColorManager instance = null;

	/**
	 * Get singleton instance
	 * 
	 * @return
	 * 		ColorManager shared instance
	 */
	public static ColorManager getInstance()
	{
		if(instance == null)
			instance = new ColorManager();
		return instance;
	}

	/**
	 * Private constructor
	 */
	private ColorManager()
	{
		colorTable = new HashMap<RGB, Color>(10);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.source.ISharedTextColors#dispose()
	 */
	public void dispose()
	{
		Iterator<Color> e = colorTable.values().iterator();
		while(e.hasNext())
			e.next().dispose();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.source.ISharedTextColors#getColor(org.eclipse.swt.graphics.RGB)
	 */
	public Color getColor(RGB rgb)
	{
		Color color = (Color)colorTable.get(rgb);
		if(color == null)
		{
			color = new Color(Display.getCurrent(), rgb);
			colorTable.put(rgb, color);
		}
		return color;
	}
}
