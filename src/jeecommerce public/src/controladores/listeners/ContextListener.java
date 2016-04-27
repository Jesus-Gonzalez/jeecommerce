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
		Stripe.apiKey = "sk_test_6YxNZaAlxoCr9J8oK6PY0J6U";
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce)
	{	
		// No hacer nada
	}
}
