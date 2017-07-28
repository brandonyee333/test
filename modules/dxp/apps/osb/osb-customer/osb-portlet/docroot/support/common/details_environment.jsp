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

<%@ include file="/support/init.jsp" %>

<%
TicketEntry ticketEntry = (TicketEntry)request.getAttribute(OSBWebKeys.OSB_TICKET_ENTRY);
ProductEntry productEntry = (ProductEntry)request.getAttribute("edit_ticket_entry.jsp-productEntry");

if (productEntry == null) {
	productEntry = (ProductEntry)request.getAttribute("add_ticket_entry.jsp-productEntry");
}

long accountEnvironmentId = ParamUtil.getLong(request, "accountEnvironmentId");

AccountEnvironment accountEnvironment = null;

if (accountEnvironmentId > 0) {
	accountEnvironment = AccountEnvironmentServiceUtil.getAccountEnvironment(accountEnvironmentId);
}

long offeringEntryId = ParamUtil.getLong(request, "offeringEntryId");

OfferingEntry offeringEntry = OfferingEntryLocalServiceUtil.getOfferingEntry(offeringEntryId);

int component = BeanParamUtil.getInteger(ticketEntry, request, "component");
boolean edit = ParamUtil.getBoolean(request, "edit");

Map<Long, String> ticketInformationFieldsMap = new HashMap<Long, String>();

if (ticketEntry != null) {
	ticketInformationFieldsMap = ticketEntry.getTicketInformationFieldsMap();
}

String envName = ParamUtil.getString(request, "envName", GetterUtil.getString(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_ENV_NAME)));
int envOS = ParamUtil.getInteger(request, "envOS", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_ENV_OS)));
String envOSCustom = ParamUtil.getString(request, "envOSCustom", GetterUtil.getString(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_ENV_OS_CUSTOM)));
int envDB = ParamUtil.getInteger(request, "envDB", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_ENV_DB)));
int envJVM = ParamUtil.getInteger(request, "envJVM", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_ENV_JVM)));
int envAS = ParamUtil.getInteger(request, "envAS", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_ENV_AS)));
int envLFR = BeanParamUtil.getInteger(accountEnvironment, request, "envLFR", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_ENV_LFR)));
int envBrowser = ParamUtil.getInteger(request, "envBrowser", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_ENV_BROWSER)));
String envBrowserCustom = ParamUtil.getString(request, "envBrowserCustom", GetterUtil.getString(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_ENV_BROWSER_CUSTOM)));
int toEnvLFR = ParamUtil.getInteger(request, "toEnvLFR", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_UPGRADE_TO_ENV_LFR)));
int envCS = ParamUtil.getInteger(request, "envCS", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_ENV_CS)));
String envSearch = ParamUtil.getString(request, "envSearch", GetterUtil.getString(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_ENV_SEARCH)));
%>

<div class="callout-a">
	<div class="aui-helper-clearfix callout-content">

		<%
		int envLFRNotification = 0;

		if (component == TicketEntryConstants.COMPONENT_UPGRADE) {
			envLFRNotification = toEnvLFR;
		}
		else {
			envLFRNotification = envLFR;
		}
		%>

		<liferay-util:include page="/support/common/eosl_environment_liferay.jsp" servletContext="<%= application %>">
			<portlet:param name="envLFR" value="<%= String.valueOf(envLFRNotification) %>" />
		</liferay-util:include>

		<div class="aui-w100 content-column">
			<div class="content-column-content">
				<c:choose>
					<c:when test="<%= accountEnvironment != null %>">
						<span class="txt-b txt-up"><liferay-ui:message key="name" />:</span>

						<%= HtmlUtil.escape(accountEnvironment.getName()) %>

						<input name="<portlet:namespace />envName" type="hidden" value="<%= HtmlUtil.escapeAttribute(accountEnvironment.getName()) %>" />
					</c:when>
					<c:when test="<%= edit %>">
						<span class="txt-b txt-up"><liferay-ui:message key="name" />:</span>

						<input maxLength="<%= ModelHintsUtil.getMaxLength(TicketEntry.class.getName(), "envName") %>" name="<portlet:namespace />envName" type="text" value="<%= HtmlUtil.escapeAttribute(envName) %>" />
					</c:when>
					<c:otherwise>
						<c:if test="<%= Validator.isNotNull(envName) %>">
							<span class="txt-b txt-up"><liferay-ui:message key="name" />:</span>

							<%= HtmlUtil.escape(envName) %>
						</c:if>

						<input name="<portlet:namespace />envName" type="hidden" value="<%= HtmlUtil.escapeAttribute(envName) %>" />
					</c:otherwise>
				</c:choose>
			</div>
		</div>

		<div class="content-column single-line">
			<div class="content-column-content left-column">

				<%
				String envLFRTitle = "liferay-version";

				if (component == TicketEntryConstants.COMPONENT_UPGRADE) {
					envLFRTitle = "upgrading-from-liferay-version";
				}
				else if (productEntry.isSocialOffice()) {
					envLFRTitle = "social-office-version";
				}
				%>

				<span class="txt-b txt-up"><%= edit ? "*" : "" %><liferay-ui:message key="<%= envLFRTitle %>" />:</span>

				<c:choose>
					<c:when test="<%= edit %>">

						<%
						StringBundler onChangeSB = new StringBundler();

						if (component == TicketEntryConstants.COMPONENT_UPGRADE) {
							onChangeSB.append(renderResponse.getNamespace());
							onChangeSB.append("getLaterLFRVersions(this.value);");
						}

						onChangeSB.append(renderResponse.getNamespace());

						if (component == TicketEntryConstants.COMPONENT_UPGRADE) {
							onChangeSB.append("updateSupportMessage(toEnvLFR.value);");
						}
						else {
							onChangeSB.append("updateSupportMessage(this.value);");
						}

						onChangeSB.append(renderResponse.getNamespace());
						onChangeSB.append("selectPortalVersion(this.value, 0, '', 0, '', 0, '', 0, '', 0, '', 0, '', '');");

						List<ListType> envLFRTypes = productEntry.getAllVersionsListTypes();

						String previousNamePrefix = StringPool.BLANK;
						%>

						<select id="<portlet:namespace />envLFR" name="<portlet:namespace />envLFR" onChange="<%= onChangeSB.toString() %>">
							<option value="0"></option>

							<%
							for (ListType envLFRType : envLFRTypes) {
								if (((envLFRType.getListTypeId() == ProductEntryConstants.PORTAL_VERSION_OTHER) && (envLFR != ProductEntryConstants.PORTAL_VERSION_OTHER)) || ArrayUtil.contains(ProductEntryConstants.LIST_TYPES_DEPRECATED, envLFRType.getListTypeId())) {
									continue;
								}

								String name = envLFRType.getName();

								String namePrefix = name.substring(0, 3);
							%>

								<c:if test="<%= Validator.isNotNull(previousNamePrefix) && !previousNamePrefix.equals(namePrefix) %>">
									<option disabled>--------</option>
								</c:if>

								<option <%= (envLFRType.getListTypeId() == envLFR) ? "selected" : "" %> value="<%= envLFRType.getListTypeId() %>"><%= LanguageUtil.get(pageContext, envLFRType.getName()) %></option>

							<%
								previousNamePrefix = namePrefix;
							}
							%>

						</select>

						<c:if test="<%= component == TicketEntryConstants.COMPONENT_UPGRADE %>">
							<span class="txt-b"><liferay-ui:message key="to" />:</span>

							<select id="<portlet:namespace />toEnvLFR" name="<portlet:namespace />toEnvLFR" onChange="<portlet:namespace />selectPortalVersion(this.value, 0, '', 0, '', 0, '', 0, '', 0, '', 0, '', ''); <portlet:namespace />updateSupportMessage(this.value);">
								<option value="0"></option>

								<%
								previousNamePrefix = StringPool.BLANK;

								for (ListType envLFRType : envLFRTypes) {
									if ((envLFRType.getListTypeId() == ProductEntryConstants.PORTAL_VERSION_OTHER) || ArrayUtil.contains(ProductEntryConstants.LIST_TYPES_DEPRECATED, envLFRType.getListTypeId())) {
										continue;
									}

									String name = envLFRType.getName();

									String namePrefix = StringPool.BLANK;

									if (envLFRType.getListTypeId() > envLFR) {
										namePrefix = name.substring(0, 3);
									}
								%>

									<c:if test="<%= envLFRType.getListTypeId() > envLFR %>">
										<c:if test="<%= Validator.isNotNull(previousNamePrefix) && !previousNamePrefix.equals(namePrefix) %>">
											<option disabled>--------</option>
										</c:if>

										<option <%= (envLFRType.getListTypeId() == toEnvLFR) ? "selected" : "" %> value="<%= envLFRType.getListTypeId() %>"><%= LanguageUtil.get(pageContext, envLFRType.getName()) %></option>
									</c:if>

								<%
									previousNamePrefix = namePrefix;
								}
								%>

							</select>
						</c:if>
					</c:when>
					<c:otherwise>
						<%= LanguageUtil.get(pageContext, TicketEntryConstants.getEnvLabel(envLFR)) %>

						<input name="<portlet:namespace />envLFR" type="hidden" value="<%= envLFR %>" />

						<%
						String supportPhaseLabel = StringPool.BLANK;

						if (toEnvLFR > 0) {
							supportPhaseLabel = ProductEntryConstants.getSupportPhaseLabel(toEnvLFR);
						}
						else if (envLFR > 0) {
							supportPhaseLabel = ProductEntryConstants.getSupportPhaseLabel(envLFR);
						}
						%>

						<c:if test="<%= component == TicketEntryConstants.COMPONENT_UPGRADE %>">
							<span class="txt-b"><liferay-ui:message key="to" />:</span>

							<%= LanguageUtil.get(pageContext, TicketEntryConstants.getEnvLabel(toEnvLFR)) %>

							<input name="<portlet:namespace />toEnvLFR" type="hidden" value="<%= toEnvLFR %>" />
						</c:if>

						<c:if test="<%= Validator.isNotNull(supportPhaseLabel) %>">
							<span class="support-phase-label">(<%= LanguageUtil.get(pageContext, supportPhaseLabel) %>)</span>
						</c:if>
					</c:otherwise>
				</c:choose>
			</div>
		</div>

		<div class="aui-w50 content-column">
			<div class="content-column-content left-column">
				<div class="single-line">
					<span class="txt-b txt-up"><%= edit ? "*" : "" %><liferay-ui:message key="operating-system" />:</span>

					<span>
						<c:choose>
							<c:when test="<%= edit %>">
								<select id="<portlet:namespace />envOS" name="<portlet:namespace />envOS" onChange="<portlet:namespace />selectEnvOS(this.value);">
								</select>

								<br />

								<input class="<%= (envOS == TicketEntryConstants.ENV_OS_OTHER) ? "" : "aui-helper-hidden" %>" id="<portlet:namespace />envOSCustom" maxLength="<%= TicketInformationConstants.getMaxLength(TicketInformationConstants.FIELD_ENV_OS_CUSTOM) %>" name="<portlet:namespace />envOSCustom" type="text" value="<%= HtmlUtil.escapeAttribute(envOSCustom) %>" />
							</c:when>
							<c:otherwise>
								<%= LanguageUtil.get(pageContext, TicketEntryConstants.getEnvLabel(envOS)) %>

								<c:if test="<%= Validator.isNotNull(envOSCustom) %>">
									- <%= HtmlUtil.escape(envOSCustom) %>
								</c:if>

								<input name="<portlet:namespace />envOS" type="hidden" value="<%= envOS %>" />
								<input name="<portlet:namespace />envOSCustom" type="hidden" value="<%= HtmlUtil.escapeAttribute(envOSCustom) %>" />
							</c:otherwise>
						</c:choose>
					<span>
				</div>

				<div class="single-line">
					<span class="txt-b txt-up"><%= edit ? "*" : "" %><liferay-ui:message key="application-server" />:</span>

					<c:choose>
						<c:when test="<%= edit %>">
							<select id="<portlet:namespace />envAS" name="<portlet:namespace />envAS">
							</select>
						</c:when>
						<c:otherwise>
							<%= LanguageUtil.get(pageContext, TicketEntryConstants.getEnvLabel(envAS)) %>

							<input name="<portlet:namespace />envAS" type="hidden" value="<%= envAS %>" />
						</c:otherwise>
					</c:choose>
				</div>

				<div class="single-line">
					<span class="txt-b txt-up"><%= edit ? "*" : "" %><liferay-ui:message key="database" />:</span>

					<c:choose>
						<c:when test="<%= edit %>">
							<select id="<portlet:namespace />envDB" name="<portlet:namespace />envDB">
							</select>
						</c:when>
						<c:otherwise>
							<%= LanguageUtil.get(pageContext, TicketEntryConstants.getEnvLabel(envDB)) %>

							<input name="<portlet:namespace />envDB" type="hidden" value="<%= envDB %>" />
						</c:otherwise>
					</c:choose>
				</div>

				<div class="single-line" id="<portlet:namespace />envCSColumn">
					<c:choose>
						<c:when test="<%= edit %>">
							<span class="txt-b txt-up"><liferay-ui:message key="cloud-services" />:</span>

							<select id="<portlet:namespace />envCS" name="<portlet:namespace />envCS">
							</select>
						</c:when>
						<c:otherwise>
							<c:if test="<%= envCS != 0 %>">
								<span class="txt-b txt-up"><liferay-ui:message key="cloud-services" />:</span>

								<%= LanguageUtil.get(pageContext, TicketEntryConstants.getEnvLabel(envCS)) %>
							</c:if>

							<input name="<portlet:namespace />envCS" type="hidden" value="<%= envCS %>" />
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>

		<div class="aui-w50 content-column">
			<div class="content-column-content right-column">
				<div class="single-line">
					<span class="txt-b txt-up"><%= edit ? "*" : "" %><liferay-ui:message key="primary-browser" />:</span>

					<span>
						<c:choose>
							<c:when test="<%= edit %>">
								<select id="<portlet:namespace />envBrowser" name="<portlet:namespace />envBrowser" onChange="<portlet:namespace />selectBrowser(this.value);">
									<c:if test="<%= accountEnvironment != null %>">
										<option value="0"></option>

										<%
										List<ListType> envBrowserTypes = SupportUtil.getPortalEnvListTypes(accountEnvironment.getEnvLFR(), TicketEntryConstants.LIST_TYPE_ENV_BROWSER);

										for (ListType envBrowserType : envBrowserTypes) {
										%>

											<option <%= (envBrowser == envBrowserType.getListTypeId()) ? "selected" : "" %> value="<%= envBrowserType.getListTypeId() %>"><%= LanguageUtil.get(pageContext, envBrowserType.getName()) %></option>

										<%
										}
										%>

									</c:if>
								</select>

								<br />

								<input class="<%= (envBrowser == TicketEntryConstants.ENV_BROWSER_OTHER) ? "" : "aui-helper-hidden" %>" id="<portlet:namespace />envBrowserCustom" maxLength="<%= TicketInformationConstants.getMaxLength(TicketInformationConstants.FIELD_ENV_BROWSER_CUSTOM) %>" name="<portlet:namespace />envBrowserCustom" type="text" value="<%= HtmlUtil.escapeAttribute(envBrowserCustom) %>" />
							</c:when>
							<c:otherwise>
								<liferay-ui:message key="<%= TicketEntryConstants.getEnvLabel(envBrowser) %>" />

								<c:if test="<%= envBrowser == TicketEntryConstants.ENV_BROWSER_OTHER %>">
									- <%= HtmlUtil.escape(envBrowserCustom) %>
								</c:if>

								<input name="<portlet:namespace />envBrowser" type="hidden" value="<%= envBrowser %>" />
								<input name="<portlet:namespace />envBrowserCustom" type="hidden" value="<%= HtmlUtil.escapeAttribute(envBrowserCustom) %>" />
							</c:otherwise>
						</c:choose>
					</span>
				</div>

				<div class="single-line">
					<span class="txt-b txt-up"><liferay-ui:message key="java-virtual-machine" />:</span>

					<c:choose>
						<c:when test="<%= edit %>">
							<select id="<portlet:namespace />envJVM" name="<portlet:namespace />envJVM">
							</select>
						</c:when>
						<c:otherwise>
							<%= LanguageUtil.get(pageContext, TicketEntryConstants.getEnvLabel(envJVM)) %>

							<input name="<portlet:namespace />envJVM" type="hidden" value="<%= envJVM %>" />
						</c:otherwise>
					</c:choose>
				</div>

				<div class="single-line" id="<portlet:namespace />envSearchColumn">
					<c:choose>
						<c:when test="<%= edit %>">
							<span class="txt-b txt-up"><liferay-ui:message key="search" />:</span>

							<select id="<portlet:namespace />envSearch" name="<portlet:namespace />envSearch" multiple>
							</select>
						</c:when>
						<c:otherwise>
							<c:if test="<%= Validator.isNotNull(envSearch) %>">
								<span class="txt-b txt-up"><liferay-ui:message key="search" />:</span>

								<%
								int[] envSearchArray = StringUtil.split(envSearch, StringPool.NEW_LINE, 0);

								for (int curEnvSearch : envSearchArray) {
								%>

									<%= LanguageUtil.get(pageContext, TicketEntryConstants.getEnvLabel(curEnvSearch)) %>

								<%
								}
								%>

							</c:if>

							<input name="<portlet:namespace />envSearch" type="hidden" value="<%= HtmlUtil.escapeAttribute(envSearch) %>" />
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="/support/common/javascript/details_environment_js.jspf" %>