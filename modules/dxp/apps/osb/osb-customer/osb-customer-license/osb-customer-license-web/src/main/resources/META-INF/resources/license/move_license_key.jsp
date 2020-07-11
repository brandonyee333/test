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

long licenseKeyId = ParamUtil.getLong(request, "licenseKeyId");

LicenseKey licenseKey = LicenseKeyServiceUtil.getLicenseKey(licenseKeyId);

ProductEntry productEntry = ProductEntryLocalServiceUtil.getProductEntry(licenseKey.getProductEntryId());

ProductPurchase productPurchase = null;

if (Validator.isNotNull(licenseKey.getKoroneikiProductPurchaseKey())) {
	productPurchase = productPurchaseWebService.getProductPurchase(licenseKey.getKoroneikiProductPurchaseKey());
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/license/move_license_key.jsp");
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("licenseKeyId", String.valueOf(licenseKeyId));
%>

<script type="text/javascript">
	function <portlet:namespace />moveLicenseKey(koroneikiProductPurchaseKey) {
		document.<portlet:namespace />fm.<portlet:namespace />koroneikiProductPurchaseKey.value = koroneikiProductPurchaseKey;
		submitForm(document.<portlet:namespace />fm);
	}

</script>

<portlet:actionURL name="updateLicenseKey" var="updateLicenseKeyURL">
	<portlet:param name="mvcPath" value="/license/move_license_key.jsp" />
</portlet:actionURL>

<div class="container-fluid-1280">
	<aui:row>
		<aui:form action="<%= updateLicenseKeyURL %>" cssClass="col-md-12" method="post">
			<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
			<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
			<aui:input name="licenseKeyId" type="hidden" value="<%= String.valueOf(licenseKey.getLicenseKeyId()) %>" />
			<aui:input name="licenseKeySetId" type="hidden" value="<%= String.valueOf(licenseKey.getLicenseKeySetId()) %>" />
			<aui:input name="complimentary" type="hidden" value="<%= String.valueOf(licenseKey.isComplimentary()) %>" />
			<aui:input name="active" type="hidden" value="<%= String.valueOf(licenseKey.getActive()) %>" />
			<aui:input name="koroneikiProductPurchaseKey" type="hidden" value="" />

			<div class="clearfix section">
				<div class="pull-right">
					<aui:button onClick="<%= backURL %>" value="back-to-previous-page" />
				</div>
			</div>

			Move License: <%= HtmlUtil.escape(licenseKey.getDescription()) %>

			<h1 class="section-heading">
				<liferay-ui:message key="choose-purchase" />
			</h1>

			<%
			List<Object> productPurchaseDisplays = new ArrayList<Object>();

			ProductPurchaseView productPurchaseView = productPurchaseViewWebService.fetchProductPurchaseView(licenseKey.getKoroneikiAccountKey(), productEntry.getKoroneikiProductKey());

			Map<String, List<ProductConsumption>> productConsumptionsMap = new HashMap<String, List<ProductConsumption>>();

			if (productPurchaseView != null) {
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
					for (ProductPurchase curProductPurchase : productPurchaseView.getProductPurchases()) {
						productPurchaseDisplays.add(new ProductPurchaseDisplay(request, curProductPurchase, productConsumptionsMap.get(curProductPurchase.getKey())));
					}
				}
			}

			productPurchaseDisplays.add((Object)null);
			%>

			<liferay-ui:search-container
				headerNames="start-date,expiration-date,instance-size,license-keys-generated,,"
				total="<%= productPurchaseDisplays.size() %>"
			>
				<liferay-ui:search-container-results
					results="<%= productPurchaseDisplays %>"
				/>

				<liferay-ui:search-container-row
					className="java.lang.Object"
					modelVar="resultRow"
				>

					<%
					ProductPurchaseDisplay productPurchaseDisplay = null;

					if (resultRow instanceof ProductPurchaseDisplay) {
						productPurchaseDisplay = (ProductPurchaseDisplay)resultRow;
					}

					String productPurchaseKey = StringPool.BLANK;

					if (productPurchaseDisplay != null) {
						productPurchaseKey = productPurchaseDisplay.getKey();
					}

					Calendar startDateCal = Calendar.getInstance(timeZone, locale);

					if (productPurchaseDisplay != null) {
						startDateCal.setTime(productPurchaseDisplay.getStartDate());
					}
					else {
						startDateCal.setTime(licenseKey.getStartDate());
					}

					Calendar expirationDateCal = Calendar.getInstance(timeZone, locale);

					if (productPurchaseDisplay != null) {
						expirationDateCal.setTime(productPurchaseDisplay.getEndDate());
					}
					else {
						startDateCal.setTime(licenseKey.getExpirationDate());
					}

					String rowHREF = null;

					if (!productPurchaseKey.equals(licenseKey.getKoroneikiProductPurchaseKey())) {
						StringBundler sb = new StringBundler(5);

						sb.append("javascript:");
						sb.append(renderResponse.getNamespace());
						sb.append("moveLicenseKey('");
						sb.append(productPurchaseKey);
						sb.append("');");

						rowHREF = sb.toString();
					}
					%>

					<liferay-ui:search-container-column-text
						href="<%= rowHREF %>"
						name="start-date"
						value="<%= longDateFormatDate.format(startDateCal.getTime()) %>"
					/>

					<liferay-ui:search-container-column-text
						href="<%= rowHREF %>"
						name="expiration-date"
						value="<%= longDateFormatDate.format(expirationDateCal.getTime()) %>"
					/>

					<liferay-ui:search-container-column-text
						name="instance-size"
					>
						<c:choose>
							<c:when test="<%= productPurchaseDisplay != null %>">
								<%= productPurchaseDisplay.getSizing() %>
							</c:when>
							<c:otherwise>
								<%= licenseKey.getSizing() %>
							</c:otherwise>
						</c:choose>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						name="license-keys-generated"
					>
						<c:choose>
							<c:when test="<%= productPurchaseDisplay != null %>">
								<%= productPurchaseDisplay.getLicenseKeysGenerated() %>
							</c:when>
							<c:otherwise>
								<%= productConsumptionWebService.searchCount("accountKey eq '" + licenseKey.getKoroneikiAccountKey() + "' and productKey eq '" + productEntry.getKoroneikiProductKey() + "' and productPurchaseKey eq null") %>
							</c:otherwise>
						</c:choose>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text>
						<c:choose>
							<c:when test="<%= productPurchaseKey.equals(licenseKey.getKoroneikiProductPurchaseKey()) %>">
								<liferay-ui:icon
									image="checked"
									label="<%= true %>"
									message="current"
								/>
							</c:when>
							<c:otherwise>
								<aui:button onClick="<%= rowHREF %>" value="choose" />
							</c:otherwise>
						</c:choose>
					</liferay-ui:search-container-column-text>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator
					markupView="lexicon"
					paginate="<%= false %>"
					resultRowSplitter="<%= new ProductPurchaseResultRowSplitter() %>"
				/>
			</liferay-ui:search-container>
		</aui:form>
	</aui:row>
</div>