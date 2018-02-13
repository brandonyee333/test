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

<liferay-ui:error exception="<%= TicketEntryDescriptionException.class %>" message="please-enter-a-description-of-the-issue" />

<div class="tab-content-tab" id="<portlet:namespace />ticketDetails">
	<c:if test="<%= hasUpdateAdvanced %>">
		<aui:field-wrapper>
			<div field-required-message="<%= LanguageUtil.get(request, "please-enter-a-valid-subject") %>">
				<aui:input bean="<%= ticketEntry %>" data-field-required-status="<%= false %>" maxLength='<%= ModelHintsUtil.getMaxLength(TicketEntry.class.getName(), "subject") %>' model="<%= TicketEntry.class %>" name="subject" type="text" />
			</div>
		</aui:field-wrapper>
	</c:if>

	<c:if test="<%= hasUpdateAdvanced %>">
		<aui:field-wrapper>
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
		</aui:field-wrapper>
	</c:if>

	<c:choose>
		<c:when test="<%= hasUpdateAdvanced && liferayIncOrg %>">
			<aui:field-wrapper>
				<aui:input max="<%= ModelHintsConstants.TEXTAREA_MAX_LENGTH %>" name="reproductionSteps" style="height: 150px;" type="textarea" value="<%= reproductionSteps %>" />
			</aui:field-wrapper>
		</c:when>
		<c:otherwise>
			<aui:input name="reproductionSteps" type="hidden" value="<%= HtmlUtil.escapeAttribute(reproductionSteps) %>" />
		</c:otherwise>
	</c:choose>

	<c:if test="<%= hasUpdateAdvanced && liferayIncOrg %>">
		<aui:field-wrapper>
			<label id="<portlet:namespace />productLabel"><liferay-ui:message key="product" /></label>

			<span class="inline" id="<portlet:namespace />productEntryName">
				<%= HtmlUtil.escape(productEntry.getName()) %>
			</span>

			<portlet:renderURL var="chooseProductURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="mvcPath" value="/support/2/select_offering_entry.jsp" />
				<portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntry.getTicketEntryId()) %>" />
			</portlet:renderURL>

			<%
			String taglibOpenOfferingEntryWindow = "var offeringEntryWindow = window.open(\'" + chooseProductURL + "\', \'offering_entry\', \'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=900\'); void(\'\'); offeringEntryWindow.focus();";
			%>

			<aui:button label="" name="product" onClick="<%= taglibOpenOfferingEntryWindow %>" value="choose" />
		</aui:field-wrapper>
	</c:if>

	<c:if test="<%= hasUpdateAdvanced %>">
		<aui:field-wrapper>
			<div class="<%= productEntry.isTicketComponentRequired() ? "" : "hide" %>" id="<portlet:namespace />componentFieldGroup">
				<aui:select id="editComponent" name="component" onChange='<%= renderResponse.getNamespace() + "selectComponent(this.value);" %>'>
					<c:choose>
						<c:when test="<%= productEntry.isDigitalEnterprise() %>">

							<%
							for (String componentGroup : TicketEntryConstants.COMPONENT_GROUPS_DE) {
							%>

								<optgroup label="<%= LanguageUtil.get(request, componentGroup) %>">

								<%
								for (int curComponent : TicketEntryConstants.getGroupComponents(componentGroup)) {
								%>

									<aui:option label="<%= LanguageUtil.get(request, TicketEntryConstants.getComponentLabel(curComponent)) %>" selected="<%= (curComponent == component) %>" value="<%= curComponent %>" />

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

								<aui:option label="<%= LanguageUtil.get(request, TicketEntryConstants.getComponentLabel(curComponent)) %>" selected="<%= (curComponent == component) %>" value="<%= curComponent %>" />

							<%
							}
							%>

						</c:otherwise>
					</c:choose>
				</aui:select>
			</div>
		</aui:field-wrapper>
	</c:if>

	<c:if test="<%= hasUpdateAdvanced && liferayIncOrg %>">
		<div class="<%= productEntry.isTicketComponentRequired() ? "" : "hide" %>" id="<portlet:namespace />subcomponentFieldGroup">
			<aui:field-wrapper>

				<%
				int[] subcomponents = TicketEntryConstants.getSubcomponents(component);
				%>

				<aui:select name="subcomponent">
					<c:if test="<%= ticketEntry.getSubcomponent() <= 0 %>">
						<aui:option label="none" value="0" />
					</c:if>

					<c:if test="<%= !ArrayUtil.contains(subcomponents, TicketEntryConstants.SUBCOMPONENT_OTHER) %>">
						<aui:option label="other" selected="<%= (subcomponent == TicketEntryConstants.SUBCOMPONENT_OTHER) %>" value="<%= TicketEntryConstants.SUBCOMPONENT_OTHER %>" />
					</c:if>

					<%
					for (int curSubcomponent : subcomponents) {
					%>

						<aui:option label="<%= LanguageUtil.get(request, TicketEntryConstants.getSubcomponentLabel(curSubcomponent)) %>" selected="<%= (curSubcomponent == subcomponent) %>" value="<%= curSubcomponent %>" />

					<%
					}
					%>

				</aui:select>

				<aui:input cssClass='<%= (subcomponent == TicketEntryConstants.SUBCOMPONENT_OTHER) ? "" : "hide" %>' label="" name="subcomponentCustom" type="text" value="<%= subcomponentCustom %>" />
			</aui:field-wrapper>
		</div>
	</c:if>

	<c:if test="<%= hasUpdateAdvanced %>">
		<aui:field-wrapper>
			<aui:select name="escalationLevel">

				<%
				for (ListType escalationLevelType : ListTypeServiceUtil.getListTypes(TicketEntryConstants.LIST_TYPE_ESCALATION_LEVEL)) {
				%>

					<aui:option label="<%= LanguageUtil.get(request, escalationLevelType.getName()) %>" selected="<%= (escalationLevelType.getListTypeId() == escalationLevel) %>" value="<%= escalationLevelType.getListTypeId() %>" />

				<%
				}
				%>

			</aui:select>
		</aui:field-wrapper>
	</c:if>

	<c:if test="<%= hasUpdateAdvanced %>">
		<aui:field-wrapper>
			<%
			String severityOnChange = StringPool.BLANK;

			if (liferayIncOrg) {
				severityOnChange = renderResponse.getNamespace() + "selectSeverity(" + severity + ", this.value);";
			}
			%>

			<aui:select name="severity" onChange="<%= severityOnChange %>">

				<%
				for (int i = 1; i <= 3; i++) {
				%>

					<aui:option label="<%= LanguageUtil.get(request, TicketEntryConstants.getSeverityLabel(i)) %>" selected="<%= (i == severity) %>" value="<%= i %>" />

				<%
				}
				%>

			</aui:select>
		</aui:field-wrapper>
	</c:if>

	<c:if test="<%= hasUpdateAdvanced && liferayIncOrg %>">
		<div class="hide" id="<portlet:namespace />severityDetails">
			<aui:field-wrapper>
				<aui:select label="what-is-the-reason-for-changing-the-ticket-severity" name="severityReason" />

				<aui:input label="please-explain-optional" name="severityReasonComments" onKeyDown="Liferay.Util.checkTab(this); Liferay.Util.disableEsc();" style="height: 150px; max-width: 505px;" type="textarea"  wrap="soft" />
			</aui:field-wrapper>
		</div>
	</c:if>

	<c:if test="<%= hasUpdateAdvanced %>">
		<div id="<portlet:namespace/>statusDropDown">
			<aui:field-wrapper>
				<aui:select data-field-required-status="<%= true %>" label="status" name="status">

					<%
					for (int statusId : TicketEntryConstants.STATUSES_WORKFLOW_ORDER) {
						if ((statusId == TicketEntryConstants.STATUS_INACTIVE) || (statusId == TicketEntryConstants.STATUS_PENDING_WORKER) || ((statusId == TicketEntryConstants.STATUS_RESOLVED_IN_PRODUCTION) && !OSBTicketEntryPermission.contains(permissionChecker, ticketEntry, OSBActionKeys.UPDATE_SOLUTION)) || ((statusId == TicketEntryConstants.STATUS_SOLUTION_DELIVERED) && (status != TicketEntryConstants.STATUS_SOLUTION_PROPOSED)) || (statusId == TicketEntryConstants.STATUS_SOLUTION_PROPOSED)) {
							if (statusId != status) {
								continue;
							}
						}
					%>

						<aui:option label="<%= LanguageUtil.get(request, TicketEntryConstants.getStatusLabel(statusId)) %>" selected="<%= (statusId == status) %>" value="<%= statusId %>" />

					<%
					}
					%>

				</aui:select>
			</aui:field-wrapper>
		</div>
	</c:if>

	<c:if test="<%= hasUpdateAdvanced %>">
		<div id="<portlet:namespace/>resolutionDropDown">
			<aui:field-wrapper>
				<aui:select data-field-required-status="<%= false %>" field-required-message="<%= LanguageUtil.get(request, "please-select-a-valid-resolution") %>" name="resolution">
					<aui:option label="" value="0" />

					<%
					for (ListType resolutionType : ListTypeServiceUtil.getListTypes(TicketEntryConstants.LIST_TYPE_RESOLUTION)) {
					%>

						<aui:option label="<%= LanguageUtil.get(request, resolutionType.getName()) %>" selected="<%= (resolutionType.getListTypeId() == resolution) %>" value="<%= resolutionType.getListTypeId() %>" />

					<%
					}
					%>

				</aui:select>

				<aui:button cssClass="hide" name="resolutionCancel" onClick='<%= renderResponse.getNamespace() + "resetStatus()" %>' value="cancel" />
			</aui:field-wrapper>
		</div>
	</c:if>

	<c:if test="<%= !screenShareMode && (ticketEntry.getStatus() != TicketEntryConstants.STATUS_CLOSED) && ticketWorker %>">
		<aui:field-wrapper>
			<label id="<portlet:namespace />pendingTypesLabel"><liferay-ui:message key="need-response-from" /></label>

			<div class="pending-types">

				<%
				int[] pendingTypes = TicketFlagLocalServiceUtil.getTicketFlagTypes(ticketEntry.getTicketEntryId(), TicketFlagConstants.TYPES_PENDING, TicketFlagConstants.FLAG_TRUE);

				for (int pendingType : TicketFlagConstants.TYPES_PENDING) {
				%>

					<aui:input checked="<%= ArrayUtil.contains(pendingTypes, pendingType) %>" inlineField="true" label="<%= TicketFlagConstants.getTypeLabel(pendingType) %>" name="pendingTypes" type="checkbox" value="<%= pendingType %>" />

				<%
				}
				%>

			</div>
		</aui:field-wrapper>
	</c:if>

	<c:choose>
		<c:when test="<%= hasUpdateAdvanced && liferayIncOrg %>">
			<aui:field-wrapper>
				<aui:select label="support-region" name="supportRegionId">

					<%
					List<SupportRegion> supportRegions = SupportRegionLocalServiceUtil.getSupportRegions(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

					for (SupportRegion supportRegion : supportRegions) {
					%>

						<aui:option label="<%= HtmlUtil.escape(supportRegion.getName()) %>" selected="<%= (supportRegion.getSupportRegionId() == supportRegionId) %>" value="<%= supportRegion.getSupportRegionId() %>" />

					<%
					}
					%>

				</aui:select>
			</aui:field-wrapper>
		</c:when>
		<c:otherwise>
			<aui:input name="supportRegionId" type="hidden" value="<%= supportRegionId %>" />
		</c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="<%= hasUpdateAdvanced && liferayIncOrg %>">
			<aui:field-wrapper>
				<aui:select label="support-language" name="languageId">

					<%
					for (String curLanguageId : AccountEntryConstants.LANGUAGES) {
					%>

						<aui:option label="<%= LanguageUtil.get(request, AccountEntryConstants.getLanguageLabel(curLanguageId)) %>" selected="<%= languageId.equals(curLanguageId) %>" value="<%= curLanguageId %>" />

					<%
					}
					%>

				</aui:select>
			</aui:field-wrapper>
		</c:when>
		<c:otherwise>
			<aui:input label="" name="languageId" type="hidden" value="<%= HtmlUtil.escapeAttribute(languageId) %>" />
		</c:otherwise>
	</c:choose>

	<c:if test="<%= hasUpdateAdmin %>">
		<aui:field-wrapper>
			<label id="<portlet:namespace />dueDateLabel"><liferay-ui:message key="due" /></label>

			<%
			Calendar dueDate = CalendarFactoryUtil.getCalendar(timeZone, locale);

			if (ticketEntry.getDueDate() != null) {
				dueDate.setTime(ticketEntry.getDueDate());
			}
			%>

			<liferay-ui:input-field bean="<%= ticketEntry %>" defaultValue="<%= dueDate %>" field="dueDate" formName="fm3" model="<%= TicketEntry.class %>" />
		</aui:field-wrapper>
	</c:if>

	<c:if test="<%= hasUpdateAdmin %>">
		<aui:field-wrapper cssClass="inline">
			<aui:input cssClass='<%= ticketEntry.getIgnoreDueDate() ? "checked" : "" %>' name="ignoreDueDate" type="checkbox" />
		</aui:field-wrapper>
	</c:if>

	<c:if test="<%= hasUpdateAdvanced %>">
		<aui:field-wrapper>
			<label id="<portlet:namespace />reporterLabel"><liferay-ui:message key="reporter" /></label>

			<%
			User reporterUser = UserLocalServiceUtil.fetchUserById(ticketEntry.getUserId());
			%>

			<span class="inline" id="<portlet:namespace />reportedByUserName">
				<c:if test="<%= reporterUser != null %>">
					<%= HtmlUtil.escape(reporterUser.getFullName()) %>

					<liferay-util:include page="/support/2/common/user_badge.jsp" servletContext="<%= application %>">
						<portlet:param name="partnerEntryId" value="<%= String.valueOf(accountEntry.getPartnerEntryId()) %>" />
						<portlet:param name="userId" value="<%= String.valueOf(reporterUser.getUserId()) %>" />
					</liferay-util:include>
				</c:if>
			</span>

			<portlet:renderURL var="selectReportedByRenderURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="mvcPath" value="/support/2/select_user.jsp" />
				<portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntry.getTicketEntryId()) %>" />
				<portlet:param name="callback" value="selectReportedBy" />
			</portlet:renderURL>

			<%
			String taglibOpenReportedByWindow = "var reportedByWindow = window.open('" + selectReportedByRenderURL.toString() + "', 'reported_by', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); reportedByWindow.focus();";
			%>

			<aui:button onClick="<%= taglibOpenReportedByWindow %>" value="choose" />
		</aui:field-wrapper>
	</c:if>
</div>

<aui:script>
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

	Liferay.provide(
		window,
		'<portlet:namespace />selectComponent',
		function(component) {
			var A = AUI();

			var requiredStatus = 'true';

			if (component == <%= TicketEntryConstants.COMPONENT_CLUSTERING %>) {
				<portlet:namespace />revealComponentTab('clusteringDetailsHeader');

				requiredStatus = 'false';
			}
			else if (component == <%= TicketEntryConstants.COMPONENT_LICENSE %>) {
				<portlet:namespace />revealComponentTab('activationKeyDetailsHeader');
			}
			else if (component == <%= TicketEntryConstants.COMPONENT_UPGRADE %>) {
				<portlet:namespace />revealComponentTab('upgradeDetailsHeader');
			}
			else {
				<portlet:namespace />revealComponentTab();
			}

			var serverTypeNode = A.one('#<portlet:namespace />serverCommunicationType');

			if (serverTypeNode) {
				serverTypeNode.attr('data-field-required-status', requiredStatus);
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

	Liferay.provide(
		window,
		'<portlet:namespace />selectSeverity',
		function(severity, newSeverity) {
			var A = AUI();

			var selectOptions = [];

			var severityDetails = A.one('#<portlet:namespace />severityDetails');

			if (severity == newSeverity) {
				var severity = A.one('#<portlet:namespace />severity');

				if (severity) {
					severity.val('<%= severity %>');
				}

				if (severityDetails) {
					severityDetails.hide();
				}

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

			var selectElement = A.one('#<portlet:namespace />severityReason');

			if (selectElement) {
				selectElement.empty();

				selectElement.append(selectOptions);
			}

			if (severityDetails) {
				severityDetails.show();
			}
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />updateComponent',
		function(selectData) {
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
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />updateSubcomponent',
		function(selectData) {
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
		},
		['aui-base']
	);
</aui:script>