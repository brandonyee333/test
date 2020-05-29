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
	if ((orderEntry != null) && (orderEntry.getStartDate() != null)) {
		startCal.setTime(orderEntry.getStartDate());
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
	if ((orderEntry != null) && (orderEntry.getActualStartDate() != null)) {
		actualStartCal.setTime(orderEntry.getActualStartDate());
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
	int type = ParamUtil.getInteger(request, "type_" + i);
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
	offeringEntry.setType(type);
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
		var input = document.createElement('input');

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
			<portlet:namespace />addInput('type_' + offeringEntriesCount.val(), <%= OfferingEntryConstants.TYPE_REGULAR %>);
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
		<portlet:namespace />addInput('type_' + offeringEntriesCount.val(), <%= OfferingEntryConstants.TYPE_REGULAR %>);
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
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="orderEntryId" type="hidden" value="<%= orderEntryId %>" />
	<aui:input name="offeringEntriesCount" type="hidden" value="<%= offeringEntries.size() %>" />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="order"
	/>

	<liferay-util:include page="/common/exception.jsp" servletContext="<%= application %>" />

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

				<span class="txt-sb">
					<%= orderEntry.getUuid() %>
				</span>
			</span>
			<span class="spacer"></span>

			<span class="segment">
				<liferay-ui:message key="created-by" />:

				<span class="txt-sb">
					<%= HtmlUtil.escape(PortalUtil.getUserName(orderEntry.getUserId(), orderEntry.getUserName())) %> <liferay-ui:message key="on" /> <%= longDateFormatDateTime.format(orderEntry.getCreateDate()) %>
				</span>
			</span>
			<span class="spacer"></span>

			<span class="segment">
				<liferay-ui:message key="last-modified" />:

				<span class="txt-sb">
					<%= HtmlUtil.escape(PortalUtil.getUserName(orderEntry.getModifiedUserId(), orderEntry.getModifiedUserName())) %> <liferay-ui:message key="on" /> <%= longDateFormatDateTime.format(orderEntry.getModifiedDate()) %>
				</span>
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

						workflowTaskURL.setParameter("mvcPath", "/edit_workflow_task.jsp");
						workflowTaskURL.setParameter("redirect", currentURL);

						WorkflowTask workflowTask = workflowTasks.get(0);

						workflowTaskURL.setParameter("workflowTaskId", String.valueOf(workflowTask.getWorkflowTaskId()));
						%>

						<aui:a href="<%= workflowTaskURL.toString() %>" label="<%= orderEntry.getStatusLabel() %>" target="_blank" />
					</c:when>
					<c:otherwise>
						<span class="txt-sb">
							<%= LanguageUtil.get(request, orderEntry.getStatusLabel()) %>
						</span>
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

						<%
						AccountEntry accountEntry = null;

						if (accountEntryId > 0) {
							accountEntry = AccountEntryLocalServiceUtil.fetchAccountEntry(accountEntryId);
						}
						%>

						<strong id="<portlet:namespace />accountEntryName"><%= (accountEntry != null) ? HtmlUtil.escape(accountEntry.getName()) : "" %></strong>

						<input onClick="var accountEntryWindow = window.open('<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/admin/select_account_entry.jsp" /><portlet:param name="callback" value="selectAccountEntry" /></portlet:renderURL>', 'account-entry', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); accountEntryWindow.focus();" type="button" value="<liferay-ui:message key="select" />" />

						<aui:input name="accountEntryId" type="hidden" value="<%= accountEntryId %>" />
					</c:when>
					<c:otherwise>
						<aui:input name="accountEntryId" type="hidden" value="<%= accountEntryId %>" />

						<%
						AccountEntry accountEntry = AccountEntryLocalServiceUtil.getAccountEntry(accountEntryId);
						%>

						<portlet:renderURL var="editAccountEntryURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
							<portlet:param name="mvcPath" value="/admin/edit_account_entry.jsp" />
							<portlet:param name="backURL" value="<%= portletURL.toString() %>" />
							<portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntryId) %>" />
						</portlet:renderURL>

						<aui:a href="<%= editAccountEntryURL %>" label="<%= accountEntry.getName() %>" />
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
							<aui:input checked="<%= prorated %>" name="prorated" onClick='<%= "document.getElementById('" + renderResponse.getNamespace() + "actualStartDate').style.display = this.checked ? '' : 'none';" %>' type="checkbox" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr id="<portlet:namespace />actualStartDate" style='display: <%= prorated ? "" : "none" %>;'>
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

		<aui:a cssClass="btn btn-default" href="<%= backURL %>" label="cancel" />
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
				<aui:input name='<%= "offeringEntryId_" + index %>' type="hidden" value="<%= offeringEntry.getOfferingEntryId() %>" />
				<aui:input name='<%= "productEntryId_" + index %>' type="hidden" value="<%= offeringEntry.getProductEntryId() %>" />
				<aui:input name='<%= "type_" + index %>' type="hidden" value="<%= offeringEntry.getType() %>" />
				<aui:input name='<%= "productDescription_" + index %>' type="hidden" value="<%= offeringEntry.getProductDescription() %>" />

				<%= HtmlUtil.escape(productEntry.getName()) %>

				<c:if test="<%= Validator.isNotNull(offeringEntry.getProductDescription()) %>">
					- <%= HtmlUtil.escape(offeringEntry.getProductDescription()) %>
				</c:if>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="licenses"
			>
				<aui:input checked="<%= offeringEntry.isLicenses() %>" label="" name='<%= "licenses_" + index %>' type="checkbox" />
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
						long lifetimeValue = Time.MINUTE * ((Integer)lifetimeValues[0]).intValue();
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
				<aui:input checked="<%= offeringEntry.isSupportTickets() %>" label="" name='<%= "supportTickets_" + index %>' type="checkbox" />
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="sla"
			>
				<aui:input name='<%= "supportResponseId_" + index %>' type="hidden" value="<%= offeringEntry.getSupportResponseId() %>" />

				<%= HtmlUtil.escape(supportResponse.getName()) %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="support-lifetime"
			>
				<aui:select label="" name='<%= "supportLifetime_" + index %>'>

					<%
					boolean customLifetime = true;

					for (Object[] lifetimeValues : OfferingDefinitionConstants.LIFETIME_VALUES_ARRAY) {
						long lifetimeValue = Time.MINUTE * ((Integer)lifetimeValues[0]).intValue();
						String lifetimeLabel = String.valueOf(lifetimeValues[1]);

						if (supportLifetime == lifetimeValue) {
							customLifetime = false;
						}
					%>

						<aui:option label="<%= lifetimeLabel %>" selected="<%= supportLifetime == lifetimeValue %>" value="<%= lifetimeValue %>" />

					<%
					}
					%>

					<c:if test="<%= (supportLifetime > 0) && customLifetime %>">
						<aui:option label="<%= OfferingDefinitionConstants.getCustomLifetimeLabel(supportLifetime) %>" selected="<%= true %>" value="<%= supportLifetime %>" />
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

						<aui:option label="<%= productEntryVersionsType.getName() %>" selected="<%= offeringEntry.getVersion() == productEntryVersionsType.getListTypeId() %>" value="<%= productEntryVersionsType.getListTypeId() %>" />

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

						<aui:option label="<%= OfferingEntryConstants.getSizingLabel(i) %>" selected="<%= offeringEntry.getSizing() == i %>" value="<%= i %>" />

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

<aui:script>
	function <portlet:namespace />selectAccountEntry(accountEntryId, accountEntryName) {
		document.<portlet:namespace />fm.<portlet:namespace />accountEntryId.value = accountEntryId;

		document.getElementById('<portlet:namespace />accountEntryName').innerHTML = accountEntryName;
	}
</aui:script>