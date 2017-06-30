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
String cmd = ParamUtil.getString(request, Constants.CMD, Constants.UPDATE);

String tabs1 = ParamUtil.getString(request, "tabs1", "captcha");
String tabs2 = ParamUtil.getString(request, "tabs2", "general");
String tabs3 = ParamUtil.getString(request, "tabs3");

String redirect = ParamUtil.getString(request, "redirect");

String currentLanguageId = LanguageUtil.getLanguageId(request);
Locale defaultLocale = LocaleUtil.getDefault();
String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

Locale[] locales = LanguageUtil.getAvailableLocales();
%>

<script type="text/javascript">
	function <portlet:namespace />updateForm() {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = '';
		submitForm(document.<portlet:namespace />fm);
	}
</script>

<liferay-portlet:renderURL portletConfiguration="true" var="portletURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
	<portlet:param name="tabs1" value="<%= tabs1 %>" />
	<portlet:param name="tabs2" value="<%= tabs2 %>" />
	<portlet:param name="tabs3" value="<%= tabs3 %>" />
	<portlet:param name="redirect" value="<%= redirect %>" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="tabs1" type="hidden" value="<%= tabs1 %>" />
	<aui:input name="tabs2" type="hidden" value="<%= tabs2 %>" />
	<aui:input name="tabs3" type="hidden" value="<%= tabs3 %>" />

	<liferay-ui:error key="announcementDateInvalid" message="please-enter-a-display-date-that-comes-before-the-expiration-date" />
	<liferay-ui:error key="componentLinkInvalid" message="please-enter-a-valid-component-link" />
	<liferay-ui:error key="componentMessageLinkInvalid" message="please-enter-a-valid-component-message-link" />

	<liferay-ui:tabs
		names="captcha,email-notifications,rabbitmq,salesforce,support,trial"
		param="tabs1"
		url="<%= portletURL %>"
	/>

	<c:choose>
		<c:when test='<%= tabs1.equals("captcha") %>'>

			<%
			boolean showCaptcha = PrefsParamUtil.getBoolean(portletPreferences, request, "showCaptcha", true);
			boolean enableHoneypot = PrefsParamUtil.getBoolean(portletPreferences, request, "enableHoneypot", true);
			%>

			<aui:fieldset>
				<aui:input label="show-captcha-for-create-account-trial-page" name="showCaptcha" type="checkbox" value="<%= showCaptcha %>" />

				<aui:input label="enable-honeypot-for-create-account-pages" name="enableHoneypot" type="checkbox" value="<%= enableHoneypot %>" />
			</aui:fieldset>
		</c:when>
		<c:when test='<%= tabs1.equals("rabbitmq") %>'>
			<liferay-ui:error key="deadLetterFilterScriptCompile">
				<liferay-ui:message key="there-was-an-error-with-your-script" />

				<br /><br />

				<div class="portlet-msg">
					<%= StackTraceUtil.getStackTrace((Exception)errorException) %>
				</div>
			</liferay-ui:error>

			<liferay-ui:message arguments="<%= new Object[] {PortletPropsValues.RABBITMQ_MESSAGE_DEAD_LETTER_QUEUE_NAME} %>" key="this-groovy-script-will-run-daily-to-filter-dead-lettered-messages-on-the-x-queue" />

			<br /><br />

			<div class="definition-of-terms">
				<h4>
					<liferay-ui:message key="available-variables" />
				</h4>

				<dl>
					<dt>
						messageJSONObject
					</dt>
					<dd>
						Payload of RabbitMQ message as a JSON object
					</dd>
					<dt>
						routingKey
					</dt>
					<dd>
						The routing key of the RabbitMQ message
					</dd>
					<dt>
						properties
					</dt>
					<dd>
						The properties of the RabbitMQ message
					</dd>
				</dl>
			</div>

			<aui:fieldset>
				<aui:input cssClass="lfr-textarea-container" label="script" name="deadLetterFilterScript" type="textarea" value='<%= PrefsParamUtil.getString(portletPreferences, request, "deadLetterFilterScript", StringPool.BLANK) %>' />
			</aui:fieldset>
		</c:when>
		<c:when test='<%= tabs1.equals("salesforce") %>'>

			<%
			String annualLicenseSubscriptionSellers = StringUtil.merge(portletPreferences.getValues("annualLicenseSubscriptionSellers", new String[0]), StringPool.NEW_LINE);
			%>

			<aui:fieldset>
				<aui:input cssClass="lfr-textarea-container" label="" name="annualLicenseSubscriptionSellers" type="textarea" value="<%= annualLicenseSubscriptionSellers %>" />
			</aui:fieldset>
		</c:when>
		<c:when test='<%= tabs1.equals("support") %>'>
			<liferay-ui:tabs
				names="automatic-comments,ticket-weight,product-messages,component-messages,dxp-message,escalation-details,attachment-keywords,status-messages,tier-messages,assignment-ratio,file-repositories,banner,indexing,new-ui"
				param="tabs2"
				url="<%= portletURL %>"
			/>

			<c:choose>
				<c:when test='<%= tabs2.equals("assignment-ratio") %>'>
					<table class="lfr-table">
					<tr>
						<td>
							<liferay-ui:message key="support-region" />
						</td>
						<td>
							<select name="<portlet:namespace />supportRegionId" onChange="<portlet:namespace />updateForm();">

								<%
								List<SupportRegion> supportRegions = SupportRegionLocalServiceUtil.getSupportRegions(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

								SupportRegion firstSupportRegion = supportRegions.get(0);

								long supportRegionId = PrefsParamUtil.getLong(portletPreferences, request, "supportRegionId", firstSupportRegion.getSupportRegionId());

								for (SupportRegion supportRegion : supportRegions) {
								%>

									<option <%= (supportRegionId == supportRegion.getSupportRegionId()) ? "selected" : StringPool.BLANK %> value="<%= supportRegion.getSupportRegionId() %>"><%= supportRegion.getName() %></option>

								<%
								}
								%>

							</select>
						</td>
					</tr>
					<tr>
						<td>
							<liferay-ui:message key="ticket-logic-assignment-ratio" />
						</td>
						<td>
							<select name="<portlet:namespace />ratio">

								<%
								double ratio = GetterUtil.getDouble(portletPreferences.getValue(supportRegionId + "_assignmentRatio", "0.75"));

								for (int i = 0; i <= 20; i++) {
								%>

									<option <%= (ratio == ((((double)i) * 5) / 100)) ? "selected" : "" %> value="<%= (((double)i) * 5) / 100 %>"><%= i * 5 %>% <liferay-ui:message key="utilization" /> / <%= 100 - (i * 5) %>% <liferay-ui:message key="business-hours-left" /></option>

								<%
								}
								%>

							</select>
						</td>
					</tr>
					</table>
				</c:when>
				<c:when test='<%= tabs2.equals("attachment-keywords") %>'>
					<aui:fieldset>
						<aui:input cssClass="lfr-textarea-container" label="keywords" name="attachmentKeywords" type="textarea" value='<%= PrefsParamUtil.getString(portletPreferences, request, "attachmentKeywords", StringPool.BLANK) %>' />
					</aui:fieldset>
				</c:when>
				<c:when test='<%= tabs2.equals("banner") %>'>

					<%
					String announcementTitle = PrefsParamUtil.getString(portletPreferences, request, "announcementTitle_" + currentLanguageId, StringPool.BLANK);
					String announcementContent = PrefsParamUtil.getString(portletPreferences, request, "announcementContent_" + currentLanguageId, StringPool.BLANK);

					int announcementDisplayDateDay = ParamUtil.getInteger(request, "announcementDisplayDateDay");
					int announcementDisplayDateMonth = ParamUtil.getInteger(request, "announcementDisplayDateMonth", -1);
					int announcementDisplayDateYear = ParamUtil.getInteger(request, "announcementDisplayDateYear");
					int announcementDisplayDateHour = ParamUtil.getInteger(request, "announcementDisplayDateHour");
					int announcementDisplayDateMinute = ParamUtil.getInteger(request, "announcementDisplayDateMinute");
					int announcementDisplayDateAmPm = ParamUtil.getInteger(request, "announcementDisplayDatesAmPm");

					if (announcementDisplayDateAmPm == Calendar.PM) {
						announcementDisplayDateHour += 12;
					}

					Date announcementDisplayDate = PortalUtil.getDate(announcementDisplayDateMonth, announcementDisplayDateDay, announcementDisplayDateYear, announcementDisplayDateHour, announcementDisplayDateMinute, (Class<? extends PortalException>)null);

					Calendar announcementDisplayCal = Calendar.getInstance();

					if (announcementDisplayDate != null) {
						announcementDisplayCal.setTime(announcementDisplayDate);
					}
					else {
						long announcementDisplayDateTime = GetterUtil.getLong(portletPreferences.getValue("announcementDisplayDate", null));

						if (announcementDisplayDateTime > 0) {
							announcementDisplayCal.setTimeInMillis(announcementDisplayDateTime);
						}
					}

					int announcementExpirationDateDay = ParamUtil.getInteger(request, "announcementExpirationDateDay");
					int announcementExpirationDateMonth = ParamUtil.getInteger(request, "announcementExpirationDateMonth", -1);
					int announcementExpirationDateYear = ParamUtil.getInteger(request, "announcementExpirationDateYear");
					int announcementExpirationDateHour = ParamUtil.getInteger(request, "announcementExpirationDateHour");
					int announcementExpirationDateMinute = ParamUtil.getInteger(request, "announcementExpirationDateMinute");
					int announcementExpirationDateAmPm = ParamUtil.getInteger(request, "announcementExpirationDateAmPm");

					if (announcementExpirationDateAmPm == Calendar.PM) {
						announcementExpirationDateHour += 12;
					}

					Date announcementExpirationDate = PortalUtil.getDate(announcementExpirationDateMonth, announcementExpirationDateDay, announcementExpirationDateYear, announcementExpirationDateHour, announcementExpirationDateMinute, (Class<? extends PortalException>)null);

					Calendar announcementExpirationCal = Calendar.getInstance();

					if (announcementExpirationDate != null) {
						announcementExpirationCal.setTime(announcementExpirationDate);
					}
					else {
						long announcementExpirationDateTime = GetterUtil.getLong(portletPreferences.getValue("announcementExpirationDate", null));

						if (announcementExpirationDateTime > 0) {
							announcementExpirationCal.setTimeInMillis(announcementExpirationDateTime);
						}
					}
					%>

					<table class="lfr-table">
					<tr>
						<td>
							<liferay-ui:message key="language" />
						</td>
						<td>
							<select name="<portlet:namespace />languageId" onChange="<portlet:namespace />updateForm();">

								<%
								for (int i = 0; i < locales.length; i++) {
									String optionStyle = StringPool.BLANK;

									if (Validator.isNotNull(portletPreferences.getValue("announcementTitle_" + LocaleUtil.toLanguageId(locales[i]), StringPool.BLANK))) {
										optionStyle = "style=\"font-weight: bold;\"";
									}
								%>

									<option <%= currentLanguageId.equals(LocaleUtil.toLanguageId(locales[i])) ? "selected" : "" %> <%= optionStyle %> value="<%= LocaleUtil.toLanguageId(locales[i]) %>"><%= locales[i].getDisplayName(locale) %></option>

								<%
								}
								%>

							</select>
						</td>
					</tr>
					</table>

					<aui:fieldset>
						<aui:input cssClass="lfr-input-text-container" ignoreRequestValue="true" label="title" name="announcementTitle" value="<%= announcementTitle %>" />

						<aui:input cssClass="lfr-textarea-container" ignoreRequestValue="true" label="content" name="announcementContent" type="textarea" value="<%= announcementContent %>" />
					</aui:fieldset>

					<table class="lfr-table">
					<tr>
						<td>
							<liferay-ui:message key="display-date" /> (GMT)
						</td>
						<td>
							<liferay-ui:input-date
								dayNullable="<%= false %>"
								dayParam="announcementDisplayDateDay"
								dayValue="<%= announcementDisplayCal.get(Calendar.DAY_OF_MONTH) %>"
								monthNullable="<%= false %>"
								monthParam="announcementDisplayDateMonth"
								monthValue="<%= announcementDisplayCal.get(Calendar.MONTH) %>"
								yearNullable="<%= false %>"
								yearParam="announcementDisplayDateYear"
								yearRangeEnd="<%= announcementDisplayCal.get(Calendar.YEAR) + 2 %>"
								yearRangeStart="<%= announcementDisplayCal.get(Calendar.YEAR) - 2 %>"
								yearValue="<%= announcementDisplayCal.get(Calendar.YEAR) %>"
							/>

							<liferay-ui:input-time
								amPmParam="announcementDisplayDateAmPm"
								amPmValue="<%= announcementDisplayCal.get(Calendar.AM_PM) %>"
								hourParam="announcementDisplayDateHour"
								hourValue="<%= announcementDisplayCal.get(Calendar.HOUR) %>"
								minuteInterval="<%= 1 %>"
								minuteParam="announcementDisplayDateMinute"
								minuteValue="<%= announcementDisplayCal.get(Calendar.MINUTE) %>"
							/>
						</td>
					</tr>
					<tr>
						<td>
							<liferay-ui:message key="expiration-date" /> (GMT)
						</td>
						<td>
							<liferay-ui:input-date
								dayNullable="<%= false %>"
								dayParam="announcementExpirationDateDay"
								dayValue="<%= announcementExpirationCal.get(Calendar.DAY_OF_MONTH) %>"
								monthNullable="<%= false %>"
								monthParam="announcementExpirationDateMonth"
								monthValue="<%= announcementExpirationCal.get(Calendar.MONTH) %>"
								yearNullable="<%= false %>"
								yearParam="announcementExpirationDateYear"
								yearRangeEnd="<%= announcementExpirationCal.get(Calendar.YEAR) + 2 %>"
								yearRangeStart="<%= announcementExpirationCal.get(Calendar.YEAR) - 2 %>"
								yearValue="<%= announcementExpirationCal.get(Calendar.YEAR) %>"
							/>

							<liferay-ui:input-time
								amPmParam="announcementExpirationDateAmPm"
								amPmValue="<%= announcementExpirationCal.get(Calendar.AM_PM) %>"
								hourParam="announcementExpirationDateHour"
								hourValue="<%= announcementExpirationCal.get(Calendar.HOUR) %>"
								minuteInterval="<%= 1 %>"
								minuteParam="announcementExpirationDateMinute"
								minuteValue="<%= announcementExpirationCal.get(Calendar.MINUTE) %>"
							/>
						</td>
					</tr>
					</table>
				</c:when>
				<c:when test='<%= tabs2.equals("component-messages") %>'>

					<%
					String productDisplayName = ParamUtil.getString(request, "productDisplayName", ProductEntryConstants.DISPLAY_NAME_PORTAL);
					int component = ParamUtil.getInteger(request, "component", TicketEntryConstants.COMPONENT_PROJECT_ADMINISTRATION);
					%>

					<table class="lfr-table">
					<tr>
						<td>
							<liferay-ui:message key="language" />
						</td>
						<td>
							<select name="<portlet:namespace />languageId" onChange="<portlet:namespace />updateForm();">

								<%
								for (int i = 0; i < locales.length; i++) {
								%>

									<option <%= currentLanguageId.equals(LocaleUtil.toLanguageId(locales[i])) ? "selected" : "" %> value="<%= LocaleUtil.toLanguageId(locales[i]) %>"><%= locales[i].getDisplayName(locale) %></option>

								<%
								}
								%>

							</select>
						</td>
					</tr>
					<tr>
						<td>
							<liferay-ui:message key="product" />
						</td>
						<td>
							<select name="<portlet:namespace />productDisplayName" onChange="<portlet:namespace />updateForm();">

								<%
								for (String curProductDisplayName : ProductEntryConstants.DISPLAY_NAMES) {
								%>

									<option <%= (curProductDisplayName.equals(productDisplayName)) ? "selected" : "" %> value="<%= HtmlUtil.escape(curProductDisplayName) %>"><%= LanguageUtil.get(pageContext, curProductDisplayName) %></option>

								<%
								}
								%>

							</select>
						</td>
					</tr>
					<tr>
						<td>
							<liferay-ui:message key="component" />
						</td>
						<td>
							<select name="<portlet:namespace />component" onChange="<portlet:namespace />updateForm();">

								<%
								int[] components = TicketEntryConstants.getProductDisplayNameComponents(productDisplayName);

								if (!ArrayUtil.contains(components, component)) {
									component = components[0];
								}

								for (int curComponent : components) {
									if (ArrayUtil.contains(TicketEntryConstants.COMPONENTS_DEPRECATED, curComponent)) {
										continue;
									}

									String optionStyle = StringPool.BLANK;

									String componentMessageKey = AdminUtil.getComponentPreferenceKey("componentMessage_", productDisplayName, String.valueOf(curComponent), currentLanguageId);

									String componentMessage = portletPreferences.getValue(componentMessageKey, StringPool.BLANK);

									if (Validator.isNotNull(componentMessage)) {
										optionStyle = "style=\"font-weight: bold;\"";
									}
								%>

									<option <%= (curComponent == component) ? "selected" : "" %> <%= optionStyle %> value="<%= curComponent %>"><%= LanguageUtil.get(pageContext, TicketEntryConstants.getComponentLabel(curComponent)) %></option>

								<%
								}
								%>

							</select>
						</td>
					</tr>
					</table>

					<%
					String componentLinkKey = AdminUtil.getPreferenceKey("componentLink_", productDisplayName, String.valueOf(component));

					String componentLink = PrefsParamUtil.getString(portletPreferences, request, componentLinkKey, StringPool.BLANK);

					String componentMessageKey = AdminUtil.getComponentPreferenceKey("componentMessage_", productDisplayName, String.valueOf(component), currentLanguageId);

					String componentMessage = PrefsParamUtil.getString(portletPreferences, request, componentMessageKey, StringPool.BLANK);

					String componentMessageLinkKey = AdminUtil.getPreferenceKey("componentMessageLink_", productDisplayName, String.valueOf(component));

					String componentMessageLink = PrefsParamUtil.getString(portletPreferences, request, componentMessageLinkKey, StringPool.BLANK);

					String showInTicketViewKey = AdminUtil.getPreferenceKey("showInTicketView_", productDisplayName, String.valueOf(component));

					boolean showInTicketView = PrefsParamUtil.getBoolean(portletPreferences, request, showInTicketViewKey, false);
					%>

					<aui:fieldset>
						<aui:input cssClass="lfr-input-text-container" helpMessage="component-link-help" ignoreRequestValue="true" label="component-link" name="componentLink" value="<%= componentLink %>" />

						<aui:input cssClass="lfr-textarea-container" ignoreRequestValue="true" label="message" name="componentMessage" type="textarea" value="<%= componentMessage %>" />

						<aui:input cssClass="lfr-input-text-container" helpMessage="component-message-link-help" ignoreRequestValue="true" label="component-message-link" name="componentMessageLink" value="<%= componentMessageLink %>" />

						<aui:input cssClass="show-in-ticket-view" helpMessage="show-component-message-help" ignoreRequestValue="true" label="show-in-ticket-view" name="showInTicketView" type="checkbox" value="<%= showInTicketView %>" />
					</aui:fieldset>
				</c:when>
				<c:when test='<%= tabs2.equals("dxp-message") %>'>

					<%
					String dxpTitle = PrefsParamUtil.getString(portletPreferences, request, "dxpTitle", StringPool.BLANK);
					String dxpMessage = PrefsParamUtil.getString(portletPreferences, request, "dxpMessage", StringPool.BLANK);
					%>

					<aui:fieldset>
						<aui:input cssClass="lfr-input-text-container" ignoreRequestValue="true" label="dxp-title" name="dxpTitle" value="<%= dxpTitle %>" />

						<aui:input cssClass="lfr-textarea-container" ignoreRequestValue="true" label="dxp-message" name="dxpMessage" type="textarea" value="<%= dxpMessage %>" />
					</aui:fieldset>
				</c:when>
				<c:when test='<%= tabs2.equals("escalation-details") %>'>

					<%
					String escalationDetails = PrefsParamUtil.getString(portletPreferences, request, "escalationDetails_" + currentLanguageId, StringPool.BLANK);
					%>

					<table class="lfr-table">
					<tr>
						<td>
							<liferay-ui:message key="language" />
						</td>
						<td>
							<select name="<portlet:namespace />languageId" onChange="<portlet:namespace />updateForm();">

								<%
								for (int i = 0; i < locales.length; i++) {
									String optionStyle = StringPool.BLANK;

									if (Validator.isNotNull(portletPreferences.getValue("escalationDetails_" + LocaleUtil.toLanguageId(locales[i]), StringPool.BLANK))) {
										optionStyle = "style=\"font-weight: bold;\"";
									}
								%>

									<option <%= currentLanguageId.equals(LocaleUtil.toLanguageId(locales[i])) ? "selected" : "" %> <%= optionStyle %> value="<%= LocaleUtil.toLanguageId(locales[i]) %>"><%= locales[i].getDisplayName(locale) %></option>

								<%
								}
								%>

							</select>
						</td>
					</tr>
					</table>

					<aui:fieldset>
						<aui:input cssClass="lfr-textarea-container" label="body" name='<%= "escalationDetails_" + currentLanguageId %>' type="textarea" value="<%= escalationDetails %>" />
					</aui:fieldset>
				</c:when>
				<c:when test='<%= tabs2.equals("file-repositories") %>'>
					<c:choose>
						<c:when test="<%= cmd.equals(Constants.ADD) %>">
							<aui:fieldset>

								<%
								String fileRepositoryId = ParamUtil.getString(request, "fileRepositoryId");

								FileRepository fileRepository = AdminUtil.getFileRepository(fileRepositoryId);

								String name = BeanParamUtil.getString(fileRepository, request, "name");
								String host = BeanParamUtil.getString(fileRepository, request, "host");
								int status = BeanParamUtil.getInteger(fileRepository, request, "status");

								long[] supportRegionIds = ParamUtil.getLongValues(request, "supportRegions");

								if (fileRepository != null) {
									supportRegionIds = fileRepository.getSupportRegionIds();
								}
								%>

								<span class="aui-field-row">
									<label class="aui-w15 aui-field-inline aui-field-label-inline-label">
										<liferay-ui:message key="file-repository-id" />
									</label>

									<c:choose>
										<c:when test="<%= fileRepositoryId.equals(StringPool.BLANK) %>">
											<aui:input inlineField="<%= true %>" inlineLabel="left" label="" name="fileRepositoryId" type="text" />
										</c:when>
										<c:otherwise>
											<aui:input name="fileRepositoryId" type="hidden" value="<%= fileRepositoryId %>" />

											<aui:input disabled="<%= true %>" inlineField="<%= true %>" inlineLabel="left" label="" name="fileRepositoryIdLabel" type="text" value="<%= fileRepositoryId %>" />
										</c:otherwise>
									</c:choose>
								</span>

								<span class="aui-field-row">
									<label class="aui-w15 aui-field-inline aui-field-label-inline-label">
										<liferay-ui:message key="server-name" />
									</label>

									<aui:input inlineField="<%= true %>" inlineLabel="left" label="" name="name" value="<%= name %>" />
								</span>

								<span class="aui-field-row">
									<label class="aui-w15 aui-field-inline aui-field-label-inline-label">
										<liferay-ui:message key="host" />
									</label>

									<aui:input inlineField="<%= true %>" inlineLabel="left" label="" name="host" value="<%= fileRepository.getHost() %>" />
								</span>

								<span class="aui-field-row">
									<label class="aui-w15 aui-field-inline aui-field-label-inline-label">
										<liferay-ui:message key="status" />
									</label>

									<aui:select inlineField="<%= true %>" inlineLabel="left" label="" name="status">
										<aui:option label="active" selected="<%= status == WorkflowConstants.STATUS_APPROVED %>" value="<%= WorkflowConstants.STATUS_APPROVED %>" />
										<aui:option label="inactive" selected="<%= status == WorkflowConstants.STATUS_INACTIVE %>" value="<%= WorkflowConstants.STATUS_INACTIVE %>" />
									</aui:select>
								</span>

								<span class="aui-field-row">
									<label class="aui-w15 aui-field-inline aui-field-label-inline-label">
										<liferay-ui:message key="support-regions" />
									</label>

									<%
									List<SupportRegion> supportRegions = SupportRegionLocalServiceUtil.getSupportRegions(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

									for (SupportRegion supportRegion : supportRegions) {
									%>

										<%= supportRegion.getName() %>

										<input <%= ArrayUtil.contains(supportRegionIds, supportRegion.getSupportRegionId()) ? "checked" : "" %> name="<portlet:namespace />supportRegions" type="checkbox" value="<%= supportRegion.getSupportRegionId() %>" />

									<%
									}
									%>

								</span>
							</aui:fieldset>
						</c:when>
						<c:otherwise>
							<liferay-ui:search-container
								emptyResultsMessage="there-are-no-file-respositories"
								headerNames="server,host,status"
							>
								<liferay-ui:search-container-results
									results="<%= AdminUtil.getFileRepositories() %>"
								/>

								<liferay-ui:search-container-row
									className="com.liferay.osb.model.FileRepository"
									escapedModel="<%= true %>"
									modelVar="fileRepository"
								>
									<liferay-ui:search-container-column-text
										name="server"
										value="<%= fileRepository.getName() %>"
									/>

									<liferay-ui:search-container-column-text
										name="host"
										value="<%= StringUtil.shorten(fileRepository.getHost(), 115) %>"
									/>

									<liferay-ui:search-container-column-text
										name="status"
										translate="<%= true %>"
										value="<%= fileRepository.getStatusLabel() %>"
									/>

									<liferay-ui:search-container-column-text
										name="action"
									>
										<liferay-ui:icon-menu>
											<liferay-portlet:renderURL portletConfiguration="true" var="updateServerURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
												<portlet:param name="cmd" value="<%= Constants.ADD %>" />
												<portlet:param name="tabs1" value="<%= tabs1 %>" />
												<portlet:param name="tabs2" value="<%= tabs2 %>" />
												<portlet:param name="tabs3" value="<%= tabs3 %>" />
												<portlet:param name="fileRepositoryId" value="<%= fileRepository.getFileRepositoryId() %>" />
												<portlet:param name="redirect" value="<%= redirect %>" />
											</liferay-portlet:renderURL>

											<liferay-ui:icon
												image="edit"
												url="<%= updateServerURL %>"
											/>
										</liferay-ui:icon-menu>
									</liferay-ui:search-container-column-text>
								</liferay-ui:search-container-row>

								<liferay-ui:search-iterator paginate="<%= false %>" />
							</liferay-ui:search-container>

							<liferay-portlet:renderURL copyCurrentRenderParameters="<%= false %>" portletConfiguration="true" var="updateServerURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
								<portlet:param name="cmd" value="<%= Constants.ADD %>" />
								<portlet:param name="tabs1" value="<%= tabs1 %>" />
								<portlet:param name="tabs2" value="<%= tabs2 %>" />
								<portlet:param name="tabs3" value="<%= tabs3 %>" />
								<portlet:param name="redirect" value="<%= redirect %>" />
							</liferay-portlet:renderURL>

							<aui:button href="<%= updateServerURL %>" value="add-file-repository" />
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:when test='<%= tabs2.equals("indexing") %>'>
					<liferay-ui:message key="indexer-engine" />

					<aui:input checked='<%= PrefsParamUtil.getBoolean(portletPreferences, request, "elasticsearchEnabled", false) %>' label="elasticsearch" name="elasticsearchEnabled" type="radio" value="true" />

					<aui:input checked='<%= !PrefsParamUtil.getBoolean(portletPreferences, request, "elasticsearchEnabled", false) %>' label="lucene" name="elasticsearchEnabled" type="radio" value="false" />
				</c:when>
				<c:when test='<%= tabs2.equals("new-ui") %>'>
					<aui:fieldset>
						<aui:input label="enable-lesa2-ui" name="support2Enabled" type="checkbox" value='<%= PrefsParamUtil.getBoolean(portletPreferences, request, "support2Enabled", false) %>' />
					</aui:fieldset>

					<aui:fieldset>
						<aui:input label="show-lesa2-ui-for-partners" name="support2Partners" type="checkbox" value='<%= PrefsParamUtil.getBoolean(portletPreferences, request, "support2Partners", false) %>' />

						<aui:input label="show-lesa2-ui-for-premier-projects" name="support2PremierAccountTier" type="checkbox" value='<%= PrefsParamUtil.getBoolean(portletPreferences, request, "support2PremierAccountTier", false) %>' />

						<aui:input label="show-lesa2-ui-for-strategic-projects" name="support2StrategicAccountTier" type="checkbox" value='<%= PrefsParamUtil.getBoolean(portletPreferences, request, "support2StrategicAccountTier", false) %>' />

						<aui:input label="show-lesa2-ui-for-oem-projects" name="support2OEMAccountTier" type="checkbox" value='<%= PrefsParamUtil.getBoolean(portletPreferences, request, "support2OEMAccountTier", false) %>' />

						<aui:input label="show-lesa2-ui-for-regular-projects" name="support2RegularAccountTier" type="checkbox" value='<%= PrefsParamUtil.getBoolean(portletPreferences, request, "support2RegularAccountTier", false) %>' />

						<aui:input cssClass="lfr-textarea-container" label="show-lesa2-ui-for-specific-projects" name="support2EnabledAccounts" type="textarea" value='<%= PrefsParamUtil.getString(portletPreferences, request, "support2EnabledAccounts", null) %>' />

						<aui:input cssClass="lfr-textarea-container" label="show-lesa2-ui-for-specific-partners" name="support2EnabledPartners" type="textarea" value='<%= PrefsParamUtil.getString(portletPreferences, request, "support2EnabledPartners", null) %>' />
					</aui:fieldset>

					<aui:fieldset>
						<aui:input label="show-lesa2-ui-announcement-to-liferay-employees" name="support2LiferayAnnouncement" type="checkbox" value='<%= PrefsParamUtil.getBoolean(portletPreferences, request, "support2LiferayAnnouncement", false) %>' />

						<aui:input label="show-lesa2-ui-announcement-to-customers" name="support2CustomerAnnouncement" type="checkbox" value='<%= PrefsParamUtil.getBoolean(portletPreferences, request, "support2CustomerAnnouncement", false) %>' />
					</aui:fieldset>
				</c:when>
				<c:when test='<%= tabs2.equals("product-messages") %>'>

					<%
					String productEntryDisplayName = ParamUtil.getString(request, "productEntryDisplayName", ProductEntryConstants.DISPLAY_NAME_DIGITAL_ENTERPRISE);
					%>

					<table class="lfr-table">
					<tr>
						<td>
							<liferay-ui:message key="product" />
						</td>
						<td>
							<select name="<portlet:namespace />productEntryDisplayName" onChange="<portlet:namespace />updateForm();">

								<%
								for (String curProductEntryDisplayName : ProductEntryConstants.DISPLAY_NAMES_DXP) {
								%>

									<option <%= productEntryDisplayName.equals(curProductEntryDisplayName) ? "selected" : StringPool.BLANK %> value="<%= curProductEntryDisplayName %>"><%= LanguageUtil.get(pageContext, curProductEntryDisplayName) %></option>

								<%
								}
								%>

							</select>
						</td>
					</tr>
					</table>

					<%
					String productLink = PrefsParamUtil.getString(portletPreferences, request, "productLink_" + productEntryDisplayName, StringPool.BLANK);
					%>

					<aui:fieldset>
						<aui:input cssClass="lfr-input-text-container" helpMessage="product-link-help" ignoreRequestValue="true" label="product-link" name="productLink" value="<%= productLink %>" />
					</aui:fieldset>
				</c:when>
				<c:when test='<%= tabs2.equals("status-messages") %>'>

					<%
					int status = ParamUtil.getInteger(request, "status", TicketEntryConstants.STATUS_INCIDENT_REPORTED);
					%>

					<table class="lfr-table">
					<tr>
						<td>
							<liferay-ui:message key="language" />
						</td>
						<td>
							<select name="<portlet:namespace />languageId" onChange="<portlet:namespace />updateForm();">

								<%
								for (int i = 0; i < locales.length; i++) {
								%>

									<option <%= currentLanguageId.equals(LocaleUtil.toLanguageId(locales[i])) ? "selected" : "" %> value="<%= LocaleUtil.toLanguageId(locales[i]) %>"><%= locales[i].getDisplayName(locale) %></option>

								<%
								}
								%>

							</select>
						</td>
					</tr>
					<tr>
						<td>
							<liferay-ui:message key="status" />
						</td>
						<td>
							<select name="<portlet:namespace />status" onChange="<portlet:namespace />updateForm();">

								<%
								for (int curStatus : TicketEntryConstants.STATUSES_WORKFLOW_ORDER) {
									String optionStyle = StringPool.BLANK;

									String statusMessageKey = AdminUtil.getPreferenceKey("statusMessage_", String.valueOf(curStatus), currentLanguageId);

									String statusMessage = portletPreferences.getValue(statusMessageKey, StringPool.BLANK);

									if (Validator.isNotNull(statusMessage)) {
										optionStyle = "style=\"font-weight: bold;\"";
									}
								%>

									<option <%= (curStatus == status) ? "selected" : "" %> <%= optionStyle %> value="<%= curStatus %>"><%= LanguageUtil.get(pageContext, TicketEntryConstants.getStatusLabel(curStatus)) %></option>

								<%
								}
								%>

							</select>
						</td>
					</tr>
					</table>

					<%
					String statusMessageKey = AdminUtil.getPreferenceKey("statusMessage_", String.valueOf(status), currentLanguageId);

					String statusMessage = PrefsParamUtil.getString(portletPreferences, request, statusMessageKey, StringPool.BLANK);
					%>

					<aui:fieldset>
						<aui:input cssClass="lfr-textarea-container" ignoreRequestValue="true" label="body" name="statusMessage" type="textarea" value="<%= statusMessage %>" />
					</aui:fieldset>
				</c:when>
				<c:when test='<%= tabs2.equals("ticket-weight") %>'>

					<%
					double light = PrefsParamUtil.getDouble(portletPreferences, request, "light", 1);
					double heavy = PrefsParamUtil.getDouble(portletPreferences, request, "heavy", 1);
					%>

					<aui:fieldset>
						<aui:input cssClass="lfr-input-text-container" label="light" name="light" value="<%= numberFormat.format(light) %>" />

						<aui:input cssClass="lfr-input-text-container" label="heavy" name="heavy" value="<%= numberFormat.format(heavy) %>" />
					</aui:fieldset>
				</c:when>
				<c:when test='<%= tabs2.equals("tier-messages") %>'>

					<%
					int tier = ParamUtil.getInteger(request, "tier", AccountEntryConstants.TIER_REGULAR);
					%>

					<table class="lfr-table">
					<tr>
						<td>
							<liferay-ui:message key="language" />
						</td>
						<td>
							<select name="<portlet:namespace />languageId" onChange="<portlet:namespace />updateForm();">

								<%
								for (int i = 0; i < locales.length; i++) {
								%>

									<option <%= currentLanguageId.equals(LocaleUtil.toLanguageId(locales[i])) ? "selected" : "" %> value="<%= LocaleUtil.toLanguageId(locales[i]) %>"><%= locales[i].getDisplayName(locale) %></option>

								<%
								}
								%>

							</select>
						</td>
					</tr>
					<tr>
						<td>
							<liferay-ui:message key="tier" />
						</td>
						<td>
							<select name="<portlet:namespace />tier" onChange="<portlet:namespace />updateForm();">

								<%
								for (int curTier : AccountEntryConstants.TIERS) {
									String tierMessageKey = AdminUtil.getPreferenceKey("tierMessage_", String.valueOf(curTier), currentLanguageId);

									String tierMessage = portletPreferences.getValue(tierMessageKey, StringPool.BLANK);

									String optionStyle = StringPool.BLANK;

									if (Validator.isNotNull(tierMessage)) {
										optionStyle = "style=\"font-weight: bold;\"";
									}
								%>

									<option <%= (curTier == tier) ? "selected" : "" %> <%= optionStyle %> value="<%= curTier %>"><%= LanguageUtil.get(pageContext, AccountEntryConstants.getTierLabel(curTier)) %></option>

								<%
								}
								%>

							</select>
						</td>
					</tr>
					</table>

					<%
					String tierMessageKey = AdminUtil.getPreferenceKey("tierMessage_", String.valueOf(tier), currentLanguageId);

					String tierMessage = PrefsParamUtil.getString(portletPreferences, request, tierMessageKey, StringPool.BLANK);
					%>

					<aui:fieldset>
						<aui:input cssClass="lfr-textarea-container" ignoreRequestValue="true" label="body" name="tierMessage" type="textarea" value="<%= tierMessage %>" />
					</aui:fieldset>
				</c:when>
				<c:otherwise>
					<liferay-ui:tabs
						names="closed-ticket,inactive-ticket,game-plan"
						param="tabs3"
						url="<%= portletURL %>"
					/>

					<%
					String commentKey = AdminUtil.getCommentPreferenceKey(tabs3, currentLanguageId);

					String commentValue = PrefsParamUtil.getString(portletPreferences, request, commentKey, StringPool.BLANK);
					%>

					<table class="lfr-table">
					<tr>
						<td>
							<liferay-ui:message key="language" />
						</td>
						<td>
							<select name="<portlet:namespace />languageId" onChange="<portlet:namespace />updateForm();">

								<%
								for (int i = 0; i < locales.length; i++) {
									String optionStyle = StringPool.BLANK;

									String curCommentKey = AdminUtil.getCommentPreferenceKey(tabs3, LocaleUtil.toLanguageId(locales[i]));

									if (Validator.isNotNull(portletPreferences.getValue(curCommentKey, StringPool.BLANK))) {
										optionStyle = "style=\"font-weight: bold;\"";
									}
								%>

									<option <%= currentLanguageId.equals(LocaleUtil.toLanguageId(locales[i])) ? "selected" : "" %> <%= optionStyle %> value="<%= LocaleUtil.toLanguageId(locales[i]) %>"><%= locales[i].getDisplayName(locale) %></option>

								<%
								}
								%>

							</select>
						</td>
					</tr>
					</table>

					<aui:fieldset>
						<aui:input cssClass="lfr-textarea-container" ignoreRequestValue="true" label="body" name="commentValue" type="textarea" value="<%= commentValue %>" />
					</aui:fieldset>
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:when test='<%= tabs1.equals("trial") %>'>

			<%
			int maxTrialKeys = PrefsParamUtil.getInteger(portletPreferences, request, "maxTrialKeys", 0);
			int trialLiferayVersion = PrefsParamUtil.getInteger(portletPreferences, request, "trialLiferayVersion", 0);
			long trialProductEntryId = PrefsParamUtil.getLong(portletPreferences, request, "trialProductEntryId", 0);
			long trialSupportResponseId = PrefsParamUtil.getLong(portletPreferences, request, "trialSupportResponseId", 0);
			%>

			<aui:fieldset>
				<aui:input cssClass="lfr-input-text-container" label="maximum-number-of-trial-keys" name="maxTrialKeys" value="<%= maxTrialKeys %>" />
			</aui:fieldset>

			<aui:fieldset>
				<aui:select label="product" name="trialProductEntryId">

					<%
					List<ProductEntry> productEntries = ProductEntryLocalServiceUtil.getProductEntries(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

					for (ProductEntry productEntry : productEntries) {
					%>

						<aui:option label="<%= productEntry.getName() %>" selected="<%= productEntry.getProductEntryId() == trialProductEntryId %>" value="<%= productEntry.getProductEntryId() %>" />

					<%
					}
					%>

				</aui:select>
			</aui:fieldset>

			<aui:fieldset>
				<aui:select label="support" name="trialSupportResponseId">

					<%
					List<SupportResponse> supportResponses = SupportResponseLocalServiceUtil.getSupportResponses(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

					for (SupportResponse supportResponse : supportResponses) {
					%>

						<aui:option label="<%= supportResponse.getName() %>" selected="<%= supportResponse.getSupportResponseId() == trialSupportResponseId %>" value="<%= supportResponse.getSupportResponseId() %>" />

					<%
					}
					%>

				</aui:select>
			</aui:fieldset>

			<aui:fieldset>
				<aui:select label="liferay-version" name="trialLiferayVersion">
					<aui:option label="latest-version" value="0" />

					<%
					ProductEntry trialProductEntry = ProductEntryLocalServiceUtil.fetchProductEntry(trialProductEntryId);

					if (trialProductEntry != null) {
						List<ListType> envLFRTypes = trialProductEntry.getAllVersionsListTypes();

						for (ListType envLFRType : envLFRTypes) {
							if (envLFRType.getListTypeId() == ProductEntryConstants.PORTAL_VERSION_OTHER) {
								continue;
							}
						%>

							<aui:option label="<%= envLFRType.getName() %>" selected="<%= envLFRType.getListTypeId() == trialLiferayVersion %>" value="<%= envLFRType.getListTypeId() %>" />

						<%
						}
					}
					%>

				</aui:select>
			</aui:fieldset>
		</c:when>
		<c:otherwise>

			<%
			String emailCustomerFeedbackSubject = PrefsParamUtil.getString(portletPreferences, request, "emailCustomerFeedbackSubject_" + currentLanguageId, StringPool.BLANK);
			String emailCustomerFeedbackBody = PrefsParamUtil.getString(portletPreferences, request, "emailCustomerFeedbackBody_" + currentLanguageId, StringPool.BLANK);

			String emailFromName = PrefsParamUtil.getString(portletPreferences, request, "emailFromName");
			String emailFromAddress = PrefsParamUtil.getString(portletPreferences, request, "emailFromAddress");

			String emailProvisioningCreateAccountSubject = PrefsParamUtil.getString(portletPreferences, request, "emailProvisioningCreateAccountSubject_" + currentLanguageId, StringPool.BLANK);
			String emailProvisioningCreateAccountBody = PrefsParamUtil.getString(portletPreferences, request, "emailProvisioningCreateAccountBody_" + currentLanguageId, StringPool.BLANK);

			String emailTicketEntrySubject = PrefsParamUtil.getString(portletPreferences, request, "emailTicketEntrySubject_" + currentLanguageId, StringPool.BLANK);
			String emailTicketEntryBody = PrefsParamUtil.getString(portletPreferences, request, "emailTicketEntryBody_" + currentLanguageId, StringPool.BLANK);
			String emailTicketEntryCommentTemplate = PrefsParamUtil.getString(portletPreferences, request, "emailTicketEntryCommentTemplate_" + currentLanguageId, StringPool.BLANK);
			String emailTicketEntryDueDateTemplate = PrefsParamUtil.getString(portletPreferences, request, "emailTicketEntryDueDateTemplate_" + currentLanguageId, StringPool.BLANK);

			String emailTrainingEventCertificateSubject = PrefsParamUtil.getString(portletPreferences, request, "emailTrainingEventCertificateSubject_" + currentLanguageId, StringPool.BLANK);
			String emailTrainingEventCertificateBody = PrefsParamUtil.getString(portletPreferences, request, "emailTrainingEventCertificateBody_" + currentLanguageId, StringPool.BLANK);

			String emailTrainingEventSurveySubject = PrefsParamUtil.getString(portletPreferences, request, "emailTrainingEventSurveySubject_" + currentLanguageId, StringPool.BLANK);
			String emailTrainingEventSurveyBody = PrefsParamUtil.getString(portletPreferences, request, "emailTrainingEventSurveyBody_" + currentLanguageId, StringPool.BLANK);

			String emailTrainingExamCertificateSubject = PrefsParamUtil.getString(portletPreferences, request, "emailTrainingExamCertificateSubject_" + currentLanguageId, StringPool.BLANK);
			String emailTrainingExamCertificateBody = PrefsParamUtil.getString(portletPreferences, request, "emailTrainingExamCertificateBody_" + currentLanguageId, StringPool.BLANK);
			%>

			<liferay-ui:tabs
				names="general,liferay-feedback,provisioning-create-account,ticket-notification,training-certificate"
				param="tabs2"
				url="<%= portletURL %>"
			/>

			<c:if test='<%= !tabs2.equals("training-certificate") %>'>
				<span class="portlet-msg-info">
					<liferay-ui:message key="enter-custom-values-or-leave-it-blank-to-use-the-default-portal-settings" />
				</span>
			</c:if>

			<c:choose>
				<c:when test='<%= tabs2.equals("liferay-feedback") %>'>
					<table class="lfr-table">
					<tr>
						<td>
							<liferay-ui:message key="language" />
						</td>
						<td>
							<select name="<portlet:namespace />languageId" onChange="<portlet:namespace />updateForm();">

								<%
								for (int i = 0; i < locales.length; i++) {
									String optionStyle = StringPool.BLANK;

									if (Validator.isNotNull(portletPreferences.getValue("emailCustomerFeedbackSubject_" + LocaleUtil.toLanguageId(locales[i]), StringPool.BLANK)) ||
										Validator.isNotNull(portletPreferences.getValue("emailCustomerFeedbackBody_" + LocaleUtil.toLanguageId(locales[i]), StringPool.BLANK))) {

										optionStyle = "style=\"font-weight: bold;\"";
									}
								%>

									<option <%= currentLanguageId.equals(LocaleUtil.toLanguageId(locales[i])) ? "selected" : "" %> <%= optionStyle %> value="<%= LocaleUtil.toLanguageId(locales[i]) %>"><%= locales[i].getDisplayName(locale) %></option>

								<%
								}
								%>

							</select>
						</td>
					</tr>
					</table>

					<aui:fieldset>
						<aui:input cssClass="lfr-input-text-container" label="subject" name='<%= "emailCustomerFeedbackSubject_" + currentLanguageId %>' value="<%= emailCustomerFeedbackSubject %>" />

						<aui:input cssClass="lfr-textarea-container" label="body" name='<%= "emailCustomerFeedbackBody_" + currentLanguageId %>' type="textarea" value="<%= emailCustomerFeedbackBody %>" />
					</aui:fieldset>

					<div class="definition-of-terms">
						<h4>
							<liferay-ui:message key="definition-of-terms" />
						</h4>

						<dl>
							<dt>
								[$CLOSE_TIME$]
							</dt>
							<dd>
								The close time of the ticket
							</dd>
							<dt>
								[$FEEDBACK_ENTRY_URL$]
							</dt>
							<dd>
								The feedback's permalink
							</dd>
							<dt>
								[$TICKET_DISPLAY_ID$]
							</dt>
							<dd>
								The ticket's display ID
							</dd>
							<dt>
								[$TICKET_ENTRY_URL$]
							</dt>
							<dd>
								The ticket's permalink
							</dd>
						</dl>
					</div>
				</c:when>
				<c:when test='<%= tabs2.equals("provisioning-create-account") %>'>
					<table class="lfr-table">
					<tr>
						<td>
							<liferay-ui:message key="language" />
						</td>
						<td>
							<select name="<portlet:namespace />languageId" onChange="<portlet:namespace />updateForm();">

								<%
								for (int i = 0; i < locales.length; i++) {
									String optionStyle = StringPool.BLANK;

									if (Validator.isNotNull(portletPreferences.getValue("emailProvisioningCreateAccountSubject_" + LocaleUtil.toLanguageId(locales[i]), StringPool.BLANK)) ||
										Validator.isNotNull(portletPreferences.getValue("emailProvisioningCreateAccountBody_" + LocaleUtil.toLanguageId(locales[i]), StringPool.BLANK))) {

										optionStyle = "style=\"font-weight: bold;\"";
									}
								%>

									<option <%= (currentLanguageId.equals(LocaleUtil.toLanguageId(locales[i]))) ? "selected" : "" %> <%= optionStyle %> value="<%= LocaleUtil.toLanguageId(locales[i]) %>"><%= locales[i].getDisplayName(locale) %></option>

								<%
								}
								%>

							</select>
						</td>
					</tr>
					</table>

					<aui:fieldset>
						<aui:input cssClass="lfr-input-text-container" label="subject" name='<%= "emailProvisioningCreateAccountSubject_" + currentLanguageId %>' value="<%= emailProvisioningCreateAccountSubject %>" />

						<aui:input cssClass="lfr-textarea-container" label="body" name='<%= "emailProvisioningCreateAccountBody_" + currentLanguageId %>' type="textarea" value="<%= emailProvisioningCreateAccountBody %>" />
					</aui:fieldset>

					<div class="definition-of-terms">
						<h4>
							<liferay-ui:message key="definition-of-terms" />
						</h4>

						<dl>
							<dt>
								[$ACCOUNT_ENTRY_NAME$]
							</dt>
							<dd>
								The project name
							</dd>
						</dl>
					</div>
				</c:when>
				<c:when test='<%= tabs2.equals("ticket-notification") %>'>
					<table class="lfr-table">
					<tr>
						<td>
							<liferay-ui:message key="language" />
						</td>
						<td>
							<select name="<portlet:namespace />languageId" onChange="<portlet:namespace />updateForm();">

								<%
								for (int i = 0; i < locales.length; i++) {
									String optionStyle = StringPool.BLANK;

									if (Validator.isNotNull(portletPreferences.getValue("emailTicketEntrySubject_" + LocaleUtil.toLanguageId(locales[i]), StringPool.BLANK)) ||
										Validator.isNotNull(portletPreferences.getValue("emailTicketEntryBody_" + LocaleUtil.toLanguageId(locales[i]), StringPool.BLANK))) {

										optionStyle = "style=\"font-weight: bold;\"";
									}
								%>

									<option <%= (currentLanguageId.equals(LocaleUtil.toLanguageId(locales[i]))) ? "selected" : "" %> <%= optionStyle %> value="<%= LocaleUtil.toLanguageId(locales[i]) %>"><%= locales[i].getDisplayName(locale) %></option>

								<%
								}
								%>

							</select>
						</td>
					</tr>
					</table>

					<aui:fieldset>
						<aui:input cssClass="lfr-input-text-container" label="subject" name='<%= "emailTicketEntrySubject_" + currentLanguageId %>' value="<%= emailTicketEntrySubject %>" />

						<aui:input cssClass="lfr-textarea-container" label="body" name='<%= "emailTicketEntryBody_" + currentLanguageId %>' type="textarea" value="<%= emailTicketEntryBody %>" />
					</aui:fieldset>

					<div class="definition-of-terms">
						<h4>
							<liferay-ui:message key="definition-of-terms" />
						</h4>

						<dl>
							<dt>
								[$ACCOUNT_ENTRY_NAME$]
							</dt>
							<dd>
								The project name
							</dd>
							<dt>
								[$ACTION$]
							</dt>
							<dd>
								The action performed on the ticket
							</dd>
							<dt>
								[$APPLICATION_SERVER$]
							</dt>
							<dd>
								The ticket's application server
							</dd>
							<dt>
								[$ASSIGNED_TO$]
							</dt>
							<dd>
								Users assigned to the ticket
							</dd>
							<dt>
								[$COMMENT_TEMPLATE$]
							</dt>
							<dd>
								Only displayed if the action includes a comment. Configure template below.
							</dd>
							<dt>
								[$COMPONENT$]
							</dt>
							<dd>
								The ticket's component
							</dd>
							<dt>
								[$CREATE_DATE$]
							</dt>
							<dd>
								The ticket's create date
							</dd>
							<dt>
								[$DATABASE$]
							</dt>
							<dd>
								The ticket's database
							</dd>
							<dt>
								[$DESCRIPTION$]
							</dt>
							<dd>
								The ticket's description
							</dd>
							<dt>
								[$DUE_DATE_TEMPLATE$]
							</dt>
							<dd>
								Only displayed if the user is a ticket worker. Configure template below.
							</dd>
							<dt>
								[$ESCALATION_LEVEL$]
							</dt>
							<dd>
								The ticket's escalation level
							</dd>
							<dt>
								[$ESCALATION_LEVEL_COLOR$]
							</dt>
							<dd>
								The CSS class for escalation level color
							</dd>
							<dt>
								[$JAVA_VIRTUAL_MACHINE$]
							</dt>
							<dd>
								The ticket's java virtual machine
							</dd>
							<dt>
								[$MODIFIED_DATE$]
							</dt>
							<dd>
								The ticket's modified date
							</dd>
							<dt>
								[$OFFERING_ENTRY_STATUS$]
							</dt>
							<dd>
								The ticket's offering status
							</dd>
							<dt>
								[$OPERATING_SYSTEM$]
							</dt>
							<dd>
								The ticket's operating system
							</dd>
							<dt>
								[$REPORTED_BY$]
							</dt>
							<dd>
								The user who created the ticket
							</dd>
							<dt>
								[$RESOLUTION$]
							</dt>
							<dd>
								The ticket's resolution
							</dd>
							<dt>
								[$SEVERITY$]
							</dt>
							<dd>
								The ticket's severity
							</dd>
							<dt>
								[$SEVERITY_COLOR$]
							</dt>
							<dd>
								The CSS class for severity color
							</dd>
							<dt>
								[$STATUS$]
							</dt>
							<dd>
								The ticket's status
							</dd>
							<dt>
								[$SUBJECT$]
							</dt>
							<dd>
								The ticket's subject
							</dd>
							<dt>
								[$SUPPORT_RESPONSE_NAME$]
							</dt>
							<dd>
								The ticket's support response name
							</dd>
							<dt>
								[$TICKET_DISPLAY_ID$]
							</dt>
							<dd>
								The ticket's display ID
							</dd>
							<dt>
								[$TICKET_ENTRY_URL$]
							</dt>
							<dd>
								The ticket's permalink
							</dd>
							<dt>
								[$VERSION$]
							</dt>
							<dd>
								The ticket's version
							</dd>
							<dt>
								[$VERSION_TYPE$]
							</dt>
							<dd>
								The ticket's version type
							</dd>
						</dl>
					</div>

					<div class="separator"><!-- --></div>

					<aui:fieldset>
						<label class="aui-field-label">[$COMMENT_TEMPLATE$]</label>

						<aui:input cssClass="lfr-textarea-container" label="" name='<%= "emailTicketEntryCommentTemplate_" + currentLanguageId %>' type="textarea" value="<%= emailTicketEntryCommentTemplate %>" />
					</aui:fieldset>

					<div class="definition-of-terms">
						<h4>
							<liferay-ui:message key="definition-of-terms" />
						</h4>

						<dl>
							<dt>
								[$BODY$]
							</dt>
							<dd>
								The ticket comment's body
							</dd>
							<dt>
								[$CREATE_DATE$]
							</dt>
							<dd>
								The ticket comment's create date
							</dd>
							<dt>
								[$USER_FULL_NAME$]
							</dt>
							<dd>
								The ticket commenter's full name
							</dd>
							<dt>
								[$USER_PORTRAIT_URL$]
							</dt>
							<dd>
								The URL of the user's portrait
							</dd>
						</dl>
					</div>

					<div class="separator"><!-- --></div>

					<aui:fieldset>
						<label class="aui-field-label">[$DUE_DATE_TEMPLATE$]</label>

						<aui:input cssClass="lfr-textarea-container" label="" name='<%= "emailTicketEntryDueDateTemplate_" + currentLanguageId %>' type="textarea" value="<%= emailTicketEntryDueDateTemplate %>" />
					</aui:fieldset>

					<div class="definition-of-terms">
						<h4>
							<liferay-ui:message key="definition-of-terms" />
						</h4>

						<dl>
							<dt>
								[$DUE_DATE$]
							</dt>
							<dd>
								The ticket's due date
							</dd>
						</dl>
					</div>
				</c:when>
				<c:when test='<%= tabs2.equals("training-certificate") %>'>
					<liferay-ui:tabs
						names="training-certificate,training-survey,certification-exam"
						param="tabs3"
						url="<%= portletURL %>"
					/>

					<span class="portlet-msg-info">
						<liferay-ui:message key="enter-custom-values-or-leave-it-blank-to-use-the-default-portal-settings" />
					</span>

					<c:choose>
						<c:when test='<%= tabs3.equals("certification-exam") %>'>
							<table class="lfr-table">
							<tr>
								<td>
									<liferay-ui:message key="language" />
								</td>
								<td>
									<select name="<portlet:namespace />languageId" onChange="<portlet:namespace />updateForm();">

										<%
										for (int i = 0; i < locales.length; i++) {
											String optionStyle = StringPool.BLANK;

											if (Validator.isNotNull(portletPreferences.getValue("emailTrainingExamCertificateSubject_" + LocaleUtil.toLanguageId(locales[i]), StringPool.BLANK)) ||
												Validator.isNotNull(portletPreferences.getValue("emailTrainingExamCertificateBody_" + LocaleUtil.toLanguageId(locales[i]), StringPool.BLANK))) {

												optionStyle = "style=\"font-weight: bold;\"";
											}
										%>

											<option <%= currentLanguageId.equals(LocaleUtil.toLanguageId(locales[i])) ? "selected" : "" %> <%= optionStyle %> value="<%= LocaleUtil.toLanguageId(locales[i]) %>"><%= locales[i].getDisplayName(locale) %></option>

										<%
										}
										%>

									</select>
								</td>
							</tr>
							</table>

							<aui:fieldset>
								<aui:input cssClass="lfr-input-text-container" label="subject" name='<%= "emailTrainingExamCertificateSubject_" + currentLanguageId %>' value="<%= emailTrainingExamCertificateSubject %>" />

								<aui:input cssClass="lfr-textarea-container" label="body" name='<%= "emailTrainingExamCertificateBody_" + currentLanguageId %>' type="textarea" value="<%= emailTrainingExamCertificateBody %>" />
							</aui:fieldset>

							<div class="definition-of-terms">
								<h4>
									<liferay-ui:message key="definition-of-terms" />
								</h4>

								<dl>
									<dt>
										[$CERTIFICATE_KEY$]
									</dt>
									<dd>
										The training customer's certificate key
									</dd>
									<dt>
										[$CUSTOMER_FULL_NAME$]
									</dt>
									<dd>
										The training customer's full name
									</dd>
									<dt>
										[$EXAM_NAME$]
									</dt>
									<dd>
										The training exam's name
									</dd>
								</dl>
							</div>
						</c:when>
						<c:when test='<%= tabs3.equals("training-survey") %>'>
							<table class="lfr-table">
							<tr>
								<td>
									<liferay-ui:message key="language" />
								</td>
								<td>
									<select name="<portlet:namespace />languageId" onChange="<portlet:namespace />updateForm();">

										<%
										for (int i = 0; i < locales.length; i++) {
											String optionStyle = StringPool.BLANK;

											if (Validator.isNotNull(portletPreferences.getValue("emailTrainingEventSurveySubject_" + LocaleUtil.toLanguageId(locales[i]), StringPool.BLANK)) ||
												Validator.isNotNull(portletPreferences.getValue("emailTrainingEventSurveyBody_" + LocaleUtil.toLanguageId(locales[i]), StringPool.BLANK))) {

												optionStyle = "style=\"font-weight: bold;\"";
											}
										%>

											<option <%= currentLanguageId.equals(LocaleUtil.toLanguageId(locales[i])) ? "selected" : "" %> <%= optionStyle %> value="<%= LocaleUtil.toLanguageId(locales[i]) %>"><%= locales[i].getDisplayName(locale) %></option>

										<%
										}
										%>

									</select>
								</td>
							</tr>
							</table>

							<aui:fieldset>
								<aui:input cssClass="lfr-input-text-container" label="subject" name='<%= "emailTrainingEventSurveySubject_" + currentLanguageId %>' value="<%= emailTrainingEventSurveySubject %>" />

								<aui:input cssClass="lfr-textarea-container" label="body" name='<%= "emailTrainingEventSurveyBody_" + currentLanguageId %>' type="textarea" value="<%= emailTrainingEventSurveyBody %>" />
							</aui:fieldset>

							<div class="definition-of-terms">
								<h4>
									<liferay-ui:message key="definition-of-terms" />
								</h4>

								<dl>
									<dt>
										[$CLASS_END_DATE$]
									</dt>
									<dd>
										The training class' end date
									</dd>
									<dt>
										[$CLASS_START_DATE$]
									</dt>
									<dd>
										The training class' start date
									</dd>
									<dt>
										[$COURSE_NAME$]
									</dt>
									<dd>
										The training course's name
									</dd>
									<dt>
										[$CUSTOMER_FULL_NAME$]
									</dt>
									<dd>
										The training customer's full name
									</dd>
									<dt>
										[$TRAINING_SURVEY_URL$]
									</dt>
									<dd>
										The survey URL for the training customer
									</dd>
								</dl>
							</div>
						</c:when>
						<c:otherwise>
							<table class="lfr-table">
							<tr>
								<td>
									<liferay-ui:message key="language" />
								</td>
								<td>
									<select name="<portlet:namespace />languageId" onChange="<portlet:namespace />updateForm();">

										<%
										for (int i = 0; i < locales.length; i++) {
											String optionStyle = StringPool.BLANK;

											if (Validator.isNotNull(portletPreferences.getValue("emailTrainingEventCertificateSubject_" + LocaleUtil.toLanguageId(locales[i]), StringPool.BLANK)) ||
												Validator.isNotNull(portletPreferences.getValue("emailTrainingEventCertificateBody_" + LocaleUtil.toLanguageId(locales[i]), StringPool.BLANK))) {

												optionStyle = "style=\"font-weight: bold;\"";
											}
										%>

											<option <%= currentLanguageId.equals(LocaleUtil.toLanguageId(locales[i])) ? "selected" : "" %> <%= optionStyle %> value="<%= LocaleUtil.toLanguageId(locales[i]) %>"><%= locales[i].getDisplayName(locale) %></option>

										<%
										}
										%>

									</select>
								</td>
							</tr>
							</table>

							<aui:fieldset>
								<aui:input cssClass="lfr-input-text-container" label="subject" name='<%= "emailTrainingEventCertificateSubject_" + currentLanguageId %>' value="<%= emailTrainingEventCertificateSubject %>" />

								<aui:input cssClass="lfr-textarea-container" label="body" name='<%= "emailTrainingEventCertificateBody_" + currentLanguageId %>' type="textarea" value="<%= emailTrainingEventCertificateBody %>" />
							</aui:fieldset>

							<div class="definition-of-terms">
								<h4>
									<liferay-ui:message key="definition-of-terms" />
								</h4>

								<dl>
									<dt>
										[$CERTIFICATE_KEY$]
									</dt>
									<dd>
										The training customer's certificate key
									</dd>
									<dt>
										[$CLASS_START_DATE$]
									</dt>
									<dd>
										The training class' start date
									</dd>
									<dt>
										[$COURSE_NAME$]
									</dt>
									<dd>
										The training course's name
									</dd>
									<dt>
										[$CUSTOMER_FULL_NAME$]
									</dt>
									<dd>
										The training customer's full name
									</dd>
									<dt>
										[$VERSION$]
									</dt>
									<dd>
										The training course's version
									</dd>
								</dl>
							</div>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<aui:fieldset>
						<aui:input cssClass="lfr-input-text-container" label="name" name="emailFromName" value="<%= emailFromName %>" />

						<liferay-ui:error key="emailFromAddress" message="please-enter-a-valid-email-address" />

						<aui:input cssClass="lfr-input-text-container" label="address" name="emailFromAddress" value="<%= emailFromAddress %>" />
					</aui:fieldset>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>

	<c:if test='<%= !tabs2.equals("file-repositories") || cmd.equals(Constants.ADD) %>'>
		<input type="submit" value="<liferay-ui:message key="save" />" />
	</c:if>
</aui:form>