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

<%-- TODO integrate marketplace licensing

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);

long licenseKeyId = ParamUtil.getLong(request, "licenseKeyId");

LicenseKey licenseKey = null;

try {
	licenseKey = LicenseKeyServiceUtil.getLicenseKey(licenseKeyId);
}
catch (NoSuchLicenseKeyException nslke) {
}

long assetReceiptLicenseId = BeanParamUtil.getLong(licenseKey, request, "assetReceiptLicenseId");

AssetReceiptLicense assetReceiptLicense = null;

try {
	assetReceiptLicense = AssetReceiptLicenseLocalServiceUtil.getAssetReceiptLicense(assetReceiptLicenseId);
}
catch (NoSuchAssetReceiptLicenseException nsarie) {
}

long appEntryId = ParamUtil.getLong(request, "appEntryId");

AppEntry appEntry = null;

AssetReceipt assetReceipt = null;

try {
	appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

	assetReceipt = AssetReceiptLocalServiceUtil.getAssetReceipt(User.class.getName(), themeDisplay.getUserId(), AppEntry.class.getName(), appEntryId);
}
catch (NoSuchAppEntryException nsaee) {
}
catch (NoSuchAssetReceiptException nsare) {
}

int startDateDay = 0;
int startDateMonth = 0;
int startDateYear = 0;

Date startDate = null;

if (assetReceiptLicense != null) {
	startDateDay = ParamUtil.getInteger(request, assetReceiptLicenseId + "startDateDay");
	startDateMonth = ParamUtil.getInteger(request, assetReceiptLicenseId + "startDateMonth");
	startDateYear = ParamUtil.getInteger(request, assetReceiptLicenseId + "startDateYear");

	if ((startDateDay > 0) && (startDateMonth >= 0) && (startDateYear > 0)) {
		Calendar calendar = Calendar.getInstance(timeZone, locale);

		calendar.set(startDateYear, startDateMonth, startDateDay);

		startDate = calendar.getTime();
	}
	else {
		startDate = assetReceiptLicense.getStartDate();
	}
}

String serverIds = ParamUtil.getString(request, "serverIds");

int[] serverIdsIndexes = ParamUtil.getIntegerValues(request, "serverIdsIndexes");

if ((serverIdsIndexes == null) || (serverIdsIndexes.length <= 0)) {
	serverIdsIndexes = new int[] {0};
}

boolean hasUpdateAdmin = false;

if (RoleLocalServiceUtil.hasUserRole(user.getUserId(), OSBConstants.ROLE_OSB_ACCOUNT_ADMIN_ID) || RoleLocalServiceUtil.hasUserRole(user.getUserId(), OSBConstants.ROLE_OSB_ADMINISTRATOR_ID)) {
	hasUpdateAdmin = true;
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/license/edit_app_license_key.jsp");
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("licenseKeyId", String.valueOf(licenseKeyId));
portletURL.setParameter("assetReceiptLicenseId", String.valueOf(assetReceiptLicenseId));
portletURL.setParameter("appEntryId", String.valueOf(appEntryId));
%>

<portlet:actionURL name="updateLicenseKey" var="updateLicenseKeyURL">
	<portlet:param name="mvcPath" value="/license/edit_app_license_key.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateLicenseKeyURL %>" class="uni-form" method="post" onSubmit='<%= "submitForm(document." + renderResponse.getNamespace() + "fm);" %>'>
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="assetReceiptLicenseId" type="hidden" value="<%= assetReceiptLicenseId %>" />

	<div class="section">
		<div class="pull-right">
			<aui:a cssClass="btn" href="<%= backURL %>" label="back-to-previous-page" />
		</div>
	</div>

	<liferay-ui:error exception="<%= LicenseKeyDescriptionException.class %>" message="please-enter-a-valid-description" />
	<liferay-ui:error exception="<%= LicenseKeyHostNameException.class %>" message="you-have-entered-duplicate-host-names" />
	<liferay-ui:error exception="<%= LicenseKeyIPAddressException.class %>" message="please-enter-valid-ip-addresses" />
	<liferay-ui:error exception="<%= LicenseKeyMACAddressException.class %>" message="please-enter-valid-mac-addresses" />
	<liferay-ui:error exception="<%= LicenseKeyMaxServersException.class %>" message="please-select-valid-maximum-servers" />
	<liferay-ui:error exception="<%= LicenseKeyOwnerException.class %>" message="please-enter-a-valid-owner" />
	<liferay-ui:error exception="<%= LicenseKeyProductVersionException.class %>" message="please-enter-a-valid-version" />
	<liferay-ui:error exception="<%= LicenseKeyServerIdException.class %>" message="please-enter-a-valid-server-id" />
	<liferay-ui:error exception="<%= LicenseKeySetNameException.class %>" message="please-enter-a-valid-name" />
	<liferay-ui:error exception="<%= MaximumLicenseKeyException.class %>" message="the-number-of-keys-generated-has-exceeded-the-number-of-allowed-keys" />

	<c:choose>
		<c:when test="<%= licenseKey != null %>">

			<%
			List<LicenseKey> licenseKeys = LicenseKeyServiceUtil.getLicenseKeys(licenseKey.getUserId(), licenseKey.getProductId());

			for (LicenseKey curLicenseKey : licenseKeys) {
				String curLicenseType = curLicenseKey.getLicenseEntryType();
			%>

				<div class="<%= (curLicenseKey.getLicenseKeyId() == licenseKeyId) ? "highlight-cluster" : "" %>" id="<portlet:namespace /><%= curLicenseKey.getLicenseKeyId() %>">
					<c:if test="<%= OSBLicenseKeyPermission.contains(permissionChecker, curLicenseKey.getLicenseKeyId(), OSBActionKeys.UPDATE_ADMIN) %>">
						<div class="aui-w33 content-column">
							<div class="content-column-content left-column">
								<span class="txt-b txt-up">
									<liferay-ui:message key="created-by" />:
								</span>

								<%= HtmlUtil.escape(PortalUtil.getUserName(curLicenseKey.getUserId(), curLicenseKey.getUserName())) %>
							</div>
						</div>

						<div class="aui-w66 content-column">
							<div class="content-column-content right-column">
								<span class="txt-b txt-up">
									<liferay-ui:message key="last-modified" />:
								</span>

								<%= HtmlUtil.escape(PortalUtil.getUserName(curLicenseKey.getModifiedUserId(), curLicenseKey.getModifiedUserName())) %> <liferay-ui:message key="on" /> <%= longDateFormatDateTime.format(curLicenseKey.getModifiedDate()) %>
							</div>
						</div>
					</c:if>

					<div class="aui-w33 content-column">
						<div class="content-column-content left-column">
							<span class="txt-b txt-up">
								<liferay-ui:message key="owner" />:
							</span>

							<%= curLicenseKey.getOwner() %>
						</div>
					</div>

					<div class="aui-w66 content-column">
						<div class="content-column-content right-column">
							<span class="txt-b txt-up">
								<liferay-ui:message key="description" />:
							</span>

							<%= HtmlUtil.escape(curLicenseKey.getDescription()) %>
						</div>
					</div>

					<div class="aui-w33 content-column">
						<div class="content-column-content left-column">
							<span class="txt-b txt-up">
								<liferay-ui:message key="product" />:
							</span>

							<%= HtmlUtil.escape(curLicenseKey.getProductEntryName()) %>

							<br />

							<span class="txt-b txt-up">
								<liferay-ui:message key="start-date" />:
							</span>

							<%= longDateFormatDate.format(curLicenseKey.getStartDate()) %>
						</div>
					</div>

					<div class="aui-w33 content-column">
						<div class="content-column-content middle-column">
							<span class="txt-b txt-up">
								<liferay-ui:message key="type" />:
							</span>

							<%= LanguageUtil.get(request, curLicenseType) %>

							<br />

							<span class="txt-b txt-up">
								<liferay-ui:message key="expiration-date" />:
							</span>

							<%= longDateFormatDate.format(curLicenseKey.getExpirationDate()) %>
						</div>
					</div>

					<div class="aui-w33 content-column">
						<div class="content-column-content right-column">
							<span class="txt-b txt-up">
								<liferay-ui:message key="status" />:
							</span>

							<c:choose>
								<c:when test="<%= curLicenseKey.isExpired() %>">
									<liferay-ui:icon
										image="close"
										label="<%= true %>"
										message="expired"
									/>
								</c:when>
								<c:when test="<%= curLicenseKey.isActive() %>">
									<liferay-ui:icon
										image="activate"
										label="<%= true %>"
										message="active"
									/>
								</c:when>
								<c:otherwise>
									<liferay-ui:icon
										image="deactivate"
										label="<%= true %>"
										message="inactive"
									/>
								</c:otherwise>
							</c:choose>
						</div>
					</div>

					<c:if test="<%= curLicenseType.equals(LicenseEntryConstants.TYPE_PER_USER) %>">
						<div class="aui-w33 content-column">
							<div class="content-column-content left-column">
								<span class="txt-b txt-up">
									<liferay-ui:message key="maximum-concurrent-users" />:
								</span>

								<%= LanguageUtil.get(request, OfferingDefinitionConstants.getMaxConcurrentUsersLabel(curLicenseKey.getMaxConcurrentUsers())) %>
							</div>
						</div>

						<div class="aui-w33 content-column">
							<div class="content-column-content right-column">
								<span class="txt-b txt-up">
									<liferay-ui:message key="maximum-users" />:
								</span>

								<%= LanguageUtil.get(request, OfferingDefinitionConstants.getMaxUsersLabel(curLicenseKey.getMaxUsers())) %>
							</div>
						</div>
					</c:if>

					<div class="aui-w33 content-column">
						<div class="content-column-content left-column">
							<span class="txt-b txt-up">
								<liferay-ui:message key="host-name" />:
							</span>

							<%= HtmlUtil.escape(curLicenseKey.getHostName()) %>
						</div>
					</div>

					<div class="aui-w33 content-column">
						<div class="content-column-content middle-column">
							<table class="lfr-table">
								<tr>
									<td>
										<span class="txt-b txt-up">
											<liferay-ui:message key="ip-addresses" />:
										</span>
									</td>
									<td>

										<%
										for (String ipAddress : StringUtil.split(curLicenseKey.getIpAddresses())) {
										%>

											<%= ipAddress %><br />

										<%
										}
										%>

									</td>
								</tr>
							</table>
						</div>
					</div>

					<div class="aui-w33 content-column">
						<div class="content-column-content right-column">
							<table class="lfr-table">
								<tr>
									<td>
										<span class="txt-b txt-up">
											<liferay-ui:message key="mac-addresses" />:
										</span>
									</td>
									<td>

										<%
										for (String macAddress : StringUtil.split(curLicenseKey.getMacAddresses())) {
										%>

											<%= macAddress %><br />

										<%
										}
										%>

									</td>
								</tr>
							</table>
						</div>
					</div>

					<c:if test="<%= curLicenseKey.isActive() %>">
						<portlet:resourceURL id="licenseKey" var="downloadLicenseFileURL">
							<portlet:param name="mvcPath" value="/license/edit_license_key_set.jsp" />
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="licenseKeyId" value="<%= String.valueOf(curLicenseKey.getLicenseKeyId()) %>" />
						</portlet:resourceURL>

						<liferay-ui:icon
							image="download"
							label="<%= true %>"
							message="download-license-file"
							method="get"
							url="<%= downloadLicenseFileURL.toString() %>"
						/>
					</c:if>

					<c:if test="<%= OSBLicenseKeyPermission.contains(permissionChecker, curLicenseKey.getLicenseKeyId(), OSBActionKeys.UPDATE_ADVANCED) %>">
						<portlet:actionURL name="updateLicenseKey" var="activateLicenseKeyURL">
							<portlet:param name="mvcPath" value="/license/edit_license_key_set.jsp" />
							<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
							<portlet:param name="licenseKeyId" value="<%= String.valueOf(curLicenseKey.getLicenseKeyId()) %>" />
							<portlet:param name="assetReceiptLicenseId" value="<%= String.valueOf(curLicenseKey.getAssetReceiptLicenseId()) %>" />
							<portlet:param name="active" value="<%= String.valueOf(!curLicenseKey.isActive()) %>" />
						</portlet:actionURL>

						<%
						String url = "location.href = '" + HttpUtil.encodeURL(activateLicenseKeyURL.toString()) + "'";

						if (curLicenseKey.isActive()) {
							url = "javascript:if (confirm('" + UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-deactivate-this-license-key") + "')) { " + url + " } else { self.focus(); }";
						}
						else {
							url = "javascript:if (confirm('" + UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-activate-this-license-key") + "')) { " + url + " } else { self.focus(); }";
						}
						%>

						<liferay-ui:icon
							image='<%= curLicenseKey.isActive() ? "deactivate" : "activate" %>'
							label="<%= true %>"
							message='<%= curLicenseKey.isActive() ? "deactivate" : "activate" %>'
							url="<%= url %>"
						/>
					</c:if>
				</div>

			<%
			}
			%>

			<div>
				<c:if test="<%= true %>">
					<portlet:renderURL var="addLicenseKeyURL">
						<portlet:param name="mvcPath" value="/license/edit_app_license_key.jsp" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="productId" value="<%= String.valueOf(licenseKey.getProductId()) %>" />
					</portlet:renderURL>

					<aui:a cssClass="btn btn-default" href="<%= addLicenseKeyURL %>" label="add-new-license-key" />
				</c:if>

				<aui:a cssClass="btn btn-default" href="<%= backURL %>" label="cancel" />
			</div>
		</c:when>
		<c:when test="<%= assetReceiptLicense == null %>">
			<h2 class="section-heading">
				<liferay-ui:message key="product" />
			</h2>

			<%
			LinkedHashMap params = new LinkedHashMap();

			params.put("status", WorkflowConstants.STATUS_APPROVED);
			params.put("assetReceiptLicense", themeDisplay.getUserId());

			List<AppEntry> appEntries = AppEntryLocalServiceUtil.search(null, params, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
			%>

			<c:choose>
				<c:when test="<%= appEntries.size() == 1 %>">

					<%
					appEntry = appEntries.get(0);

					appEntryId = appEntry.getAppEntryId();

					assetReceipt = AssetReceiptLocalServiceUtil.getAssetReceipt(User.class.getName(), themeDisplay.getUserId(), AppEntry.class.getName(), appEntryId);
					%>

					<strong><%= HtmlUtil.escape(appEntry.getTitle()) %></strong>

					<aui:input label="" name="appEntryId" type="hidden" value="<%= appEntryId %>" />
				</c:when>
				<c:otherwise>
					<aui:select label="" name="appEntryId" onChange="<portlet:namespace />updateLicenseKey(0);">
						<aui:option value="" />

						<%
						for (AppEntry curAppEntry : appEntries) {
						%>

							<aui:option label="<%= curAppEntry.getTitle() %>" selected="<%= curAppEntry.getAppEntryId() == appEntryId %>" value="<%= curAppEntry.getAppEntryId() %>" />

						<%
						}
						%>

					</aui:select>
				</c:otherwise>
			</c:choose>

			<h2 class="section-heading">
				<liferay-ui:message key="choose-order" />
			</h2>

			<liferay-ui:search-container
				delta="<%= 10 %>"
				headerNames="start-date,lifetime,license-keys-available"
				iteratorURL="<%= portletURL %>"
				total="<%= assetReceiptLicensesCount %>"
			>

				<%
				List<AssetReceiptLicense> assetReceiptLicenses = new ArrayList<AssetReceiptLicense>();
				int assetReceiptLicensesCount = 0;

				if (assetReceipt != null) {
					assetReceiptLicenses = AssetReceiptLicenseLocalServiceUtil.getAssetReceiptLicenses(assetReceipt.getAssetReceiptId(), searchContainer.getStart(), searchContainer.getEnd(), null);
					assetReceiptLicensesCount = AssetReceiptLicenseLocalServiceUtil.getAssetReceiptLicensesCount(assetReceipt.getAssetReceiptId());
				}
				%>

				<liferay-ui:search-container-results
					results="<%= assetReceiptLicenses %>"
				/>

				<liferay-ui:search-container-row
					className="com.liferay.osb.model.AssetReceiptLicense"
					escapedModel="<%= true %>"
					keyProperty="assetReceiptLicenseId"
					modelVar="curAssetReceiptLicense"
				>

					<%
					Calendar cal = Calendar.getInstance(timeZone, locale);

					cal.setTime(curAssetReceiptLicense.getCreateDate());

					String rowHREF = null;

					if (curAssetReceiptLicense.hasAvailableLicenseKeys()) {
						StringBuilder sb = new StringBuilder();

						sb.append("javascript:");
						sb.append(renderResponse.getNamespace());
						sb.append("updateLicenseKey(");
						sb.append(curAssetReceiptLicense.getAssetReceiptLicenseId());
						sb.append(");");

						rowHREF = sb.toString();
					}
					%>

					<liferay-ui:search-container-column-text
						href="<%= rowHREF %>"
						name="license-type"
						value="<%= AssetLicenseConstants.getLicenseTypeLabel(curAssetReceiptLicense.getLicenseType()) %>"
					/>

					<liferay-ui:search-container-column-text
						name="start-date"
					>
						<c:choose>
							<c:when test="<%= (rowHREF != null) && hasUpdateAdmin %>">

								<%
								Date firstEnabledDate = CalendarFactoryUtil.getCalendar(2010, 1, 1);
								Date lastEnabledDate = CalendarFactoryUtil.getCalendar(2050, 1, 1);
								%>

								<liferay-ui:input-date
									dayParam='<%= curAssetReceiptLicense.getAssetReceiptLicenseId() + "startDateDay" %>'
									dayValue="<%= cal.get(Calendar.DAY_OF_MONTH) %>"
									firstDayOfWeek="<%= cal.getFirstDayOfWeek() %>"
									firstEnabledDate="<%= firstEnabledDate %>"
									formName='<%= "fm" %>'
									lastEnabledDate="<%= lastEnabledDate %>"
									monthParam='<%= curAssetReceiptLicense.getAssetReceiptLicenseId() + "startDateMonth" %>'
									monthValue="<%= cal.get(Calendar.MONTH) %>"
									yearParam='<%= curAssetReceiptLicense.getAssetReceiptLicenseId() + "startDateYear" %>'
									yearValue="<%= cal.get(Calendar.YEAR) %>"
								/>
							</c:when>
							<c:otherwise>
								<c:if test="<%= rowHREF != null %>">
									<a href="<%= rowHREF %>">
								</c:if>

								<%= longDateFormatDate.format(cal.getTime()) %>

								<c:if test="<%= rowHREF != null %>">
									</a>
								</c:if>
							</c:otherwise>
						</c:choose>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						href="<%= rowHREF %>"
						name="lifetime"
						value='<%= (curAssetReceiptLicense.getLicenseLifetime() / Time.DAY) + " Days" %>'
					/>

					<liferay-ui:search-container-column-text
						href="<%= rowHREF %>"
						name="license-keys-available"
					>
						<%= curAssetReceiptLicense.hasUnlimitedServers() ? LanguageUtil.get(request, "unlimited") : curAssetReceiptLicense.getAvailableLicenseKeyCount() %>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						href="<%= rowHREF %>"
					>
						<c:if test="<%= curAssetReceiptLicense.hasAvailableLicenseKeys() %>">
							<aui:button onClick="<%= rowHREF %>" value="choose" />
						</c:if>
					</liferay-ui:search-container-column-text>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator />
			</liferay-ui:search-container>
		</c:when>
		<c:otherwise>
			<div class="aui-w50 content-column">
				<div class="content-column-content left-column">
					<h2 class="section-heading">
						<liferay-ui:message key="owner" />
					</h2>

					<liferay-ui:input-field
						defaultValue="<%= user.getFullName() %>"
						field="owner"
						model="<%= LicenseKey.class %>"
					/>
				</div>
			</div>

			<div class="aui-w50 content-column">
				<div class="content-column-content right-column">
					<h2 class="section-heading">
						<liferay-ui:message key="description" />
					</h2>

					<liferay-ui:input-field
						defaultValue="<%= appEntry.getTitle() %>"
						field="description"
						model="<%= LicenseKey.class %>"
					/>
				</div>
			</div>

			<h2 class="section-heading">
				<liferay-ui:message key="license-info" />
			</h2>

			<div class="aui-w33 content-column">
				<div class="content-column-content left-column">
					<span class="txt-b txt-up">
						<liferay-ui:message key="product" />:
					</span>

					<%= HtmlUtil.escape(appEntry.getTitle()) %>

					<aui:input name="appEntryId" type="hidden" value="<%= appEntry.getAppEntryId() %>" />

					<br />

					<span class="txt-b txt-up">
						<liferay-ui:message key="start-date" />:
					</span>

					<%= longDateFormatDate.format(startDate) %>

					<aui:input name="startDateMonth" type="hidden" value="<%= startDateMonth %>" />
					<aui:input name="startDateDay" type="hidden" value="<%= startDateDay %>" />
					<aui:input name="startDateYear" type="hidden" value="<%= startDateYear %>" />
				</div>
			</div>

			<div class="aui-w33 content-column">
				<div class="content-column-content middle-column">
					<span class="txt-b txt-up">
						<liferay-ui:message key="type" />:
					</span>

					<%= HtmlUtil.escape(LanguageUtil.get(request, AssetLicenseConstants.getLicenseTypeLabel(assetReceiptLicense.getLicenseType()))) %>

					<br />

					<span class="txt-b txt-up">
						<liferay-ui:message key="expiration-date" />:
					</span>

					<%= longDateFormatDate.format(assetReceiptLicense.getEndDate()) %>
				</div>
			</div>

			<div class="aui-w33 content-column">
				<div class="content-column-content right-column">
					<span class="txt-b txt-up">
						<liferay-ui:message key="license-keys-available" />:
					</span>

					<%= assetReceiptLicense.hasUnlimitedServers() ? LanguageUtil.get(request, "unlimited") : assetReceiptLicense.getAvailableLicenseKeyCount() %>
				</div>
			</div>

			<c:if test="<%= assetReceiptLicense.getLicenseType() == AssetLicenseConstants.LICENSE_TYPE_PER_USER %>">
				<div class="aui-w33 content-column">
					<div class="content-column-content left-column">
						<span class="txt-b txt-up">
							<liferay-ui:message key="maximum-users" />:
						</span>

						<%= assetReceiptLicense.getLicenseTypeAllotment() %>
					</div>
				</div>
			</c:if>

			<h2 class="section-heading">
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

			<div>
				<aui:button type="submit" value="generate" />

				<aui:a cssClass="btn btn-default" href="<%= backURL %>" label="cancel" />
			</div>

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
</aui:form>

<aui:script>
	function <portlet:namespace />updateLicenseKey(assetReceiptLicenseId) {
		var updateURL = '<portlet:renderURL><portlet:param name="mvcPath" value="/license/edit_app_license_key.jsp" /></portlet:renderURL>';

		if (assetReceiptLicenseId > 0) {
			document.<portlet:namespace />fm.<portlet:namespace />assetReceiptLicenseId.value = assetReceiptLicenseId;
		}

		submitForm(document.<portlet:namespace />fm, updateURL);
	}
</aui:script> --%>