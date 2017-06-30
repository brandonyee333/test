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

String type = "training";

int trainingCertificateType = PrefsParamUtil.getInteger(portletPreferences, request, "trainingCertificateType");

if (trainingCertificateType == TrainingCertificateTemplateConstants.TYPE_TRAINING_EXAM) {
	type = "certification";
}
%>

<div>
	<liferay-ui:message key='<%= "please-fill-out-the-form-below-to-verify-an-individuals-completion-of-an-official-liferay-" + type %>' />
</div>

<portlet:actionURL name="verifyCertificateKey" var="verifyCertificateKeyURL">
	<portlet:param name="mvcPath" value="/training_certificate_verification/view.jsp" />
</portlet:actionURL>

<aui:form action="<%= verifyCertificateKeyURL %>" method="post">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="trainingCertificateType" type="hidden" value="<%= trainingCertificateType %>" />

	<liferay-ui:error exception="<%= NoSuchTrainingCertificateException.class %>" message="unable-to-verify" />

	<aui:input label="certificate-key" name="certificateKey" type="text">
		<aui:validator name="required" />
	</aui:input>

	<aui:input name="lastName" type="text">
		<aui:validator name="required" />
	</aui:input>

	<aui:button-row>
		<aui:button type="submit" value="verify" />
	</aui:button-row>
</aui:form>