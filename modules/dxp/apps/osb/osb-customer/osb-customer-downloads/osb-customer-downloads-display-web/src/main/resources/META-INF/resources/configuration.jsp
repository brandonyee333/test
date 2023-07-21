<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String ddmStructureKey = portletPreferences.getValue("ddmStructureKey", null);
%>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

	<div class="portlet-configuration-body-content">
		<div class="container-fluid-1280">
			<aui:fieldset-group markupView="lexicon">
				<aui:fieldset>
					<aui:select label="ddm-structure-key" name="preferences--ddmStructureKey--">
						<aui:option value="" />

						<%
						for (String curDDMStructureKey : DownloadsDDMStructureConstants.KEYS) {
						%>

							<aui:option label="<%= curDDMStructureKey %>" selected="<%= curDDMStructureKey.equals(ddmStructureKey) %>" value="<%= curDDMStructureKey %>" />

						<%
						}
						%>

					</aui:select>
				</aui:fieldset>
			</aui:fieldset-group>
		</div>
	</div>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />
	</aui:button-row>
</aui:form>