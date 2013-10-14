package pe.com.hydra.reapro.portlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
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

public class BPMInboxPortlet extends GenericPortlet {

	public static String NAMESPACE = "http://www.ibm.com/wps/accelerators/utl/taskLaunching";

	@Override
	protected void doView(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {

		getListOfParameterNames(request);

		response.setContentType("textxhtml");
		//response.getWriter().println("Hello World");
		PortletPreferences preferences = request.getPreferences();
		// Hostname of the IBM Business Process Manager server
		String hostname = preferences.getValue("bpm.hostname",
				"bpm8.onp.gob.pe");
		System.out.println("hostname=" + hostname);
		// IBM Business Process Manager server port number
		int port = Integer.parseInt(preferences.getValue("bpm.port", "9080"));
		System.out.println("port=" + port);

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
		getListOfParameterNames(request);
		response.setRenderParameters(request.getParameterMap());

	}

	private void getListOfAttributeNames(PortletRequest request) {
		Enumeration e = request.getAttributeNames();
		System.out.println("=================================");
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			System.out.println("-" + key);
		}
		System.out.println("=================================");
	}

	private void getListOfParameterNames(PortletRequest request) {
		Enumeration e = request.getParameterNames();
		System.out.println("=================================");
		System.out.println("========Parameter Names==========");
		System.out.println("=================================");
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			System.out.println("-" + key);
		}
		System.out.println("=================================");
	}

}