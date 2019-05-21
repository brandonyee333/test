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
String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);

long supportResponseId = ParamUtil.getLong(request, "supportResponseId");

SupportResponse supportResponse = SupportResponseLocalServiceUtil.fetchSupportResponse(supportResponseId);

int supportLevel = BeanParamUtil.getInteger(supportResponse, request, "supportLevel");
int severity1Response = BeanParamUtil.getInteger(supportResponse, request, "severity1Response");
int severity1Resolution = BeanParamUtil.getInteger(supportResponse, request, "severity1Resolution");
int severity2Response = BeanParamUtil.getInteger(supportResponse, request, "severity2Response");
int severity2Resolution = BeanParamUtil.getInteger(supportResponse, request, "severity2Resolution");
int severity3Response = BeanParamUtil.getInteger(supportResponse, request, "severity3Response");
int severity3Resolution = BeanParamUtil.getInteger(supportResponse, request, "severity3Resolution");
%>

<portlet:actionURL name="updateSupportResponse" var="updateSupportResponseURL">
	<portlet:param name="mvcPath" value="/admin/edit_support_response.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateSupportResponseURL %>" method="post">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="supportResponseId" type="hidden" value="<%= supportResponseId %>" />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="support"
	/>

	<liferay-ui:error exception="<%= DuplicateSupportResponseException.class %>" message="please-enter-a-unique-name" />
	<liferay-ui:error exception="<%= SupportResponseNameException.class %>" message="please-enter-a-valid-name" />

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

						<aui:option label="<%= SupportResponseConstants.getSupportLevelLabel(curSupportLevel) %>" selected="<%= curSupportLevel == supportLevel %>" value="<%= curSupportLevel %>" />

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
								<aui:option />

								<%
								for (int severityResponse : _SEVERITY_RESPONSES) {
								%>

									<aui:option label='<%= LanguageUtil.format(request, "x-hours", severityResponse) %>' selected="<%= severity1Response == severityResponse %>" value="<%= severityResponse %>" />

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
								<aui:option />

								<%
								for (int severityResolution : _SEVERITY_RESOLUTIONS) {
								%>

									<aui:option label='<%= LanguageUtil.format(request, "x-days", severityResolution) %>' selected="<%= severity1Resolution == severityResolution %>" value="<%= severityResolution %>" />

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
								<aui:option />

								<%
								for (int severityResponse : _SEVERITY_RESPONSES) {
								%>

									<aui:option label='<%= LanguageUtil.format(request, "x-hours", severityResponse) %>' selected="<%= severity2Response == severityResponse %>" value="<%= severityResponse %>" />

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
								<aui:option />

								<%
								for (int severityResolution : _SEVERITY_RESOLUTIONS) {
								%>

									<aui:option label='<%= LanguageUtil.format(request, "x-days", severityResolution) %>' selected="<%= severity2Resolution == severityResolution %>" value="<%= severityResolution %>" />

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
								<aui:option />

								<%
								for (int severityResponse : _SEVERITY_RESPONSES) {
								%>

									<aui:option label='<%= LanguageUtil.format(request, "x-hours", severityResponse) %>' selected="<%= severity3Response == severityResponse %>" value="<%= severityResponse %>" />

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
								<aui:option />

								<%
								for (int severityResolution : _SEVERITY_RESOLUTIONS) {
								%>

									<aui:option label='<%= LanguageUtil.format(request, "x-days", severityResolution) %>' selected="<%= severity3Resolution == severityResolution %>" value="<%= severityResolution %>" />

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

	<aui:a cssClass="btn btn-default" href="<%= backURL %>" label="cancel" />
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