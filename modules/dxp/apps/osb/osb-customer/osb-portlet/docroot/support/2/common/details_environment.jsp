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

<div class="clearfix">
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
					<aui:input name="envName" type="hidden" value="<%= envName %>" />
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

								<aui:select data-field-required-status="<%= false %>" field-required-message='<%= LanguageUtil.format(request, "invalid-value-provided-for-x", "liferay-version") %>' name="<%= envLFRName %>" onChange="<%= envLFROnChange %>">
									<aui:option value="0" />

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
											<aui:option disabled="<%= true %>" label="--------" />
										</c:if>

										<aui:option label='<%= envLFRType.getName() + (limited ? "(limited)" : StringPool.BLANK) %>' selected="<%= envLFRType.getListTypeId() == envLFR %>" value="<%= envLFRType.getListTypeId() %>" />

									<%
										previousNamePrefix = namePrefix;
									}
									%>

								</aui:select>
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
							<aui:select data-field-required-status="<%= false %>" field-required-message='<%= LanguageUtil.format(request, "invalid-value-provided-for-x", "operating-system") %>' name="envOS" onChange='<%= renderResponse.getNamespace() + "selectEnvOS(this.value);" %>' />

							<br />

							<aui:input cssClass='<%= (envOS == TicketEntryConstants.ENV_OS_OTHER) ? "" : "hide" %>' maxLength="<%= TicketInformationConstants.getMaxLength(TicketInformationConstants.FIELD_ENV_OS_CUSTOM) %>" name="envOSCustom" type="text" value="<%= envOSCustom %>" />
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
							<aui:select data-field-required-status="<%= false %>" field-required-message='<%= LanguageUtil.format(request, "invalid-value-provided-for-x", "jvm") %>' name="envJVM" />
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
							<aui:select data-field-required-status="<%= false %>" field-required-message='<%= LanguageUtil.format(request, "invalid-value-provided-for-x", "application-server") %>' name="envAS" />
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
							<aui:select data-field-required-status="<%= false %>" field-required-message='<%= LanguageUtil.format(request, "invalid-value-provided-for-x", "database") %>' name="envDB" />
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

						<aui:select name="envBrowser" onChange='<%= renderResponse.getNamespace() + "selectBrowser(this.value);" %>'>
							<c:if test="<%= accountEnvironment != null %>">
								<aui:option value="0" />

								<%
								List<ListType> envBrowserTypes = SupportUtil.getPortalEnvListTypes(accountEnvironment.getEnvLFR(), TicketEntryConstants.LIST_TYPE_ENV_BROWSER);

								for (ListType envBrowserType : envBrowserTypes) {
								%>

									<aui:option label="<%= envBrowserType.getName() %>" selected="<%= envBrowser == envBrowserType.getListTypeId() %>" value="<%= envBrowserType.getListTypeId() %>" />

								<%
								}
								%>

							</c:if>
						</aui:select>

						<br />

						<aui:input cssClass='<%= (envBrowser == TicketEntryConstants.ENV_BROWSER_OTHER) ? "" : "hide" %>' maxLength="<%= TicketInformationConstants.getMaxLength(TicketInformationConstants.FIELD_ENV_BROWSER_CUSTOM) %>" name="envBrowserCustom" type="text" value="<%= envBrowserCustom %>" />
					</div>
				</div>
			</c:if>
		</div>

		<c:choose>
			<c:when test="<%= edit %>">
				<div class="fl single-line">
					<div class="aui-w33 content-column">
						<div class="content-column-content env hide" id="<portlet:namespace />envCSColumn">
							<span class="field-label txt-b" title="<liferay-ui:message key="cloud-services" />"><liferay-ui:message key="cs" />:</span>

							<aui:select name="envCS" />
						</div>
					</div>

					<div class="aui-w66 content-column">
						<div class="content-column-content env hide" id="<portlet:namespace />envSearchColumn">
							<span class="field-label txt-b" title="<liferay-ui:message key="search" />">
								<liferay-ui:message key="sh" />:

								<liferay-ui:icon-help message="you-may-select-multiple-options" />
							</span>

							<aui:select multiple="<%= true %>" name="envSearch" />
						</div>
					</div>
				</div>

				<br />

				<div class="hide portlet-msg-error" id="<portlet:namespace />portalExtMessage"></div>

				<div class="clearfix">
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

				<div class="hide portlet-msg-error" id="<portlet:namespace />patchLevelMessage"></div>

				<div class="clearfix">
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
			<aui:input name="name" type="hidden" value='<%= (accountEnvironment != null) ? accountEnvironment.getName() : "" %>' />
			<aui:input name="productEntryId" type="hidden" value="<%= productEntry.getProductEntryId() %>" />

			<div class="create-env-button">
				<aui:button cssClass="aui-button-input fl" onClick='<%= renderResponse.getNamespace() + "updateAccountEnvironment(0, '');" %>' value="create-environment" />

				<%
				String updateEnvironmentOnClick = renderResponse.getNamespace() + "updateAccountEnvironment(document.getElementById('" + renderResponse.getNamespace() + "accountEnvironmentId').value, document.getElementById('" + renderResponse.getNamespace() + "name').value);";
				%>

				<aui:button cssClass="aui-button-input fl hide" onClick="<%= updateEnvironmentOnClick %>" value="update-environment" />
			</div>
		</c:if>
	</div>
</div>

<c:if test="<%= edit %>">
	<%@ include file="/support/2/javascript/details_environment_js.jspf" %>
</c:if>