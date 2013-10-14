<%@page import="pe.com.hydra.reapro.client.Task"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>

<%@ page import="javax.portlet.*"%>
<portlet:defineObjects />
<style>
.datagrid table {
	border-collapse: collapse;
	text-align: left;
	width: 100%;
}

.datagrid {
	font: normal 12px/150% Arial, Helvetica, sans-serif;
	background: #fff;
	overflow: hidden;
	border: 1px solid #006699;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 3px;
}

.datagrid table td,.datagrid table th {
	padding: 3px 10px;
}

.datagrid table thead th {
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #006699
		), color-stop(1, #00557F));
	background: -moz-linear-gradient(center top, #006699 5%, #00557F 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#006699',
		endColorstr='#00557F');
	background-color: #006699;
	color: #ffffff;
	font-size: 15px;
	font-weight: bold;
	border-left: 1px solid #0070A8;
}

.datagrid table thead th:first-child {
	border: none;
}

.datagrid table tbody td {
	color: #00496B;
	border-left: 1px solid #E1EEF4;
	font-size: 12px;
	font-weight: normal;
}

.datagrid table tbody .alt td {
	background: #E1EEF4;
	color: #00496B;
}

.datagrid table tbody td:first-child {
	border-left: none;
}

.datagrid table tbody tr:last-child td {
	border-bottom: none;
}

.datagrid table tfoot td div {
	border-top: 1px solid #006699;
	background: #E1EEF4;
}

.datagrid table tfoot td {
	padding: 0;
	font-size: 12px
}

.datagrid table tfoot td div {
	padding: 2px;
}

.datagrid table tfoot td ul {
	margin: 0;
	padding: 0;
	list-style: none;
	text-align: right;
}

.datagrid table tfoot  li {
	display: inline;
}

.datagrid table tfoot li a {
	text-decoration: none;
	display: inline-block;
	padding: 2px 8px;
	margin: 1px;
	color: #FFFFFF;
	border: 1px solid #006699;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 3px;
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #006699
		), color-stop(1, #00557F));
	background: -moz-linear-gradient(center top, #006699 5%, #00557F 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#006699',
		endColorstr='#00557F');
	background-color: #006699;
}

.datagrid table tfoot ul.active,.datagrid table tfoot ul a:hover {
	text-decoration: none;
	border-color: #006699;
	color: #FFFFFF;
	background: none;
	background-color: #00557F;
}
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
<h1>Mis Tareas</h1>
<h3>Tareas Abiertas</h3>

<%
	if (taskList == null) {
%>
<p>No hay tareas</p>
<%
	} else {
%>

<div class="datagrid">
	<table border="1">
		<thead>
			<tr>
				<th>Tarea</th>
				<th>Proceso</th>
				<th>Actividad</th>
				<th>Rol</th>
				<th>Fecha de Vencimiento</th>
				<th>Dato Proceso 1</th>
				<th>Dato Proceso 2</th>
				<th>Estado</th>
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
			<tr class="<%=estilo%>">
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