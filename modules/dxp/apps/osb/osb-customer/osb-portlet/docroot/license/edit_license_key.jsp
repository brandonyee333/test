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

LicenseKeySet licenseKeySet = null;

try {
	licenseKeySet = LicenseKeySetLocalServiceUtil.getLicenseKeySet(licenseKeySetId);
}
catch (NoSuchLicenseKeySetException nslkse) {
}

long accountEntryId = BeanParamUtil.getLong(licenseKeySet, request, "accountEntryId");

AccountEntry accountEntry = null;

boolean addLicensePermission = false;

try {
	if (accountEntryId > 0) {
		accountEntry = AccountEntryServiceUtil.getAccountEntry(accountEntryId);

		addLicensePermission = OSBAccountEntryPermission.contains(permissionChecker, accountEntryId, OSBActionKeys.ADD_LICENSE);
	}
}
catch (NoSuchAccountEntryException nsaee) {
}

long offeringEntryId = ParamUtil.getLong(request, "offeringEntryId");

OfferingEntry offeringEntry = null;

try {
	offeringEntry = OfferingEntryLocalServiceUtil.getOfferingEntry(offeringEntryId);

	if (licenseKeySet == null) {
		accountEntry = offeringEntry.getAccountEntry();
	}
}
catch (NoSuchOfferingEntryException nsoee) {
}

long productEntryId = ParamUtil.getLong(request, "productEntryId");

ProductEntry productEntry = null;

try {
	productEntry = ProductEntryLocalServiceUtil.getProductEntry(productEntryId);
}
catch (NoSuchProductEntryException nspee) {
}

long licenseEntryId = ParamUtil.getLong(request, "licenseEntryId");

LicenseEntry licenseEntry = null;

String licenseEntryType = StringPool.BLANK;

try {
	licenseEntry = LicenseEntryLocalServiceUtil.getLicenseEntry(licenseEntryId);

	licenseEntryType = licenseEntry.getType();
}
catch (NoSuchLicenseEntryException nspee) {
}

int startDateDay = 0;
int startDateMonth = 0;
int startDateYear = 0;

OrderEntry orderEntry = null;
OfferingDefinition offeringDefinition = null;
Date startDate = null;
long licenseKeyCount = 0;
long licenseKeyMaxServers = 0;

if (offeringEntry != null) {
	orderEntry = offeringEntry.getOrderEntry();

	startDateDay = ParamUtil.getInteger(request, offeringEntryId + "startDateDay");
	startDateMonth = ParamUtil.getInteger(request, offeringEntryId + "startDateMonth");
	startDateYear = ParamUtil.getInteger(request, offeringEntryId + "startDateYear");

	if ((startDateDay > 0) && (startDateMonth >= 0) && (startDateYear > 0)) {
		Calendar calendar = Calendar.getInstance(timeZone, locale);

		calendar.set(startDateYear, startDateMonth, startDateDay);

		startDate = calendar.getTime();
	}
	else if (licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER) || licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER)) {
		startDate = new Date();
	}
	else {
		startDate = orderEntry.getStartDate();
	}

	OfferingEntryGroup offeringEntryGroup = offeringEntry.getOfferingEntryGroup();

	licenseKeyCount += offeringEntryGroup.getLicenseKeysCount();
	licenseKeyMaxServers += offeringEntryGroup.getQuantity();
}

long clusterId = ParamUtil.getLong(request, "clusterId");
int productVersion = ParamUtil.getInteger(request, "productVersion");
int maxServers = ParamUtil.getInteger(request, "maxServers");
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

portletURL.setParameter("mvcPath", "/license/edit_license_key.jsp");
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("licenseKeySetId", String.valueOf(licenseKeySetId));
portletURL.setParameter("clusterId", String.valueOf(clusterId));
portletURL.setParameter("accountEntryId", String.valueOf(accountEntryId));
portletURL.setParameter("offeringEntryId", String.valueOf(offeringEntryId));
portletURL.setParameter("productEntryId", String.valueOf(productEntryId));
portletURL.setParameter("productVersion", String.valueOf(productVersion));
portletURL.setParameter("licenseEntryId", String.valueOf(licenseEntryId));
%>

<portlet:actionURL name="updateLicenseKey" var="updateLicenseKeyURL">
	<portlet:param name="mvcPath" value="/license/edit_license_key.jsp" />
</portlet:actionURL>

<div class="container-fluid-1280">
	<aui:row>
		<aui:form action="<%= updateLicenseKeyURL %>" cssClass="col-md-12" method="post" onSubmit='<%= "submitForm(document." + renderResponse.getNamespace() + "fm);" %>'>
			<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
			<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
			<aui:input name="licenseKeySetId" type="hidden" value="<%= licenseKeySetId %>" />
			<aui:input name="clusterId" type="hidden" value="<%= clusterId %>" />
			<aui:input name="offeringEntryId" type="hidden" value="<%= offeringEntryId %>" />

			<div class="section">
				<div class="pull-right">
					<aui:button onClick="<%= backURL %>" value="back-to-previous-page" />
				</div>
			</div>

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

			<c:choose>
				<c:when test="<%= offeringEntry == null %>">
					<aui:col md="12">
						<h2 class="section-heading">
							<liferay-ui:message key="project" />
						</h2>

						<%
						List<AccountEntry> accountEntries = new ArrayList<AccountEntry>();

						if (hasUpdateAdmin) {
							accountEntries = AccountEntryLocalServiceUtil.getAccountEntries(AccountEntryConstants.STATUSES_ACTIVE, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
						}
						else {
							LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

							params.put("accountWorker", new Long[] {user.getUserId(), null});
							params.put("status", AccountEntryConstants.STATUSES_ACTIVE);

							accountEntries = AccountEntryLocalServiceUtil.search(null, params, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
						}
						%>

						<c:choose>
							<c:when test="<%= licenseKeySet != null %>">
								<strong><%= HtmlUtil.escape(accountEntry.getName()) %></strong>

								<aui:input label="" name="accountEntryId" type="hidden" value="<%= accountEntry.getAccountEntryId() %>" />
							</c:when>
							<c:when test="<%= accountEntries.size() == 1 %>">

								<%
								accountEntry = accountEntries.get(0);

								accountEntryId = accountEntry.getAccountEntryId();

								addLicensePermission = OSBAccountEntryPermission.contains(permissionChecker, accountEntryId, OSBActionKeys.ADD_LICENSE);
								%>

								<strong><%= HtmlUtil.escape(accountEntry.getName()) %></strong>

								<aui:input label="" name="accountEntryId" type="hidden" value="<%= accountEntryId %>" />
							</c:when>
							<c:otherwise>

								<%
								String taglibOnChange = renderResponse.getNamespace() + "updateLicenseKey('', '', '', 0);";
								%>

								<aui:select label="" name="accountEntryId" onChange="<%= taglibOnChange %>">
									<aui:option value="" />

									<%
									for (AccountEntry curAccountEntry : accountEntries) {
									%>

										<aui:option label="<%= curAccountEntry.getName() %>" selected="<%= curAccountEntry.getAccountEntryId() == accountEntryId %>" value="<%= curAccountEntry.getAccountEntryId() %>" />

									<%
									}
									%>

								</aui:select>
							</c:otherwise>
						</c:choose>
					</aui:col>

					<%
					String taglibOnChange = renderResponse.getNamespace() + "updateLicenseKey(this.value, '', '', 0);";
					%>

					<aui:col md="12">
						<h2 class="section-heading">
							<liferay-ui:message key="product" />
						</h2>

						<aui:select label="" name="productEntryId" onChange="<%= taglibOnChange %>">
							<aui:option value="" />

							<%
							if (accountEntry != null) {
								LinkedHashMap params = new LinkedHashMap();

								params.put("licenseOfferingEntries", Long.valueOf(accountEntry.getAccountEntryId()));

								List<ProductEntry> productEntries = ProductEntryLocalServiceUtil.search(null, params, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

								for (ProductEntry curProductEntry : productEntries) {
							%>

									<aui:option label="<%= curProductEntry.getName() %>" selected="<%= curProductEntry.getProductEntryId() == productEntryId %>" value="<%= curProductEntry.getProductEntryId() %>" />

							<%
								}
							}
							%>

						</aui:select>
					</aui:col>

					<aui:col md="6">
						<h2 class="section-heading">
							<liferay-ui:message key="choose-liferay-version" />
						</h2>

						<%
						taglibOnChange = renderResponse.getNamespace() + "updateLicenseKey(document." + renderResponse.getNamespace() + "fm." + renderResponse.getNamespace() + "productEntryId.value, this.value, '', 0);";
						%>

						<aui:select label="" name="productVersion" onChange="<%= taglibOnChange %>">
							<aui:option value="" />

							<%
							if (productEntry != null) {
								List<ListType> productVersionTypes = productEntry.getAllVersionsListTypes();

								String previousNamePrefix = StringPool.BLANK;

								for (ListType productVersionType : productVersionTypes) {
									if ((productVersionType.getListTypeId() == ProductEntryConstants.PORTAL_VERSION_4_4_0) || (productVersionType.getListTypeId() == ProductEntryConstants.PORTAL_VERSION_OTHER)) {
										continue;
									}

									String name = productVersionType.getName();

									String namePrefix = name.substring(0, 3);
							%>

									<c:if test="<%= Validator.isNotNull(previousNamePrefix) && !previousNamePrefix.equals(namePrefix) %>">
										<aui:option disabled="<%= true %>">--------</aui:option>
									</c:if>

									<aui:option label="<%= productVersionType.getName() %>" selected="<%= productVersionType.getListTypeId() == productVersion %>" value="<%= productVersionType.getListTypeId() %>" />

							<%
									previousNamePrefix = namePrefix;
								}
							}
							%>

						</aui:select>
					</aui:col>

					<aui:col md="6">
						<h2 class="section-heading">
							<liferay-ui:message key="type" />
						</h2>

						<%
						taglibOnChange = renderResponse.getNamespace() + "updateLicenseKey(document." + renderResponse.getNamespace() + "fm." + renderResponse.getNamespace() + "productEntryId.value, document." + renderResponse.getNamespace() + "fm." + renderResponse.getNamespace() + "productVersion.value, this.value, 0);";
						%>

						<aui:select label="" name="licenseEntryId" onChange="<%= taglibOnChange %>">
							<aui:option value="" />

							<%
							if (productVersion > 0) {
								List<LicenseEntry> licenseEntries = LicenseEntryLocalServiceUtil.getLicenseEntries(productEntryId, productVersion);

								for (LicenseEntry curLicenseEntry : licenseEntries) {
							%>

									<aui:option selected="<%= curLicenseEntry.getLicenseEntryId() == licenseEntryId %>" value="<%= curLicenseEntry.getLicenseEntryId() %>"><%= curLicenseEntry.getName() %> (<%= LanguageUtil.get(request, curLicenseEntry.getType()) %>)</aui:option>

							<%
								}
							}
							%>

						</aui:select>
					</aui:col>

					<aui:col md="12">
						<h2 class="section-heading">
							<liferay-ui:message key="choose-license" />
						</h2>

						<%
						List<OfferingEntryGroup> offeringEntryGroups = new ArrayList<OfferingEntryGroup>();

						if ((accountEntry != null) && (productVersion >= 0) && (productEntry != null) && (licenseEntry != null)) {
							LinkedHashMap params = new LinkedHashMap();

							params.put("validLicense", new Long[] {productEntryId, productEntryId});
							params.put("version", ProductEntryConstants.getMajorVersion(productVersion));

							offeringEntryGroups = SupportUtil.getOfferingEntryGroups(0, accountEntryId, new int[] {OfferingEntryConstants.TYPE_REGULAR, OfferingEntryConstants.TYPE_SUBSCRIPTION}, new int[0], 0, 0, 0, 0, 0, 0, params, true);
						}
						%>

						<liferay-ui:search-container
							delta="<%= 10 %>"
							headerNames="start-date,lifetime,license-keys-available"
							iteratorURL="<%= portletURL %>"
							total="<%= offeringEntryGroups.size() %>"
						>
							<liferay-ui:search-container-results
								results="<%= ListUtil.subList(offeringEntryGroups, searchContainer.getStart(), searchContainer.getEnd()) %>"
							/>

							<liferay-ui:search-container-row
								className="com.liferay.osb.model.OfferingEntryGroup"
								modelVar="offeringEntryGroup"
							>

								<%
								Calendar cal = Calendar.getInstance(timeZone, locale);

								if ((accountEntry.getType() != AccountEntryConstants.TYPE_TRIAL) && !licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER) && !licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER)) {
									cal.setTime(offeringEntryGroup.getStartDate());
								}

								OfferingEntry availableOfferingEntry = null;

								String rowHREF = null;

								if (addLicensePermission && (offeringEntryGroup.getStatus() == OfferingEntryConstants.STATUS_ACTIVE) && offeringEntryGroup.hasAvailableServers()) {
									availableOfferingEntry = offeringEntryGroup.getAvailableLicenseOfferingEntry();

									StringBuilder sb = new StringBuilder();

									sb.append("javascript:");
									sb.append(renderResponse.getNamespace());
									sb.append("updateLicenseKey('', '', '', ");
									sb.append(availableOfferingEntry.getOfferingEntryId());
									sb.append(");");

									rowHREF = sb.toString();
								}
								%>

								<liferay-ui:search-container-column-text
									name="start-date"
								>
									<c:choose>
										<c:when test="<%= (availableOfferingEntry != null) && hasUpdateAdmin %>">

											<%
											Calendar calendar = CalendarFactoryUtil.getCalendar(2010, 1, 1);

											Date firstEnabledDate = calendar.getTime();

											calendar = CalendarFactoryUtil.getCalendar(2050, 1, 1);

											Date lastEnabledDate = calendar.getTime();
											%>

											<liferay-ui:input-date
												dayParam='<%= availableOfferingEntry.getOfferingEntryId() + "startDateDay" %>'
												dayValue="<%= cal.get(Calendar.DAY_OF_MONTH) %>"
												firstDayOfWeek="<%= cal.getFirstDayOfWeek() %>"
												firstEnabledDate="<%= firstEnabledDate %>"
												formName='<%= "fm" %>'
												lastEnabledDate="<%= lastEnabledDate %>"
												monthParam='<%= availableOfferingEntry.getOfferingEntryId() + "startDateMonth" %>'
												monthValue="<%= cal.get(Calendar.MONTH) %>"
												yearParam='<%= availableOfferingEntry.getOfferingEntryId() + "startDateYear" %>'
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
								>
									<%= (offeringEntryGroup.getLicenseLifetime() / Time.DAY) + " Days" %>

									<c:if test="<%= offeringEntryGroup.getType() == OfferingEntryConstants.TYPE_SUBSCRIPTION %>">
										- <%= LanguageUtil.get(request, OfferingEntryConstants.getTypeLabel(offeringEntryGroup.getType())) %>
									</c:if>
								</liferay-ui:search-container-column-text>

								<liferay-ui:search-container-column-text
									href="<%= rowHREF %>"
									name="license-keys-available"
								>
									<c:choose>
										<c:when test="<%= offeringEntryGroup.getStatus() == OfferingEntryConstants.STATUS_CLOSED %>">
											<liferay-ui:icon
												image="close"
												label="<%= true %>"
												message="closed"
											/>
										</c:when>
										<c:when test="<%= offeringEntryGroup.getStatus() == OfferingEntryConstants.STATUS_ON_HOLD %>">
											<liferay-ui:icon
												image="lock"
												label="<%= true %>"
												message="on-hold"
											/>
										</c:when>
										<c:otherwise>
											<%= offeringEntryGroup.getQuantity() - offeringEntryGroup.getLicenseKeysCount() %>
										</c:otherwise>
									</c:choose>
								</liferay-ui:search-container-column-text>

								<liferay-ui:search-container-column-text
									href="<%= rowHREF %>"
								>
									<c:if test="<%= addLicensePermission && (offeringEntryGroup.getStatus() == OfferingEntryConstants.STATUS_ACTIVE) && offeringEntryGroup.hasAvailableServers() %>">
										<aui:button onClick="<%= rowHREF %>" value="choose" />
									</c:if>
								</liferay-ui:search-container-column-text>
							</liferay-ui:search-container-row>

							<liferay-ui:search-iterator />
						</liferay-ui:search-container>
					</aui:col>
				</c:when>
				<c:otherwise>
					<aui:col md="12">
						<h2 class="section-heading">
							<liferay-ui:message key="name" />
						</h2>

						<c:choose>
							<c:when test="<%= licenseKeySet != null %>">
								<strong><%= HtmlUtil.escape(licenseKeySet.getName()) %></strong>
							</c:when>
							<c:otherwise>
								<aui:input bean="<%= licenseKeySet %>" label="" model="<%= LicenseKeySet.class %>" name="name" value="<%= accountEntry.getName() %>" />
							</c:otherwise>
						</c:choose>
					</aui:col>

					<aui:col md="6">
						<h2 class="section-heading">
							<liferay-ui:message key="owner" />
						</h2>

						<aui:input label="" model="<%= LicenseKey.class %>" name="owner" value="<%= accountEntry.getName() %>" />
					</aui:col>

					<aui:col md="6">
						<h2 class="section-heading">
							<liferay-ui:message key="description" />
						</h2>

						<aui:input label="" model="<%= LicenseKey.class %>" name="description" value="<%= accountEntry.getName() %>" />
					</aui:col>

					<aui:col md="12">
						<h2 class="section-heading">
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

						<%= longDateFormatDate.format(new Date(startDate.getTime() + offeringEntry.getLicenseLifetime())) %>
					</aui:col>

					<aui:col md="4">
						<span class="bold uppercase"><liferay-ui:message key="version" />:</span>

						<%= LanguageUtil.get(request, LicenseKeyConstants.getProductVersionLabel(productVersion)) %>

						<aui:input name="productVersion" type="hidden" value="<%= productVersion %>" />

						<br />

						<span class="bold uppercase"><liferay-ui:message key="license-keys-available" />:</span>

						<%= licenseKeyMaxServers - licenseKeyCount %>
					</aui:col>

					<c:if test="<%= licenseEntryType.equals(LicenseEntryConstants.TYPE_PER_USER) %>">
						<aui:col md="4">
							<span class="bold uppercase"><liferay-ui:message key="maximum-concurrent-users" />:</span>

							<%= LanguageUtil.get(request, offeringDefinition.getMaxConcurrentUsersLabel()) %>
						</aui:col>

						<aui:col md="4">
							<span class="bold uppercase"><liferay-ui:message key="maximum-users" />:</span>

							<%= LanguageUtil.get(request, offeringDefinition.getMaxUsersLabel()) %>
						</aui:col>
					</c:if>

					<c:choose>
						<c:when test="<%= LicenseKeyConstants.getLicenseVersion(productVersion) >= 3 %>">
							<c:if test="<%= licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER) || licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER) %>">
								<aui:col md="12">
									<h2 class="section-heading">
										<liferay-ui:message key="maximum-connections" />
									</h2>

									<c:choose>
										<c:when test="<%= RoleLocalServiceUtil.hasUserRole(user.getUserId(), OSBConstants.ROLE_OSB_ACCOUNT_ADMIN_ID) || RoleLocalServiceUtil.hasUserRole(user.getUserId(), OSBConstants.ROLE_OSB_ADMINISTRATOR_ID) %>">
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
									<h2 class="section-heading">
										<liferay-ui:message key="maximum-servers" />
									</h2>

									<c:choose>
										<c:when test="<%= clusterId > 0 %>">
											<strong><%= maxServers %></strong>

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
										<h2 class="section-heading">
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
										<h2 class="section-heading">
											<liferay-ui:message key="mac-addresses" />
										</h2>

										<aui:input label="<%= serverIds %>" name="<portlet:namespace />serverIds" type="textarea" />
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
										<h2 class="section-heading">
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
										<h2 class="section-heading">
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

					<div>
						<aui:col md="12">
							<aui:button type="submit" value="generate" />

							<aui:button onClick="<%= backURL %>" value="cancel" />
						</aui:col>
					</div>
				</c:otherwise>
			</c:choose>
		</aui:form>
	</aui:row>
</div>

<aui:script>
	function <portlet:namespace />updateLicenseKey(productEntryId, productVersion, licenseEntryId, offeringEntryId) {
		var A = AUI();

		var updateURL = '<portlet:renderURL><portlet:param name="mvcPath" value="/license/edit_license_key.jsp" /></portlet:renderURL>';

		if (offeringEntryId > 0) {
			A.one('#<portlet:namespace />offeringEntryId').val(offeringEntryId);
		}
		else {
			A.one('#<portlet:namespace />productEntryId').val(productEntryId);
			A.one('#<portlet:namespace />productVersion').val(productVersion);
			A.one('#<portlet:namespace />licenseEntryId').val(licenseEntryId);
		}

		submitForm(document.<portlet:namespace />fm, updateURL);
	}
</aui:script>