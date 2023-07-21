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
	redirect = trashDisplayContext.getContainerModelRedirectURL();
}

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

TrashRenderer trashRenderer = null;

if ((row != null) && (row.getObject() instanceof TrashRenderer)) {
	trashRenderer = (TrashRenderer)row.getObject();
}
else {
	trashRenderer = (TrashRenderer)request.getAttribute(TrashWebKeys.TRASH_RENDERER);
}

TrashHandler trashHandler = TrashHandlerRegistryUtil.getTrashHandler(trashRenderer.getClassName());
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<c:if test="<%= trashHandler.isMovable() %>">
		<portlet:renderURL var="moveURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="mvcPath" value="/view_container_model.jsp" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="classNameId" value="<%= String.valueOf(PortalUtil.getClassNameId(trashRenderer.getClassName())) %>" />
			<portlet:param name="classPK" value="<%= String.valueOf(trashRenderer.getClassPK()) %>" />
			<portlet:param name="containerModelClassNameId" value="<%= String.valueOf(PortalUtil.getClassNameId(trashHandler.getContainerModelClassName(trashRenderer.getClassPK()))) %>" />
		</portlet:renderURL>

		<%
		String taglibOnClick = renderResponse.getNamespace() + "restoreDialog('" + moveURL + "')";
		%>

		<liferay-ui:icon
			message="restore"
			onClick="<%= taglibOnClick %>"
			url="javascript:;"
		/>
	</c:if>

	<c:if test="<%= trashHandler.isDeletable() %>">
		<portlet:actionURL name="deleteEntries" var="deleteEntryURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="className" value="<%= trashRenderer.getClassName() %>" />
			<portlet:param name="classPK" value="<%= String.valueOf(trashRenderer.getClassPK()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteEntryURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>