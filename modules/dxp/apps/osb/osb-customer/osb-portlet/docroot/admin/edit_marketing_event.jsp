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

long marketingEventId = ParamUtil.getLong(request, "marketingEventId");

MarketingEvent marketingEvent = MarketingEventLocalServiceUtil.fetchMarketingEvent(marketingEventId);

int type = BeanParamUtil.getInteger(marketingEvent, request, "type");
String defaultLanguageId = BeanParamUtil.getString(marketingEvent, request, "defaultLanguageId", LocaleUtil.toLanguageId(LocaleUtil.getDefault()));
boolean dateTBA = BeanParamUtil.getBoolean(marketingEvent, request, "dateTBA");
int globalRegion = BeanParamUtil.getInteger(marketingEvent, request, "globalRegion");
boolean online = BeanParamUtil.getBoolean(marketingEvent, request, "online");
int registrationType = BeanParamUtil.getInteger(marketingEvent, request, "registrationType");

long imageFileEntryId = BeanParamUtil.getLong(marketingEvent, request, "imageFileEntryId");

FileEntry imageFileEntry = null;

if (marketingEvent != null) {
	imageFileEntry = marketingEvent.getImageFileEntry();
}

long slidesFileEntryId = BeanParamUtil.getLong(marketingEvent, request, "slidesFileEntryId");

FileEntry slidesFileEntry = null;

if (marketingEvent != null) {
	slidesFileEntry = marketingEvent.getSlidesFileEntry();
}

String timeZoneId = BeanParamUtil.getString(marketingEvent, request, "timeZoneId", StringPool.UTC);

TimeZone selTimeZone = TimeZoneUtil.getTimeZone(timeZoneId);

Calendar startDate = CalendarFactoryUtil.getCalendar(selTimeZone, locale);

if ((marketingEvent != null) && (marketingEvent.getStartDate() != null)) {
	startDate.setTime(marketingEvent.getStartDate());
}
else {
	startDate.set(Calendar.HOUR_OF_DAY, 9);
	startDate.set(Calendar.MINUTE, 0);
}

Calendar endDate = (Calendar)startDate.clone();

if ((marketingEvent != null) && (marketingEvent.getEndDate() != null)) {
	endDate.setTime(marketingEvent.getEndDate());
}
else {
	endDate.add(Calendar.DATE, 3);

	endDate.set(Calendar.HOUR_OF_DAY, 17);
	endDate.set(Calendar.MINUTE, 0);
}

Address address = null;

if (marketingEvent != null) {
	address = marketingEvent.getAddress();
}

long addressRegionId = ParamUtil.getLong(request, "addressRegionId", BeanPropertiesUtil.getLong(address, "regionId"));
long addressCountryId = ParamUtil.getLong(request, "addressCountryId", BeanPropertiesUtil.getLong(address, "countryId"));
%>

<portlet:actionURL name="updateMarketingEvent" var="updateMarketingEventURL">
	<portlet:param name="mvcPath" value="/admin/edit_marketing_event.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateMarketingEventURL %>" cssClass="edit-marketing-event" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "updateMarketingEvent();" %>'>
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="marketingEventId" type="hidden" value="<%= marketingEventId %>" />
	<aui:input name="imageFileEntryId" type="hidden" value="<%= imageFileEntryId %>" />
	<aui:input name="slidesFileEntryId" type="hidden" value="<%= slidesFileEntryId %>" />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="marketing-event"
	/>

	<liferay-ui:error exception="<%= AddressCityException.class %>" message="please-enter-a-valid-city" />
	<liferay-ui:error exception="<%= AddressStreetException.class %>" message="please-enter-a-valid-street" />
	<liferay-ui:error exception="<%= AddressZipException.class %>" message="please-enter-a-valid-zip" />
	<liferay-ui:error exception="<%= MarketingEventEndDateException.class %>" message="please-enter-a-valid-end-date" />
	<liferay-ui:error exception="<%= MarketingEventHostedByException.class %>" message="please-enter-a-valid-host" />
	<liferay-ui:error exception="<%= MarketingEventHostedByURLException.class %>" message="please-enter-a-valid-host-url" />
	<liferay-ui:error exception="<%= MarketingEventRegistrationURLException.class %>" message="please-enter-a-valid-registration-url" />
	<liferay-ui:error exception="<%= MarketingEventStartDateException.class %>" message="please-enter-a-valid-start-date" />

	<liferay-ui:error exception="<%= MarketingEventTitleException.class %>">

		<%
		MarketingEventTitleException mete = (MarketingEventTitleException)errorException;

		List<String> languageDisplayNames = new ArrayList<String>();

		for (String languageId : mete.getLanguageIds()) {
			Locale curLocale = LocaleUtil.fromLanguageId(languageId);

			languageDisplayNames.add(curLocale.getDisplayName(locale));
		}
		%>

		<c:if test="<%= !languageDisplayNames.isEmpty() %>">
			<%= LanguageUtil.format(pageContext, "please-enter-a-valid-title-for-x", StringUtil.merge(languageDisplayNames, StringPool.COMMA_AND_SPACE)) %>
		</c:if>
	</liferay-ui:error>

	<liferay-ui:error exception="<%= MarketingEventTitleURLException.class %>" message="please-enter-a-valid-title-url" />
	<liferay-ui:error exception="<%= MarketingEventVideoTitleException.class %>" message="please-enter-a-valid-video-title" />
	<liferay-ui:error exception="<%= NoSuchCountryException.class %>" message="please-select-a-country" />
	<liferay-ui:error exception="<%= NoSuchRegionException.class %>" message="please-select-a-region" />

	<aui:model-context bean="<%= marketingEvent %>" model="<%= MarketingEvent.class %>" />

	<aui:fieldset>

		<%
		String taglibType = renderResponse.getNamespace() + "changeType(this.value);";
		%>

		<aui:select name="type" onChange="<%= taglibType %>">

			<%
			for (int curType : MarketingEventConstants.TYPES) {
			%>

				<aui:option label="<%= MarketingEventConstants.getTypeLabel(curType) %>" selected="<%= curType == type %>" value="<%= curType %>" />

			<%
			}
			%>

		</aui:select>

		<%@ include file="/admin/marketing_event_translation.jspf" %>

		<aui:input helpMessage="urls-must-start-with-http-or-https" label="title-url" name="titleURL" />

		<aui:input name="hostedBy" />

		<aui:input helpMessage="urls-must-start-with-http-or-https" label="hosted-by-url" name="hostedByURL" />

		<div id="<portlet:namespace />eventImage">
			<strong><liferay-ui:message key="image" /></strong><br />

			<div id="<portlet:namespace />imageFileTitle">
				<%= (imageFileEntry == null) ? StringPool.BLANK : HtmlUtil.escape(imageFileEntry.getTitle()) %>
			</div>

			<aui:button-row>
				<liferay-portlet:renderURL portletName="<%= PortletKeys.DOCUMENT_LIBRARY %>" var="selectFileEntryURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
					<liferay-portlet:param name="struts_action" value="/document_library/select_file_entry" />
					<liferay-portlet:param name="groupId" value="<%= String.valueOf(OSBConstants.GROUP_GUEST_ID) %>" />
				</liferay-portlet:renderURL>

				<%
				String taglibSelectFileEntryWindow = renderResponse.getNamespace() + "setSelectFileEntryType('image'); var selectFileEntryWindow = window.open('" + selectFileEntryURL + "', 'selectFileEntry', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); selectFileEntryWindow.focus();";
				%>

				<aui:button onClick="<%= taglibSelectFileEntryWindow %>" value="select" />

				<%
				String taglibRemoveFileEntry = renderResponse.getNamespace() + "removeFileEntry('image');";
				%>

				<aui:button onClick="<%= taglibRemoveFileEntry %>" value="remove" />
			</aui:button-row>
		</div>

		<div id="<portlet:namespace />webinarSection" style='<%= (type == MarketingEventConstants.TYPE_WEBINAR) ? StringPool.BLANK : "display: none;" %>'>
			<strong><liferay-ui:message key="slides" /></strong><br />

			<div id="<portlet:namespace />slidesFileTitle">
				<%= (slidesFileEntry == null) ? StringPool.BLANK : HtmlUtil.escape(slidesFileEntry.getTitle()) %>
			</div>

			<aui:button-row>
				<liferay-portlet:renderURL portletName="<%= PortletKeys.DOCUMENT_LIBRARY %>" var="selectFileEntryURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
					<liferay-portlet:param name="struts_action" value="/document_library/select_file_entry" />
					<liferay-portlet:param name="groupId" value="<%= String.valueOf(OSBConstants.GROUP_GUEST_ID) %>" />
				</liferay-portlet:renderURL>

				<%
				String taglibSelectFileEntryWindow = renderResponse.getNamespace() + "setSelectFileEntryType('slides'); var selectFileEntryWindow = window.open('" + selectFileEntryURL.toString() + "', 'selectFileEntry', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); selectFileEntryWindow.focus();";
				%>

				<aui:button onClick="<%= taglibSelectFileEntryWindow %>" value="select" />

				<%
				String taglibRemoveFileEntry = renderResponse.getNamespace() + "removeFileEntry('slides');";
				%>

				<aui:button onClick="<%= taglibRemoveFileEntry %>" value="remove" />
			</aui:button-row>

			<aui:input helpMessage="video-title-help" name="videoTitle" />
		</div>

		<liferay-ui:panel collapsible="<%= true %>" extended="<%= false %>" persistState="<%= true %>" title="dates">
			<aui:field-wrapper label="time-zone">
				<liferay-ui:input-time-zone name="timeZoneId" value="<%= HtmlUtil.escape(timeZoneId) %>" />
			</aui:field-wrapper>

			<aui:input name="startDate" value="<%= startDate %>" />

			<aui:input disabled="<%= dateTBA %>" name="endDate" value="<%= endDate %>" />

			<%
			String taglibDateTBA = renderResponse.getNamespace() + "disableInputDate('endDate', this.checked);";
			%>

			<aui:input helpMessage="date-to-be-announced-help" label="date-to-be-announced" name="dateTBA" onClick="<%= taglibDateTBA %>" type="checkbox" value="<%= dateTBA %>" />
		</liferay-ui:panel>

		<liferay-ui:panel collapsible="<%= true %>" extended="<%= false %>" persistState="<%= true %>" title="address">
			<aui:input cssClass="address" disabled="<%= online %>" label="street1" name="addressStreet1" type="text" value="<%= (address == null ? StringPool.BLANK : address.getStreet1()) %>" />

			<aui:input cssClass="address" disabled="<%= online %>" label="street2" name="addressStreet2" type="text" value="<%= (address == null ? StringPool.BLANK : address.getStreet2()) %>" />

			<aui:input cssClass="address" disabled="<%= online %>" label="street3" name="addressStreet3" type="text" value="<%= (address == null ? StringPool.BLANK : address.getStreet3()) %>" />

			<aui:input cssClass="address" disabled="<%= online %>" label="city" name="addressCity" type="text" value="<%= (address == null ? StringPool.BLANK : address.getCity()) %>" />

			<aui:input cssClass="address" disabled="<%= online %>" label="zip" name="addressZip" type="text" value="<%= (address == null ? StringPool.BLANK : address.getZip()) %>" />

			<aui:select disabled="<%= online %>" label="country" name="addressCountryId" />

			<aui:select disabled="<%= online %>" label="region" name="addressRegionId" />

			<aui:select disabled="<%= online %>" name="globalRegion">

				<%
				for (int curGlobalRegion : MarketingEventConstants.GLOBAL_REGIONS) {
				%>

					<aui:option label="<%= MarketingEventConstants.getGlobalRegionLabel(curGlobalRegion) %>" selected="<%= curGlobalRegion == globalRegion %>" value="<%= curGlobalRegion %>" />

				<%
				}
				%>

			</aui:select>

			<%
			String taglibOnline = renderResponse.getNamespace() + "disableInputAddress(this.checked);";
			%>

			<aui:input helpMessage="online-help" name="online" onClick="<%= taglibOnline %>" type="checkbox" value="<%= online %>" />
		</liferay-ui:panel>

		<liferay-ui:panel collapsible="<%= true %>" extended="<%= false %>" persistState="<%= true %>" title="registration">
			<aui:select name="registrationType">

				<%
				for (int curRegistrationType : MarketingEventConstants.REGISTRATION_TYPES) {
				%>

					<aui:option label="<%= MarketingEventConstants.getRegistrationTypeLabel(curRegistrationType) %>" selected="<%= curRegistrationType == registrationType %>" value="<%= curRegistrationType %>" />

				<%
				}
				%>

			</aui:select>

			<aui:input helpMessage="urls-must-start-with-http-or-https" label="registration-url" name="registrationURL" />
		</liferay-ui:panel>

		<aui:button-row>
			<aui:button type="submit" value="save" />

			<aui:button href="<%= backURL %>" type="cancel" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<aui:script>
	var selectFileEntryType = '';

	Liferay.provide(
		window,
		'<%= PortalUtil.getPortletNamespace(PortletKeys.DOCUMENT_LIBRARY) %>selectFileEntry',
		function(fileEntryId, title) {
			if (selectFileEntryType == 'image') {
				document.<portlet:namespace />fm.<portlet:namespace />imageFileEntryId.value = fileEntryId;

				document.getElementById('<portlet:namespace />imageFileTitle').innerHTML = title;
			}
			else if (selectFileEntryType == 'slides') {
				document.<portlet:namespace />fm.<portlet:namespace />slidesFileEntryId.value = fileEntryId;

				document.getElementById('<portlet:namespace />slidesFileTitle').innerHTML = title;
			}
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />changeType',
		function(type) {
			if (type == '<%= MarketingEventConstants.TYPE_WEBINAR %>') {
				document.getElementById('<portlet:namespace />webinarSection').style.display = '';
			}
			else {
				document.getElementById('<portlet:namespace />webinarSection').style.display = 'none';
			}
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />disableInputAddress',
		function(checked) {
			document.<portlet:namespace />fm['<portlet:namespace />addressStreet1'].disabled = checked;
			document.<portlet:namespace />fm['<portlet:namespace />addressStreet1'].value = '';
			document.<portlet:namespace />fm['<portlet:namespace />addressStreet2'].disabled = checked;
			document.<portlet:namespace />fm['<portlet:namespace />addressStreet2'].value = '';
			document.<portlet:namespace />fm['<portlet:namespace />addressStreet3'].disabled = checked;
			document.<portlet:namespace />fm['<portlet:namespace />addressStreet3'].value = '';
			document.<portlet:namespace />fm['<portlet:namespace />addressCity'].disabled = checked;
			document.<portlet:namespace />fm['<portlet:namespace />addressCity'].value = '';
			document.<portlet:namespace />fm['<portlet:namespace />addressZip'].disabled = checked;
			document.<portlet:namespace />fm['<portlet:namespace />addressZip'].value = '';
			document.<portlet:namespace />fm['<portlet:namespace />addressCountryId'].disabled = checked;
			document.<portlet:namespace />fm['<portlet:namespace />addressCountryId'].value = '0';
			document.<portlet:namespace />fm['<portlet:namespace />addressRegionId'].disabled = checked;
			document.<portlet:namespace />fm['<portlet:namespace />addressRegionId'].value = '0';
			document.<portlet:namespace />fm['<portlet:namespace />globalRegion'].disabled = checked;
			document.<portlet:namespace />fm['<portlet:namespace />globalRegion'].value = '<%= MarketingEventConstants.GLOBAL_REGION_WORLDWIDE %>';
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />disableInputDate',
		function(date, checked) {
			var A = AUI();

			document.<portlet:namespace />fm['<portlet:namespace />' + date + 'Month'].disabled = checked;
			document.<portlet:namespace />fm['<portlet:namespace />' + date + 'Day'].disabled = checked;
			document.<portlet:namespace />fm['<portlet:namespace />' + date + 'Year'].disabled = checked;
			document.<portlet:namespace />fm['<portlet:namespace />' + date + 'Hour'].disabled = checked;
			document.<portlet:namespace />fm['<portlet:namespace />' + date + 'Minute'].disabled = checked;
			document.<portlet:namespace />fm['<portlet:namespace />' + date + 'AmPm'].disabled = checked;

			var calendarWidget = A.Widget.getByNode(document.<portlet:namespace />fm['<portlet:namespace />' + date + 'Month']);

			if (calendarWidget) {
				calendarWidget.set('disabled', checked);
			}
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />removeFileEntry',
		function(fileEntryType) {
			if (fileEntryType == 'image') {
				document.<portlet:namespace />fm.<portlet:namespace />imageFileEntryId.value = 0;

				document.getElementById('<portlet:namespace />imageFileTitle').innerHTML = '';
			}
			else if (fileEntryType == 'slides') {
				document.<portlet:namespace />fm.<portlet:namespace />slidesFileEntryId.value = 0;

				document.getElementById('<portlet:namespace />slidesFileTitle').innerHTML = '';
			}
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />setSelectFileEntryType',
		function(value) {
			selectFileEntryType = value;
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />updateMarketingEvent',
		function() {
			var defaultLanguageId = document.getElementById('<portlet:namespace />defaultLanguageId').value;

			document.getElementById('<portlet:namespace />translation_' + defaultLanguageId + '_summary').value = window.<portlet:namespace />summaryTranslation.getHTML();
			document.getElementById('<portlet:namespace />translation_' + defaultLanguageId + '_title').value = window.<portlet:namespace />titleTranslation.value;

			submitForm(document.<portlet:namespace />fm);
		},
		['aui-base']
	);
</aui:script>

<aui:script use="liferay-dynamic-select">
	new Liferay.DynamicSelect(
		[
			{
				select: '<portlet:namespace />addressCountryId',
				selectData: Liferay.Address.getCountries,
				selectDesc: 'name',
				selectId: 'countryId',
				selectVal: '<%= addressCountryId %>'
			},
			{
				select: '<portlet:namespace />addressRegionId',
				selectData:Liferay.Address.getRegions,
				selectDesc: 'name',
				selectId: 'regionId',
				selectVal: '<%= addressRegionId %>'
			}
		]
	);
</aui:script>