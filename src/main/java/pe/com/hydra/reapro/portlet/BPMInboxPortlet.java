package pe.com.hydra.reapro.portlet;

import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.json.JSONException;

import pe.com.hydra.reapro.client.ServiceRESTClient;
import bpm.rest.client.BPMClientException;
import bpm.rest.client.authentication.AuthenticationTokenHandlerException;

public class BPMInboxPortlet extends GenericPortlet {

	@Override
	protected void doView(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {

		response.setContentType("text/html");
		response.getWriter().println("Hello World");
		PortletPreferences preferences = request.getPreferences();
		// Hostname of the IBM Business Process Manager server
		String hostname = preferences.getValue("bpm.hostname",
				"bpm8.onp.gob.pe");
		// IBM Business Process Manager server port number
		int port = Integer.parseInt(preferences.getValue("bpm.port", "9080"));

		List taskList = null;

		try {
			ServiceRESTClient client = new ServiceRESTClient(hostname, port);
			taskList = client.obtenerBandejaDeTareas();

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AuthenticationTokenHandlerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BPMClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PortletSession portletSession;
		if (request.isRequestedSessionIdValid()) {
			System.out.println("session is valid");
			portletSession = request.getPortletSession(false);
		} else {
			System.out.println("session is not valid");
			portletSession = request.getPortletSession(true);
		}
		portletSession.setAttribute("taskList", taskList);

		getPortletContext().getRequestDispatcher("/WEB-INF/jsp/inbox.jsp")
				.include(request, response);

	}

	@Override
	public void processAction(ActionRequest request, ActionResponse response)
			throws PortletException, IOException {
		// TODO Auto-generated method stub
		super.processAction(request, response);
	}

}