package jtf.tutorial;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public final class ImageHelper {
	public static ImageDescriptor getSharedImageDescriptor(String key) {
		return PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(
		        key);
	}

	public static ImageDescriptor getImageDescriptor(String ID,
	        ImageRegistry registry, String key) {
		// Fetch image descriptor from registry
		ImageDescriptor imageDescriptor = registry.getDescriptor(key);
		if (imageDescriptor != null)
			return imageDescriptor;

		StringBuffer path = new StringBuffer();

		// Create path from Key. May already contain prefix and suffix
		appendBuffer(path, key, "images/", false);
		path.append(key);
		appendBuffer(path, key, ".gif", true);

		// Load descriptor from .gif file in /images directory of plugin
		try {
			imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(ID,
			        path.toString());
		} catch (RuntimeException ex) {
			URL imageURL = null;
			String cpath = "file:" + path.toString(); //$NON-NLS-1$
			try {
				imageURL = new URL(cpath);
			} catch (MalformedURLException ex1) {

			}
			if (imageURL == null) {
				return null;
			}

			imageDescriptor = ImageDescriptor.createFromURL(imageURL);
		}

		// Use missing image icon if not found
		if (imageDescriptor == null)
			imageDescriptor = ImageDescriptor.getMissingImageDescriptor();

		// Save Descriptor in registry & return
		registry.put(key, imageDescriptor);
		return imageDescriptor;
	}

	private static void appendBuffer(StringBuffer buffer, String text,
	        String key, boolean start) {
		if (!start && !text.startsWith(key))
			buffer.append(key);

		if (start && !text.endsWith(key))
			buffer.append(key);
	}

}
