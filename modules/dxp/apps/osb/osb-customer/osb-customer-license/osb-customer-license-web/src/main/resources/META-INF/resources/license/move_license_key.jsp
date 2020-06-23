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

ProductPurchase productPurchase = productPurchaseWebService.getProductPurchase(licenseKey.getKoroneikiProductPurchaseKey());

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
				<liferay-ui:message key="choose-license" />
			</h1>

			<%
			List<ProductPurchaseDisplay> productPurchaseDisplays = new ArrayList<ProductPurchaseDisplay>();

			ProductPurchaseView productPurchaseView = productPurchaseViewWebService.getProductPurchaseView(licenseKey.getKoroneikiAccountKey(), productPurchase.getProductKey());

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
				for (ProductPurchase curProductPurchase : productPurchaseView.getProductPurchases()) {
					productPurchaseDisplays.add(new ProductPurchaseDisplay(request, curProductPurchase, productConsumptionsMap.get(curProductPurchase.getKey())));
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
					Calendar startDateCal = Calendar.getInstance(timeZone, locale);

					startDateCal.setTime(productPurchaseDisplay.getStartDate());

					Calendar expirationDateCal = Calendar.getInstance(timeZone, locale);

					expirationDateCal.setTime(productPurchaseDisplay.getEndDate());

					String rowHREF = null;

					String productPurchaseKey = productPurchaseDisplay.getKey();

					if (!productPurchaseKey.equals(licenseKey.getKoroneikiProductPurchaseKey())) {
						StringBuilder sb = new StringBuilder();

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
						name="product"
						value="<%= productPurchaseDisplay.getProductName() %>"
					/>

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
				/>
			</liferay-ui:search-container>
		</aui:form>
	</aui:row>
</div>