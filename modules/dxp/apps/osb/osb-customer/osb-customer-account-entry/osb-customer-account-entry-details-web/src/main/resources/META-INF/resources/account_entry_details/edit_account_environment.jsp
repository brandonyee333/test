<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/init.jsp" %>

<c:choose>
	<c:when test='<%= SessionMessages.contains(renderRequest, "requestProcessed") %>'>
		<aui:script>
			Liferay.Util.getOpener().Liferay.Portlet.refresh('#p_p_id_<%= AccountEntryDetailsPortletKeys.ACCOUNT_ENTRY_DETAILS %>_', '');

			Liferay.fire(
				'closeWindow',
				{
					id: '<portlet:namespace />editAccountEnvironment'
				}
			);
		</aui:script>
	</c:when>
	<c:otherwise>

		<%
		AccountEntry accountEntry = (AccountEntry)renderRequest.getAttribute(AccountEntryDetailsWebKeys.ACCOUNT_ENTRY);
		AccountEnvironment accountEnvironment = (AccountEnvironment)renderRequest.getAttribute(AccountEntryDetailsWebKeys.ACCOUNT_ENVIRONMENT);

		long accountEntryId = BeanParamUtil.getLong(accountEntry, request, "accountEntryId");

		long accountEnvironmentId = BeanParamUtil.getLong(accountEnvironment, request, "accountEnvironmentId");
		long productEntryId = BeanParamUtil.getLong(accountEnvironment, request, "productEntryId");
		int envOS = BeanParamUtil.getInteger(accountEnvironment, request, "envOS");
		String envOSCustom = BeanParamUtil.getString(accountEnvironment, request, "envOSCustom");
		int envDB = BeanParamUtil.getInteger(accountEnvironment, request, "envDB");
		int envJVM = BeanParamUtil.getInteger(accountEnvironment, request, "envJVM");
		int envAS = BeanParamUtil.getInteger(accountEnvironment, request, "envAS");
		int envLFR = BeanParamUtil.getInteger(accountEnvironment, request, "envLFR");
		%>

		<portlet:actionURL name="editAccountEnvironment" var="updateAccountEnvironmentURL" />

		<aui:form action="<%= updateAccountEnvironmentURL %>" enctype="multipart/form-data" method="post">
			<aui:input name="accountEntryId" type="hidden" value="<%= accountEntryId %>" />
			<aui:input name="accountEnvironmentId" type="hidden" value="<%= accountEnvironmentId %>" />

			<liferay-ui:error exception="<%= AccountEnvironmentEnvASException.class %>" message="please-select-a-valid-application-server" />
			<liferay-ui:error exception="<%= AccountEnvironmentEnvDBException.class %>" message="please-select-a-valid-database" />
			<liferay-ui:error exception="<%= AccountEnvironmentEnvLFRException.class %>" message="please-select-a-valid-liferay-version" />
			<liferay-ui:error exception="<%= AccountEnvironmentEnvOSException.class %>" message="please-select-a-valid-operating-system" />

			<liferay-ui:error exception="<%= AccountEnvironmentAttachmentSizeException.class %>">

				<%
				AccountEnvironmentAttachmentSizeException aease = (AccountEnvironmentAttachmentSizeException)errorException;
				%>

				<c:choose>
					<c:when test="<%= aease.getType() == AccountEnvironmentAttachmentSizeException.EMPTY_FILE %>">
						<liferay-ui:message arguments="<%= aease.getFileName() %>" key="x-contains-no-data-and-cannot-be-uploaded" />
					</c:when>
					<c:when test="<%= aease.getType() == AccountEnvironmentAttachmentSizeException.EXCEEDS_LIMIT %>">
						<liferay-ui:message key="please-upload-a-file-less-than-100-mb" />
					</c:when>
				</c:choose>
			</liferay-ui:error>

			<liferay-ui:error exception="<%= AccountEnvironmentNameException.class %>" message="please-provide-a-unique-environment-name-for-the-product" />
			<liferay-ui:error exception="<%= DuplicateAccountEnvironmentException.class %>" message="please-provide-a-unique-environment-name" />

			<aui:model-context bean="<%= accountEnvironment %>" model="<%= AccountEnvironment.class %>" />

			<div class="container-fluid-1280">
				<aui:row>
					<aui:col width="<%= 100 %>">
						<aui:input inlineField="<%= true %>" name="name" />
					</aui:col>
				</aui:row>

				<aui:row>
					<aui:col width="<%= 75 %>">
						<aui:select disabled="<%= productEntryId > 0 %>" id="productEntryId" label="product" name="productEntryId" onChange='<%= renderResponse.getNamespace() + "selectProductEntry(this.value);" %>'>
							<aui:option label="select" value="0" />

							<%
							LinkedHashMap params = new LinkedHashMap();

							params.put("validTicket", StringPool.BLANK);

							List<OfferingEntryGroup> offeringEntryGroups = OfferingEntryGroupFactoryUtil.createOfferingEntryGroups(0, accountEntryId, new int[0], new int[] {OfferingEntryConstants.STATUS_ACTIVE, OfferingEntryConstants.STATUS_ON_HOLD}, 0, 0, 0, 0, 0, 0, params, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

							for (OfferingEntryGroup offeringEntryGroup : offeringEntryGroups) {
								ProductEntry curProductEntry = offeringEntryGroup.getProductEntry();
								OfferingEntry curOfferingEntry = offeringEntryGroup.getAvailableSupportOfferingEntry();
							%>

								<aui:option label="<%= curProductEntry.getName() %>" selected="<%= curProductEntry.getProductEntryId() == productEntryId %>" value="<%= curOfferingEntry.getProductEntryId() %>" />

							<%
							}
							%>

						</aui:select>
					</aui:col>

					<aui:col width="<%= 25 %>">
						<aui:select label="liferay-version" name="envLFR" onChange='<%= renderResponse.getNamespace() + "selectPortalVersion(this.value, 0, '', 0, '', 0, '', 0, '');" %>'>
							<aui:option value="0" />

							<c:if test="<%= accountEnvironment != null %>">

								<%
								ProductEntry productEntry = ProductEntryLocalServiceUtil.getProductEntry(accountEnvironment.getProductEntryId());

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
				</aui:row>

				<aui:row>
					<aui:col width="<%= 50 %>">
						<aui:select label="operating-system" name="envOS" onChange='<%= renderResponse.getNamespace() + "selectEnvOS(this.value);" %>'>
							<aui:option value="0" />
						</aui:select>

						<br />

						<aui:input cssClass='<%= (envOS == AccountEnvironmentConstants.ENV_OS_OTHER) ? "" : "hide" %>' label="" maxLength="75" name="envOSCustom" type="text" value="<%= envOSCustom %>" />
					</aui:col>

					<aui:col width="<%= 50 %>">
						<aui:select label="java-version" name="envJVM">
							<aui:option value="0" />
						</aui:select>
					</aui:col>
				</aui:row>

				<aui:row>
					<aui:col width="<%= 50 %>">
						<aui:select label="application-server" name="envAS">
							<aui:option value="0" />
						</aui:select>
					</aui:col>

					<aui:col width="<%= 50 %>">
						<aui:select label="database" name="envDB">
							<aui:option value="0" />
						</aui:select>
					</aui:col>
				</aui:row>

				<aui:row>
					<aui:col width="<%= 100 %>">
						<liferay-ui:message key="portal-ext" />

						<%
						AccountEnvironmentAttachment portalExtAccountEnvironmentAttachment = null;

						if (accountEnvironment != null) {
							portalExtAccountEnvironmentAttachment = AccountEnvironmentAttachmentLocalServiceUtil.fetchAccountEnvironmentAttachment(accountEnvironmentId, AccountEnvironmentAttachmentConstants.TYPE_PORTAL_EXT);
						}
						%>

						<c:if test="<%= portalExtAccountEnvironmentAttachment != null %>">
							<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/account_environment_attachment" var="accountEnvironmentAttachmentURL">
								<portlet:param name="accountEnvironmentAttachmentId" value="<%= String.valueOf(portalExtAccountEnvironmentAttachment.getAccountEnvironmentAttachmentId()) %>" />
							</liferay-portlet:resourceURL>

							<aui:a href="<%= accountEnvironmentAttachmentURL.toString() %>" label="<%= portalExtAccountEnvironmentAttachment.getFileName() %>" target="_blank" />
						</c:if>

						<aui:input label="" name="portal-ext" type="file" />
					</aui:col>
				</aui:row>

				<aui:row>
					<aui:col width="<%= 100 %>">
						<liferay-ui:message key="patch-info" />:

						<%
						AccountEnvironmentAttachment patchLevelAccountEnvironmentAttachment = null;

						if (accountEnvironment != null) {
							patchLevelAccountEnvironmentAttachment = AccountEnvironmentAttachmentLocalServiceUtil.fetchAccountEnvironmentAttachment(accountEnvironmentId, AccountEnvironmentAttachmentConstants.TYPE_PATCH_LEVEL);
						}
						%>

						<c:if test="<%= patchLevelAccountEnvironmentAttachment != null %>">
							<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/account_environment_attachment" var="accountEnvironmentAttachmentURL">
								<portlet:param name="accountEnvironmentAttachmentId" value="<%= String.valueOf(patchLevelAccountEnvironmentAttachment.getAccountEnvironmentAttachmentId()) %>" />
							</liferay-portlet:resourceURL>

							<aui:a href="<%= accountEnvironmentAttachmentURL.toString() %>" label="<%= patchLevelAccountEnvironmentAttachment.getFileName() %>" target="_blank" />
						</c:if>

						<aui:input label="" name="patch-level" type="file" />
					</aui:col>
				</aui:row>

				<aui:button type="submit" value="save" />
			</div>
		</aui:form>

		<aui:script use="aui-base,aui-io">
			<portlet:namespace />selectPortalVersion(<%= envLFR %>, <%= envAS %>, '<%= LanguageUtil.get(request, AccountEnvironmentConstants.getEnvLabel(envAS)) %>', <%= envDB %>, '<%= LanguageUtil.get(request, AccountEnvironmentConstants.getEnvLabel(envDB)) %>', <%= envJVM %>, '<%= LanguageUtil.get(request, AccountEnvironmentConstants.getEnvLabel(envJVM)) %>', <%= envOS %>, '<%= LanguageUtil.get(request, AccountEnvironmentConstants.getEnvLabel(envOS)) %>');
		</aui:script>

		<aui:script>
			function <portlet:namespace />selectEnvOS(envOS) {
				var A = AUI();

				var envOSCustom = A.one('#<portlet:namespace />envOSCustom');

				if (envOS == '<%= AccountEnvironmentConstants.ENV_OS_OTHER %>') {
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

					var accountEntryId = A.one('#<portlet:namespace />accountEntryId').val();
					var productEntryId = A.one('#<portlet:namespace />productEntryId').val();

					A.io.request(
						'<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/account_environment_list_types" />',
						{
							data: {
								<portlet:namespace />accountEntryId: accountEntryId,
								<portlet:namespace />envLFR: envLFR,
								<portlet:namespace />productEntryId: productEntryId
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
				function(productEntryId) {
					var A = AUI();

					if (productEntryId <= 0) {
						A.one("#<portlet:namespace />envLFR").empty();

						A.one("#<portlet:namespace />envLFR").setData('key', '');

						return;
					}

					A.io.request(
						'<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/product_entry_version_list_types" />',
						{
							data: {
								<portlet:namespace />productEntryId: productEntryId
							},
							dataType: 'json',
							method: 'post',
							on: {
								success: function(event, id, obj) {
									var response = this.get('responseData');

									<portlet:namespace />updateEnvLFR(response["ENV_LFR#key"], response["ENV_LFR"]);
								}
							}
						}
					);
				},
				['aui-io']
			);

			function <portlet:namespace />updateEnvLFR(envLFRKey, envLFRData) {
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
		</aui:script>
	</c:otherwise>
</c:choose>