package jtf.tutorial;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

public final class Images {
	public static final String IMG_ERROR = "error"; //$NON-NLS-1$

	public static Image getImage(String key) {
		ImageRegistry registry = Activator.getDefault().getImageRegistry();

		// Load Image Descriptor if needed
		ImageHelper.getImageDescriptor(Activator.PLUGIN_ID, registry, key);
		Image image = registry.get(key);

		return image;
	}

	public static ImageDescriptor getImageDescriptor(String key) {
		ImageRegistry registry = Activator.getDefault().getImageRegistry();
		return ImageHelper.getImageDescriptor(Activator.PLUGIN_ID, registry,
		        key);
	}
}
