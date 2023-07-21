<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/init.jsp" %>

<%
boolean addDisplayPageParameter = GetterUtil.getBoolean(request.getAttribute("liferay-ui:asset-add-button:addDisplayPageParameter"));
long[] allAssetCategoryIds = GetterUtil.getLongValues(request.getAttribute("liferay-ui:asset-add-button:allAssetCategoryIds"), null);
String[] allAssetTagNames = GetterUtil.getStringValues(request.getAttribute("liferay-ui:asset-add-button:allAssetTagNames"), (String[])null);
long[] classNameIds = GetterUtil.getLongValues(request.getAttribute("liferay-ui:asset-add-button:classNameIds"));
long[] classTypeIds = GetterUtil.getLongValues(request.getAttribute("liferay-ui:asset-add-button:classTypeIds"));
long[] groupIds = GetterUtil.getLongValues(request.getAttribute("liferay-ui:asset-add-button:groupIds"));
String redirect = (String)request.getAttribute("liferay-ui:asset-add-button:redirect");
boolean useDialog = GetterUtil.getBoolean(request.getAttribute("liferay-ui:asset-add-button:useDialog"), true);

boolean hasAddPortletURLs = false;

for (long groupId : groupIds) {
	Map<String, PortletURL> addPortletURLs = AssetUtil.getAddPortletURLs((LiferayPortletRequest)portletRequest, (LiferayPortletResponse)portletResponse, groupId, classNameIds, classTypeIds, allAssetCategoryIds, allAssetTagNames, redirect);

	if ((addPortletURLs != null) && !addPortletURLs.isEmpty()) {
		hasAddPortletURLs = true;
	}
%>

	<c:if test="<%= hasAddPortletURLs %>">
		<aui:nav>
			<c:choose>
				<c:when test="<%= addPortletURLs.size() == 1 %>">

					<%
					Set<Map.Entry<String, PortletURL>> addPortletURLsSet = addPortletURLs.entrySet();

					Iterator<Map.Entry<String, PortletURL>> iterator = addPortletURLsSet.iterator();

					Map.Entry<String, PortletURL> entry = iterator.next();

					AssetRendererFactory<?> assetRendererFactory = AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(AssetUtil.getClassName(entry.getKey()));

					String message = AssetUtil.getClassNameMessage(entry.getKey(), locale);

					long curGroupId = groupId;

					Group group = GroupLocalServiceUtil.fetchGroup(groupId);

					if (!group.isStagedPortlet(assetRendererFactory.getPortletId()) && !group.isStagedRemotely()) {
						curGroupId = group.getLiveGroupId();
					}
					%>

					<aui:nav-item href="<%= _getURL(curGroupId, plid, entry.getValue(), assetRendererFactory.getPortletId(), message, addDisplayPageParameter, layout, pageContext, portletResponse, useDialog) %>" label='<%= LanguageUtil.format(request, (groupIds.length == 1) ? "add-x" : "add-x-in-x", new Object[] {HtmlUtil.escape(message), HtmlUtil.escape((GroupLocalServiceUtil.getGroup(groupId)).getDescriptiveName(locale))}, false) %>' />
				</c:when>
				<c:otherwise>
					<aui:nav-item dropdown="<%= true %>" label='<%= LanguageUtil.format(request, (groupIds.length == 1) ? "add-new" : "add-new-in-x", HtmlUtil.escape((GroupLocalServiceUtil.getGroup(groupId)).getDescriptiveName(locale)), false) %>'>

						<%
						for (Map.Entry<String, PortletURL> entry : addPortletURLs.entrySet()) {
							AssetRendererFactory<?> assetRendererFactory = AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(AssetUtil.getClassName(entry.getKey()));

							String message = AssetUtil.getClassNameMessage(entry.getKey(), locale);

							long curGroupId = groupId;

							Group group = GroupLocalServiceUtil.fetchGroup(groupId);

							if (!group.isStagedPortlet(assetRendererFactory.getPortletId()) && !group.isStagedRemotely()) {
								curGroupId = group.getLiveGroupId();
							}
						%>

							<aui:nav-item href="<%= _getURL(curGroupId, plid, entry.getValue(), assetRendererFactory.getPortletId(), message, addDisplayPageParameter, layout, pageContext, portletResponse, useDialog) %>" label="<%= HtmlUtil.escape(message) %>" />

						<%
						}
						%>

					</aui:nav-item>
				</c:otherwise>
			</c:choose>
		</aui:nav>
	</c:if>

<%
}

request.setAttribute("liferay-ui:asset-add-button:hasAddPortletURLs", hasAddPortletURLs);
%>

<%!
private String _getURL(long groupId, long plid, PortletURL addPortletURL, String portletId, String message, boolean addDisplayPageParameter, Layout layout, PageContext pageContext, PortletResponse portletResponse, boolean useDialog) {
	String addPortletURLString = AssetUtil.getAddURLPopUp(groupId, plid, addPortletURL, portletId, addDisplayPageParameter, layout);

	if (useDialog) {
		return "javascript:Liferay.Util.openWindow({dialog: {destroyOnHide: true}, dialogIframe: {bodyCssClass: 'dialog-with-footer'}, id: '" + portletResponse.getNamespace() + "editAsset', title: '" + HtmlUtil.escapeJS(LanguageUtil.format((HttpServletRequest) pageContext.getRequest(), "new-x", HtmlUtil.escape(message), false)) + "', uri: '" + HtmlUtil.escapeJS(addPortletURLString) + "'});";
	}

	return addPortletURLString;
}
%>