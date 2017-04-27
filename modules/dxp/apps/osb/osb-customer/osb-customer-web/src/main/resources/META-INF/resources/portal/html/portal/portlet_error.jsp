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

<%@ include file="/html/portal/init.jsp" %>

<style type="text/css">
	.portlet .portlet-content {
		border: none;
		position: relative;
	}

	.portlet-error {
		width: 100%;
	}

	.portlet-error h1 {
		color: #1C75B9;
		font-size: 2.5em;
		padding-bottom: 10px;
	}

	.portlet-error p {
		color: #41444F;
		font-size: 1.14em;
		padding-bottom: 10px;
	}

	.portlet-error .comic-wrapper {
		display: inline-block;
		width: 50%;
	}

	.portlet-error .comic-wrapper img {
		float: right;

		<c:if test="<%= themeDisplay.isStateMaximized() %>">
			width: 35%;
		</c:if>
	}

	.portlet-error .message-wrapper {
		display: inline-block;
		width: 35%;
	}

	.portlet-error .message-wrapper .btn {
		border-color: #1C75B9;
		color: #1C75B9;
	}

	.portlet-error .message-wrapper .btn:hover {
		background: none repeat scroll 0 0 #1C75B9;
		color: #FFF;
	}

	.portlet-error .message-wrapper .message {
		position: absolute;
		top: 35%;
		width: 100%;
	}

	.portlet-topper {
		display: none;
	}
</style>

<div class="portlet-error">
	<div class="comic-wrapper">
		<img src="<%= themeDisplay.getPathThemeImages() %>/custom/lost_ray_alloy.png" />
	</div>

	<div class="message-wrapper">
		<div class="message">
			<h1>
				<img src="<%= themeDisplay.getPathThemeImages() %>/custom/exclamation.png" />

				<liferay-ui:message key="oops" />
			</h1>

			<p>
				<liferay-ui:message key="portlet-temporarily-unavailable" />
			</p>

			<a class="btn" href="javascript:history.go(-1);">&lt; <liferay-ui:message key="back" /></a>
		</div>
	</div>
</div>