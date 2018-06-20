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

<span class="txt-b"><liferay-ui:message key="download-your-portal-license-key" /></span>

<div class="osb-portlet-activation">
	<div class="aui-helper-clearfix activation-key-container">
		<div class="aui-w25 content-column">
			<div class="activation-column content-column-content">
				<select id="<portlet:namespace />activationKeyProject" name="<portlet:namespace />activationProject"></select>
			</div>
		</div>

		<div class="aui-w25 content-column">
			<div class="activation-column content-column-content">
				<select id="<portlet:namespace />activationKeyVersion" name="<portlet:namespace />activationKeyVersion"></select>
			</div>
		</div>

		<div class="aui-w25 content-column">
			<div class="activation-column content-column-content">
				<select id="<portlet:namespace />activationKeyOrder" name="<portlet:namespace />activationKeyOrder"></select>
			</div>
		</div>

		<div class="aui-w25 content-column">
			<div class="activation-column content-column-content">
				<button id="<portlet:namespace />activationKeyDownloadButton" type="button"><liferay-ui:message key="download-license" /></button>
			</div>
		</div>
	</div>
</div>