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

Set<Locale> localesSet = LanguageUtil.getAvailableLocales();

Locale[] locales = localesSet.toArray(new Locale[localesSet.size()]);
%>

<script type="text/javascript">
	function <portlet:namespace />updateForm() {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = '';
		submitForm(document.<portlet:namespace />fm);
	}
</script>

<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="portletURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
	<portlet:param name="tabs1" value="<%= tabs1 %>" />
	<portlet:param name="tabs2" value="<%= tabs2 %>" />
	<portlet:param name="tabs3" value="<%= tabs3 %>" />
	<portlet:param name="redirect" value="<%= redirect %>" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="tabs1" type="hidden" value="<%= tabs1 %>" />
	<aui:input name="tabs2" type="hidden" value="<%= tabs2 %>" />
	<aui:input name="tabs3" type="hidden" value="<%= tabs3 %>" />

	<liferay-ui:error key="announcementDateInvalid" message="please-enter-a-display-date-that-comes-before-the-expiration-date" />
	<liferay-ui:error key="componentLinkInvalid" message="please-enter-a-valid-component-link" />
	<liferay-ui:error key="componentMessageLinkInvalid" message="please-enter-a-valid-component-message-link" />

	<liferay-ui:tabs
		names="email-notifications,rabbitmq,support,trial"
		param="tabs1"
		url="<%= portletURL %>"
	/>

	<c:choose>
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
				<aui:input label="script" name="deadLetterFilterScript" type="textarea" value='<%= PrefsParamUtil.getString(portletPreferences, request, "deadLetterFilterScript", StringPool.BLANK) %>' />
			</aui:fieldset>
		</c:when>
		<c:when test='<%= tabs1.equals("support") %>'>
			<liferay-ui:tabs
				names="automatic-comments,ticket-weight,product-messages,component-messages,dxp-message,escalation-details,attachment-keywords,status-messages,tier-messages,assignment-ratio,file-repositories,banner"
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

								<%
								List<SupportRegion> supportRegions = SupportRegionLocalServiceUtil.getSupportRegions(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

								SupportRegion firstSupportRegion = supportRegions.get(0);

								long supportRegionId = PrefsParamUtil.getLong(portletPreferences, request, "supportRegionId", firstSupportRegion.getSupportRegionId());
								%>

								<aui:select label="" name="supportRegionId" onChange='<%= renderResponse.getNamespace() + "updateForm();" %>'>

									<%
									for (SupportRegion supportRegion : supportRegions) {
									%>

										<aui:option label="<%= supportRegion.getName() %>" selected="<%= supportRegionId == supportRegion.getSupportRegionId() %>" value="<%= supportRegion.getSupportRegionId() %>" />

									<%
									}
									%>

								</aui:select>
							</td>
						</tr>
						<tr>
							<td>
								<liferay-ui:message key="ticket-logic-assignment-ratio" />
							</td>
							<td>
								<aui:select label="" name="ratio">

									<%
									double ratio = GetterUtil.getDouble(portletPreferences.getValue(supportRegionId + "_assignmentRatio", "0.75"));

									for (int i = 0; i <= 20; i++) {
									%>

										<aui:option selected="<%= ratio == ((((double)i) * 5) / 100) %>" value="<%= (((double)i) * 5) / 100 %>">
											<%= i * 5 %>% <liferay-ui:message key="utilization" /> / <%= 100 - (i * 5) %>% <liferay-ui:message key="business-hours-left" />
										</aui:option>

									<%
									}
									%>

								</aui:select>
							</td>
						</tr>
					</table>
				</c:when>
				<c:when test='<%= tabs2.equals("attachment-keywords") %>'>
					<aui:fieldset>
						<aui:input label="keywords" name="attachmentKeywords" type="textarea" value='<%= PrefsParamUtil.getString(portletPreferences, request, "attachmentKeywords", StringPool.BLANK) %>' />
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
								<aui:select label="" name="languageId" onChange='<%= renderResponse.getNamespace() + "updateForm();" %>'>

									<%
									for (int i = 0; i < locales.length; i++) {
										String optionStyle = StringPool.BLANK;

										if (Validator.isNotNull(portletPreferences.getValue("announcementTitle_" + LocaleUtil.toLanguageId(locales[i]), StringPool.BLANK))) {
											optionStyle = "style=\"font-weight: bold;\"";
										}
									%>

										<aui:option label="<%= locales[i].getDisplayName(locale) %>" selected="<%= currentLanguageId.equals(LocaleUtil.toLanguageId(locales[i])) %>" style="<%= optionStyle %>" value="<%= LocaleUtil.toLanguageId(locales[i]) %>" />

									<%
									}
									%>

								</aui:select>
							</td>
						</tr>
					</table>

					<aui:fieldset>
						<aui:input ignoreRequestValue="<%= true %>" label="title" name="announcementTitle" value="<%= announcementTitle %>" />

						<aui:input ignoreRequestValue="<%= true %>" label="content" name="announcementContent" type="textarea" value="<%= announcementContent %>" />
					</aui:fieldset>

					<%
					Calendar enabledDateCal = announcementExpirationCal;

					enabledDateCal.add(Calendar.YEAR, -2);

					Date firstEnabledDate = enabledDateCal.getTime();

					enabledDateCal.add(Calendar.YEAR, 4);

					Date lastEnabledDate = enabledDateCal.getTime();
					%>

					<table class="lfr-table">
						<tr>
							<td>
								<liferay-ui:message key="display-date" /> (GMT)
							</td>
							<td>
								<liferay-ui:input-date
									dayParam="announcementDisplayDateDay"
									dayValue="<%= announcementDisplayCal.get(Calendar.DAY_OF_MONTH) %>"
									firstEnabledDate="<%= firstEnabledDate %>"
									lastEnabledDate="<%= lastEnabledDate %>"
									monthParam="announcementDisplayDateMonth"
									monthValue="<%= announcementDisplayCal.get(Calendar.MONTH) %>"
									nullable="<%= false %>"
									yearParam="announcementDisplayDateYear"
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
									dayParam="announcementExpirationDateDay"
									dayValue="<%= announcementExpirationCal.get(Calendar.DAY_OF_MONTH) %>"
									firstEnabledDate="<%= firstEnabledDate %>"
									lastEnabledDate="<%= lastEnabledDate %>"
									monthParam="announcementExpirationDateMonth"
									monthValue="<%= announcementExpirationCal.get(Calendar.MONTH) %>"
									nullable="<%= false %>"
									yearParam="announcementExpirationDateYear"
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
				<c:when test='<%= tabs2.equals("dxp-message") %>'>

					<%
					String dxpTitle = PrefsParamUtil.getString(portletPreferences, request, "dxpTitle", StringPool.BLANK);
					String dxpMessage = PrefsParamUtil.getString(portletPreferences, request, "dxpMessage", StringPool.BLANK);
					%>

					<aui:fieldset>
						<aui:input ignoreRequestValue="<%= true %>" label="dxp-title" name="dxpTitle" value="<%= dxpTitle %>" />

						<aui:input ignoreRequestValue="<%= true %>" label="dxp-message" name="dxpMessage" type="textarea" value="<%= dxpMessage %>" />
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
								<aui:select label="" name="languageId" onChange='<%= renderResponse.getNamespace() + "updateForm();" %>'>

									<%
									for (int i = 0; i < locales.length; i++) {
										String optionStyle = StringPool.BLANK;

										if (Validator.isNotNull(portletPreferences.getValue("escalationDetails_" + LocaleUtil.toLanguageId(locales[i]), StringPool.BLANK))) {
											optionStyle = "style=\"font-weight: bold;\"";
										}
									%>

										<aui:option label="<%= locales[i].getDisplayName(locale) %>" selected="<%= currentLanguageId.equals(LocaleUtil.toLanguageId(locales[i])) %>" style="<%= optionStyle %>" value="<%= LocaleUtil.toLanguageId(locales[i]) %>" />

									<%
									}
									%>

								</aui:select>
							</td>
						</tr>
					</table>

					<aui:fieldset>
						<aui:input label="body" name='<%= "escalationDetails_" + currentLanguageId %>' type="textarea" value="<%= escalationDetails %>" />
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
									<label class="aui-field-inline aui-field-label-inline-label w15">
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
									<label class="aui-field-inline aui-field-label-inline-label w15">
										<liferay-ui:message key="server-name" />
									</label>

									<aui:input inlineField="<%= true %>" inlineLabel="left" label="" name="name" value="<%= name %>" />
								</span>
								<span class="aui-field-row">
									<label class="aui-field-inline aui-field-label-inline-label w15">
										<liferay-ui:message key="host" />
									</label>

									<aui:input inlineField="<%= true %>" inlineLabel="left" label="" name="host" value="<%= fileRepository.getHost() %>" />
								</span>
								<span class="aui-field-row">
									<label class="aui-field-inline aui-field-label-inline-label w15">
										<liferay-ui:message key="status" />
									</label>

									<aui:select inlineField="<%= true %>" inlineLabel="left" label="" name="status">
										<aui:option label="active" selected="<%= status == WorkflowConstants.STATUS_APPROVED %>" value="<%= WorkflowConstants.STATUS_APPROVED %>" />
										<aui:option label="inactive" selected="<%= status == WorkflowConstants.STATUS_INACTIVE %>" value="<%= WorkflowConstants.STATUS_INACTIVE %>" />
									</aui:select>
								</span>
								<span class="aui-field-row">
									<label class="aui-field-inline aui-field-label-inline-label w15">
										<liferay-ui:message key="support-regions" />
									</label>

									<%
									List<SupportRegion> supportRegions = SupportRegionLocalServiceUtil.getSupportRegions(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

									for (SupportRegion supportRegion : supportRegions) {
									%>

										<%= supportRegion.getName() %>

										<aui:input checked="<%= ArrayUtil.contains(supportRegionIds, supportRegion.getSupportRegionId()) %>" name="supportRegions" type="checkbox" value="<%= supportRegion.getSupportRegionId() %>" />

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
											<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="updateServerURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
												<portlet:param name="cmd" value="<%= Constants.ADD %>" />
												<portlet:param name="tabs1" value="<%= tabs1 %>" />
												<portlet:param name="tabs2" value="<%= tabs2 %>" />
												<portlet:param name="tabs3" value="<%= tabs3 %>" />
												<portlet:param name="fileRepositoryId" value="<%= fileRepository.getFileRepositoryId() %>" />
												<portlet:param name="redirect" value="<%= redirect %>" />
											</liferay-portlet:renderURL>

											<liferay-ui:icon
												message="edit"
												url="<%= updateServerURL %>"
											/>
										</liferay-ui:icon-menu>
									</liferay-ui:search-container-column-text>
								</liferay-ui:search-container-row>

								<liferay-ui:search-iterator
									markupView="lexicon"
									paginate="<%= false %>"
								/>
							</liferay-ui:search-container>

							<liferay-portlet:renderURL copyCurrentRenderParameters="<%= false %>" portletConfiguration="<%= true %>" var="updateServerURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
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
								<aui:select label="" name="productEntryDisplayName" onChange='<%= renderResponse.getNamespace() + "updateForm();" %>'>

									<%
									for (String curProductEntryDisplayName : ProductEntryConstants.DISPLAY_NAMES_DXP) {
									%>

										<aui:option label="<%= curProductEntryDisplayName %>" selected="<%= productEntryDisplayName.equals(curProductEntryDisplayName) %>" value="<%= curProductEntryDisplayName %>" />

									<%
									}
									%>

								</aui:select>
							</td>
						</tr>
					</table>

					<%
					String productLink = PrefsParamUtil.getString(portletPreferences, request, "productLink_" + productEntryDisplayName, StringPool.BLANK);
					%>

					<aui:fieldset>
						<aui:input helpMessage="product-link-help" ignoreRequestValue="<%= true %>" label="product-link" name="productLink" value="<%= productLink %>" />
					</aui:fieldset>
				</c:when>
				<c:when test='<%= tabs2.equals("ticket-weight") %>'>

					<%
					double light = PrefsParamUtil.getDouble(portletPreferences, request, "light", 1);
					double heavy = PrefsParamUtil.getDouble(portletPreferences, request, "heavy", 1);
					%>

					<aui:fieldset>
						<aui:input label="light" name="light" value="<%= numberFormat.format(light) %>" />

						<aui:input label="heavy" name="heavy" value="<%= numberFormat.format(heavy) %>" />
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
								<aui:select label="" name="languageId" onChange='<%= renderResponse.getNamespace() + "updateForm();" %>'>

									<%
									for (int i = 0; i < locales.length; i++) {
									%>

										<aui:option label="<%= locales[i].getDisplayName(locale) %>" selected="<%= currentLanguageId.equals(LocaleUtil.toLanguageId(locales[i])) %>" value="<%= LocaleUtil.toLanguageId(locales[i]) %>" />

									<%
									}
									%>

								</aui:select>
							</td>
						</tr>
						<tr>
							<td>
								<liferay-ui:message key="tier" />
							</td>
							<td>
								<aui:select label="" name="tier" onChange='<%= renderResponse.getNamespace() + "updateForm();" %>'>

									<%
									for (int curTier : AccountEntryConstants.TIERS) {
										String tierMessageKey = AdminUtil.getPreferenceKey("tierMessage_", String.valueOf(curTier), currentLanguageId);

										String tierMessage = portletPreferences.getValue(tierMessageKey, StringPool.BLANK);

										String optionStyle = StringPool.BLANK;

										if (Validator.isNotNull(tierMessage)) {
											optionStyle = "style=\"font-weight: bold;\"";
										}
									%>

										<aui:option label="<%= AccountEntryConstants.getTierLabel(curTier) %>" selected="<%= curTier == tier %>" style="<%= optionStyle %>" value="<%= curTier %>" />

									<%
									}
									%>

								</aui:select>
							</td>
						</tr>
					</table>

					<%
					String tierMessageKey = AdminUtil.getPreferenceKey("tierMessage_", String.valueOf(tier), currentLanguageId);

					String tierMessage = PrefsParamUtil.getString(portletPreferences, request, tierMessageKey, StringPool.BLANK);
					%>

					<aui:fieldset>
						<aui:input ignoreRequestValue="<%= true %>" label="body" name="tierMessage" type="textarea" value="<%= tierMessage %>" />
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
								<aui:select label="" name="languageId" onChange='<%= renderResponse.getNamespace() + "updateForm();" %>'>

									<%
									for (int i = 0; i < locales.length; i++) {
										String optionStyle = StringPool.BLANK;

										String curCommentKey = AdminUtil.getCommentPreferenceKey(tabs3, LocaleUtil.toLanguageId(locales[i]));

										if (Validator.isNotNull(portletPreferences.getValue(curCommentKey, StringPool.BLANK))) {
											optionStyle = "style=\"font-weight: bold;\"";
										}
									%>

										<aui:option label="<%= locales[i].getDisplayName(locale) %>" selected="<%= currentLanguageId.equals(LocaleUtil.toLanguageId(locales[i])) %>" style="<%= optionStyle %>" value="<%= LocaleUtil.toLanguageId(locales[i]) %>" />

									<%
									}
									%>

								</aui:select>
							</td>
						</tr>
					</table>

					<aui:fieldset>
						<aui:input ignoreRequestValue="<%= true %>" label="body" name="commentValue" type="textarea" value="<%= commentValue %>" />
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
				<aui:input label="maximum-number-of-trial-keys" name="maxTrialKeys" value="<%= maxTrialKeys %>" />
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
			%>

			<liferay-ui:tabs
				names="general,liferay-feedback,provisioning-create-account,ticket-notification,"
				param="tabs2"
				url="<%= portletURL %>"
			/>

			<span class="portlet-msg-info">
				<liferay-ui:message key="enter-custom-values-or-leave-it-blank-to-use-the-default-portal-settings" />
			</span>

			<c:choose>
				<c:when test='<%= tabs2.equals("liferay-feedback") %>'>
					<table class="lfr-table">
						<tr>
							<td>
								<liferay-ui:message key="language" />
							</td>
							<td>
								<aui:select label="" name="languageId" onChange='<%= renderResponse.getNamespace() + "updateForm();" %>'>

									<%
									for (int i = 0; i < locales.length; i++) {
										String optionStyle = StringPool.BLANK;

										if (Validator.isNotNull(portletPreferences.getValue("emailCustomerFeedbackSubject_" + LocaleUtil.toLanguageId(locales[i]), StringPool.BLANK)) || Validator.isNotNull(portletPreferences.getValue("emailCustomerFeedbackBody_" + LocaleUtil.toLanguageId(locales[i]), StringPool.BLANK))) {
											optionStyle = "style=\"font-weight: bold;\"";
										}
									%>

										<aui:option label="<%= locales[i].getDisplayName(locale) %>" selected="<%= currentLanguageId.equals(LocaleUtil.toLanguageId(locales[i])) %>" style="<%= optionStyle %>" value="<%= LocaleUtil.toLanguageId(locales[i]) %>" />

									<%
									}
									%>

								</aui:select>
							</td>
						</tr>
					</table>

					<aui:fieldset>
						<aui:input label="subject" name='<%= "emailCustomerFeedbackSubject_" + currentLanguageId %>' value="<%= emailCustomerFeedbackSubject %>" />

						<aui:input label="body" name='<%= "emailCustomerFeedbackBody_" + currentLanguageId %>' type="textarea" value="<%= emailCustomerFeedbackBody %>" />
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
								<aui:select label="" name="languageId" onChange='<%= renderResponse.getNamespace() + "updateForm();" %>'>

									<%
									for (int i = 0; i < locales.length; i++) {
										String optionStyle = StringPool.BLANK;

										if (Validator.isNotNull(portletPreferences.getValue("emailProvisioningCreateAccountSubject_" + LocaleUtil.toLanguageId(locales[i]), StringPool.BLANK)) || Validator.isNotNull(portletPreferences.getValue("emailProvisioningCreateAccountBody_" + LocaleUtil.toLanguageId(locales[i]), StringPool.BLANK))) {
											optionStyle = "style=\"font-weight: bold;\"";
										}
									%>

										<aui:option label="<%= locales[i].getDisplayName(locale) %>" selected="<%= currentLanguageId.equals(LocaleUtil.toLanguageId(locales[i])) %>" style="<%= optionStyle %>" value="<%= LocaleUtil.toLanguageId(locales[i]) %>" />

									<%
									}
									%>

								</aui:select>
							</td>
						</tr>
					</table>

					<aui:fieldset>
						<aui:input label="subject" name='<%= "emailProvisioningCreateAccountSubject_" + currentLanguageId %>' value="<%= emailProvisioningCreateAccountSubject %>" />

						<aui:input label="body" name='<%= "emailProvisioningCreateAccountBody_" + currentLanguageId %>' type="textarea" value="<%= emailProvisioningCreateAccountBody %>" />
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
								<aui:select label="" name="languageId" onChange='<%= renderResponse.getNamespace() + "updateForm();" %>'>

									<%
									for (int i = 0; i < locales.length; i++) {
										String optionStyle = StringPool.BLANK;

										if (Validator.isNotNull(portletPreferences.getValue("emailTicketEntrySubject_" + LocaleUtil.toLanguageId(locales[i]), StringPool.BLANK)) || Validator.isNotNull(portletPreferences.getValue("emailTicketEntryBody_" + LocaleUtil.toLanguageId(locales[i]), StringPool.BLANK))) {
											optionStyle = "style=\"font-weight: bold;\"";
										}
									%>

										<aui:option label="<%= locales[i].getDisplayName(locale) %>" selected="<%= currentLanguageId.equals(LocaleUtil.toLanguageId(locales[i])) %>" style="<%= optionStyle %>" value="<%= LocaleUtil.toLanguageId(locales[i]) %>" />

									<%
									}
									%>

								</aui:select>
							</td>
						</tr>
					</table>

					<aui:fieldset>
						<aui:input label="subject" name='<%= "emailTicketEntrySubject_" + currentLanguageId %>' value="<%= emailTicketEntrySubject %>" />

						<aui:input label="body" name='<%= "emailTicketEntryBody_" + currentLanguageId %>' type="textarea" value="<%= emailTicketEntryBody %>" />
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

						<aui:input label="" name='<%= "emailTicketEntryCommentTemplate_" + currentLanguageId %>' type="textarea" value="<%= emailTicketEntryCommentTemplate %>" />
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

						<aui:input name='<%= "emailTicketEntryDueDateTemplate_" + currentLanguageId %>' type="textarea" value="<%= emailTicketEntryDueDateTemplate %>" />
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
				<c:otherwise>
					<aui:fieldset>
						<aui:input label="name" name="emailFromName" value="<%= emailFromName %>" />

						<liferay-ui:error key="emailFromAddress" message="please-enter-a-valid-email-address" />

						<aui:input label="address" name="emailFromAddress" value="<%= emailFromAddress %>" />
					</aui:fieldset>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>

	<c:if test='<%= !tabs2.equals("file-repositories") || cmd.equals(Constants.ADD) %>'>
		<aui:button type="submit" value="save" />
	</c:if>
</aui:form>