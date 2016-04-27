package controladores.websockets;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

public class WsChatEndpointConfig extends ServerEndpointConfig.Configurator
{
	// Obtenemos la sesión HTTP
    @Override
    public void modifyHandshake(ServerEndpointConfig config, 
                                HandshakeRequest request, 
                                HandshakeResponse response)
    {
        config.getUserProperties().put("sesion", (HttpSession) request.getHttpSession());
    }
}