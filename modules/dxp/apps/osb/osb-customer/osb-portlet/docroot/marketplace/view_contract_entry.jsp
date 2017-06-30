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

<%
String redirect = ParamUtil.getString(request, "redirect");

long contractEntryId = ParamUtil.getLong(request, "contractEntryId");

String productClassName = ParamUtil.getString(request, "productClassName");
long productClassPK = ParamUtil.getLong(request, "productClassPK");
String signatoryClassName = ParamUtil.getString(request, "signatoryClassName");
long signatoryClassPK = ParamUtil.getLong(request, "signatoryClassPK");
String title = ParamUtil.getString(request, "title");

ContractEntry contractEntry = ContractEntryLocalServiceUtil.getContractEntry(contractEntryId);
%>

<div class="marketplace view-contract-entry">
	<c:if test="<%= Validator.isNotNull(title) %>">
		<liferay-ui:header
			showBackURL="<%= false %>"
			title="<%= HtmlUtil.escape(title) %>"
		/>
	</c:if>

	<%
	String content = HtmlUtil.escape(contractEntry.getContent(themeDisplay.getLanguageId()));

	content = content.replace(StringPool.NEW_LINE, "<br />");
	%>

	<p class="contract" id="<portlet:namespace />contract">
		<%= content %>
	</p>

	<c:if test="<%= Validator.isNotNull(redirect) %>">
		<portlet:actionURL name="acceptContractEntry" var="acceptContractEntryURL">
			<portlet:param name="redirect" value="<%= redirect %>" />
		</portlet:actionURL>

		<aui:form action="<%= acceptContractEntryURL %>" method="post" name="fmContractEntry">
			<aui:input name="contractEntryId" type="hidden" value="<%= contractEntryId %>" />
			<aui:input name="productClassName" type="hidden" value="<%= productClassName %>" />
			<aui:input name="productClassPK" type="hidden" value="<%= productClassPK %>" />
			<aui:input name="signatoryClassName" type="hidden" value="<%= signatoryClassName %>" />
			<aui:input name="signatoryClassPK" type="hidden" value="<%= signatoryClassPK %>" />

			<a class="btn disabled" href="javascript:<portlet:namespace />accept();" id="<portlet:namespace />accept"><liferay-ui:message key="i-agree" /></a>
		</aui:form>
	</c:if>
</div>

<aui:script>
	function <portlet:namespace />accept() {
		var button = AUI().one('#<portlet:namespace />accept');

		if (button.hasClass('disabled')) {
			return;
		}

		submitForm(document.<portlet:namespace />fmContractEntry);
	}
</aui:script>

<aui:script use="aui-base,aui-debounce">
	var contract = A.one('#<portlet:namespace />contract');
	var accept = A.one('#<portlet:namespace />accept');

	var acceptScrollHeight = contract.attr('scrollHeight') - (contract.height() + 100);

	if (acceptScrollHeight > 0) {
		var handle = contract.on(
			'scroll',
			A.debounce(
				function() {
					if (contract.attr('scrollTop') > acceptScrollHeight) {
						accept.removeClass('disabled');

						if (handle) {
							handle.detach();
						}
					}
				},
				200
			)
		);
	}
	else {
		accept.removeClass('disabled');
	}
</aui:script>