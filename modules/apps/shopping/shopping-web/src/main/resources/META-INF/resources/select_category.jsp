<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ShoppingCategory category = (ShoppingCategory)request.getAttribute(WebKeys.SHOPPING_CATEGORY);

long categoryId = BeanParamUtil.getLong(category, request, "categoryId", ShoppingCategoryConstants.DEFAULT_PARENT_CATEGORY_ID);

String eventName = ParamUtil.getString(request, "eventName", liferayPortletResponse.getNamespace() + "selectCategory");
%>

<aui:form method="post" name="fm">
	<c:if test="<%= category != null %>">
		<div class="breadcrumbs">
			<%= ShoppingUtil.getBreadcrumbs(category, renderRequest, renderResponse) %>
		</div>
	</c:if>

	<%
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("mvcRenderCommandName", "/shopping/select_category");
	portletURL.setParameter("categoryId", String.valueOf(categoryId));
	%>

	<liferay-ui:search-container
		headerNames="category,num-of-categories,num-of-items, ,"
		iteratorURL="<%= portletURL %>"
		total="<%= ShoppingCategoryServiceUtil.getCategoriesCount(scopeGroupId, categoryId) %>"
	>
		<liferay-ui:search-container-results
			results="<%= ShoppingCategoryServiceUtil.getCategories(scopeGroupId, categoryId, searchContainer.getStart(), searchContainer.getEnd()) %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.shopping.model.ShoppingCategory"
			escapedModel="<%= true %>"
			keyProperty="categoryId"
			modelVar="curCategory"
		>
			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/shopping/select_category" />
				<portlet:param name="categoryId" value="<%= String.valueOf(curCategory.getCategoryId()) %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="category"
			>
				<%= curCategory.getName() %>

				<c:if test="<%= Validator.isNotNull(curCategory.getDescription()) %>">
					<br />

					<%= curCategory.getDescription() %>
				</c:if>
			</liferay-ui:search-container-column-text>

			<%
			List subcategoryIds = new ArrayList();

			subcategoryIds.add(Long.valueOf(curCategory.getCategoryId()));

			ShoppingCategoryServiceUtil.getSubcategoryIds(subcategoryIds, scopeGroupId, curCategory.getCategoryId());

			int categoriesCount = subcategoryIds.size() - 1;
			int itemsCount = ShoppingItemServiceUtil.getCategoriesItemsCount(scopeGroupId, subcategoryIds);
			%>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="num-of-categories"
				value="<%= String.valueOf(categoriesCount) %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="num-of-items"
				value="<%= String.valueOf(itemsCount) %>"
			/>

			<liferay-ui:search-container-column-text>

				<%
				Map<String, Object> data = new HashMap<String, Object>();

				data.put("categoryId", curCategory.getCategoryId());
				data.put("name", curCategory.getName());
				%>

				<aui:button cssClass="selector-button" data="<%= data %>" value="choose" />
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</aui:form>

<aui:script>
	Liferay.Util.selectEntityHandler('#<portlet:namespace />fm', '<%= HtmlUtil.escapeJS(eventName) %>');
</aui:script>