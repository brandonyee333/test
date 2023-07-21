<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/portal/init.jsp" %>

<%
String referer = ParamUtil.getString(request, WebKeys.REFERER, PortalUtil.getCurrentURL(request));

if (referer.equals(themeDisplay.getPathMain() + "/portal/update_terms_of_use")) {
	referer = themeDisplay.getPathMain() + "?doAsUserId=" + themeDisplay.getDoAsUserId();
}

TermsOfUseContentProvider termsOfUseContentProvider = TermsOfUseContentProviderRegistryUtil.getTermsOfUseContentProvider();
%>

<%@ include file="/html/portal/select_language.jspf" %>

<aui:form action='<%= themeDisplay.getPathMain() + "/portal/update_terms_of_use" %>' name="fm">
	<aui:input name="p_auth" type="hidden" value="<%= AuthTokenUtil.getToken(request) %>" />
	<aui:input name="doAsUserId" type="hidden" value="<%= themeDisplay.getDoAsUserId() %>" />
	<aui:input name="<%= WebKeys.REFERER %>" type="hidden" value="<%= referer %>" />

	<c:choose>
		<c:when test="<%= termsOfUseContentProvider != null %>">

			<%
			termsOfUseContentProvider.includeView(request, PipingServletResponse.createPipingServletResponse(pageContext));
			%>

		</c:when>
		<c:otherwise>
			<liferay-util:include page="/html/portal/terms_of_use_default.jsp" />
		</c:otherwise>
	</c:choose>

	<c:if test="<%= !user.isAgreedToTermsOfUse() %>">
		<aui:button-row>
			<aui:button cssClass="btn-lg" type="submit" value="i-agree" />

			<%
			String taglibOnClick = "alert('" + UnicodeLanguageUtil.get(request, "you-must-agree-with-the-terms-of-use-to-continue") + "');";
			%>

			<aui:button cssClass="btn-lg" onClick="<%= taglibOnClick %>" type="cancel" value="i-disagree" />
		</aui:button-row>
	</c:if>
</aui:form>