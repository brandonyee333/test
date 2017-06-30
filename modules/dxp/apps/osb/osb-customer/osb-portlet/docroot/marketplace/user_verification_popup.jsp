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

<div class="marketplace user-verification-popup">
	<p>
		<liferay-ui:message key="please-verify-the-email-address-associated-with-your-liferay-account" />
	</p>

	<div>
		<input class="send-verification-email-button" onClick="<portlet:namespace />sendEmailAddressVerification()" type="button" value="<liferay-ui:message key="send-verification-email" />" />

		<input class="not-right-now-button" onClick="<portlet:namespace />removeUserVerificationPopup()" type="button" value="<liferay-ui:message key="not-right-now" />" />
	</div>
</div>