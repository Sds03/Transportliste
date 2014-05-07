package View;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

import ru.atomation.jbrowser.impl.JBrowserComponent;
import ru.atomation.jbrowser.impl.JBrowserBuilder;
import ru.atomation.jbrowser.impl.JBrowserCanvas;
import ru.atomation.jbrowser.impl.JBrowserFrame;
import ru.atomation.jbrowser.impl.JComponentFactory;
import ru.atomation.jbrowser.interfaces.BrowserAdapter;
import ru.atomation.jbrowser.interfaces.BrowserManager;

/**

 */
public class Embedded {

	public String distance;
	public String destination;
	
	public Embedded(String distance, String destination){
		
		this.distance = distance;
		this.destination = destination;
	
	
	
	
		@SuppressWarnings("unchecked")
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		
		BrowserManager browserManager = new JBrowserBuilder()
				.buildBrowserManager();
		final JBrowserComponent<JFrame> browser = (JBrowserComponent<JFrame>) browserManager
				.getComponentFactory(JBrowserFrame.class).createBrowser();
		browser.getComponent().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//browser.getComponent().setSize((int) (screenSize.getWidth() ),
          //      (int) (screenSize.getHeight() ));
		browser.getComponent().setMinimumSize(new Dimension(1024, 768));
		browser.getComponent().setLocationRelativeTo(null);
		browser.getComponent().setVisible(true);
		browser.addBrowserListener(new BrowserAdapter() {
			@Override
			public boolean beforeOpen(String uri) {
				
				if (uri.startsWith("call:")) {
				 StringBuilder sb = new StringBuilder(uri);			
					System.out.println("uri: " + uri.toString());
					this.distance = String.uri.indexOf(':')
					return false;
				}
				return super.beforeOpen(uri);
			}

		});
		browser.setUrl("http://localhost/distances.html");
	}
}
