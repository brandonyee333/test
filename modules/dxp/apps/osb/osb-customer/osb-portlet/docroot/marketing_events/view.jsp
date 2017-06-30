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

<%@ include file="/marketing_events/init.jsp" %>

<%
int[] types = ParamUtil.getIntegerValues(request, "types");
int[] globalRegions = ParamUtil.getIntegerValues(request, "globalRegions");
long[] countryIds = ParamUtil.getLongValues(request, "countryIds");
boolean pastEvents = ParamUtil.getBoolean(request, "pastEvents");

if (Validator.isNotNull(defaultType)) {
	types = MarketingEventConstants.getTypes(GetterUtil.getInteger(defaultType));
}
%>

<liferay-portlet:renderURL varImpl="searchURL" />

<aui:form action="<%= searchURL %>" cssClass="jsp-view" method="get" name="fm">
	<liferay-portlet:renderURLParams varImpl="searchURL" />
	<aui:input name="types" type="hidden" value="<%= StringUtil.merge(types) %>" />
	<aui:input name="globalRegions" type="hidden" value="<%= StringUtil.merge(globalRegions) %>" />
	<aui:input name="countryIds" type="hidden" value="<%= StringUtil.merge(countryIds) %>" />
	<aui:input name="pastEvents" type="hidden" value="<%= pastEvents %>" />

	<liferay-ui:header
		localizeTitle="<%= false %>"
		title="<%= headerTitle %>"
	/>

	<div class="header">
		<c:if test="<%= Validator.isNotNull(headerContent) %>">
			<div class="header-content">
				<%= headerContent %>
			</div>
		</c:if>

		<c:if test="<%= showSearch %>">
			<aui:input cssClass="search-box" ignoreRequestValue="<%= true %>" label="" name="searchBox" onKeyPress="if (event.keyCode == 13) { return false; }" />
		</c:if>

		<c:if test="<%= Validator.isNull(defaultType) %>">

			<%
			String taglibTypesOnClick = renderResponse.getNamespace() + "filter('types', this.value, false);";
			%>

			<aui:select ignoreRequestValue="<%= true %>" inlineField="<%= true %>" label="" name="filterByTypes" onChange="<%= taglibTypesOnClick %>">
				<aui:option label='<%= (types.length > 0) ? LanguageUtil.format(pageContext, "x-selected-x", new Object[] {types.length, "type"}) : LanguageUtil.format(pageContext, "all-selected-x", "type") %>' />
				<aui:option label="clear-options" value="-1" />

				<%
				for (JSONObject typeJSONObject : MarketingEventsUtil.getTypeJSONObjects(globalRegions, countryIds, null, pastEvents, themeDisplay.getLanguageId())) {
				%>

					<aui:option cssClass='<%= ArrayUtil.contains(types, typeJSONObject.getInt("type")) ? "highlighted-option" : StringPool.BLANK %>' disabled='<%= typeJSONObject.getInt("count") == 0 %>' label='<%= typeJSONObject.getString("label") %>' value='<%= typeJSONObject.getInt("type") %>' />

				<%
				}
				%>

			</aui:select>
		</c:if>

		<%
		String taglibGlobalRegionsOnClick = renderResponse.getNamespace() + "filter('globalRegions', this.value, false);";
		%>

		<aui:select ignoreRequestValue="<%= true %>" inlineField="<%= true %>" label="" name="filterByGlobalRegions" onChange="<%= taglibGlobalRegionsOnClick %>">
			<aui:option label='<%= (globalRegions.length > 0) ? LanguageUtil.format(pageContext, "x-selected-x", new Object[] {globalRegions.length, "global-region"}) : LanguageUtil.format(pageContext, "all-selected-x", "global-region") %>' />
			<aui:option label="clear-options" value="-1" />

			<%
			for (JSONObject globalRegionJSONObject : MarketingEventsUtil.getGlobalRegionJSONObjects(types, countryIds, null, pastEvents, themeDisplay.getLanguageId())) {
			%>

				<aui:option cssClass='<%= ArrayUtil.contains(globalRegions, globalRegionJSONObject.getInt("globalRegion")) ? "highlighted-option" : StringPool.BLANK %>' disabled='<%= globalRegionJSONObject.getInt("count") == 0 %>' label='<%= globalRegionJSONObject.getString("label") %>' value='<%= globalRegionJSONObject.getInt("globalRegion") %>' />

			<%
			}
			%>

		</aui:select>

		<%
		String taglibCountryIdsOnClick = renderResponse.getNamespace() + "filter('countryIds', this.value, false);";
		%>

		<aui:select ignoreRequestValue="<%= true %>" inlineField="<%= true %>" label="" name="filterByCountryIds" onChange="<%= taglibCountryIdsOnClick %>">
			<aui:option label='<%= (countryIds.length > 0) ? LanguageUtil.format(pageContext, "x-selected-x", new Object[] {countryIds.length, "country"}) : LanguageUtil.format(pageContext, "all-selected-x", "country") %>' />
			<aui:option label="clear-options" value="-1" />

			<%
			for (Country country : MarketingEventsUtil.getCountries(types, globalRegions, null, pastEvents)) {
				String countryName = StringUtil.replace(country.getName(), StringPool.SPACE, StringPool.DASH);
			%>

				<aui:option cssClass='<%= ArrayUtil.contains(countryIds, country.getCountryId()) ? "highlighted-option" : StringPool.BLANK %>' label="<%= StringUtil.lowerCase(countryName) %>" value="<%= country.getCountryId() %>" />

			<%
			}
			%>

		</aui:select>

		<%
		String taglibPastEventsOnClick = renderResponse.getNamespace() + "filter('pastEvents', this.value, false);";
		%>

		<aui:input ignoreRequestValue="<%= true %>" inlineField="<%= true %>" label="past-events" name="pastEvents" onClick="<%= taglibPastEventsOnClick %>" type="checkbox" value="<%= pastEvents %>" />
	</div>

	<%
	List<Country> countries = MarketingEventsUtil.getCountries(countryIds);
	%>

	<c:if test="<%= !countries.isEmpty() || (globalRegions.length > 0) || (types.length > 0) %>">
		<div class="navigation">
			<c:if test="<%= Validator.isNull(defaultType) && (types.length > 0) %>">
				<div class="container">
					<span class="title"><liferay-ui:message key="type" /></span>

					<%
					for (int curType : types) {
					%>

						<span class="label">

							<%
							String taglibRemoveOnClick = renderResponse.getNamespace() + "filter('types', '" + curType + "', false);";
							String taglibTextOnClick = renderResponse.getNamespace() + "filter('types', '" + curType + "', true);";
							%>

							<aui:a cssClass="text" href="javascript:;" onClick="<%= taglibTextOnClick %>" title='<%= LanguageUtil.format(pageContext, "search-only-in-x", MarketingEventConstants.getTypeLabel(curType)) %>'><%= LanguageUtil.get(pageContext, MarketingEventConstants.getTypeLabel(curType)) %>

							</aui:a><aui:a cssClass="remove" href="javascript:;" onClick="<%= taglibRemoveOnClick %>" title='<%= LanguageUtil.format(pageContext, "remove-x", MarketingEventConstants.getTypeLabel(curType)) %>'>x</aui:a>
						</span>

					<%
					}
					%>

				</div>
			</c:if>

			<c:if test="<%= globalRegions.length > 0 %>">
				<div class="container">
					<span class="title"><liferay-ui:message key="global-region" /></span>

					<%
					for (int curGlobalRegion : globalRegions) {
					%>

						<span class="label">

							<%
							String taglibRemoveOnClick = renderResponse.getNamespace() + "filter('globalRegions', '" + curGlobalRegion + "', false);";
							String taglibTextOnClick = renderResponse.getNamespace() + "filter('globalRegions', '" + curGlobalRegion + "', true);";
							%>

							<aui:a cssClass="text" href="javascript:;" onClick="<%= taglibTextOnClick %>" title='<%= LanguageUtil.format(pageContext, "search-only-in-x", MarketingEventConstants.getGlobalRegionLabel(curGlobalRegion)) %>'><liferay-ui:message key="<%= MarketingEventConstants.getGlobalRegionLabel(curGlobalRegion) %>" />

							</aui:a><aui:a cssClass="remove" href="javascript:;" onClick="<%= taglibRemoveOnClick %>" title='<%= LanguageUtil.format(pageContext, "remove-x", MarketingEventConstants.getGlobalRegionLabel(curGlobalRegion)) %>'>x</aui:a>
						</span>

					<%
					}
					%>

				</div>
			</c:if>

			<c:if test="<%= !countries.isEmpty() %>">
				<div class="container">
					<span class="title"><liferay-ui:message key="country" /></span>

					<%
					for (Country country : countries) {
					%>

						<span class="label">

							<%
							String taglibRemoveOnClick = renderResponse.getNamespace() + "filter('countryIds', '" + country.getCountryId() + "', false);";
							String taglibTextOnClick = renderResponse.getNamespace() + "filter('countryIds', '" + country.getCountryId() + "', true);";
							%>

							<aui:a cssClass="text" href="javascript:;" onClick="<%= taglibTextOnClick %>" title='<%= LanguageUtil.format(pageContext, "search-only-in-x", country.getName()) %>'><liferay-ui:message key="<%= country.getName() %>" />

							</aui:a><aui:a cssClass="remove" href="javascript:;" onClick="<%= taglibRemoveOnClick %>" title='<%= LanguageUtil.format(pageContext, "remove-x", country.getName()) %>'>x</aui:a>
						</span>

					<%
					}
					%>

				</div>
			</c:if>
		</div>
	</c:if>

	<div class="content" id="<portlet:namespace />marketingEventsContainer">

		<%
		String orderByType = MarketingEventConstants.ORDER_BY_TYPE_ASC;

		if (pastEvents) {
			orderByType = MarketingEventConstants.ORDER_BY_TYPE_DESC;
		}

		List<MarketingEvent> marketingEvents = MarketingEventLocalServiceUtil.getMarketingEvents(types, globalRegions, countryIds, null, pastEvents, themeDisplay.getLanguageId(), null, orderByType);

		for (MarketingEvent marketingEvent : marketingEvents) {
		%>

			<div class="marketing-event" data-marketingEventId="<%= marketingEvent.getMarketingEventId() %>">
				<div class="marketing-event-header">

					<%
					FileEntry imageFileEntry = marketingEvent.getImageFileEntry();
					%>

					<c:choose>
						<c:when test="<%= imageFileEntry != null %>">
							<img src='<%= themeDisplay.getPathContext() + "/documents/" + imageFileEntry.getRepositoryId() + "/" + imageFileEntry.getFolderId() + "/" + HttpUtil.encodeURL(imageFileEntry.getTitle()) + "/" + imageFileEntry.getUuid() %>' />
						</c:when>
						<c:otherwise>
							<img src='<%= PortalUtil.getPathContext(request) + "/marketing_events/images/" + TextFormatter.format(MarketingEventConstants.getTypeLabel(marketingEvent.getType()) + ".svg", TextFormatter.N) %>' />
						</c:otherwise>
					</c:choose>
				</div>

				<div class="marketing-event-content">
					<div class="title">

						<%
						String title = GetterUtil.getString(marketingEvent.getTitle(locale), marketingEvent.getTitle(marketingEvent.getDefaultLanguageId()));
						%>

						<%= StringUtil.shorten(title, 50) %>
					</div>

					<div class="date">
						<c:choose>
							<c:when test="<%= !marketingEvent.isDateTBA() %>">

								<%
								TimeZone marketingEventTimeZone = TimeZoneUtil.getTimeZone(marketingEvent.getTimeZoneId());

								Format marketingEventLongDateFormatDate = FastDateFormatFactoryUtil.getSimpleDateFormat("MMM dd, yyyy", locale, marketingEventTimeZone);
								Format marketingEventLongDateFormatTime = FastDateFormatFactoryUtil.getSimpleDateFormat("h:mm a z", locale, marketingEventTimeZone);
								Format marketingEventShortDateFormatDate = FastDateFormatFactoryUtil.getSimpleDateFormat("MMM dd", locale, marketingEventTimeZone);

								Format userDateFormatDate = FastDateFormatFactoryUtil.getSimpleDateFormat("MMM dd, yyyy", locale, user.getTimeZone());
								Format userDateFormatTime = FastDateFormatFactoryUtil.getSimpleDateFormat("h:mm a z", locale, user.getTimeZone());
								%>

								<c:choose>
									<c:when test="<%= !themeDisplay.isSignedIn() || !marketingEvent.isTypeWebinar() %>">
										<c:choose>
											<c:when test="<%= !Validator.equals(marketingEventLongDateFormatDate.format(marketingEvent.getStartDate()), marketingEventLongDateFormatDate.format(marketingEvent.getEndDate())) %>">
												<%= marketingEventShortDateFormatDate.format(marketingEvent.getStartDate()) %> - <%= marketingEventLongDateFormatDate.format(marketingEvent.getEndDate()) %>
											</c:when>
											<c:otherwise>
												<%= marketingEventLongDateFormatDate.format(marketingEvent.getStartDate()) %>
											</c:otherwise>
										</c:choose>

										<br />

										<%= marketingEventLongDateFormatTime.format(marketingEvent.getStartDate()) %>
									</c:when>
									<c:when test="<%= Validator.equals(marketingEventLongDateFormatDate.format(marketingEvent.getStartDate()), userDateFormatDate.format(marketingEvent.getStartDate())) %>">
										<%= marketingEventLongDateFormatDate.format(marketingEvent.getStartDate()) %><br />
										<%= marketingEventLongDateFormatTime.format(marketingEvent.getStartDate()) %>

										<c:if test="<%= !marketingEventTimeZone.hasSameRules(user.getTimeZone()) %>">
											| <%= userDateFormatTime.format(marketingEvent.getStartDate()) %>
										</c:if>
									</c:when>
									<c:otherwise>
										<%= marketingEventLongDateFormatDate.format(marketingEvent.getStartDate()) %> <%= marketingEventLongDateFormatTime.format(marketingEvent.getStartDate()) %><br />
										<%= userDateFormatDate.format(marketingEvent.getStartDate()) %> <%= userDateFormatTime.format(marketingEvent.getStartDate()) %>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<liferay-ui:message key="to-be-announced" />
							</c:otherwise>
						</c:choose>
					</div>

					<div class="location">

						<%
						Address address = marketingEvent.getAddress();
						%>

						<c:choose>
							<c:when test="<%= address != null %>">
								<c:if test="<%= Validator.isNotNull(address.getCity()) %>">
									<%= HtmlUtil.escape(address.getCity()) %><%= (address.getRegionId() > 0) ? (StringPool.COMMA_AND_SPACE + HtmlUtil.escape(address.getRegion().getName())) : StringPool.BLANK %>
								</c:if>

								<c:if test="<%= address.getCountry() != null %>">
									<br />

									<%= HtmlUtil.escape(address.getCountry().getName()) %>
								</c:if>
							</c:when>
							<c:when test="<%= marketingEvent.isOnline() %>">
								<liferay-ui:message key="online" />
							</c:when>
						</c:choose>
					</div>
				</div>
			</div>

		<%
		}
		%>

		<c:if test="<%= marketingEvents.isEmpty() %>">
			<div class="row-no-results">
				<liferay-ui:message key="no-events-were-found" />
			</div>
		</c:if>
	</div>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />filter',
		function(param, value, remove) {
			if (value == '') {
				return;
			}

			var inputEl = document.getElementById('<portlet:namespace />' + param);

			if (value == '-1') {
				inputEl.value = '';
			}
			else if ((inputEl.value == '') || remove) {
				inputEl.value = value;
			}
			else {
				var values = inputEl.value;

				if (values.indexOf(value) != -1) {
					var newValues = values.split(',');

					newValues.splice(newValues.indexOf(value), 1);

					inputEl.value = newValues.join();
				}
				else {
					inputEl.value = values + ',' + value;
				}
			}

			<portlet:namespace />refreshPortlet();
		}
	);

	Liferay.provide(
		window,
		'<portlet:namespace />openPopup',
		function(marketingEventId) {
			var A = AUI();

			var width = 600;

			if (document.documentElement.clientWidth < 650) {
				width = document.documentElement.clientWidth * .9;
			}

			var dialog = new A.Dialog(
				{
					constrain: true,
					cssClass: 'osb-portlet-marketing-events-popup',
					destroyOnClose: true,
					draggable: false,
					modal: true,
					resizable: false,
					width: width
				}
			);

			dialog.alignToViewport((document.documentElement.clientWidth - width) / 2, 90);

			dialog.plug(
				A.Plugin.IO,
				{
					data: {
						marketingEventId: marketingEventId
					},
					uri: '<liferay-portlet:renderURL secure="<%= request.isSecure() %>" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/marketing_events/view_marketing_event.jsp" /></liferay-portlet:renderURL>'
				}
			);

			dialog.render();

			A.one('body').on(
				['click', 'keyup'],
				function(event) {
					if ((event.target == A.one('.aui-overlaymask-content')) || (event.keyCode == 27)) {
						dialog.close();
					}
				}
			);
		},
		['aui-base', 'aui-dialog', 'aui-io']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />refreshPortlet',
		function() {
			var A = AUI();

			var refreshURL = '<%= HtmlUtil.escapeJS(PortletURLUtil.getRefreshURL(request, themeDisplay)) %>';

			var data = {};

			A.all('#<portlet:namespace />fm input[type=hidden]').each(
				function(item, index, collection) {
					data[item.attr('name')] = item.attr('value');
				}
			);

			var params = {};

			if (refreshURL.split('?').length > 1) {
				var refreshURLPieces = refreshURL.split('?');

				params = A.QueryString.parse(refreshURLPieces[1]);

				refreshURL = refreshURLPieces[0];
			}

			var marketingEventsContainer = A.one('#<portlet:namespace />marketingEventsContainer');

			marketingEventsContainer.hide();
			marketingEventsContainer.placeAfter(A.Node.create('<div class="loading-animation" />'));

			marketingEventsContainer.remove();

			Liferay.Portlet.addHTML(
				{
					data: A.mix(params, data, true),
					onComplete: function(portlet, portletId) {
						portlet.refreshURL = refreshURL;

						Liferay.fire(
							portlet.portletId + ':portletRefreshed',
							{
								portlet: portlet,
								portletId: portletId
							}
						);
					},
					placeHolder: A.one('#p_p_id<portlet:namespace />'),
					url: refreshURL
				}
			);
		},
		['aui-base', 'querystring']
	);

	AUI().ready(
		'aui-base',
		function() {

			<%
			HttpServletRequest originalRequest = PortalUtil.getOriginalServletRequest(request);

			String marketingEventId = ParamUtil.getString(originalRequest, "marketingEventId");
			%>

			var marketingEventId = '<%= HtmlUtil.escapeJS(marketingEventId) %>';

			if ((marketingEventId != '') && (marketingEventId != 'null')) {
				<portlet:namespace />openPopup(marketingEventId);
			}
		}
	);
</aui:script>

<aui:script use='<%= showSearch ? "aui-base,aui-live-search" : "aui-base" %>'>
	<c:if test="<%= showSearch %>">
		new A.LiveSearch(
			{
				input: '#<portlet:namespace />searchBox',
				nodes: '.osb-portlet-marketing-events .content .marketing-event'
			}
		);
	</c:if>

	A.one('#<portlet:namespace />marketingEventsContainer').delegate(
		'click',
		function(event) {
			var marketingEventId = event.currentTarget.getAttribute('data-marketingEventId');

			<portlet:namespace />openPopup(marketingEventId);
		},
		'.marketing-event'
	);
</aui:script>