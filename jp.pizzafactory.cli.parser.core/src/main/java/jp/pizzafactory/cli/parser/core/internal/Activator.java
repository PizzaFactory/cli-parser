package jp.pizzafactory.cli.parser.core.internal;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "jp.pizzafactory.cli.parser.core";

	// The shared instance
	private static Activator plugin;

	boolean started;

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		started = true;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		started = false;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}
}
