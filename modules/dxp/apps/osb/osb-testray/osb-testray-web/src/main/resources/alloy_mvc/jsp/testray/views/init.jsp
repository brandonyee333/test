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

<%@ include file="/alloy_mvc/jsp/util/init.jsp" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@
taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %><%@
taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/testray" prefix="testray" %><%@
taglib uri="http://liferay.com/tld/testray-permission" prefix="testrayPermission" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%!
public class AlloyLanguageUtil extends LanguageUtil {

	public AlloyLanguageUtil(HttpServletRequest request) {
		_request = request;
	}

	public String format(String pattern, Object[] arguments) {
		return format(_request, pattern, arguments);
	}

	public String get(String key) {
		return get(_request, key);
	}

	public String getUnicode(String key) {
		return UnicodeLanguageUtil.get(_request, key);
	}

	private final HttpServletRequest _request;

}
%>

<c:set value="<%= new AlloyLanguageUtil(request) %>" var="AlloyLanguageUtil" />

<c:set scope="request" value="false" var="aui:form:useNamespace" />

<c:set value="" var="htmlNamespace" />

<c:set value="<%= windowState.equals(LiferayWindowState.POP_UP) %>" var="popup" />

<c:set value="<%= Indexer.class %>" var="indexerClass" />
<c:set value="<%= TestrayArchive.class %>" var="testrayArchiveClass" />
<c:set value="<%= TestrayArchiveCompressedDataBlobModel.class %>" var="testrayArchiveCompressedDataBlobModelClass" />
<c:set value="<%= TestrayAssignment.class %>" var="testrayAssignmentClass" />
<c:set value="<%= TestrayBuild.class %>" var="testrayBuildClass" />
<c:set value="<%= TestrayCase.class %>" var="testrayCaseClass" />
<c:set value="<%= TestrayCaseResult.class %>" var="testrayCaseResultClass" />
<c:set value="<%= TestrayCaseResultWarning.class %>" var="testrayCaseResultWarningClass" />
<c:set value="<%= TestrayCaseType.class %>" var="testrayCaseTypeClass" />
<c:set value="<%= TestrayComponent.class %>" var="testrayComponentClass" />
<c:set value="<%= TestrayFactor.class %>" var="testrayFactorClass" />
<c:set value="<%= TestrayFactorCategory.class %>" var="testrayFactorCategoryClass" />
<c:set value="<%= TestrayFactorOption.class %>" var="testrayFactorOptionClass" />
<c:set value="<%= TestrayIssue.class %>" var="testrayIssueClass" />
<c:set value="<%= TestrayProductVersion.class %>" var="testrayProductVersionClass" />
<c:set value="<%= TestrayProject.class %>" var="testrayProjectClass" />
<c:set value="<%= TestrayRequirement.class %>" var="testrayRequirementClass" />
<c:set value="<%= TestrayRoutine.class %>" var="testrayRoutineClass" />
<c:set value="<%= TestrayRun.class %>" var="testrayRunClass" />
<c:set value="<%= TestraySubtask.class %>" var="testraySubtaskClass" />
<c:set value="<%= TestraySuite.class %>" var="testraySuiteClass" />
<c:set value="<%= TestrayTask.class %>" var="testrayTaskClass" />
<c:set value="<%= TestrayTeam.class %>" var="testrayTeamClass" />
<c:set value="<%= User.class %>" var="userClass" />