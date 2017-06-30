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

<c:choose>
	<c:when test="<%= SessionMessages.contains(renderRequest, PortalUtil.getPortletId(renderRequest) + SessionMessages.KEY_SUFFIX_REFRESH_PORTLET) %>">
		<aui:script>
			Liferay.Util.getWindow().close();
		</aui:script>
	</c:when>
	<c:otherwise>

		<%
		long accountEnvironmentId = ParamUtil.getLong(request, "accountEnvironmentId");

		AccountEnvironment accountEnvironment = AccountEnvironmentLocalServiceUtil.fetchAccountEnvironment(accountEnvironmentId);

		long accountEntryId = BeanParamUtil.getLong(accountEnvironment, request, "accountEntryId");

		AccountEntry accountEntry = null;

		if (accountEntryId > 0) {
			accountEntry = AccountEntryServiceUtil.getAccountEntry(accountEntryId);
		}

		long productEntryId = BeanParamUtil.getLong(accountEnvironment, request, "productEntryId");

		ProductEntry productEntry = null;

		if (productEntryId > 0) {
			productEntry = ProductEntryLocalServiceUtil.getProductEntry(productEntryId);
		}

		long offeringEntryId = ParamUtil.getLong(request, "offeringEntryId");

		OfferingEntry offeringEntry = null;

		if (offeringEntryId > 0) {
			offeringEntry = OfferingEntryLocalServiceUtil.getOfferingEntry(offeringEntryId);
		}

		int envOS = BeanParamUtil.getInteger(accountEnvironment, request, "envOS");
		String envOSCustom = BeanParamUtil.getString(accountEnvironment, request, "envOSCustom");
		int envDB = BeanParamUtil.getInteger(accountEnvironment, request, "envDB");
		int envJVM = BeanParamUtil.getInteger(accountEnvironment, request, "envJVM");
		int envAS = BeanParamUtil.getInteger(accountEnvironment, request, "envAS");
		int envLFR = BeanParamUtil.getInteger(accountEnvironment, request, "envLFR");
		%>

		<portlet:actionURL name="updateAccountEnvironment" var="updateAccountEnvironmentURL">
			<portlet:param name="mvcPath" value="/support/edit_account_environment.jsp" />
		</portlet:actionURL>

		<aui:form action="<%= updateAccountEnvironmentURL %>" enctype="multipart/form-data" method="post" name="fm1">
			<input name="<portlet:namespace />accountEnvironmentId" type="hidden" value="<%= accountEnvironmentId %>" />
			<input name="<portlet:namespace />accountEntryId" type="hidden" value="<%= accountEntryId %>" />

			<liferay-ui:error exception="<%= AccountEnvironmentAttachmentException.class %>" message="please-upload-a-portal-ext-and-patch-level-file" />
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

			<liferay-ui:error exception="<%= DuplicateAccountEnvironmentAttachmentException.class %>" message="please-enter-a-unique-document-name" />
			<liferay-ui:error exception="<%= PrincipalException.class %>" message="you-do-not-have-the-required-permissions" />

			<liferay-util:include page="/support/common/eosl_environment_liferay.jsp" servletContext="<%= application %>">
				<portlet:param name="envLFR" value="<%= String.valueOf(envLFR) %>" />
			</liferay-util:include>

			<div class="edit-account-environment">
				<aui:model-context bean="<%= accountEnvironment %>" model="<%= AccountEnvironment.class %>" />

				<table class="lfr-table">
				<tr>
					<td>
						<span class="txt-b txt-up">* <liferay-ui:message key="name" />:</span>

						<aui:input inlineField="<%= true %>" label="" name="name" />
					</td>
					<td>
						<span class="txt-b txt-up"><%= (accountEnvironment != null) ? "" : "* " %><liferay-ui:message key="product" />:</span>

						<select <%= (productEntryId > 0) ? "disabled" : "" %> id="<portlet:namespace />offeringEntryId" name="<portlet:namespace />offeringEntryId" onChange="<portlet:namespace />selectProductEntry(this.value);">
							<option value="0"><liferay-ui:message key="select" /></option>

							<%
							LinkedHashMap params = new LinkedHashMap();

							params.put("validTicket", StringPool.BLANK);

							List<OfferingEntryGroup> offeringEntryGroups = SupportUtil.getOfferingEntryGroups(0, accountEntryId, new int[0], new int[] {OfferingEntryConstants.STATUS_ACTIVE, OfferingEntryConstants.STATUS_ON_HOLD}, 0, 0, 0, 0, 0, 0, params, true);

							for (OfferingEntryGroup offeringEntryGroup : offeringEntryGroups) {
								ProductEntry curProductEntry = offeringEntryGroup.getProductEntry();
								OfferingEntry curOfferingEntry = offeringEntryGroup.getAvailableSupportOfferingEntry();
							%>

								<option <%= (curProductEntry.getProductEntryId() == productEntryId) ? "selected" : "" %> value="<%= curOfferingEntry.getOfferingEntryId() %>"><%= HtmlUtil.escape(LanguageUtil.get(pageContext, curProductEntry.getName())) %></option>

							<%
							}
							%>

						</select>

						<c:if test="<%= productEntryId > 0 %>">
							<input name="<portlet:namespace />offeringEntryId" type="hidden" value="<%= offeringEntryId %>" />
						</c:if>
					</td>
					<td></td>
				</tr>
				<tr>
					<td>
						<span class="txt-b txt-up">* <liferay-ui:message key="liferay-version" />:</span>

						<select id="<portlet:namespace />envLFR" name="<portlet:namespace />envLFR" onChange="<portlet:namespace />selectPortalVersion(this.value, 0, '', 0, '', 0, '', 0, ''); <portlet:namespace />updateSupportMessage(this.value);">
							<c:if test="<%= productEntry != null %>">
								<option value="0"><liferay-ui:message key="select" /></option>

								<%
								List<ListType> envLFRTypes = productEntry.getAllVersionsListTypes();

								String previousNamePrefix = StringPool.BLANK;

								for (ListType envLFRType : envLFRTypes) {
									if ((envLFR != envLFRType.getListTypeId()) && ArrayUtil.contains(ProductEntryConstants.LIST_TYPES_DEPRECATED, envLFRType.getListTypeId())) {
										continue;
									}

									String name = envLFRType.getName();

									String namePrefix = name.substring(0, 3);
								%>

									<c:if test="<%= Validator.isNotNull(previousNamePrefix) && !previousNamePrefix.equals(namePrefix) %>">
										<option disabled>--------</option>
									</c:if>

									<option <%= (envLFRType.getListTypeId() == envLFR) ? "selected" : "" %> value="<%= envLFRType.getListTypeId() %>"><%= LanguageUtil.get(pageContext, envLFRType.getName()) %></option>

								<%
									previousNamePrefix = namePrefix;
								}
								%>

							</c:if>
						</select>
					</td>
					<td>
						<span class="txt-b txt-up">* <liferay-ui:message key="application-server" />:</span>

						<select id="<portlet:namespace />envAS" name="<portlet:namespace />envAS">
							<option value="0"></option>
						</select>
					</td>
					<td>
						<span class="txt-b txt-up">* <liferay-ui:message key="database" />:</span>

						<select id="<portlet:namespace />envDB" name="<portlet:namespace />envDB">
							<option value="0"></option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<span class="fl txt-b txt-up">* <liferay-ui:message key="operating-system" />:</span>

						<div class="fl">
							<select id="<portlet:namespace />envOS" name="<portlet:namespace />envOS" onChange="<portlet:namespace />selectEnvOS(this.value);">
								<option value="0"></option>
							</select>

							<br />

							<input class="<%= (envOS == TicketEntryConstants.ENV_OS_OTHER) ? "" : "aui-helper-hidden" %>" id="<portlet:namespace />envOSCustom" maxLength="<%= TicketInformationConstants.getMaxLength(TicketInformationConstants.FIELD_ENV_OS_CUSTOM) %>" name="<portlet:namespace />envOSCustom" type="text" value="<%= HtmlUtil.escapeAttribute(envOSCustom) %>" />
						</div>
					</td>
					<td>
						<span class="txt-b txt-up">* <liferay-ui:message key="java-virtual-machine" />:</span>

						<select id="<portlet:namespace />envJVM" name="<portlet:namespace />envJVM">
							<option value="0"></option>
						</select>
					</td>
					<td />
				</tr>
				<tr>
					<td colspan="3">
						<span class="txt-b txt-up">* <liferay-ui:message key="portal-ext" /><a class="help-link" href="/group/customer/kbase/-/knowledge_base/article/33142855" target="_blank"><img src="<%= themeDisplay.getPathThemeImages() + "/common/help.png" %>" /></a>:</span>

						<span id="<portlet:namespace />portalExtFilename">

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
						</span>

						<input id="<portlet:namespace />portalExt" name="<portlet:namespace />portalExt" type="file" value="upload" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<span class="txt-b txt-up">* <liferay-ui:message key="patch-level" /><a class="help-link" href="/group/customer/kbase/-/knowledge_base/article/33142925" target="_blank"><img src="<%= themeDisplay.getPathThemeImages() + "/common/help.png" %>" /></a>:</span>

						<span id="<portlet:namespace />patchLevelFilename">

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
						</span>

						<input id="<portlet:namespace />patchLevel" name="<portlet:namespace />patchLevel" type="file" value="upload" />
					</td>
					<td align="right">
						<input class="aui-button-input" onClick="javascript:<portlet:namespace />submit();" type="button" value="<liferay-ui:message key="save" />" />
					</td>
				</tr>
				</table>
			</div>
		</aui:form>

		<aui:script>
			<c:if test="<%= accountEnvironment != null %>">
				AUI().ready(
					'aui-base',
					'aui-io',
					function(A) {
						<portlet:namespace />selectPortalVersion(<%= envLFR %>, <%= envAS %>, '<%= LanguageUtil.get(pageContext, AccountEnvironmentConstants.getEnvLabel(envAS)) %>', <%= envDB %>, '<%= LanguageUtil.get(pageContext, AccountEnvironmentConstants.getEnvLabel(envDB)) %>', <%= envJVM %>, '<%= LanguageUtil.get(pageContext, AccountEnvironmentConstants.getEnvLabel(envJVM)) %>', <%= envOS %>, '<%= LanguageUtil.get(pageContext, AccountEnvironmentConstants.getEnvLabel(envOS)) %>');
					}
				);
			</c:if>

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

			function <portlet:namespace />updateSupportMessage(envLFR) {
				var supportMessageDisplay_5_2 = AUI().one('#<portlet:namespace />supportMessageDisplay_5_2');
				var supportMessageDisplay_6_0 = AUI().one('#<portlet:namespace />supportMessageDisplay_6_0');
				var supportMessageDisplay_6_1 = AUI().one('#<portlet:namespace />supportMessageDisplay_6_1');

				if ((envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_4 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_5 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_6 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_7 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_8 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_9 %>)) {
					supportMessageDisplay_5_2.show();
				}
				else {
					supportMessageDisplay_5_2.hide();
				}

				if ((envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_0_10 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_0_11 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_0_12 %>)) {
					supportMessageDisplay_6_0.show();
				}
				else {
					supportMessageDisplay_6_0.hide();
				}

				if ((envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_1_10 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_1_20 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_1_30 %>)) {
					supportMessageDisplay_6_1.show();
				}
				else {
					supportMessageDisplay_6_1.hide();
				}
			}

			function <portlet:namespace />updateTicketEnvLFR(envLFRKey, envLFRData) {
				var envLFR = AUI().one("#<portlet:namespace />envLFR");

				if (envLFR.getData('key') == envLFRKey) {
					return;
				}

				envLFR.setData('key', envLFRKey);

				var envLFROptions = [];

				envLFROptions.push('<option value="0"><liferay-ui:message key="select" /></option>');

				if (envLFRData) {
					var previousNamePrefix = '';

					for (var i = 0; i < envLFRData.length; i++) {
						var value = envLFRData[i].value;
						var name = envLFRData[i].name;

						var namePrefix = name.substring(0, 3);

						if (namePrefix != previousNamePrefix) {
							envLFROptions.push('<option disabled>--------</option>');
						}

						envLFROptions.push('<option value="' + value + '">' + name + '</option>');

						previousNamePrefix = namePrefix;
					}
				}

				envLFROptions = envLFROptions.join('');

				envLFR.empty();

				envLFR.append(envLFROptions);

				envLFR.val(0);
			}

			Liferay.provide(
				window,
				'<portlet:namespace />selectPortalVersion',
				function(envLFR, envAS, envASName, envDB, envDBName, envJVM, envJVMName, envOS, envOSName) {
					var A = AUI();

					if (envLFR <= 0) {
						var envTypes = ["envAS", "envDB", "envJVM", "envOS"];

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

			Liferay.provide(
				window,
				'<portlet:namespace />selectProductEntry',
				function(offeringEntryId) {
					var A = AUI();

					if (offeringEntryId <= 0) {
						A.one("#<portlet:namespace />envLFR").empty();

						A.one("#<portlet:namespace />envLFR").setData('key', '');

						return;
					}

					A.io.request(
						'<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="ticketEnvLFR" />',
						{
							data: {
								<portlet:namespace />offeringEntryId: offeringEntryId
							},
							dataType: 'json',
							method: 'post',
							on: {
								success: function(event, id, obj) {
									var response = this.get('responseData');

									<portlet:namespace />updateTicketEnvLFR(response["ENV_LFR#key"], response["ENV_LFR"]);
								}
							}
						}
					);
				},
				['aui-io']
			);

			Liferay.provide(
				window,
				'<portlet:namespace />submit',
				function() {
					var A = AUI();

					var name = A.one("#<portlet:namespace />name");
					var offering = A.one('#<portlet:namespace />offeringEntryId');
					var envLFR = A.one('#<portlet:namespace />envLFR');
					var envAS = A.one('#<portlet:namespace />envAS');
					var envDB = A.one('#<portlet:namespace />envDB');
					var envJVM = A.one('#<portlet:namespace />envJVM');
					var envOS = A.one('#<portlet:namespace />envOS');

					if (name.val().trim().length == 0) {
						alert('<liferay-ui:message key="please-provide-a-unique-environment-name" />');

						name.focus();

						return false;
					}

					if (offering.val() == 0) {
						alert('<liferay-ui:message key="please-choose-a-product" />');

						offering.focus();

						return false;
					}

					if (envLFR.val() == 0) {
						alert('<liferay-ui:message key="please-choose-a-liferay-portal-version" />');

						envLFR.focus();

						return false;
					}

					if (envOS.val() == 0) {
						alert('<liferay-ui:message key="please-choose-an-operating-system" />');

						envLFR.focus();

						return false;
					}

					if (envJVM.val() == 0) {
						alert('<liferay-ui:message key="please-choose-a-java-virtual-machine" />');

						envLFR.focus();

						return false;
					}

					if (envAS.val() == 0) {
						alert('<liferay-ui:message key="please-choose-an-application-server" />');

						envLFR.focus();

						return false;
					}

					if (envDB.val() == 0) {
						alert('<liferay-ui:message key="please-choose-a-database" />');

						envLFR.focus();

						return false;
					}

					A.io.request(
						'<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="accountEnvironment" />',
						{
							data: {
								<portlet:namespace />accountEntryId: <%= accountEntryId %>,
								<portlet:namespace />accountEnvironmentId: <%= accountEnvironmentId %>,
								<portlet:namespace />name: name.val().trim(),
								<portlet:namespace />offeringEntryId: offering.val()
							},
							dataType: 'json',
							method: 'post',
							on: {
								success: function(event, id, obj) {
									var response = this.get('responseData');

									if (response && (response.exists == 'true')) {
										alert('<liferay-ui:message key="please-provide-a-unique-environment-name" />');

										name.focus();
									}
									else {
										submitForm(document.<portlet:namespace />fm1);
									}
								}
							}
						}
					);
				},
				['aui-io']
			);
		</aui:script>
	</c:otherwise>
</c:choose>