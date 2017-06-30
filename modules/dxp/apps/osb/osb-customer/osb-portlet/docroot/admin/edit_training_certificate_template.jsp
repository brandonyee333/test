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
String redirect = (String)request.getAttribute(WebKeys.REDIRECT);

if (Validator.isNull(redirect)) {
	redirect = ParamUtil.getString(request, "redirect");
}

String backURL = ParamUtil.getString(request, "backURL", redirect);

long trainingCertificateTemplateId = ParamUtil.getLong(request, "trainingCertificateTemplateId");

TrainingCertificateTemplate trainingCertificateTemplate = null;

try {
	trainingCertificateTemplate = TrainingCertificateTemplateLocalServiceUtil.getTrainingCertificateTemplate(trainingCertificateTemplateId);
}
catch (NoSuchTrainingCertificateTemplateException nstcte) {
}

int type = BeanParamUtil.getInteger(trainingCertificateTemplate, request, "type");
%>

<portlet:actionURL name="updateTrainingCertificateTemplate" var="updateTrainingCertificateTemplateURL">
	<portlet:param name="mvcPath" value="/admin/edit_training_certificate_template.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateTrainingCertificateTemplateURL %>" enctype="multipart/form-data" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="trainingCertificateTemplateId" type="hidden" value="<%= trainingCertificateTemplateId %>" />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="certificate-template"
	/>

	<liferay-ui:error exception="<%= FileExtensionException.class %>">
		<liferay-ui:message arguments='<%= ".pdf" %>' key="please-enter-a-file-with-a-valid-extension-x" />
	</liferay-ui:error>

	<liferay-ui:error exception="<%= FileNameException.class %>" message="please-enter-a-file-with-a-valid-file-name" />
	<liferay-ui:error exception="<%= TrainingCertificateTemplateNameException.class %>" message="please-enter-a-valid-name" />

	<aui:model-context bean="<%= trainingCertificateTemplate %>" model="<%= TrainingCertificateTemplate.class %>" />

	<table class="lfr-table">
	<tr>
		<td>
			<liferay-ui:message key="type" />
		</td>
		<td>
			<aui:select label="" name="type">

				<%
				for (int curType : TrainingCertificateTemplateConstants.TYPES) {
				%>

					<aui:option label="<%= TrainingCertificateTemplateConstants.getTypeLabel(curType) %>" selected="<%= type == curType %>" value="<%= curType %>" />

				<%
				}
				%>

			</aui:select>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="name" />
		</td>
		<td>
			<aui:input label="" name="name" />
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="description" />
		</td>
		<td>
			<aui:input label="" name="description" />
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="template" />
		</td>
		<td>
			<aui:input label="" name="templateFile" type="file" />

			<portlet:resourceURL id="generateCertificateTemplate" var="generateCertificateTemplateURL">
				<portlet:param name="mvcPath" value="/admin/edit_training_certificate_template.jsp" />
				<portlet:param name="trainingCertificateTemplateId" value="<%= String.valueOf(trainingCertificateTemplateId) %>" />
			</portlet:resourceURL>

			<c:if test="<%= trainingCertificateTemplateId > 0 %>">
				<aui:a href="<%= generateCertificateTemplateURL %>" label="download-template" />
			</c:if>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="badge" />
		</td>
		<td>
			<aui:input label="" name="badgeFile" type="file" />
		</td>
	</tr>
	</table>

	<br />

	<input type="submit" value="<liferay-ui:message key="save" />" />

	<input onClick="location.href = '<%= HtmlUtil.escape(backURL) %>';" type="button" value="<liferay-ui:message key="cancel" />" />
</aui:form>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<script type="text/javascript">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />name);
	</script>
</c:if>