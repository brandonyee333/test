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

<div class="account-environment-dialog">
	<c:choose>
		<c:when test="<%= SessionMessages.contains(renderRequest, PortalUtil.getPortletId(renderRequest) + SessionMessages.KEY_SUFFIX_REFRESH_PORTLET) %>">
			<aui:script>
				Liferay.Util.getWindow().hide();
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

			String productEntryName = StringPool.BLANK;

			if (productEntryId > 0) {
				productEntry = ProductEntryLocalServiceUtil.getProductEntry(productEntryId);

				productEntryName = productEntry.getName();
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
				<portlet:param name="mvcPath" value="/support/2/edit_account_environment.jsp" />
			</portlet:actionURL>

			<aui:form action="<%= updateAccountEnvironmentURL %>" cssClass="container-fluid-1280" enctype="multipart/form-data" method="post" name="updateAccountEnvironmentFm">
				<aui:input name="accountEnvironmentId" type="hidden" value="<%= accountEnvironmentId %>" />
				<aui:input name="accountEntryId" type="hidden" value="<%= accountEntryId %>" />

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
				<liferay-ui:error exception="<%= DuplicateAccountEnvironmentException.class %>" message="please-enter-a-unique-name" />
				<liferay-ui:error exception="<%= PrincipalException.class %>" message="you-do-not-have-the-required-permissions" />

				<liferay-util:include page="/support/2/common/eosl_environment_liferay.jsp" servletContext="<%= application %>">
					<portlet:param name="envLFR" value="<%= String.valueOf(envLFR) %>" />
					<portlet:param name="support2Enabled" value="<%= Boolean.TRUE.toString() %>" />
				</liferay-util:include>

				<div class="edit-account-environment">
					<aui:container>
						<aui:row>
							<aui:col width="<%= 50 %>">
								<aui:input label="name" maxLength='<%= ModelHintsUtil.getMaxLength(AccountEnvironment.class.getName(), "name") %>' name="name" required="<%= true %>" type="text" value='<%= (accountEnvironment != null) ? accountEnvironment.getName() : "" %>' />
							</aui:col>

							<aui:col width="<%= 50 %>">
								<aui:select label="product" name="offeringEntryId" onChange='<%= renderResponse.getNamespace() + "selectProductEntry(this.value);" %>' required="<%= true %>">
										<aui:option label="select" value="" />

										<%
										LinkedHashMap params = new LinkedHashMap();

										params.put("validTicket", StringPool.BLANK);

										List<OfferingEntryGroup> offeringEntryGroups = SupportUtil.getOfferingEntryGroups(0, accountEntryId, new int[0], new int[] {OfferingEntryConstants.STATUS_ACTIVE, OfferingEntryConstants.STATUS_ON_HOLD}, 0, 0, 0, 0, 0, 0, params, true);

										Map<String, Long> productEntryEnvironments = new HashMap<String, Long>();

										for (OfferingEntryGroup offeringEntryGroup : offeringEntryGroups) {
											ProductEntry curProductEntry = offeringEntryGroup.getProductEntry();

											if (!productEntryEnvironments.containsKey(curProductEntry.getName())) {
												OfferingEntry curOfferingEntry = offeringEntryGroup.getAvailableSupportOfferingEntry();

												productEntryEnvironments.put(curProductEntry.getName(), curOfferingEntry.getOfferingEntryId());
											}
										}

										for (Map.Entry<String, Long> entry : productEntryEnvironments.entrySet()) {
										%>

											<aui:option label="<%= entry.getKey() %>" selected="<%= productEntryName.equals(entry.getKey()) %>" value="<%= entry.getValue() %>" />

										<%
										}
										%>

									</aui:select>

									<c:if test="<%= productEntryId > 0 %>">
										<aui:input name="offeringEntryId" type="hidden" value="<%= offeringEntryId %>" />
									</c:if>
							</aui:col>
						</aui:row>

						<aui:row>
							<aui:col width="<%= 33 %>">
								<%
								String envLFROnChange = renderResponse.getNamespace() + "selectPortalVersion(this.value, 0, '', 0, '', 0, '', 0, ''); " + renderResponse.getNamespace() + "updateSupportMessage(this.value);";
								%>

								<aui:select label="lr" name="envLFR" onChange="<%= envLFROnChange.toString() %>" required="<%= true %>" title="liferay-version">
									<c:if test="<%= productEntry != null %>">
										<aui:option label="select" />

										<%
										List<ListType> envLFRTypes = productEntry.getAllVersionsListTypes();

										String previousNamePrefix = StringPool.BLANK;

										long[] listTypesDeprecated = Arrays.stream(ProductEntryConstants.LIST_TYPES_DEPRECATED).asLongStream().toArray();

										for (ListType envLFRType : envLFRTypes) {
											if ((envLFR != envLFRType.getListTypeId()) && ArrayUtil.contains(listTypesDeprecated, envLFRType.getListTypeId())) {
												continue;
											}

											String name = envLFRType.getName();

											String namePrefix = name.substring(0, 3);
										%>

											<c:if test="<%= Validator.isNotNull(previousNamePrefix) && !previousNamePrefix.equals(namePrefix) %>">
												<aui:option disabled="<%= true %>" label="--------" />
											</c:if>

											<aui:option label="<%= envLFRType.getName() %>" selected="<%= envLFRType.getListTypeId() == envLFR %>" value="<%= envLFRType.getListTypeId() %>" />

										<%
											previousNamePrefix = namePrefix;
										}
										%>

									</c:if>
								</aui:select>
							</aui:col>

							<aui:col width="<%= 33 %>">
								<aui:select label="os" name="envOS" onChange='<%= renderResponse.getNamespace() + "selectEnvOS(this.value);" %>' required="<%= true %>" title="operating-system">
									<aui:option />
								</aui:select>

								<aui:input cssClass='<%= (envOS == TicketEntryConstants.ENV_OS_OTHER) ? "" : "hide" %>' label="" maxLength="<%= TicketInformationConstants.getMaxLength(TicketInformationConstants.FIELD_ENV_OS_CUSTOM) %>" name="envOSCustom" type="text" value="<%= envOSCustom %>" />
							</aui:col>

							<aui:col width="<%= 33 %>">
								<aui:select label="jvm" name="envJVM" required="<%= true %>" title="java-virtual-machine">
									<aui:option />
								</aui:select>
							</aui:col>
						</aui:row>

						<aui:row>
							<aui:col width="<%= 33 %>">
								<aui:select label="as" name="envAS" required="<%= true %>" title="application-server">
									<aui:option />
								</aui:select>
							</aui:col>

							<aui:col width="<%= 33 %>">
								<aui:select label="db" name="envDB" required="<%= true %>" title="database">
									<aui:option />
								</aui:select>
							</aui:col>
						</aui:row>

						<aui:row>
							<aui:col width="<%= 100 %>">
								<div class="properties-file">
									<%
									AccountEnvironmentAttachment portalExtAccountEnvironmentAttachment = null;

									if (accountEnvironment != null) {
										portalExtAccountEnvironmentAttachment = AccountEnvironmentAttachmentLocalServiceUtil.fetchAccountEnvironmentAttachment(accountEnvironmentId, AccountEnvironmentAttachmentConstants.TYPE_PORTAL_EXT);
									}
									%>

									<aui:input label="portal-ext" name="portalExt" type="file" value="upload" wrapperCssClass="properties-file-input">
										<aui:validator name="required">
											function() {
												return !<%= portalExtAccountEnvironmentAttachment != null %>;
											}
										</aui:validator>
									</aui:input>

									<c:if test="<%= portalExtAccountEnvironmentAttachment != null %>">

										<%
										LiferayPortletURL accountEnvironmentAttachmentURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

										accountEnvironmentAttachmentURL.setCopyCurrentRenderParameters(false);
										accountEnvironmentAttachmentURL.setParameter("accountEnvironmentAttachmentId", String.valueOf(portalExtAccountEnvironmentAttachment.getAccountEnvironmentAttachmentId()));
										accountEnvironmentAttachmentURL.setResourceID("accountEnvironmentAttachment");
										%>

										<aui:a href="<%= accountEnvironmentAttachmentURL.toString() %>" label="<%= HtmlUtil.escape(portalExtAccountEnvironmentAttachment.getFileName()) %>"  target="_blank" />
									</c:if>
								</div>
							</aui:col>
						</aui:row>

						<aui:row>
							<aui:col width="<%= 100 %>">
								<div class="properties-file">

									<%
									AccountEnvironmentAttachment patchLevelAccountEnvironmentAttachment = null;

									if (accountEnvironment != null) {
										patchLevelAccountEnvironmentAttachment = AccountEnvironmentAttachmentLocalServiceUtil.fetchAccountEnvironmentAttachment(accountEnvironmentId, AccountEnvironmentAttachmentConstants.TYPE_PATCH_LEVEL);
									}
									%>

									<aui:input label="patch-level" name="patchLevel" type="file" value="upload" wrapperCssClass="properties-file-input">
										<aui:validator name="required">
											function() {
												return !<%= patchLevelAccountEnvironmentAttachment != null %>;
											}
										</aui:validator>
									</aui:input>

									<c:if test="<%= patchLevelAccountEnvironmentAttachment != null %>">

										<%
										LiferayPortletURL accountEnvironmentAttachmentURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

										accountEnvironmentAttachmentURL.setCopyCurrentRenderParameters(false);
										accountEnvironmentAttachmentURL.setParameter("accountEnvironmentAttachmentId", String.valueOf(patchLevelAccountEnvironmentAttachment.getAccountEnvironmentAttachmentId()));
										accountEnvironmentAttachmentURL.setResourceID("accountEnvironmentAttachment");
										%>

										<aui:a href="<%= accountEnvironmentAttachmentURL.toString() %>" label="<%= HtmlUtil.escape(patchLevelAccountEnvironmentAttachment.getFileName()) %>" target="_blank" />
									</c:if>
								</div>
							</aui:col>
						</aui:row>

						<aui:button-row>
							<aui:button cssClass="pull-left" onClick="Liferay.Util.getWindow().hide();" value="cancel" />

							<aui:button cssClass="pull-right" onClick='<%= renderResponse.getNamespace() + "submit();" %>' value='<%= (accountEnvironmentId == 0) ? "create" : "update" %>' />
						</aui:button-row>
					</aui:container>
				</div>
			</aui:form>

			<aui:script>
				function <portlet:namespace />focusNode(node) {
					node.focus();

					return false;
				}

				Liferay.provide(
					window,
					'<portlet:namespace />toggleProduct',
					function(liferay) {
						var A = AUI();

						var envLFR = A.one('#<portlet:namespace />portalVersion');

						if (envLFR) {
							if (liferay == false) {
								envLFR.set('text', '*<liferay-ui:message key="so" />:');

								envLFR.attr('title', '<liferay-ui:message key="social-office" />');
							}
							else if (envLFR.get('text') != '*<liferay-ui:message key="lr" />:') {
								envLFR.set('text', '*<liferay-ui:message key="lr" />:');

								envLFR.attr('title', '<liferay-ui:message key="liferay-version" />');
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

						if (envOSCustom) {
							var condition = (envOS == '<%= TicketEntryConstants.ENV_OS_OTHER %>');

							envOSCustom.toggle(condition);

							if (!condition) {
								envOSCustom.val('');
							}
						}
					},
					['aui-base']
				);

				Liferay.provide(
					window,
					'<portlet:namespace />selectPortalVersion',
					function(envLFR, envAS, envASName, envDB, envDBName, envJVM, envJVMName, envOS, envOSName) {
						var A = AUI();

						if (envLFR <= 0) {
							var envTypes = ['envAS', 'envDB', 'envJVM', 'envOS'];

							for (var envType in envTypes) {
								var envElement = A.one('#<portlet:namespace />' + envTypes[envType]);

								if (envElement) {
									envElement.empty();

									envElement.setData('key', '');
								}
							}
						}
						else {
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
						}
					},
					['aui-io']
				);

				Liferay.provide(
					window,
					'<portlet:namespace />selectProductEntry',
					function(offeringEntryId) {
						var A = AUI();

						if (offeringEntryId === 0) {
							var envLFR = A.one('#<portlet:namespace />envLFR');

							if (envLFR) {
								envLFR.empty();

								envLFR.setData('key', '');
							}
						}
						else {
							A.io.request(
								'<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="ticketEnvLFR" />',
								{
									data: {
										<portlet:namespace />offeringEntryId: offeringEntryId
									},
									dataType: 'JSON',
									method: 'POST',
									on: {
										success: function() {
											var response = this.get('responseData');

											<portlet:namespace />toggleProduct(response.portal);

											<portlet:namespace />updateTicketEnvLFR(response['ENV_LFR#key'], response['ENV_LFR']);
										}
									}
								}
							);
						}
					},
					['aui-io']
				);

				Liferay.provide(
					window,
					'<portlet:namespace />submit',
					function() {
						var A = AUI();

						var name = A.one('#<portlet:namespace />name');
						var offeringEntryId = A.one('#<portlet:namespace />offeringEntryId');

						if (name && offeringEntryId) {
							var nameVal = name.val().trim();
							var offeringEntryIdVal = offeringEntryId.val();

							if (offeringEntryIdVal === 0) {
								alert('<liferay-ui:message key="please-choose-a-product" />');

								return <portlet:namespace />focusNode(offeringEntryId);
							}

							A.io.request(
								'<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="accountEnvironment" />',
								{
									data: {
										<portlet:namespace />accountEntryId: <%= accountEntryId %>,
										<portlet:namespace />accountEnvironmentId: <%= accountEnvironmentId %>,
										<portlet:namespace />name: nameVal,
										<portlet:namespace />offeringEntryId: offeringEntryIdVal
									},
									dataType: 'JSON',
									method: 'POST',
									on: {
										success: function() {
											var response = this.get('responseData');

											if (response && response.exists == 'true') {
												alert('<liferay-ui:message key="please-provide-a-unique-environment-name" />');

												Liferay.Util.focusFormField(name)
											}
											else {
												submitForm(document.<portlet:namespace />updateAccountEnvironmentFm);
											}
										}
									}
								}
							);
						}
					},
					['aui-io']
				);

				Liferay.provide(
					window,
					'<portlet:namespace />updateEnvironmentField',
					function(selectId, selectDataKey, selectData, selectVal, selectName) {
						var A = AUI();

						var selectElement = A.one('#' + selectId);

						if (selectElement && (selectElement.getData('key') != selectDataKey)) {
							selectElement.setData('key', selectDataKey);

							var selectOptions = [];

							selectOptions.push('<option></option>');

							var selectValExists = false;

							if (selectData) {
								for (var i = 0; i < selectData.length; i++) {
									var currentItem = selectData[i];

									var name = currentItem.name;
									var value = currentItem.value;

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
					},
					['aui-base']
				);

				Liferay.provide(
					window,
					'<portlet:namespace />updateSupportMessage',
					function(envLFR) {
						var A = AUI();

						var supportMessageDisplay_5_2 = A.one('#<portlet:namespace />supportMessageDisplay_5_2');

						if (supportMessageDisplay_5_2) {
							var visibleSupportMessageDisplay_5_2 = (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_4 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_5 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_6 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_7 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_8 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_9 %>);

							supportMessageDisplay_5_2.toggle(visibleSupportMessageDisplay_5_2);
						}

						var supportMessageDisplay_6_0 = A.one('#<portlet:namespace />supportMessageDisplay_6_0');

						if (supportMessageDisplay_6_0) {
							var visibleSupportMessageDisplay_6_0 = (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_0_10 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_0_11 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_0_12 %>);

							supportMessageDisplay_6_0.toggle(visibleSupportMessageDisplay_6_0);
						}

						var supportMessageDisplay_6_1 = A.one('#<portlet:namespace />supportMessageDisplay_6_1');

						if (supportMessageDisplay_6_1) {
							var visibleSupportMessageDisplay_6_1 = (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_1_10 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_1_20 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_1_30 %>);

							supportMessageDisplay_6_1.toggle(visibleSupportMessageDisplay_6_1);
						}
					},
					['aui-base']
				);

				Liferay.provide(
					window,
					'<portlet:namespace />updateTicketEnvLFR',
					function(envLFRKey, envLFRData) {
						var A = AUI();

						var envLFR = A.one('#<portlet:namespace />envLFR');

						if (envLFR && (envLFR.getData('key') != envLFRKey)) {
							envLFR.setData('key', envLFRKey);

							var envLFROptions = [];

							envLFROptions.push('<option><liferay-ui:message key="select" /></option>');

							if (envLFRData) {
								var previousNamePrefix = '';

								for (var i = 0; i < envLFRData.length; i++) {
									var currentItem = envLFRData[i];

									var name = currentItem.name;
									var value = currentItem.value;

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
					},
					['aui-base']
				);

				var server = document.getElementById('<portlet:namespace />offeringEntryId');

				if (server.options[server.selectedIndex].text.includes('<liferay-ui:message key="social-office" />')) {
					<portlet:namespace />toggleProduct(false);
				}

				<c:if test="<%= accountEnvironment != null %>">
					<portlet:namespace />selectPortalVersion(<%= envLFR %>, <%= envAS %>, '<%= LanguageUtil.get(request, AccountEnvironmentConstants.getEnvLabel(envAS)) %>', <%= envDB %>, '<%= LanguageUtil.get(request, AccountEnvironmentConstants.getEnvLabel(envDB)) %>', <%= envJVM %>, '<%= LanguageUtil.get(request, AccountEnvironmentConstants.getEnvLabel(envJVM)) %>', <%= envOS %>, '<%= LanguageUtil.get(request, AccountEnvironmentConstants.getEnvLabel(envOS)) %>');
				</c:if>
			</aui:script>
		</c:otherwise>
	</c:choose>
</div>