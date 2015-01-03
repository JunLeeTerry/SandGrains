package jtf.tutorial.ui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import jtf.tutorial.Activator;
import jtf.tutorial.ColorManager;
import jtf.tutorial.actions.TextViewerOperationHandler;
import jtf.tutorial.textdecoration.SyntaxChecker;
import jtf.tutorial.tripleclick.ITextTripleClickStrategy;

import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.IHandlerActivation;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.texteditor.AnnotationPreference;
import org.eclipse.ui.texteditor.DefaultMarkerAnnotationAccess;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.texteditor.MarkerAnnotationPreferences;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;

public class ExprViewer extends SourceViewer {
	/**
	 * Triple click strategy mouse listener
	 */
	private class TripleClickStrategyConnector extends MouseAdapter {
		private long doubleClickTime;

		public TripleClickStrategyConnector() {
			doubleClickTime = 0;
		}

		@Override
		public void mouseDoubleClick(MouseEvent e) {
			doubleClickTime = System.currentTimeMillis();
		}

		@Override
		public void mouseDown(MouseEvent e) {
			// compare time interval with threshold
			if (System.currentTimeMillis() - doubleClickTime <= TRIPLE_CLICK_THRESHOLD) {
				ITextTripleClickStrategy strategy = (ITextTripleClickStrategy) selectContentTypePlugin(
				        getSelectedRange().x, tripleClickStrategies);
				if (strategy != null) {
					strategy.tripleClicked(ExprViewer.this);
				}
			}

			// clear double click time to avoid trigger triple click more than once
			doubleClickTime = 0;
		}
	}
	
	// How long we can wait for triple click after double click
	public static final long TRIPLE_CLICK_THRESHOLD = 500;
	
	// Triple click strategy mapping
	Map<String, ITextTripleClickStrategy> tripleClickStrategies;
	
	// shared command handlers
	static Map<String, IHandler> handlers = new HashMap<String, IHandler>();
	
	// handler service
	IHandlerService handlerService = (IHandlerService)PlatformUI.getWorkbench().getService(IHandlerService.class);
	
	// command handler activations
	Map<IHandler, IHandlerActivation> handlerActivations = new HashMap<IHandler, IHandlerActivation>();
	
	// decoration support for compute source viewer
	SourceViewerDecorationSupport decorationSupport;

	public ExprViewer(Composite parent, IVerticalRuler ruler, int styles) {
		super(parent, ruler, styles);
	}

	public ExprViewer(Composite parent, IVerticalRuler verticalRuler,
	        IOverviewRuler overviewRuler, boolean showAnnotationsOverview,
	        int styles) {
		super(parent, verticalRuler, overviewRuler, showAnnotationsOverview,
		        styles);
	}
	
	@Override
	public void activatePlugins()
	{
		super.activatePlugins();

		// install triple click adapter
		getTextWidget().addMouseListener(new TripleClickStrategyConnector());
	}
	
	@Override
	public void configure(SourceViewerConfiguration configuration) {
	    super.configure(configuration);
	    
		if(configuration instanceof IExprViewerConfiguration) {
			// get custom extension interface
			IExprViewerConfiguration extension = (IExprViewerConfiguration)configuration;
			
			// install triple click strategy
			String[] types = configuration.getConfiguredContentTypes(this);
			for(int i = 0; i < types.length; i++)
			{
				String t = types[i];
				setTextTripleClickStrategy(extension.getTripleClickStrategy(this, t), t);
			}
		}
		
		// configure decoration support
		configureDecorationSupport();
		
		// create command handlers
		createHandlers();
		
		// install syntax checker
		SyntaxChecker checker = new SyntaxChecker(this);
		checker.install();
	}

	/**
	 * Set triple click strategy for a content type
	 */
	public void setTextTripleClickStrategy(ITextTripleClickStrategy strategy, String contentType) {
		if (strategy != null) {
			if (tripleClickStrategies == null)
				tripleClickStrategies = new HashMap<String, ITextTripleClickStrategy>();
			tripleClickStrategies.put(contentType, strategy);
		} else if (tripleClickStrategies != null)
			tripleClickStrategies.remove(contentType);
	}
	
	/**
	 * Create command handlers
	 */
	protected void createHandlers() {
		// content assist
		IHandler handler = new TextViewerOperationHandler(this, ISourceViewer.CONTENTASSIST_PROPOSALS);
		handlers.put(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS, handler);
		
		// activate handlers
		activateHandlers();
	}

	/**
	 * Activate all handlers
	 */
	protected void activateHandlers() {
		// if handler service is null, return
		if (handlerService == null)
			return;

		// activate handlers if it is not active
		Iterator<String> i = handlers.keySet().iterator();
		while (i.hasNext()) {
			String id = i.next();
			IHandler handler = handlers.get(id);
			IHandlerActivation activation = handlerActivations.get(handler);
			if (activation == null) {
				activation = handlerService.activateHandler(id, handler);
				handlerActivations.put(handler, activation);
			}
		}
	}
	
	@Override
	protected void handleDispose() {
		if(handlerService != null) {
			for(IHandlerActivation activation : handlerActivations.values())
				handlerService.deactivateHandler(activation);
		}
		
		// dispose decoration support
		if(decorationSupport != null) {
			decorationSupport.dispose();
			decorationSupport = null;
		}
		
	    super.handleDispose();
	}
	
	/**
	 * Configure decoration support, decoration is used to display editing box,
	 */
	protected void configureDecorationSupport()
	{
		// create support object
		decorationSupport = new SourceViewerDecorationSupport(this, null, new DefaultMarkerAnnotationAccess(), ColorManager.getInstance());

		// add other annotation preference
		MarkerAnnotationPreferences prefs = new MarkerAnnotationPreferences();
		MarkerAnnotationPreferences.initializeDefaultValues(Activator.getDefault().getPreferenceStore());
		Iterator<Object> e = prefs.getAnnotationPreferences().iterator();
		while(e.hasNext()) {
			// add to support
			decorationSupport.setAnnotationPreference((AnnotationPreference)e.next());
		}

		// install support
		decorationSupport.install(Activator.getDefault().getPreferenceStore());
	}
}
