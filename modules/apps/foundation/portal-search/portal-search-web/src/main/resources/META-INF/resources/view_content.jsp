<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
long assetEntryId = ParamUtil.getLong(request, "assetEntryId");

String type = ParamUtil.getString(request, "type");

AssetRendererFactory<?> assetRendererFactory = AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByType(type);

AssetEntry assetEntry = assetRendererFactory.getAssetEntry(assetEntryId);

AssetRenderer<?> assetRenderer = assetEntry.getAssetRenderer();
%>

<c:if test="<%= (assetEntry != null) && assetEntry.isVisible() && assetRenderer.hasViewPermission(permissionChecker) %>">
	<liferay-ui:header
		localizeTitle="<%= false %>"
		title="<%= assetRenderer.getTitle(locale) %>"
	/>

	<c:if test="<%= assetRenderer.hasEditPermission(permissionChecker) %>">
		<div class="asset-actions lfr-meta-actions">

			<%
			PortletURL redirectURL = renderResponse.createRenderURL();

			redirectURL.setParameter("mvcPath", "/edit_content_redirect.jsp");

			PortletURL editPortletURL = assetRenderer.getURLEdit((LiferayPortletRequest)renderRequest, (LiferayPortletResponse)renderResponse, LiferayWindowState.POP_UP, redirectURL);

			Map<String, Object> data = new HashMap<String, Object>();

			data.put("destroyOnHide", true);
			data.put("id", HtmlUtil.escape(portletDisplay.getNamespace()) + "editAsset");
			data.put("title", LanguageUtil.format(request, "edit-x", HtmlUtil.escape(assetRenderer.getTitle(locale)), false));
			%>

			<liferay-ui:icon
				cssClass="visible-interaction"
				data="<%= data %>"
				icon="pencil"
				label="<%= false %>"
				markupView="lexicon"
				message='<%= LanguageUtil.format(request, "edit-x-x", new Object[] {"hide-accessible", HtmlUtil.escape(assetRenderer.getTitle(locale))}, false) %>'
				method="get"
				url="<%= editPortletURL.toString() %>"
				useDialog="<%= true %>"
			/>
		</div>
	</c:if>

	<liferay-ui:asset-display
		assetEntry="<%= assetEntry %>"
		assetRenderer="<%= assetRenderer %>"
		assetRendererFactory="<%= assetRendererFactory %>"
	/>
</c:if>