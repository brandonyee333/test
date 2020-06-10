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

String accountKey = ParamUtil.getString(request, "accountKey");

Account koroneikiAccount = null;

boolean addLicensePermission = false;

if (Validator.isNotNull(accountKey)) {
	koroneikiAccount = accountWebService.getAccount(accountKey);

	addLicensePermission = accountPermission.contains(permissionChecker, koroneikiAccount, OSBActionKeys.ADD_LICENSE);
}

long productEntryId = ParamUtil.getLong(request, "productEntryId");

ProductEntry productEntry = ProductEntryLocalServiceUtil.fetchProductEntry(productEntryId);

int productVersion = ParamUtil.getInteger(request, "productVersion");

long licenseEntryId = ParamUtil.getLong(request, "licenseEntryId");

LicenseEntry licenseEntry = LicenseEntryLocalServiceUtil.fetchLicenseEntry(licenseEntryId);

boolean aggregateLicense = ParamUtil.getBoolean(request, "aggregateLicense");

boolean hasUpdateAdmin = false;

if (RoleLocalServiceUtil.hasUserRole(user.getUserId(), OSBCustomerConstants.ROLE_OSB_ACCOUNT_ADMIN_ID) || RoleLocalServiceUtil.hasUserRole(user.getUserId(), OSBCustomerConstants.ROLE_OSB_ADMINISTRATOR_ID)) {
	hasUpdateAdmin = true;
}
%>

<div class="container-fluid-1280">
	<aui:row>
		<h1>
			<liferay-ui:message key="generate-new-license" />
		</h1>

		<portlet:renderURL var="updateLicenseKeyURL">
			<portlet:param name="mvcPath" value="/license/edit_license_key.jsp" />
		</portlet:renderURL>

		<aui:form action="<%= updateLicenseKeyURL %>" cssClass="col-md-12" method="post">
			<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
			<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
			<aui:input name="productPurchaseKey" type="hidden" value="" />
			<aui:input name="aggregateLicense" type="hidden" value="<%= aggregateLicense %>" />

			<aui:button-row cssClass="pull-right">
				<aui:button cssClass="btn-sm" onClick="<%= backURL %>" value="back-to-previous-page" />
			</aui:button-row>

			<aui:col md="12">
				<h2 class="control-label">
					<liferay-ui:message key="account" />
				</h2>

				<c:choose>
					<c:when test="<%= hasUpdateAdmin %>">
						<span class="account-entry-name semi-bold" id="<portlet:namespace />accountName"><%= (koroneikiAccount != null) ? HtmlUtil.escape(koroneikiAccount.getName()) : "" %></span>

						<input class="btn btn-default select-account-entry" onClick="var accountWindow = window.open('<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/license/select_account.jsp" /><portlet:param name="callback" value="selectAccount" /></portlet:renderURL>', 'account', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); accountWindow.focus();" type="button" value="<liferay-ui:message key="select" />" />

						<aui:input name="accountKey" type="hidden" value='<%= (koroneikiAccount != null) ? koroneikiAccount.getKey() : "" %>' />
					</c:when>
					<c:otherwise>

						<%
						StringBundler sb = new StringBundler(5);

						sb.append("status eq ");
						sb.append(Account.Status.APPROVED);
						sb.append(" and workerContactUuids/any(s:s eq '");
						sb.append(user.getUuid());
						sb.append("')");

						List<Account> accounts = accountWebService.search(StringPool.BLANK, sb.toString(), 1, 1000, StringPool.BLANK);
						%>

						<c:choose>
							<c:when test="<%= accounts.size() == 1 %>">

								<%
								koroneikiAccount = accounts.get(0);

								accountKey = koroneikiAccount.getKey();

								addLicensePermission = accountPermission.contains(permissionChecker, koroneikiAccount, OSBActionKeys.ADD_LICENSE);
								%>

								<span class="semi-bold"><%= HtmlUtil.escape(koroneikiAccount.getName()) %></span>

								<aui:input label="" name="accountKey" type="hidden" value="<%= accountKey %>" />
							</c:when>
							<c:otherwise>

								<%
								String taglibOnChange = renderResponse.getNamespace() + "updateLicenseKey('', '', '');";
								%>

								<aui:select label="" name="accountKey" onChange="<%= taglibOnChange %>">
									<aui:option value="" />

									<%
									for (Account curKoroneikiAccount : accounts) {
									%>

										<aui:option label="<%= curKoroneikiAccount.getName() %>" selected="<%= accountKey.equals(curKoroneikiAccount.getKey()) %>" value="<%= curKoroneikiAccount.getKey() %>" />

									<%
									}
									%>

								</aui:select>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</aui:col>

			<aui:col md="12">
				<h2 class="control-label">
					<liferay-ui:message key="product" />
				</h2>

				<%
				String taglibOnChange = renderResponse.getNamespace() + "updateLicenseKey(this.value, '', '');";
				%>

				<aui:select label="" name="productEntryId" onChange="<%= taglibOnChange %>">
					<aui:option value="" />

					<c:if test="<%= koroneikiAccount != null %>">

						<%
						List<ProductEntry> productEntries = ProductEntryLocalServiceUtil.getProductEntries(true);

						List<String> koroneikiProductKeys = ListUtil.toList(productEntries, ProductEntry.KORONEIKI_PRODUCT_KEY_ACCESSOR);

						String filter = "accountKey eq '" + koroneikiAccount.getKey() + "' and productKey eq '" + StringUtil.merge(koroneikiProductKeys, "' and productKey eq '") + "'";

						List<ProductPurchaseView> productPurchaseViews = productPurchaseViewWebService.getProductPurchaseViews(StringPool.BLANK, filter, 1, 1000, StringPool.BLANK);

						List<ProductEntry> availableProductEntries = new ArrayList<ProductEntry>();
						List<ProductEntry> otherProductEntries = new ArrayList<ProductEntry>();

						for (ProductEntry curProductEntry : productEntries) {
							String koroneikiProductKey = curProductEntry.getKoroneikiProductKey();
							boolean available = false;

							for (ProductPurchaseView productPurchaseView : productPurchaseViews) {
								Product product = productPurchaseView.getProduct();

								if (koroneikiProductKey.equals(product.getKey())) {
									int availableKeys = 0;

									ProductPurchase[] productPurchases = productPurchaseView.getProductPurchases();

									if (productPurchases != null) {
										for (ProductPurchase productPurchase : productPurchases) {
											availableKeys += productPurchase.getQuantity();
										}
									}

									ProductConsumption[] productConsumptions = productPurchaseView.getProductConsumptions();

									if (productConsumptions != null) {
										availableKeys = availableKeys - productConsumptions.length;
									}

									if (availableKeys > 0) {
										available = true;

										break;
									}
								}
							}

							if (available) {
								availableProductEntries.add(curProductEntry);
							}
							else {
								otherProductEntries.add(curProductEntry);
							}
						}
						%>

						<optgroup label="<liferay-ui:message key="purchased" />">

						<c:if test="<%= !availableProductEntries.isEmpty() %>">

							<%
							for (ProductEntry curProductEntry : availableProductEntries) {
							%>

								<aui:option label="<%= curProductEntry.getName() %>" selected="<%= curProductEntry.getProductEntryId() == productEntryId %>" value="<%= curProductEntry.getProductEntryId() %>" />

							<%
							}
							%>

						</c:if>

						<c:if test="<%= !otherProductEntries.isEmpty() %>">
							<optgroup label="<liferay-ui:message key="other" />">

							<%
							for (ProductEntry curProductEntry : otherProductEntries) {
							%>

								<aui:option label="<%= curProductEntry.getName() %>" selected="<%= curProductEntry.getProductEntryId() == productEntryId %>" value="<%= curProductEntry.getProductEntryId() %>" />

							<%
							}
							%>

						</c:if>
					</c:if>
				</aui:select>
			</aui:col>

			<aui:col md="6">
				<h2 class="control-label">
					<liferay-ui:message key="choose-liferay-version" />
				</h2>

				<%
				String taglibOnChange = renderResponse.getNamespace() + "updateLicenseKey(document." + renderResponse.getNamespace() + "fm." + renderResponse.getNamespace() + "productEntryId.value, this.value, '');";
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
				<h2 class="control-label">
					<liferay-ui:message key="type" />
				</h2>

				<%
				String taglibOnChange = renderResponse.getNamespace() + "updateLicenseKey(document." + renderResponse.getNamespace() + "fm." + renderResponse.getNamespace() + "productEntryId.value, document." + renderResponse.getNamespace() + "fm." + renderResponse.getNamespace() + "productVersion.value, this.value);";
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
				<h2 class="control-label">
					<liferay-ui:message key="choose-license" />
				</h2>

				<%
				List<ProductPurchaseDisplay> productPurchaseDisplays = new ArrayList<ProductPurchaseDisplay>();

				if (Validator.isNotNull(accountKey) && (productEntry != null) && (productVersion > 0) && (licenseEntry != null)) {
					ProductPurchaseView productPurchaseView = productPurchaseViewWebService.getProductPurchaseView(accountKey, productEntry.getKoroneikiProductKey());

					Map<String, List<ProductConsumption>> productConsumptionsMap = new HashMap<String, List<ProductConsumption>>();

					if (productPurchaseView.getProductConsumptions() != null) {
						for (ProductConsumption productConsumption : productPurchaseView.getProductConsumptions()) {
							List<ProductConsumption> curProductConsumptions = productConsumptionsMap.get(productConsumption.getProductPurchaseKey());

							if (curProductConsumptions == null) {
								curProductConsumptions = new ArrayList<ProductConsumption>();

								productConsumptionsMap.put(productConsumption.getProductPurchaseKey(), curProductConsumptions);
							}

							curProductConsumptions.add(productConsumption);
						}
					}

					if (productPurchaseView.getProductPurchases() != null) {
						for (ProductPurchase productPurchase : productPurchaseView.getProductPurchases()) {
							productPurchaseDisplays.add(new ProductPurchaseDisplay(request, productPurchase, productConsumptionsMap.get(productPurchase.getKey())));
						}
					}
				}
				%>

				<liferay-ui:search-container
					headerNames="start-date,lifetime,instance-size,license-keys-available"
					total="<%= productPurchaseDisplays.size() %>"
				>
					<liferay-ui:search-container-results
						results="<%= productPurchaseDisplays %>"
					/>

					<liferay-ui:search-container-row
						className="com.liferay.osb.customer.license.web.internal.display.context.ProductPurchaseDisplay"
						modelVar="productPurchaseDisplay"
					>

						<%
						String licenseEntryType = licenseEntry.getType();

						Calendar startDateCal = Calendar.getInstance(timeZone, locale);

						if (!licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER) && !licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER)) {
							startDateCal.setTime(productPurchaseDisplay.getStartDate());
						}

						Calendar expirationDateCal = Calendar.getInstance(timeZone, locale);

						if (!licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER) && !licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER)) {
							expirationDateCal.setTime(productPurchaseDisplay.getEndDate());
						}

						String rowHREF = null;

						if (addLicensePermission && productPurchaseDisplay.hasAvailableLicenseKeys()) {
							StringBundler sb = new StringBundler(5);

							sb.append("javascript:");
							sb.append(renderResponse.getNamespace());
							sb.append("selectProductPurchase('");
							sb.append(productPurchaseDisplay.getKey());
							sb.append("');");

							rowHREF = sb.toString();
						}
						%>

						<liferay-ui:search-container-column-text
							name="start-date"
						>
							<c:choose>
								<c:when test="<%= hasUpdateAdmin %>">

									<%
									Calendar calendar = CalendarFactoryUtil.getCalendar(2010, 1, 1);

									Date firstEnabledDate = calendar.getTime();

									calendar = CalendarFactoryUtil.getCalendar(2050, 1, 1);

									Date lastEnabledDate = calendar.getTime();
									%>

									<liferay-ui:input-date
										dayParam='<%= productPurchaseDisplay.getKey() + "startDateDay" %>'
										dayValue="<%= startDateCal.get(Calendar.DAY_OF_MONTH) %>"
										firstDayOfWeek="<%= startDateCal.getFirstDayOfWeek() %>"
										firstEnabledDate="<%= firstEnabledDate %>"
										formName="fm"
										lastEnabledDate="<%= lastEnabledDate %>"
										monthParam='<%= productPurchaseDisplay.getKey() + "startDateMonth" %>'
										monthValue="<%= startDateCal.get(Calendar.MONTH) %>"
										yearParam='<%= productPurchaseDisplay.getKey() + "startDateYear" %>'
										yearValue="<%= startDateCal.get(Calendar.YEAR) %>"
									/>
								</c:when>
								<c:otherwise>
									<c:if test="<%= rowHREF != null %>">
										<a href="<%= rowHREF %>">
									</c:if>

									<%= longDateFormatDate.format(startDateCal.getTime()) %>

									<c:if test="<%= rowHREF != null %>">
										</a>
									</c:if>
								</c:otherwise>
							</c:choose>
						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-text
							name="expiration-date"
						>
							<c:choose>
								<c:when test="<%= hasUpdateAdmin %>">

									<%
									Calendar calendar = CalendarFactoryUtil.getCalendar(2010, 1, 1);

									Date firstEnabledDate = calendar.getTime();

									calendar = CalendarFactoryUtil.getCalendar(2050, 1, 1);

									Date lastEnabledDate = calendar.getTime();
									%>

									<liferay-ui:input-date
										dayParam='<%= productPurchaseDisplay.getKey() + "expirationDateDay" %>'
										dayValue="<%= expirationDateCal.get(Calendar.DAY_OF_MONTH) %>"
										firstDayOfWeek="<%= expirationDateCal.getFirstDayOfWeek() %>"
										firstEnabledDate="<%= firstEnabledDate %>"
										formName="fm"
										lastEnabledDate="<%= lastEnabledDate %>"
										monthParam='<%= productPurchaseDisplay.getKey() + "expirationDateMonth" %>'
										monthValue="<%= expirationDateCal.get(Calendar.MONTH) %>"
										yearParam='<%= productPurchaseDisplay.getKey() + "expirationDateYear" %>'
										yearValue="<%= expirationDateCal.get(Calendar.YEAR) %>"
									/>
								</c:when>
								<c:otherwise>
									<c:if test="<%= rowHREF != null %>">
										<a href="<%= rowHREF %>">
									</c:if>

									<%= longDateFormatDate.format(expirationDateCal.getTime()) %>

									<c:if test="<%= rowHREF != null %>">
										</a>
									</c:if>
								</c:otherwise>
							</c:choose>
						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-text
							href="<%= rowHREF %>"
							name="instance-size"
						>
							<%= productPurchaseDisplay.getSizing() %>

							<aui:input name='<%= productPurchaseDisplay.getKey() + "sizing" %>' type="hidden" value="<%= productPurchaseDisplay.getSizing() %>" />
						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-text
							href="<%= rowHREF %>"
							name="license-keys-available"
						>
							<c:choose>
								<c:when test="<%= !productPurchaseDisplay.isApproved() %>">
									<liferay-ui:icon
										image="inactive"
										label="<%= true %>"
										message="inactive"
									/>
								</c:when>
								<c:otherwise>
									<%= productPurchaseDisplay.getLicenseKeysAvailable() %>
								</c:otherwise>
							</c:choose>
						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-text
							href="<%= rowHREF %>"
						>
							<c:if test="<%= addLicensePermission && productPurchaseDisplay.hasAvailableLicenseKeys() %>">
								<aui:button onClick="<%= rowHREF %>" value="choose" />
							</c:if>
						</liferay-ui:search-container-column-text>
					</liferay-ui:search-container-row>

					<liferay-ui:search-iterator
						paginate="<%= false %>"
					/>
				</liferay-ui:search-container>

				<%-- detached here --%>
			</aui:col>
		</aui:form>
	</aui:row>
</div>

<aui:script>
	function <portlet:namespace />selectAccount(accountKey, accountName) {
		document.<portlet:namespace />fm.<portlet:namespace />accountKey.value = accountKey;

		document.getElementById('<portlet:namespace />accountName').innerHTML = accountName;

		<portlet:namespace />updateLicenseKey('', '', '');
	}

	function <portlet:namespace />selectProductPurchase(productPurchaseKey) {
		document.<portlet:namespace />fm.<portlet:namespace />productPurchaseKey.value = productPurchaseKey;

		submitForm(document.<portlet:namespace />fm, '<portlet:renderURL><portlet:param name="mvcPath" value="/license/edit_license_key_details.jsp" /></portlet:renderURL>');
	}

	function <portlet:namespace />updateLicenseKey(productEntryId, productVersion, licenseEntryId) {
		var A = AUI();

		A.one('#<portlet:namespace />productEntryId').val(productEntryId);
		A.one('#<portlet:namespace />productVersion').val(productVersion);
		A.one('#<portlet:namespace />licenseEntryId').val(licenseEntryId);

		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>