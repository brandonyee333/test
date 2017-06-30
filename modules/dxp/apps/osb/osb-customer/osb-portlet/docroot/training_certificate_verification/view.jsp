<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

String title = "verify-a-training";

int trainingCertificateType = PrefsParamUtil.getInteger(portletPreferences, request, "trainingCertificateType");

if (TrainingCertificateTemplateConstants.TYPE_TRAINING_EXAM == trainingCertificateType) {
	title = "verify-a-certification";
}
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title="<%= title %>"
/>

<c:choose>
	<c:when test='<%= SessionMessages.contains(portletSession, "certificateKeyVerified") %>'>
		<liferay-util:include page="/training_certificate_verification/certificate_info.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/training_certificate_verification/form.jsp" servletContext="<%= application %>" />
	</c:otherwise>
</c:choose>