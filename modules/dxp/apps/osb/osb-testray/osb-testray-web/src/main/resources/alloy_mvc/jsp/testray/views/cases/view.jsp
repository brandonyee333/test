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

<liferay-util:include page="/alloy_mvc/jsp/testray/views/cases/header.jsp" servletContext="<%= application %>">
	<liferay-util:param name="tabs1" value="case-details" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<div class="testray-card testray-card-metadata-panel">
	<liferay-ui:panel-container
		cssClass="metadata-panel"
	>
		<liferay-ui:panel
			collapsible="${false}"
			title="details"
		>
			<dl class="data-list dl-horizontal">
				<dt>
					<liferay-ui:message key="type" />
				</dt>
				<dd>
					<c:out value="${testrayCaseType.name}" />
				</dd>
				<dt>
					<liferay-ui:message key="priority" />
				</dt>
				<dd>
					<c:out value="${testrayCase.priority}" />
				</dd>
				<dt>
					<liferay-ui:message key="main-component" />
				</dt>
				<dd>
					<c:out value="${testrayComponent.name}" />
				</dd>

				<c:if test="${!PortletPropsValues.TESTRAY_SIMPLIFIED_CASES}">
					<dt>
						<liferay-ui:message key="subcomponents" />
					</dt>
					<dd>
						<c:out value="${testrayCaseTestrayComponentNames}" />
					</dd>
				</c:if>

				<dt>
					<liferay-ui:message key="description" />
				</dt>
				<dd>
					<testray:rich-output
						type="${testrayCase.descriptionType}"
						value="${testrayCase.description}"
					/>
				</dd>
				<dt>
					<liferay-ui:message key="estimated-duration" />
				</dt>
				<dd>
					${(testrayCase.estimatedDuration > 0) ? testrayCase.estimatedDuration : StringPool.DASH}
				</dd>
				<dt>
					<liferay-ui:message key="steps" />
				</dt>
				<dd>
					<testray:rich-output
						type="${testrayCase.stepsType}"
						value="${testrayCase.steps}"
					/>
				</dd>
				<dt>
					<liferay-ui:message key="date-created" />
				</dt>
				<dd>
					<testray:date
						value="${testrayCase.createDate}"
					/>
				</dd>
				<dt>
					<liferay-ui:message key="date-last-modified" />
				</dt>
				<dd>
					<testray:date
						value="${testrayCase.modifiedDate}"
					/>
				</dd>
				<dt>
					<liferay-ui:message key="all-issues-found" />
				</dt>
				<dd>
					<c:set value="${testrayCaseResultReporter.issues}" var="issues" />
					<c:set value="${true}" var="showViewAllLink" />

					<%@ include file="/alloy_mvc/jsp/testray/views/issues.jspf" %>
				</dd>
			</dl>
		</liferay-ui:panel>
	</liferay-ui:panel-container>
</div>

<%@ include file="/alloy_mvc/jsp/testray/views/case_results/history_table.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>