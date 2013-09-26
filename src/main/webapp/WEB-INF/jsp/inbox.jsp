<%@page import="pe.com.hydra.reapro.client.Task"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>

<%@ page import="javax.portlet.*"%>
<portlet:defineObjects />
<style>
<!--
.profile li {
	float: right;
	clear: right;
}
-->
</style>
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
%>

<table>
	<thead>
		<tr>
			<td>ID Tarea</td>
			<td>N&uacute;mero Proceso</td>
			<td>N&uacute;mero Actividad</td>
			<td>Rol Asignado</td>
			<td>Fecha Creaci&oacute;n</td>
			<td>N&uacute;mero Expediente</td>
		</tr>
	</thead>
	<tbody>

		<%
			for (int i = 0; i < taskList.size(); i++) {
					Task task = (Task) taskList.get(i);
		%>
		<tr>
			<td><portlet:actionURL var="actionURL">
					<portlet:param name="javax.portlet.action" value="showTaskCoach" />
					<portlet:param name="selectedTaskID" value="<%=task.getTaskId()%>" />
				</portlet:actionURL> <a href="<%=actionURL%>"><%=task.getTaskId()%></a></td>
			<td>N&uacute;mero Proceso</td>
			<td>N&uacute;mero Actividad</td>
			<td><%=task.getTaskAssignedPersonRole()%></td>
			<td>Fecha Creaci&oacute;n</td>
			<td>N&uacute;mero Expediente</td>
		</tr>

		<%
			}
		%>

	</tbody>
</table>

<%
	}
%>


<portlet:renderURL var="renderURL" />

<a href="<%=renderURL%>">Render URL</a>
<br />