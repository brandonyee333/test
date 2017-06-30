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
long marketingEventId = ParamUtil.getLong(request, "marketingEventId");

MarketingEvent marketingEvent = MarketingEventLocalServiceUtil.getMarketingEvent(marketingEventId);
%>

<div class="jsp-view-marketing-event">
	<div class="marketing-event-header">

		<%
		FileEntry imageFileEntry = marketingEvent.getImageFileEntry();
		%>

		<c:choose>
			<c:when test="<%= marketingEvent.hasVideo() %>">
				<div id="<portlet:namespace />video"><!-- --></div>
			</c:when>
			<c:when test="<%= imageFileEntry != null %>">
				<img class="marketing-event-image" src='<%= themeDisplay.getPathContext() + "/documents/" + imageFileEntry.getRepositoryId() + "/" + imageFileEntry.getFolderId() + "/" + HttpUtil.encodeURL(imageFileEntry.getTitle()) + "/" + imageFileEntry.getUuid() %>' />
			</c:when>
			<c:otherwise>
				<img class="marketing-event-image" src='<%= PortalUtil.getPathContext(request) + "/marketing_events/images/" + TextFormatter.format(MarketingEventConstants.getTypeLabel(marketingEvent.getType()) + ".svg", TextFormatter.N) %>' />
			</c:otherwise>
		</c:choose>
	</div>

	<div class="marketing-event-content">
		<div class="title">
			<c:choose>
				<c:when test="<%= Validator.isNotNull(marketingEvent.getTitleURL()) %>">
					<aui:a href="<%= marketingEvent.getTitleURL() %>" target="_blank"><%= GetterUtil.getString(marketingEvent.getTitle(locale), marketingEvent.getTitle(marketingEvent.getDefaultLanguageId())) %></aui:a>
				</c:when>
				<c:otherwise>
					<%= GetterUtil.getString(marketingEvent.getTitle(locale), marketingEvent.getTitle(marketingEvent.getDefaultLanguageId())) %>
				</c:otherwise>
			</c:choose>
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
					<c:if test="<%= Validator.isNotNull(address.getStreet1()) %>">
						<%= HtmlUtil.escape(address.getStreet1()) %><br />
					</c:if>

					<c:if test="<%= Validator.isNotNull(address.getStreet2()) %>">
						<%= HtmlUtil.escape(address.getStreet2()) %><br />
					</c:if>

					<c:if test="<%= Validator.isNotNull(address.getStreet3()) %>">
						<%= HtmlUtil.escape(address.getStreet3()) %><br />
					</c:if>

					<c:if test="<%= Validator.isNotNull(address.getCity()) %>">

						<%
						StringBundler sb = new StringBundler(5);

						sb.append(HtmlUtil.escape(address.getCity()));

						if (address.getRegionId() > 0) {
							Region region = address.getRegion();

							sb.append(StringPool.COMMA_AND_SPACE);
							sb.append(HtmlUtil.escape(region.getName()));
						}

						String zip = address.getZip();

						if (Validator.isNotNull(zip) && !zip.equals(MarketingEventConstants.ADDRESS_ZIP_NOT_AVAILABLE)) {
							sb.append(StringPool.SPACE);
							sb.append(HtmlUtil.escape(address.getZip()));
						}
						%>

						<%= sb.toString() %><br />
					</c:if>

					<c:if test="<%= address.getCountry() != null %>">
						<%= HtmlUtil.escape(address.getCountry().getName()) %>
					</c:if>
				</c:when>
				<c:when test="<%= marketingEvent.isOnline() %>">
					<liferay-ui:message key="online" />
				</c:when>
			</c:choose>
		</div>

		<div class="host">
			<c:if test="<%= Validator.isNotNull(marketingEvent.getHostedBy()) %>">
				<c:choose>
					<c:when test="<%= Validator.isNotNull(marketingEvent.getHostedByURL()) %>">
						<%= LanguageUtil.format(pageContext, "hosted-by-x", new Object[] {"<a href=\"" + HtmlUtil.escapeHREF(marketingEvent.getHostedByURL()) + "\" target=\"_blank\">" + HtmlUtil.escape(marketingEvent.getHostedBy()) + "</a>"}) %>
					</c:when>
					<c:otherwise>
						<%= LanguageUtil.format(pageContext, "hosted-by-x", new Object[] {HtmlUtil.escape(marketingEvent.getHostedBy())}) %>
					</c:otherwise>
				</c:choose>
			</c:if>
		</div>

		<div class="summary">
			<%= GetterUtil.getString(marketingEvent.getSummary(locale), marketingEvent.getSummary(marketingEvent.getDefaultLanguageId())) %>
		</div>
	</div>

	<div class="marketing-event-footer">
		<div class="downloads">

			<%
			FileEntry slidesFileEntry = marketingEvent.getSlidesFileEntry();
			%>

			<c:if test="<%= marketingEvent.hasVideo() || (slidesFileEntry != null) %>">
				<liferay-ui:message key="download" />:
			</c:if>

			<c:if test="<%= marketingEvent.hasVideo() %>">
				<aui:a href='<%= marketingEvent.getVideoURL(".mp4") %>' label="mp4" target="_blank" /> |

				<aui:a href='<%= marketingEvent.getVideoURL(".ogv") %>' label="ogv" target="_blank" />
			</c:if>

			<c:if test="<%= slidesFileEntry != null %>">
				<c:if test="<%= marketingEvent.hasVideo() %>">
					|
				</c:if>

				<aui:a href='<%= themeDisplay.getPathMain() + "/document_library/get_file?groupId=" + slidesFileEntry.getRepositoryId() + "&folderId=" + slidesFileEntry.getFolderId() + "&title=" + HttpUtil.encodeURL(slidesFileEntry.getTitle()) + "&uuid=" + slidesFileEntry.getUuid() %>' label="slides" target="_blank" />
			</c:if>

			<c:if test="<%= marketingEvent.hasVideo() || (slidesFileEntry != null) %>">
				<div class="share-icon">
					<img src="<%= PortalUtil.getPathContext(request) %>/marketing_events/images/share.svg" />
				</div>

				<div class="share-link aui-helper-hidden">
					<input onClick="this.select();" type="text" value="<%= PortalUtil.getPortalURL(request) %>/events?marketingEventId=<%= marketingEventId %>">
				</div>
			</c:if>
		</div>

		<%
		Date startDate = marketingEvent.getStartDate();
		%>

		<c:if test="<%= Validator.isNotNull(marketingEvent.getRegistrationURL()) && startDate.after(DateUtil.newDate()) %>">
			<aui:a cssClass="btn style-a" href="<%= marketingEvent.getRegistrationURL() %>" label="<%= StringUtil.toUpperCase(LanguageUtil.get(pageContext, MarketingEventConstants.getRegistrationTypeButtonLabel(marketingEvent.getRegistrationType()))) %>" target="_blank" />
		</c:if>
	</div>
</div>

<aui:script use="aui-base">
	var shareIcon = A.one('.share-icon');
	var shareLink = A.one('.share-link');

	if (shareIcon && shareLink) {
		shareIcon.on(
			'click',
			function(event) {
				shareLink.toggleClass('aui-helper-hidden');
			}
		);
	}
</aui:script>

<c:if test="<%= marketingEvent.hasVideo() %>">
	<aui:script use="aui-base,aui-video">
		new A.Video(
			{
				contentBox: '#<portlet:namespace />video',
				cssClass: 'video',
				ogvUrl: '<%= marketingEvent.getVideoURL(".ogv") %>',
				poster: '<%= marketingEvent.getVideoURL(".jpg") %>',
				url: '<%= marketingEvent.getVideoURL(".mp4") %>'
			}
		).render();
	</aui:script>
</c:if>