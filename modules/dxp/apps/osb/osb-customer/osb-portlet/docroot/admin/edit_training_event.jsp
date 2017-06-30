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
themeDisplay.setIncludeServiceJs(true);

String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL", redirect);

long trainingEventId = ParamUtil.getLong(request, "trainingEventId");

TrainingEvent trainingEvent = TrainingEventLocalServiceUtil.fetchTrainingEvent(trainingEventId);

List<TrainingCustomer> trainingCustomers = Collections.emptyList();

if (trainingEvent != null) {
	trainingCustomers = trainingEvent.getTrainingCustomers();
}

long ddlRecordSetId = BeanParamUtil.getLong(trainingEvent, request, "DDLRecordSetId");
long partnerEntryId = BeanParamUtil.getLong(trainingEvent, request, "partnerEntryId");
long trainingCertificateTemplateId = BeanParamUtil.getLong(trainingEvent, request, "trainingCertificateTemplateId");
long trainingCourseId = BeanParamUtil.getLong(trainingEvent, request, "trainingCourseId");
int portalMinorVersion = BeanParamUtil.getInteger(trainingEvent, request, "portalMinorVersion");
int type = BeanParamUtil.getInteger(trainingEvent, request, "type");
String languageId = BeanParamUtil.getString(trainingEvent, request, "languageId", LocaleUtil.toLanguageId(Locale.getDefault()));

boolean localizedSlides = BeanParamUtil.getBoolean(trainingEvent, request, "localizedSlides");

boolean displayLocalizedSlides = true;

List<String> languageIds = ListUtil.toList(PortletPropsValues.TRAINING_EVENT_LOCALIZED_SLIDES);

if (languageIds.contains(languageId)) {
	localizedSlides = true;

	displayLocalizedSlides = false;
}

String timeZoneId = BeanParamUtil.getString(trainingEvent, request, "timeZoneId", StringPool.UTC);

if (Validator.isNull(timeZoneId)) {
	timeZoneId = StringPool.UTC;
}

TimeZone selTimeZone = TimeZoneUtil.getTimeZone(timeZoneId);

Calendar startDate = CalendarFactoryUtil.getCalendar(selTimeZone, locale);

startDate.add(Calendar.MONTH, 2);
startDate.set(Calendar.HOUR_OF_DAY, 9);
startDate.set(Calendar.MINUTE, 0);

if ((trainingEvent != null) && (trainingEvent.getStartDate() != null)) {
	startDate.setTime(trainingEvent.getStartDate());
}

Calendar endDate = CalendarFactoryUtil.getCalendar(selTimeZone, locale);

endDate.add(Calendar.MONTH, 2);
endDate.add(Calendar.DATE, 3);
endDate.set(Calendar.HOUR_OF_DAY, 17);
endDate.set(Calendar.MINUTE, 0);

if ((trainingEvent != null) && (trainingEvent.getEndDate() != null)) {
	endDate.setTime(trainingEvent.getEndDate());
}

Address address = null;

long addressRegionId = 0;
long addressCountryId = 0;

if ((trainingEvent != null) && (trainingEvent.getAddressId() > 0)) {
	address = trainingEvent.getAddress();

	addressRegionId = ParamUtil.getLong(request, "addressRegionId", address.getRegionId());
	addressCountryId = ParamUtil.getLong(request, "addressCountryId", address.getCountryId());
}
else {
	addressRegionId = ParamUtil.getLong(request, "addressRegionId");
	addressCountryId = ParamUtil.getLong(request, "addressCountryId");
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/edit_training_event.jsp");
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("trainingEventId", String.valueOf(trainingEventId));

request.setAttribute("edit.jsp-portletURL", portletURL);
%>

<div class="admin edit-training-event">
	<portlet:actionURL name="updateTrainingEvent" var="updateTrainingEventURL">
		<portlet:param name="mvcPath" value="/admin/edit_training_event.jsp" />
	</portlet:actionURL>

	<aui:form action="<%= updateTrainingEventURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveTrainingEvent();" %>'>
		<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
		<aui:input name="ddlRecordSetId" type="hidden" value="<%= ddlRecordSetId %>" />
		<aui:input name="partnerEntryId" type="hidden" value="<%= partnerEntryId %>" />
		<aui:input name="trainingEventId" type="hidden" value="<%= trainingEventId %>" />
		<aui:input name="addTrainingCustomerIds" type="hidden" value="" />

		<liferay-ui:tabs
			backURL="<%= backURL %>"
			names="class"
		/>

		<liferay-ui:error exception="<%= AddressCityException.class %>" message="please-enter-a-valid-city" />
		<liferay-ui:error exception="<%= AddressStreetException.class %>" message="please-enter-a-valid-street" />
		<liferay-ui:error exception="<%= AddressZipException.class %>" message="please-enter-a-valid-zip" />
		<liferay-ui:error exception="<%= NoSuchCountryException.class %>" message="please-select-a-country" />
		<liferay-ui:error exception="<%= NoSuchListTypeException.class %>" message="please-select-a-version" />
		<liferay-ui:error exception="<%= NoSuchRegionException.class %>" message="please-select-a-region" />
		<liferay-ui:error exception="<%= NoSuchTrainingCourseException.class %>" message="please-select-a-course" />
		<liferay-ui:error exception="<%= NoSuchTrainingLocationException.class %>" message="please-select-a-location" />
		<liferay-ui:error exception="<%= RecordSetDDMStructureIdException.class %>" message="please-enter-a-valid-survey-template" />

		<aui:model-context bean="<%= trainingEvent %>" model="<%= TrainingEvent.class %>" />

		<table class="lfr-table">
		<tr>
			<td>
				<liferay-ui:message key="client-name" />
			</td>
			<td>
				<aui:input label="" name="name" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="type" />
			</td>
			<td>
				<aui:select label="" name="type">

					<%
					for (int curType : TrainingEventConstants.TYPES) {
					%>

						<aui:option label="<%= TrainingEventConstants.getTypeLabel(curType) %>" selected="<%= type == curType %>" value="<%= curType %>" />

					<%
					}
					%>

				</aui:select>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="course" />
			</td>
			<td>
				<aui:select label="" name="trainingCourseId">
					<aui:option value="" />

					<%
					List<TrainingCourse> trainingCourses = TrainingCourseLocalServiceUtil.getTrainingCourses(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

					for (TrainingCourse trainingCourse : trainingCourses) {
						if ((trainingEventId > 0) || !trainingCourse.isArchived()) {
					%>

							<aui:option label="<%= trainingCourse.getName() %>" selected="<%= trainingCourseId == trainingCourse.getTrainingCourseId() %>" value="<%= trainingCourse.getTrainingCourseId() %>" />

					<%
						}
					}
					%>

				</aui:select>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="version" />
			</td>
			<td>
				<aui:select label="" name="portalMinorVersion">
					<aui:option value="" />

					<%
					List<ListType> portalMinorVersionTypes = ListTypeServiceUtil.getListTypes(TrainingEventConstants.LIST_TYPE_PORTAL_MINOR_VERSIONS);

					for (ListType portalMinorVersionType : portalMinorVersionTypes) {
					%>

						<aui:option label="<%= portalMinorVersionType.getName() %>" selected="<%= portalMinorVersionType.getListTypeId() == portalMinorVersion %>" value="<%= portalMinorVersionType.getListTypeId() %>" />

					<%
					}
					%>

				</aui:select>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<br />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="location" />
			</td>
			<td>
				<aui:select label="" name="eventTrainingLocationId" onChange='<%= renderResponse.getNamespace() + "selectTrainingLocation(this.value);" %>'>
					<aui:option value="" />

					<%
					List<TrainingLocation> trainingLocations = TrainingLocationLocalServiceUtil.getTrainingLocations(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

					for (TrainingLocation trainingLocation : trainingLocations) {
					%>

						<aui:option label="<%= trainingLocation.getName() %>" value="<%= trainingLocation.getTrainingLocationId() %>" />

					<%
					}
					%>

				</aui:select>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="street1" />
			</td>
			<td>
				<aui:input cssClass="address" label="" name="addressStreet1" type="text" value="<%= (address == null ? StringPool.BLANK : address.getStreet1()) %>" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="street2" />
			</td>
			<td>
				<aui:input cssClass="address" label="" name="addressStreet2" type="text" value="<%= (address == null ? StringPool.BLANK : address.getStreet2()) %>" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="street3" />
			</td>
			<td>
				<aui:input cssClass="address" label="" name="addressStreet3" type="text" value="<%= (address == null ? StringPool.BLANK : address.getStreet3()) %>" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="city" />
			</td>
			<td>
				<aui:input cssClass="address" label="" name="addressCity" type="text" value="<%= (address == null ? StringPool.BLANK : address.getCity()) %>" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="zip" />
			</td>
			<td>
				<aui:input cssClass="address" label="" name="addressZip" type="text" value="<%= (address == null ? StringPool.BLANK : address.getZip()) %>" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="country" />
			</td>
			<td>
				<select id="<portlet:namespace />addressCountryId" name="<portlet:namespace />addressCountryId"></select>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="region" />
			</td>
			<td>
				<select id="<portlet:namespace />addressRegionId" name="<portlet:namespace />addressRegionId"></select>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<br />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="language" />
			</td>
			<td>
				<aui:select label="" name="languageId" onChange='<%= renderResponse.getNamespace() + "displayLocalizedSlides(this.value);" %>'>

					<%
					Locale selLocale = LocaleUtil.fromLanguageId(languageId, false);
					%>

					<aui:option label="<%= locale.getDisplayName(locale) %>" selected="<%= locale.equals(selLocale) %>" value="<%= LocaleUtil.toLanguageId(locale) %>" />

					<%
					for (String curLanguageId : PortletPropsValues.TRAINING_EVENT_LANGUAGE_LOCALES) {
						Locale curLocale = LocaleUtil.fromLanguageId(curLanguageId, false);

						if (curLocale.equals(locale)) {
							continue;
						}
					%>

						<aui:option label="<%= curLocale.getDisplayName(locale) %>" selected="<%= curLocale.equals(selLocale) %>" value="<%= LocaleUtil.toLanguageId(curLocale) %>" />

					<%
					}
					%>

				</aui:select>
			</td>
		</tr>
		<tr class='<%= !displayLocalizedSlides ? "aui-helper-hidden" : "" %>' id="<portlet:namespace />displayLocalizedSlides">
			<td>
				<liferay-ui:message key="localized-slides" />
			</td>
			<td>
				<aui:input label="" name="localizedSlides" value="<%= localizedSlides %>" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<br />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="partner" />
			</td>
			<td>

				<%
				String partnerEntryCode = StringPool.BLANK;

				try {
					PartnerEntry partnerEntry = PartnerEntryLocalServiceUtil.getPartnerEntry(partnerEntryId);

					partnerEntryCode = partnerEntry.getCode();
				}
				catch (NoSuchPartnerEntryException nspe) {
				}
				%>

				<span id="<portlet:namespace />partnerEntryCode">
					<c:choose>
						<c:when test="<%= RoleLocalServiceUtil.hasUserRole(permissionChecker.getUserId(), OSBConstants.ROLE_OSB_ADMINISTRATOR_ID) && (partnerEntryId > 0) %>">
							<a href="<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="mvcPath" value="/admin/edit_partner_entry.jsp" /><portlet:param name="redirect" value="<%= portletURL.toString() %>" /><portlet:param name="partnerEntryId" value="<%= String.valueOf(partnerEntryId) %>" /></portlet:renderURL>">
								<%= partnerEntryCode %>
							</a>
						</c:when>
						<c:otherwise>
							<%= partnerEntryCode %>
						</c:otherwise>
					</c:choose>
				</span>

				<input onClick="var partnerEntryWindow = window.open('<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/admin/select_partner_entry.jsp" /></portlet:renderURL>', 'partner', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); partnerEntryWindow.focus();" type="button" value="<liferay-ui:message key="select" />" />

				<input id="<portlet:namespace />removePartnerEntryButton" onClick="<portlet:namespace />removePartnerEntry();" type="button" value="<liferay-ui:message key="remove" />" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<br />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="time-zone" />
			</td>
			<td>
				<liferay-ui:input-time-zone name="timeZoneId" value="<%= HtmlUtil.escape(timeZoneId) %>" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="start-date" />
			</td>
			<td>
				<aui:input label="" name="startDate" value="<%= startDate %>" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="end-date" />
			</td>
			<td>
				<aui:input label="" name="endDate" value="<%= endDate %>" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<br />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="student-capacity" />
			</td>
			<td>
				<aui:input label="" name="maxCustomers" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<br />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="enrollment-url" />
			</td>
			<td>
				<aui:input label="" name="enrollmentURL" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="certificate-template" />
			</td>
			<td>
				<aui:select label="" name="trainingCertificateTemplateId">
					<aui:option value="" />

					<%
					List<TrainingCertificateTemplate> trainingCertificateTemplates = TrainingCertificateTemplateLocalServiceUtil.getTrainingCertificateTemplates(TrainingCertificateTemplateConstants.TYPE_TRAINING_EVENT);

					for (TrainingCertificateTemplate trainingCertificateTemplate : trainingCertificateTemplates) {
					%>

						<aui:option label="<%= trainingCertificateTemplate.getName() %>" selected="<%= trainingCertificateTemplateId == trainingCertificateTemplate.getTrainingCertificateTemplateId() %>" value="<%= trainingCertificateTemplate.getTrainingCertificateTemplateId() %>" />

					<%
					}
					%>

				</aui:select>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="survey-template" />
			</td>
			<td>
				<aui:select label="" name="ddmStructureId">
					<aui:option value="" />

					<%
					long ddmStructureId = 0;

					DDLRecordSet ddlRecordSet = DDLRecordSetLocalServiceUtil.fetchDDLRecordSet(ddlRecordSetId);

					if (ddlRecordSet != null) {
						ddmStructureId = ddlRecordSet.getDDMStructureId();
					}

					List<DDMStructure> ddmStructures = DDMStructureLocalServiceUtil.search(company.getCompanyId(), new long[] {OSBConstants.GROUP_GUEST_ID}, new long[] {PortalUtil.getClassNameId(TrainingEvent.class)}, StringPool.BLANK, StringPool.BLANK, PortletPropsValues.TRAINING_SURVEY_STRUCTURE_STORAGE_TYPE, DDMStructureConstants.TYPE_DEFAULT, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

					for (DDMStructure ddmStructure : ddmStructures) {
					%>

						<aui:option label="<%= ddmStructure.getName() %>" selected="<%= ddmStructureId == ddmStructure.getStructureId() %>" value="<%= ddmStructure.getStructureId() %>" />

					<%
					}
					%>

				</aui:select>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="survey-group-email" />
			</td>
			<td>
				<aui:input label="" name="emailAddress" />
			</td>
		</tr>
		</table>

		<br />

		<div>
			<input type="submit" value="<liferay-ui:message key="save" />" />

			<input onClick="location.href = '<%= HtmlUtil.escape(backURL) %>';" type="button" value="<liferay-ui:message key="cancel" />" />
		</div>
	</aui:form>

	<br />

	<aui:form name="fm2">
		<liferay-ui:tabs names="students" />

		<aui:input name="trainingCustomerIds" type="hidden" value="" />

		<c:if test="<%= trainingEvent != null %>">
			<aui:input name="trainingEventId" type="hidden" value="<%= String.valueOf(trainingEvent.getTrainingEventId()) %>" />

			<aui:button cssClass="confirm-attendance" disabled="true" onClick='<%= renderResponse.getNamespace() + "updateTrainingCustomerAttendence()" %>' value="confirm-attendance" />

			<portlet:renderURL var="editTrainingEventCustomersURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
				<portlet:param name="mvcPath" value="/admin/edit_training_event_customers.jsp" />
				<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
				<portlet:param name="trainingEventId" value="<%= String.valueOf(trainingEventId) %>" />
			</portlet:renderURL>

			<input onClick="location.href = '<%= editTrainingEventCustomersURL %>';" type="button" value="<liferay-ui:message key="assign-students" />" />

			<portlet:renderURL var="editTrainingEventWorkersURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
				<portlet:param name="mvcPath" value="/admin/edit_training_event_workers.jsp" />
				<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
				<portlet:param name="trainingEventId" value="<%= String.valueOf(trainingEventId) %>" />
			</portlet:renderURL>

			<input onClick="location.href = '<%= editTrainingEventWorkersURL %>';" type="button" value="<liferay-ui:message key="assign-teachers" />" />
		</c:if>

		<liferay-ui:search-container
			emptyResultsMessage="no-students-were-found"
			rowChecker="<%= new RowChecker(renderResponse) %>"
		>
			<liferay-ui:search-container-results
				results="<%= trainingCustomers %>"
				total="<%= trainingCustomers.size() %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.osb.model.TrainingCustomer"
				escapedModel="<%= true %>"
				keyProperty="trainingCustomerId"
				modelVar="trainingCustomer"
			>

				<%
				UserProfileHistory userProfileHistory = UserProfileHistoryLocalServiceUtil.fetchUserProfileHistory(trainingCustomer.getUserProfileHistoryId());
				%>

				<liferay-ui:search-container-column-text
					name="first-name"
					value="<%= HtmlUtil.escape(userProfileHistory.getFirstName()) %>"
				/>

				<liferay-ui:search-container-column-text
					name="last-name"
					value="<%= HtmlUtil.escape(userProfileHistory.getLastName()) %>"
				/>

				<liferay-ui:search-container-column-text
					name="email-address"
					value="<%= HtmlUtil.escape(userProfileHistory.getEmailAddress()) %>"
				/>

				<liferay-ui:search-container-column-text
					name="company"
					value="<%= HtmlUtil.escape(userProfileHistory.getLegalEntityName()) %>"
				/>

				<liferay-ui:search-container-column-text
					name="attended"
				>
					<span class="status" data-status="<%= trainingCustomer.getStatus() %>">
						<liferay-ui:message key='<%= trainingCustomer.getStatus() > 0 ? "yes" : "no" %>' />
					</span>
				</liferay-ui:search-container-column-text>

				<c:choose>
					<c:when test="<%= (trainingCertificateTemplateId > 0) && (trainingEvent.getPortalMinorVersion() > 0) %>">
						<portlet:resourceURL id="generateCertificate" var="generateCertificateURL">
							<portlet:param name="mvcPath" value="/admin/edit_training_event.jsp" />
							<portlet:param name="trainingCustomerId" value="<%= String.valueOf(trainingCustomer.getTrainingCustomerId()) %>" />
						</portlet:resourceURL>

						<liferay-ui:search-container-column-text
							href="<%= generateCertificateURL %>"
							name="certificate-key"
							value="<%= !TrainingCertificateLocalServiceUtil.hasTrainingCertificateCertifiedDate(trainingCustomer.getTrainingCustomerId()) ? StringPool.BLANK : trainingCustomer.getTrainingCertificateKey() %>"
						/>

						<liferay-ui:search-container-column-jsp
							align="right"
							path="/admin/edit_training_event_customer_action.jsp"
						/>
					</c:when>
					<c:otherwise>
						<liferay-ui:search-container-column-text
							name="certificate-key"
							value="<%= !TrainingCertificateLocalServiceUtil.hasTrainingCertificateCertifiedDate(trainingCustomer.getTrainingCustomerId()) ? StringPool.BLANK : trainingCustomer.getTrainingCertificateKey() %>"
						/>
					</c:otherwise>
				</c:choose>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator paginate="<%= false %>" />
		</liferay-ui:search-container>
	</aui:form>
</div>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<script type="text/javascript">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />trainingCourseId);
	</script>
</c:if>

<aui:script>
	function <portlet:namespace />displayLocalizedSlides(languageId) {
		var A = AUI();

		var visible = true;

		var languageIds = '<%= languageIds %>';

		if (languageIds.indexOf(languageId) < 0) {
			visible = false;
		}

		var displayLocalizedSlides = A.one('#<portlet:namespace />displayLocalizedSlides');

		displayLocalizedSlides.toggle(!visible);

		var localizedSlidesCheckbox = A.one("#<portlet:namespace />localizedSlidesCheckbox");

		localizedSlidesCheckbox.set('checked', visible);

		Liferay.Util.updateCheckboxValue(localizedSlidesCheckbox);
	}

	function <portlet:namespace />removePartnerEntry() {
		document.<portlet:namespace />fm.<portlet:namespace />partnerEntryId.value = 0;

		var nameEl = document.getElementById("<portlet:namespace />partnerEntryCode");

		nameEl.href = "";
		nameEl.innerHTML = "";
	}

	function <portlet:namespace />selectPartnerEntry(partnerEntryId, partnerEntryCode) {
		document.<portlet:namespace />fm.<portlet:namespace />partnerEntryId.value = partnerEntryId;

		var nameEl = document.getElementById("<portlet:namespace />partnerEntryCode");

		if (<%= RoleLocalServiceUtil.hasUserRole(permissionChecker.getUserId(), OSBConstants.ROLE_OSB_ADMINISTRATOR_ID) %>) {
			nameEl.innerHTML = "<a href='<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="mvcPath" value="/admin/edit_partner_entry.jsp" /><portlet:param name="redirect" value="<%= portletURL.toString() %>" /></portlet:renderURL>&<portlet:namespace />partnerEntryId=" + partnerEntryId + "'>" + partnerEntryCode + "</a>";
		}
		else {
			nameEl.innerHTML = partnerEntryCode + "&nbsp;";
		}
	}

	Liferay.provide(
		window,
		'<portlet:namespace />saveTrainingEvent',
		function() {
			document.<portlet:namespace />fm.<portlet:namespace />addTrainingCustomerIds.value = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");
			submitForm(document.<portlet:namespace />fm);
		},
		['liferay-util-list-fields']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />selectTrainingLocation',
		function(trainingLocationId) {
			if (trainingLocationId <= 0) {
				document.<portlet:namespace />fm.<portlet:namespace />addressStreet1.value = "";
				document.<portlet:namespace />fm.<portlet:namespace />addressStreet2.value = "";
				document.<portlet:namespace />fm.<portlet:namespace />addressStreet3.value = "";
				document.<portlet:namespace />fm.<portlet:namespace />addressCity.value = "";
				document.<portlet:namespace />fm.<portlet:namespace />addressZip.value = "";
				document.<portlet:namespace />fm.<portlet:namespace />addressCountryId.value = 0;
				document.<portlet:namespace />fm.<portlet:namespace />addressRegionId.options.length = 0;

				return;
			}

			var A = AUI();

			A.io.request(
				'<liferay-portlet:resourceURL id="getAddress" />',
				{
					data: {
						<portlet:namespace />trainingLocationId: trainingLocationId
					},
					dataType: 'json',
					method: 'post',
					on: {
						success: function(event, id, obj) {
							var response = this.get('responseData');

							document.<portlet:namespace />fm.<portlet:namespace />addressStreet1.value = response["street1"];
							document.<portlet:namespace />fm.<portlet:namespace />addressStreet2.value = response["street2"];
							document.<portlet:namespace />fm.<portlet:namespace />addressStreet3.value = response["street3"];
							document.<portlet:namespace />fm.<portlet:namespace />addressCity.value = response["city"];
							document.<portlet:namespace />fm.<portlet:namespace />addressZip.value = response["zip"];
							document.<portlet:namespace />fm.<portlet:namespace />addressCountryId.value = response["countryId"];

							var regionOptions = [];

							var regions = JSON.parse(response["regions"]);

							for (var i in regions) {
								var region = regions[i];

								var key = region.regionId;
								var value = region.name;

								regionOptions.push('<option value="' + key + '">' + value + '</option>');
							}

							regionOptions = regionOptions.join('');

							var options = document.<portlet:namespace />fm.<portlet:namespace />addressRegionId;

							var select = A.one(options);

							if (select) {
								select.empty().append(regionOptions).val(response["regionId"]);

								if (Liferay.Browser.isIe()) {
									select.setStyle('width', 'auto');
								}
							}
						}
					}
				}
			);
		},
		['aui-io']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />updateTrainingCustomerAttendence',
		function() {
			document.<portlet:namespace />fm2.<portlet:namespace />trainingCustomerIds.value = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm2, '<portlet:namespace />allRowIds');

			<portlet:actionURL name="updateTrainingCustomerAttendence" var="updateTrainingCustomerAttendenceURL">
				<portlet:param name="mvcPath" value="/admin/edit_training_event.jsp" />
				<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
			</portlet:actionURL>

			submitForm(document.<portlet:namespace />fm2, '<%= updateTrainingCustomerAttendenceURL %>');
		},
		['liferay-util-list-fields']
	);
</aui:script>

<aui:script use="aui-node,liferay-dynamic-select">
	new Liferay.DynamicSelect(
		[
			{
				select: "<portlet:namespace />addressCountryId",
				selectData: Liferay.Address.getCountries,
				selectDesc: 'name',
				selectId: 'countryId',
				selectVal: "<%= addressCountryId %>"
			},
			{
				select: "<portlet:namespace />addressRegionId",
				selectData:Liferay.Address.getRegions,
				selectDesc: "name",
				selectId: "regionId",
				selectVal: "<%= addressRegionId %>"
			}
		]
	);

	A.one('#<portlet:namespace />fm2').delegate(
		'change',
		function(event) {
			var confirmAttendenceButton = A.one('#<portlet:namespace />fm2 .confirm-attendance');
			var confirmAttendenceButtonInput = confirmAttendenceButton.one('input');

			var rowsChecked = event.currentTarget.ancestor('table').all('input[name="<portlet:namespace />rowIds"]:checked');

			if (rowsChecked.isEmpty()) {
				confirmAttendenceButton.addClass('aui-button-disabled');

				confirmAttendenceButtonInput.setAttribute('disabled');
			}
			else {
				confirmAttendenceButton.removeClass('aui-button-disabled');

				confirmAttendenceButtonInput.removeAttribute('disabled');
			}
		},
		'input[type=checkbox]'
	);
</aui:script>