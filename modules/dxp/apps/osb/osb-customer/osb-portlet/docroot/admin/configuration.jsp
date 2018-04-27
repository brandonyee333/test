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
			String emailFromName = PrefsParamUtil.getString(portletPreferences, request, "emailFromName");
			String emailFromAddress = PrefsParamUtil.getString(portletPreferences, request, "emailFromAddress");

			String emailProvisioningCreateAccountSubject = PrefsParamUtil.getString(portletPreferences, request, "emailProvisioningCreateAccountSubject_" + currentLanguageId, StringPool.BLANK);
			String emailProvisioningCreateAccountBody = PrefsParamUtil.getString(portletPreferences, request, "emailProvisioningCreateAccountBody_" + currentLanguageId, StringPool.BLANK);
			%>

			<liferay-ui:tabs
				names="general,provisioning-create-account"
				param="tabs2"
				url="<%= portletURL %>"
			/>

			<span class="portlet-msg-info">
				<liferay-ui:message key="enter-custom-values-or-leave-it-blank-to-use-the-default-portal-settings" />
			</span>

			<c:choose>
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