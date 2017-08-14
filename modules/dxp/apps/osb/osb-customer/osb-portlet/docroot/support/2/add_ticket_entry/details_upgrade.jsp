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

<div>
	<h2 class="component-title section-heading">
		<liferay-ui:message key="upgrade-details" />
	</h2>

	<div>
		<liferay-ui:message key="please-provide-accurate-upgrade-details-these-details-will-help-us-reproduce-your-issue-and-come-to-a-faster-resolution" />
	</div>

	<c:if test='<%= !productEntryLESADisplayNames.contains("digital-enterprise") %>'>
		<div class="portlet-msg-info">
			<liferay-ui:message key="if-you-require-support-for-a-potential-upgrade-to-dxp-please-contact-you-provisioning-representative-for-further-assistance" />
		</div>
	</c:if>
</div>

<br />

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

	<div class="aui-w45 fl">
		<div class="section-heading">
			<liferay-ui:message key="from-liferay-version" />:

			<c:if test="<%= !productEntry.isDigitalEnterprise() %>">
				<liferay-ui:icon-help message="please-update-this-field-from-the-above-lr-field-under-environment-configuration" />
			</c:if>
		</div>

		<c:choose>
			<c:when test="<%= productEntry.isDigitalEnterprise() %>">
				<aui:select id="<portlet:namespace />fromEnvLFR" name="<portlet:namespace />envLFR" onChange="<portlet:namespace />updateSupportMessage(this.value, 'Upgrade');">
					<aui:option value="0" />

					<%
					String previousNamePrefix = StringPool.BLANK;

					for (ListType envLFRType : envLFRTypes) {
					%>

						<aui:option label="<%= LanguageUtil.get(pageContext, envLFRType.getName()) %>" selected="<%= envLFRType.getListTypeId() == toEnvLFR %>" value="<%= envLFRType.getListTypeId() %>" />

					<%
					}
					%>

				</aui:select>
			</c:when>
			<c:otherwise>
				<aui:select disabled="disabled" id="<portlet:namespace />fromEnvLFR">
					<aui:option label="<%= LanguageUtil.get(pageContext, TicketEntryConstants.getEnvLabel(envLFR)) %><%= limited ? " (" + LanguageUtil.get(pageContext, "limited") + ")" : StringPool.BLANK %>" />
				</aui:select>
			</c:otherwise>
		</c:choose>
	</div>

	<div class="aui-w45 fr">
		<div class="section-heading">
			<liferay-ui:message key="to-liferay-version" />:

			<c:if test="<%= productEntry.isDigitalEnterprise() %>">
				<liferay-ui:icon-help message="please-update-this-field-from-the-above-lr-field-under-environment-configuration" />
			</c:if>
		</div>

		<c:choose>
			<c:when test="<%= productEntry.isDigitalEnterprise() %>">
				<aui:select disabled="<%= true %>" id="<portlet:namespace />toEnvLFR">
					<aui:option label="<%= LanguageUtil.get(pageContext, TicketEntryConstants.getEnvLabel(envLFR)) %><%= limited ? " (" + LanguageUtil.get(pageContext, "limited") + ")" : StringPool.BLANK %>" />
				</aui:select>
			</c:when>
			<c:otherwise>
				<aui:select id="<portlet:namespace />toEnvLFR" name="<portlet:namespace />toEnvLFR" onChange="<portlet:namespace />updateSupportMessage(this.value, 'Upgrade');">
					<aui:option value="0" />

					<c:if test="<%= TicketEntryConstants.getEnvLabel(envLFR) != TicketEntryConstants.NOT_AVAILABLE %>">

						<%
						String previousNamePrefix = StringPool.BLANK;

						for (ListType envLFRType : envLFRTypes) {
							if (ArrayUtil.contains(ProductEntryConstants.LIST_TYPES_DEPRECATED, envLFRType.getListTypeId())) {
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
									<aui:option disabled="<%= true %>" />
								</c:if>

								<aui:option label="<%= LanguageUtil.get(pageContext, envLFRType.getName()) %><%= toEnvLFRLimited ? " (" + LanguageUtil.get(pageContext, "limited") + ")" : StringPool.BLANK %>" selected="<%= envLFRType.getListTypeId() == toEnvLFR %>" value="<%= envLFRType.getListTypeId() %>" />
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

<div>
	<h2 class="section-heading">
		<liferay-ui:message key="how-is-the-document-library-server-persisting-documents" />
	</h2>

	<div>
		<aui:select id="<portlet:namespace />docLibPersistence" name="<portlet:namespace />docLibPersistence">
			<aui:option value="0" />

			<%
			for (int curDocLibPersistence : TicketEntryConstants.DOC_LIB_PERSISTENCES) {
			%>

				<aui:option label="<%= LanguageUtil.get(request, TicketEntryConstants.getDocLibPersistenceLabel(curDocLibPersistence)) %>" selected="<%= curDocLibPersistence == docLibPersistence %>" value="<%= curDocLibPersistence %>" />

			<%
			}
			%>

		</aui:select>
	</div>
</div>

<div>
	<h2 class="section-heading">
		<liferay-ui:message key="steps-used-to-perform-upgrade" />:
	</h2>

	<liferay-util:include page="/common/textarea.jsp" servletContext="<%= application %>">
		<liferay-util:param name="content" value="<%= stepsToUpgrade %>" />
		<liferay-util:param name="editorId" value="stepsToUpgrade" />
		<liferay-util:param name="name" value="stepsToUpgrade" />
		<liferay-util:param name="showCounter" value="<%= String.valueOf(Boolean.FALSE) %>" />
	</liferay-util:include>
</div>

<c:if test="<%= !productEntry.isDigitalEnterprise() %>">
	<div>
		<h2 class="section-heading">
			<liferay-ui:message key="please-provide-the-new-portal-ext.properties-file" />
		</h2>

		<liferay-util:include page="/support/2/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
			<liferay-util:param name="confirm" value="<%= String.valueOf(Boolean.TRUE) %>" />
			<liferay-util:param name="cssClass" value="clearfix file-container" />
			<liferay-util:param name="kBaseArticleId" value="33142855" />
			<liferay-util:param name="label" value="new-liferay-server" />
			<liferay-util:param name="ticketAttachmentId" value="<%= String.valueOf(toPortalExtTicketAttachmentId) %>" />
			<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_NEW_PORTAL_EXT) %>" />
		</liferay-util:include>
	</div>

	<div>
		<h2 class="section-heading">
			<liferay-ui:message key="please-provide-the-new-patching-tool-info-file" />
		</h2>

		<liferay-util:include page="/support/2/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
			<liferay-util:param name="confirm" value="<%= String.valueOf(Boolean.TRUE) %>" />
			<liferay-util:param name="cssClass" value="clearfix file-container" />
			<liferay-util:param name="kBaseArticleId" value="33142855" />
			<liferay-util:param name="label" value="new-liferay-server" />
			<liferay-util:param name="ticketAttachmentId" value="<%= String.valueOf(toPatchLevelTicketAttachmentId) %>" />
			<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_NEW_PATCH_LEVEL) %>" />
		</liferay-util:include>
	</div>
</c:if>

<div>
	<h2 class="section-heading">
		<liferay-ui:message key="if-possible-please-provide-any-log-files" />
	</h2>

	<liferay-util:include page="/support/2/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
		<liferay-util:param name="cssClass" value="clearfix file-container" />
		<liferay-util:param name="label" value="log-file-or-zip-file" />
		<liferay-util:param name="ticketAttachmentId" value="<%= String.valueOf(serverLogTicketAttachmentId) %>" />
		<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_SERVER_LOG) %>" />
	</liferay-util:include>
</div>