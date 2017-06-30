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

<%@ include file="/marketplace/init.jsp" %>

<liferay-ui:header
	showBackURL="<%= false %>"
	title="thank-you"
/>

<p>
	<liferay-ui:message arguments='<%= "mailto:sales@liferay.com" %>' key="a-sales-representative-will-contact-you-shortly-if-you-are-not-contacted-in-a-few-days-please-contact-sales" />
</p>

<p style="padding-top: 10px; text-align: right;">
	<liferay-portlet:renderURL var="marketplaceURL">
		<portlet:param name="<%= mvcPathParam %>" value="/marketplace/view.jsp" />
		<portlet:param name="assetCategoryId" value="0" />
	</liferay-portlet:renderURL>

	<a href="<%= marketplaceURL %>"><liferay-ui:message key="return-to-marketplace" /> &raquo;</a>
</p>