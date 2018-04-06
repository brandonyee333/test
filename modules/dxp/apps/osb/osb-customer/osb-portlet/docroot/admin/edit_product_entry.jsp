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

long productEntryId = ParamUtil.getLong(request, "productEntryId");

ProductEntry productEntry = ProductEntryLocalServiceUtil.fetchProductEntry(productEntryId);

int type = BeanParamUtil.getInteger(productEntry, request, "type");
int environment = BeanParamUtil.getInteger(productEntry, request, "environment");
String versionsListType = BeanParamUtil.getString(productEntry, request, "versionsListType");
String dossieraIdMappings = ParamUtil.getString(request, "dossieraIdMappings");

if ((productEntry != null) && Validator.isNull(dossieraIdMappings)) {
	dossieraIdMappings = StringUtil.merge(productEntry.getDossieraIdMappings(), StringPool.NEW_LINE);
}
%>

<portlet:actionURL name="updateProductEntry" var="updateProductEntryURL">
	<portlet:param name="mvcPath" value="/admin/edit_product_entry.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateProductEntryURL %>" method="post">
	<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>" />
	<input name="<portlet:namespace />backURL" type="hidden" value="<%= HtmlUtil.escape(backURL) %>" />
	<input name="<portlet:namespace />productEntryId" type="hidden" value="<%= productEntryId %>" />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="product"
	/>

	<liferay-ui:error exception="<%= DuplicateProductEntryException.class %>" message="please-enter-a-unique-name" />
	<liferay-ui:error exception="<%= ProductEntryEnvironmentException.class %>" message="please-enter-a-valid-environment" />
	<liferay-ui:error exception="<%= ProductEntryNameException.class %>" message="please-enter-a-valid-name" />

	<table class="lfr-table">
		<tr>
			<td>
				<liferay-ui:message key="name" />
			</td>
			<td>
				<liferay-ui:input-field
					bean="<%= productEntry %>"
					field="name"
					model="<%= ProductEntry.class %>"
				/>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="type" />
			</td>
			<td>
				<aui:select label="" name="type">
					<option value=""></option>

					<%
					for (int curType : ProductEntryConstants.TYPES) {
					%>

						<option <%= (type == curType) ? "selected" : "" %> value="<%= curType %>"><%= LanguageUtil.get(request, ProductEntryConstants.getTypeLabel(curType)) %></option>

					<%
					}
					%>

				</aui:select>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="environment" />
			</td>
			<td>
				<aui:select label="" name="environment">
					<option value=""></option>

					<%
					for (int i = 1; i <= 6; i++) {
					%>

						<option <%= (environment == i) ? "selected" : "" %> value="<%= i %>"><%= LanguageUtil.get(request, ProductEntryConstants.getEnvironmentLabel(i)) %></option>

					<%
					}
					%>

				</aui:select>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="version-list-type" />
			</td>
			<td>
				<aui:input label="" name="versionsListType" type="text" value="<%= HtmlUtil.escapeAttribute(versionsListType) %>" />
			</td>
		</tr>

		<c:if test="<%= (productEntry != null) && Validator.isNotNull(versionsListType) %>">
			<tr>
				<td>
					<liferay-ui:message key="versions" />
				</td>
				<td>
					<%= ListUtil.toString(productEntry.getVersionsListTypes(), "name") %>
				</td>
			</tr>
		</c:if>

		<tr>
			<td>
				<liferay-ui:message key="dossiera-id-mappings" />
			</td>
			<td>
				<aui:fieldset>
					<aui:input cssClass="lfr-textarea-container" label="" name="dossieraIdMappings" style="width: 500px;" type="textarea" value="<%= dossieraIdMappings %>" />
				</aui:fieldset>
			</td>
		</tr>
	</table>

	<br />

	<aui:button type="submit" value="save" />

	<a class="btn btn-default" href="<%= HtmlUtil.escape(backURL) %>"><liferay-ui:message key="cancel" /></a>
</aui:form>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<script type="text/javascript">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />name);
	</script>
</c:if>