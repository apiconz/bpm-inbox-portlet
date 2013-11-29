package pe.com.hydra.reapro.portlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletSession;
import javax.portlet.ProcessAction;
import javax.portlet.ProcessEvent;
import javax.portlet.RenderMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.xml.namespace.QName;

import org.json.JSONException;

import pe.com.hydra.reapro.client.ServiceRESTClient;
import bpm.rest.client.BPMClientException;
import bpm.rest.client.authentication.AuthenticationTokenHandlerException;

public class BPMInboxPortlet extends GenericPortlet {

	private static final String VIEW_JSP = "/WEB-INF/jsp/inbox.jsp";
	private static final String CONFIG_JSP = "/WEB-INF/jsp/config.jsp";
	private static final String EDIT_JSP = "/WEB-INF/jsp/edit.jsp";

	Logger logger = Logger.getLogger(BPMInboxPortlet.class.getName());
	
	public static String NAMESPACE = "http://www.ibm.com/wps/accelerators/utl/taskLaunching";

	@RenderMode(name="config")
	protected void doConfig(RenderRequest renderRequest, RenderResponse renderResponse)
			throws PortletException, IOException {
		include(CONFIG_JSP, renderRequest, renderResponse);
	}
	
	@RenderMode(name="edit")
	protected void doEdit(RenderRequest renderRequest, RenderResponse renderResponse)
			throws PortletException, IOException {
		include(EDIT_JSP, renderRequest, renderResponse);
	}
	
	@Override
	protected void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws PortletException, IOException {

		getListOfParameterNames(renderRequest);

		renderResponse.setContentType("text/html");
		PortletPreferences preferences = renderRequest.getPreferences();
		// Hostname of the IBM Business Process Manager server
		String hostname = preferences.getValue("bpm.hostname",
				"bpm8.onp.gob.pe");
		logger.info("hostname=" + hostname);
		// IBM Business Process Manager server port number
		int port = Integer.parseInt(preferences.getValue("bpm.port", "9080"));
		logger.info("port=" + port);

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
		if (renderRequest.isRequestedSessionIdValid()) {
			logger.info("session is valid");
			portletSession = renderRequest.getPortletSession(false);
		} else {
			logger.info("session is not valid");
			portletSession = renderRequest.getPortletSession(true);
		}
		portletSession.setAttribute("taskList", taskList);

		include(VIEW_JSP, renderRequest, renderResponse);
		

	}

	@ProcessEvent(name = "refresh")
	public void reloadTaskList(EventRequest request, EventResponse response)
			throws PortletException, IOException {
		Event event = request.getEvent();

		logger.info("Event Name-->" + event.getName());
		logger.info("Event Value-->" + event.getValue());
	}

	@ProcessAction(name = "showTaskCoach")
	public void showTaskCoach(ActionRequest request, ActionResponse response)
			throws PortletException, IOException {

		String taskID = request.getParameter("selectedTaskID");
		String providerID = "tpi1378833781728";
		logger.info("selectedTaskID =" + taskID);

		String xmlTaskID = "{\"TaskID\":\"" + taskID + "\",\"ProviderID\":\""
				+ providerID + "\"}";

		logger.info("xmlTaskID=" + xmlTaskID);
		response.setEvent(new QName(NAMESPACE, "TaskSelection"), xmlTaskID);
		getListOfParameterNames(request);
		response.setRenderParameters(request.getParameterMap());

	}

	private void getListOfAttributeNames(PortletRequest request) {
		Enumeration e = request.getAttributeNames();
		logger.info("=================================");
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			logger.info("-" + key);
		}
		logger.info("=================================");
	}

	private void getListOfParameterNames(PortletRequest request) {
		Enumeration e = request.getParameterNames();
		logger.info("=================================");
		logger.info("========Parameter Names==========");
		logger.info("=================================");
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			logger.info("-" + key);
		}
		logger.info("=================================");
	}

	protected void include(
            String path, RenderRequest renderRequest,
            RenderResponse renderResponse)
        throws IOException, PortletException {
 
        PortletRequestDispatcher portletRequestDispatcher =
            getPortletContext().getRequestDispatcher(path);
 
        if (portletRequestDispatcher == null) {
            logger.info(path + " is not a valid include");
        }
        else {
            portletRequestDispatcher.include(renderRequest, renderResponse);
        }
    }
	
}