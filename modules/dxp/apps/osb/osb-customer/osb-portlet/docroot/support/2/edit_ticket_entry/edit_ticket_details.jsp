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

<%@ include file="/support/2/init.jsp" %>

<%
TicketEntry ticketEntry = (TicketEntry)request.getAttribute(OSBWebKeys.OSB_TICKET_ENTRY);

AccountEntry accountEntry = (AccountEntry)request.getAttribute("edit_ticket_entry_dialog.jsp-accountEntry");
ProductEntry productEntry = (ProductEntry)request.getAttribute("edit_ticket_entry_dialog.jsp-productEntry");
boolean hasUpdateAdmin = (Boolean)request.getAttribute("edit_ticket_entry_dialog.jsp-hasUpdateAdmin");
boolean hasUpdateAdvanced = (Boolean)request.getAttribute("edit_ticket_entry_dialog.jsp-hasUpdateAdvanced");
int component = (Integer)request.getAttribute("edit_ticket_entry_dialog.jsp-component");

String description = BeanParamUtil.getString(ticketEntry, request, "description");
String reproductionSteps = BeanParamUtil.getString(ticketEntry, request, "reproductionSteps");
int escalationLevel = BeanParamUtil.getInteger(ticketEntry, request, "escalationLevel");
int resolution = BeanParamUtil.getInteger(ticketEntry, request, "resolution");
int severity = BeanParamUtil.getInteger(ticketEntry, request, "severity");
int status = BeanParamUtil.getInteger(ticketEntry, request, "status");
int subcomponent = BeanParamUtil.getInteger(ticketEntry, request, "subcomponent");
String subcomponentCustom = BeanParamUtil.getString(ticketEntry, request, "subcomponentCustom");
String subject = BeanParamUtil.getString(ticketEntry, request, "subject");
long supportRegionId = BeanParamUtil.getLong(ticketEntry, request, "supportRegionId");
String languageId = BeanParamUtil.getString(ticketEntry, request, "languageId");

boolean partnerWorker = false;

if (accountEntry.isPartnerManagedSupport() && PartnerWorkerLocalServiceUtil.hasPartnerWorker(user.getUserId(), accountEntry.getPartnerEntryId())) {
	partnerWorker = true;
}

boolean ticketWorker = false;

if (liferayIncOrg || partnerWorker) {
	ticketWorker = true;
}
%>

<liferay-ui:error exception="<%= TicketEntryComponentException.class %>" message="please-select-a-valid-component" />
<liferay-ui:error exception="<%= TicketEntryDescriptionException.class %>" message="please-enter-a-description-of-the-issue" />
<liferay-ui:error exception="<%= TicketEntryResolutionException.class %>" message="please-select-a-valid-resolution" />
<liferay-ui:error exception="<%= TicketEntrySeverityException.class %>" message="please-select-a-valid-severity" />
<liferay-ui:error exception="<%= TicketEntryStatusException.class %>" message="please-select-a-valid-status" />
<liferay-ui:error exception="<%= TicketEntrySubcomponentException.class %>" message="please-provide-a-valid-subcomponent" />
<liferay-ui:error exception="<%= TicketEntrySubjectException.class %>" message="please-enter-a-valid-subject" />
<liferay-ui:error exception="<%= TicketEntryWeightException.class %>" message="please-enter-a-valid-weight" />

<div class="tab-content-tab" id="<portlet:namespace />ticketDetails">
	<c:if test="<%= hasUpdateAdvanced %>">
		<div class="field-group">
			<label id="<portlet:namespace />subjectLabel"><liferay-ui:message key="subject" /></label>

			<div class="field-align" field-required-message="<%= LanguageUtil.get(request, "please-enter-a-valid-subject") %>">
				<span class="inline long-field">
					<aui:input bean="<%= ticketEntry %>" data-field-required-status="<%= false %>" id="subject" label="" maxLength='<%= ModelHintsUtil.getMaxLength(TicketEntry.class.getName(), "subject") %>' model="<%= TicketEntry.class %>" name="subject" type="text" />
				</span>
			</div>
		</div>
	</c:if>

	<c:if test="<%= hasUpdateAdvanced %>">
		<div class="field-group">
			<label id="<portlet:namespace />descriptionLabel"><liferay-ui:message key="description" /></label>

			<liferay-util:include page="/common/textarea.jsp" servletContext="<%= application %>">
				<liferay-util:param name="content" value="<%= description %>" />
				<liferay-util:param name="editorId" value="description" />
				<liferay-util:param name="fieldRequired" value="<%= Boolean.TRUE.toString() %>" />
				<liferay-util:param name="fieldRequiredMessage" value='<%= LanguageUtil.get(request, "please-enter-a-description-of-the-issue") %>' />
				<liferay-util:param name="height" value="150" />
				<liferay-util:param name="name" value="description" />
				<liferay-util:param name="showCounter" value="<%= String.valueOf(true) %>" />
			</liferay-util:include>
		</div>
	</c:if>

	<c:choose>
		<c:when test="<%= hasUpdateAdvanced && liferayIncOrg %>">
			<div class="field-group">
				<label id="<portlet:namespace />reproductionStepsLabel"><liferay-ui:message key="reproduction-steps" /></label>

				<textarea id="<portlet:namespace />reproductionSteps" maxlength="<%= ModelHintsConstants.TEXTAREA_MAX_LENGTH %>" name="<portlet:namespace />reproductionSteps" style="height: 150px; width: 100%;" wrap="soft"><%= HtmlUtil.escape(reproductionSteps) %></textarea>
			</div>
		</c:when>
		<c:otherwise>
			<input name="<portlet:namespace />reproductionSteps" type="hidden" value="<%= HtmlUtil.escapeAttribute(reproductionSteps) %>" />
		</c:otherwise>
	</c:choose>

	<c:if test="<%= hasUpdateAdvanced && liferayIncOrg %>">
		<div class="field-group">
			<label id="<portlet:namespace />productLabel"><liferay-ui:message key="product" /></label>

			<span class="field-input-align inline" id="<portlet:namespace />productEntryName">
				<%= HtmlUtil.escape(productEntry.getName()) %>
			</span>

			<portlet:renderURL var="chooseProductURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="mvcPath" value="/support/2/select_offering_entry.jsp" />
				<portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntry.getTicketEntryId()) %>" />
			</portlet:renderURL>

			<input class="aui-button-input" onClick="var offeringEntryWindow = window.open('<%= chooseProductURL %>', 'offering_entry', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=900'); void(''); offeringEntryWindow.focus();" type="button" value="<liferay-ui:message key="choose" />" />
		</div>
	</c:if>

	<c:if test="<%= hasUpdateAdvanced %>">
		<div class="field-group <%= productEntry.isTicketComponentRequired() ? "" : "hide" %>" id="<portlet:namespace />componentFieldGroup">
			<label id="<portlet:namespace />componentLabel"><liferay-ui:message key="component" /></label>

			<select id="<portlet:namespace />editComponent" name="<portlet:namespace />component" onChange="<%= renderResponse.getNamespace() %>selectComponent(this.value);">
				<c:choose>
					<c:when test="<%= productEntry.isDigitalEnterprise() %>">

						<%
						for (String componentGroup : TicketEntryConstants.COMPONENT_GROUPS_DE) {
						%>

							<optgroup label="<%= LanguageUtil.get(request, componentGroup) %>">

								<%
								for (int curComponent : TicketEntryConstants.getGroupComponents(componentGroup)) {
								%>

									<option <%= (curComponent == component) ? "selected" : "" %> value="<%= curComponent %>"><%= LanguageUtil.get(request, TicketEntryConstants.getComponentLabel(curComponent)) %></option>

								<%
								}
								%>

							</optgroup>

						<%
						}
						%>

					</c:when>
					<c:otherwise>

						<%
						for (int curComponent : TicketEntryConstants.getProductComponents(productEntry)) {
						%>

							<option <%= (curComponent == component) ? "selected" : "" %> value="<%= curComponent %>"><%= LanguageUtil.get(request, TicketEntryConstants.getComponentLabel(curComponent)) %></option>

						<%
						}
						%>

					</c:otherwise>
				</c:choose>
			</select>
		</div>
	</c:if>

	<c:if test="<%= hasUpdateAdvanced && liferayIncOrg %>">
		<div class="field-group <%= productEntry.isTicketComponentRequired() ? "" : "hide" %>" id="<portlet:namespace />subcomponentFieldGroup">
			<label id="<portlet:namespace />subcomponentLabel"><liferay-ui:message key="subcomponent" /></label>

			<%
			int[] subcomponents = TicketEntryConstants.getSubcomponents(component);
			%>

			<select id="<portlet:namespace />subcomponent" name="<portlet:namespace />subcomponent">
				<c:if test="<%= ticketEntry.getSubcomponent() <= 0 %>">
					<option value="0"><liferay-ui:message key="none" /></option>
				</c:if>

				<c:if test="<%= !ArrayUtil.contains(subcomponents, TicketEntryConstants.SUBCOMPONENT_OTHER) %>">
					<option <%= (subcomponent == TicketEntryConstants.SUBCOMPONENT_OTHER) ? "selected" : "" %> value="<%= TicketEntryConstants.SUBCOMPONENT_OTHER %>"><liferay-ui:message key="other" /></option>
				</c:if>

				<%
				for (int curSubcomponent : subcomponents) {
				%>

					<option <%= (curSubcomponent == subcomponent) ? "selected" : "" %> value="<%= curSubcomponent %>"><%= LanguageUtil.get(request, TicketEntryConstants.getSubcomponentLabel(curSubcomponent)) %></option>

				<%
				}
				%>

			</select>

			<input class="<%= (subcomponent == TicketEntryConstants.SUBCOMPONENT_OTHER) ? "" : "hide" %>" name="<portlet:namespace />subcomponentCustom" type="text" value="<%= HtmlUtil.escapeAttribute(subcomponentCustom) %>" />
		</div>
	</c:if>

	<c:if test="<%= hasUpdateAdvanced %>">
		<div class="field-group">
			<label id="<portlet:namespace />escalationLevelLabel"><liferay-ui:message key="escalation-level" /></label>

			<select name="<portlet:namespace />escalationLevel">

				<%
				for (ListType escalationLevelType : ListTypeServiceUtil.getListTypes(TicketEntryConstants.LIST_TYPE_ESCALATION_LEVEL)) {
				%>

					<option <%= (escalationLevelType.getListTypeId() == escalationLevel) ? "selected" : "" %> value="<%= escalationLevelType.getListTypeId() %>"><%= LanguageUtil.get(request, escalationLevelType.getName()) %></option>

				<%
				}
				%>

			</select>
		</div>
	</c:if>

	<c:if test="<%= hasUpdateAdvanced %>">
		<div class="field-group">
			<label id="<portlet:namespace />severityLabel"><liferay-ui:message key="severity" /></label>

			<%
			String severityOnChange = StringPool.BLANK;

			if (liferayIncOrg) {
				severityOnChange = renderResponse.getNamespace() + "selectSeverity(" + severity + ", this.value);";
			}
			%>

			<select name="<portlet:namespace />severity" onChange="<%= severityOnChange %>">

				<%
				for (int i = 1; i <= 3; i++) {
				%>

					<option <%= (i == severity) ? "selected" : "" %> value="<%= i %>"><%= LanguageUtil.get(request, TicketEntryConstants.getSeverityLabel(i)) %></option>

				<%
				}
				%>

			</select>
		</div>
	</c:if>

	<c:if test="<%= hasUpdateAdvanced && liferayIncOrg %>">
		<div class="field-group" id="<portlet:namespace />severityDetails" style="display: none;">
			<label class="field-modified" id="<portlet:namespace />severityDetailsLabel"><liferay-ui:message key="severity-details" /></label>

			<span class="field-input-align inline long-field">
				<liferay-ui:message key="what-is-the-reason-for-changing-the-ticket-severity" />

				<div class="inline">
					<select class="long-field" id="<portlet:namespace />severityReason" name="<portlet:namespace />severityReason">
					</select>
				</div>

				<div id="<portlet:namespace />severityReasonLabel"></div>

				<br />

				<liferay-ui:message key="please-explain-optional" />

				<textarea name="<portlet:namespace />severityReasonComments" onKeyDown="Liferay.Util.checkTab(this); Liferay.Util.disableEsc();" style="height: 150px; max-width: 505px; width: 100%;" wrap="soft"></textarea>
			</span>
		</div>
	</c:if>

	<c:if test="<%= hasUpdateAdvanced %>">
		<div class="field-group" id="<portlet:namespace/>statusDropDown">
			<label id="<portlet:namespace />statusLabel"><liferay-ui:message key="status" /></label>

			<select data-field-required-status="<%= true %>" id="<portlet:namespace />status" name="<portlet:namespace />status">

				<%
				for (int statusId : TicketEntryConstants.STATUSES_WORKFLOW_ORDER) {
					if ((statusId == TicketEntryConstants.STATUS_INACTIVE) || (statusId == TicketEntryConstants.STATUS_PENDING_WORKER) || ((statusId == TicketEntryConstants.STATUS_RESOLVED_IN_PRODUCTION) && !OSBTicketEntryPermission.contains(permissionChecker, ticketEntry, OSBActionKeys.UPDATE_SOLUTION)) || ((statusId == TicketEntryConstants.STATUS_SOLUTION_DELIVERED) && (status != TicketEntryConstants.STATUS_SOLUTION_PROPOSED)) || (statusId == TicketEntryConstants.STATUS_SOLUTION_PROPOSED)) {
						if (statusId != status) {
							continue;
						}
					}
				%>

					<option <%= (statusId == status) ? "selected" : "" %> value="<%= statusId %>"><%= LanguageUtil.get(request, TicketEntryConstants.getStatusLabel(statusId)) %></option>

				<%
				}
				%>

			</select>
		</div>
	</c:if>

	<c:if test="<%= hasUpdateAdvanced %>">
		<div class="field-group" id="<portlet:namespace/>resolutionDropDown">
			<label id="<portlet:namespace />resolutionLabel"><liferay-ui:message key="resolution" /></label>

			<div class="field-align">
				<select data-field-required-status="<%= false %>" field-required-message="<%= LanguageUtil.get(request, "please-select-a-valid-resolution") %>" id="<portlet:namespace />resolution" name="<portlet:namespace />resolution">
					<option value="0"></option>

					<%
					for (ListType resolutionType : ListTypeServiceUtil.getListTypes(TicketEntryConstants.LIST_TYPE_RESOLUTION)) {
					%>

						<option <%= (resolutionType.getListTypeId() == resolution) ? "selected" : "" %> value="<%= resolutionType.getListTypeId() %>"><%= LanguageUtil.get(request, resolutionType.getName()) %></option>

					<%
					}
					%>

				</select>
			</div>

			<input class="aui-button-input" id="<portlet:namespace/>resolutionCancel" onclick="<portlet:namespace />resetStatus()" style="display: none;" type="button" value="<liferay-ui:message key="cancel" />" />
		</div>
	</c:if>

	<c:if test="<%= !screenShareMode && (ticketEntry.getStatus() != TicketEntryConstants.STATUS_CLOSED) && ticketWorker %>">
		<div class="field-group">
			<label id="<portlet:namespace />pendingTypesLabel"><liferay-ui:message key="need-response-from" /></label>

			<span class="field-input-align inline">

				<%
				int[] pendingTypes = TicketFlagLocalServiceUtil.getTicketFlagTypes(ticketEntry.getTicketEntryId(), TicketFlagConstants.TYPES_PENDING, TicketFlagConstants.FLAG_TRUE);

				for (int pendingType : TicketFlagConstants.TYPES_PENDING) {
				%>

					<input <%= ArrayUtil.contains(pendingTypes, pendingType) ? "checked=\"checked\"" : "" %> name="<portlet:namespace />pendingTypes" type="checkbox" value="<%= pendingType %>" />

					<liferay-ui:message key="<%= TicketFlagConstants.getTypeLabel(pendingType) %>" />

				<%
				}
				%>

			</span>
		</div>
	</c:if>

	<c:choose>
		<c:when test="<%= hasUpdateAdvanced && liferayIncOrg %>">
			<div class="field-group">
				<label id="<portlet:namespace />supportRegionIdLabel"><liferay-ui:message key="support-region" /></label>

				<select name="<portlet:namespace />supportRegionId">

					<%
					List<SupportRegion> supportRegions = SupportRegionLocalServiceUtil.getSupportRegions(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

					for (SupportRegion supportRegion : supportRegions) {
					%>

						<option <%= (supportRegion.getSupportRegionId() == supportRegionId) ? "selected" : "" %> value="<%= supportRegion.getSupportRegionId() %>"><%= HtmlUtil.escape(supportRegion.getName()) %></option>

					<%
					}
					%>

				</select>
			</div>
		</c:when>
		<c:otherwise>
			<input name="<portlet:namespace />supportRegionId" type="hidden" value="<%= supportRegionId %>" />
		</c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="<%= hasUpdateAdvanced && liferayIncOrg %>">
			<div class="field-group">
				<label id="<portlet:namespace />languageIdLabel"><liferay-ui:message key="support-language" /></label>

				<select name="<portlet:namespace />languageId">

					<%
					for (String curLanguageId : AccountEntryConstants.LANGUAGES) {
					%>

						<option <%= languageId.equals(curLanguageId) ? "selected" : "" %> value="<%= curLanguageId %>"><%= LanguageUtil.get(request, AccountEntryConstants.getLanguageLabel(curLanguageId)) %></option>

					<%
					}
					%>

				</select>
			</div>
		</c:when>
		<c:otherwise>
			<input name="<portlet:namespace />languageId" type="hidden" value="<%= HtmlUtil.escapeAttribute(languageId) %>" />
		</c:otherwise>
	</c:choose>

	<c:if test="<%= hasUpdateAdmin %>">
		<div class="field-group">
			<label id="<portlet:namespace />dueDateLabel"><liferay-ui:message key="due" /></label>

			<%
			Calendar dueDate = CalendarFactoryUtil.getCalendar(timeZone, locale);

			if (ticketEntry.getDueDate() != null) {
				dueDate.setTime(ticketEntry.getDueDate());
			}
			%>

			<liferay-ui:input-field bean="<%= ticketEntry %>" defaultValue="<%= dueDate %>" field="dueDate" formName="fm3" model="<%= TicketEntry.class %>" />
		</div>
	</c:if>

	<c:if test="<%= hasUpdateAdmin %>">
		<div class="field-group">
			<label id="<portlet:namespace />ignoreDueDateLabel"><liferay-ui:message key="ignore-due-date" /></label>

			<span class="field-input-align inline">
				<input <%= ticketEntry.getIgnoreDueDate() ? "checked" : "" %> name="<portlet:namespace />ignoreDueDate" type="checkbox" />
			</span>
		</div>
	</c:if>

	<c:if test="<%= hasUpdateAdvanced %>">
		<div class="field-group">
			<label id="<portlet:namespace />reporterLabel"><liferay-ui:message key="reporter" /></label>

			<%
			User reporterUser = UserLocalServiceUtil.fetchUserById(ticketEntry.getUserId());
			%>

			<span class="field-input-align inline" id="<portlet:namespace />reportedByUserName">
				<c:if test="<%= reporterUser != null %>">
					<%= HtmlUtil.escape(reporterUser.getFullName()) %>

					<liferay-util:include page="/support/2/common/user_badge.jsp" servletContext="<%= application %>">
						<portlet:param name="partnerEntryId" value="<%= String.valueOf(accountEntry.getPartnerEntryId()) %>" />
						<portlet:param name="userId" value="<%= String.valueOf(reporterUser.getUserId()) %>" />
					</liferay-util:include>
				</c:if>
			</span>

			<input class="aui-button-input" onClick="var reportedByWindow = window.open('<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/support/2/select_user.jsp" /><portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntry.getTicketEntryId()) %>" /><portlet:param name="callback" value="selectReportedBy" /></portlet:renderURL>', 'reported_by', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); reportedByWindow.focus();" type="button" value="<liferay-ui:message key="choose" />" />
		</div>
	</c:if>
</div>

<aui:script>
	function <portlet:namespace />resetSeverityDetails() {
		document.<portlet:namespace />fm3.<portlet:namespace />severity.value = '<%= severity %>';

		document.getElementById('<portlet:namespace />severityDetails').style.display = 'none';
	}

	function <portlet:namespace />selectOfferingEntry(accountEntryId, offeringEntryId, accountEntryName, supportResponseName, productEntryName) {
		document.<portlet:namespace />fm3.<portlet:namespace />accountEntryId.value = accountEntryId;
		document.<portlet:namespace />fm3.<portlet:namespace />offeringEntryId.value = offeringEntryId;

		var productEntryNameEl = document.getElementById('<portlet:namespace />productEntryName');
		var productLabelEl = document.getElementById('<portlet:namespace />productLabel');

		productEntryNameEl.innerHTML = productEntryName;

		productLabelEl.classList.add('field-modified');

		<portlet:namespace />selectProductComponents(productEntryName);
	}

	function <portlet:namespace />selectReportedBy(userId, userName) {
		document.<portlet:namespace />fm3.<portlet:namespace />reportedByUserId.value = userId;

		var reporterLabelEl = document.getElementById('<portlet:namespace />reporterLabel');
		var userNameEl = document.getElementById('<portlet:namespace />reportedByUserName');

		userNameEl.innerHTML = userName;

		reporterLabelEl.classList.add('field-modified');
	}

	function <portlet:namespace />selectSeverity(severity, newSeverity) {
		var A = AUI();

		var selectElement = A.one('#<portlet:namespace />severityReason');

		var selectOptions = [];

		if (severity == newSeverity) {
			<portlet:namespace />resetSeverityDetails();

			return;
		}
		else if (severity < newSeverity) {
			selectOptions.push('<option value="customer-did-not-understand-the-meaning-of-critical"><liferay-ui:message key="customer-did-not-understand-the-meaning-of-critical" unicode="<%= true %>" /></option>');
			selectOptions.push('<option value="other"><liferay-ui:message key="other" /></option>');
		}
		else {
			selectOptions.push('<option value="customer-did-not-understand-the-meaning-of-critical"><liferay-ui:message key="customer-did-not-understand-the-meaning-of-critical" unicode="<%= true %>" /></option>');
			selectOptions.push('<option value="customer-wanted-more-urgent-action"><liferay-ui:message key="customer-wanted-more-urgent-action" unicode="<%= true %>" /></option>');

			if (newSeverity == <%= SupportResponseConstants.SEVERITY_MAJOR %>) {
				selectOptions.push('<option value="critical-production-down-that-was-stabilized-to-major"><liferay-ui:message key="critical-production-down-that-was-stabilized-to-major" unicode="<%= true %>" /></option>');
			}
			else {
				selectOptions.push('<option value="other"><liferay-ui:message key="other" unicode="<%= true %>" /></option>');
			}
		}

		selectOptions = selectOptions.join('');

		selectElement.empty();

		selectElement.append(selectOptions);

		document.getElementById('<portlet:namespace />severityDetails').style.display = '';
	}

	function <portlet:namespace />updateComponent(selectData) {
		var A = AUI();

		var componentFieldGroup = A.one('#<portlet:namespace />componentFieldGroup');
		var subcomponentFieldGroup = A.one('#<portlet:namespace />subcomponentFieldGroup');

		if (selectData) {
			var selectElement = A.one('#<portlet:namespace />editComponent');

			var selectOptions = [];

			selectOptions.push('<option value="0"></option>');

			if (selectData[0].componentGroup) {
				var componentGroup = selectData[0].componentGroup;

				selectOptions.push('<optgroup label="' + componentGroup + '">');

				for (var i = 0; i < selectData.length; i++) {
					if (componentGroup != selectData[i].componentGroup) {
						selectOptions.push('</optgroup>');
						selectOptions.push('<optgroup label="' + selectData[i].componentGroup + '">');

						componentGroup = selectData[i].componentGroup;
					}

					selectOptions.push('<option value="' + selectData[i].value + '">' + selectData[i].name + '</option>');
				}

				selectOptions.push('</optgroup>');
			}
			else {
				for (var i = 0; i < selectData.length; i++) {
					selectOptions.push('<option value="' + selectData[i].value + '">' + selectData[i].name + '</option>');
				}
			}

			selectOptions = selectOptions.join('');

			selectElement.empty();

			selectElement.append(selectOptions);

			selectElement.val(0);

			componentFieldGroup.show();
			subcomponentFieldGroup.show();
		}
		else {
			componentFieldGroup.hide();
			subcomponentFieldGroup.hide();
		}
	}

	function <portlet:namespace />updateSubcomponent(selectData) {
		var A = AUI();

		var selectElement = A.one('#<portlet:namespace />subcomponent');

		var selectOptions = [];

		selectOptions.push('<option value="0"><liferay-ui:message key="none" /></option>');

		if (selectData) {
			for (var i = 0; i < selectData.length; i++) {
				var value = selectData[i].value;
				var name = selectData[i].name;

				selectOptions.push('<option value="' + value + '">' + name + '</option>');
			}
		}

		selectOptions = selectOptions.join('');

		selectElement.empty();

		selectElement.append(selectOptions);

		selectElement.val(0);
	}

	Liferay.provide(
		window,
		'<portlet:namespace />selectComponent',
		function(component) {
			var A = AUI();

			var serverTypeNode = A.one('#<portlet:namespace />serverCommunicationType');

			if (component == <%= TicketEntryConstants.COMPONENT_CLUSTERING %>) {
				<portlet:namespace />revealComponentTab('clusteringDetails');

				serverTypeNode.setAttribute('data-field-required-status', 'false');
			}
			else if (component == <%= TicketEntryConstants.COMPONENT_LICENSE %>) {
				<portlet:namespace />revealComponentTab('activationKeyDetails');

				serverTypeNode.setAttribute('data-field-required-status', 'true');
			}
			else if (component == <%= TicketEntryConstants.COMPONENT_UPGRADE %>) {
				<portlet:namespace />revealComponentTab('upgradeDetails');

				serverTypeNode.setAttribute('data-field-required-status', 'true');
			}
			else {
				<portlet:namespace />revealComponentTab();

				serverTypeNode.setAttribute('data-field-required-status', 'true');
			}

			<portlet:namespace />loadEnvironmentDetailsTab(component);

			A.io.request(
				'<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="ticketSubcomponents" />',
				{
					data: {
						<portlet:namespace />component: component
					},
					dataType: 'JSON',
					method: 'POST',
					on: {
						success: function(event, id, obj) {
							var response = this.get('responseData');

							<portlet:namespace />updateSubcomponent(response);
						}
					}
				}
			);
		},
		['aui-io']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />selectProductComponents',
		function(productEntryName) {
			var A = AUI();

			A.io.request(
				'<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="ticketComponents" />',
				{
					data: {
						<portlet:namespace />productEntryName: productEntryName
					},
					dataType: 'JSON',
					method: 'POST',
					on: {
						success: function(event, id, obj) {
							var response = this.get('responseData');

							<portlet:namespace />updateComponent(response);

							<portlet:namespace />selectComponent(0);
						}
					}
				}
			);
		},
		['aui-io']
	);
</aui:script>