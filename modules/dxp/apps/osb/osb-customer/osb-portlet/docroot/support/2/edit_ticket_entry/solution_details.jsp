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

<%@ include file="/support/2/init.jsp" %>

<%
TicketEntry ticketEntry = (TicketEntry)request.getAttribute(OSBWebKeys.OSB_TICKET_ENTRY);

long ticketEntryId = ParamUtil.getLong(request, "ticketEntryId");

int component = BeanParamUtil.getInteger(ticketEntry, request, "component");
int subcomponent = BeanParamUtil.getInteger(ticketEntry, request, "subcomponent");
String subcomponentCustom = BeanParamUtil.getString(ticketEntry, request, "subcomponentCustom");
String summary = ParamUtil.getString(request, "summary");
boolean useCustomerSummary = ParamUtil.getBoolean(request, "useCustomerSummary", true);
int issueType = ParamUtil.getInteger(request, "issueType");
String solution = ParamUtil.getString(request, "solution");
boolean customerSpecific = ParamUtil.getBoolean(request, "customerSpecific");
boolean environmentSpecific = ParamUtil.getBoolean(request, "environmentSpecific");
boolean versionSpecific = ParamUtil.getBoolean(request, "versionSpecific");
boolean reviewForKB = ParamUtil.getBoolean(request, "reviewForKB");
boolean skipTesting = ParamUtil.getBoolean(request, "skipTesting");

int ticketLinkType1 = ParamUtil.getInteger(request, "ticketLinkType1");
int ticketLinkType2 = ParamUtil.getInteger(request, "ticketLinkType2");
int ticketLinkType3 = ParamUtil.getInteger(request, "ticketLinkType3");

int ticketLinkTypes[] = {ticketLinkType1, ticketLinkType2, ticketLinkType3};

String ticketLinkURL1 = ParamUtil.getString(request, "ticketLinkURL1");
String ticketLinkURL2 = ParamUtil.getString(request, "ticketLinkURL2");
String ticketLinkURL3 = ParamUtil.getString(request, "ticketLinkURL3");

String ticketLinkURLs[] = {ticketLinkURL1, ticketLinkURL2, ticketLinkURL3};

long ticketAttachmentId1 = ParamUtil.getLong(request, "file1TicketAttachmentId");
long ticketAttachmentId2 = ParamUtil.getLong(request, "file2TicketAttachmentId");
long ticketAttachmentId3 = ParamUtil.getLong(request, "file3TicketAttachmentId");

long[] fileAttachmentIds = {ticketAttachmentId1, ticketAttachmentId2, ticketAttachmentId3};
%>

<portlet:actionURL name="addTicketSolution" var="addTicketSolutionURL">
	<portlet:param name="mvcPath" value="/support/2/edit_ticket_entry/solution_details.jsp" />
	<portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntryId) %>" />
</portlet:actionURL>

<aui:form action="<%= addTicketSolutionURL %>" class="uni-form" enctype="multipart/form-data" method="post" name="fm">
	<liferay-ui:error exception="<%= DuplicateTicketAttachmentException.class %>" message="please-enter-a-unique-document-name" />
	<liferay-ui:error exception="<%= FileNameException.class %>" message="please-enter-a-file-with-a-valid-file-name" />

	<liferay-ui:error exception="<%= TicketEntryAttachmentSizeException.class %>">

		<%
		TicketEntryAttachmentSizeException tease = (TicketEntryAttachmentSizeException)errorException;
		%>

		<c:choose>
			<c:when test="<%= tease.getType() == TicketEntryAttachmentSizeException.EMPTY_FILE %>">
				<liferay-ui:message key="the-file-contains-no-data-and-cannot-be-uploaded" />
			</c:when>
			<c:when test="<%= tease.getType() == TicketEntryAttachmentSizeException.EXCEEDS_LIMIT %>">
				<liferay-ui:message key="please-upload-a-file-less-than-100-mb" />
			</c:when>
		</c:choose>
	</liferay-ui:error>

	<liferay-ui:error exception="<%= TicketEntrySubcomponentException.class %>" message="please-provide-a-valid-subcomponent" />
	<liferay-ui:error exception="<%= TicketLinkTypeException.class %>" message="please-choose-a-valid-link-type" />
	<liferay-ui:error exception="<%= TicketLinkURLException.class %>" message="please-enter-a-valid-url" />
	<liferay-ui:error exception="<%= TicketSolutionBodyException.class %>" message="please-enter-a-valid-issue-solution" />
	<liferay-ui:error exception="<%= TicketSolutionSummaryException.class %>" message="please-enter-a-valid-issue-summary" />

	<div class="aui-w100 solution-details unit">
		<h1>
			<liferay-ui:message key="solution-proposed-details" />
		</h1>

		<div>
			<span class="txt-b txt-up"><liferay-ui:message key="public" /></span>

			<span class="txt-b">(<liferay-ui:message key="will-be-displayed-to-the-customer" />)</span>

			<br /><br />

			<div class="solution-details-comments-container" id="<portlet:namespace />commentsContainer">
				<span class="highlighted-flag">*</span>

				<span class="txt-b"><liferay-ui:message key="solution" /></span>

				<br />

				<span><liferay-ui:message key="please-describe-how-to-resolve-the-issue-as-specifically-and-concisely-as-possible" /></span>

				<liferay-util:include page="/support/2/bbcode_editor.jsp" servletContext="<%= application %>">
					<liferay-util:param name="content" value="<%= solution %>" />
					<liferay-util:param name="editorId" value="editor1" />
					<liferay-util:param name="name" value="solution" />
				</liferay-util:include>
			</div>

			<br />

			<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id="attachmentsAndLinksPanel" persistState="<%= true %>" title="attachments-links">
				<div id="<portlet:namespace />addAttachments">
					<span class="txt-b"><liferay-ui:message key="add-attachments" /></span>

					<%
					for (int fileIndex = 1; fileIndex <= 3; fileIndex++) {
					%>

						<liferay-util:include page="/support/2/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
							<liferay-util:param name="cssClass" value="clearfix file-container" />
							<liferay-util:param name="fieldName" value='<%= "file" + fileIndex %>' />
							<liferay-util:param name="label" value='<%= LanguageUtil.get(request, "file") + StringPool.SPACE + fileIndex %>' />
							<liferay-util:param name="ticketAttachmentId" value="<%= String.valueOf(fileAttachmentIds[fileIndex - 1]) %>" />
							<liferay-util:param name="translate" value="<%= String.valueOf(Boolean.FALSE) %>" />
						</liferay-util:include>

						<c:if test="<%= ticketEntry.getStatus() != TicketEntryConstants.STATUS_RESOLVED_IN_PRODUCTION %>">
							<div class="hotfix">
								<aui:input label='<%= LanguageUtil.get(request, "hotfix") %>' name='<%= "hotfix" + fileIndex %>' type="checkbox" />
							</div>
						</c:if>

					<%
					}
					%>

				</div>

				<br />

				<div>
					<span class="txt-b"><liferay-ui:message key="references" /></span>

					<br />

					<liferay-ui:message key="please-include-any-links-to-related-articles-or-content-that-could-provide-further-context-for-the-solution" />

					<br />

					<%
					for (int urlIndex = 1; urlIndex <= 3; urlIndex++) {
					%>

						<div class="link-container">
							<liferay-ui:message key="url" />:

							<input class="lfr-input-text" name="<portlet:namespace />ticketLinkURL<%= urlIndex %>" style="width: 350px;" type="text" value="<%= HtmlUtil.escape(ticketLinkURLs[urlIndex - 1]) %>" />

							<c:if test="<%= liferayIncOrg %>">
								<liferay-ui:message key="type" />:

								<select name="<portlet:namespace />ticketLinkType<%= urlIndex %>">
									<option value=""></option>
									<option <%= (ticketLinkTypes[urlIndex - 1] == TicketLinkConstants.TYPE_COMMUNITY_RESOURCE) ? "selected" : "" %> value="<%= TicketLinkConstants.TYPE_COMMUNITY_RESOURCE %>"><liferay-ui:message key="<%= TicketLinkConstants.getTypeLabel(TicketLinkConstants.TYPE_COMMUNITY_RESOURCE) %>" /></option>
									<option <%= (ticketLinkTypes[urlIndex - 1] == TicketLinkConstants.TYPE_KNOWLEDGE_BASE_ARTICLE) ? "selected" : "" %> value="<%= TicketLinkConstants.TYPE_KNOWLEDGE_BASE_ARTICLE %>"><liferay-ui:message key="<%= TicketLinkConstants.getTypeLabel(TicketLinkConstants.TYPE_KNOWLEDGE_BASE_ARTICLE) %>" /></option>
									<option <%= (ticketLinkTypes[urlIndex - 1] == TicketLinkConstants.TYPE_OFFICIAL_DOCUMENTATION) ? "selected" : "" %> value="<%= TicketLinkConstants.TYPE_OFFICIAL_DOCUMENTATION %>"><liferay-ui:message key="<%= TicketLinkConstants.getTypeLabel(TicketLinkConstants.TYPE_OFFICIAL_DOCUMENTATION) %>" /></option>
									<option <%= (ticketLinkTypes[urlIndex - 1] == TicketLinkConstants.TYPE_OTHER) ? "selected" : "" %> value="<%= TicketLinkConstants.TYPE_OTHER %>"><liferay-ui:message key="<%= TicketLinkConstants.getTypeLabel(TicketLinkConstants.TYPE_OTHER) %>" /></option>
								</select>
							</c:if>
						</div>

					<%
					}
					%>

				</div>
			</liferay-ui:panel>

			<br />

			<span class="txt-b txt-up"><liferay-ui:message key="internal-only" /></span>

			<span class="txt-b">(<liferay-ui:message key="will-not-be-displayed-to-the-customer" />)</span>

			<br /><br />

			<div>
				<div class="aui-w33 inline-block">

					<%
					int[] subcomponents = TicketEntryConstants.getSubcomponents(component);
					%>

					<c:if test="<%= subcomponents.length > 0 %>">
						<span class="highlighted-flag">*</span>
					</c:if>

					<span class="txt-b"><liferay-ui:message key="subcomponent" />:</span>

					<select name="<portlet:namespace />ticketEntrySubcomponent" onChange="<portlet:namespace />selectTicketEntrySubcomponent(this.value);">
						<c:if test="<%= ticketEntry.getSubcomponent() <= 0 %>">
							<option value="0"><liferay-ui:message key="none" /></option>
						</c:if>

						<c:if test="<%= !ArrayUtil.contains(subcomponents, TicketEntryConstants.SUBCOMPONENT_OTHER) %>">
							<option <%= (subcomponent == TicketEntryConstants.SUBCOMPONENT_OTHER) ? "selected" : "" %> value="<%= TicketEntryConstants.SUBCOMPONENT_OTHER %>"><liferay-ui:message key="other" /></option>
						</c:if>

						<%
						for (int curSubcomponent : subcomponents) {
						%>

							<option <%= (curSubcomponent == subcomponent) ? "selected" : "" %> value="<%= curSubcomponent %>"><%= LanguageUtil.get(request, TicketEntryConstants.getSubcomponentLabel(curSubcomponent)) %></option>

						<%
						}
						%>

					</select>

					<input class="<%= (subcomponent == TicketEntryConstants.SUBCOMPONENT_OTHER) ? "" : "hide" %>" id="<portlet:namespace />ticketEntrySubcomponentCustom" name="<portlet:namespace />ticketEntrySubcomponentCustom" type="text" value="<%= HtmlUtil.escapeAttribute(subcomponentCustom) %>" />
				</div>

				<div class="aui-w33 inline-block">
					<span class="txt-b"><liferay-ui:message key="issue-type" />:</span>

					<select name="<portlet:namespace />issueType">
						<option value="0"></option>
						<option <%= (issueType == TicketSolutionConstants.ISSUE_TYPE_BUG) ? "selected" : "" %> value="<%= TicketSolutionConstants.ISSUE_TYPE_BUG %>"><liferay-ui:message key="bug" /></option>
						<option <%= (issueType == TicketSolutionConstants.ISSUE_TYPE_CONFIGURATION) ? "selected" : "" %> value="<%= TicketSolutionConstants.ISSUE_TYPE_CONFIGURATION %>"><liferay-ui:message key="configuration" /></option>
					</select>
				</div>
			</div>

			<br />

			<div>
				<span class="highlighted-flag">*</span>

				<span class="txt-b"><liferay-ui:message key="issue-summary" />:</span>

				<select id="<portlet:namespace />useCustomerSummary" name="<portlet:namespace />useCustomerSummary" onchange="<portlet:namespace />toggleIssueSummary(this.value);">
					<option <%= useCustomerSummary ? "selected" : "" %> value="1"><liferay-ui:message key="customers-description-accurately-captures-the-issue" /></option>
					<option <%= !useCustomerSummary ? "selected" : "" %> value="0"><liferay-ui:message key="customers-description-does-not-accurately-capture-the-issue" /></option>
				</select>

				<div class="<%= useCustomerSummary ? "hide" : "" %>" id="<portlet:namespace />issueSummaryContainer">
					<span><liferay-ui:message key="please-provide-the-correct-description-of-the-issue-in-this-ticket-be-sure-to-include-any-details-that-may-be-relevant-to-the-cause-of-the-issue-as-this-information-will-be-used-for-internal-auditing-and-for-reference-by-other-engineers" /></span>

					<liferay-util:include page="/support/2/bbcode_editor.jsp" servletContext="<%= application %>">
						<liferay-util:param name="content" value="<%= summary %>" />
						<liferay-util:param name="editorId" value="editor0" />
						<liferay-util:param name="name" value="summary" />
					</liferay-util:include>
				</div>
			</div>

			<br />

			<div>
				<input <%= reviewForKB ? "checked" : "" %> name="<portlet:namespace />reviewForKB" type="checkbox" />

				<liferay-ui:message key="this-issue-should-be-reviewed-and-considered-to-be-added-as-an-article-in-the-knowledge-base" />
			</div>

			<div>
				<input <%= customerSpecific ? "checked" : "" %> name="<portlet:namespace />customerSpecific" type="checkbox" />

				<liferay-ui:message key="this-issue-only-applies-to-this-customer" />
			</div>

			<div>
				<input <%= versionSpecific ? "checked" : "" %> name="<portlet:namespace />versionSpecific" type="checkbox" />

				<liferay-ui:message key="this-issue-only-applies-to-this-version-of-liferay" />
			</div>

			<div>
				<input <%= environmentSpecific ? "checked" : "" %> name="<portlet:namespace />environmentSpecific" type="checkbox" />

				<liferay-ui:message key="this-issue-only-applies-to-this-environment" />
			</div>

			<c:if test="<%= ticketEntry.getStatus() != TicketEntryConstants.STATUS_RESOLVED_IN_PRODUCTION %>">
				<div>
					<input <%= skipTesting ? "checked" : "" %> name="<portlet:namespace />skipTesting" onChange="<portlet:namespace />updateSkipTesting(this.checked);" type="checkbox" />

					<liferay-ui:message key="this-solution-does-not-require-customer-testing" />
				</div>
			</c:if>
		</div>

		<div>
			<input class="aui-button-input" type="submit" value="<liferay-ui:message key="send" />" />

			<input class="aui-button-input" onClick="window.close();" type="button" value="<liferay-ui:message key="cancel" />" />
		</div>
	</div>
</aui:form>

<aui:script>
	<c:if test="<%= (ticketEntry != null) && (ticketEntry.getStatus() == TicketEntryConstants.STATUS_SOLUTION_PROPOSED) %>">
		window.close();
	</c:if>

	var useCustomerSummary = A.one('#<portlet:namespace />useCustomerSummary');

	<portlet:namespace />toggleIssueSummary(useCustomerSummary.val());

	function <portlet:namespace />selectTicketEntrySubcomponent(subcomponent) {
		var A = AUI();

		var subcomponentCustom = A.one('#<portlet:namespace />ticketEntrySubcomponentCustom');

		if (subcomponent == '<%= TicketEntryConstants.SUBCOMPONENT_OTHER %>') {
			subcomponentCustom.show();
		}
		else {
			subcomponentCustom.hide();

			subcomponentCustom.val('');
		}
	}

	function <portlet:namespace />toggleIssueSummary(useCustomerSummary) {
		var A = AUI();

		var issueSummaryContainer = A.one('#<portlet:namespace />issueSummaryContainer');

		if (useCustomerSummary == 0) {
			issueSummaryContainer.show();
		}
		else {
			issueSummaryContainer.hide();
		}
	}

	function <portlet:namespace />updateSkipTesting(skipTesting) {
		var A = AUI();

		var hotfixCheckboxes = A.all('.hotfix');

		hotfixCheckboxes.each(
			function(item, index, collection) {
				if (skipTesting) {
					item.hide();
				}
				else {
					item.show();
				}
			}
		);
	}
</aui:script>