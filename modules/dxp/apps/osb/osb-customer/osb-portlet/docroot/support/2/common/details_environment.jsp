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

if (productEntry == null) {
	productEntry = (ProductEntry)request.getAttribute("preferences.jsp-productEntry");
}

long accountEntryId = ParamUtil.getLong(request, "accountEntryId");
long accountEnvironmentId = ParamUtil.getLong(request, "accountEnvironmentId");
int component = ParamUtil.getInteger(request, "component");
String portalExtCheckbox = ParamUtil.getString(request, "portalExtCheckbox");
long portalExtTicketAttachmentId = ParamUtil.getLong(request, "portalExtTicketAttachmentId");
String patchLevelCheckbox = ParamUtil.getString(request, "patchLevelCheckbox");
long patchLevelTicketAttachmentId = ParamUtil.getLong(request, "patchLevelTicketAttachmentId");

AccountEnvironment accountEnvironment = null;

if (accountEnvironmentId > 0) {
	accountEnvironment = AccountEnvironmentServiceUtil.getAccountEnvironment(accountEnvironmentId);
}

boolean edit = ParamUtil.getBoolean(request, "edit", false);

String envName = BeanParamUtil.getString(accountEnvironment, request, "name");
int envOS = BeanParamUtil.getInteger(accountEnvironment, request, "envOS");
String envOSCustom = BeanParamUtil.getString(accountEnvironment, request, "envOSCustom");
int envDB = BeanParamUtil.getInteger(accountEnvironment, request, "envDB");
int envJVM = BeanParamUtil.getInteger(accountEnvironment, request, "envJVM");
int envAS = BeanParamUtil.getInteger(accountEnvironment, request, "envAS");
int envLFR = BeanParamUtil.getInteger(accountEnvironment, request, "envLFR");
int envBrowser = ParamUtil.getInteger(request, "envBrowser");
String envBrowserCustom = ParamUtil.getString(request, "envBrowserCustom");
int envCS = ParamUtil.getInteger(request, "envCS");
String envSearch = ParamUtil.getString(request, "envSearch");
boolean validateEnvironment = ParamUtil.getBoolean(request, "validateEnvironment");

boolean socialOffice = false;

if (productEntry != null) {
	socialOffice = productEntry.isSocialOffice();
}
%>

<div class="aui-helper-clearfix">
	<liferay-util:include page="/support/2/common/eosl_environment_liferay.jsp" servletContext="<%= application %>">
		<portlet:param name="envLFR" value="<%= String.valueOf(envLFR) %>" />
	</liferay-util:include>

	<div class="configuration">
		<c:choose>
			<c:when test="<%= !edit %>">
				<div class="fl single-line">
					<div class="aui-w50 content-column">
						<div class="content-column-content env">
							<span class="field-label txt-b"><%= edit ? "*" : "" %><liferay-ui:message key="name" />:</span>

							<span class="txt-b"><%= HtmlUtil.escape(envName) %></span>
						</div>
					</div>

					<div class="aui-w50 content-column">
						<div class="content-column-content env">
							<span class="field-label txt-b"><%= edit ? "*" : "" %><liferay-ui:message key="product" />:</span>

							<%= LanguageUtil.get(request, productEntry.getName()) %>
						</div>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<c:if test="<%= Validator.isNotNull(envName) %>">
					<input name="<portlet:namespace />envName" type="hidden" value="<%= HtmlUtil.escapeAttribute(envName) %>" />
				</c:if>
			</c:otherwise>
		</c:choose>

		<div class="fl single-line">
			<div class="aui-w33 content-column">
				<div class="content-column-content env">
					<div>
						<c:choose>
							<c:when test="<%= socialOffice %>">
								<span class="field-label txt-b" title="<liferay-ui:message key="social-office" />"><%= edit ? "*" : "" %><liferay-ui:message key="so" />:</span>
							</c:when>
							<c:otherwise>
								<span class="field-label txt-b" title="<liferay-ui:message key="liferay-version" />"><%= edit ? "*" : "" %><liferay-ui:message key="lr" />:</span>
							</c:otherwise>
						</c:choose>

						<c:choose>
							<c:when test="<%= edit %>">

								<%
								List<ListType> envLFRTypes = productEntry.getAllVersionsListTypes();

								String previousNamePrefix = StringPool.BLANK;

								String envLFRName = renderResponse.getNamespace();

								if (productEntry.isDigitalEnterprise() && (component == TicketEntryConstants.COMPONENT_UPGRADE)) {
									envLFRName += "toEnvLFR";
								}
								else {
									envLFRName += "envLFR";
								}

								String envLFROnChange = renderResponse.getNamespace() + "selectPortalVersion(this.value, 0, '', 0, '', 0, '', 0, '', 0, '', 0, '', ''); " + renderResponse.getNamespace() + "updateSupportMessage(this.value, '');";

								if (component == TicketEntryConstants.COMPONENT_UPGRADE) {
									envLFROnChange = envLFROnChange + renderResponse.getNamespace() + "updateUpgradeEnvLFR(this.value, " + productEntry.isDigitalEnterprise() + ");";
								}
								%>

								<select data-field-required-status="<%= false %>" field-required-message="<%= LanguageUtil.format(request, "invalid-value-provided-for-x", "liferay-version") %>" id="<portlet:namespace />envLFR" name="<%= envLFRName %>" onChange="<%= envLFROnChange %>">
									<option value="0"></option>

									<%
									long[] listTypesDeprecated = Arrays.stream(ProductEntryConstants.LIST_TYPES_DEPRECATED).asLongStream().toArray();

									for (ListType envLFRType : envLFRTypes) {
										if (((envLFRType.getListTypeId() == ProductEntryConstants.PORTAL_VERSION_OTHER) && (envLFR != ProductEntryConstants.PORTAL_VERSION_OTHER)) || ArrayUtil.contains(listTypesDeprecated, envLFRType.getListTypeId())) {
											continue;
										}

										boolean limited = false;

										if (envLFRType.getListTypeId() < ProductEntryConstants.PORTAL_VERSION_6_2_10) {
											limited = true;
										}

										String name = envLFRType.getName();

										String namePrefix = name.substring(0, 3);
									%>

										<c:if test="<%= Validator.isNotNull(previousNamePrefix) && !previousNamePrefix.equals(namePrefix) %>">
											<option disabled>--------</option>
										</c:if>

										<option <%= (envLFRType.getListTypeId() == envLFR) ? "selected" : "" %> value="<%= envLFRType.getListTypeId() %>"><%= LanguageUtil.get(request, envLFRType.getName()) %><%= limited ? " (" + LanguageUtil.get(request, "limited") + ")" : StringPool.BLANK %></option>

									<%
										previousNamePrefix = namePrefix;
									}
									%>

								</select>
							</c:when>
							<c:otherwise>
								<%= LanguageUtil.get(request, accountEnvironment.getEnvLFRLabel()) %>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>

			<div class="aui-w33 content-column">
				<div class="content-column-content env">
					<span class="field-label txt-b" title="<liferay-ui:message key="operating-system" />"><%= edit ? "*" : "" %><liferay-ui:message key="os" />:</span>

					<c:choose>
						<c:when test="<%= edit %>">
							<select data-field-required-status="<%= false %>" field-required-message="<%= LanguageUtil.format(request, "invalid-value-provided-for-x", "operating-system") %>" id="<portlet:namespace />envOS" name="<portlet:namespace />envOS" onChange="<portlet:namespace />selectEnvOS(this.value);">
							</select>

							<br />

							<input class="<%= (envOS == TicketEntryConstants.ENV_OS_OTHER) ? "" : "aui-helper-hidden" %>" id="<portlet:namespace />envOSCustom" maxLength="<%= TicketInformationConstants.getMaxLength(TicketInformationConstants.FIELD_ENV_OS_CUSTOM) %>" name="<portlet:namespace />envOSCustom" type="text" value="<%= HtmlUtil.escapeAttribute(envOSCustom) %>" />
						</c:when>
						<c:otherwise>
							<%= LanguageUtil.get(request, accountEnvironment.getEnvOSLabel()) %>
						</c:otherwise>
					</c:choose>
				</div>
			</div>

			<div class="aui-w33 content-column">
				<div class="content-column-content env">
					<span class="field-label txt-b" title="<liferay-ui:message key="java-virtual-machine" />"><%= edit ? "*" : "" %><liferay-ui:message key="jvm" />:</span>

					<c:choose>
						<c:when test="<%= edit %>">
							<select data-field-required-status="<%= false %>" field-required-message="<%= LanguageUtil.format(request, "invalid-value-provided-for-x", "jvm") %>" id="<portlet:namespace />envJVM" name="<portlet:namespace />envJVM">
							</select>
						</c:when>
						<c:otherwise>
							<%= LanguageUtil.get(request, accountEnvironment.getEnvJVMLabel()) %>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>

		<div class="fl single-line">
			<div class="aui-w33 content-column">
				<div class="content-column-content env">
					<span class="field-label txt-b" title="<liferay-ui:message key="application-server" />"><%= edit ? "*" : "" %><liferay-ui:message key="as" />:</span>
					<c:choose>
						<c:when test="<%= edit %>">
							<select data-field-required-status="<%= false %>" field-required-message="<%= LanguageUtil.format(request, "invalid-value-provided-for-x", "application-server") %>" id="<portlet:namespace />envAS" name="<portlet:namespace />envAS">
							</select>
						</c:when>
						<c:otherwise>
							<%= LanguageUtil.get(request, accountEnvironment.getEnvASLabel()) %>
						</c:otherwise>
					</c:choose>
				</div>
			</div>

			<div class="aui-w33 content-column">
				<div class="content-column-content env">
					<span class="field-label txt-b" title="<liferay-ui:message key="database" />"><%= edit ? "*" : "" %><liferay-ui:message key="db" />:</span>

					<c:choose>
						<c:when test="<%= edit %>">
							<select data-field-required-status="<%= false %>" field-required-message="<%= LanguageUtil.format(request, "invalid-value-provided-for-x", "database") %>" id="<portlet:namespace />envDB" name="<portlet:namespace />envDB">
							</select>
						</c:when>
						<c:otherwise>
							<%= LanguageUtil.get(request, accountEnvironment.getEnvDBLabel()) %>
						</c:otherwise>
					</c:choose>
				</div>
			</div>

			<c:if test="<%= edit %>">
				<div class="aui-w33 content-column">
					<div class="content-column-content env">
						<span class="field-label txt-b" title="<liferay-ui:message key="browser" />"><liferay-ui:message key="br" />:</span>

						<select id="<portlet:namespace />envBrowser" name="<portlet:namespace />envBrowser" onChange="<portlet:namespace />selectBrowser(this.value);">
							<c:if test="<%= accountEnvironment != null %>">
								<option value="0"></option>

								<%
								List<ListType> envBrowserTypes = SupportUtil.getPortalEnvListTypes(accountEnvironment.getEnvLFR(), TicketEntryConstants.LIST_TYPE_ENV_BROWSER);

								for (ListType envBrowserType : envBrowserTypes) {
								%>

									<option <%= (envBrowser == envBrowserType.getListTypeId()) ? "selected" : "" %> value="<%= envBrowserType.getListTypeId() %>"><%= LanguageUtil.get(request, envBrowserType.getName()) %></option>

								<%
								}
								%>

							</c:if>
						</select>

						<br />

						<input class="<%= (envBrowser == TicketEntryConstants.ENV_BROWSER_OTHER) ? "" : "aui-helper-hidden" %>" id="<portlet:namespace />envBrowserCustom" maxLength="<%= TicketInformationConstants.getMaxLength(TicketInformationConstants.FIELD_ENV_BROWSER_CUSTOM) %>" name="<portlet:namespace />envBrowserCustom" type="text" value="<%= HtmlUtil.escapeAttribute(envBrowserCustom) %>" />
					</div>
				</div>
			</c:if>
		</div>

		<c:choose>
			<c:when test="<%= edit %>">
				<div class="fl single-line">
					<div class="aui-w33 content-column">
						<div class="aui-helper-hidden content-column-content env" id="<portlet:namespace />envCSColumn">
							<span class="field-label txt-b" title="<liferay-ui:message key="cloud-services" />"><liferay-ui:message key="cs" />:</span>

							<select id="<portlet:namespace />envCS" name="<portlet:namespace />envCS">
							</select>
						</div>
					</div>

					<div class="aui-w66 content-column">
						<div class="aui-helper-hidden content-column-content env" id="<portlet:namespace />envSearchColumn">
							<span class="field-label txt-b" title="<liferay-ui:message key="search" />">
								<liferay-ui:message key="sh" />:

								<liferay-ui:icon-help message="you-may-select-multiple-options" />
							</span>

							<select id="<portlet:namespace />envSearch" name="<portlet:namespace />envSearch" multiple>
							</select>
						</div>
					</div>
				</div>

				<br />

				<div class="aui-helper-hidden portlet-msg-error" id="<portlet:namespace />portalExtMessage"></div>

				<div class="aui-helper-clearfix">
					<liferay-util:include page="/support/2/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
						<liferay-util:param name="accountEnvironmentAttachmentType" value="<%= String.valueOf(AccountEnvironmentAttachmentConstants.TYPE_PORTAL_EXT) %>" />
						<liferay-util:param name="accountEnvironmentId" value="<%= String.valueOf(accountEnvironmentId) %>" />
						<liferay-util:param name="confirm" value="<%= String.valueOf(Boolean.TRUE) %>" />
						<liferay-util:param name="confirmCheckbox" value="<%= portalExtCheckbox %>" />
						<liferay-util:param name="kBaseArticleId" value="33142855" />
						<liferay-util:param name="label" value="portal-ext" />
						<liferay-util:param name="required" value="<%= String.valueOf(Boolean.TRUE) %>" />
						<liferay-util:param name="ticketAttachmentId" value="<%= String.valueOf(portalExtTicketAttachmentId) %>" />
						<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_PORTAL_EXT) %>" />
					</liferay-util:include>
				</div>

				<div class="aui-helper-hidden portlet-msg-error" id="<portlet:namespace />patchLevelMessage"></div>

				<div class="aui-helper-clearfix">
					<liferay-util:include page="/support/2/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
						<liferay-util:param name="accountEnvironmentAttachmentType" value="<%= String.valueOf(AccountEnvironmentAttachmentConstants.TYPE_PATCH_LEVEL) %>" />
						<liferay-util:param name="accountEnvironmentId" value="<%= String.valueOf(accountEnvironmentId) %>" />
						<liferay-util:param name="confirm" value="<%= String.valueOf(Boolean.TRUE) %>" />
						<liferay-util:param name="confirmCheckbox" value="<%= patchLevelCheckbox %>" />
						<liferay-util:param name="kBaseArticleId" value="33142925" />
						<liferay-util:param name="label" value="patch-level" />
						<liferay-util:param name="required" value="<%= String.valueOf(Boolean.TRUE) %>" />
						<liferay-util:param name="ticketAttachmentId" value="<%= String.valueOf(patchLevelTicketAttachmentId) %>" />
						<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_PATCH_LEVEL) %>" />
					</liferay-util:include>
				</div>
			</c:when>
			<c:otherwise>
				<div class="fl single-line">
					<span class="field-label txt-b"><liferay-ui:message key="portal-ext" />:</span>

					<span id="<portlet:namespace />portalExtFilename">

						<%
						AccountEnvironmentAttachment portalExtAccountEnvironmentAttachment = AccountEnvironmentAttachmentLocalServiceUtil.fetchAccountEnvironmentAttachment(accountEnvironmentId, AccountEnvironmentAttachmentConstants.TYPE_PORTAL_EXT);
						%>

						<c:if test="<%= portalExtAccountEnvironmentAttachment != null %>">

							<%
							LiferayPortletURL accountEnvironmentAttachmentURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

							accountEnvironmentAttachmentURL.setCopyCurrentRenderParameters(false);
							accountEnvironmentAttachmentURL.setParameter("accountEnvironmentAttachmentId", String.valueOf(portalExtAccountEnvironmentAttachment.getAccountEnvironmentAttachmentId()));
							accountEnvironmentAttachmentURL.setResourceID("accountEnvironmentAttachment");
							%>

							<a href="<%= accountEnvironmentAttachmentURL.toString() %>" target="_blank"><%= HtmlUtil.escape(portalExtAccountEnvironmentAttachment.getFileName()) %></a>
						</c:if>
					</span>
				</div>

				<div class="fl single-line">
					<span class="field-label txt-b"><liferay-ui:message key="patch-level" />:</span>

					<span id="<portlet:namespace />patchLevelFilename">

						<%
						AccountEnvironmentAttachment patchLevelAccountEnvironmentAttachment = AccountEnvironmentAttachmentLocalServiceUtil.fetchAccountEnvironmentAttachment(accountEnvironmentId, AccountEnvironmentAttachmentConstants.TYPE_PATCH_LEVEL);
						%>

						<c:if test="<%= patchLevelAccountEnvironmentAttachment != null %>">

							<%
							LiferayPortletURL accountEnvironmentAttachmentURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

							accountEnvironmentAttachmentURL.setCopyCurrentRenderParameters(false);
							accountEnvironmentAttachmentURL.setParameter("accountEnvironmentAttachmentId", String.valueOf(patchLevelAccountEnvironmentAttachment.getAccountEnvironmentAttachmentId()));
							accountEnvironmentAttachmentURL.setResourceID("accountEnvironmentAttachment");
							%>

							<a href="<%= accountEnvironmentAttachmentURL.toString() %>" target="_blank"><%= HtmlUtil.escape(patchLevelAccountEnvironmentAttachment.getFileName()) %></a>
						</c:if>
					</span>
				</div>
			</c:otherwise>
		</c:choose>

		<c:if test="<%= edit %>">
			<input id="<portlet:namespace />name" type="hidden" value="<%= (accountEnvironment != null) ? HtmlUtil.escapeAttribute(accountEnvironment.getName()) : "" %>" />
			<input id="<portlet:namespace />productEntryId" type="hidden" value="<%= productEntry.getProductEntryId() %>" />

			<div class="create-env-button">
				<input class="aui-button-input fl" id="<portlet:namespace />createEnvironment" onClick="<portlet:namespace />updateAccountEnvironment(0, '');" type="button" value="<liferay-ui:message key="create-environment" />" />

				<input class="aui-button-input aui-helper-hidden fl" id="<portlet:namespace />updateEnvironment" onClick="<portlet:namespace />updateAccountEnvironment(document.getElementById('<portlet:namespace />accountEnvironmentId').value, document.getElementById('<portlet:namespace />name').value);" type="button" value="<liferay-ui:message key="update-environment" />" />
			</div>
		</c:if>
	</div>
</div>

<c:if test="<%= edit %>">
	<%@ include file="/support/2/javascript/details_environment_js.jspf" %>
</c:if>