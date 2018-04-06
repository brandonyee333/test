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
long accountEnvironmentId = ParamUtil.getLong(request, "accountEnvironmentId");

AccountEnvironment accountEnvironment = AccountEnvironmentLocalServiceUtil.fetchAccountEnvironment(accountEnvironmentId);

long accountEntryId = BeanParamUtil.getLong(accountEnvironment, request, "accountEntryId");

AccountEntry accountEntry = null;

if (accountEntryId > 0) {
	accountEntry = AccountEntryServiceUtil.getAccountEntry(accountEntryId);
}

long productEntryId = BeanParamUtil.getLong(accountEnvironment, request, "productEntryId");
int envOS = BeanParamUtil.getInteger(accountEnvironment, request, "envOS");
String envOSCustom = BeanParamUtil.getString(accountEnvironment, request, "envOSCustom");
int envDB = BeanParamUtil.getInteger(accountEnvironment, request, "envDB");
int envJVM = BeanParamUtil.getInteger(accountEnvironment, request, "envJVM");
int envAS = BeanParamUtil.getInteger(accountEnvironment, request, "envAS");
int envLFR = BeanParamUtil.getInteger(accountEnvironment, request, "envLFR");
%>

<portlet:actionURL name="updateAccountEnvironment" var="updateAccountEnvironmentURL">
	<portlet:param name="mvcPath" value="/admin/edit_account_environment.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateAccountEnvironmentURL %>" method="post">
	<aui:input name="accountEntryId" type="hidden" value="<%= accountEntryId %>" />
	<aui:input name="accountEnvironmentId" type="hidden" value="<%= accountEnvironmentId %>" />
	<aui:input name="productEntryId" type="hidden" value="<%= productEntryId %>" />

	<liferay-ui:tabs
		names="environment-details"
	/>

	<liferay-ui:error exception="<%= AccountEnvironmentEnvASException.class %>" message="please-select-a-valid-application-server" />
	<liferay-ui:error exception="<%= AccountEnvironmentEnvDBException.class %>" message="please-select-a-valid-database" />
	<liferay-ui:error exception="<%= AccountEnvironmentEnvOSException.class %>" message="please-select-a-valid-operating-system" />

	<%
	ProductEntry productEntry = ProductEntryLocalServiceUtil.getProductEntry(productEntryId);
	%>

	<liferay-ui:error exception="<%= AccountEnvironmentEnvLFRException.class %>">
		<c:choose>
			<c:when test="<%= (productEntry != null) && productEntry.isSocialOffice() %>">
				<liferay-ui:message key="please-select-a-valid-social-office-version" />
			</c:when>
			<c:otherwise>
				<liferay-ui:message key="please-select-a-valid-liferay-version" />
			</c:otherwise>
		</c:choose>
	</liferay-ui:error>

	<liferay-ui:error exception="<%= AccountEnvironmentAttachmentSizeException.class %>">

		<%
		AccountEnvironmentAttachmentSizeException aease = (AccountEnvironmentAttachmentSizeException)errorException;
		%>

		<c:choose>
			<c:when test="<%= aease.getType() == AccountEnvironmentAttachmentSizeException.EMPTY_FILE %>">
				<liferay-ui:message key="the-file-contains-no-data-and-cannot-be-uploaded" />
			</c:when>
			<c:when test="<%= aease.getType() == AccountEnvironmentAttachmentSizeException.EXCEEDS_LIMIT %>">
				<liferay-ui:message key="please-upload-a-file-less-than-100-mb" />
			</c:when>
		</c:choose>
	</liferay-ui:error>

	<liferay-ui:error exception="<%= AccountEnvironmentNameException.class %>" message="please-provide-a-unique-environment-name-for-the-product" />
	<liferay-ui:error exception="<%= DuplicateAccountEnvironmentException.class %>" message="please-provide-a-unique-environment-name" />

	<aui:model-context bean="<%= accountEnvironment %>" model="<%= AccountEnvironment.class %>" />

	<div class="account-environment">
		<div class="popup">
			<table class="lfr-table">
				<tr>
					<td colspan="3">
						<aui:input inlineField="<%= true %>" name="name" />
					</td>
				</tr>
				<tr>
					<td>
						<aui:select label='<%= productEntry.isSocialOffice() ? "social-office-version" : "liferay-version" %>' name="envLFR" onChange='<%= renderResponse.getNamespace() + "selectPortalVersion(this.value, 0, '', 0, '', 0, '', 0, '');" %>'>
							<option value="0"></option>

							<%
							List<ListType> envLFRTypes = productEntry.getAllVersionsListTypes();

							String previousNamePrefix = StringPool.BLANK;

							for (ListType envLFRType : envLFRTypes) {
								if ((envLFRType.getListTypeId() == ProductEntryConstants.PORTAL_VERSION_OTHER) && (envLFR != ProductEntryConstants.PORTAL_VERSION_OTHER)) {
									continue;
								}

								String name = envLFRType.getName();

								String namePrefix = name.substring(0, 3);
							%>

								<c:if test="<%= Validator.isNotNull(previousNamePrefix) && !previousNamePrefix.equals(namePrefix) %>">
									<option disabled>--------</option>
								</c:if>

								<option <%= (envLFRType.getListTypeId() == envLFR) ? "selected" : "" %> value="<%= envLFRType.getListTypeId() %>"><%= LanguageUtil.get(request, envLFRType.getName()) %></option>

							<%
								previousNamePrefix = namePrefix;
							}
							%>

						</aui:select>
					</td>
					<td>
						<aui:select label="application-server" name="envAS">
							<option value="0"></option>
						</aui:select>
					</td>
					<td>
						<aui:select label="database" name="envDB">
							<option value="0"></option>
						</aui:select>
					</td>
				</tr>
				<tr>
					<td>
						<div class="pull-left">
							<aui:select label="operating-system" name="envOS" onChange='<%= renderResponse.getNamespace() + "selectEnvOS(this.value);" %>'>
								<option value="0"></option>
							</aui:select>

							<br />

							<aui:input cssClass='<%= (envOS == TicketEntryConstants.ENV_OS_OTHER) ? "" : "hide" %>' label="" maxLength="<%= TicketInformationConstants.getMaxLength(TicketInformationConstants.FIELD_ENV_OS_CUSTOM) %>" name="envOSCustom" type="text" value="<%= HtmlUtil.escapeAttribute(envOSCustom) %>" />
						</div>
					</td>
					<td>
						<aui:select label="java-virtual-machine" name="envJVM">
							<option value="0"></option>
						</aui:select>
					</td>
					<td />
				</tr>
				<tr>
					<td colspan="3">
						<span><label for="<portlet:namespace />portal-ext"><liferay-ui:message key="portal-ext" /></label>:</span>

						<%
						AccountEnvironmentAttachment portalExtAccountEnvironmentAttachment = null;

						if (accountEnvironment != null) {
							portalExtAccountEnvironmentAttachment = AccountEnvironmentAttachmentLocalServiceUtil.fetchAccountEnvironmentAttachment(accountEnvironmentId, AccountEnvironmentAttachmentConstants.TYPE_PORTAL_EXT);
						}
						%>

						<c:if test="<%= portalExtAccountEnvironmentAttachment != null %>">

							<%
							LiferayPortletURL accountEnvironmentAttachmentURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

							accountEnvironmentAttachmentURL.setCopyCurrentRenderParameters(false);
							accountEnvironmentAttachmentURL.setParameter("accountEnvironmentAttachmentId", String.valueOf(portalExtAccountEnvironmentAttachment.getAccountEnvironmentAttachmentId()));
							accountEnvironmentAttachmentURL.setResourceID("accountEnvironmentAttachment");
							%>

							<a href="<%= accountEnvironmentAttachmentURL.toString() %>" target="_blank"><%= HtmlUtil.escape(portalExtAccountEnvironmentAttachment.getFileName()) %></a>
						</c:if>

						<aui:input label="" name="portal-ext" type="file" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<span><label for="<portlet:namespace />patch-level"><liferay-ui:message key="patch-level" /></label>:</span>

						<%
						AccountEnvironmentAttachment patchLevelAccountEnvironmentAttachment = null;

						if (accountEnvironment != null) {
							patchLevelAccountEnvironmentAttachment = AccountEnvironmentAttachmentLocalServiceUtil.fetchAccountEnvironmentAttachment(accountEnvironmentId, AccountEnvironmentAttachmentConstants.TYPE_PATCH_LEVEL);
						}
						%>

						<c:if test="<%= patchLevelAccountEnvironmentAttachment != null %>">

							<%
							LiferayPortletURL accountEnvironmentAttachmentURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

							accountEnvironmentAttachmentURL.setCopyCurrentRenderParameters(false);
							accountEnvironmentAttachmentURL.setParameter("accountEnvironmentAttachmentId", String.valueOf(patchLevelAccountEnvironmentAttachment.getAccountEnvironmentAttachmentId()));
							accountEnvironmentAttachmentURL.setResourceID("accountEnvironmentAttachment");
							%>

							<a href="<%= accountEnvironmentAttachmentURL.toString() %>" target="_blank"><%= HtmlUtil.escape(patchLevelAccountEnvironmentAttachment.getFileName()) %></a>
						</c:if>

						<aui:input label="" name="patch-level" type="file" />
					</td>
				</tr>
				<tr>
					<td>
						<aui:button type="submit" value="save" />
					</td>
				</tr>
			</table>
		</div>
	</div>
</aui:form>

<aui:script use="aui-base,aui-io">
	<portlet:namespace />selectPortalVersion(<%= envLFR %>, <%= envAS %>, '<%= LanguageUtil.get(request, AccountEnvironmentConstants.getEnvLabel(envAS)) %>', <%= envDB %>, '<%= LanguageUtil.get(request, AccountEnvironmentConstants.getEnvLabel(envDB)) %>', <%= envJVM %>, '<%= LanguageUtil.get(request, AccountEnvironmentConstants.getEnvLabel(envJVM)) %>', <%= envOS %>, '<%= LanguageUtil.get(request, AccountEnvironmentConstants.getEnvLabel(envOS)) %>');
</aui:script>

<aui:script>
	function <portlet:namespace />selectEnvOS(envOS) {
		var A = AUI();

		var envOSCustom = A.one('#<portlet:namespace />envOSCustom');

		if (envOS == '<%= TicketEntryConstants.ENV_OS_OTHER %>') {
			envOSCustom.show();
		}
		else {
			envOSCustom.hide();

			envOSCustom.val('');
		}
	}

	function <portlet:namespace />updateEnvironmentField(selectId, selectDataKey, selectData, selectVal, selectName) {
		var A = AUI();

		var selectElement = A.one('#' + selectId);

		if (selectElement.getData('key') == selectDataKey) {
			return;
		}

		var selectValExists = false;

		selectElement.setData('key', selectDataKey);

		var selectOptions = [];

		selectOptions.push('<option value="0"></option>');

		if (selectData) {
			for (var i = 0; i < selectData.length; i++) {
				var value = selectData[i].value;
				var name = selectData[i].name;

				selectOptions.push('<option value="' + value + '">' + name + '</option>');

				if (value == selectVal) {
					selectValExists = true;
				}
			}
		}

		if (!selectValExists && (selectVal > 0)) {
			selectOptions.push('<option value="' + selectVal + '">' + selectName + '</option>');
		}

		selectOptions = selectOptions.join('');

		selectElement.empty();
		selectElement.append(selectOptions);
		selectElement.val(String(selectVal));
	}

	Liferay.provide(
		window,
		'<portlet:namespace />selectPortalVersion',
		function(envLFR, envAS, envASName, envDB, envDBName, envJVM, envJVMName, envOS, envOSName) {
			var A = AUI();

			if (envLFR <= 0) {
				A.one('#<portlet:namespace />envAS').empty();
				A.one('#<portlet:namespace />envDB').empty();
				A.one('#<portlet:namespace />envJVM').empty();
				A.one('#<portlet:namespace />envOS').empty();

				return;
			}

			A.io.request(
				'<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="ticketEnvironment" />',
				{
					data: {
						<portlet:namespace />envLFR: envLFR
					},
					dataType: 'JSON',
					method: 'POST',
					on: {
						success: function() {
							var response = this.get('responseData');

							<portlet:namespace />updateEnvironmentField('<portlet:namespace />envAS', response['ENV_AS#key'], response['ENV_AS'], envAS, envASName);
							<portlet:namespace />updateEnvironmentField('<portlet:namespace />envDB', response['ENV_DB#key'], response['ENV_DB'], envDB, envDBName);
							<portlet:namespace />updateEnvironmentField('<portlet:namespace />envJVM', response['ENV_JVM#key'], response['ENV_JVM'], envJVM, envJVMName);
							<portlet:namespace />updateEnvironmentField('<portlet:namespace />envOS', response['ENV_OS#key'], response['ENV_OS'], envOS, envOSName);
						}
					}
				}
			);
		},
		['aui-io']
	);
</aui:script>