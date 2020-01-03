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

long productEntryId = ParamUtil.getLong(request, "productEntryId");

ProductEntry productEntry = ProductEntryLocalServiceUtil.fetchProductEntry(productEntryId);

Product koroneikiProduct = null;

if ((productEntry != null) && Validator.isNotNull(productEntry.getKoroneikiProductKey())) {
	koroneikiProduct = productWebService.getProduct(productEntry.getKoroneikiProductKey());
}

int type = BeanParamUtil.getInteger(productEntry, request, "type");
int environment = BeanParamUtil.getInteger(productEntry, request, "environment");
String versionsListType = BeanParamUtil.getString(productEntry, request, "versionsListType");
String zendeskTag = ParamUtil.getString(request, "zendeskTag");

if (productEntry != null) {
	if (Validator.isNull(zendeskTag)) {
		zendeskTag = productEntry.getZendeskTag();
	}
}
%>

<portlet:actionURL name="updateProductEntry" var="updateProductEntryURL">
	<portlet:param name="mvcPath" value="/admin/edit_product_entry.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateProductEntryURL %>" method="post">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="productEntryId" type="hidden" value="<%= productEntryId %>" />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="product"
	/>

	<liferay-ui:error exception="<%= DuplicateProductEntryException.class %>" message="please-enter-a-unique-name" />
	<liferay-ui:error exception="<%= ProductEntryEnvironmentException.class %>" message="please-enter-a-valid-environment" />
	<liferay-ui:error exception="<%= ProductEntryNameException.class %>" message="please-enter-a-valid-name" />
	<liferay-ui:error exception="<%= ZendeskTagException.class %>" message="please-enter-a-valid-zendesk-tag" />

	<aui:model-context bean="<%= productEntry %>" model="<%= ProductEntry.class %>" />

	<table class="lfr-table">
		<tr>
			<td>
				<liferay-ui:message key="koroneiki-product-key" />
			</td>
			<td>
				<aui:input label="" name="koroneikiProductKey" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="name" />
			</td>
			<td>
				<%= HtmlUtil.escape(productEntry.getName()) %>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="type" />
			</td>
			<td>
				<aui:select label="" name="type">
					<aui:option value="" />

					<%
					for (int curType : ProductEntryConstants.TYPES) {
					%>

						<aui:option label="<%= ProductEntryConstants.getTypeLabel(curType) %>" selected="<%= type == curType %>" value="<%= curType %>" />

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
					<aui:option value="" />

					<%
					for (int i = 1; i <= 6; i++) {
					%>

						<aui:option label="<%= ProductEntryConstants.getEnvironmentLabel(i) %>" selected="<%= environment == i %>" value="<%= i %>" />

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
				<aui:input label="" name="versionsListType" type="text" value="<%= versionsListType %>" />
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
				<liferay-ui:message key="external-links" />
			</td>
			<td>
				<table class="lfr-table">

					<%
					if (koroneikiProduct != null) {
						ExternalLink[] externalLinks = koroneikiProduct.getExternalLinks();

						if (externalLinks != null) {
					%>
					
						<tr>
							<td>
								<strong><liferay-ui:message key="domain" /></strong>
							</td>
							<td>
								<strong><liferay-ui:message key="entity-name" /></strong>
							</td>
							<td>
								<strong><liferay-ui:message key="entity-id" /></strong>
							</td>
						</tr>
					
					<% 
						for (ExternalLink externalLink : externalLinks) {
					%>

								<tr>
									<td>
										<%= HtmlUtil.escape(externalLink.getDomain()) %>
									</td>
									<td>
										<%= HtmlUtil.escape(externalLink.getEntityName()) %>
									</td>
									<td>
										<%= HtmlUtil.escape(externalLink.getEntityId()) %>
									</td>
								</tr>

					<%
							}
						}
					}
					%>

				</table>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="zendesk-tag" />
			</td>
			<td>
				<aui:fieldset>
					<aui:input label="" name="zendeskTag" style="width: 500px;" type="text" value="<%= zendeskTag %>" />
				</aui:fieldset>
			</td>
		</tr>
	</table>

	<br />

	<aui:button type="submit" value="save" />

	<aui:a cssClass="btn btn-default" href="<%= backURL %>" label="cancel" />
</aui:form>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<script type="text/javascript">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />name);
	</script>
</c:if>