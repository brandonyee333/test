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

<script type="text/javascript">
	function <portlet:namespace />removeFileEntry() {
		document.<portlet:namespace />fm.<portlet:namespace />studioEulaFileEntryId_<%= currentLanguageId %>.value = 0;

		var titleEl = document.getElementById("<portlet:namespace />studioEulaFileTitle");

		titleEl.innerHTML = "";
	}

	function <%= PortalUtil.getPortletNamespace(PortletKeys.DOCUMENT_LIBRARY) %>selectFileEntry(fileEntryId, title) {
		document.<portlet:namespace />fm.<portlet:namespace />studioEulaFileEntryId_<%= currentLanguageId %>.value = fileEntryId;

		var titleEl = document.getElementById("<portlet:namespace />studioEulaFileTitle");

		titleEl.innerHTML = title;
	}

	function <portlet:namespace />updateLanguage() {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = '';
		submitForm(document.<portlet:namespace />fm);
	}
</script>

<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="portletURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
	<portlet:param name="tabs1" value="<%= tabs1 %>" />
	<portlet:param name="redirect" value="<%= redirect %>" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="tabs1" type="hidden" value="<%= tabs1 %>" />
	<aui:input name="tabs2" type="hidden" value="<%= tabs2 %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name='<%= "studioEulaFileEntryId_" + currentLanguageId %>' type="hidden" value="<%= studioEulaFileEntryId %>" />

	<liferay-ui:tabs
		names="customer-access,guest-access,studio-eula,trial"
		param="tabs1"
		url="<%= portletURL %>"
	/>

	<liferay-ui:error key="studioEulaVersion" message="eula-version-must-be-greater-than-eula-version-required-to-accept" />

	<c:choose>
		<c:when test='<%= tabs1.equals("customer-access") %>'>
			<aui:fieldset>
				<aui:input cssClass="lfr-input-text-container" label="customer-access-pattern" name="customerAccessPattern" value="<%= customerAccessPattern %>" />
			</aui:fieldset>
		</c:when>
		<c:when test='<%= tabs1.equals("guest-access") %>'>
			<aui:fieldset>
				<aui:input cssClass="lfr-input-text-container" label="guest-access-pattern" name="guestAccessPattern" value="<%= guestAccessPattern %>" />
			</aui:fieldset>
		</c:when>
		<c:when test='<%= tabs1.equals("trial") %>'>
			<aui:fieldset>
				<aui:input cssClass="lfr-input-text-container" label="trial-pattern" name="trialPattern" value="<%= trialPattern %>" />
			</aui:fieldset>
		</c:when>
		<c:otherwise>
			<liferay-ui:tabs
				names="general,requirements"
				param="tabs2"
				url="<%= portletURL %>"
			/>

			<c:choose>
				<c:when test='<%= tabs2.equals("requirements") %>'>
					<table class="lfr-table">
					<tr>
						<td>
							<liferay-ui:message key="language" />
						</td>
						<td>
							<select name="<portlet:namespace />languageId" onChange="<portlet:namespace />updateLanguage(this);">

								<%
								for (int i = 0; i < locales.length; i++) {
									String optionStyle = StringPool.BLANK;

									if (Validator.isNotNull(portletPreferences.getValue("studioEulaFileEntryId_" + LocaleUtil.toLanguageId(locales[i]), StringPool.BLANK))) {
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
					<tr>
						<td colspan="2">
							<br />
						</td>
					</tr>
					<tr>
						<td>
							<liferay-ui:message key="eula-file" />
						</td>
						<td>
							<span id="<portlet:namespace />studioEulaFileTitle">
								<%= HtmlUtil.escape(studioEulaFileTitle) %>
							</span>

							<%
							PortletURL selectFileEntryURL = PortletURLFactoryUtil.create(request, PortletKeys.DOCUMENT_LIBRARY, themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);

							selectFileEntryURL.setParameter("struts_action", "/document_library/select_file_entry");
							selectFileEntryURL.setParameter("groupId", String.valueOf(scopeGroupId));
							selectFileEntryURL.setWindowState(LiferayWindowState.POP_UP);
							%>

							<input id="<portlet:namespace />selectFileEntryButton" onClick="var fileEntryWindow = window.open('<%= selectFileEntryURL.toString() %>', 'fileEntry', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); fileEntryWindow.focus();" type="button" value="<liferay-ui:message key="select" />">

							<input id="<portlet:namespace />removeFileEntryButton" onClick="<portlet:namespace />removeFileEntry();" type="button" value="<liferay-ui:message key="remove" />" />
						</td>
					</tr>
					<tr>
						<td>
							<liferay-ui:message key="eula-version-displayed" />
						</td>
						<td>

							<%
							List<DLFileVersion> fileVersions = new ArrayList<DLFileVersion>();

							if (fileEntry != null) {
								fileVersions = fileEntry.getFileVersions(WorkflowConstants.STATUS_ANY);
							}
							%>

							<select name="<portlet:namespace />studioEulaVersion_<%= currentLanguageId %>">
								<option value="0"></option>

								<%
								for (DLFileVersion fileVersion : fileVersions) {
								%>

									<option <%= studioEulaVersion.equals(fileVersion.getVersion()) ? "selected" : "" %> value="<%= fileVersion.getVersion() %>"><%= fileVersion.getVersion() %></option>

								<%
								}
								%>

							</select>
						</td>
					</tr>
					<tr>
						<td>
							<liferay-ui:message key="eula-version-required-to-accept" />
						</td>
						<td>

							<%
							fileVersions = new ArrayList<DLFileVersion>();

							if (fileEntry != null) {
								fileVersions = fileEntry.getFileVersions(WorkflowConstants.STATUS_ANY);
							}
							%>

							<select name="<portlet:namespace />studioEulaVersionRequired_<%= currentLanguageId %>">
								<option value="0"></option>

								<%
								for (DLFileVersion fileVersion : fileVersions) {
								%>

									<option <%= studioEulaVersionRequired.equals(fileVersion.getVersion()) ? "selected" : "" %> value="<%= fileVersion.getVersion() %>"><%= fileVersion.getVersion() %></option>

								<%
								}
								%>

							</select>
						</td>
					</tr>
					</table>
				</c:when>
				<c:otherwise>
					<aui:fieldset>
						<aui:input cssClass="lfr-input-text-container" label="protected-file-directory" name="fileDirectory" value="<%= fileDirectory %>" />

						<aui:input cssClass="lfr-input-text-container" label="download-page" name="downloadPage" value="<%= downloadPage %>" />
					</aui:fieldset>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>

	<input type="submit" value="<liferay-ui:message key="save" />" />
</aui:form>