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

<%
String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);

long licenseKeyId = ParamUtil.getLong(request, "licenseKeyId");

LicenseKey licenseKey = LicenseKeyLocalServiceUtil.getLicenseKey(licenseKeyId);

LicenseKeySet licenseKeySet = licenseKey.getLicenseKeySet();

String licenseEntryType = licenseKey.getLicenseEntryType();
String productEntryName = licenseKey.getProductEntryName();
%>

<portlet:actionURL name="updateLicenseKey" var="updateLicenseKeyURL">
	<portlet:param name="mvcPath" value="/license/split_license_key_set.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateLicenseKeyURL %>" class="uni-form" method="post">
	<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>" />
	<input name="<portlet:namespace />backURL" type="hidden" value="<%= HtmlUtil.escape(backURL) %>" />
	<input name="<portlet:namespace />licenseKeyId" type="hidden" value="<%= licenseKeyId %>" />
	<input name="<portlet:namespace />offeringEntryId" type="hidden" value="<%= licenseKey.getOfferingEntryId() %>" />
	<input name="<portlet:namespace />active" type="hidden" value="<%= licenseKey.getActive() %>" />

	<div class="clearfix section">
		<div class="pull-right">
			<a class="btn" href="<%= HtmlUtil.escapeAttribute(backURL) %>">&lt; <liferay-ui:message key="back-to-previous-page" /></a>
		</div>
	</div>

	<liferay-ui:error exception="<%= LicenseKeySetNameException.class %>" message="please-enter-a-valid-name" />

	<h1 class="section-heading">
		<liferay-ui:message key="enter-a-new-name-for-this-license" />
	</h1>

	<div class="callout-a">
		<div class="callout-content">
			<liferay-ui:input-field
				bean="<%= licenseKeySet %>"
				field="name"
				model="<%= LicenseKeySet.class %>"
			/>
		</div>
	</div>

	<div class="callout-a">
		<div class="callout-content clearfix">
			<div class="content-column w33">
				<div class="content-column-content left-column">
					<span class="txt-b txt-up"><liferay-ui:message key="owner" />:</span>

					<%= HtmlUtil.escape(licenseKey.getOwner()) %>
				</div>
			</div>

			<div class="content-column w66">
				<div class="content-column-content right-column">
					<span class="txt-b txt-up"><liferay-ui:message key="description" />:</span>

					<%= HtmlUtil.escape(licenseKey.getDescription()) %>
				</div>
			</div>
		</div>

		<div class="callout-content clearfix">
			<div class="content-column w33">
				<div class="content-column-content left-column">
					<span class="txt-b txt-up"><liferay-ui:message key="product" />:</span>

					<%= productEntryName %>

					<br />

					<span class="txt-b txt-up"><liferay-ui:message key="start-date" />:</span>

					<c:choose>
						<c:when test="<%= licenseEntryType.equals(LicenseEntryConstants.TYPE_TRIAL) %>">
							<liferay-ui:message key="registration" />
						</c:when>
						<c:otherwise>
							<%= longDateFormatDate.format(licenseKey.getStartDate()) %>
						</c:otherwise>
					</c:choose>
				</div>
			</div>

			<div class="content-column w33">
				<div class="content-column-content middle-column">
					<span class="txt-b txt-up"><liferay-ui:message key="type" />:</span>

					<%= LanguageUtil.get(request, licenseEntryType) %>

					<br />

					<c:choose>
						<c:when test="<%= licenseEntryType.equals(LicenseEntryConstants.TYPE_TRIAL) %>">
							<span class="txt-b txt-up"><liferay-ui:message key="lifetime" />:</span>

							<%
							Date startDate = licenseKey.getStartDate();
							Date expirationDate = licenseKey.getExpirationDate();
							%>

							<%= (expirationDate.getTime() - startDate.getTime()) / Time.DAY %> <liferay-ui:message key="days" />
						</c:when>
						<c:otherwise>
							<span class="txt-b txt-up"><liferay-ui:message key="expiration-date" />:</span>

							<%= longDateFormatDate.format(licenseKey.getExpirationDate()) %>
						</c:otherwise>
					</c:choose>
				</div>
			</div>

			<div class="content-column w33">
				<div class="content-column-content right-column">
					<span class="txt-b txt-up"><liferay-ui:message key="version" />:</span>

					<%= licenseKey.getProductVersionLabel() %>

					<br />

					<span class="txt-b txt-up"><liferay-ui:message key="status" />:</span>

					<c:choose>
						<c:when test="<%= licenseKey.isExpired() %>">
							<liferay-ui:icon
								image="close"
								label="<%= true %>"
								message="expired"
							/>
						</c:when>
						<c:when test="<%= licenseKey.isActive() %>">
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
		</div>

		<br />

		<div class="callout-content">
			<c:choose>
				<c:when test="<%= licenseKey.getLicenseVersion() == 2 %>">
					<c:choose>
						<c:when test="<%= licenseEntryType.equals(LicenseEntryConstants.TYPE_CLUSTER) || licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER) %>">
							<div>
								<span class="txt-b txt-up"><liferay-ui:message key="maximum-servers" />:</span>

								<%= licenseKey.getMaxServers() %>
							</div>

							<br />
						</c:when>
						<c:when test="<%= licenseEntryType.equals(LicenseEntryConstants.TYPE_PRODUCTION) %>">
							<table class="lfr-table">
								<tr>
									<td>
										<span class="txt-b txt-up"><liferay-ui:message key="mac-addresses" />:</span>
									</td>
									<td>

										<%
										List<LicenseKey> clusterLicenseKeys = LicenseKeyLocalServiceUtil.getOfferingEntryLicenseKeys(licenseKey.getOfferingEntryId(), licenseKey.getClusterId());

										for (int i = 0; i < clusterLicenseKeys.size(); i++) {
											LicenseKey clusterLicenseKey = clusterLicenseKeys.get(i);
										%>

											<%= clusterLicenseKey.getServerId() %><%= (i + 1) < clusterLicenseKeys.size() ? "<br />" : "" %>

										<%
										}
										%>

									</td>
								</tr>
							</table>
						</c:when>
					</c:choose>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="<%= licenseEntryType.equals(LicenseEntryConstants.TYPE_CLUSTER) || licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER) %>">
							<table class="lfr-table">
								<tr>
									<td>
										<span class="txt-b txt-up"><liferay-ui:message key="mac-addresses" />:</span>
									</td>
									<td>

										<%
										List<LicenseKey> clusterLicenseKeys = LicenseKeyLocalServiceUtil.getOfferingEntryLicenseKeys(licenseKey.getOfferingEntryId(), licenseKey.getClusterId());

										for (int i = 0; i < clusterLicenseKeys.size(); i++) {
											LicenseKey clusterLicenseKey = clusterLicenseKeys.get(i);
										%>

											<%= clusterLicenseKey.getServerId() %><%= (i + 1) < clusterLicenseKeys.size() ? "<br />" : "" %>

										<%
										}
										%>

									</td>
								</tr>
							</table>
						</c:when>
						<c:when test="<%= licenseEntryType.equals(LicenseEntryConstants.TYPE_PRODUCTION) %>">
							<div>
								<span class="txt-b txt-up"><liferay-ui:message key="server-id" />:</span>

								<%= licenseKey.getServerId() %>
							</div>

							<br />
						</c:when>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<div>
		<input class="aui-button-input" type="submit" value="<liferay-ui:message key="save" />" />

		<a class="btn btn-default" href="<%= HtmlUtil.escape(backURL) %>"><liferay-ui:message key="cancel" /></a>
	</div>
</aui:form>