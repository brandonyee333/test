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

<%@ include file="/support/init.jsp" %>

<%
PortletPreferences preferences = SupportUtil.getPortletPreferences();

long announcementDisplayDate = GetterUtil.getLong(preferences.getValue("announcementDisplayDate", null));
long announcementExpirationDate = GetterUtil.getLong(preferences.getValue("announcementExpirationDate", null));

long now = System.currentTimeMillis();
%>

<c:if test="<%= (now >= announcementDisplayDate) && (now <= announcementExpirationDate) %>">

	<%
	String announcementTitle = SupportUtil.getPreferenceValue(preferences, locale, "announcementTitle");
	String announcementContent = SupportUtil.getPreferenceValue(preferences, locale, "announcementContent");
	%>

	<liferay-util:html-top outputKey="osb_portlet_support_banner">
		<div id="supportAnnouncement">
			<div class="announcement-content">
				<p>
					<span class="announcement-text light-text">
						<%= announcementTitle %>
					</span>
					<span class="btn pop-up style-b" data-pop-display="#moreInfo">
						<liferay-ui:message key="learn-more" />
					</span>
				</p>
			</div>
		</div>

		<div class="aui-helper-hidden page-pop-up" data-overlay="true" id="moreInfo">
			<div id="closePopUp">&nbsp;</div>

			<div class="pop-up-content">
				<div>
					<%= announcementContent %>
				</div>
			</div>
		</div>

		<script>
			AUI().ready(
				'aui-overlay-mask',
				'node',
				function(A) {
					var centerOnPage = function(node) {
						var WIN = A.getWin();

						var winHeight = WIN.get('innerHeight');

						if (winHeight == undefined) {
							winHeight = document.documentElement.clientHeight;
						}

						var winWidth = WIN.get('innerWidth');

						if (winWidth == undefined) {
							winWidth = document.documentElement.clientWidth;
						}

						var nodeHeight = node.get('clientHeight');
						var nodeWidth = node.get('clientWidth');

						var xCenter = (winWidth / 2) - (nodeWidth / 2);
						var yCenter = (winHeight / 2) - (nodeHeight / 2);

						node.setStyle('left', xCenter);
						node.setStyle('top', yCenter / 2);
					};

					var createOverlayMask = function() {
						var bindUI = function() {
							var overlayMask = A.one('.aui-overlaymask');

							if (overlayMask) {
								overlayMask.on(
									'click',
									function() {
										overlayMask.remove(true);
									}
								);
							}
						};

						var init = function() {
							var overlay = new A.OverlayMask().render();

							overlay.set('z-index', 20);
							overlay.show();

							bindUI();
						};

						return init();
					};

					var displayPopUp = function(node) {
						node.removeClass('aui-helper-hidden');

						centerOnPage(node);

						var popUpContent = node.one('.pop-up-content');

						if (node.getAttribute('data-overlay')) {
							createOverlayMask();
						}

						popUpContent.on(
							'clickoutside',
							function(event) {
								var overlayMask = A.one('.aui-overlaymask');

								if (overlayMask) {
									overlayMask.remove();
								}

								node.addClass('aui-helper-hidden');

								popUpContent.detach('clickoutside');
							}
						);
					};

					var iOS = /iPad/i.test(navigator.userAgent) || /iPhone/i.test(navigator.userAgent);
					var mouseEvent = 'click';

					if (iOS) {
						mouseEvent = 'mousemove';
					}

					A.all('.pop-up').on(
						mouseEvent,
						function(event) {
							event.stopPropagation();

							var popDisplay = event.currentTarget.getAttribute("data-pop-display");

							var popUp = A.one(popDisplay);

							displayPopUp(popUp);
						}
					);
				}
			);
		</script>
	</liferay-util:html-top>
</c:if>