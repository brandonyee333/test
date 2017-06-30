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
String backURL = ParamUtil.getString(request, "backURL", redirect);

long trainingCustomerId = ParamUtil.getLong(request, "trainingCustomerId");

TrainingCustomer trainingCustomer = null;

try {
	trainingCustomer = TrainingCustomerLocalServiceUtil.getTrainingCustomer(trainingCustomerId);
}
catch (NoSuchTrainingCustomerException nstce) {
}

long trainingEventId = ParamUtil.getLong(request, "trainingEventId");

User trainingUser = null;

if (trainingCustomer != null) {
	trainingUser = trainingCustomer.getUser();
}

String emailAddress = BeanParamUtil.getString(trainingUser, request, "emailAddress");

if (emailAddress.endsWith(TrainingCustomerConstants.EMAIL_ADDRESS_SUFFIX)) {
	emailAddress = StringPool.BLANK;
}
%>

<portlet:actionURL name="updateTrainingCustomer" var="updateTrainingCustomerURL">
	<portlet:param name="mvcPath" value="/admin/edit_training_customer.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateTrainingCustomerURL %>" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="trainingCustomerId" type="hidden" value="<%= trainingCustomerId %>" />
	<aui:input name="trainingEventId" type="hidden" value="<%= trainingEventId %>" />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="student"
	/>

	<liferay-ui:error exception="<%= ContactFirstNameException.class %>" message="please-enter-a-valid-first-name" />
	<liferay-ui:error exception="<%= ContactLastNameException.class %>" message="please-enter-a-valid-last-name" />
	<liferay-ui:error exception="<%= DuplicateUserEmailAddressException.class %>" message="the-email-address-you-requested-is-already-taken" />
	<liferay-ui:error exception="<%= ReservedUserEmailAddressException.class %>" message="the-email-address-you-requested-is-reserved" />
	<liferay-ui:error exception="<%= UserEmailAddressException.class %>" message="please-enter-a-valid-email-address" />
	<liferay-ui:error exception="<%= UserProfileFirstNameException.class %>" message="please-enter-a-valid-first-name" />
	<liferay-ui:error exception="<%= UserProfileHistoryFirstNameException.class %>" message="please-enter-a-valid-first-name" />
	<liferay-ui:error exception="<%= UserProfileHistoryLastNameException.class %>" message="please-enter-a-valid-last-name" />
	<liferay-ui:error exception="<%= UserProfileLastNameException.class %>" message="please-enter-a-valid-last-name" />

	<table class="lfr-table">
	<tr>
		<td>
			<liferay-ui:message key="first-name" />
		</td>
		<td>
			<aui:input bean="<%= trainingUser %>" label="" model="<%= User.class %>" name="firstName" />
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="last-name" />
		</td>
		<td>
			<aui:input bean="<%= trainingUser %>" label="" model="<%= User.class %>" name="lastName" />
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="email-address" />
		</td>
		<td>
			<aui:input label="" name="emailAddress" style="width: 150px;" type="text" value="<%= emailAddress %>" />
		</td>
	</tr>
	<tr class="legal-entity-name">
		<td>
			<liferay-ui:message key="legal-entity-name" />
		</td>
		<td>
			<liferay-ui:custom-attribute
				className="com.liferay.portal.model.User"
				classPK="<%= (trainingUser != null) ? trainingUser.getUserId() : 0 %>"
				editable="<%= true %>"
				label="<%= false %>"
				name="osbCompany"
			/>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<br />
		</td>
	</tr>
	</table>

	<br />

	<input type="submit" value="<liferay-ui:message key="save" />" />

	<input onClick="location.href = '<%= HtmlUtil.escape(backURL) %>';" type="button" value="<liferay-ui:message key="cancel" />" />
</aui:form>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<script type="text/javascript">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />firstName);
	</script>
</c:if>