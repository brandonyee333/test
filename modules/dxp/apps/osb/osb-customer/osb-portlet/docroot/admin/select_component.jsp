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

<%@ include file="/init.jsp" %>

<%
PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/select_component.jsp");

List<ListType> componentTypes = ListUtil.copy(ListTypeServiceUtil.getListTypes(TicketEntryConstants.LIST_TYPE_COMPONENT));

Iterator<ListType> itr = componentTypes.iterator();

long[] componentsDeprecated = Arrays.stream(TicketEntryConstants.COMPONENTS_DEPRECATED).asLongStream().toArray();

while (itr.hasNext()) {
	ListType componentType = itr.next();

	if (ArrayUtil.contains(componentsDeprecated, componentType.getListTypeId())) {
		itr.remove();
	}
}

String selectAll = "javascript:";
%>

<aui:form method="post">
	<liferay-ui:tabs
		names="components"
	/>

	<liferay-ui:search-container
		headerNames="component"
		iteratorURL="<%= portletURL %>"
		total="<%= componentTypes.size() %>"
	>
		<liferay-ui:search-container-results
			results="<%= componentTypes %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.model.ListType"
			modelVar="componentType"
		>

			<%
			StringBuilder sb = new StringBuilder();

			sb.append("opener.");
			sb.append(renderResponse.getNamespace());
			sb.append("selectRow('components', '");
			sb.append(componentType.getListTypeId());
			sb.append("', '");
			sb.append(renderResponse.getNamespace());
			sb.append("componentSearchContainer', ['");
			sb.append(LanguageUtil.get(request, componentType.getName()));
			sb.append("']);");

			selectAll += sb.toString();

			String rowHREF = "javascript:" + sb.toString() + "window.close();";
			%>

			<liferay-ui:search-container-column-text
				href="<%= rowHREF %>"
				name="component"
			>
				<%= LanguageUtil.get(request, componentType.getName()) %>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<aui:button onClick='<%= selectAll + "window.close();" %>' value="select-all" />

		<br /><br />

		<liferay-ui:search-iterator
			markupView="lexicon"
			paginate="<%= false %>"
		/>
	</liferay-ui:search-container>
</aui:form>