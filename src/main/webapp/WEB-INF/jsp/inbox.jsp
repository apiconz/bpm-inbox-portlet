<%@page import="pe.com.hydra.reapro.client.Task"%>
<%@page import="javax.portlet.RenderRequest"%>
<%@page import="javax.portlet.RenderResponse"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>

<%@ page import="javax.portlet.*"%>
<%!RenderRequest renderRequest;
	RenderResponse renderResponse;%>
<portlet:defineObjects />

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

<%
	if (taskList == null) {
%>
<p>No hay tareas</p>
<%
	} else {
%>
<div style="float: right; display: block; position: relative;">
	<input type="button" name="modificar" value="Modificar" />
</div>
<div style="float: left; display: block; position: relative;" >
	<table style="border: 0px; " >
		<thead>
			<tr>
				<th>Tarea</th>
				<th>Proceso</th>
				<th>Actividad</th>
				<th>Rol</th>
				<th>Fecha de Vencimiento</th>
				<th>Dato Proceso</th>
				<th>Dato Proceso</th>
				<th>Estado</th>
				<th>Prioridad</th>
			</tr>
		</thead>
		<tbody>

			<%
				String estilo = "";
					for (int i = 0; i < taskList.size(); i++) {
						Task task = (Task) taskList.get(i);
						if (i % 2 == 0) {
							estilo = "alt";
						} else {
							estilo = "";
						}
						;
			%>
			<tr>
				<td><portlet:actionURL var="actionURL">
						<portlet:param name="javax.portlet.action" value="showTaskCoach" />
						<portlet:param name="selectedTaskID" value="<%=task.getTaskId()%>" />
					</portlet:actionURL> <a href="<%=actionURL%>"><%=task.getTaskId()%></a></td>
				<td><%=task.getBpdName()%></td>
				<td><%=task.getTaskActivityName()%></td>
				<td><%=task.getTaskAssignedPersonRole()%></td>
				<td><%=task.getTaskDueDate()%></td>
				<td><%=(task.getDepartment().equals("null")) ? "" : task
							.getDepartment()%></td>
				<td><%=(task.getHiringManager().equals("null")) ? ""
							: task.getHiringManager()%></td>
				<td><%=task.getTaskStatus()%></td>

				<%
					if ((i % 2) == 0) {
				%>
				<td style="">
					<p style="background: url('<%=renderResponse.encodeURL(request.getContextPath() + "/images/prio1.png")%>') no-repeatscroll 0% 0% transparent">
						<span style="position: relative; right: 10000px;">1</span>
					</p>
				</td>
				<%
					} else if ((i % 3) == 0) {
				%>
				<td style=""><p style="background: url('<%=renderResponse.encodeURL(request.getContextPath() + "/WEB-INF/images/prio2.png") %>') no-repeatscroll 0% 0% transparent">
						<span style="position: relative; right: 10000px;">2</span>
					</p></td>
				<%
					} else {
				%>
				<td style=""><p style="background: url('<%=renderResponse.encodeURL(request.getContextPath() + "/WEB-INF/images/prio3.png") %>') no-repeatscroll 0% 0% transparent">
						<span style="position: relative; right: 10000px;">3</span>
					</p></td>
				<%
					}
				%>

			</tr>

			<%
				}
			%>

		</tbody>
		<tfoot>
			<tr>
				<td colspan="8"><div id="paging">
						<ul>
							<li><a href="#"><span>Hay <%=taskList.size()%>
										procesos abiertos
								</span></a></li>
						</ul>
					</div>
			</tr>
		</tfoot>

	</table>
</div>
<%
	}
%>