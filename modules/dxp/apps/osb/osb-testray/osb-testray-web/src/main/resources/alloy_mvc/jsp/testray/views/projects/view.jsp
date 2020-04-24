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

<%@ include file="/alloy_mvc/jsp/testray/views/init.jsp" %>

<%@ include file="/alloy_mvc/jsp/testray/views/start.jspf" %>

<liferay-util:include page="/alloy_mvc/jsp/testray/views/projects/header.jsp" servletContext="<%= application %>">
	<liferay-util:param name="tab" value="overview" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<div class="testray-card testray-card-medium">
	<aui:fieldset>
		<aui:field-wrapper name="description">
			${fn:escapeXml(testrayProject.description)}
		</aui:field-wrapper>

		<aui:field-wrapper name="createDate">
			<testray:date
				value="${testrayProject.createDate}"
			/>
		</aui:field-wrapper>

		<aui:field-wrapper name="createdBy">
			${testrayProject.userName}
		</aui:field-wrapper>

		<aui:field-wrapper name="dateLastModified">
			<testray:date
				value="${testrayProject.modifiedDate}"
			/>
		</aui:field-wrapper>
	</aui:fieldset>
</div>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>