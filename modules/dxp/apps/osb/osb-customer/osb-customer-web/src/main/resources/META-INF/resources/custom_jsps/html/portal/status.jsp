<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/html/portal/init.jsp" %>

<%
int status = ParamUtil.getInteger(request, "status");

if (status > 0) {
	response.setStatus(status);
}

String exception = ParamUtil.getString(request, "exception");

String url = ParamUtil.getString(request, "previousURL");

if (Validator.isNull(url)) {
	url = PortalUtil.getCurrentURL(request);
}

url = themeDisplay.getPortalURL() + url;

boolean noSuchResourceException = false;

for (String key : SessionErrors.keySet(request)) {
	key = key.substring(key.lastIndexOf(StringPool.PERIOD) + 1);

	if (key.startsWith("NoSuch") && key.endsWith("Exception")) {
		noSuchResourceException = true;
	}
}

if (Validator.isNotNull(exception)) {
	exception = exception.substring(exception.lastIndexOf(StringPool.PERIOD) + 1);

	if (exception.startsWith("NoSuch") && exception.endsWith("Exception")) {
		noSuchResourceException = true;
	}
}
%>

<c:choose>
	<c:when test="<%= noSuchResourceException %>">
		<style type="text/css">
			#main-content {
				margin: 20px auto;
				max-width: 1200px;
				min-height: 60vh;
			}

			.error-container {
				display: table;
				padding: 12px 10px 10px;
				width: 100%;
			}

			.error-container .doc-search {
				margin: 30px auto;
			}

			.error-container .doc-search .btn {
				box-sizing: border-box;
				margin-left: 1%;
				width: 28%;
			}

			.error-container .doc-search .doc-search-input {
				background: url('<%= themeDisplay.getPathThemeImages() %>/custom/search_icons.png') -5px -10px no-repeat;
				background-size: 45px;
				border: 1px solid #DFE0E1;
				border-radius: 2px;
				padding: 5px 5px 5px 35px;
				width: 70%;
			}

			.error-container .error-content {
				float: left;
				width: 57%;
			}

			.error-container .error-image {
				float: left;
				width: 35%;
			}

			.error-container .error-url {
				color: #919697;
				word-break: break-all;
			}

			.error-container .heading {
				color: #1B3A6B;
			}

			.error-container .portlet-content {
				border: none;
			}

			.error-container .portlet-topper {
				display: none;
			}

			.error-container .suggested-link {
				box-sizing: border-box;
				float: left;
				padding: 10px 10px 10px 0;
				text-align: left;
				text-decoration: none;
				width: 50%;
			}

			@media all and (max-width: 740px) {
				.error-container .doc-search .btn, .error-container .doc-search .doc-search-input, .error-container .suggested-link {
					width: 100%;
				}

				.error-container .doc-search .btn {
					margin: 10px 0;
				}

				.error-container .error-image, .error-container .portlet-content {
					text-align: center;
				}
			}
		</style>

		<div class="row-fluid" id="main-content">
			<div class="error-container">
				<div class="col-md-5 error-image">
					<img src="<%= themeDisplay.getPathThemeImages() %>/custom/lost_ray_alloy.png" />
				</div>

				<div class="col-md-7 error-content">
					<div>
						<h2 class="heading redesign">
							<liferay-ui:message key="were-sorry-the-page-you-requested-was-not-found" />
						</h2>

						<span class="error-url"><%= HtmlUtil.escape(url) %></span>

						<%
						String portletId = "1_WAR_osbknowledgebaseportlet";

						String portletNamespace = "_" + portletId + "_";

						String keywords = ParamUtil.getString(request, portletNamespace + "keywords");
						%>

						<form action="/documentation/search" class="doc-search" method="get" name="<%= portletNamespace %>searchFm">
							<input name="p_p_id" type="hidden" value="<%= portletId %>" />
							<input name="p_p_lifecycle" type="hidden" value="0" />
							<input name="p_p_state" type="hidden" value="normal" />

							<input class="doc-search-input" name="<%= portletNamespace %>keywords" required type="text" value="<%= HtmlUtil.escapeAttribute(keywords) %>" />

							<input class="btn" type="submit" value="<liferay-ui:message key="search" />">
						</form>

						<div class="suggested-links">
							<a class="suggested-link" href="/documentation/7.0/deploy"><liferay-ui:message key="deploy" /></a>
							<a class="suggested-link" href="/documentation/7.0/admin"><liferay-ui:message key="admin" /></a>
							<a class="suggested-link" href="/documentation/7.0/develop"><liferay-ui:message key="develop" /></a>
							<a class="suggested-link" href="/documentation/search/knowledge-base"><liferay-ui:message key="knowledge-base" /></a>
						</div>
					</div>
				</div>
			</div>

			<div class="separator"><!-- --></div>

			<a href="javascript:history.go(-1);">&laquo; <liferay-ui:message key="back" /></a>
		</div>
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/html/portal/status.portal.jsp" />
	</c:otherwise>
</c:choose>