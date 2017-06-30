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

<%@ include file="/marketplace_apps/init.jsp" %>

<%
long ownerClassNameId = ParamUtil.getLong(request, "ownerClassNameId");
long ownerClassPK = ParamUtil.getLong(request, "ownerClassPK");

long productClassNameId = PortalUtil.getClassNameId(AppEntry.class);

LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

params.put("ownerClassNameId", ownerClassNameId);
params.put("ownerClassPK", ownerClassPK);

List<AssetReceipt> assetReceipts = AssetReceiptLocalServiceUtil.getAssetReceipts(productClassNameId, params, 0, 16, new AssetReceiptCreateDateComparator());
int assetReceiptsCount = AssetReceiptLocalServiceUtil.getAssetReceiptsCount(productClassNameId, params);

String viewAppEntriesURL = ParamUtil.getString(request, "viewAppEntriesURL");
%>

<c:choose>
	<c:when test="<%= assetReceiptsCount <= 0 %>">
		<div class="apps">
			<liferay-ui:message key="no-apps-purchased" />
		</div>
	</c:when>
	<c:otherwise>
		<div class="apps">

			<%
			for (AssetReceipt assetReceipt : assetReceipts) {
				AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(assetReceipt.getProductClassPK());

				long iconImageId = appEntry.getIconImageId();

				String iconURL = StringPool.BLANK;

				if (iconImageId > 0) {
					LiferayPortletURL liferayPortletURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

					liferayPortletURL.setCopyCurrentRenderParameters(false);
					liferayPortletURL.setParameter("assetAttachmentId", String.valueOf(iconImageId), false);
					liferayPortletURL.setResourceID("serveIcon");

					iconURL = liferayPortletURL.toString();
				}
				else {
					iconURL = themeDisplay.getPathThemeImages() + "/custom/icon-marketplace-placeholder-70x70.png";
				}
			%>

				<liferay-portlet:renderURL var="viewAppEntryURL">
					<portlet:param name="mvcPath" value="/marketplace_apps/view_app_entry.jsp" />
					<portlet:param name="assetReceiptId" value="<%= String.valueOf(assetReceipt.getAssetReceiptId()) %>" />
				</liferay-portlet:renderURL>

				<a class="app" data-appEntryTitle="<%= HtmlUtil.escape(appEntry.getTitle()) %>" href="<%= viewAppEntryURL %>"><img class="thumb" src="<%= iconURL %>" /></a>

			<%
			}
			%>

		</div>

		<a class="all-apps" href="<%= HtmlUtil.escapeAttribute(viewAppEntriesURL) %>"><liferay-ui:message arguments="<%= String.valueOf(assetReceiptsCount) %>" key="see-all-x-apps" /></a>
	</c:otherwise>
</c:choose>