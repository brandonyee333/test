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

<c:choose>
	<c:when test="<%= SessionMessages.contains(renderRequest, PortalUtil.getPortletId(renderRequest) + SessionMessages.KEY_SUFFIX_REFRESH_PORTLET) %>">
		<aui:script>
			window.opener.location.reload();

			window.close();
		</aui:script>
	</c:when>
	<c:otherwise>

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

		<aui:form action="<%= addTicketSolutionURL %>" enctype="multipart/form-data" method="post" name="solutionDetailsFm">
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

			<div class="solution-details">
				<h2>
					<liferay-ui:message key="solution-proposed-details" />
				</h2>

				<div>
					<span class="txt-b txt-up"><liferay-ui:message key="public" /></span>

					<span class="txt-b">(<liferay-ui:message key="will-be-displayed-to-the-customer" />)</span>

					<div class="section-vertical-spacing solution-details-comments-container" id="<portlet:namespace />commentsContainer">
						<div class="txt-b">
							<span class="highlighted-flag">*</span> <liferay-ui:message key="solution" />
						</div>

						<span><liferay-ui:message key="please-describe-how-to-resolve-the-issue-as-specifically-and-concisely-as-possible" /></span>

						<liferay-util:include page="/support/2/bbcode_editor.jsp" servletContext="<%= application %>">
							<liferay-util:param name="content" value="<%= solution %>" />
							<liferay-util:param name="editorId" value="editor1" />
							<liferay-util:param name="name" value="solution" />
						</liferay-util:include>
					</div>

					<liferay-ui:panel
						collapsible="<%= true %>"
						extended="<%= true %>"
						id="attachmentsAndLinksPanel"
						persistState="<%= true %>"
						title="attachments-links"
					>
						<div>
							<span class="txt-b"><liferay-ui:message key="add-attachments" /></span>

							<%
							for (int fileIndex = 1; fileIndex <= 3; fileIndex++) {
							%>

								<liferay-util:include page="/support/2/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
									<liferay-util:param name="cssClass" value="clearfix file-container" />
									<liferay-util:param name="fieldName" value='<%= "file" + fileIndex %>' />
									<liferay-util:param name="label" value='<%= LanguageUtil.get(request, "file") + StringPool.SPACE + fileIndex %>' />
									<liferay-util:param name="ticketAttachmentId" value="<%= String.valueOf(fileAttachmentIds[fileIndex - 1]) %>" />
									<liferay-util:param name="translate" value="<%= Boolean.FALSE.toString() %>" />
								</liferay-util:include>

								<c:if test="<%= ticketEntry.getStatus() != TicketEntryConstants.STATUS_RESOLVED_IN_PRODUCTION %>">
									<div class="hotfix">
										<aui:input label="hotfix" name='<%= "hotfix" + fileIndex %>' type="checkbox" />
									</div>
								</c:if>

							<%
							}
							%>

						</div>

						<div>
							<div class="txt-b">
								<liferay-ui:message key="references" />
							</div>

							<liferay-ui:message key="please-include-any-links-to-related-articles-or-content-that-could-provide-further-context-for-the-solution" />

							<%
							for (int urlIndex = 1; urlIndex <= 3; urlIndex++) {
							%>

								<div class="link-container">
									<liferay-ui:message key="url" />:

									<aui:input label="" name='<%= "ticketLinkURL" + urlIndex %>' style="width: 350px;" type="text" value="<%= ticketLinkURLs[urlIndex - 1] %>" />

									<c:if test="<%= liferayIncOrg %>">
										<liferay-ui:message key="type" />:

										<aui:select label="" name='<%= "ticketLinkType" + urlIndex %>'>
											<aui:option value="" />
											<aui:option label="<%= TicketLinkConstants.getTypeLabel(TicketLinkConstants.TYPE_COMMUNITY_RESOURCE) %>" selected="<%= (ticketLinkTypes[urlIndex - 1] == TicketLinkConstants.TYPE_COMMUNITY_RESOURCE) %>" value="<%= TicketLinkConstants.TYPE_COMMUNITY_RESOURCE %>" />
											<aui:option label="<%= TicketLinkConstants.getTypeLabel(TicketLinkConstants.TYPE_KNOWLEDGE_BASE_ARTICLE) %>" selected="<%= (ticketLinkTypes[urlIndex - 1] == TicketLinkConstants.TYPE_KNOWLEDGE_BASE_ARTICLE) %>" value="<%= TicketLinkConstants.TYPE_KNOWLEDGE_BASE_ARTICLE %>" />
											<aui:option label="<%= TicketLinkConstants.getTypeLabel(TicketLinkConstants.TYPE_OFFICIAL_DOCUMENTATION) %>" selected="<%= (ticketLinkTypes[urlIndex - 1] == TicketLinkConstants.TYPE_OFFICIAL_DOCUMENTATION) %>" value="<%= TicketLinkConstants.TYPE_OFFICIAL_DOCUMENTATION %>" />
											<aui:option label="<%= TicketLinkConstants.getTypeLabel(TicketLinkConstants.TYPE_OTHER) %>" selected="<%= (ticketLinkTypes[urlIndex - 1] == TicketLinkConstants.TYPE_OTHER) %>" value="<%= TicketLinkConstants.TYPE_OTHER %>" />
										</aui:select>
									</c:if>
								</div>

							<%
							}
							%>

						</div>
					</liferay-ui:panel>

					<div class="section-vertical-spacing txt-b">
						<span class="txt-up"><liferay-ui:message key="internal-only" /></span>
						<span class="txt-b">(<liferay-ui:message key="will-not-be-displayed-to-the-customer" />)</span>
					</div>

					<div>
						<div class="inline-block w33">

							<%
							int[] subcomponents = TicketEntryConstants.getSubcomponents(component);
							%>

							<c:if test="<%= subcomponents.length > 0 %>">
								<span class="highlighted-flag">*</span>
							</c:if>

							<span class="txt-b"><liferay-ui:message key="subcomponent" />:</span>

							<aui:select label="" name="ticketEntrySubcomponent" onChange='<%= renderResponse.getNamespace() + "selectTicketEntrySubcomponent(this.value);" %>'>
								<c:if test="<%= ticketEntry.getSubcomponent() <= 0 %>">
									<aui:option label="" value="0" />
								</c:if>

								<c:if test="<%= !ArrayUtil.contains(subcomponents, TicketEntryConstants.SUBCOMPONENT_OTHER) %>">
									<aui:option label="other" selected="<%= subcomponent == TicketEntryConstants.SUBCOMPONENT_OTHER %>" value="<%= TicketEntryConstants.SUBCOMPONENT_OTHER %>" />
								</c:if>

								<%
								for (int curSubcomponent : subcomponents) {
								%>

									<aui:option label="<%= TicketEntryConstants.getSubcomponentLabel(curSubcomponent) %>" selected="<%= curSubcomponent == subcomponent %>" value="<%= curSubcomponent %>" />

								<%
								}
								%>

							</aui:select>

							<aui:input cssClass='<%= (subcomponent == TicketEntryConstants.SUBCOMPONENT_OTHER) ? "" : "hide" %>' label="" name="ticketEntrySubcomponentCustom" type="text" value="<%= HtmlUtil.escapeAttribute(subcomponentCustom) %>" />
						</div>

						<div class="inline-block w33">
							<span class="txt-b"><liferay-ui:message key="issue-type" />:</span>

							<aui:select label="" name="issueType">
								<aui:option value="0" />
								<aui:option label="bug" selected="<%= issueType == TicketSolutionConstants.ISSUE_TYPE_BUG %>" value="<%= TicketSolutionConstants.ISSUE_TYPE_BUG %>" />
								<aui:option label="configuration" selected="<%= issueType == TicketSolutionConstants.ISSUE_TYPE_CONFIGURATION %>" value="<%= TicketSolutionConstants.ISSUE_TYPE_CONFIGURATION %>" />
							</aui:select>
						</div>
					</div>

					<div>
						<span class="highlighted-flag">*</span>

						<span class="txt-b"><liferay-ui:message key="issue-summary" />:</span>

						<aui:select label="" name="useCustomerSummary" onChange='<%= renderResponse.getNamespace() + "toggleIssueSummary(this.value);" %>'>
							<aui:option label="customers-description-accurately-captures-the-issue" selected="<%= useCustomerSummary %>" value="1" />
							<aui:option label="customers-description-does-not-accurately-capture-the-issue" selected="<%= !useCustomerSummary %>" value="0" />
						</aui:select>

						<div class="section-vertical-spacing <%= useCustomerSummary ? "hide" : "" %>" id="<portlet:namespace />issueSummaryContainer">
							<span><liferay-ui:message key="please-provide-the-correct-description-of-the-issue-in-this-ticket-be-sure-to-include-any-details-that-may-be-relevant-to-the-cause-of-the-issue-as-this-information-will-be-used-for-internal-auditing-and-for-reference-by-other-engineers" /></span>

							<liferay-util:include page="/support/2/bbcode_editor.jsp" servletContext="<%= application %>">
								<liferay-util:param name="content" value="<%= summary %>" />
								<liferay-util:param name="editorId" value="editor0" />
								<liferay-util:param name="name" value="summary" />
							</liferay-util:include>
						</div>
					</div>

					<div>
						<aui:input checked="<%= reviewForKB %>" label="this-issue-should-be-reviewed-and-considered-to-be-added-as-an-article-in-the-knowledge-base" name="reviewForKB" type="checkbox" />

						<aui:input checked="<%= customerSpecific %>" label="this-issue-only-applies-to-this-customer" name="customerSpecific" type="checkbox" />

						<aui:input checked="<%= versionSpecific %>" label="this-issue-only-applies-to-this-version-of-liferay" name="versionSpecific" type="checkbox" />

						<aui:input checked="<%= environmentSpecific %>" label="this-issue-only-applies-to-this-environment" name="environmentSpecific" type="checkbox" />

						<c:if test="<%= ticketEntry.getStatus() != TicketEntryConstants.STATUS_RESOLVED_IN_PRODUCTION %>">
							<aui:input checked="<%= skipTesting %>" label="this-solution-does-not-require-customer-testing" name="skipTesting" onChange='<%= renderResponse.getNamespace() + "updateSkipTesting(this.checked);" %>' type="checkbox" />
						</c:if>
					</div>
				</div>

				<aui:button type="submit" value="send" />

				<aui:button onClick="window.close();" value="cancel" />
			</div>
		</aui:form>

		<aui:script>
			Liferay.provide(
				window,
				'<portlet:namespace />selectTicketEntrySubcomponent',
				function(subcomponent) {
					var A = AUI();

					var subcomponentCustom = A.one('#<portlet:namespace />ticketEntrySubcomponentCustom');

					if (subcomponentCustom) {
						var isSubcomponent = (subcomponent == '<%= TicketEntryConstants.SUBCOMPONENT_OTHER %>');

						subcomponentCustom.toggle(isSubcomponent);

						if (!isSubcomponent) {
							subcomponentCustom.val('');
						}
					}
				},
				['aui-base']
			);

			Liferay.provide(
				window,
				'<portlet:namespace />toggleIssueSummary',
				function(useCustomerSummary) {
					var A = AUI();

					var issueSummaryContainer = A.one('#<portlet:namespace />issueSummaryContainer');

					if (issueSummaryContainer) {
						issueSummaryContainer.toggle(useCustomerSummary == 0);
					}
				},
				['aui-base']
			);

			Liferay.provide(
				window,
				'<portlet:namespace />updateSkipTesting',
				function(skipTesting) {
					var A = AUI();

					var hotfixCheckboxes = A.all('.hotfix');

					hotfixCheckboxes.each(
						function(item) {
							item.toggle(!skipTesting);
						}
					);
				},
				['aui-base']
			);
		</aui:script>

		<aui:script use="aui-base">
			var useCustomerSummary = A.one('#<portlet:namespace />useCustomerSummary');

			if (useCustomerSummary) {
				<portlet:namespace />toggleIssueSummary(useCustomerSummary.val());
			}
		</aui:script>
	</c:otherwise>
</c:choose>