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

<%
String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);

long licenseKeySetId = ParamUtil.getLong(request, "licenseKeySetId");

LicenseKeySet licenseKeySet = LicenseKeySetLocalServiceUtil.fetchLicenseKeySet(licenseKeySetId);

String koroneikiProductPurchaseKey = ParamUtil.getString(request, "koroneikiProductPurchaseKey");

ProductPurchase productPurchase = productPurchaseWebService.getProductPurchase(koroneikiProductPurchaseKey);

Account koroneikiAccount = accountWebService.getAccount(productPurchase.getAccountKey());

ProductEntry productEntry = ProductEntryLocalServiceUtil.getProductEntryByKoroneikiKey(productPurchase.getProductKey());

long licenseEntryId = ParamUtil.getLong(request, "licenseEntryId");

LicenseEntry licenseEntry = LicenseEntryLocalServiceUtil.getLicenseEntry(licenseEntryId);

String licenseEntryType = licenseEntry.getType();

int sizing = ParamUtil.getInteger(request, productPurchase.getKey() + "sizing");

int startDateDay = ParamUtil.getInteger(request, productPurchase.getKey() + "startDateDay");
int startDateMonth = ParamUtil.getInteger(request, productPurchase.getKey() + "startDateMonth");
int startDateYear = ParamUtil.getInteger(request, productPurchase.getKey() + "startDateYear");

Date startDate = null;

if ((startDateDay > 0) && (startDateMonth >= 0) && (startDateYear > 0)) {
	Calendar calendar = Calendar.getInstance(timeZone, locale);

	calendar.set(startDateYear, startDateMonth, startDateDay);

	startDate = calendar.getTime();
}
else if (licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER) || licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER)) {
	startDate = new Date();
}
else {
	startDate = productPurchase.getStartDate();
}

int expirationDateDay = ParamUtil.getInteger(request, productPurchase.getKey() + "expirationDateDay");
int expirationDateMonth = ParamUtil.getInteger(request, productPurchase.getKey() + "expirationDateMonth");
int expirationDateYear = ParamUtil.getInteger(request, productPurchase.getKey() + "expirationDateYear");

Date expirationDate = null;

if ((expirationDateDay > 0) && (expirationDateMonth >= 0) && (expirationDateYear > 0)) {
	Calendar calendar = Calendar.getInstance(timeZone, locale);

	calendar.set(expirationDateYear, expirationDateMonth, expirationDateDay);

	expirationDate = calendar.getTime();
}
else {
	expirationDate = productPurchase.getEndDate();
}

long licenseKeyCount = productConsumptionWebService.searchCount("accountKey eq '" + productPurchase.getAccountKey() + "' and productPurchaseKey eq '" + productPurchase.getKey() + "'");
long licenseKeyMaxServers = productPurchase.getQuantity();

long clusterId = ParamUtil.getLong(request, "clusterId");
int productVersion = ParamUtil.getInteger(request, "productVersion");
int maxServers = ParamUtil.getInteger(request, "maxServers");
String serverIds = ParamUtil.getString(request, "serverIds");

int[] serverIdsIndexes = ParamUtil.getIntegerValues(request, "serverIdsIndexes");

if ((serverIdsIndexes == null) || (serverIdsIndexes.length <= 0)) {
	serverIdsIndexes = new int[] {0};
}
%>

<portlet:actionURL name="updateLicenseKey" var="updateLicenseKeyURL">
	<portlet:param name="mvcPath" value="/license/edit_license_key.jsp" />
</portlet:actionURL>

<div class="container-fluid-1280">
	<aui:row>
		<h1>
			<liferay-ui:message key="generate-new-license" />
		</h1>

		<aui:form action="<%= updateLicenseKeyURL %>" cssClass="col-md-12" method="post">
			<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
			<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
			<aui:input name="licenseKeySetId" type="hidden" value="<%= licenseKeySetId %>" />
			<aui:input name="koroneikiProductPurchaseKey" type="hidden" value="<%= koroneikiProductPurchaseKey %>" />
			<aui:input name="koroneikiAccountKey" type="hidden" value="<%= productPurchase.getAccountKey() %>" />
			<aui:input name="accountEntryName" type="hidden" value="<%= koroneikiAccount.getName() %>" />
			<aui:input name="clusterId" type="hidden" value="<%= clusterId %>" />
			<aui:input name="sizing" type="hidden" value="<%= sizing %>" />

			<aui:button-row cssClass="pull-right">
				<aui:button cssClass="btn-sm" onClick="<%= backURL %>" value="back-to-previous-page" />
			</aui:button-row>

			<liferay-ui:error exception="<%= DuplicateHostNameException.class %>" message="you-entered-duplicate-host-names" />
			<liferay-ui:error exception="<%= DuplicateIPAddressException.class %>" message="you-entered-duplicate-ip-addresses" />
			<liferay-ui:error exception="<%= DuplicateMACAddressException.class %>" message="you-entered-duplicate-mac-addresses" />
			<liferay-ui:error exception="<%= LicenseKeyDescriptionException.class %>" message="please-enter-a-valid-description" />
			<liferay-ui:error exception="<%= LicenseKeyHostNameException.class %>" message="please-enter-valid-host-names" />
			<liferay-ui:error exception="<%= LicenseKeyIPAddressException.class %>" message="please-enter-valid-ip-addresses" />
			<liferay-ui:error exception="<%= LicenseKeyMACAddressException.class %>" message="please-enter-valid-mac-addresses" />
			<liferay-ui:error exception="<%= LicenseKeyMaxServersException.class %>" message="please-select-valid-maximum-servers" />
			<liferay-ui:error exception="<%= LicenseKeyOwnerException.class %>" message="please-enter-a-valid-owner" />
			<liferay-ui:error exception="<%= LicenseKeyProductVersionException.class %>" message="please-enter-a-valid-version" />
			<liferay-ui:error exception="<%= LicenseKeyServerIdException.class %>" message="please-enter-a-valid-server-id" />
			<liferay-ui:error exception="<%= LicenseKeySetNameException.class %>" message="please-enter-a-valid-name" />
			<liferay-ui:error exception="<%= MaximumLicenseKeyException.class %>" message="the-number-of-keys-generated-has-exceeded-the-number-of-allowed-keys" />

			<aui:col md="12">
				<h2 class="control-label">
					<liferay-ui:message key="name" />
				</h2>

				<c:choose>
					<c:when test="<%= licenseKeySet != null %>">
						<span class="semi-bold"><%= HtmlUtil.escape(licenseKeySet.getName()) %></span>
					</c:when>
					<c:otherwise>
						<aui:input bean="<%= licenseKeySet %>" label="" model="<%= LicenseKeySet.class %>" name="name" value="<%= koroneikiAccount.getName() %>" />
					</c:otherwise>
				</c:choose>
			</aui:col>

			<aui:col md="6">
				<h2 class="control-label">
					<liferay-ui:message key="owner" />
				</h2>

				<aui:input label="" model="<%= LicenseKey.class %>" name="owner" value="<%= koroneikiAccount.getName() %>" />
			</aui:col>

			<aui:col md="6">
				<h2 class="control-label">
					<liferay-ui:message key="description" />
				</h2>

				<aui:input label="" model="<%= LicenseKey.class %>" name="description" value="<%= koroneikiAccount.getName() %>" />
			</aui:col>

			<aui:col md="12">
				<h2 class="control-label">
					<liferay-ui:message key="license-info" />
				</h2>
			</aui:col>

			<aui:col md="4">
				<span class="bold uppercase"><liferay-ui:message key="product" />:</span>

				<%= productEntry.getName() %>

				<aui:input label="" name="productEntryId" type="hidden" value="<%= productEntry.getProductEntryId() %>" />

				<br />

				<span class="bold uppercase"><liferay-ui:message key="start-date" />:</span>

				<%= longDateFormatDate.format(startDate) %>

				<aui:input name="startDateMonth" type="hidden" value="<%= startDateMonth %>" />
				<aui:input name="startDateDay" type="hidden" value="<%= startDateDay %>" />
				<aui:input name="startDateYear" type="hidden" value="<%= startDateYear %>" />
			</aui:col>

			<aui:col md="4">
				<span class="bold uppercase"><liferay-ui:message key="type" />:</span>

				<%= HtmlUtil.escape(LanguageUtil.get(request, licenseEntryType)) %>

				<aui:input label="" name="licenseEntryId" type="hidden" value="<%= licenseEntryId %>" />

				<br />

				<span class="bold uppercase"><liferay-ui:message key="expiration-date" />:</span>

				<%= longDateFormatDate.format(expirationDate) %>

				<aui:input name="expirationDateMonth" type="hidden" value="<%= expirationDateMonth %>" />
				<aui:input name="expirationDateDay" type="hidden" value="<%= expirationDateDay %>" />
				<aui:input name="expirationDateYear" type="hidden" value="<%= expirationDateYear %>" />
			</aui:col>

			<aui:col md="4">
				<span class="bold uppercase"><liferay-ui:message key="version" />:</span>

				<%= LanguageUtil.get(request, LicenseKeyConstants.getProductVersionLabel(productVersion)) %>

				<aui:input name="productVersion" type="hidden" value="<%= productVersion %>" />

				<br />

				<span class="bold uppercase"><liferay-ui:message key="license-keys-available" />:</span>

				<%= licenseKeyMaxServers - licenseKeyCount %>
			</aui:col>

			<c:choose>
				<c:when test="<%= LicenseKeyConstants.getLicenseVersion(productVersion) >= 3 %>">
					<c:if test="<%= licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER) || licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER) %>">
						<aui:col md="12">
							<h2 class="control-label">
								<liferay-ui:message key="maximum-connections" />
							</h2>

							<c:choose>
								<c:when test="<%= RoleLocalServiceUtil.hasUserRole(user.getUserId(), OSBCustomerConstants.ROLE_OSB_ACCOUNT_ADMIN_ID) || RoleLocalServiceUtil.hasUserRole(user.getUserId(), OSBCustomerConstants.ROLE_OSB_ADMINISTRATOR_ID) %>">
									<aui:select label="" name="maxHttpSessions">
										<aui:option value="5">5</aui:option>
										<aui:option value="6">6</aui:option>
										<aui:option value="7">7</aui:option>
										<aui:option value="8">8</aui:option>
										<aui:option value="9">9</aui:option>
										<aui:option value="10">10</aui:option>
									</aui:select>
								</c:when>
								<c:otherwise>
									5

									<aui:input name="maxHttpSessions" type="hidden" value="5" />
								</c:otherwise>
							</c:choose>
						</aui:col>
					</c:if>

					<c:if test="<%= licenseEntryType.equals(LicenseEntryConstants.TYPE_CLUSTER) %>">
						<aui:col md="12">
							<h2 class="control-label">
								<liferay-ui:message key="maximum-servers" />
							</h2>

							<c:choose>
								<c:when test="<%= clusterId > 0 %>">
									<span class="semi-bold"><%= maxServers %></span>

									<aui:input name="maxServers" type="hidden" value="<%= maxServers %>" />
								</c:when>
								<c:otherwise>
									<aui:select label="" name="maxServers">
										<aui:option value="" />
										<aui:option value="1">1</aui:option>
										<aui:option value="2">2</aui:option>
										<aui:option value="3">3</aui:option>
										<aui:option value="4">4</aui:option>
										<aui:option value="5">5</aui:option>
										<aui:option value="6">6</aui:option>
										<aui:option value="7">7</aui:option>
										<aui:option value="8">8</aui:option>
										<aui:option value="9">9</aui:option>
										<aui:option value="10">10</aui:option>
										<aui:option value="11">11</aui:option>
										<aui:option value="12">12</aui:option>
										<aui:option value="13">13</aui:option>
										<aui:option value="14">14</aui:option>
										<aui:option value="15">15</aui:option>
									</aui:select>
								</c:otherwise>
							</c:choose>
						</aui:col>
					</c:if>

					<c:choose>
						<c:when test="<%= licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER) || licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER) %>">
							<aui:input name="serverIds" type="hidden" value="<%= LicenseKeyConstants.SERVER_ID_DEVELOPER %>" />
						</c:when>
						<c:when test="<%= licenseEntryType.equals(LicenseEntryConstants.TYPE_ELASTIC) %>">
							<aui:input name="serverIds" type="hidden" value="<%= LicenseKeyConstants.SERVER_ID_ELASTIC %>" />
						</c:when>
						<c:when test="<%= licenseEntryType.equals(LicenseEntryConstants.TYPE_ENTERPRISE) %>">
							<aui:input name="serverIds" type="hidden" value="<%= LicenseKeyConstants.SERVER_ID_ENTERPRISE %>" />
						</c:when>
						<c:when test="<%= licenseEntryType.equals(LicenseEntryConstants.TYPE_OEM) %>">
							<aui:input name="serverIds" type="hidden" value="<%= LicenseKeyConstants.SERVER_ID_OEM %>" />
						</c:when>
						<c:otherwise>
							<aui:col md="12">
								<h2 class="control-label">
									<liferay-ui:message key="server-id-fields" />
								</h2>

								<div id="<portlet:namespace />serverIds">
									<aui:fieldset>

										<%
										for (int serverIdsIndex : serverIdsIndexes) {
										%>

											<div class="lfr-form-row">
												<div class="row-fields">
													<aui:input fieldParam='<%= "hostName" + serverIdsIndex %>' label="host-name" name='<%= "hostName" + serverIdsIndex %>' type="text" />

													<aui:input fieldParam='<%= "ipAddresses" + serverIdsIndex %>' label="ip-addresses" name='<%= "ipAddresses" + serverIdsIndex %>' type="textarea" />

													<aui:input fieldParam='<%= "macAddresses" + serverIdsIndex %>' label="mac-addresses" name='<%= "macAddresses" + serverIdsIndex %>' type="textarea" />
												</div>
											</div>

										<%
										}
										%>

										<aui:input name="serverIdsIndexes" type="hidden" value="<%= StringUtil.merge(serverIdsIndexes) %>" />
									</aui:fieldset>
								</div>
							</aui:col>

							<aui:script use="liferay-auto-fields">
								new Liferay.AutoFields(
									{
										contentBox: '#<portlet:namespace />serverIds > fieldset',
										fieldIndexes: '<portlet:namespace />serverIdsIndexes'
									}
								).render();
							</aui:script>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:when test="<%= LicenseKeyConstants.getLicenseVersion(productVersion) == 2 %>">
					<c:choose>
						<c:when test="<%= licenseEntryType.equals(LicenseEntryConstants.TYPE_CLUSTER) || licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER) %>">
							<aui:col md="12">
								<h2 class="control-label">
									<liferay-ui:message key="maximum-servers" />
								</h2>

								<aui:select label="" name="maxServers">
									<aui:option value="" />
									<aui:option value="1">1</aui:option>
									<aui:option value="2">2</aui:option>
									<aui:option value="3">3</aui:option>
									<aui:option value="4">4</aui:option>
									<aui:option value="5">5</aui:option>
									<aui:option value="6">6</aui:option>
									<aui:option value="7">7</aui:option>
									<aui:option value="8">8</aui:option>
									<aui:option value="9">9</aui:option>
									<aui:option value="10">10</aui:option>
									<aui:option value="11">11</aui:option>
									<aui:option value="12">12</aui:option>
									<aui:option value="13">13</aui:option>
									<aui:option value="14">14</aui:option>
									<aui:option value="15">15</aui:option>
								</aui:select>

								<aui:input name="serverIds" type="hidden" value="<%= LicenseKeyConstants.SERVER_ID_CLUSTER %>" />
							</aui:col>
						</c:when>
						<c:when test="<%= licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER) %>">
							<aui:input name="serverIds" type="hidden" value="<%= LicenseKeyConstants.SERVER_ID_DEVELOPER %>" />
						</c:when>
						<c:when test="<%= licenseEntryType.equals(LicenseEntryConstants.TYPE_ENTERPRISE) %>">
							<aui:input name="serverIds" type="hidden" value="<%= LicenseKeyConstants.SERVER_ID_ENTERPRISE %>" />
						</c:when>
						<c:when test="<%= licenseEntryType.equals(LicenseEntryConstants.TYPE_OEM) %>">
							<aui:input name="serverIds" type="hidden" value="<%= LicenseKeyConstants.SERVER_ID_OEM %>" />
						</c:when>
						<c:when test="<%= licenseEntryType.equals(LicenseEntryConstants.TYPE_PRODUCTION) %>">
							<aui:col md="12">
								<h2 class="control-label">
									<liferay-ui:message key="mac-addresses" />
								</h2>

								<aui:input label="<%= serverIds %>" name="serverIds" type="textarea" />
							</aui:col>
						</c:when>
						<c:when test="<%= licenseEntryType.equals(LicenseEntryConstants.TYPE_TRIAL) %>">
							<aui:input name="serverIds" type="hidden" value="<%= LicenseKeyConstants.SERVER_ID_TRIAL %>" />
						</c:when>
					</c:choose>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="<%= licenseEntryType.equals(LicenseEntryConstants.TYPE_CLUSTER) || licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER) %>">
							<aui:col md="12">
								<h2 class="control-label">
									<liferay-ui:message key="mac-addresses" />
								</h2>

								<aui:input label="<%= serverIds %>" name="serverIds" type="textarea" />
							</aui:col>
						</c:when>
						<c:when test="<%= licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER) %>">
							<aui:input name="serverIds" type="hidden" value="<%= LicenseKeyConstants.SERVER_ID_DEVELOPER %>" />
						</c:when>
						<c:when test="<%= licenseEntryType.equals(LicenseEntryConstants.TYPE_ENTERPRISE) %>">
							<aui:input name="serverIds" type="hidden" value="<%= LicenseKeyConstants.SERVER_ID_ENTERPRISE %>" />
						</c:when>
						<c:when test="<%= licenseEntryType.equals(LicenseEntryConstants.TYPE_OEM) %>">
							<aui:input name="serverIds" type="hidden" value="<%= LicenseKeyConstants.SERVER_ID_OEM %>" />
						</c:when>
						<c:when test="<%= licenseEntryType.equals(LicenseEntryConstants.TYPE_PRODUCTION) %>">
							<aui:col md="12">
								<h2 class="control-label">
									<liferay-ui:message key="server-id" />
								</h2>

								<aui:input label="" name="serverIds" type="text" value="<%= serverIds %>" />
							</aui:col>
						</c:when>
						<c:when test="<%= licenseEntryType.equals(LicenseEntryConstants.TYPE_TRIAL) %>">
							<aui:input name="serverIds" type="hidden" value="<%= LicenseKeyConstants.SERVER_ID_TRIAL %>" />
						</c:when>
					</c:choose>
				</c:otherwise>
			</c:choose>

			<aui:col md="12">
				<aui:button primary="<%= true %>" type="submit" value="generate" />

				<aui:button onClick="<%= backURL %>" value="cancel" />
			</aui:col>
		</aui:form>
	</aui:row>
</div>