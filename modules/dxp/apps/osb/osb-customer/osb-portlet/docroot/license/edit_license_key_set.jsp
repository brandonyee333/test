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

if (Validator.isNull(backURL)) {
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("mvcPath", "/license/view.jsp");

	backURL = portletURL.toString();
}

long licenseKeySetId = ParamUtil.getLong(request, "licenseKeySetId");

LicenseKeySet licenseKeySet = LicenseKeySetServiceUtil.getLicenseKeySet(licenseKeySetId);

List<LicenseKey> licenseKeys = LicenseKeyServiceUtil.getLicenseKeySetLicenseKeys(licenseKeySetId);

long offeringEntryId = ParamUtil.getLong(request, "offeringEntryId");
long clusterId = ParamUtil.getLong(request, "clusterId");

Calendar calendar = CalendarFactoryUtil.getCalendar(TimeZoneUtil.getTimeZone(StringPool.UTC), locale);

calendar.add(Calendar.YEAR, -1);

Date firstEnabledDate = calendar.getTime();

calendar = CalendarFactoryUtil.getCalendar(TimeZoneUtil.getTimeZone(StringPool.UTC), locale);

calendar.add(Calendar.YEAR, 3);

Date lastEnabledDate = calendar.getTime();

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/license/edit_license_key_set.jsp");
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("licenseKeySetId", String.valueOf(licenseKeySetId));
portletURL.setParameter("offeringEntryId", String.valueOf(offeringEntryId));
portletURL.setParameter("clusterId", String.valueOf(clusterId));
%>

<script type="text/javascript">
	function <portlet:namespace />quickSave() {
		document.<portlet:namespace />fm.<portlet:namespace />redirect.value = '<%= portletURL.toString() %>';
		submitForm(document.<portlet:namespace />fm);
	}

	function <portlet:namespace />toggleForm(hideId, showId) {
		document.getElementById(showId).style.display = "";
		document.getElementById(hideId).style.display = "none";
	}
</script>

<portlet:actionURL name="updateLicenseKeySet" var="updateLicenseKeySetURL">
	<portlet:param name="mvcPath" value="/license/edit_license_key_set.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateLicenseKeySetURL %>" class="uni-form" method="post" onSubmit="submitForm(this); return false;">
	<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>" />
	<input name="<portlet:namespace />backURL" type="hidden" value="<%= HtmlUtil.escape(backURL) %>" />
	<input name="<portlet:namespace />licenseKeySetId" type="hidden" value="<%= licenseKeySetId %>" />
	<input name="<portlet:namespace />licenseKeyId" type="hidden" />
	<input name="<portlet:namespace />offeringEntryId" type="hidden" value="<%= offeringEntryId %>" />
	<input name="<portlet:namespace />clusterId" type="hidden" value="<%= clusterId %>" />

	<div class="clearfix section">
		<div class="pull-right">
			<a class="btn" href="<%= HtmlUtil.escapeAttribute(backURL) %>">&lt; <liferay-ui:message key="back-to-previous-page" /></a>
		</div>
	</div>

	<h1 class="section-heading">
		<div id="<portlet:namespace />nameDisplay">
			<%= HtmlUtil.escape(licenseKeySet.getName()) %>

			<c:if test="<%= OSBLicenseKeySetPermission.contains(permissionChecker, licenseKeySetId, OSBActionKeys.UPDATE) %>">

				<%
				String taglibEdit = "javascript:" + renderResponse.getNamespace() + "toggleForm('" + renderResponse.getNamespace() + "nameDisplay', '" + renderResponse.getNamespace() + "name');";
				%>

				<liferay-ui:icon
					image="edit"
					url="<%= taglibEdit %>"
				/>
			</c:if>
		</div>

		<div id="<portlet:namespace />name" style="display: none;">
			<liferay-ui:input-field
				bean="<%= licenseKeySet %>"
				field="name"
				model="<%= LicenseKeySet.class %>"
			/>

			<%
			String saveOnClick = "javascript:" + renderResponse.getNamespace() + "quickSave();";
			%>

			<liferay-ui:icon
				image="reply"
				message="save"
				url="<%= saveOnClick %>"
			/>
		</div>
	</h1>

	<%
	boolean canSplit = false;
	boolean canRenewAggregate = true;

	int inactiveLicensesCount = 0;

	while (!licenseKeys.isEmpty()) {
		LicenseKey licenseKey = licenseKeys.remove(0);

		String licenseEntryType = licenseKey.getLicenseEntryType();
		String productEntryName = licenseKey.getProductEntryName();
		int productVersion = licenseKey.getProductVersion();

		if (licenseKey.isActive() && !licenseKey.canRenew()) {
			canRenewAggregate = false;
		}

		if (!licenseKey.isActive()) {
			inactiveLicensesCount++;
		}

		String cssClass = StringPool.BLANK;

		if ((licenseKey.getOfferingEntryId() == offeringEntryId) && (licenseKey.getClusterId() == clusterId)) {
			cssClass = "highlight-cluster";
		}
	%>

		<c:if test="<%= !licenseKey.isActive() && (inactiveLicensesCount == 1) %>">
			<div class="lfr-collapsible lfr-extended lfr-panel" id="<portlet:namespace />inactiveLicenses">
				<div class="lfr-panel-titlebar">
					<div class="lfr-panel-title">
						<span>
							<liferay-ui:message key="deactivated-licenses" />
						</span>
					</div>
				</div>

				<div class="lfr-panel-content">
		</c:if>

		<c:choose>
			<c:when test="<%= licenseKey.getLicenseVersion() >= 3 %>">
				<%@ include file="/license/edit_license_key_set_version_3_4.jspf" %>
			</c:when>
			<c:otherwise>
				<div class="callout-a <%= cssClass %>" id="<portlet:namespace /><%= licenseKey.getOfferingEntryId() %>-<%= licenseKey.getClusterId() %>">
					<c:if test="<%= OSBLicenseKeyPermission.contains(permissionChecker, licenseKey.getLicenseKeyId(), OSBActionKeys.UPDATE_ADMIN) %>">
						<div class="callout-content clearfix">
							<div class="content-column w33">
								<div class="content-column-content left-column">
									<span class="txt-b txt-up"><liferay-ui:message key="created-by" />:</span>

									<%= HtmlUtil.escape(PortalUtil.getUserName(licenseKey.getUserId(), licenseKey.getUserName())) %>
								</div>
							</div>

							<div class="content-column w66">
								<div class="content-column-content right-column">
									<span class="txt-b txt-up"><liferay-ui:message key="last-modified" />:</span>

									<%= HtmlUtil.escape(PortalUtil.getUserName(licenseKey.getModifiedUserId(), licenseKey.getModifiedUserName())) %> <liferay-ui:message key="on" /> <%= longDateFormatDateTime.format(licenseKey.getModifiedDate()) %>
								</div>
							</div>
						</div>
					</c:if>

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

												licenseKeys.removeAll(clusterLicenseKeys);

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

												licenseKeys.removeAll(clusterLicenseKeys);

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

						<c:if test="<%= licenseKey.getLicenseVersion() == 1 %>">
							<div>
								<br />

								<span class="txt-b txt-up"><liferay-ui:message key="key" />:</span>

								<liferay-ui:input-resource
									url="<%= licenseKey.getKey() %>"
								/>
							</div>

							<br />
						</c:if>

						<div>
							<c:if test="<%= licenseKey.isActive() && ((licenseKey.getLicenseVersion() >= 2) || licenseEntryType.equals(LicenseEntryConstants.TYPE_CLUSTER) || licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER)) %>">
								<portlet:resourceURL id="licenseKey" var="downloadLicenseFileURL">
									<portlet:param name="mvcPath" value="/license/edit_license_key_set.jsp" />
									<portlet:param name="redirect" value="<%= currentURL %>" />
									<portlet:param name="licenseKeyId" value="<%= String.valueOf(licenseKey.getLicenseKeyId()) %>" />
								</portlet:resourceURL>

								<liferay-ui:icon
									image="download"
									label="<%= true %>"
									message="download-license-file"
									method="get"
									url="<%= downloadLicenseFileURL.toString() %>"
								/>
							</c:if>

							<c:if test="<%= canSplit && OSBLicenseKeySetPermission.contains(permissionChecker, licenseKeySetId, OSBActionKeys.UPDATE) %>">
								<portlet:renderURL var="splitLicenseKeyURL">
									<portlet:param name="mvcPath" value="/license/split_license_key_set.jsp" />
									<portlet:param name="redirect" value="<%= currentURL %>" />
									<portlet:param name="licenseKeyId" value="<%= String.valueOf(licenseKey.getLicenseKeyId()) %>" />
								</portlet:renderURL>

								<liferay-ui:icon
									image="unlink"
									label="<%= true %>"
									message="split-license"
									url="<%= splitLicenseKeyURL.toString() %>"
								/>
							</c:if>

							<c:if test="<%= OSBLicenseKeyPermission.contains(permissionChecker, licenseKey.getLicenseKeyId(), OSBActionKeys.UPDATE_ADMIN) %>">
								<portlet:renderURL var="moveLicenseKeyURL">
									<portlet:param name="mvcPath" value="/license/move_license_key.jsp" />
									<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
									<portlet:param name="licenseKeyId" value="<%= String.valueOf(licenseKey.getLicenseKeyId()) %>" />
									<portlet:param name="offeringEntryId" value="<%= String.valueOf(licenseKey.getOfferingEntryId()) %>" />
								</portlet:renderURL>

								<liferay-ui:icon
									image="forward"
									label="<%= true %>"
									message="move-license"
									url="<%= moveLicenseKeyURL.toString() %>"
								/>
							</c:if>

							<c:if test="<%= OSBLicenseKeyPermission.contains(permissionChecker, licenseKey.getLicenseKeyId(), OSBActionKeys.UPDATE_ADVANCED) %>">
								<portlet:actionURL name="updateLicenseKey" var="activateLicenseKeyURL">
									<portlet:param name="mvcPath" value="/license/edit_license_key_set.jsp" />
									<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
									<portlet:param name="licenseKeySetId" value="<%= String.valueOf(licenseKeySetId) %>" />
									<portlet:param name="licenseKeyId" value="<%= String.valueOf(licenseKey.getLicenseKeyId()) %>" />
									<portlet:param name="offeringEntryId" value="<%= String.valueOf(licenseKey.getOfferingEntryId()) %>" />
									<portlet:param name="active" value="<%= String.valueOf(!licenseKey.isActive()) %>" />
								</portlet:actionURL>

								<%
								String url = "location.href = '" + HttpUtil.encodeURL(activateLicenseKeyURL.toString()) + "'";

								if (licenseKey.isActive()) {
									url = "javascript:if (confirm('" + UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-deactivate-this-license-key") + "')) { " + url + " } else { self.focus(); }";
								}
								else {
									url = "javascript:if (confirm('" + UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-activate-this-license-key") + "')) { " + url + " } else { self.focus(); }";
								}
								%>

								<liferay-ui:icon
									image='<%= licenseKey.isActive() ? "deactivate" : "activate" %>'
									label="<%= true %>"
									message='<%= licenseKey.isActive() ? "deactivate" : "activate" %>'
									url="<%= url %>"
								/>
							</c:if>

							<c:if test="<%= licenseKey.canRenew() && OSBAccountEntryPermission.contains(permissionChecker, licenseKeySet.getAccountEntryId(), OSBActionKeys.ADD_LICENSE) %>">
								<div class="pull-right">
									<div class="pull-left">
										<liferay-ui:message key="start-date" />:
									</div>

									<liferay-ui:input-date
										dayParam='<%= "startDay_" + licenseKey.getLicenseKeyId() %>'
										dayValue="<%= 0 %>"
										firstEnabledDate="<%= firstEnabledDate %>"
										lastEnabledDate="<%= lastEnabledDate %>"
										monthParam='<%= "startMonth_" + licenseKey.getLicenseKeyId() %>'
										monthValue="<%= -1 %>"
										yearParam='<%= "startYear_" + licenseKey.getLicenseKeyId() %>'
										yearValue="<%= 0 %>"
									/>

									<liferay-ui:message key="duration" />:

									<aui:select inlineField="<%= true %>" name='<%= "renewTime_" + licenseKey.getLicenseKeyId() %>'>
										<aui:option label="2-weeks" value="14" />
										<aui:option label="30-days" value="30" />
										<aui:option label="60-days" value="60" />
										<aui:option label="1-year" value="365" />
									</aui:select>

									<a class="btn" href="javascript:<portlet:namespace />renewLicenseKey(<%= licenseKey.getLicenseKeyId() %>);"><liferay-ui:message key="renew" /></a>
								</div>
							</c:if>
						</div>
					</div>
				</div>
			</c:otherwise>
		</c:choose>

		<c:if test="<%= licenseKeys.isEmpty() && (inactiveLicensesCount > 0) %>">
				</div>
			</div>

			<br />
		</c:if>

	<%
		canSplit = true;
	}
	%>

	<c:if test="<%= LicenseUtil.isAggregate(licenseKeySetId) %>">
		<portlet:resourceURL id="licenseKeySet" var="downloadAggregateLicenseFileURL">
			<portlet:param name="mvcPath" value="/license/edit_license_key_set.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="licenseKeySetId" value="<%= String.valueOf(licenseKeySetId) %>" />
		</portlet:resourceURL>

		<div>
			<liferay-ui:icon
				image="download"
				label="<%= true %>"
				message="download-aggregate-license-file"
				method="get"
				url="<%= downloadAggregateLicenseFileURL.toString() %>"
			/>

			<c:if test="<%= LicenseUtil.isRenewAggregate(licenseKeySetId) && OSBAccountEntryPermission.contains(permissionChecker, licenseKeySet.getAccountEntryId(), OSBActionKeys.ADD_LICENSE) %>">
				<div class="pull-right">
					<div class="pull-left">
						<liferay-ui:message key="start-date" />:
					</div>

					<liferay-ui:input-date
						dayParam="aggregateStartDay"
						dayValue="<%= 0 %>"
						firstEnabledDate="<%= firstEnabledDate %>"
						lastEnabledDate="<%= lastEnabledDate %>"
						monthParam="aggregateStartMonth"
						monthValue="<%= -1 %>"
						yearParam="aggregateStartYear"
						yearValue="<%= 0 %>"
					/>

					<liferay-ui:message key="duration" />:

					<aui:select inlineField="<%= true %>" name="aggregateRenewTime">
						<aui:option label="2-weeks" value="14" />
						<aui:option label="30-days" value="30" />
						<aui:option label="60-days" value="60" />
						<aui:option label="1-year" value="365" />
					</aui:select>

					<a class="btn" href="javascript:<portlet:namespace />renewLicenseKey();"><liferay-ui:message key="renew-aggregate" /></a>
				</div>
			</c:if>
		</div>

		<br />
	</c:if>

	<br />

	<div>
		<c:if test="<%= OSBAccountEntryPermission.contains(permissionChecker, licenseKeySet.getAccountEntryId(), OSBActionKeys.ADD_LICENSE) %>">
			<portlet:renderURL var="addLicenseKeyURL">
				<portlet:param name="mvcPath" value="/license/edit_license_key.jsp" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="licenseKeySetId" value="<%= String.valueOf(licenseKeySetId) %>" />
			</portlet:renderURL>

			<a class="btn btn-default" href="<%= addLicenseKeyURL %>"><liferay-ui:message key="add-new-license-key" /></a>
		</c:if>

		<c:if test="<%= OSBLicenseKeySetPermission.contains(permissionChecker, licenseKeySetId, OSBActionKeys.UPDATE) %>">
			<portlet:renderURL var="mergeLicenseKeySetURL">
				<portlet:param name="mvcPath" value="/license/merge_license_key_set.jsp" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="licenseKeySetId" value="<%= String.valueOf(licenseKeySetId) %>" />
			</portlet:renderURL>

			<a class="btn btn-default" href="<%= mergeLicenseKeySetURL %>">liferay-ui:message key="merge-licenses" /></a>
		</c:if>

		<a class="btn btn-default" href="<%= HtmlUtil.escape(backURL) %>"><liferay-ui:message key="cancel" /></a>
	</div>

	<c:if test="<%= inactiveLicensesCount > 3 %>">
		<aui:script>
			var A = AUI();

			A.one('#<portlet:namespace />inactiveLicenses').addClass('lfr-collapsed');
		</aui:script>
	</c:if>
</aui:form>

<aui:script use="liferay-panel">
	var panel = new Liferay.Panel(
		{
			collapsible: true,
			panel: '#<portlet:namespace />inactiveLicenses',
			persistState: false
		}
	);

	Liferay.Panel.register('<portlet:namespace />inactiveLicenses', panel);
</aui:script>

<aui:script>
	function <portlet:namespace/>renewLicenseKey(licenseKeyId) {
		document.<portlet:namespace />fm.<portlet:namespace />licenseKeyId.value = licenseKeyId;

		submitForm(document.<portlet:namespace />fm, '<portlet:actionURL name="renewLicenseKey"><portlet:param name="mvcPath" value="/license/edit_license_key_set.jsp" /><portlet:param name="redirect" value="<%= portletURL.toString() %>" /></portlet:actionURL>');
	}
</aui:script>