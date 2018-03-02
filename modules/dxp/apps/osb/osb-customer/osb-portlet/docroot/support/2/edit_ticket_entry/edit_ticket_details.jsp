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
		<aui:input bean="<%= ticketEntry %>" inlineField="<%= true %>" maxLength='<%= ModelHintsUtil.getMaxLength(TicketEntry.class.getName(), "subject") %>' model="<%= TicketEntry.class %>" name="subject" type="text" wrapperCssClass="form-field-wrapper">
			<aui:validator errorMessage="please-enter-a-valid-subject" name="required" />
		</aui:input>

		<aui:field-wrapper cssClass="form-field-wrapper">
			<label class="control-label" id="<portlet:namespace />descriptionLabel"><liferay-ui:message key="description" /></label>

			<liferay-util:include page="/common/textarea.jsp" servletContext="<%= application %>">
				<liferay-util:param name="content" value="<%= description %>" />
				<liferay-util:param name="editorId" value="description" />
				<liferay-util:param name="fieldRequired" value="<%= Boolean.TRUE.toString() %>" />
				<liferay-util:param name="fieldRequiredMessage" value='<%= LanguageUtil.get(request, "please-enter-a-description-of-the-issue") %>' />
				<liferay-util:param name="height" value="150" />
				<liferay-util:param name="name" value="description" />
				<liferay-util:param name="showCounter" value="<%= Boolean.TRUE.toString() %>" />
			</liferay-util:include>
		</aui:field-wrapper>
	</c:if>

	<c:choose>
		<c:when test="<%= hasUpdateAdvanced && liferayIncOrg %>">
			<aui:input inlineField="<%= true %>" max="<%= ModelHintsConstants.TEXTAREA_MAX_LENGTH %>" name="reproductionSteps" style="height: 150px;" type="textarea" value="<%= reproductionSteps %>" wrapperCssClass="form-field-wrapper" />
		</c:when>
		<c:otherwise>
			<aui:input name="reproductionSteps" type="hidden" value="<%= HtmlUtil.escapeAttribute(reproductionSteps) %>" />
		</c:otherwise>
	</c:choose>

	<c:if test="<%= hasUpdateAdvanced && liferayIncOrg %>">
		<aui:field-wrapper cssClass="form-field-wrapper">
			<label class="control-label" id="<portlet:namespace />productLabel"><liferay-ui:message key="product" /></label>

			<div class="inline">
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
			</div>
		</aui:field-wrapper>
	</c:if>

	<c:if test="<%= hasUpdateAdvanced %>">
		<div class="<%= productEntry.isTicketComponentRequired() ? "" : "hide" %>" id="<portlet:namespace />componentFieldGroup">
			<aui:select id="editComponent" inlineField="<%= true %>" label="component" name="component" onChange='<%= renderResponse.getNamespace() + "selectComponent(this.value);" %>' wrapperCssClass="form-field-wrapper">
				<c:choose>
					<c:when test="<%= productEntry.isDigitalEnterprise() %>">

						<%
						for (String componentGroup : TicketEntryConstants.COMPONENT_GROUPS_DE) {
						%>

							<optgroup label="<%= LanguageUtil.get(request, componentGroup) %>">

								<%
								for (int curComponent : TicketEntryConstants.getGroupComponents(componentGroup)) {
								%>

									<aui:option label="<%= TicketEntryConstants.getComponentLabel(curComponent) %>" selected="<%= curComponent == component %>" value="<%= curComponent %>" />

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

							<aui:option label="<%= TicketEntryConstants.getComponentLabel(curComponent) %>" selected="<%= curComponent == component %>" value="<%= curComponent %>" />

						<%
						}
						%>

					</c:otherwise>
				</c:choose>
			</aui:select>
		</div>
	</c:if>

	<c:if test="<%= hasUpdateAdvanced && liferayIncOrg %>">
		<div class="<%= productEntry.isTicketComponentRequired() ? "" : "hide" %>" id="<portlet:namespace />subcomponentFieldGroup">

			<%
			int[] subcomponents = TicketEntryConstants.getSubcomponents(component);
			%>

			<aui:select inlineField="<%= true %>" name="subcomponent" wrapperCssClass="form-field-wrapper">
				<c:if test="<%= ticketEntry.getSubcomponent() <= 0 %>">
					<aui:option label="none" value="0" />
				</c:if>

				<c:if test="<%= !ArrayUtil.contains(subcomponents, TicketEntryConstants.SUBCOMPONENT_OTHER) %>">
					<aui:option label="other" selected="<%= subcomponent == TicketEntryConstants.SUBCOMPONENT_OTHER %>" value="<%= TicketEntryConstants.SUBCOMPONENT_OTHER %>" />
				</c:if>

				<%
				for (int curSubcomponent : subcomponents) {
				%>

					<aui:option label="<%= TicketEntryConstants.getSubcomponentLabel(curSubcomponent) %>" selected="<%= curSubcomponent == subcomponent %>" value="<%= curSubcomponent %>" />

				<%
				}
				%>

			</aui:select>

			<aui:input cssClass="no-label" inlineField="<%= true %>" label="" name="subcomponentCustom" type="text" value="<%= subcomponentCustom %>" wrapperCssClass='<%= (subcomponent == TicketEntryConstants.SUBCOMPONENT_OTHER) ? "form-field-wrapper" : "hide" %>' />
		</div>
	</c:if>

	<c:if test="<%= hasUpdateAdvanced %>">
		<aui:select inlineField="<%= true %>" name="escalationLevel" wrapperCssClass="form-field-wrapper">

			<%
			for (ListType escalationLevelType : ListTypeServiceUtil.getListTypes(TicketEntryConstants.LIST_TYPE_ESCALATION_LEVEL)) {
			%>

				<aui:option label="<%= escalationLevelType.getName() %>" selected="<%= escalationLevelType.getListTypeId() == escalationLevel %>" value="<%= escalationLevelType.getListTypeId() %>" />

			<%
			}
			%>

		</aui:select>
	</c:if>

	<c:if test="<%= hasUpdateAdvanced %>">

		<%
		String severityOnChange = StringPool.BLANK;

		if (liferayIncOrg) {
			severityOnChange = renderResponse.getNamespace() + "selectSeverity(" + severity + ", this.value);";
		}
		%>

		<aui:select inlineField="<%= true %>" name="severity" onChange="<%= severityOnChange %>" wrapperCssClass="form-field-wrapper">

			<%
			for (int i = 1; i <= 3; i++) {
			%>

				<aui:option label="<%= TicketEntryConstants.getSeverityLabel(i) %>" selected="<%= i == severity %>" value="<%= i %>" />

			<%
			}
			%>

		</aui:select>
	</c:if>

	<c:if test="<%= hasUpdateAdvanced && liferayIncOrg %>">
		<div class="hide" id="<portlet:namespace />severityDetails">
			<aui:select inlineField="<%= true %>" label="what-is-the-reason-for-changing-the-ticket-severity" name="severityReason" wrapperCssClass="clearfix" />

			<aui:input inlineField="<%= true %>" label="please-explain-optional" name="severityReasonComments" onKeyDown="Liferay.Util.checkTab(this); Liferay.Util.disableEsc();" style="height: 150px; max-width: 505px;" type="textarea" wrap="soft" wrapperCssClass="form-field-wrapper" />
		</div>
	</c:if>

	<c:if test="<%= hasUpdateAdvanced %>">
		<aui:select id="statusDropDown" inlineField="<%= true %>" name="status" wrapperCssClass="form-field-wrapper">

			<%
			for (int statusId : TicketEntryConstants.STATUSES_WORKFLOW_ORDER) {
				if ((statusId == TicketEntryConstants.STATUS_INACTIVE) || (statusId == TicketEntryConstants.STATUS_PENDING_WORKER) || ((statusId == TicketEntryConstants.STATUS_RESOLVED_IN_PRODUCTION) && !OSBTicketEntryPermission.contains(permissionChecker, ticketEntry, OSBActionKeys.UPDATE_SOLUTION)) || ((statusId == TicketEntryConstants.STATUS_SOLUTION_DELIVERED) && (status != TicketEntryConstants.STATUS_SOLUTION_PROPOSED)) || (statusId == TicketEntryConstants.STATUS_SOLUTION_PROPOSED)) {
					if (statusId != status) {
						continue;
					}
				}
			%>

				<aui:option label="<%= TicketEntryConstants.getStatusLabel(statusId) %>" selected="<%= statusId == status %>" value="<%= statusId %>" />

			<%
			}
			%>

		</aui:select>
	</c:if>

	<c:if test="<%= hasUpdateAdvanced %>">
		<aui:select id="resolutionDropDown" inlineField="<%= true %>" name="resolution" wrapperCssClass="form-field-wrapper">
			<aui:option label="" value="0" />

			<%
			for (ListType resolutionType : ListTypeServiceUtil.getListTypes(TicketEntryConstants.LIST_TYPE_RESOLUTION)) {
			%>

				<aui:option label="<%= resolutionType.getName() %>" selected="<%= resolutionType.getListTypeId() == resolution %>" value="<%= resolutionType.getListTypeId() %>" />

			<%
			}
			%>

			<aui:validator errorMessage="please-select-a-valid-resolution" name="required" />
		</aui:select>
	</c:if>

	<c:if test="<%= !screenShareMode && (ticketEntry.getStatus() != TicketEntryConstants.STATUS_CLOSED) && ticketWorker %>">
		<aui:field-wrapper cssClass="form-field-wrapper" label="need-response-from">

			<%
			int[] pendingTypes = TicketFlagLocalServiceUtil.getTicketFlagTypes(ticketEntry.getTicketEntryId(), TicketFlagConstants.TYPES_PENDING, TicketFlagConstants.FLAG_TRUE);

			for (int pendingType : TicketFlagConstants.TYPES_PENDING) {
			%>

				<aui:input checked="<%= ArrayUtil.contains(pendingTypes, pendingType) %>" inlineField="<%= true %>" label="<%= TicketFlagConstants.getTypeLabel(pendingType) %>" name="pendingTypes" type="checkbox" value="<%= pendingType %>" />

			<%
			}
			%>

		</aui:field-wrapper>
	</c:if>

	<c:choose>
		<c:when test="<%= hasUpdateAdvanced && liferayIncOrg %>">
			<aui:select inlineField="<%= true %>" label="support-region" name="supportRegionId" wrapperCssClass="form-field-wrapper">

				<%
				List<SupportRegion> supportRegions = SupportRegionLocalServiceUtil.getSupportRegions(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

				for (SupportRegion supportRegion : supportRegions) {
				%>

					<aui:option label="<%= HtmlUtil.escape(supportRegion.getName()) %>" selected="<%= supportRegion.getSupportRegionId() == supportRegionId %>" value="<%= supportRegion.getSupportRegionId() %>" />

				<%
				}
				%>

			</aui:select>
		</c:when>
		<c:otherwise>
			<aui:input name="supportRegionId" type="hidden" value="<%= supportRegionId %>" />
		</c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="<%= hasUpdateAdvanced && liferayIncOrg %>">
			<aui:select inlineField="<%= true %>" label="support-language" name="languageId" wrapperCssClass="form-field-wrapper">

				<%
				for (String curLanguageId : AccountEntryConstants.LANGUAGES) {
				%>

					<aui:option label="<%= AccountEntryConstants.getLanguageLabel(curLanguageId) %>" selected="<%= languageId.equals(curLanguageId) %>" value="<%= curLanguageId %>" />

				<%
				}
				%>

			</aui:select>
		</c:when>
		<c:otherwise>
			<aui:input label="" name="languageId" type="hidden" value="<%= HtmlUtil.escapeAttribute(languageId) %>" />
		</c:otherwise>
	</c:choose>

	<c:if test="<%= hasUpdateAdmin %>">
		<aui:field-wrapper cssClass="form-field-wrapper">
			<label class="control-label" id="<portlet:namespace />dueDateLabel"><liferay-ui:message key="due" /></label>

			<%
			Calendar dueDate = CalendarFactoryUtil.getCalendar(timeZone, locale);

			if (ticketEntry.getDueDate() != null) {
				dueDate.setTime(ticketEntry.getDueDate());
			}
			%>

			<div class="inline-block">
				<liferay-ui:input-field bean="<%= ticketEntry %>" defaultValue="<%= dueDate %>" field="dueDate" formName="updateTicketfm" model="<%= TicketEntry.class %>" />
			</div>
		</aui:field-wrapper>

		<aui:field-wrapper cssClass="form-field-wrapper" label="ignore-due-date">
			<aui:input cssClass='<%= ticketEntry.getIgnoreDueDate() ? "checked" : "" %>' label="" name="ignoreDueDate" type="checkbox" />
		</aui:field-wrapper>
	</c:if>

	<c:if test="<%= hasUpdateAdvanced %>">
		<aui:field-wrapper cssClass="form-field-wrapper" label="reporter">

			<%
			User reporterUser = UserLocalServiceUtil.fetchUserById(ticketEntry.getUserId());
			%>

			<span id="<portlet:namespace />reportedByUserName">
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
		document.<portlet:namespace />updateTicketfm.<portlet:namespace />accountEntryId.value = accountEntryId;
		document.<portlet:namespace />updateTicketfm.<portlet:namespace />offeringEntryId.value = offeringEntryId;

		var productEntryNameEl = document.getElementById('<portlet:namespace />productEntryName');
		var productLabelEl = document.getElementById('<portlet:namespace />productLabel');

		productEntryNameEl.innerHTML = productEntryName;

		productLabelEl.classList.add('field-modified');

		<portlet:namespace />selectProductComponents(productEntryName);
	}

	function <portlet:namespace />selectReportedBy(userId, userName) {
		document.<portlet:namespace />updateTicketfm.<portlet:namespace />reportedByUserId.value = userId;

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

			var COMPONENTS = {
				[<%= TicketEntryConstants.COMPONENT_CLUSTERING %>]: 'clusteringDetailsHeader',
				[<%= TicketEntryConstants.COMPONENT_LICENSE %>]: 'activationKeyDetailsHeader',
				[<%= TicketEntryConstants.COMPONENT_UPGRADE %>]: 'upgradeDetailsHeader'
			};

			A.all('.component-tab').hide();

			var activeComponent = COMPONENTS[component];

			if (activeComponent) {
				var componentTab = A.one('#<portlet:namespace />' + activeComponent);

				if (componentTab) {
					componentTab.show();
				}
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
						success: function() {
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

							<portlet:namespace />selectComponent();
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

			var selectOptions = ['<option value="customer-did-not-understand-the-meaning-of-critical"><liferay-ui:message key="customer-did-not-understand-the-meaning-of-critical" unicode="<%= true %>" /></option>'];

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
				selectOptions.push('<option value="other"><liferay-ui:message key="other" /></option>');
			}
			else {
				selectOptions.push('<option value="customer-wanted-more-urgent-action"><liferay-ui:message key="customer-wanted-more-urgent-action" unicode="<%= true %>" /></option>');

				if (newSeverity == <%= SupportResponseConstants.SEVERITY_MAJOR %>) {
					selectOptions.push('<option value="critical-production-down-that-was-stabilized-to-major"><liferay-ui:message key="critical-production-down-that-was-stabilized-to-major" unicode="<%= true %>" /></option>');
				}
				else {
					selectOptions.push('<option value="other"><liferay-ui:message key="other" unicode="<%= true %>" /></option>');
				}
			}

			var selectElement = A.one('#<portlet:namespace />severityReason');

			if (selectElement) {
				selectElement.setHTML(selectOptions.join(''));
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

				var selectOptions = ['<option value="0"></option>'];

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

				selectElement.setHTML(selectOptions.join(''));

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
		function(data) {
			var A = AUI();

			var subcomponentNode = A.one('#<portlet:namespace />subcomponent');

			var subcomponentOptions = ['<option value="0"><liferay-ui:message key="none" /></option>'];

			for (var i = 0; i < data.length; i++) {
				var value = data[i].value;
				var name = data[i].name;

				subcomponentOptions.push('<option value="' + value + '">' + name + '</option>');
			}

			subcomponentNode.setHTML(subcomponentOptions.join(''));
		},
		['aui-base']
	);
</aui:script>