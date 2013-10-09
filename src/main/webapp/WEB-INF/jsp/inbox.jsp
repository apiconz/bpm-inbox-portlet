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
<script>
function receiveFromCoach(aString) {
    alert(aString);
}
</script>
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

<table border="1">
	<thead>
		<tr>
			<th>ID Tarea</th>
			<th>Nombre BPD</th>
			<th>Nombre de Actividad</th>
			<th>Rol Asignado</th>
			<th>Fecha de Vencimiento</th>
			<th>Departamento</th>
			<th>Hiring Manager</th>
			<th>Status Tarea</th>
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
			<td><%=task.getBpdName() %></td>
			<td><%=task.getTaskActivityName() %></td>
			<td><%=task.getTaskAssignedPersonRole()%></td>
			<td><%=task.getTaskDueDate() %></td>
			<td><%=(task.getDepartment().equals("null"))?"":task.getDepartment() %></td>
			<td><%=(task.getHiringManager().equals("null"))?"":task.getHiringManager() %></td>
			<td><%=task.getTaskStatus() %></td>
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