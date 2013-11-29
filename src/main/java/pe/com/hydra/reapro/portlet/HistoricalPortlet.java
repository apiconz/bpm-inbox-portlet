package pe.com.hydra.reapro.portlet;

import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletSession;
import javax.portlet.ProcessAction;
import javax.portlet.ProcessEvent;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.xml.namespace.QName;

import org.json.JSONException;

import pe.com.hydra.reapro.client.ServiceRESTClient;
import bpm.rest.client.BPMClientException;
import bpm.rest.client.authentication.AuthenticationTokenHandlerException;

public class HistoricalPortlet extends GenericPortlet {

	public static String NAMESPACE = "http://www.ibm.com/wps/accelerators/utl/taskLaunching";

	@Override
	protected void doView(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {

		response.setContentType("text/html");
		PortletPreferences preferences = request.getPreferences();
		// Hostname of the IBM Business Process Manager server
		String hostname = preferences.getValue("bpm.hostname",
				"bpm8.onp.gob.pe");
		// IBM Business Process Manager server port number
		int port = Integer.parseInt(preferences.getValue("bpm.port", "9080"));

		List taskList = null;

		try {
			ServiceRESTClient client = new ServiceRESTClient(hostname, port);
			taskList = client.obtenerBandejaHistorico();

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

	@ProcessEvent(name = "refresh")
	public void reloadTaskList(EventRequest request, EventResponse response)
			throws PortletException, IOException {
		Event event = request.getEvent();

		System.out.println("Event Name-->" + event.getName());
		System.out.println("Event Value-->" + event.getValue());
	}

	@ProcessAction(name = "showTaskCoach")
	public void showTaskCoach(ActionRequest request, ActionResponse response)
			throws PortletException, IOException {

		String taskID = request.getParameter("selectedTaskID");
		String providerID = "tpi1378833781728";
		System.out.println("selectedTaskID =" + taskID);

		String xmlTaskID = "{\"TaskID\":\"" + taskID + "\",\"ProviderID\":\""
				+ providerID + "\"}";

		System.out.println("xmlTaskID=" + xmlTaskID);
		response.setEvent(new QName(NAMESPACE, "TaskSelection"), xmlTaskID);
		response.setRenderParameters(request.getParameterMap());

	}
}
