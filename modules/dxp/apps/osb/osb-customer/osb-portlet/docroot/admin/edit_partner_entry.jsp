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

long partnerEntryId = ParamUtil.getLong(request, "partnerEntryId");

PartnerEntry partnerEntry = null;

try {
	partnerEntry = PartnerEntryLocalServiceUtil.getPartnerEntry(partnerEntryId);
}
catch (NoSuchPartnerEntryException nspee) {
}

long parentPartnerEntryId = BeanParamUtil.getLong(partnerEntry, request, "parentPartnerEntryId");

PartnerEntry parentPartnerEntry = null;

try {
	parentPartnerEntry = PartnerEntryLocalServiceUtil.getPartnerEntry(parentPartnerEntryId);
}
catch (NoSuchPartnerEntryException nspee) {
}

List<AccountEntry> accountEntries = Collections.emptyList();
List<PartnerEntry> childPartnerEntries = Collections.emptyList();

long supportRegionId = ParamUtil.getLong(request, "supportRegionId");

if (partnerEntry != null) {
	accountEntries = partnerEntry.getAccountEntries();
	childPartnerEntries = partnerEntry.getChildPartnerEntries(true);

	SupportRegion supportRegion = partnerEntry.getSupportRegion();

	if ((supportRegionId == 0) && (supportRegion != null)) {
		supportRegionId = supportRegion.getSupportRegionId();
	}
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/edit_partner_entry.jsp");
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("partnerEntryId", String.valueOf(partnerEntryId));
%>

<portlet:actionURL name="updatePartnerEntry" var="updatePartnerEntryURL">
	<portlet:param name="mvcPath" value="/admin/edit_partner_entry.jsp" />
</portlet:actionURL>

<aui:form action="<%= updatePartnerEntryURL %>" method="post">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="partnerEntryId" type="hidden" value="<%= partnerEntryId %>" />
	<aui:input name="parentPartnerEntryId" type="hidden" value="<%= parentPartnerEntryId %>" />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="partner"
	/>

	<liferay-ui:error exception="<%= DuplicatePartnerEntryCodeException.class %>" message="please-enter-a-unique-code" />
	<liferay-ui:error exception="<%= DuplicatePartnerEntryDossieraAccountKeyException.class %>" message="please-enter-a-unique-dossiera-account-key" />
	<liferay-ui:error exception="<%= PartnerEntryCodeException.class %>" message="please-enter-a-valid-code" />
	<liferay-ui:error exception="<%= PartnerEntryParentPartnerEntryException.class %>" message="please-enter-a-valid-parent-partner-entry" />

	<aui:model-context bean="<%= partnerEntry %>" model="<%= PartnerEntry.class %>" />

	<table class="lfr-table">
		<c:if test="<%= parentPartnerEntry != null %>">
			<tr>
				<td>
					<liferay-ui:message key="parent-partner" />
				</td>
				<td>
					<strong><%= HtmlUtil.escape(parentPartnerEntry.getCode()) %></strong>
				</td>
			</tr>
		</c:if>

		<c:if test="<%= parentPartnerEntry == null %>">
			<tr>
				<td>
					<liferay-ui:message key="dossiera-account-key" />
				</td>
				<td>
					<aui:input label="" name="dossieraAccountKey" />
				</td>
			</tr>
		</c:if>

		<tr>
			<td>
				<liferay-ui:message key="code" />
			</td>
			<td>
				<aui:input label="" name="code" />
			</td>
		</tr>

		<c:if test="<%= partnerEntry != null %>">
			<tr>
				<td colspan="2">
					<div class="hide portlet-msg-info" id="<portlet:namespace />statusMessageDisplay">
						<liferay-ui:message key="there-are-partner-teams-that-are-open" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<liferay-ui:message key="status" />
				</td>
				<td>

					<%
					String taglibOnChange = StringPool.BLANK;

					if (!childPartnerEntries.isEmpty()) {
						taglibOnChange = renderResponse.getNamespace() + "toggleMessage(this.value);";
					}
					%>

					<aui:select label="" name="status" onChange="<%= taglibOnChange %>">
						<aui:option label="active" selected="<%= partnerEntry.getStatus() == WorkflowConstants.STATUS_APPROVED %>" value="<%= WorkflowConstants.STATUS_APPROVED %>" />
						<aui:option label="inactive" selected="<%= partnerEntry.getStatus() == WorkflowConstants.STATUS_INACTIVE %>" value="<%= WorkflowConstants.STATUS_INACTIVE %>" />
					</aui:select>
				</td>
			</tr>
		</c:if>

		<c:if test="<%= partnerEntry != null %>">
			<tr>
				<td>
					<liferay-ui:message key="created-by" />
				</td>
				<td>
					<%= HtmlUtil.escape(PortalUtil.getUserName(partnerEntry.getUserId(), partnerEntry.getUserName())) %>
				</td>
			</tr>
			<tr>
				<td>
					<liferay-ui:message key="last-modified" />
				</td>
				<td>
					<%= HtmlUtil.escape(PortalUtil.getUserName(partnerEntry.getModifiedUserId(), partnerEntry.getModifiedUserName())) %> <liferay-ui:message key="on" /> <%= longDateFormatDateTime.format(partnerEntry.getModifiedDate()) %>
				</td>
			</tr>
		</c:if>

		<tr>
			<td>
				<liferay-ui:message key="additional-notes" />
			</td>
			<td>
				<aui:input label="" name="notes" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="support-region" />
			</td>
			<td>
				<aui:select label="" name="supportRegionId">
					<aui:option value="" />

					<%
					for (SupportRegion curSupportRegion : SupportRegionLocalServiceUtil.getSupportRegions(QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {
					%>

						<aui:option label="<%= curSupportRegion.getName() %>" selected="<%= supportRegionId == curSupportRegion.getSupportRegionId() %>" value="<%= curSupportRegion.getSupportRegionId() %>" />

					<%
					}
					%>

				</aui:select>
			</td>
		</tr>
		<tr>
			<td colspan="2">

				<%
				request.setAttribute("phones.className", PartnerEntry.class.getName());
				request.setAttribute("phones.classPK", partnerEntryId);
				%>

				<div id="<portlet:namespace />phoneNumbers">
					<liferay-util:include page="/common/phone_numbers.jsp" portletId="<%= PortletProviderUtil.getPortletId(User.class.getName(), PortletProvider.Action.VIEW) %>" />
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2">

				<%
				request.setAttribute("addresses.className", PartnerEntry.class.getName());
				request.setAttribute("addresses.classPK", partnerEntryId);
				%>

				<div id="<portlet:namespace />addresses">
					<liferay-util:include page="/common/addresses.jsp" portletId="<%= PortletProviderUtil.getPortletId(User.class.getName(), PortletProvider.Action.VIEW) %>" />
				</div>
			</td>
		</tr>
	</table>

	<br />

	<c:if test="<%= (partnerEntry != null) && Validator.isNull(partnerEntry.getDossieraAccountKey()) %>">
		<div class="portlet-msg-info">
			<liferay-ui:message key="dossiera-account-key-is-required-to-assign-workers" />
		</div>
	</c:if>

	<div>
		<aui:button type="submit" value="save" />

		<c:if test="<%= partnerEntry != null %>">
			<c:if test="<%= Validator.isNotNull(partnerEntry.getDossieraAccountKey()) %>">
				<portlet:renderURL var="assignWorkersURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
					<portlet:param name="mvcPath" value="/admin/edit_partner_entry_workers.jsp" />
					<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
					<portlet:param name="partnerEntryId" value="<%= String.valueOf(partnerEntry.getPartnerEntryId()) %>" />
				</portlet:renderURL>

				<aui:a cssClass="btn btn-default" href="<%= assignWorkersURL %>" label="assign-workers" />
			</c:if>

			<portlet:renderURL var="addChildPartnerURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
				<portlet:param name="mvcPath" value="/admin/edit_partner_entry.jsp" />
				<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
				<portlet:param name="parentPartnerEntryId" value="<%= String.valueOf(partnerEntry.getPartnerEntryId()) %>" />
			</portlet:renderURL>

			<aui:a cssClass="btn btn-default" href="<%= addChildPartnerURL %>" label="add-partner-team" />
		</c:if>

		<aui:a cssClass="btn btn-default" href="<%= backURL %>" label="cancel" />
	</div>

	<c:if test="<%= !accountEntries.isEmpty() %>">
		<br />

		<liferay-ui:tabs
			names="projects"
		/>

		<liferay-ui:search-container>
			<liferay-ui:search-container-results
				results="<%= accountEntries %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.osb.model.AccountEntry"
				escapedModel="<%= true %>"
				keyProperty="accountEntryId"
				modelVar="accountEntry"
			>
				<portlet:renderURL var="rowURL">
					<portlet:param name="mvcPath" value="/admin/edit_account_entry.jsp" />
					<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
					<portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntry.getAccountEntryId()) %>" />
				</portlet:renderURL>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="name"
					value="<%= accountEntry.getName() %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="first-line-support"
					value='<%= accountEntry.isPartnerManagedSupport() ? LanguageUtil.get(request, "yes") : LanguageUtil.get(request, "no") %>'
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
				paginate="<%= false %>"
			/>
		</liferay-ui:search-container>
	</c:if>

	<c:if test="<%= !childPartnerEntries.isEmpty() %>">
		<br />

		<div class="child-partners">
			<liferay-ui:tabs
				names="partner-teams"
			/>

			<%
			Map<Long, Integer> depthMap = new HashMap<Long, Integer>();

			for (PartnerEntry childPartnerEntry : childPartnerEntries) {
				int depth = 0;

				if (depthMap.containsKey(childPartnerEntry.getParentPartnerEntryId())) {
					depth = depthMap.get(childPartnerEntry.getParentPartnerEntryId()) + 1;
				}

				depthMap.put(childPartnerEntry.getPartnerEntryId(), depth);
			%>

				<div style="padding-left: <%= depth * 25 %>px;">
					<portlet:renderURL var="partnerEntryURL">
						<portlet:param name="mvcPath" value="/admin/edit_partner_entry.jsp" />
						<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
						<portlet:param name="partnerEntryId" value="<%= String.valueOf(childPartnerEntry.getPartnerEntryId()) %>" />
					</portlet:renderURL>

					<h4>
						<aui:a href="<%= partnerEntryURL %>" label="<%= childPartnerEntry.getCode() %>" />
					</h4>

					<liferay-ui:search-container>
						<liferay-ui:search-container-results
							results="<%= childPartnerEntry.getAccountEntries() %>"
						/>

						<liferay-ui:search-container-row
							className="com.liferay.osb.model.AccountEntry"
							escapedModel="<%= true %>"
							keyProperty="accountEntryId"
							modelVar="accountEntry"
						>
							<portlet:renderURL var="rowURL">
								<portlet:param name="mvcPath" value="/admin/edit_account_entry.jsp" />
								<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
								<portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntry.getAccountEntryId()) %>" />
							</portlet:renderURL>

							<liferay-ui:search-container-column-text
								href="<%= rowURL %>"
								name="projects"
								value="<%= accountEntry.getName() %>"
							/>

							<liferay-ui:search-container-column-text
								href="<%= rowURL %>"
								name="first-line-support"
								value='<%= accountEntry.isPartnerManagedSupport() ? LanguageUtil.get(request, "yes") : LanguageUtil.get(request, "no") %>'
							/>
						</liferay-ui:search-container-row>

						<liferay-ui:search-iterator
							markupView="lexicon"
							paginate="<%= false %>"
						/>
					</liferay-ui:search-container>
				</div>

			<%
			}
			%>

		</div>
	</c:if>
</aui:form>

<aui:script>
	function <portlet:namespace />toggleMessage(status) {
		var A = AUI();

		var statusMessageDisplay = A.one('#<portlet:namespace />statusMessageDisplay');

		if (status == <%= WorkflowConstants.STATUS_INACTIVE %>) {
			statusMessageDisplay.show();
		}
		else {
			statusMessageDisplay.hide();
		}
	}
</aui:script>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<script type="text/javascript">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />code);
	</script>
</c:if>