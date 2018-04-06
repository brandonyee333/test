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

long orderEntryId = ParamUtil.getLong(request, "orderEntryId");

OrderEntry orderEntry = OrderEntryLocalServiceUtil.fetchOrderEntry(orderEntryId);

long accountEntryId = BeanParamUtil.getLong(orderEntry, request, "accountEntryId");

String defaultSalesforceOpportunityKey = StringPool.BLANK;

if (orderEntry != null) {
	defaultSalesforceOpportunityKey = orderEntry.getSalesforceOpportunityKey();
}

String salesforceOpportunityKey = ParamUtil.getString(request, "salesforceOpportunityKey", defaultSalesforceOpportunityKey);

Calendar startCal = CalendarFactoryUtil.getCalendar(locale);

int startDateMonth = ParamUtil.getInteger(request, "startDateMonth");
int startDateDay = ParamUtil.getInteger(request, "startDateDay");
int startDateYear = ParamUtil.getInteger(request, "startDateYear");

Date startDate = PortalUtil.getDate(startDateMonth, startDateDay, startDateYear, (Class<? extends PortalException>)null);

if (startDate != null) {
	startCal.setTime(startDate);
}
else {
	if (orderEntry != null) {
		if (orderEntry.getStartDate() != null) {
			startCal.setTime(orderEntry.getStartDate());
		}
	}
}

boolean prorated = BeanParamUtil.getBoolean(orderEntry, request, "prorated");

Calendar actualStartCal = (Calendar)startCal.clone();

int actualStartDateMonth = ParamUtil.getInteger(request, "actualStartDateMonth");
int actualStartDateDay = ParamUtil.getInteger(request, "actualStartDateDay");
int actualStartDateYear = ParamUtil.getInteger(request, "actualStartDateYear");

Date actualStartDate = PortalUtil.getDate(actualStartDateMonth, actualStartDateDay, actualStartDateYear, (Class<? extends PortalException>)null);

if (actualStartDate != null) {
	actualStartCal.setTime(actualStartDate);
}
else {
	if (orderEntry != null) {
		if (orderEntry.getActualStartDate() != null) {
			actualStartCal.setTime(orderEntry.getActualStartDate());
		}
	}
}

int renewCount = BeanParamUtil.getInteger(orderEntry, request, "renewCount");

List<OfferingEntry> offeringEntries = new ArrayList<OfferingEntry>();

String offeringEntriesCountString = request.getParameter("offeringEntriesCount");

if ((offeringEntriesCountString == null) && (orderEntry != null)) {
	offeringEntries.addAll(orderEntry.getOfferingEntries());
}

int offeringEntriesCount = GetterUtil.getInteger(offeringEntriesCountString);

for (int i = 0; i < offeringEntriesCount; i++) {
	int quantity = ParamUtil.getInteger(request, "quantity_" + i, 1);

	if (quantity == 0) {
		continue;
	}

	long offeringEntryId = ParamUtil.getLong(request, "offeringEntryId_" + i);
	long productEntryId = ParamUtil.getLong(request, "productEntryId_" + i);
	long supportResponseId = ParamUtil.getLong(request, "supportResponseId_" + i);
	String productDescription = ParamUtil.getString(request, "productDescription_" + i);
	boolean licenses = ParamUtil.getBoolean(request, "licenses_" + i);
	long licenseLifetime = ParamUtil.getLong(request, "licenseLifetime_" + i);
	boolean supportTickets = ParamUtil.getBoolean(request, "supportTickets_" + i);
	long supportLifetime = ParamUtil.getLong(request, "supportLifetime_" + i);
	int sizing = ParamUtil.getInteger(request, "sizing_" + i);

	OfferingEntry offeringEntry = new OfferingEntryImpl();

	offeringEntry.setOfferingEntryId(offeringEntryId);
	offeringEntry.setProductEntryId(productEntryId);
	offeringEntry.setSupportResponseId(supportResponseId);
	offeringEntry.setProductDescription(productDescription);
	offeringEntry.setLicenses(licenses);
	offeringEntry.setLicenseLifetime(licenseLifetime);
	offeringEntry.setSupportTickets(supportTickets);
	offeringEntry.setSupportLifetime(supportLifetime);
	offeringEntry.setSizing(sizing);
	offeringEntry.setQuantity(quantity);

	offeringEntries.add(offeringEntry);
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/edit_order_entry.jsp");
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("orderEntryId", String.valueOf(orderEntryId));
%>

<script type="text/javascript">
	function <portlet:namespace />addInput(name, value) {
		var input = document.createElement("input");

		input.setAttribute('name', '<portlet:namespace />' + name);
		input.setAttribute('type', 'hidden');
		input.setAttribute('value', value);

		document.<portlet:namespace />fm.appendChild(input);
	}

	function <portlet:namespace />selectOfferingBundle(offeringDefinitionFieldValuesArray) {
		var A = AUI();

		var offeringEntriesCount = A.one(document.<portlet:namespace />fm.<portlet:namespace />offeringEntriesCount);

		for (var i = 0; i < offeringDefinitionFieldValuesArray.length; i++) {
			var offeringDefinitionFieldValues = offeringDefinitionFieldValuesArray[i];

			<portlet:namespace />addInput('productEntryId_' + offeringEntriesCount.val(), offeringDefinitionFieldValues[0]);
			<portlet:namespace />addInput('supportResponseId_' + offeringEntriesCount.val(), offeringDefinitionFieldValues[1]);
			<portlet:namespace />addInput('productDescription_' + offeringEntriesCount.val(), offeringDefinitionFieldValues[2]);
			<portlet:namespace />addInput('licenses_' + offeringEntriesCount.val(), offeringDefinitionFieldValues[3]);
			<portlet:namespace />addInput('supportTickets_' + offeringEntriesCount.val(), offeringDefinitionFieldValues[4]);
			<portlet:namespace />addInput('quantity_' + offeringEntriesCount.val(), offeringDefinitionFieldValues[5]);

			offeringEntriesCount.val(parseInt(offeringEntriesCount.val()) + 1);
		}

		<portlet:namespace />updateOrderEntry();
	}

	function <portlet:namespace />selectOfferingDefinitionId(offeringDefinitionId, offeringDefinitionFieldValues, skipUpdate) {
		var A = AUI();

		var offeringEntriesCount = A.one(document.<portlet:namespace />fm.<portlet:namespace />offeringEntriesCount);

		<portlet:namespace />addInput('productEntryId_' + offeringEntriesCount.val(), offeringDefinitionFieldValues[0]);
		<portlet:namespace />addInput('supportResponseId_' + offeringEntriesCount.val(), offeringDefinitionFieldValues[2]);
		<portlet:namespace />addInput('productDescription_' + offeringEntriesCount.val(), offeringDefinitionFieldValues[4]);
		<portlet:namespace />addInput('licenses_' + offeringEntriesCount.val(), offeringDefinitionFieldValues[5]);
		<portlet:namespace />addInput('supportTickets_' + offeringEntriesCount.val(), offeringDefinitionFieldValues[8]);
		<portlet:namespace />addInput('quantity_' + offeringEntriesCount.val(), offeringDefinitionFieldValues[10]);

		offeringEntriesCount.val(parseInt(offeringEntriesCount.val()) + 1);

		<portlet:namespace />updateOrderEntry();
	}

	function <portlet:namespace />updateOrderEntry() {
		submitForm(document.<portlet:namespace />fm, '<portlet:renderURL><portlet:param name="mvcPath" value="/admin/edit_order_entry.jsp" /></portlet:renderURL>');
	}
</script>

<portlet:actionURL name="updateOrderEntry" var="updateOrderEntryURL">
	<portlet:param name="mvcPath" value="/admin/edit_order_entry.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateOrderEntryURL %>" method="post">
	<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>" />
	<input name="<portlet:namespace />backURL" type="hidden" value="<%= HtmlUtil.escape(backURL) %>" />
	<input name="<portlet:namespace />orderEntryId" type="hidden" value="<%= orderEntryId %>" />
	<input name="<portlet:namespace />offeringEntriesCount" type="hidden" value="<%= offeringEntries.size() %>" />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="order"
	/>

	<liferay-ui:error exception="<%= NoSuchAccountEntryException.class %>" message="please-select-a-project" />
	<liferay-ui:error exception="<%= OrderEntryActualStartDateException.class %>" message="the-actual-start-date-must-be-after-the-start-date" />
	<liferay-ui:error exception="<%= RequiredOfferingEntryException.class %>" message="you-cannot-remove-offerings-that-have-generated-license-keys-or-support-tickets" />

	<c:if test='<%= SessionMessages.contains(renderRequest, "lcsSyncFailed") %>'>
		<div class="portlet-msg-alert">
			<liferay-ui:message key="there-was-an-error-syncing-with-lcs" />
		</div>
	</c:if>

	<c:if test="<%= orderEntry != null %>">
		<div class="sub-header">
			<span class="first segment">
				<liferay-ui:message key="uuid" />:

				<span class="txt-sb"><%= orderEntry.getUuid() %></span>
			</span>
			<span class="spacer"></span>

			<span class="segment">
				<liferay-ui:message key="created-by" />:

				<span class="txt-sb"><%= HtmlUtil.escape(PortalUtil.getUserName(orderEntry.getUserId(), orderEntry.getUserName())) %> <liferay-ui:message key="on" /> <%= longDateFormatDateTime.format(orderEntry.getCreateDate()) %></span>
			</span>
			<span class="spacer"></span>

			<span class="segment">
				<liferay-ui:message key="last-modified" />:

				<span class="txt-sb"><%= HtmlUtil.escape(PortalUtil.getUserName(orderEntry.getModifiedUserId(), orderEntry.getModifiedUserName())) %> <liferay-ui:message key="on" /> <%= longDateFormatDateTime.format(orderEntry.getModifiedDate()) %></span>
			</span>
			<span class="spacer"></span>

			<span class="last segment">
				<liferay-ui:message key="status" />:

				<%
				List<WorkflowTask> workflowTasks = new ArrayList<WorkflowTask>();

				if (!orderEntry.isApproved()) {
					if (orderEntry.getStatus() == WorkflowConstants.STATUS_PENDING_VALIDATION) {
						workflowTasks = WorkflowTaskManagerUtil.search(OSBConstants.COMPANY_ID, 0, null, AccountEntry.class.getName(), new Long[] {orderEntry.getAccountEntryId()}, null, null, false, null, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
					}
					else {
						workflowTasks = WorkflowTaskManagerUtil.search(OSBConstants.COMPANY_ID, 0, null, OrderEntry.class.getName(), new Long[] {orderEntry.getOrderEntryId()}, null, null, false, null, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
					}
				}
				%>

				<c:choose>
					<c:when test="<%= !workflowTasks.isEmpty() %>">

						<%
						PortletURL workflowTaskURL = PortletURLFactoryUtil.create(request, PortletKeys.MY_WORKFLOW_TASK, themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);

						workflowTaskURL.setParameter("struts_action", "/my_workflow_tasks/edit_workflow_task");
						workflowTaskURL.setParameter("redirect", currentURL);

						WorkflowTask workflowTask = workflowTasks.get(0);

						workflowTaskURL.setParameter("workflowTaskId", String.valueOf(workflowTask.getWorkflowTaskId()));
						%>

						<a href="<%= workflowTaskURL.toString() %>" target="_blank"><%= LanguageUtil.get(request, orderEntry.getStatusLabel()) %></a>
					</c:when>
					<c:otherwise>
						<span class="txt-sb"><%= LanguageUtil.get(request, orderEntry.getStatusLabel()) %></span>
					</c:otherwise>
				</c:choose>
			</span>
		</div>

		<br />
	</c:if>

	<table class="lfr-table">
		<tr>
			<td>
				<liferay-ui:message key="project" />
			</td>
			<td>
				<c:choose>
					<c:when test="<%= orderEntry == null %>">
						<aui:select label="" name="accountEntryId">
							<option></option>

							<%
							List<AccountEntry> accountEntries = AccountEntryLocalServiceUtil.getAccountEntries(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

							for (AccountEntry accountEntry : accountEntries) {
							%>

								<option <%= (accountEntryId == accountEntry.getAccountEntryId()) ? "selected" : "" %> value="<%= accountEntry.getAccountEntryId() %>"><%= accountEntry.getName() %></option>

							<%
							}
							%>

						</aui:select>
					</c:when>
					<c:otherwise>
						<input name="<portlet:namespace />accountEntryId" type="hidden" value="<%= accountEntryId %>" />

						<%
						AccountEntry accountEntry = AccountEntryLocalServiceUtil.getAccountEntry(accountEntryId);
						%>

						<a href="<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="mvcPath" value="/admin/edit_account_entry.jsp" /><portlet:param name="redirect" value="<%= portletURL.toString() %>" /><portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntryId) %>" /></portlet:renderURL>"><%= accountEntry.getName() %></a>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="salesforce-opportunity-key" />
			</td>
			<td>
				<c:choose>
					<c:when test="<%= (orderEntry != null) && (orderEntry.getUserId() == OSBConstants.USER_DEFAULT_USER_ID) %>">
						<%= HtmlUtil.escape(salesforceOpportunityKey) %>

						<aui:input hidden="<%= true %>" name="salesforceOpportunityKey" value="<%= salesforceOpportunityKey %>" />
					</c:when>
					<c:otherwise>
						<aui:input label="" name="salesforceOpportunityKey" value="<%= salesforceOpportunityKey %>" />
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="purchase-order" />
			</td>
			<td>
				<liferay-ui:input-field
					bean="<%= orderEntry %>"
					field="purchaseOrderKey"
					model="<%= OrderEntry.class %>"
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
				<liferay-ui:message key="start-date" />
			</td>
			<td>
				<table class="lfr-table">
					<tr>
						<td>
							<liferay-ui:input-field
								bean="<%= orderEntry %>"
								defaultValue="<%= startCal %>"
								field="startDate"
								model="<%= OrderEntry.class %>"
							/>
						</td>
						<td>
							<liferay-ui:message key="prorated" />
						</td>
						<td>
							<input <%= prorated ? "checked" : "" %> id="<portlet:namespace />prorated" name="<portlet:namespace />prorated" onClick="document.getElementById('<portlet:namespace />actualStartDate').style.display = this.checked ? '' : 'none';" type="checkbox" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr id="<portlet:namespace />actualStartDate" style="display: <%= prorated ? "" : "none" %>;">
			<td>
				<liferay-ui:message key="actual-start-date" />
			</td>
			<td>
				<liferay-ui:input-field
					bean="<%= orderEntry %>"
					defaultValue="<%= actualStartCal %>"
					field="actualStartDate"
					model="<%= OrderEntry.class %>"
				/>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<br />
			</td>
		</tr>

		<c:if test="<%= orderEntry != null %>">
			<tr>
				<td>
					<liferay-ui:message key="renewed-count" />
				</td>
				<td>
					<%= renewCount %>
				</td>
			</tr>
		</c:if>
	</table>

	<br />

	<div>
		<aui:button type="submit" value="save" />

		<c:if test="<%= orderEntry != null %>">
			<portlet:actionURL name="renewOrderEntry" var="renewOrderEntryURL">
				<portlet:param name="mvcPath" value="/admin/edit_order_entry.jsp" />
				<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
				<portlet:param name="orderEntryId" value="<%= String.valueOf(orderEntry.getOrderEntryId()) %>" />
				<portlet:param name="renewCount" value="<%= String.valueOf(orderEntry.getRenewCount() + 1) %>" />
			</portlet:actionURL>

			<aui:button onClick='<%= "javascript:if (confirm('" + UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-renew-this-order") + "')) { location.href='" + renewOrderEntryURL + "'; } else { self.focus(); }" %>' value="renew-support" />

			<portlet:actionURL name="renewOrderEntry" var="resetOrderEntryURL">
				<portlet:param name="mvcPath" value="/admin/edit_order_entry.jsp" />
				<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
				<portlet:param name="orderEntryId" value="<%= String.valueOf(orderEntry.getOrderEntryId()) %>" />
			</portlet:actionURL>

			<aui:button onClick='<%= "javascript:if (confirm('" + UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-reset-this-order") + "')) { location.href='" + resetOrderEntryURL + "'; } else { self.focus(); }" %>' value="reset-renewals" />
		</c:if>

		<a class="btn btn-default" href="<%= HtmlUtil.escape(backURL) %>"><liferay-ui:message key="cancel" /></a>
	</div>

	<br />

	<liferay-ui:tabs
		names="offerings"
	/>

	<div>
		<portlet:renderURL var="selectOfferingDefinitionURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="mvcPath" value="/admin/select_offering_definition.jsp" />
			<portlet:param name="callback" value="selectOfferingDefinitionId" />
		</portlet:renderURL>

		<%
		String taglibSelectOfferingDefinition = "var categoryWindow = window.open('" + selectOfferingDefinitionURL + "', 'category', 'directories=no,height=768,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=1024'); void(''); categoryWindow.focus();";
		%>

		<aui:button onClick="<%= taglibSelectOfferingDefinition %>" value="add-offering" />

		<portlet:renderURL var="selectOfferingBundleURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="mvcPath" value="/admin/select_offering_bundle.jsp" />
			<portlet:param name="callback" value="selectOfferingBundle" />
		</portlet:renderURL>

		<%
		String taglibSelectOfferingBundle = "var categoryWindow = window.open('" + selectOfferingBundleURL + "', 'category', 'directories=no,height=768,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=1024'); void(''); categoryWindow.focus();";
		%>

		<aui:button onClick="<%= taglibSelectOfferingBundle %>" value="add-offering-bundle" />

		<aui:button onClick='<%= renderResponse.getNamespace() + "updateOrderEntry();" %>' value="refresh" />
	</div>

	<br />

	<liferay-ui:search-container
		headerNames="product,licenses,license-lifetime,support-tickets,sla,support-lifetime,support-end-date,sizing,quantity"
	>
		<liferay-ui:search-container-results
			results="<%= offeringEntries %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.osb.model.OfferingEntry"
			keyProperty="offeringEntryId"
			modelVar="offeringEntry"
		>

			<%
			ProductEntry productEntry = offeringEntry.getProductEntry();
			SupportResponse supportResponse = offeringEntry.getSupportResponse();

			long supportLifetime = 365 * 1440 * Time.MINUTE;

			if (offeringEntry.getSupportLifetime() > 0) {
				supportLifetime = offeringEntry.getSupportLifetime();
			}
			%>

			<liferay-ui:search-container-column-text
				name="product"
			>
				<input name="<portlet:namespace />offeringEntryId_<%= index %>" type="hidden" value="<%= offeringEntry.getOfferingEntryId() %>" />
				<input name="<portlet:namespace />productEntryId_<%= index %>" type="hidden" value="<%= offeringEntry.getProductEntryId() %>" />
				<input name="<portlet:namespace />productDescription_<%= index %>" type="hidden" value="<%= offeringEntry.getProductDescription() %>" />

				<%= HtmlUtil.escape(productEntry.getName()) %>

				<c:if test="<%= Validator.isNotNull(offeringEntry.getProductDescription()) %>">
					- <%= HtmlUtil.escape(offeringEntry.getProductDescription()) %>
				</c:if>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="licenses"
			>
				<input <%= offeringEntry.isLicenses() ? "checked" : "" %> name="<portlet:namespace />licenses_<%= index %>" type="checkbox" />
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="license-lifetime"
			>
				<aui:select label="" name='<%= "licenseLifetime_" + index %>'>

					<%
					long licenseLifetime = 36500 * 1440 * Time.MINUTE;

					if (offeringEntry.getLicenseLifetime() > 0) {
						licenseLifetime = offeringEntry.getLicenseLifetime();
					}

					for (Object[] lifetimeValues : OfferingDefinitionConstants.LIFETIME_VALUES_ARRAY) {
						long lifetimeValue = (Time.MINUTE * ((Integer)lifetimeValues[0]).intValue());
						String lifetimeLabel = String.valueOf(lifetimeValues[1]);
					%>

						<option <%= (licenseLifetime == lifetimeValue) ? "selected" : "" %> value="<%= lifetimeValue %>"><%= LanguageUtil.get(request, lifetimeLabel) %></option>

					<%
					}
					%>

				</aui:select>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="support-tickets"
			>
				<input <%= offeringEntry.isSupportTickets() ? "checked" : "" %> name="<portlet:namespace />supportTickets_<%= index %>" type="checkbox" />
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="sla"
			>
				<input name="<portlet:namespace />supportResponseId_<%= index %>" type="hidden" value="<%= offeringEntry.getSupportResponseId() %>" />

				<%= HtmlUtil.escape(supportResponse.getName()) %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="support-lifetime"
			>
				<aui:select label="" name='<%= "supportLifetime_" + index %>'>

					<%
					boolean customLifetime = true;

					for (Object[] lifetimeValues : OfferingDefinitionConstants.LIFETIME_VALUES_ARRAY) {
						long lifetimeValue = (Time.MINUTE * ((Integer)lifetimeValues[0]).intValue());
						String lifetimeLabel = String.valueOf(lifetimeValues[1]);

						if (supportLifetime == lifetimeValue) {
							customLifetime = false;
						}
					%>

						<option <%= (supportLifetime == lifetimeValue) ? "selected" : "" %> value="<%= lifetimeValue %>"><%= LanguageUtil.get(request, lifetimeLabel) %></option>

					<%
					}
					%>

					<c:if test="<%= (supportLifetime > 0) && customLifetime %>">
						<option selected value="<%= supportLifetime %>"><%= OfferingDefinitionConstants.getCustomLifetimeLabel(supportLifetime) %></option>
					</c:if>
				</aui:select>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="support-end-date"
				value="<%= longDateFormatDate.format(new Date(startCal.getTime().getTime() + supportLifetime + (Time.YEAR * renewCount))) %>"
			/>

			<liferay-ui:search-container-column-text
				name="version"
			>
				<aui:select label="" name='<%= "version_" + index %>'>

					<%
					List<ListType> productEntryVersionsTypes = productEntry.getVersionsListTypes();

					for (int i = productEntryVersionsTypes.size() - 1; i >= 0; i--) {
						ListType productEntryVersionsType = productEntryVersionsTypes.get(i);
					%>

						<option <%= (offeringEntry.getVersion() == productEntryVersionsType.getListTypeId()) ? "selected" : "" %> value="<%= productEntryVersionsType.getListTypeId() %>"><liferay-ui:message key="<%= productEntryVersionsType.getName() %>" /></option>

					<%
					}
					%>

				</aui:select>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="sizing"
			>
				<aui:select label="" name='<%= "sizing_" + index %>'>

					<%
					for (int i = 1; i <= 4; i++) {
					%>

						<option <%= (offeringEntry.getSizing() == i) ? "selected" : "" %> value="<%= i %>"><liferay-ui:message key="<%= OfferingEntryConstants.getSizingLabel(i) %>" /></option>

					<%
					}
					%>

				</aui:select>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="quantity"
			>
				<aui:input label="" name='<%= "quantity_" + index %>' size="2" type="text" value="<%= offeringEntry.getQuantity() %>" />
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
			paginate="<%= false %>"
		/>
	</liferay-ui:search-container>
</aui:form>