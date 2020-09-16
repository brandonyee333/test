<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "email-notifications");
String tabs2 = ParamUtil.getString(request, "tabs2", "general");

String redirect = ParamUtil.getString(request, "redirect");

String currentLanguageId = LanguageUtil.getLanguageId(request);

Set<Locale> localesSet = LanguageUtil.getAvailableLocales();

Locale[] locales = localesSet.toArray(new Locale[0]);
%>

<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="portletURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
	<portlet:param name="tabs1" value="<%= tabs1 %>" />
	<portlet:param name="tabs2" value="<%= tabs2 %>" />
	<portlet:param name="redirect" value="<%= redirect %>" />
</liferay-portlet:renderURL>

<script type="text/javascript">
	function <portlet:namespace />updateForm() {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = '';
		submitForm(document.<portlet:namespace />fm, '<%= portletURL.toString() %>');
	}
</script>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="tabs1" type="hidden" value="<%= tabs1 %>" />
	<aui:input name="tabs2" type="hidden" value="<%= tabs2 %>" />

	<div class="container-fluid-1280">
		<liferay-ui:tabs
			names="email-notifications,trial"
			param="tabs1"
			url="<%= portletURL %>"
		/>

		<c:choose>
			<c:when test='<%= tabs1.equals("trial") %>'>

				<%
				int trialLiferayVersion = PrefsParamUtil.getInteger(portletPreferences, request, "trialLiferayVersion", 0);
				long trialProductEntryId = PrefsParamUtil.getLong(portletPreferences, request, "trialProductEntryId", 0);
				long trialSupportResponseId = PrefsParamUtil.getLong(portletPreferences, request, "trialSupportResponseId", 0);
				%>

				<aui:fieldset>
					<aui:input label="maximum-number-of-trial-keys" name="maxTrialKeys" value='<%= PrefsParamUtil.getInteger(portletPreferences, request, "maxTrialKeys", 0) %>' />
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

				<p class="portlet-msg-info">
					<liferay-ui:message key="enter-custom-values-or-leave-it-blank-to-use-the-default-portal-settings" />
				</p>

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
												optionStyle = "font-weight: bold;";
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

		<aui:button type="submit" value="save" />
	</div>
</aui:form>