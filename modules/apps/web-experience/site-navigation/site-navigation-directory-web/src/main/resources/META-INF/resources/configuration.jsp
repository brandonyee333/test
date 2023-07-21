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
			<aui:row>
				<aui:col width="<%= 50 %>">
					<aui:fieldset column="<%= true %>">
						<aui:select name="preferences--sites--" value="<%= sitesDirectoryDisplayContext.getSites() %>">
							<aui:option label="<%= SitesDirectoryTag.SITES_TOP_LEVEL %>" />
							<aui:option label="<%= SitesDirectoryTag.SITES_PARENT_LEVEL %>" />
							<aui:option label="<%= SitesDirectoryTag.SITES_SIBLINGS %>" />
							<aui:option label="<%= SitesDirectoryTag.SITES_CHILDREN %>" />
						</aui:select>

						<aui:select name="preferences--displayStyle--" value="<%= sitesDirectoryDisplayContext.getDisplayStyle() %>">
							<aui:option label="icon" />
							<aui:option label="descriptive" />
							<aui:option label="list" />
							<aui:option label="list-hierarchy" />
						</aui:select>
					</aui:fieldset>
				</aui:col>

				<aui:col width="<%= 50 %>">
					<liferay-portlet:preview
						portletName="<%= portletResource %>"
						queryString="struts_action=/sites_directory/view"
						showBorders="<%= true %>"
					/>
				</aui:col>
			</aui:row>
		</div>
	</div>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />
	</aui:button-row>
</aui:form>

<aui:script sandbox="<%= true %>">
	var form = $('#<portlet:namespace />fm');

	var selectDisplayStyle = form.fm('displayStyle');
	var selectSites = form.fm('sites');

	var curPortletBoundaryId = '#p_p_id_<%= HtmlUtil.escapeJS(portletResource) %>_';

	form.on(
		'change',
		'select',
		function() {
			var data = Liferay.Util.ns(
				'_<%= HtmlUtil.escapeJS(portletResource) %>_',
				{
					displayStyle: selectDisplayStyle.val(),
					sites: selectSites.val()
				}
			);

			Liferay.Portlet.refresh(curPortletBoundaryId, data);
		}
	);
</aui:script>