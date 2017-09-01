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
	<div class="aui-helper-hidden tab-content-tab" id="<portlet:namespace />environmentDetails">
		<div>

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
				<input name="<portlet:namespace />envName" type="hidden" value="<%= HtmlUtil.escapeAttribute(envName) %>" />
			</c:if>

			<div class="field-group">
				<label id="<portlet:namespace />envLFRLabel"><liferay-ui:message key="liferay-version" /></label>

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

				<div class="field-align">
					<select data-field-required-status="<%= false %>" field-required-message="<%= LanguageUtil.get(request, "please-select-a-valid-liferay-version") %>" id="<portlet:namespace />envLFR" name="<portlet:namespace />envLFR" onChange="<%= envLFROnChangeSB.toString() %>">

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
								<option disabled>--------</option>
							</c:if>

							<option <%= (envLFRType.getListTypeId() == envLFR) ? "selected" : "" %> value="<%= envLFRType.getListTypeId() %>"><%= LanguageUtil.get(request, envLFRType.getName()) %><%= limited ? " (" + LanguageUtil.get(request, "limited") + ")" : StringPool.BLANK %></option>

						<%
							previousNamePrefix = namePrefix;
						}
						%>

					</select>

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

						<span id="<portlet:namespace />toEnvLFRLabel"><liferay-ui:message key="to" /></span>

						<select id="<portlet:namespace />toEnvLFR" name="<portlet:namespace />toEnvLFR" onChange="<%= toEnvLFROnChangeSB %>">

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
										<option disabled>--------</option>
									</c:if>

									<option <%= (envLFRType.getListTypeId() == toEnvLFR) ? "selected" : "" %> value="<%= envLFRType.getListTypeId() %>"><%= LanguageUtil.get(request, envLFRType.getName()) %><%= toEnvLFRLimited ? " (" + LanguageUtil.get(request, "limited") + ")" : StringPool.BLANK %></option>
								</c:if>

							<%
								previousNamePrefix = namePrefix;
							}
							%>

						</select>
					</c:if>
				</div>
			</div>

			<c:if test="<%= component != TicketEntryConstants.COMPONENT_LICENSE %>">
				<div class="field-group">
					<label id="<portlet:namespace />envOSLabel"><liferay-ui:message key="operating-system" /></label>

					<div class="field-align">
						<select data-field-required-status="<%= false %>" field-required-message="<%= LanguageUtil.get(request, "please-select-a-valid-operating-system") %>" id="<portlet:namespace />envOS" name="<portlet:namespace />envOS" onChange="<portlet:namespace />selectEnvOS(this.value);">
							<c:if test="<%= envOS != 0 %>">
								<option selected value="<%= envOS %>"><%= LanguageUtil.get(request, TicketEntryConstants.getEnvLabel(envOS)) %></option>
							</c:if>
						</select>

						<br />

						<input class="<%= (envOS == TicketEntryConstants.ENV_OS_OTHER) ? "" : "aui-helper-hidden" %>" id="<portlet:namespace />envOSCustom" maxLength="<%= TicketInformationConstants.getMaxLength(TicketInformationConstants.FIELD_ENV_OS_CUSTOM) %>" name="<portlet:namespace />envOSCustom" type="text" value="<%= HtmlUtil.escapeAttribute(envOSCustom) %>" />
					</div>
				</div>

				<div class="field-group">
					<label id="<portlet:namespace />envASLabel"><liferay-ui:message key="application-server" /></label>

					<div class="field-align">
						<select data-field-required-status="<%= false %>" field-required-message="<%= LanguageUtil.get(request, "please-select-a-valid-application-server") %>" id="<portlet:namespace />envAS" name="<portlet:namespace />envAS">
							<c:if test="<%= envAS != 0 %>">
								<option selected value="<%= envAS %>"><%= LanguageUtil.get(request, TicketEntryConstants.getEnvLabel(envAS)) %></option>
							</c:if>
						</select>
					</div>
				</div>

				<div class="field-group">
					<label id="<portlet:namespace />envJVMLabel"><liferay-ui:message key="java-virtual-machine" /></label>

					<select data-field-required-status="<%= false %>" field-required-message="<%= LanguageUtil.get(request, "please-select-a-valid-java-virtual-machine") %>" id="<portlet:namespace />envJVM" name="<portlet:namespace />envJVM">
						<c:if test="<%= envJVM != 0 %>">
							<option selected value="<%= envJVM %>"><%= LanguageUtil.get(request, TicketEntryConstants.getEnvLabel(envJVM)) %></option>
						</c:if>
					</select>
				</div>

				<div class="field-group">
					<label id="<portlet:namespace />envDBLabel"><liferay-ui:message key="database" /></label>

					<div class="field-align">
						<select data-field-required-status="<%= false %>" field-required-message="<%= LanguageUtil.get(request, "please-select-a-valid-database") %>" id="<portlet:namespace />envDB" name="<portlet:namespace />envDB">
							<c:if test="<%= envDB != 0 %>">
								<option selected value="<%= envDB %>"><%= LanguageUtil.get(request, TicketEntryConstants.getEnvLabel(envDB)) %></option>
							</c:if>
						</select>
					</div>
				</div>

				<div class="field-group">
					<label id="<portlet:namespace />envBrowserLabel"><liferay-ui:message key="primary-browser" /></label>

					<select id="<portlet:namespace />envBrowser" name="<portlet:namespace />envBrowser" onChange="<portlet:namespace />selectBrowser(this.value);">
						<c:if test="<%= envBrowser != 0 %>">
							<option selected value="<%= envBrowser %>"><%= LanguageUtil.get(request, TicketEntryConstants.getEnvLabel(envBrowser)) %></option>
						</c:if>
					</select>

					<br />

					<input class="<%= (envBrowser == TicketEntryConstants.ENV_BROWSER_OTHER) ? "" : "aui-helper-hidden" %>" id="<portlet:namespace />envBrowserCustom" maxLength="<%= TicketInformationConstants.getMaxLength(TicketInformationConstants.FIELD_ENV_BROWSER_CUSTOM) %>" name="<portlet:namespace />envBrowserCustom" type="text" value="<%= HtmlUtil.escapeAttribute(envBrowserCustom) %>" />
				</div>
			</c:if>
		</div>
	</div>
</c:if>

<aui:script use="aui-base,aui-io">
	<portlet:namespace />loadEnvironmentDetails('<%= component %>', '<%= toEnvLFR %>');
</aui:script>

<aui:script>
	function <portlet:namespace />loadEnvironmentDetails(component, toEnvLFR) {
		var A = AUI();

		var envLFREl = A.one('#<portlet:namespace />envLFR');

		if (component == <%= TicketEntryConstants.COMPONENT_UPGRADE %>) {
			<c:choose>
				<c:when test="<%= productEntry.isDigitalEnterprise() %>">
					if (toEnvLFR > 0) {
						<portlet:namespace />getEarlierLFRVersions(toEnvLFR);
					}

					envLFREl = A.one("#<portlet:namespace />toEnvLFR");
				</c:when>
				<c:otherwise>
					<portlet:namespace />getLaterLFRVersions(envLFREl.val(), <%= offeringEntryId %>);
				</c:otherwise>
			</c:choose>
		}

		<portlet:namespace />selectPortalVersion(envLFREl.val(), <%= envAS %>, '<%= LanguageUtil.get(request, TicketEntryConstants.getEnvLabel(envAS)) %>', <%= envBrowser %>, '<%= LanguageUtil.get(request, TicketEntryConstants.getEnvLabel(envBrowser)) %>', <%= envDB %>, '<%= LanguageUtil.get(request, TicketEntryConstants.getEnvLabel(envDB)) %>', <%= envJVM %>, '<%= LanguageUtil.get(request, TicketEntryConstants.getEnvLabel(envJVM)) %>', <%= envOS %>, '<%= LanguageUtil.get(request, TicketEntryConstants.getEnvLabel(envOS)) %>');
	}

	function <portlet:namespace />selectBrowser(envBrowser) {
		var envBrowserCustom = AUI().one('#<portlet:namespace />envBrowserCustom');

		if (envBrowser == '<%= TicketEntryConstants.ENV_BROWSER_OTHER %>') {
			envBrowserCustom.show();
		}
		else {
			envBrowserCustom.hide();

			envBrowserCustom.val('');
		}
	}

	function <portlet:namespace />selectEnvOS(envOS) {
		var envOSCustom = AUI().one('#<portlet:namespace />envOSCustom');

		if (envOS == '<%= TicketEntryConstants.ENV_OS_OTHER %>') {
			envOSCustom.show();
		}
		else {
			envOSCustom.hide();

			envOSCustom.val('');
		}
	}

	function <portlet:namespace />selectPortalVersionRetainValues(envLFR) {
		var A = AUI();

		var envAS = A.one("#<portlet:namespace />envAS").val();
		var envASText = A.one("#<portlet:namespace />envASLabel").html();

		var envBR = A.one("#<portlet:namespace />envBrowser").val();
		var envBRText = A.one("#<portlet:namespace />envBrowserLabel").html();

		var envDB = A.one("#<portlet:namespace />envDB").val();
		var envDBText = A.one("#<portlet:namespace />envDBLabel").html();

		var envJVM = A.one("#<portlet:namespace />envJVM").val();
		var envJVMText = A.one("#<portlet:namespace />envJVMLabel").html();

		var envOS = A.one("#<portlet:namespace />envOS").val();
		var envOSText = A.one("#<portlet:namespace />envOSLabel").html();

		<portlet:namespace />selectPortalVersion(envLFR, envAS, envASText, envBR, envBRText, envDB, envDBText, envJVM, envJVMText, envOS, envOSText);
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

		if (selectId == '<portlet:namespace />envBrowser') {
			selectOptions.push('<option value="0"></option>');
		}

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

		if (selectId == '<portlet:namespace />envBrowser') {
			<portlet:namespace />selectBrowser(selectVal);
		}

		if (selectId == '<portlet:namespace />envOS') {
			<portlet:namespace />selectEnvOS(selectVal);
		}
	}

	function <portlet:namespace />updateEnvLFR(selectData) {
		var A = AUI();

		var envLFR = '';
		var selectElement = '';
		var selectVal = '';

		<c:choose>
			<c:when test="<%= productEntry.isDigitalEnterprise() %>">
				envLFR = A.one('#<portlet:namespace />toEnvLFR');

				selectElement = A.one('#<portlet:namespace />envLFR');

				selectVal = <%= envLFR %>;
			</c:when>
			<c:otherwise>
				envLFR = A.one('#<portlet:namespace />envLFR');

				selectElement = A.one('#<portlet:namespace />toEnvLFR');

				selectVal = selectElement.val();
			</c:otherwise>
		</c:choose>

		var selectValExists = false;

		var selectOptions = [];

		if (selectData) {
			var previousNamePrefix = "";

			for (var i = 0; i < selectData.length; i++) {
				var value = selectData[i].value;
				var name = selectData[i].name;

				var limited = '';

				if (value < <%= ProductEntryConstants.PORTAL_VERSION_6_2_10 %>) {
					limited = '(<%= UnicodeLanguageUtil.get(request, "limited") %>)';
				}

				var namePrefix = name.substring(0, 3);

				if ((previousNamePrefix != "") && (previousNamePrefix != namePrefix)) {
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

		selectElement.empty();

		selectElement.append(selectOptions);

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
	}

	function <portlet:namespace />updateSupportMessage(envLFR) {
		var A = AUI();

		var supportMessageDisplay_5_2 = A.one('#<portlet:namespace />supportMessageDisplay_5_2');

		if (supportMessageDisplay_5_2) {
			var condition = (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_4 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_5 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_6 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_7 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_8 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_9 %>);

			supportMessageDisplay_5_2.toggle(condition);
		}

		var supportMessageDisplay_6_0 = A.one('#<portlet:namespace />supportMessageDisplay_6_0');

		if (supportMessageDisplay_6_0) {
			var condition = (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_0_10 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_0_11 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_0_12 %>);

			supportMessageDisplay_6_0.toggle(condition);
		}

		var supportMessageDisplay_6_1 = A.one('#<portlet:namespace />supportMessageDisplay_6_1');

		if (supportMessageDisplay_6_1) {
			var condition = (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_1_10 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_1_20 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_1_30 %>);

			supportMessageDisplay_6_1.toggle(condition);
		}
	}

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
					dataType: 'json',
					method: 'post',
					on: {
						success: function(event, id, obj) {
							var response = this.get('responseData');

							<portlet:namespace />updateEnvLFR(response);
						}
					}
				}
			);
		},
		['aui-io']
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
					dataType: 'json',
					method: 'post',
					on: {
						success: function(event, id, obj) {
							var response = this.get('responseData');

							<portlet:namespace />updateEnvLFR(response);
						}
					}
				}
			);
		},
		['aui-io']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />selectPortalVersion',
		function(envLFR, envAS, envASName, envBrowser, envBrowserName, envDB, envDBName, envJVM, envJVMName, envOS, envOSName) {
			var A = AUI();

			if (envLFR <= 0) {
				var envTypes = ["envAS", "envBrowser", "envDB", "envJVM", "envOS"];

				for (var envType in envTypes) {
					var envElement = A.one("#<portlet:namespace />" + envTypes[envType]);

					envElement.empty();

					envElement.setData('key', 0);
				}

				return;
			}

			A.io.request(
				'<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="ticketEnvironment" />',
				{
					data: {
						<portlet:namespace />envLFR: envLFR
					},
					dataType: 'json',
					method: 'post',
					on: {
						success: function(event, id, obj) {
							var response = this.get('responseData');

							<portlet:namespace />updateEnvironmentField("<portlet:namespace />envAS", response["ENV_AS#key"], response["ENV_AS"], envAS, envASName);
							<portlet:namespace />updateEnvironmentField("<portlet:namespace />envBrowser", response["ENV_Browser#key"], response["ENV_Browser"], envBrowser, envBrowserName);
							<portlet:namespace />updateEnvironmentField("<portlet:namespace />envDB", response["ENV_DB#key"], response["ENV_DB"], envDB, envDBName);
							<portlet:namespace />updateEnvironmentField("<portlet:namespace />envJVM", response["ENV_JVM#key"], response["ENV_JVM"], envJVM, envJVMName);
							<portlet:namespace />updateEnvironmentField("<portlet:namespace />envOS", response["ENV_OS#key"], response["ENV_OS"], envOS, envOSName);
						}
					}
				}
			);
		},
		['aui-io']
	);
</aui:script>