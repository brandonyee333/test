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

<%@ include file="/support/2/init.jsp" %>

<%
TicketEntry ticketEntry = (TicketEntry)request.getAttribute(OSBWebKeys.OSB_TICKET_ENTRY);

int component = (Integer)request.getAttribute("edit_ticket_entry_dialog.jsp-component");
int envLFR = (Integer)request.getAttribute("edit_ticket_entry_dialog.jsp-envLFR");
boolean hasUpdateAdmin = (Boolean)request.getAttribute("edit_ticket_entry_dialog.jsp-hasUpdateAdmin");
boolean hasUpdateAdvanced = (Boolean)request.getAttribute("edit_ticket_entry_dialog.jsp-hasUpdateAdvanced");
ProductEntry productEntry = (ProductEntry)request.getAttribute("edit_ticket_entry_dialog.jsp-productEntry");

Map<Long, String> ticketInformationFieldsMap = ticketEntry.getTicketInformationFieldsMap();

String additionalComments = ParamUtil.getString(request, "additionalComments", GetterUtil.getString(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_ADDITIONAL_COMMENTS)));
int docLibPersistence = ParamUtil.getInteger(request, "docLibPersistence", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_DOC_LIB_PERSISTENCE)));
String hostNames = ParamUtil.getString(request, "hostNames", GetterUtil.getString(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_HOST_NAMES)));
String ipAddresses = ParamUtil.getString(request, "ipAddresses", GetterUtil.getString(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_IP_ADDRESSES)));
int numberOfNodes = ParamUtil.getInteger(request, "numberOfNodes", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_CLUSTER_NUMBER_OF_NODES)));
int purpose = ParamUtil.getInteger(request, "purpose", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_LICENSE_PURPOSE)));
int serverCommunicationType = ParamUtil.getInteger(request, "serverCommunicationType", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_CLUSTER_SERVER_COMMUNICATION_TYPE)));
String serverConfigurations = ParamUtil.getString(request, "serverConfigurations", GetterUtil.getString(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_SERVER_CONFIGURATIONS)));
String serverIds = ParamUtil.getString(request, "serverIds", GetterUtil.getString(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_SERVER_IDS)));
String stepsToUpgrade = ParamUtil.getString(request, "stepsToUpgrade", GetterUtil.getString(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_STEPS_TO_UPGRADE)));
int type = ParamUtil.getInteger(request, "type", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_LICENSE_TYPE)));
%>

<c:if test="<%= hasUpdateAdvanced %>">
	<div class="hide tab-content-tab" id="<portlet:namespace />clusteringDetails">
		<aui:select inlineField="<%= true %>" label="server-communication-type" name="serverCommunicationType" wrapperCssClass="form-field-wrapper">
			<aui:option label="" value="" />

			<%
			for (int curServerCommunicationType : TicketEntryConstants.CLUSTER_SERVER_COMMUNICATION_TYPES) {
			%>

				<aui:option label="<%= TicketEntryConstants.getClusterServerCommunicationTypeLabel(curServerCommunicationType) %>" selected="<%= serverCommunicationType == curServerCommunicationType %>" value="<%= curServerCommunicationType %>" />

			<%
			}
			%>

			<aui:validator errorMessage="please-select-a-valid-server-communication-type" name="required">
				function() {
					return !A.one('#<portlet:namespace />clusteringDetailsHeader').hasClass('hide');
				}
			</aui:validator>
		</aui:select>

		<aui:select inlineField="<%= true %>" label="number-of-nodes" name="numberOfNodes" wrapperCssClass="form-field-wrapper">
			<aui:option label="" value="0" />
			<aui:option label="1" selected="<%= numberOfNodes == 1 %>" value="1" />
			<aui:option label="2" selected="<%= numberOfNodes == 2 %>" value="2" />
			<aui:option label="3" selected="<%= numberOfNodes == 3 %>" value="3" />
			<aui:option label="4" selected="<%= numberOfNodes == 4 %>" value="4" />
			<aui:option label="5" selected="<%= numberOfNodes == 5 %>" value="5" />
			<aui:option label="6" selected="<%= numberOfNodes == 6 %>" value="6" />
			<aui:option label="7" selected="<%= numberOfNodes == 7 %>" value="7" />
			<aui:option label="8+" selected="<%= numberOfNodes >= 8 %>" value="8" />
		</aui:select>

		<aui:field-wrapper cssClass="form-field-wrapper">
			<label class="control-label" id="<portlet:namespace />serverConfigurationsLabel"><liferay-ui:message key="jvm-arguments-settings-optional" /></label>

			<liferay-util:include page="/common/textarea.jsp" servletContext="<%= application %>">
				<liferay-util:param name="content" value="<%= serverConfigurations %>" />
				<liferay-util:param name="editorId" value="serverConfigurations" />
				<liferay-util:param name="height" value="150" />
				<liferay-util:param name="name" value="serverConfigurations" />
				<liferay-util:param name="showCounter" value="<%= Boolean.TRUE.toString() %>" />
			</liferay-util:include>
		</aui:field-wrapper>
	</div>

	<div class="hide tab-content-tab" id="<portlet:namespace />activationKeyDetails">
		<aui:select inlineField="<%= true %>" label="type-of-key" name="type" wrapperCssClass="form-field-wrapper">
			<aui:option label="" value="0" />

			<%
			int[] types = TicketEntryConstants.getLicenseTypes();

			for (int curType : types) {
			%>

				<aui:option label="<%= TicketEntryConstants.getLicenseTypeLabel(curType) %>" selected="<%= curType == type %>" value="<%= curType %>" />

			<%
			}
			%>

		</aui:select>

		<aui:select inlineField="<%= true %>" label="purpose" name="purpose" wrapperCssClass="form-field-wrapper">
			<aui:option label="" value="0" />

			<%
			for (int curPurpose : TicketEntryConstants.getLicensePurposes()) {
			%>

				<aui:option label="<%= TicketEntryConstants.getLicensePurposeLabel(curPurpose) %>" selected="<%= curPurpose == purpose %>" value="<%= curPurpose %>" />

			<%
			}
			%>

		</aui:select>

		<aui:field-wrapper cssClass="form-field-wrapper">
			<label class="control-label" id="<portlet:namespace />serverIdsLabel"><liferay-ui:message key="server-ids-mac-addresses" /></label>

			<liferay-util:include page="/common/textarea.jsp" servletContext="<%= application %>">
				<liferay-util:param name="content" value="<%= serverIds %>" />
				<liferay-util:param name="editorId" value="serverIds" />
				<liferay-util:param name="name" value="serverIds" />
				<liferay-util:param name="showCounter" value="<%= Boolean.TRUE.toString() %>" />
			</liferay-util:include>
		</aui:field-wrapper>

		<aui:field-wrapper cssClass="form-field-wrapper">
			<label class="control-label" id="<portlet:namespace />ipAddressesLabel"><liferay-ui:message key="ip-addresses" /></label>

			<liferay-util:include page="/common/textarea.jsp" servletContext="<%= application %>">
				<liferay-util:param name="content" value="<%= ipAddresses %>" />
				<liferay-util:param name="editorId" value="ipAddresses" />
				<liferay-util:param name="name" value="ipAddresses" />
				<liferay-util:param name="showCounter" value="<%= Boolean.TRUE.toString() %>" />
			</liferay-util:include>
		</aui:field-wrapper>

		<aui:field-wrapper cssClass="form-field-wrapper">
			<label class="control-label" id="<portlet:namespace />hostNamesLabel"><liferay-ui:message key="host-names" /></label>

			<liferay-util:include page="/common/textarea.jsp" servletContext="<%= application %>">
				<liferay-util:param name="content" value="<%= hostNames %>" />
				<liferay-util:param name="editorId" value="hostNames" />
				<liferay-util:param name="name" value="hostNames" />
				<liferay-util:param name="showCounter" value="<%= Boolean.TRUE.toString() %>" />
			</liferay-util:include>
		</aui:field-wrapper>

		<aui:field-wrapper cssClass="form-field-wrapper">
			<label class="control-label" id="<portlet:namespace />additionalCommentsLabel"><liferay-ui:message key="additional-comments-unstable-server-details-multiple-jvms-etc" /></label>

			<liferay-util:include page="/common/textarea.jsp" servletContext="<%= application %>">
				<liferay-util:param name="content" value="<%= additionalComments %>" />
				<liferay-util:param name="editorId" value="additionalComments" />
				<liferay-util:param name="height" value="150" />
				<liferay-util:param name="name" value="additionalComments" />
				<liferay-util:param name="showCounter" value="<%= Boolean.TRUE.toString() %>" />
			</liferay-util:include>
		</aui:field-wrapper>
	</div>

	<div class="hide tab-content-tab" id="<portlet:namespace />upgradeDetails">
		<aui:select inlineField="<%= true %>" label="how-is-the-document-library-server-persisting-documents" name="docLibPersistence" wrapperCssClass="form-field-wrapper">
			<aui:option label="" value="0" />

			<%
			for (int curDocLibPersistence : TicketEntryConstants.DOC_LIB_PERSISTENCES) {
			%>

				<aui:option label="<%= TicketEntryConstants.getDocLibPersistenceLabel(curDocLibPersistence) %>" selected="<%= curDocLibPersistence == docLibPersistence %>" value="<%= curDocLibPersistence %>" />

			<%
			}
			%>

		</aui:select>

		<aui:field-wrapper cssClass="form-field-wrapper">
			<label class="control-label" id="<portlet:namespace />stepsToUpgradeLabel"><liferay-ui:message key="steps-used-to-perform-the-upgrade" /></label>

			<liferay-util:include page="/common/textarea.jsp" servletContext="<%= application %>">
				<liferay-util:param name="content" value="<%= stepsToUpgrade %>" />
				<liferay-util:param name="editorId" value="stepsToUpgrade" />
				<liferay-util:param name="height" value="150" />
				<liferay-util:param name="name" value="stepsToUpgrade" />
				<liferay-util:param name="showCounter" value="<%= Boolean.TRUE.toString() %>" />
			</liferay-util:include>
		</aui:field-wrapper>
	</div>
</c:if>