package pe.com.hydra.reapro.portlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

public class BPMInboxPortlet extends GenericPortlet {

	@Override
	protected void doView(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {

		response.setContentType("text/html");
		response.getWriter().println("Hello World");

		getPortletContext().getRequestDispatcher("/WEB-INF/jsp/inbox.jsp").include(request, response);
		
	}

	@Override
	public void processAction(ActionRequest request, ActionResponse response)
			throws PortletException, IOException {
		// TODO Auto-generated method stub
		super.processAction(request, response);
	}

}