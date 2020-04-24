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