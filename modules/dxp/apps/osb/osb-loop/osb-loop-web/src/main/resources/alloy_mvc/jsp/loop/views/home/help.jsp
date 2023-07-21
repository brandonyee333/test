<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/alloy_mvc/jsp/loop/views/init.jsp" %>

<%@ include file="/alloy_mvc/jsp/loop/views/toolbar.jspf" %>

<div id="${renderResponse.namespace}loopSurfaceMain">
	<div id="${renderResponse.namespace}loopSurfaceMain-default">
		<div class="loop-help">
			<div class="card-container">
				<div class="card-header">
					<span class="header-text">
						<liferay-ui:message key="help" />
					</span>
				</div>

				<div class="card-content">
					<c:set var="helpPage">
						<liferay-journal:journal-article
							articleId="${articleId}"
							groupId="${themeDisplay.siteGroupId}"
						/>
					</c:set>

					<c:choose>
						<c:when test="${empty helpPage}">
							<liferay-ui:message key="no-help-page-found" />
						</c:when>
						<c:otherwise>
							${helpPage}
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>

		<aui:script>
			window.RC.actions.setPageTitle('${LanguageUtil.get(request, "help")}');
		</aui:script>
	</div>
</div>