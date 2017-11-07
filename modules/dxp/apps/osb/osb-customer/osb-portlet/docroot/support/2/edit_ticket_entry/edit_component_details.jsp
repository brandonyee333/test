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
		<div class="field-group">
			<label id="<portlet:namespace />serverCommunicationTypeLabel"><liferay-ui:message key="server-communication-type" /></label>

			<div class="field-align" field-required-message="<%= LanguageUtil.get(request, "please-select-a-valid-server-communication-type") %>">
				<span class="inline">
					<select data-field-required-status="<%= false %>" id="<portlet:namespace />serverCommunicationType" name="<portlet:namespace />serverCommunicationType">
						<option value=""></option>

						<%
						for (int curServerCommunicationType : TicketEntryConstants.CLUSTER_SERVER_COMMUNICATION_TYPES) {
						%>

							<option <%= (serverCommunicationType == curServerCommunicationType) ? "selected" : StringPool.BLANK %> value="<%= curServerCommunicationType %>"><liferay-ui:message key="<%= TicketEntryConstants.getClusterServerCommunicationTypeLabel(curServerCommunicationType) %>" /></option>

						<%
						}
						%>

					</select>
				</span>
			</div>
		</div>

		<div class="field-group">
			<label id="<portlet:namespace />numberOfNodesLabel"><liferay-ui:message key="number-of-nodes" /></label>

			<select id="<portlet:namespace />numberOfNodes" name="<portlet:namespace />numberOfNodes">
				<option value="0"></option>
				<option <%= (numberOfNodes == 1) ? "selected" : StringPool.BLANK %> value="1">1</option>
				<option <%= (numberOfNodes == 2) ? "selected" : StringPool.BLANK %> value="2">2</option>
				<option <%= (numberOfNodes == 3) ? "selected" : StringPool.BLANK %> value="3">3</option>
				<option <%= (numberOfNodes == 4) ? "selected" : StringPool.BLANK %> value="4">4</option>
				<option <%= (numberOfNodes == 5) ? "selected" : StringPool.BLANK %> value="5">5</option>
				<option <%= (numberOfNodes == 6) ? "selected" : StringPool.BLANK %> value="6">6</option>
				<option <%= (numberOfNodes == 7) ? "selected" : StringPool.BLANK %> value="7">7</option>
				<option <%= (numberOfNodes >= 8) ? "selected" : StringPool.BLANK %> value="8">8+</option>
			</select>
		</div>

		<div class="field-group">
			<label id="<portlet:namespace />serverConfigurationsLabel"><liferay-ui:message key="jvm-arguments-settings-optional" /></label>

			<liferay-util:include page="/common/textarea.jsp" servletContext="<%= application %>">
				<liferay-util:param name="content" value="<%= serverConfigurations %>" />
				<liferay-util:param name="editorId" value="serverConfigurations" />
				<liferay-util:param name="height" value="150" />
				<liferay-util:param name="name" value="serverConfigurations" />
				<liferay-util:param name="showCounter" value="<%= String.valueOf(Boolean.TRUE) %>" />
			</liferay-util:include>
		</div>
	</div>

	<div class="hide tab-content-tab" id="<portlet:namespace />activationKeyDetails">
		<div class="field-group">
			<label id="<portlet:namespace />typeLabel"><liferay-ui:message key="type-of-key" /></label>

			<select name="<portlet:namespace />type">
				<option value="0"></option>

				<%
				int[] types = TicketEntryConstants.getLicenseTypes();

				for (int curType : types) {
				%>

					<option <%= (curType == type) ? "selected" : "" %> value="<%= curType %>"><%= LanguageUtil.get(request, TicketEntryConstants.getLicenseTypeLabel(curType)) %></option>

				<%
				}
				%>

			</select>
		</div>

		<div class="field-group">
			<label id="<portlet:namespace />purposeLabel"><liferay-ui:message key="purpose" /></label>

			<select name="<portlet:namespace />purpose">
				<option value="0"></option>

				<%
				for (int curPurpose : TicketEntryConstants.getLicensePurposes()) {
				%>

					<option <%= (curPurpose == purpose) ? "selected" : "" %> value="<%= curPurpose %>"><%= LanguageUtil.get(request, TicketEntryConstants.getLicensePurposeLabel(curPurpose)) %></option>

				<%
				}
				%>

			</select>
		</div>

		<div class="field-group">
			<label id="<portlet:namespace />serverIdsLabel"><liferay-ui:message key="server-ids-mac-addresses" /></label>

			<liferay-util:include page="/common/textarea.jsp" servletContext="<%= application %>">
				<liferay-util:param name="content" value="<%= serverIds %>" />
				<liferay-util:param name="editorId" value="serverIds" />
				<liferay-util:param name="name" value="serverIds" />
				<liferay-util:param name="showCounter" value="<%= String.valueOf(Boolean.TRUE) %>" />
			</liferay-util:include>
		</div>

		<div class="field-group">
			<label id="<portlet:namespace />ipAddressesLabel"><liferay-ui:message key="ip-addresses" /></label>

			<liferay-util:include page="/common/textarea.jsp" servletContext="<%= application %>">
				<liferay-util:param name="content" value="<%= ipAddresses %>" />
				<liferay-util:param name="editorId" value="ipAddresses" />
				<liferay-util:param name="name" value="ipAddresses" />
				<liferay-util:param name="showCounter" value="<%= String.valueOf(Boolean.TRUE) %>" />
			</liferay-util:include>
		</div>

		<div class="field-group">
			<label id="<portlet:namespace />hostNamesLabel"><liferay-ui:message key="host-names" /></label>

			<liferay-util:include page="/common/textarea.jsp" servletContext="<%= application %>">
				<liferay-util:param name="content" value="<%= hostNames %>" />
				<liferay-util:param name="editorId" value="hostNames" />
				<liferay-util:param name="name" value="hostNames" />
				<liferay-util:param name="showCounter" value="<%= String.valueOf(Boolean.TRUE) %>" />
			</liferay-util:include>
		</div>

		<div class="field-group">
			<label id="<portlet:namespace />additionalCommentsLabel"><liferay-ui:message key="additional-comments-unstable-server-details-multiple-jvms-etc" /></label>

			<liferay-util:include page="/common/textarea.jsp" servletContext="<%= application %>">
				<liferay-util:param name="content" value="<%= additionalComments %>" />
				<liferay-util:param name="editorId" value="additionalComments" />
				<liferay-util:param name="height" value="150" />
				<liferay-util:param name="name" value="additionalComments" />
				<liferay-util:param name="showCounter" value="<%= String.valueOf(Boolean.TRUE) %>" />
			</liferay-util:include>
		</div>
	</div>

	<div class="hide tab-content-tab" id="<portlet:namespace />upgradeDetails">
		<div class="field-group">
			<label id="<portlet:namespace />docLibPersistenceLabel"><liferay-ui:message key="how-is-the-document-library-server-persisting-documents" /></label>

			<span class="inline">
				<select id="<portlet:namespace />docLibPersistence" name="<portlet:namespace />docLibPersistence">
					<option value="0"></option>

					<%
					for (int curDocLibPersistence : TicketEntryConstants.DOC_LIB_PERSISTENCES) {
					%>

						<option <%= (curDocLibPersistence == docLibPersistence) ? "selected" : StringPool.BLANK %> value="<%= curDocLibPersistence %>"><%= LanguageUtil.get(request, TicketEntryConstants.getDocLibPersistenceLabel(curDocLibPersistence)) %></option>

					<%
					}
					%>

				</select>
			</span>
		</div>

		<div class="field-group">
			<label id="<portlet:namespace />stepsToUpgradeLabel"><liferay-ui:message key="steps-used-to-perform-the-upgrade" /></label>

			<liferay-util:include page="/common/textarea.jsp" servletContext="<%= application %>">
				<liferay-util:param name="content" value="<%= stepsToUpgrade %>" />
				<liferay-util:param name="editorId" value="stepsToUpgrade" />
				<liferay-util:param name="height" value="150" />
				<liferay-util:param name="name" value="stepsToUpgrade" />
				<liferay-util:param name="showCounter" value="<%= String.valueOf(Boolean.TRUE) %>" />
			</liferay-util:include>
		</div>
	</div>
</c:if>