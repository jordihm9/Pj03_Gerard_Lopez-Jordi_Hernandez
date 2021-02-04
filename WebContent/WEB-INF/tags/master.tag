<%@ tag language="java" pageEncoding="UTF-8"%>
<!--  IMPORT JSTL LIBRARY -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- TAGS -->
<%@taglib tagdir="/WEB-INF/tags" prefix="t" %>

<%@attribute name="pageTitle" fragment="true" %>
<%@attribute name="importCss" fragment="true" %>
<%@attribute name="importJs" fragment="true" %>

<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- TITLE -->
	<title><jsp:invoke fragment="pageTitle"/></title>
	<!-- CSS -->
    <!-- JAVASCRIPT -->
    <!-- FONTS -->
</head>

<body>
	<t:header></t:header>
	<jsp:doBody/>
</body>

</html>