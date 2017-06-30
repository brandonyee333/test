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

<div class="heading-msg portlet-msg-error">
	<span class="error-icon"></span>

	<liferay-ui:message key="you-do-not-have-access-to-this-survey" />
</div>

<div class="survey-message">
	<p>
		<liferay-ui:message key="we-apologize-for-the-inconvenience-but-you-do-not-have-access-to-this-survey" />
	</p>

	<p>
		<liferay-ui:message arguments='<%= "mailto:training@liferay.com" %>' key="contact-us-at-training" />
	</p>
</div>