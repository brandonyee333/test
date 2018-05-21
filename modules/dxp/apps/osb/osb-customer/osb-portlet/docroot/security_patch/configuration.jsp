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
String name = GetterUtil.getString(portletPreferences.getValue("name", null));
String fileName = GetterUtil.getString(portletPreferences.getValue("fileName", null));
String releaseNotesURL = GetterUtil.getString(portletPreferences.getValue("releaseNotesURL", null));
String footerText = GetterUtil.getString(portletPreferences.getValue("footerText", null));
%>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="actionURL" />

<aui:form action="<%= actionURL %>">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

	<liferay-ui:error exception="<%= ValidatorException.class %>">

		<%
		ValidatorException ve = (ValidatorException)errorException;
		%>

		<liferay-ui:message key="<%= ve.getMessage() %>" />
	</liferay-ui:error>

	<div class="security-patch-configuration unit w95">
		<div class="unit-content">
			<div class="callout-a">
				<div class="callout-content clearfix">
					<div class="content-column w20">
						<span class="txt-b"><liferay-ui:message key="name" /></span>
					</div>

					<div class="content-column w80">
						<input class="lfr-input-text-container" id="<portlet:namespace />name" name="<portlet:namespace />name" type="text" value="<%= HtmlUtil.escapeAttribute(name) %>" />
					</div>
				</div>

				<br />

				<div class="callout-content clearfix">
					<div class="content-column w20">
						<span class="txt-b"><liferay-ui:message key="release-notes-url" /></span>
					</div>

					<div class="content-column w80">
						<input class="lfr-input-text-container" id="<portlet:namespace />releaseNotesURL" name="<portlet:namespace />releaseNotesURL" type="text" value="<%= HtmlUtil.escapeAttribute(releaseNotesURL) %>" />
					</div>
				</div>

				<br />

				<div class="callout-content clearfix">
					<div class="content-column w20">
						<span class="txt-b"><liferay-ui:message key="file" /></span>
					</div>

					<div class="content-column w80">
						<c:if test="<%= Validator.isNotNull(fileName) %>">
							<div class="content-column w80">
								<span id="<portlet:namespace />filename"><%= HtmlUtil.escape(fileName) %></span>
							</div>

							<div class="content-column w20">
								<input class="aui-button-input" onClick="javascript:document.getElementById('<portlet:namespace />file').click(); return;" type="button" value="<liferay-ui:message key="upload-new" />" />
							</div>
						</c:if>

						<input class="lfr-input-text-container <%= Validator.isNull(fileName) ? "" : "hide" %>" id="<portlet:namespace />file" name="<portlet:namespace />file" onChange="<portlet:namespace />uploadUpdate();" type="file" />
					</div>
				</div>

				<br />

				<div class="callout-content clearfix">
					<div class="content-column w20">
						<span class="txt-b"><liferay-ui:message key="footer-text" /></span>
					</div>

					<div class="content-column w80">
						<textarea maxlength="<%= ModelHintsConstants.TEXTAREA_MAX_LENGTH %>" name="<portlet:namespace />footerText" onKeyDown="Liferay.Util.checkTab(this); Liferay.Util.disableEsc();" wrap="soft"><%= HtmlUtil.escape(footerText) %></textarea>
					</div>
				</div>

				<br />

				<div class="callout-content clearfix">
					<aui:button type="submit" />
				</div>
			</div>
		</div>
	</div>
</aui:form>

<aui:script>
	function <portlet:namespace />uploadUpdate(id) {
		var A = AUI();

		var filename = A.one('#<portlet:namespace />file').val();

		A.one('#<portlet:namespace />filename').html(filename);
	}
</aui:script>