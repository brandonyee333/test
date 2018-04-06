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
String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);

long supportResponseId = ParamUtil.getLong(request, "supportResponseId");

SupportResponse supportResponse = null;

try {
	supportResponse = SupportResponseLocalServiceUtil.getSupportResponse(supportResponseId);
}
catch (NoSuchSupportResponseException nssre) {
}

int supportLevel = BeanParamUtil.getInteger(supportResponse, request, "supportLevel");
int severity1Response = BeanParamUtil.getInteger(supportResponse, request, "severity1Response");
int severity1Resolution = BeanParamUtil.getInteger(supportResponse, request, "severity1Resolution");
int severity2Response = BeanParamUtil.getInteger(supportResponse, request, "severity2Response");
int severity2Resolution = BeanParamUtil.getInteger(supportResponse, request, "severity2Resolution");
int severity3Response = BeanParamUtil.getInteger(supportResponse, request, "severity3Response");
int severity3Resolution = BeanParamUtil.getInteger(supportResponse, request, "severity3Resolution");
String languageId = BeanParamUtil.getString(supportResponse, request, "languageId");
%>

<portlet:actionURL name="updateSupportResponse" var="updateSupportResponseURL">
	<portlet:param name="mvcPath" value="/admin/edit_support_response.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateSupportResponseURL %>" method="post">
	<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>" />
	<input name="<portlet:namespace />backURL" type="hidden" value="<%= HtmlUtil.escape(backURL) %>" />
	<input name="<portlet:namespace />supportResponseId" type="hidden" value="<%= supportResponseId %>" />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="support"
	/>

	<liferay-ui:error exception="<%= DuplicateSupportResponseException.class %>" message="please-enter-a-unique-name" />
	<liferay-ui:error exception="<%= SupportResponseNameException.class %>" message="please-enter-a-valid-name" />
	<liferay-ui:error exception="<%= SupportResponseSupportLevelException.class %>" message="please-enter-a-valid-service-level-agreement" />

	<table class="lfr-table">
		<tr>
			<td>
				<liferay-ui:message key="name" />
			</td>
			<td>
				<liferay-ui:input-field
					bean="<%= supportResponse %>"
					field="name"
					model="<%= SupportResponse.class %>"
				/>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="service-level-agreement" />
			</td>
			<td>
				<aui:select label="" name="supportLevel">

					<%
					for (int curSupportLevel : SupportResponseConstants.SUPPORT_LEVELS) {
					%>

						<option <%= (curSupportLevel == supportLevel) ? "selected" : "" %> value="<%= curSupportLevel %>"><liferay-ui:message key="<%= SupportResponseConstants.getSupportLevelLabel(curSupportLevel) %>" /></option>

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
				<liferay-ui:message key="severity-1" />
			</td>
			<td>
				<table class="lfr-table">
					<tr>
						<td>
							<liferay-ui:message key="response" />
						</td>
						<td>
							<aui:select label="" name="severity1Response">
								<option></option>

								<%
								for (int severityResponse : _SEVERITY_RESPONSES) {
								%>

									<option <%= (severity1Response == severityResponse) ? "selected" : "" %> value="<%= severityResponse %>"><%= LanguageUtil.format(request, "x-hours", severityResponse) %></option>

								<%
								}
								%>

							</aui:select>
						</td>
						<td>
							<liferay-ui:message key="resolution" />
						</td>
						<td>
							<aui:select label="" name="severity1Resolution">
								<option></option>

								<%
								for (int severityResolution : _SEVERITY_RESOLUTIONS) {
								%>

									<option <%= (severity1Resolution == severityResolution) ? "selected" : "" %> value="<%= severityResolution %>"><%= LanguageUtil.format(request, "x-days", severityResolution) %></option>

								<%
								}
								%>

							</aui:select>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="severity-2" />
			</td>
			<td>
				<table class="lfr-table">
					<tr>
						<td>
							<liferay-ui:message key="response" />
						</td>
						<td>
							<aui:select label="" name="severity2Response">
								<option></option>

								<%
								for (int severityResponse : _SEVERITY_RESPONSES) {
								%>

									<option <%= (severity2Response == severityResponse) ? "selected" : "" %> value="<%= severityResponse %>"><%= LanguageUtil.format(request, "x-hours", severityResponse) %></option>

								<%
								}
								%>

							</aui:select>
						</td>
						<td>
							<liferay-ui:message key="resolution" />
						</td>
						<td>
							<aui:select label="" name="severity2Resolution">
								<option></option>

								<%
								for (int severityResolution : _SEVERITY_RESOLUTIONS) {
								%>

									<option <%= (severity2Resolution == severityResolution) ? "selected" : "" %> value="<%= severityResolution %>"><%= LanguageUtil.format(request, "x-days", severityResolution) %></option>

								<%
								}
								%>

							</aui:select>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="severity-3" />
			</td>
			<td>
				<table class="lfr-table">
					<tr>
						<td>
							<liferay-ui:message key="response" />
						</td>
						<td>
							<aui:select label="" name="severity3Response">
								<option></option>

								<%
								for (int severityResponse : _SEVERITY_RESPONSES) {
								%>

									<option <%= (severity3Response == severityResponse) ? "selected" : "" %> value="<%= severityResponse %>"><%= LanguageUtil.format(request, "x-hours", severityResponse) %></option>

								<%
								}
								%>

							</aui:select>
						</td>
						<td>
							<liferay-ui:message key="resolution" />
						</td>
						<td>
							<aui:select label="" name="severity3Resolution">
								<option></option>

								<%
								for (int severityResolution : _SEVERITY_RESOLUTIONS) {
								%>

									<option <%= (severity3Resolution == severityResolution) ? "selected" : "" %> value="<%= severityResolution %>"><%= LanguageUtil.format(request, "x-days", severityResolution) %></option>

								<%
								}
								%>

							</aui:select>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

	<br />

	<aui:button type="submit" value="save" />

	<a class="btn btn-default" href="<%= HtmlUtil.escape(backURL) %>"><liferay-ui:message key="cancel" /></a>
</aui:form>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<script type="text/javascript">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />name);
	</script>
</c:if>

<%!
private static final int[] _SEVERITY_RESPONSES = {1, 2, 4, 8, 24, 48};

private static final int[] _SEVERITY_RESOLUTIONS = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
%>