<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="t" %>

<t:master>
	<jsp:attribute name="pageTitle">Pj03 Billing</jsp:attribute>
	<jsp:body>
		<div id="invoices-list" class="container">
			<t:invoices-table></t:invoices-table>
			<button id="newInvoice" class="btn">New Invoice</button>
		</div>
		<div id="invoice-form" class="outer blured">
			<div class="center">
				<t:invoice-form></t:invoice-form>
			</div>
		</div>
	</jsp:body>
</t:master>