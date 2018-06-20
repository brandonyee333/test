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

Calendar firstEnabledCalendar = CalendarFactoryUtil.getCalendar(TimeZoneUtil.getTimeZone(StringPool.UTC), locale);

firstEnabledCalendar.add(Calendar.YEAR, -1);

Date firstEnabledDate = firstEnabledCalendar.getTime();

Calendar lastEnabledCalendar = CalendarFactoryUtil.getCalendar(TimeZoneUtil.getTimeZone(StringPool.UTC), locale);

lastEnabledCalendar.add(Calendar.YEAR, 3);

Date lastEnabledDate = lastEnabledCalendar.getTime();

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
		document.getElementById(showId).style.display = '';
		document.getElementById(hideId).style.display = 'none';
	}
</script>

<portlet:actionURL name="updateLicenseKeySet" var="updateLicenseKeySetURL">
	<portlet:param name="mvcPath" value="/license/edit_license_key_set.jsp" />
</portlet:actionURL>

<div class="container-fluid-1280">
	<aui:row>
		<aui:form action="<%= updateLicenseKeySetURL %>" cssClass="col-md-12" method="post" onSubmit="submitForm(this); return false;">
			<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
			<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
			<aui:input name="licenseKeySetId" type="hidden" value="<%= licenseKeySetId %>" />
			<aui:input name="licenseKeyId" type="hidden" />
			<aui:input name="offeringEntryId" type="hidden" value="<%= offeringEntryId %>" />
			<aui:input name="clusterId" type="hidden" value="<%= clusterId %>" />

			<div class="pull-right">
				<aui:button onClick="<%= backURL %>" value="back-to-previous-page" />
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
			boolean canRenewAggregate = true;

			List<LicenseKey> activeLicenseKeys = new ArrayList<LicenseKey>();
			List<LicenseKey> inactiveLicenseKeys = new ArrayList<LicenseKey>();
			List<LicenseKey> displayLicenseKeys = new ArrayList<LicenseKey>();

			while (!licenseKeys.isEmpty()) {
				LicenseKey licenseKey = licenseKeys.remove(0);

				if (!licenseKey.isActive()) {
					inactiveLicenseKeys.add(licenseKey);
				}
				else {
					activeLicenseKeys.add(licenseKey);
				}
			}

			displayLicenseKeys = activeLicenseKeys;
			%>

			<%@ include file="/license/view_license_key_set.jspf" %>

			<%
			canRenewAggregate = true;

			displayLicenseKeys = inactiveLicenseKeys;
			%>

			<c:if test="<%= !inactiveLicenseKeys.isEmpty() %>">
				<liferay-ui:panel
					collapsible="<%= true %>"
					cssClass="row"
					extended="<%= inactiveLicenseKeys.size() <= 3 %>"
					id="inactiveLicenses"
					markupView="lexicon"
					persistState="<%= false %>"
					title="deactivated-licenses"
				>
					<aui:col md="12">
						<%@ include file="/license/view_license_key_set.jspf" %>
					</aui:col>
				</liferay-ui:panel>
			</c:if>

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
						<div class="license-duration pull-right">
							<liferay-ui:message key="start-date" />:

							<liferay-ui:input-date
								dayParam="aggregateStartDay"
								dayValue="<%= calendar.get(Calendar.DAY_OF_MONTH) %>"
								firstEnabledDate="<%= firstEnabledDate %>"
								lastEnabledDate="<%= lastEnabledDate %>"
								monthParam="aggregateStartMonth"
								monthValue="<%= calendar.get(Calendar.MONTH) %>"
								name="startDate"
								yearParam="aggregateStartYear"
								yearValue="<%= calendar.get(Calendar.YEAR) %>"
							/>

							<liferay-ui:message key="duration" />:

							<aui:select inlineField="<%= true %>" label="" name="aggregateRenewTime">
								<aui:option label="2-weeks" value="14" />
								<aui:option label="30-days" value="30" />
								<aui:option label="60-days" value="60" />
								<aui:option label="1-year" value="365" />
							</aui:select>

							<aui:button onClick='<%= renderResponse.getNamespace() + "renewLicenseKey();" %>' value="renew-aggregate" />
						</div>
					</c:if>
				</div>
			</c:if>

			<div>
				<c:if test="<%= OSBAccountEntryPermission.contains(permissionChecker, licenseKeySet.getAccountEntryId(), OSBActionKeys.ADD_LICENSE) %>">
					<portlet:renderURL var="addLicenseKeyURL">
						<portlet:param name="mvcPath" value="/license/edit_license_key.jsp" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="licenseKeySetId" value="<%= String.valueOf(licenseKeySetId) %>" />
					</portlet:renderURL>

					<aui:button onClick="<%= addLicenseKeyURL %>" value="add-new-license-key" />
				</c:if>

				<aui:button onClick="<%= backURL %>" value="cancel" />
			</div>
		</aui:form>
	</aui:row>
</div>

<aui:script>
	function <portlet:namespace/>renewLicenseKey(licenseKeyId) {
		document.<portlet:namespace />fm.<portlet:namespace />licenseKeyId.value = licenseKeyId;
		document.<portlet:namespace />fm.<portlet:namespace />redirect.value = '<%= currentURL %>';

		submitForm(document.<portlet:namespace />fm, '<portlet:actionURL name="renewLicenseKey"><portlet:param name="mvcPath" value="/license/edit_license_key_set.jsp" /></portlet:actionURL>');
	}
</aui:script>