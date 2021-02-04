<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="t" %>

<t:master>
	<jsp:attribute name="pageTitle">Pj03 Billing</jsp:attribute>
	<jsp:body>
		<div id="invoices-list">
			<t:invoices-table></t:invoices-table>
		</div>
		<div id="invoice-form">
			<t:invoice-form></t:invoice-form>
		</div>
	</jsp:body>
</t:master>