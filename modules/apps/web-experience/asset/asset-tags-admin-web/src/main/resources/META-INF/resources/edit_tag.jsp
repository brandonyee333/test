<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

if (Validator.isNull(redirect)) {
	PortletURL portletURL = renderResponse.createRenderURL();

	redirect = portletURL.toString();
}

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle(assetTagsDisplayContext.getAssetTitle());
%>

<portlet:actionURL name="editTag" var="editTagURL">
	<portlet:param name="mvcPath" value="/edit_tag.jsp" />
</portlet:actionURL>

<aui:form action="<%= editTagURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<liferay-ui:error exception="<%= AssetTagException.class %>">

		<%
		AssetTagException ate = (AssetTagException)errorException;
		%>

		<c:if test="<%= ate.getType() == AssetTagException.INVALID_CHARACTER %>">
			<liferay-ui:message arguments='<%= new String[] {"tag-name", StringUtil.merge(AssetUtil.INVALID_CHARACTERS, StringPool.SPACE)} %>' key="the-x-cannot-contain-the-following-invalid-characters-x" />
		</c:if>
	</liferay-ui:error>

	<liferay-ui:error exception="<%= DuplicateTagException.class %>" message="a-tag-with-that-name-already-exists" />

	<aui:model-context bean="<%= assetTagsDisplayContext.getTag() %>" model="<%= AssetTag.class %>" />

	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<aui:input name="tagId" type="hidden" value="<%= assetTagsDisplayContext.getTagId() %>" />

			<aui:input autoFocus="<%= true %>" cssClass="tag-name" name="name" placeholder="name" />
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />

		<aui:button cssClass="btn-lg" href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>