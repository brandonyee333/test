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

<%@ include file="/downloads/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "customer-access");
String tabs2 = ParamUtil.getString(request, "tabs2", "general");

String redirect = ParamUtil.getString(request, "redirect");
%>

<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="portletURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
	<portlet:param name="tabs1" value="<%= tabs1 %>" />
	<portlet:param name="redirect" value="<%= redirect %>" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="tabs1" type="hidden" value="<%= tabs1 %>" />
	<aui:input name="tabs2" type="hidden" value="<%= tabs2 %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<liferay-ui:tabs
		names="customer-access,guest-access,esa,evaluation-eula,studio-eula,trial"
		param="tabs1"
		url="<%= portletURL %>"
	/>

	<liferay-ui:error key="evaluationEulaVersion" message="eula-version-must-be-greater-than-eula-version-required-to-accept" />
	<liferay-ui:error key="studioEulaVersion" message="eula-version-must-be-greater-than-eula-version-required-to-accept" />

	<div class="container-fluid-1280">
		<c:choose>
			<c:when test='<%= tabs1.equals("esa") %>'>
				<table class="lfr-table">
					<tr>
						<td>
							<liferay-ui:message key="language" />
						</td>
						<td>
							<aui:select label="" name="languageId" onChange='<%= renderResponse.getNamespace() + "updateLanguage(this);" %>'>

								<%
								for (int i = 0; i < locales.length; i++) {
									String optionStyle = StringPool.BLANK;

									if (Validator.isNotNull(portletPreferences.getValue("esaUrl_" + LocaleUtil.toLanguageId(locales[i]), StringPool.BLANK))) {
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
					<tr>
						<td colspan="2">
							<br />
						</td>
					</tr>
					<tr>
						<td>
							<liferay-ui:message key="esa-url" />
						</td>
						<td>
							<aui:input label="" name='<%= "esaUrl_" + currentLanguageId %>' type="text" value="<%= esaUrl %>" />
						</td>
					</tr>
					<tr>
						<td>
							<liferay-ui:message key="esa-version-displayed" />
						</td>
						<td>
							<aui:input label="" name='<%= "esaVersion_" + currentLanguageId %>' type="text" value="<%= esaVersion %>" />
						</td>
					</tr>
					<tr>
						<td>
							<liferay-ui:message key="esa-required-to-accept" />
						</td>
						<td>
							<aui:input label="" name='<%= "esaVersionRequired_" + currentLanguageId %>' type="text" value="<%= esaVersionRequired %>" />
						</td>
					</tr>
				</table>
			</c:when>
			<c:when test='<%= tabs1.equals("evaluation-eula") %>'>
				<table class="lfr-table">
					<tr>
						<td>
							<liferay-ui:message key="language" />
						</td>
						<td>
							<aui:select label="" name="languageId" onChange='<%= renderResponse.getNamespace() + "updateLanguage(this);" %>'>

								<%
								for (int i = 0; i < locales.length; i++) {
									String optionStyle = StringPool.BLANK;

									if (Validator.isNotNull(portletPreferences.getValue("evaluationEulaUrl_" + LocaleUtil.toLanguageId(locales[i]), StringPool.BLANK))) {
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
					<tr>
						<td colspan="2">
							<br />
						</td>
					</tr>
					<tr>
						<td>
							<liferay-ui:message key="evaluation-eula-url" />
						</td>
						<td>
							<aui:input label="" name='<%= "evaluationEulaUrl_" + currentLanguageId %>' type="text" value="<%= evaluationEulaUrl %>" />
						</td>
					</tr>
					<tr>
						<td>
							<liferay-ui:message key="eula-version-displayed" />
						</td>
						<td>
							<aui:input label="" name='<%= "evaluationEulaVersion_" + currentLanguageId %>' type="text" value="<%= evaluationEulaVersion %>" />
						</td>
					</tr>
					<tr>
						<td>
							<liferay-ui:message key="eula-version-required-to-accept" />
						</td>
						<td>
							<aui:input label="" name='<%= "evaluationEulaVersionRequired_" + currentLanguageId %>' type="text" value="<%= evaluationEulaVersionRequired %>" />
						</td>
					</tr>
				</table>
			</c:when>
			<c:when test='<%= tabs1.equals("guest-access") %>'>
				<aui:fieldset>
					<aui:input cssClass="lfr-input-text-container" label="guest-access-pattern" name="guestAccessPattern" value="<%= guestAccessPattern %>" />
				</aui:fieldset>
			</c:when>
			<c:when test='<%= tabs1.equals("studio-eula") %>'>
				<table class="lfr-table">
					<tr>
						<td>
							<liferay-ui:message key="language" />
						</td>
						<td>
							<aui:select label="" name="languageId" onChange='<%= renderResponse.getNamespace() + "updateLanguage(this);" %>'>

								<%
								for (int i = 0; i < locales.length; i++) {
									String optionStyle = StringPool.BLANK;

									if (Validator.isNotNull(portletPreferences.getValue("studioEulaUrl_" + LocaleUtil.toLanguageId(locales[i]), StringPool.BLANK))) {
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
					<tr>
						<td colspan="2">
							<br />
						</td>
					</tr>
					<tr>
						<td>
							<liferay-ui:message key="studio-eula-url" />
						</td>
						<td>
							<aui:input label="" name='<%= "studioEulaUrl_" + currentLanguageId %>' value="<%= studioEulaUrl %>" />
						</td>
					</tr>
					<tr>
						<td>
							<liferay-ui:message key="eula-version-displayed" />
						</td>
						<td>
							<aui:input label="" name='<%= "studioEulaVersion_" + currentLanguageId %>' value="<%= studioEulaVersion %>" />
						</td>
					</tr>
					<tr>
						<td>
							<liferay-ui:message key="eula-version-required-to-accept" />
						</td>
						<td>
							<aui:input label="" name='<%= "studioEulaVersionRequired_" + currentLanguageId %>' value="<%= studioEulaVersionRequired %>" />
						</td>
					</tr>
				</table>
			</c:when>
			<c:when test='<%= tabs1.equals("trial") %>'>
				<aui:fieldset>
					<aui:input cssClass="lfr-input-text-container" label="trial-pattern" name="trialPattern" value="<%= trialPattern %>" />
				</aui:fieldset>
			</c:when>
			<c:otherwise>
				<aui:fieldset>
					<aui:input cssClass="lfr-input-text-container" label="customer-access-pattern" name="customerAccessPattern" value="<%= customerAccessPattern %>" />
				</aui:fieldset>
			</c:otherwise>
		</c:choose>

		<aui:button type="submit" value="save" />
	</div>
</aui:form>