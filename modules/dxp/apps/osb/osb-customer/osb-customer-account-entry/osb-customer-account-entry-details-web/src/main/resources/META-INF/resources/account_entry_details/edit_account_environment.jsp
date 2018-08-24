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

		<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/edit_account_environment">
			<portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntryId) %>" />
		</liferay-portlet:resourceURL>

		<portlet:actionURL name="editAccountEnvironment" var="updateAccountEnvironmentURL" />

		<aui:form action="<%= updateAccountEnvironmentURL %>" enctype="multipart/form-data" method="post">
			<aui:input name="accountEntryId" type="hidden" value="<%= accountEntryId %>" />
			<aui:input name="accountEnvironmentId" type="hidden" value="<%= accountEnvironmentId %>" />
			<aui:input name="productEntryId" type="hidden" value="<%= productEntryId %>" />

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
	</c:otherwise>
</c:choose>