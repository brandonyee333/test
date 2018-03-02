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
OfferingEntry offeringEntry = (OfferingEntry)request.getAttribute("add_ticket_entry.jsp-offeringEntry");
ProductEntry productEntry = (ProductEntry)request.getAttribute("add_ticket_entry.jsp-productEntry");
TreeSet<String> productEntryLESADisplayNames = (TreeSet<String>)request.getAttribute("add_ticket_entry.jsp-productEntryLESADisplayNames");

long accountEnvironmentId = ParamUtil.getLong(request, "accountEnvironmentId");
int docLibPersistence = ParamUtil.getInteger(request, "docLibPersistence");
String patchLevelCheckbox = ParamUtil.getString(request, "patchLevelCheckbox");
long patchLevelTicketAttachmentId = ParamUtil.getLong(request, "patchLevelTicketAttachmentId");
String portalExtCheckbox = ParamUtil.getString(request, "portalExtCheckbox");
long portalExtTicketAttachmentId = ParamUtil.getLong(request, "portalExtTicketAttachmentId");
long serverLogTicketAttachmentId = ParamUtil.getLong(request, "serverLogTicketAttachmentId");
String stepsToUpgrade = ParamUtil.getString(request, "stepsToUpgrade");
int toEnvLFR = ParamUtil.getInteger(request, "toEnvLFR");
long toPatchLevelTicketAttachmentId = ParamUtil.getLong(request, "toPatchLevelTicketAttachmentId");
long toPortalExtTicketAttachmentId = ParamUtil.getLong(request, "toPortalExtTicketAttachmentId");

AccountEnvironment accountEnvironment = null;

if (accountEnvironmentId > 0) {
	accountEnvironment = AccountEnvironmentServiceUtil.getAccountEnvironment(accountEnvironmentId);
}

int envLFR = BeanParamUtil.getInteger(accountEnvironment, request, "envLFR");
%>

<liferay-ui:error exception="<%= DuplicateTicketAttachmentException.class %>" message="please-enter-a-unique-document-name" />

<liferay-ui:error exception="<%= RequiredFieldException.class %>">

	<%
	RequiredFieldException rfe = (RequiredFieldException)errorException;

	String requiredField = rfe.getRequiredField();
	%>

	<c:if test='<%= requiredField.equals("patch-level") || requiredField.equals("portal-ext") %>'>
		<liferay-ui:message key="please-upload-a-portal-ext-and-patch-level-file" />
	</c:if>
</liferay-ui:error>

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

<aui:fieldset-group>
	<aui:fieldset helpMessage="please-provide-accurate-upgrade-details-these-details-will-help-us-reproduce-your-issue-and-come-to-a-faster-resolution" label="upgrade-details">
		<c:if test="<%= !productEntryLESADisplayNames.contains(ProductEntryConstants.DISPLAY_NAME_DIGITAL_ENTERPRISE) %>">
			<div class="portlet-msg-info">
				<liferay-ui:message key="if-you-require-support-for-a-potential-upgrade-to-dxp-please-contact-you-provisioning-representative-for-further-assistance" />
			</div>
		</c:if>

		<div class="clearfix">
			<liferay-util:include page="/support/2/common/eosl_environment_liferay.jsp" servletContext="<%= application %>">
				<portlet:param name="envLFR" value="<%= String.valueOf(toEnvLFR) %>" />
				<portlet:param name="idPrefix" value="supportUpgradeMessageDisplay" />
			</liferay-util:include>

			<%
			List<ListType> envLFRTypes = productEntry.getAllVersionsListTypes();

			boolean limited = false;

			if (envLFR < ProductEntryConstants.PORTAL_VERSION_6_2_10) {
				limited = true;
			}
			%>

			<div class="pull-left w45">
				<c:choose>
					<c:when test="<%= productEntry.isDigitalEnterprise() %>">
						<aui:select helpMessage='<%= !productEntry.isDigitalEnterprise() ? "please-update-this-field-from-the-above-lr-field-under-environment-configuration" : StringPool.BLANK %>' id="fromEnvLFR" label="from-liferay-version" name="envLFR" onChange='<%= renderResponse.getNamespace() + "updateSupportMessage(this.value, 'Upgrade');" %>'>
							<aui:option value="0" />

							<%
							String previousNamePrefix = StringPool.BLANK;

							for (ListType envLFRType : envLFRTypes) {
							%>

								<aui:option label="<%= envLFRType.getName() %>" selected="<%= envLFRType.getListTypeId() == toEnvLFR %>" value="<%= envLFRType.getListTypeId() %>" />

							<%
							}
							%>

						</aui:select>
					</c:when>
					<c:otherwise>
						<aui:select disabled="<%= true %>" helpMessage='<%= !productEntry.isDigitalEnterprise() ? "please-update-this-field-from-the-above-lr-field-under-environment-configuration" : StringPool.BLANK %>' label="from-liferay-version" name="fromEnvLFR">

							<%
							String optionLabel = LanguageUtil.get(request, TicketEntryConstants.getEnvLabel(envLFR));

							if (limited) {
								optionLabel += " (" + LanguageUtil.get(request, "limited") + ")";
							}
							%>

							<aui:option label="<%= optionLabel %>" />
						</aui:select>
					</c:otherwise>
				</c:choose>
			</div>

			<div class="pull-right w45">
				<c:choose>
					<c:when test="<%= productEntry.isDigitalEnterprise() %>">
						<aui:select disabled="<%= true %>" helpMessage='<%= productEntry.isDigitalEnterprise() ? "please-update-this-field-from-the-above-lr-field-under-environment-configuration" : StringPool.BLANK %>' label="to-liferay-version" name="toEnvLFR">

							<%
							String optionLabel = LanguageUtil.get(request, TicketEntryConstants.getEnvLabel(envLFR));

							if (limited) {
								optionLabel += " (" + LanguageUtil.get(request, "limited") + ")";
							}
							%>

							<aui:option label="<%= optionLabel %>" />
						</aui:select>
					</c:when>
					<c:otherwise>
						<aui:select helpMessage='<%= productEntry.isDigitalEnterprise() ? "please-update-this-field-from-the-above-lr-field-under-environment-configuration" : StringPool.BLANK %>' label="to-liferay-version" name="toEnvLFR" onChange='<%= renderResponse.getNamespace() + "updateSupportMessage(this.value, 'Upgrade');" %>'>
							<aui:option value="0" />

							<c:if test="<%= TicketEntryConstants.getEnvLabel(envLFR) != TicketEntryConstants.NOT_AVAILABLE %>">

								<%
								String previousNamePrefix = StringPool.BLANK;

								long[] listTypesDeprecated = Arrays.stream(ProductEntryConstants.LIST_TYPES_DEPRECATED).asLongStream().toArray();

								for (ListType envLFRType : envLFRTypes) {
									if (ArrayUtil.contains(listTypesDeprecated, envLFRType.getListTypeId())) {
										continue;
									}

									boolean toEnvLFRLimited = false;

									if (envLFRType.getListTypeId() < ProductEntryConstants.PORTAL_VERSION_6_2_10) {
										toEnvLFRLimited = true;
									}

									String name = envLFRType.getName();

									String namePrefix = StringPool.BLANK;

									if (envLFRType.getListTypeId() > envLFR) {
										namePrefix = name.substring(0, 3);
									}
								%>

									<c:if test="<%= envLFRType.getListTypeId() > envLFR %>">
										<c:if test="<%= Validator.isNotNull(previousNamePrefix) && !previousNamePrefix.equals(namePrefix) %>">
											<aui:option disabled="<%= true %>" label="--------" />
										</c:if>

										<%
										String optionLabel = LanguageUtil.get(request, envLFRType.getName());

										if (toEnvLFRLimited) {
											optionLabel += " (" + LanguageUtil.get(request, "limited") + ")";
										}
										%>

										<aui:option label="<%= optionLabel %>" selected="<%= envLFRType.getListTypeId() == toEnvLFR %>" value="<%= envLFRType.getListTypeId() %>" />
									</c:if>

								<%
									previousNamePrefix = namePrefix;
								}
								%>

							</c:if>
						</aui:select>
					</c:otherwise>
				</c:choose>
			</div>
		</div>

		<aui:select label="how-is-the-document-library-server-persisting-documents" name="docLibPersistence">
			<aui:option value="0" />

			<%
			for (int curDocLibPersistence : TicketEntryConstants.DOC_LIB_PERSISTENCES) {
			%>

				<aui:option label="<%= TicketEntryConstants.getDocLibPersistenceLabel(curDocLibPersistence) %>" selected="<%= curDocLibPersistence == docLibPersistence %>" value="<%= curDocLibPersistence %>" />

			<%
			}
			%>

		</aui:select>

		<aui:field-wrapper label="steps-used-to-perform-upgrade">
			<liferay-util:include page="/common/textarea.jsp" servletContext="<%= application %>">
				<liferay-util:param name="content" value="<%= stepsToUpgrade %>" />
				<liferay-util:param name="editorId" value="stepsToUpgrade" />
				<liferay-util:param name="name" value="stepsToUpgrade" />
				<liferay-util:param name="showCounter" value="<%= String.valueOf(Boolean.FALSE) %>" />
			</liferay-util:include>
		</aui:field-wrapper>

		<c:if test="<%= !productEntry.isDigitalEnterprise() %>">
			<aui:field-wrapper label="please-provide-the-new-portal-ext.properties-file">
				<liferay-util:include page="/support/2/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
					<liferay-util:param name="confirm" value="<%= String.valueOf(Boolean.TRUE) %>" />
					<liferay-util:param name="cssClass" value="clearfix file-container" />
					<liferay-util:param name="kBaseArticleId" value="33142855" />
					<liferay-util:param name="label" value="new-liferay-server" />
					<liferay-util:param name="ticketAttachmentId" value="<%= String.valueOf(toPortalExtTicketAttachmentId) %>" />
					<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_NEW_PORTAL_EXT) %>" />
				</liferay-util:include>
			</aui:field-wrapper>

			<aui:field-wrapper label="please-provide-the-new-patching-tool-info-file">
				<liferay-util:include page="/support/2/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
					<liferay-util:param name="confirm" value="<%= String.valueOf(Boolean.TRUE) %>" />
					<liferay-util:param name="cssClass" value="clearfix file-container" />
					<liferay-util:param name="kBaseArticleId" value="33142855" />
					<liferay-util:param name="label" value="new-liferay-server" />
					<liferay-util:param name="ticketAttachmentId" value="<%= String.valueOf(toPatchLevelTicketAttachmentId) %>" />
					<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_NEW_PATCH_LEVEL) %>" />
				</liferay-util:include>
			</aui:field-wrapper>
		</c:if>

		<aui:field-wrapper label="if-possible-please-provide-any-log-files">
			<liferay-util:include page="/support/2/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
				<liferay-util:param name="cssClass" value="clearfix file-container" />
				<liferay-util:param name="label" value="log-file-or-zip-file" />
				<liferay-util:param name="ticketAttachmentId" value="<%= String.valueOf(serverLogTicketAttachmentId) %>" />
				<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_SERVER_LOG) %>" />
			</liferay-util:include>
		</aui:field-wrapper>
	</aui:fieldset>
</aui:fieldset-group>