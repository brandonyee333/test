<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />

	<div class="portlet-configuration-body-content">
		<div class="container-fluid-1280">
			<div class="alert alert-info">
				<liferay-ui:message key="define-the-behavior-of-this-search" />
			</div>

			<aui:fieldset>
				<aui:input label="only-show-results-for-web-content-listed-in-a-web-content-display-portlet" name="preferences--showListed--" type="checkbox" value="<%= journalContentSearchPortletInstanceConfiguration.showListed() %>" />

				<aui:input name="preferences--enableHighlighting--" type="checkbox" value="<%= journalContentSearchPortletInstanceConfiguration.enableHighlighting() %>" />

				<div class="<%= !journalContentSearchPortletInstanceConfiguration.showListed() ? StringPool.BLANK : " hide" %>" id="<portlet:namespace />webContentDisplay">
					<aui:input cssClass="lfr-input-text-container" name="preferences--targetPortletId--" value="<%= journalContentSearchPortletInstanceConfiguration.targetPortletId() %>" />
				</div>
			</aui:fieldset>
		</div>
	</div>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />
	</aui:button-row>
</aui:form>

<aui:script>
	Liferay.Util.toggleBoxes('<portlet:namespace />showListed', '<portlet:namespace />webContentDisplay', true);
</aui:script>