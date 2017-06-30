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

<%@ include file="/init.jsp" %>

<%
long eulaFileEntryId = GetterUtil.getLong(portletPreferences.getValue("eulaFileEntryId", null));

boolean passedCertifiedDeveloper61Exam = TrainingCertificateTemplateLocalServiceUtil.hasCompletedTrainingCertificateTemplate(user.getUserId(), PortalUtil.getClassNameId(TrainingExamResult.class), OSBConstants.TRAINING_CERTIFICATE_TEMPLATE_CERTIFIED_DEVELOPER_61_ID);
boolean passedCertifiedDeveloper62Exam = TrainingCertificateTemplateLocalServiceUtil.hasCompletedTrainingCertificateTemplate(user.getUserId(), PortalUtil.getClassNameId(TrainingExamResult.class), OSBConstants.TRAINING_CERTIFICATE_TEMPLATE_CERTIFIED_DEVELOPER_62_ID);
%>

<div class="training-certification-agreement view">
	<c:choose>
		<c:when test="<%= ContractAuditLocalServiceUtil.hasLatestContractAudit(ContractEntryConstants.DEFAULT_CLASS_NAME_ID, ContractEntryConstants.DEFAULT_CLASS_PK, ContractEntryConstants.TYPE_TRAINING_EXAM_EULA, User.class.getName(), user.getUserId()) %>">
			<h1>
				<liferay-ui:message key="certification-downloads" />
			</h1>

			<c:if test="<%= passedCertifiedDeveloper61Exam %>">
				<div>
					<h2>
						<liferay-ui:message arguments='<%= "6.1" %>' key="liferay-certified-professional-developer-x" />
					</h2>

					<%
					DLFileEntry certifiedDeveloper61FileEntry = DLFileEntryServiceUtil.getFileEntry(OSBConstants.DL_FILE_ENTRY_CERTIFIED_DEVELOPER_61_ID);

					String certifiedDeveloper61URL = themeDisplay.getPathMain() + "/document_library/get_file?p_l_id=" + themeDisplay.getPlid() + "&fileEntryId=" + OSBConstants.DL_FILE_ENTRY_CERTIFIED_DEVELOPER_61_ID;
					%>

					<div>
						<a class="template-logo" href="<%= certifiedDeveloper61URL %>">
							<img src="<%= PortalUtil.getPathContext(request) %>/training_certification_agreement/images/certified_developer_61.png" />
						</a>

						<a class="template-text" href="<%= certifiedDeveloper61URL %>">
							<%= HtmlUtil.escape(certifiedDeveloper61FileEntry.getTitle()) %>
						</a>

						<a class="template-btn btn" href="<%= certifiedDeveloper61URL %>">
							<liferay-ui:message key="download" />
						</a>
					</div>
				</div>
			</c:if>

			<c:if test="<%= passedCertifiedDeveloper62Exam %>">
				<div>
					<h2>
						<liferay-ui:message arguments='<%= "6.2" %>' key="liferay-certified-professional-developer-x" />
					</h2>

					<%
					DLFileEntry certifiedDeveloper62FileEntry = DLFileEntryServiceUtil.getFileEntry(OSBConstants.DL_FILE_ENTRY_CERTIFIED_DEVELOPER_62_ID);

					String certifiedDeveloper62URL = themeDisplay.getPathMain() + "/document_library/get_file?p_l_id=" + themeDisplay.getPlid() + "&fileEntryId=" + OSBConstants.DL_FILE_ENTRY_CERTIFIED_DEVELOPER_62_ID;
					%>

					<div>
						<a class="template-logo" href="<%= certifiedDeveloper62URL %>">
							<img src="<%= PortalUtil.getPathContext(request) %>/training_certification_agreement/images/certified_developer_62.png" />
						</a>

						<a class="template-text" href="<%= certifiedDeveloper62URL %>">
							<%= HtmlUtil.escape(certifiedDeveloper62FileEntry.getTitle()) %>
						</a>

						<a class="template-btn btn" href="<%= certifiedDeveloper62URL %>">
							<liferay-ui:message key="download" />
						</a>
					</div>
				</div>
			</c:if>
		</c:when>
		<c:when test="<%= (passedCertifiedDeveloper61Exam || passedCertifiedDeveloper62Exam) %>">
			<h1>
				<liferay-ui:message key="liferay-certification-program-agreement" />
			</h1>

			<div class="terms-container">
				<div class="terms">

					<%
					ContractEntry contractEntry = ContractEntryLocalServiceUtil.getLatestContractEntry(ContractEntryConstants.DEFAULT_CLASS_NAME_ID, ContractEntryConstants.DEFAULT_CLASS_PK, ContractEntryConstants.TYPE_TRAINING_EXAM_EULA);

					String content = HtmlUtil.escape(contractEntry.getContent(themeDisplay.getLanguageId()));

					content = content.replace(StringPool.NEW_LINE, "<br />");
					%>

					<%= content %>
				</div>
			</div>

			<portlet:actionURL name="acceptEULA" var="acceptEULAURL" />

			<aui:form action="<%= acceptEULAURL %>" method="post" name="fm">
				<aui:input name="contractEntryId" type="hidden" value="<%= String.valueOf(contractEntry.getContractEntryId()) %>" />

				<a class="btn accept-eula-btn disabled" href="javascript:<portlet:namespace />accept();" id="<portlet:namespace />accept"><liferay-ui:message key="i-agree" /></a>
			</aui:form>

			<aui:script>
				function <portlet:namespace />accept() {
					var button = AUI().one('#<portlet:namespace />accept');

					if (button.hasClass('disabled')) {
						return;
					}

					submitForm(document.<portlet:namespace />fm);
				}
			</aui:script>

			<aui:script use="aui-base,aui-debounce">
				var terms = A.one('.terms');
				var termsContainer = A.one('.terms-container');
				var accept = A.one('#<portlet:namespace />accept');

				var termsHeight = terms.get('offsetHeight');
				var termsContainerHeight = termsContainer.get('offsetHeight');

				var acceptScrollHeight = termsHeight - termsContainerHeight - 10;

				if (acceptScrollHeight > 0) {
					var handle = termsContainer.on(
						'scroll',
						A.debounce(
							function() {
								var termsContainerScrollTop = termsContainer.get('scrollTop');

								if (handle && (termsContainerScrollTop > acceptScrollHeight)) {
									accept.removeClass('disabled');

									handle.detach();
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
		</c:when>
	</c:choose>
</div>