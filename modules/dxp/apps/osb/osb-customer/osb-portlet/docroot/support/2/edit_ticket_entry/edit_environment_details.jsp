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

Integer component = (Integer)request.getAttribute("edit_ticket_entry_dialog.jsp-component");
Integer envLFR = (Integer)request.getAttribute("edit_ticket_entry_dialog.jsp-envLFR");
Boolean hasUpdateAdmin = (Boolean)request.getAttribute("edit_ticket_entry_dialog.jsp-hasUpdateAdmin");
Boolean hasUpdateAdvanced = (Boolean)request.getAttribute("edit_ticket_entry_dialog.jsp-hasUpdateAdvanced");
Long offeringEntryId = (Long)request.getAttribute("edit_ticket_entry_dialog.jsp-offeringEntryId");
ProductEntry productEntry = (ProductEntry)request.getAttribute("edit_ticket_entry_dialog.jsp-productEntry");

if (component == null) {
	long productEntryId = ParamUtil.getLong(request, "productEntryId");

	if (productEntryId > 0) {
		productEntry = ProductEntryLocalServiceUtil.fetchProductEntry(productEntryId);
	}

	component = ParamUtil.getInteger(request, "component");
	envLFR = ParamUtil.getInteger(request, "envLFR");
	hasUpdateAdmin = ParamUtil.getBoolean(request, "hasUpdateAdmin");
	hasUpdateAdvanced = ParamUtil.getBoolean(request, "hasUpdateAdvanced");
	offeringEntryId = ParamUtil.getLong(request, "offeringEntryId");
}

Map<Long, String> ticketInformationFieldsMap = ticketEntry.getTicketInformationFieldsMap();

int envAS = ParamUtil.getInteger(request, "envAS", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_ENV_AS)));
int envBrowser = ParamUtil.getInteger(request, "envBrowser", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_ENV_BROWSER)));
String envBrowserCustom = ParamUtil.getString(request, "envBrowserCustom", GetterUtil.getString(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_ENV_BROWSER_CUSTOM)));
int envDB = ParamUtil.getInteger(request, "envDB", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_ENV_DB)));
String envName = ParamUtil.getString(request, "envName", GetterUtil.getString(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_ENV_NAME)));
int envJVM = ParamUtil.getInteger(request, "envJVM", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_ENV_JVM)));
int envOS = ParamUtil.getInteger(request, "envOS", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_ENV_OS)));
String envOSCustom = ParamUtil.getString(request, "envOSCustom", GetterUtil.getString(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_ENV_OS_CUSTOM)));
int toEnvLFR = ParamUtil.getInteger(request, "toEnvLFR", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_UPGRADE_TO_ENV_LFR)));
%>

<c:if test="<%= hasUpdateAdvanced %>">
	<div class="hide tab-content-tab" id="<portlet:namespace />environmentDetails">

		<%
		int envLFRNotification = 0;

		if (component == TicketEntryConstants.COMPONENT_UPGRADE) {
			envLFRNotification = toEnvLFR;
		}
		else {
			envLFRNotification = envLFR;
		}
		%>

		<liferay-util:include page="/support/2/common/eosl_environment_liferay.jsp" servletContext="<%= application %>">
			<portlet:param name="envLFR" value="<%= String.valueOf(envLFRNotification) %>" />
		</liferay-util:include>

		<c:if test="<%= Validator.isNotNull(envName) %>">
			<aui:input label="" name="envName" type="hidden" value="<%= HtmlUtil.escapeAttribute(envName) %>" />
		</c:if>

		<%
		StringBundler envLFROnChangeSB = new StringBundler(6);

		envLFROnChangeSB.append(renderResponse.getNamespace());
		envLFROnChangeSB.append("loadEnvironmentDetails(");
		envLFROnChangeSB.append(component);
		envLFROnChangeSB.append(", 0);");

		if (!productEntry.isDigitalEnterprise()) {
			envLFROnChangeSB.append(renderResponse.getNamespace());
			envLFROnChangeSB.append("updateSupportMessage(this.value);");
		}

		List<ListType> envLFRTypes = productEntry.getAllVersionsListTypes();

		String previousNamePrefix = StringPool.BLANK;
		%>

		<aui:select inlineField="<%= true %>" label="liferay-version" name="envLFR" onChange="<%= envLFROnChangeSB.toString() %>" wrapperCssClass="form-field-wrapper">

			<%
			long[] listTypesDeprecated = Arrays.stream(ProductEntryConstants.LIST_TYPES_DEPRECATED).asLongStream().toArray();

			for (ListType envLFRType : envLFRTypes) {
				if ((envLFRType.getListTypeId() == ProductEntryConstants.PORTAL_VERSION_OTHER) && (envLFR != ProductEntryConstants.PORTAL_VERSION_OTHER) || ArrayUtil.contains(listTypesDeprecated, envLFRType.getListTypeId())) {
					continue;
				}

				boolean limited = false;

				if (envLFRType.getListTypeId() < ProductEntryConstants.PORTAL_VERSION_6_2_10) {
					limited = true;
				}

				String name = envLFRType.getName();

				String namePrefix = name.substring(0, 3);
			%>

				<c:if test="<%= Validator.isNotNull(previousNamePrefix) && !previousNamePrefix.equals(namePrefix) %>">
					<aui:option disabled="<%= true %>" label="--------" />
				</c:if>

				<%
				String envLFRTypeLabel = LanguageUtil.get(request, envLFRType.getName());

				if (limited) {
					envLFRTypeLabel = envLFRTypeLabel + " (" + LanguageUtil.get(request, "limited") + ")";
				}
				%>

				<aui:option label="<%= envLFRTypeLabel %>" selected="<%= envLFRType.getListTypeId() == envLFR %>" value="<%= envLFRType.getListTypeId() %>" />

			<%
				previousNamePrefix = namePrefix;
			}
			%>

			<aui:validator errorMessage="please-select-a-valid-liferay-version" name="required" />
		</aui:select>

		<c:if test="<%= component == TicketEntryConstants.COMPONENT_UPGRADE %>">

			<%
			StringBundler toEnvLFROnChangeSB = new StringBundler(6);

			toEnvLFROnChangeSB.append(renderResponse.getNamespace());
			toEnvLFROnChangeSB.append("loadEnvironmentDetails(");
			toEnvLFROnChangeSB.append(component);
			toEnvLFROnChangeSB.append(", this.value);");
			toEnvLFROnChangeSB.append(renderResponse.getNamespace());
			toEnvLFROnChangeSB.append("updateSupportMessage(this.value)");
			%>

			<aui:select inlineField="<%= true %>" label="to" name="toEnvLFR" onChange="<%= toEnvLFROnChangeSB.toString() %>" wrapperCssClass="form-field-wrapper">

				<%
				previousNamePrefix = StringPool.BLANK;

				for (ListType envLFRType : envLFRTypes) {
					boolean toEnvLFRLimited = false;

					if (envLFRType.getListTypeId() < ProductEntryConstants.PORTAL_VERSION_6_2_10) {
						toEnvLFRLimited = true;
					}

					String name = envLFRType.getName();

					String namePrefix = StringPool.BLANK;

					if (envLFRType.getListTypeId() > envLFR) {
						namePrefix = name.substring(0, 3);
					}
				%>

					<c:if test="<%= envLFRType.getListTypeId() > envLFR %>">
						<c:if test="<%= Validator.isNotNull(previousNamePrefix) && !previousNamePrefix.equals(namePrefix) %>">
							<aui:option disabled="<%= true %>" label="--------" />
						</c:if>

						<%
						String envLFRLimitedTypeLabel = LanguageUtil.get(request, envLFRType.getName());

						if (toEnvLFRLimited) {
							envLFRLimitedTypeLabel = envLFRLimitedTypeLabel + " (" + LanguageUtil.get(request, "limited") + ")";
						}
						%>

						<aui:option label="<%= envLFRLimitedTypeLabel %>" selected="<%= envLFRType.getListTypeId() == toEnvLFR %>" value="<%= envLFRType.getListTypeId() %>" />
					</c:if>

				<%
					previousNamePrefix = namePrefix;
				}
				%>

			</aui:select>
		</c:if>

		<c:if test="<%= component != TicketEntryConstants.COMPONENT_LICENSE %>">
			<aui:select inlineField="<%= true %>" label="operating-system" name="envOS" onChange='<%= renderResponse.getNamespace() + "selectEnvOS(this.value);" %>' wrapperCssClass="form-field-wrapper">
				<c:if test="<%= envOS != 0 %>">
					<aui:option label="<%= TicketEntryConstants.getEnvLabel(envOS) %>" selected="<%= true %>" value="<%= envOS %>" />
				</c:if>

				<aui:validator errorMessage="please-select-a-valid-operating-system" name="required" />
			</aui:select>

			<aui:input cssClass="no-label" inlineField="<%= true %>" label="" maxLength="<%= TicketInformationConstants.getMaxLength(TicketInformationConstants.FIELD_ENV_OS_CUSTOM) %>" name="envOSCustom" type="text" value="<%= HtmlUtil.escapeAttribute(envOSCustom) %>" wrapperCssClass='<%= envOS == TicketEntryConstants.ENV_OS_OTHER ? "form-field-wrapper" : "hide" %>' />

			<aui:select inlineField="<%= true %>" label="application-server" name="envAS" wrapperCssClass="form-field-wrapper">
				<c:if test="<%= envAS != 0 %>">
					<aui:option label="<%= TicketEntryConstants.getEnvLabel(envAS) %>" selected="<%= true %>" value="<%= envAS %>" />
				</c:if>

				<aui:validator errorMessage="please-select-a-valid-application-server" name="required" />
			</aui:select>

			<aui:select inlineField="<%= true %>" label="java-virtual-machine" name="envJVM" wrapperCssClass="form-field-wrapper">
				<c:if test="<%= envJVM != 0 %>">
					<aui:option label="<%= TicketEntryConstants.getEnvLabel(envJVM) %>" selected="<%= true %>" value="<%= envJVM %>" />
				</c:if>

				<aui:validator errorMessage="please-select-a-valid-java-virtual-machine" name="required" />
			</aui:select>

			<aui:select inlineField="<%= true %>" label="database" name="envDB" wrapperCssClass="form-field-wrapper">
				<c:if test="<%= envDB != 0 %>">
					<aui:option label="<%= TicketEntryConstants.getEnvLabel(envDB) %>" selected="<%= true %>" value="<%= envDB %>" />
				</c:if>

				<aui:validator errorMessage="please-select-a-valid-database" name="required" />
			</aui:select>

			<aui:select inlineField="<%= true %>" label="primary-browser" name="envBrowser" onChange='<%= renderResponse.getNamespace() + "selectBrowser(this.value);" %>' wrapperCssClass="form-field-wrapper">
				<c:if test="<%= envBrowser != 0 %>">
					<aui:option label="<%= TicketEntryConstants.getEnvLabel(envBrowser) %>" selected="<%= true %>" value="<%= envBrowser %>" />
				</c:if>
			</aui:select>

			<aui:input cssClass="no-label" inlineField="<%= true %>" label="" maxLength="<%= TicketInformationConstants.getMaxLength(TicketInformationConstants.FIELD_ENV_BROWSER_CUSTOM) %>" name="envBrowserCustom" type="text" value="<%= HtmlUtil.escapeAttribute(envBrowserCustom) %>" wrapperCssClass='<%= (envBrowser == TicketEntryConstants.ENV_BROWSER_OTHER ? "form-field-wrapper" : "hide") %>' />
		</c:if>
	</div>
</c:if>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />loadEnvironmentDetails',
		function(component, toEnvLFR) {
			var A = AUI();

			var envLFR = A.one('#<portlet:namespace />envLFR');

			if (envLFR) {
				var envLFRVal = envLFR.val();

				if (component == <%= TicketEntryConstants.COMPONENT_UPGRADE %>) {
					<c:choose>
						<c:when test="<%= productEntry.isDigitalEnterprise() %>">
							if (toEnvLFR > 0) {
								<portlet:namespace />getEarlierLFRVersions(toEnvLFR);
							}

							envLFR = A.one('#<portlet:namespace />toEnvLFR');
						</c:when>
						<c:otherwise>
							<portlet:namespace />getLaterLFRVersions(envLFRVal, <%= offeringEntryId %>);
						</c:otherwise>
					</c:choose>
				}

				<portlet:namespace />selectPortalVersion(envLFRVal, <%= envAS %>, '<%= LanguageUtil.get(request, TicketEntryConstants.getEnvLabel(envAS)) %>', <%= envBrowser %>, '<%= LanguageUtil.get(request, TicketEntryConstants.getEnvLabel(envBrowser)) %>', <%= envDB %>, '<%= LanguageUtil.get(request, TicketEntryConstants.getEnvLabel(envDB)) %>', <%= envJVM %>, '<%= LanguageUtil.get(request, TicketEntryConstants.getEnvLabel(envJVM)) %>', <%= envOS %>, '<%= LanguageUtil.get(request, TicketEntryConstants.getEnvLabel(envOS)) %>');
			}
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />selectBrowser',
		function(envBrowser) {
			var A = AUI();

			var envBrowserCustom = A.one('#<portlet:namespace />envBrowserCustom');

			if (envBrowserCustom) {
				var other = (envBrowser == '<%= TicketEntryConstants.ENV_BROWSER_OTHER %>');

				envBrowserCustom.toggle(other);

				if (!other) {
					envBrowserCustom.val('');
				}
			}
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />selectEnvOS',
		function(envOS) {
			var A = AUI();

			var envOSCustom = A.one('#<portlet:namespace />envOSCustom');

			var other = (envOS == '<%= TicketEntryConstants.ENV_OS_OTHER %>');

			envOSCustom.toggle(other);

			if (!other) {
				envOSCustom.val('');
			}
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />selectPortalVersionRetainValues',
		function(envLFR) {
			var A = AUI();

			var envAS = A.one('#<portlet:namespace />envAS');

			if (envAS) {
				envAS = envAS.val();
			}

			var envASText = A.one('#<portlet:namespace />envASLabel');

			if (envASText) {
				envASText = envASText.html();
			}

			var envBR = A.one('#<portlet:namespace />envBrowser');

			if (envBR) {
				envBR = envBR.val();
			}

			var envBRText = A.one('#<portlet:namespace />envBrowserLabel');

			if (envBRText) {
				envBRText = envBRText.html();
			}

			var envDB = A.one('#<portlet:namespace />envDB');

			if (envDB) {
				envDB = envDB.val();
			}

			var envDBText = A.one('#<portlet:namespace />envDBLabel');

			if (envDBText) {
				envDBText = envDBText.html();
			}

			var envJVM = A.one('#<portlet:namespace />envJVM');

			if (envJVM) {
				envJVM = envJVM.val();
			}

			var envJVMText = A.one('#<portlet:namespace />envJVMLabel');

			if (envJVMText) {
				envJVMText = envJVMText.html();
			}

			var envOS = A.one('#<portlet:namespace />envOS');

			if (envOS) {
				envOS = envOS.val();
			}

			var envOSText = A.one('#<portlet:namespace />envOSLabel');

			if (envOSText) {
				envOSText = envOSText.html();
			}

			if (envAS && envASText && envBR && envBRText && envDB && envDBText && envJVM && envJVMText && envOS && envOSText) {
				<portlet:namespace />selectPortalVersion(envLFR, envAS, envASText, envBR, envBRText, envDB, envDBText, envJVM, envJVMText, envOS, envOSText);
			}
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />updateEnvironmentField',
		function(selectId, selectDataKey, selectData, selectVal, selectName) {
			var A = AUI();

			var selectElement = A.one('#' + selectId);

			if (selectElement) {
				if (selectElement.getData('key') == selectDataKey) {
					return;
				}

				var selectValExists = false;

				selectElement.setData('key', selectDataKey);

				var selectOptions = [];

				if (selectId == '<portlet:namespace />envBrowser') {
					selectOptions.push('<option value="0"></option>');
				}

				if (selectData) {
					for (var i = 0; i < selectData.length; i++) {
						var name = selectData[i].name;
						var value = selectData[i].value;

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

				if (selectId == '<portlet:namespace />envBrowser') {
					<portlet:namespace />selectBrowser(selectVal);
				}

				if (selectId == '<portlet:namespace />envOS') {
					<portlet:namespace />selectEnvOS(selectVal);
				}
			}
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />updateEnvLFR',
		function(selectData) {
			var A = AUI();

			<c:choose>
				<c:when test="<%= productEntry.isDigitalEnterprise() %>">
					var envLFR = A.one('#<portlet:namespace />toEnvLFR');

					var selectElement = A.one('#<portlet:namespace />envLFR');

					if (selectElement) {
						var selectVal = <%= envLFR %>;
					}
				</c:when>
				<c:otherwise>
					var envLFR = A.one('#<portlet:namespace />envLFR');

					var selectElement = A.one('#<portlet:namespace />toEnvLFR');

					if (selectElement) {
						var selectVal = selectElement.val();
					}
				</c:otherwise>
			</c:choose>

			var selectValExists = false;

			var selectOptions = [];

			if (selectData) {
				var previousNamePrefix = '';

				for (var i = 0; i < selectData.length; i++) {
					var name = selectData[i].name;
					var value = selectData[i].value;

					var limited = '';

					if (value < <%= ProductEntryConstants.PORTAL_VERSION_6_2_10 %>) {
						limited = '(<liferay-ui:message key="limited" />)';
					}

					var namePrefix = name.substring(0, 3);

					if ((previousNamePrefix != '') && (previousNamePrefix != namePrefix)) {
						selectOptions.push('<option disabled>--------</option>');
					}

					selectOptions.push('<option value="' + value + '">' + name + limited + '</option>');

					if ((value == selectVal) && (envLFR.val() > 0)) {
						selectValExists = true;
					}

					previousNamePrefix = namePrefix;
				}
			}

			selectOptions = selectOptions.join('');

			selectElement.setHTML(selectOptions);

			if (selectValExists && (selectVal > 0)) {
				selectElement.val(String(selectVal));

				<c:choose>
					<c:when test="<%= productEntry.isDigitalEnterprise() %>">
						selectVal = A.one('#<portlet:namespace />toEnvLFR').val();
					</c:when>
					<c:otherwise>
						selectVal = A.one('#<portlet:namespace />envLFR').val();
					</c:otherwise>
				</c:choose>

				<portlet:namespace />selectPortalVersionRetainValues(selectVal);
			}
			else {
				selectElement.val(0);

				<portlet:namespace />updateSupportMessage(0);
			}
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />updateSupportMessage',
		function(envLFR, section) {
			var A = AUI();

			var is5_2 = ((envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_4 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_5 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_6 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_7 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_8 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_9 %>));

			var supportMessageDisplay_5_2 = A.one('#<portlet:namespace />support' + section + 'MessageDisplay_5_2');

			if (supportMessageDisplay_5_2) {
				supportMessageDisplay_5_2.toggle(is5_2);
			}

			var is6_0 = ((envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_0_10 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_0_11 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_0_12 %>));

			var supportMessageDisplay_6_0 = A.one('#<portlet:namespace />support' + section + 'MessageDisplay_6_0');

			if (supportMessageDisplay_6_0) {
				supportMessageDisplay_6_0.toggle(is6_0);
			}

			var is6_1 = ((envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_1_10 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_1_20 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_1_30 %>));

			var supportMessageDisplay_6_1 = A.one('#<portlet:namespace />support' + section + 'MessageDisplay_6_1');

			if (supportMessageDisplay_6_1) {
				supportMessageDisplay_6_1.toggle(is6_1);
			}
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />getEarlierLFRVersions',
		function(envLFR) {
			var A = AUI();

			A.io.request(
				'<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="earlierLFRVersions" />',
				{
					data: {
						<portlet:namespace />envLFR: envLFR
					},
					dataType: 'JSON',
					method: 'POST',
					on: {
						success: function() {
							var response = this.get('responseData');

							<portlet:namespace />updateEnvLFR(response);
						}
					}
				}
			);
		},
		['aui-io-request']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />getLaterLFRVersions',
		function(envLFR, offeringEntryId) {
			var A = AUI();

			A.io.request(
				'<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="laterLFRVersions" />',
				{
					data: {
						<portlet:namespace />envLFR: envLFR,
						<portlet:namespace />offeringEntryId: offeringEntryId
					},
					dataType: 'JSON',
					method: 'POST',
					on: {
						success: function() {
							var response = this.get('responseData');

							<portlet:namespace />updateEnvLFR(response);
						}
					}
				}
			);
		},
		['aui-io-request']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />selectPortalVersion',
		function(envLFR, envAS, envASName, envBrowser, envBrowserName, envDB, envDBName, envJVM, envJVMName, envOS, envOSName) {
			var A = AUI();

			if (envLFR <= 0) {
				var envTypes = ['envAS', 'envBrowser', 'envDB', 'envJVM', 'envOS'];

				for (var envType in envTypes) {
					var envNode = A.one('#<portlet:namespace />' + envTypes[envType]);

					if (envNode) {
						envElement.empty();

						envElement.setData('key', 0);
					}
				}

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
							<portlet:namespace />updateEnvironmentField('<portlet:namespace />envBrowser', response['ENV_Browser#key'], response['ENV_Browser'], envBrowser, envBrowserName);
							<portlet:namespace />updateEnvironmentField('<portlet:namespace />envDB', response['ENV_DB#key'], response['ENV_DB'], envDB, envDBName);
							<portlet:namespace />updateEnvironmentField('<portlet:namespace />envJVM', response['ENV_JVM#key'], response['ENV_JVM'], envJVM, envJVMName);
							<portlet:namespace />updateEnvironmentField('<portlet:namespace />envOS', response['ENV_OS#key'], response['ENV_OS'], envOS, envOSName);
						}
					}
				}
			);
		},
		['aui-io-request']
	);

	<portlet:namespace />loadEnvironmentDetails('<%= component %>', '<%= toEnvLFR %>');
</aui:script>