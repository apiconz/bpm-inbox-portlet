<%@page import="pe.com.hydra.reapro.client.Task"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>

<%@ page import="javax.portlet.*"%>
<portlet:defineObjects />
<%
	List taskList = (List) renderRequest.getPortletSession()
			.getAttribute("taskList", PortletSession.PORTLET_SCOPE);

	//Portlet namespace
	String portletNS = renderResponse.getNamespace();
%>
<h1>Inicial</h1>

<%
	if (taskList == null) {
%>
<p>nooooooooooo</p>
<%
	} else {
		for (int i = 0; i < taskList.size(); i++) {
			Task task = (Task) taskList.get(i);
%>

A ver:
<%=task.getName()%>

<%
	}
	}
%>