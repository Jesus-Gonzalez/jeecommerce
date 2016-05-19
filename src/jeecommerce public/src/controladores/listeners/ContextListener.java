package controladores.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.stripe.Stripe;

public class ContextListener
implements ServletContextListener
{	
	@Override
	public void contextInitialized(ServletContextEvent sce)
	{
		// No hacer nada
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce)
	{	
		// No hacer nada
	}
}
