<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

	<div class="portlet-configuration-body-content">
		<div class="container-fluid-1280">
			<aui:fieldset-group markupView="lexicon">
				<aui:fieldset>
					<aui:input name="preferences--mapAddress--" type="text" value="<%= mapAddress %>" wrapperCssClass="lfr-input-text-container" />

					<aui:input label="allow-map-address-to-be-edited" name="preferences--mapInputEnabled--" type="toggle-switch" value="<%= mapInputEnabled %>" />

					<aui:input name="preferences--directionsAddress--" type="text" value="<%= directionsAddress %>" wrapperCssClass="lfr-input-text-container" />

					<aui:input label="allow-directions-address-to-be-edited" name="preferences--directionsInputEnabled--" type="toggle-switch" value="<%= directionsInputEnabled %>" />

					<aui:input name="preferences--showDirectionSteps--" type="toggle-switch" value="<%= showDirectionSteps %>" />

					<aui:input name="preferences--enableChangingTravelingMode--" type="toggle-switch" value="<%= enableChangingTravelingMode %>" />

					<aui:input name="preferences--height--" size="4" suffix="px" type="text" value="<%= height %>" />

					<aui:input name="preferences--showGoogleMapsLink--" type="toggle-switch" value="<%= showGoogleMapsLink %>" />
				</aui:fieldset>
			</aui:fieldset-group>
		</div>
	</div>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />

		<aui:button cssClass="btn-lg" href='<%= ParamUtil.getString(request, "redirect") %>' type="cancel" />
	</aui:button-row>
</aui:form>