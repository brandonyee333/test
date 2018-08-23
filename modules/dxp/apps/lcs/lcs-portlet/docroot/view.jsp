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
String lcsPage = ParamUtil.getString(request, "lcsPage", "connection");

Set<LCSAlert> lcsClusterEntryTokenAlerts = LCSUtil.getLCSClusterEntryTokenAlerts();
%>

<section class="content">
	<c:choose>
		<c:when test="<%= !lcsClusterEntryTokenAlerts.contains(LCSAlert.SUCCESS_CONNECTION_TO_LCS_VALID) %>">
			<div class="container-fluid-1280">
				<%@ include file="/info.jspf" %>
			</div>
		</c:when>
		<c:otherwise>
			<aui:nav-bar markupView="lexicon">
				<aui:nav cssClass="navbar-nav">
					<liferay-portlet:renderURL var="connectionURL">
						<liferay-portlet:param name="lcsPage" value="connection" />
					</liferay-portlet:renderURL>

					<aui:nav-item href="<%= connectionURL %>" label="connection" selected='<%= lcsPage.equals("connection") %>' />

					<liferay-portlet:renderURL var="infoURL">
						<liferay-portlet:param name="lcsPage" value="info" />
					</liferay-portlet:renderURL>

					<aui:nav-item href="<%= infoURL %>" label="info" selected='<%= lcsPage.equals("info") %>' />
				</aui:nav>
			</aui:nav-bar>

			<div class="container-fluid-1280">
				<c:choose>
					<c:when test='<%= lcsPage.equals("connection") %>'>
						<%@ include file="/connection.jspf" %>
					</c:when>
					<c:when test='<%= lcsPage.equals("info") %>'>
						<%@ include file="/info.jspf" %>
					</c:when>
				</c:choose>
			</div>
		</c:otherwise>
	</c:choose>

	<c:if test="<%= !lcsClusterEntryTokenAlerts.isEmpty() %>">
		<div class="container-fluid-1280">

			<%
			for (LCSAlert lcsAlert : lcsClusterEntryTokenAlerts) {
				if ("success".equals(lcsAlert.getType())) {
					continue;
				}
			%>

				<div class="<%= lcsAlert.getCSSClass() %>">
					<c:choose>
						<c:when test="<%= lcsAlert == LCSAlert.WARNING_LCS_PORTLET_NEW_VERSION_AVAILABLE %>">
							<liferay-ui:message arguments="<%= PortletPropsValues.LRDCOM_LCS_CLIENT_DOWNLOAD_URL %>" key="<%= lcsAlert.getLabel() %>" />
						</c:when>
						<c:otherwise>
							<liferay-ui:message key="<%= lcsAlert.getLabel() %>" />
						</c:otherwise>
					</c:choose>
				</div>

			<%
			}
			%>

		</div>
	</c:if>
</section>

<footer class="footer">
	<div class="container-fluid-1280">
		<div class="footer-note">
			<liferay-ui:message arguments="<%= PortletPropsValues.LCS_CLIENT_VERSION %>" key="liferay-connected-services-client-x" />
		</div>

		<div class="footer-note">
			<liferay-ui:message arguments="<%= PortletPropsValues.LRDCOM_SUPPORT_URL %>" key="if-you-have-a-liferay-enterprise-subscription-and-you-have-questions-or-issues-please-open-a-ticket-in-lesa-under-the-liferay-connected-services-component" />
		</div>

		<div class="footer-note">
			<liferay-ui:message arguments="<%= PortletPropsValues.LRDCOM_SALES_EMAIL_ADDRESS %>" key="if-you-do-not-have-an-active-enterprise-subscription-please-contact-your-account-executive-or-x" />
		</div>
	</div>
</footer>