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

<liferay-ui:message key="thank-you-for-your-interest-in-liferay-dxp-a-request-to-renew-your-trial-has-been-sent-to-our-sales-team-who-will-contact-you-shortly" />

<div align="right">
	<aui:button onClick='<%= renderResponse.getNamespace() + "closeDialog(0);" %>' value="close" />
</div>