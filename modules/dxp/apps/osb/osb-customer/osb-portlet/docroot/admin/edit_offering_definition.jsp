<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);

long offeringDefinitionId = ParamUtil.getLong(request, "offeringDefinitionId");

OfferingDefinition offeringDefinition = OfferingDefinitionLocalServiceUtil.fetchOfferingDefinition(offeringDefinitionId);

long productEntryId = BeanParamUtil.getLong(offeringDefinition, request, "productEntryId");
long supportResponseId = BeanParamUtil.getLong(offeringDefinition, request, "supportResponseId");
boolean licenses = BeanParamUtil.getBoolean(offeringDefinition, request, "licenses");
%>

<script type="text/javascript">
	function <portlet:namespace />disableInput(field, checked) {
		document.<portlet:namespace />fm['<portlet:namespace />' + field].disabled = checked;
	}
</script>

<portlet:actionURL name="updateOfferingDefinition" var="updateOfferingDefinitionURL">
	<portlet:param name="mvcPath" value="/admin/edit_offering_definition.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateOfferingDefinitionURL %>" method="post">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="offeringDefinitionId" type="hidden" value="<%= offeringDefinitionId %>" />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="offering"
	/>

	<liferay-ui:error exception="<%= DuplicateOfferingDefinitionException.class %>" message="the-offering-already-exists" />
	<liferay-ui:error exception="<%= RequiredOfferingDefinitionException.class %>" message="you-cannot-modify-this-offering" />

	<table class="lfr-table">
		<c:if test="<%= offeringDefinition != null %>">
			<tr>
				<td>
					<liferay-ui:message key="created-by" />
				</td>
				<td>
					<%= HtmlUtil.escape(PortalUtil.getUserName(offeringDefinition.getUserId(), offeringDefinition.getUserName())) %>
				</td>
			</tr>
			<tr>
				<td>
					<liferay-ui:message key="last-modified" />
				</td>
				<td>
					<%= longDateFormatDateTime.format(offeringDefinition.getModifiedDate()) %>
				</td>
			</tr>
		</c:if>

		<tr>
			<td>
				<liferay-ui:message key="product" />
			</td>
			<td>
				<aui:select label="" name="productEntryId">

					<%
					List<ProductEntry> productEntries = ProductEntryLocalServiceUtil.getProductEntries(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

					for (ProductEntry productEntry : productEntries) {
					%>

						<aui:option label="<%= productEntry.getName() %>" selected="<%= productEntryId == productEntry.getProductEntryId() %>" value="<%= productEntry.getProductEntryId() %>" />

					<%
					}
					%>

				</aui:select>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="product-description" />
			</td>
			<td>
				<liferay-ui:input-field
					bean="<%= offeringDefinition %>"
					field="productDescription"
					model="<%= OfferingDefinition.class %>"
				/>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="licenses" />
			</td>
			<td>

				<%
				String taglibLicensesOnClick = renderResponse.getNamespace() + "disableInput('unlimitedLicenses', !this.checked);";
				%>

				<liferay-ui:input-checkbox
					defaultValue="<%= licenses %>"
					onClick="<%= taglibLicensesOnClick %>"
					param="licenses"
				/>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="unlimited-licenses" />
			</td>
			<td>
				<liferay-ui:input-checkbox
					defaultValue='<%= BeanParamUtil.getBoolean(offeringDefinition, request, "unlimitedLicenses") %>'
					disabled="<%= !licenses %>"
					param="unlimitedLicenses"
				/>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<br />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="sla" />
			</td>
			<td>
				<aui:select label="" name="supportResponseId">

					<%
					List<SupportResponse> supportResponses = SupportResponseLocalServiceUtil.getSupportResponses(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

					for (SupportResponse supportResponse : supportResponses) {
					%>

						<aui:option label="<%= supportResponse.getName() %>" selected="<%= supportResponseId == supportResponse.getSupportResponseId() %>" value="<%= supportResponse.getSupportResponseId() %>" />

					<%
					}
					%>

				</aui:select>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="support-tickets" />
			</td>
			<td>
				<liferay-ui:input-field
					bean="<%= offeringDefinition %>"
					field="supportTickets"
					model="<%= OfferingDefinition.class %>"
				/>
			</td>
		</tr>
	</table>

	<br />

	<aui:button type="submit" value="save" />

	<aui:a cssClass="btn btn-default" href="<%= backURL %>" label="cancel" />
</aui:form>