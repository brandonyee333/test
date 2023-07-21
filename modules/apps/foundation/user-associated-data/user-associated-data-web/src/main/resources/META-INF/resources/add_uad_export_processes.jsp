<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String backURL = ParamUtil.getString(request, "backURL");

List<UADApplicationExportDisplay> uadApplicationExportDisplayList = (List<UADApplicationExportDisplay>)request.getAttribute(UADWebKeys.UAD_APPLICATION_EXPORT_DISPLAY_LIST);

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(backURL);

renderResponse.setTitle(StringBundler.concat(selectedUser.getFullName(), " - ", LanguageUtil.get(request, "new-data-export")));
%>

<portlet:renderURL var="viewUADExportProcesses">
	<portlet:param name="mvcRenderCommandName" value="/view_uad_export_processes" />
	<portlet:param name="p_u_i_d" value="<%= String.valueOf(selectedUser.getUserId()) %>" />
</portlet:renderURL>

<div class="container-fluid-1280 uad-add-export-form-wrapper">
	<aui:form method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "exportApplicationData();" %>'>
		<aui:input name="redirect" type="hidden" value="<%= viewUADExportProcesses.toString() %>" />
		<aui:input name="p_u_i_d" type="hidden" value="<%= String.valueOf(selectedUser.getUserId()) %>" />
		<aui:input name="applicationKeys" type="hidden" />

		<div class="card-block card-horizontal main-content-card">
			<div class="add-form-help-text">
				<liferay-ui:message key="please-select-the-applications-for-which-you-want-to-start-an-export-process" />
			</div>

			<%
			boolean disableManagementBar = true;

			for (UADApplicationExportDisplay uadApplicationExportDisplay : uadApplicationExportDisplayList) {
				if (uadApplicationExportDisplay.getDataCount() > 0) {
					disableManagementBar = false;

					break;
				}
			}
			%>

			<liferay-frontend:management-bar
				disabled="<%= disableManagementBar %>"
				includeCheckBox="<%= true %>"
				searchContainerId="uadApplicationExportDisplay"
			/>

			<liferay-ui:search-container
				id="uadApplicationExportDisplay"
				iteratorURL="<%= currentURLObj %>"
				rowChecker="<%= new UADApplicationExportDisplayChecker(renderResponse) %>"
				total="<%= uadApplicationExportDisplayList.size() %>"
			>
				<liferay-ui:search-container-results
					results="<%= ListUtil.subList(uadApplicationExportDisplayList, searchContainer.getStart(), searchContainer.getEnd()) %>"
				/>

				<liferay-ui:search-container-row
					className="com.liferay.user.associated.data.web.internal.display.UADApplicationExportDisplay"
					keyProperty="applicationKey"
					modelVar="uadApplicationExportDisplay"
				>
					<liferay-ui:search-container-column-text
						cssClass="table-cell-content"
						name="application"
						value="<%= UADLanguageUtil.getApplicationName(uadApplicationExportDisplay.getApplicationKey(), locale) %>"
					/>

					<liferay-ui:search-container-column-text
						cssClass="table-cell-content"
						name="items"
						value="<%= String.valueOf(uadApplicationExportDisplay.getDataCount()) %>"
					/>

					<%
					Format dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat("yyyy.MM.dd - hh:mm a", locale, themeDisplay.getTimeZone());

					Date lastExportDate = uadApplicationExportDisplay.getLastExportDate();
					%>

					<liferay-ui:search-container-column-text
						cssClass="table-cell-content"
						name="last-available-export"
					>
						<%= (lastExportDate != null) ? dateFormat.format(lastExportDate) : StringPool.DASH %>
					</liferay-ui:search-container-column-text>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator
					markupView="lexicon"
				/>
			</liferay-ui:search-container>

			<aui:button-row>
				<aui:button primary="<%= true %>" type="submit" value="export" />

				<aui:button href="<%= backURL %>" type="cancel" />
			</aui:button-row>
		</div>
	</aui:form>
</div>

<aui:script>
	function <portlet:namespace />exportApplicationData() {
		var form = document.getElementById('<portlet:namespace />fm');

		if (form) {
			var applicationKeys = form.querySelector('#<portlet:namespace />applicationKeys');

			if (applicationKeys) {
				applicationKeys.setAttribute(
					'value',
					Liferay.Util.listCheckedExcept(form, '<portlet:namespace />allRowIds', '<portlet:namespace />rowIds')
				);
			}

			submitForm(form, '<portlet:actionURL name="/export_application_data" />');
		}
	}
</aui:script>