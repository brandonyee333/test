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
boolean displayLiferay = SupportUtil.isVersion2LiferayAnnouncementEnabled();
boolean displayNonLiferay = SupportUtil.isVersion2CustomerAnnouncementEnabled();
%>

<c:if test="<%= (liferayIncOrg && displayLiferay) || (!liferayIncOrg && displayNonLiferay) %>">
	<liferay-util:html-top outputKey="osb_portlet_support_banner_lesa_2">
		<div id="supportAnnouncement">
			<div class="announcement-content lesa-2">
				<p>
					<span class="announcement-text light-text">
						<c:choose>
							<c:when test="<%= SupportUtil.isVersion2Enabled(user.getUserId()) %>">
								<liferay-ui:message key="weve-made-some-changes-to-lesa.-please-visit-your-preferences-to-view-these-new-changes" />
							</c:when>
							<c:otherwise>
								<liferay-ui:message key="were-working-on-making-improvements-to-lesa-and-will-be-rolling-out-a-new-redesign-soon.-please-stay-tuned" />
							</c:otherwise>
						</c:choose>
					</span>
				</p>
			</div>
		</div>
	</liferay-util:html-top>
</c:if>