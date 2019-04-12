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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

JournalArticle journalArticle = (JournalArticle)row.getObject();

Fields ddmFields = journalConverter.getDDMFields(journalArticle.getDDMStructure(), journalArticle.getContent());

DDMFormValues ddmFormValues = journalConverter.getDDMFormValues(journalArticle.getDDMStructure(), ddmFields);

Map<String, List<DDMFormFieldValue>> ddmFormFieldValuesMap = ddmFormValues.getDDMFormFieldValuesMap();

String additionalNotes = _getStringValue(ddmFormFieldValuesMap, "additionalNotes", locale);
String alertMessage = _getStringValue(ddmFormFieldValuesMap, "alertMessage", locale);
String requiredAgreement = _getStringValue(ddmFormFieldValuesMap, "requiredAgreement", locale);
%>

<h3 class="section-title">
	<portlet:renderURL var="journalArticleURL">
		<portlet:param name="mvcRenderCommandName" value="/view" />
		<portlet:param name="journalArticleResourcePrimKey" value="<%= String.valueOf(journalArticle.getResourcePrimKey()) %>" />
	</portlet:renderURL>

	<aui:a href="<%= journalArticleURL.toString() %>" label="<%= journalArticle.getTitle(locale) %>" />
</h3>

<%
DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("yyyy-MM-dd");

Date releaseDate = dateFormat.parse(DDMFieldsUtil.getString(ddmFields, "releaseDate"));
%>

<div class="hidden-lg hidden-md">
	<%= mediumDateFormatDate.format(releaseDate) %>
</div>

<div class="section-links">

	<%
	List<DDMFormFieldValue> linkFieldValues = ddmFormFieldValuesMap.get("link");

	for (DDMFormFieldValue linkFieldValue : linkFieldValues) {
	%>

		<a class="link" href="<%= _getStringValue(linkFieldValue.getNestedDDMFormFieldValuesMap(), "linkUrl", locale) %>"><%= _getStringValue(linkFieldValue, locale) %></a>

	<%
	}
	%>

</div>

<c:if test="<%= Validator.isNotNull(alertMessage) %>">
	<aui:alert closeable="<%= false %>" cssClass="section-alert">
		<aui:row>
			<aui:col cssClass="alert-col-header" lg="2">
				<svg class="lexicon-icon lexicon-icon-info-circle">
					<use xlink:href="#info-circle" />
				</svg>

				<span class="alert-header"><liferay-ui:message key="info" />:</span>
			</aui:col>

			<aui:col cssClass="alert-col-message" lg="10">
				<span class="alert-message"><%= alertMessage %></span>
			</aui:col>
		</aui:row>
	</aui:alert>
</c:if>

<div class="additional-notes">
	<%= additionalNotes %>
</div>

<div class="downloads" id="<portlet:namespace />downloads<%= journalArticle.getResourcePrimKey() %>"></div>

<%
JSONArray downloadGroupsJSONArray = JSONFactoryUtil.createJSONArray();

List<DDMFormFieldValue> downloadGroupFieldValues = ddmFormFieldValuesMap.get("downloadGroup");

for (DDMFormFieldValue downloadGroupFieldValue : downloadGroupFieldValues) {
	JSONObject downloadGroupJSONObject = JSONFactoryUtil.createJSONObject();

	Map<String, List<DDMFormFieldValue>> downloadGroupFieldValueMap = downloadGroupFieldValue.getNestedDDMFormFieldValuesMap();

	JSONArray downloadsJSONArray = JSONFactoryUtil.createJSONArray();

	List<DDMFormFieldValue> downloadFieldValues = downloadGroupFieldValueMap.get("download");

	for (DDMFormFieldValue downloadFieldValue : downloadFieldValues) {
		JSONObject downloadJSONObject = JSONFactoryUtil.createJSONObject();

		Map<String, List<DDMFormFieldValue>> downloadFieldValueMap = downloadFieldValue.getNestedDDMFormFieldValuesMap();

		downloadJSONObject.put("downloadName", _getStringValue(downloadFieldValue, locale));
		downloadJSONObject.put("downloadURL", _getStringValue(downloadFieldValueMap, "downloadUrl", locale));

		JSONObject downloadDetailsJSONObject = JSONFactoryUtil.createJSONObject();

		List<DDMFormFieldValue> downloadDetailFieldValues = downloadFieldValueMap.get("downloadDetail");

		for (DDMFormFieldValue downloadDetailFieldValue : downloadDetailFieldValues) {
			Map<String, List<DDMFormFieldValue>> downloadDetailFieldValueMap = downloadDetailFieldValue.getNestedDDMFormFieldValuesMap();

			downloadDetailsJSONObject.put(_getStringValue(downloadDetailFieldValueMap, "detailLabel", locale), _getStringValue(downloadDetailFieldValueMap, "detailValue", locale));
		}

		downloadJSONObject.put("downloadDetails", downloadDetailsJSONObject);

		downloadsJSONArray.put(downloadJSONObject);
	}

	downloadGroupJSONObject.put("downloadGroupName", _getStringValue(downloadGroupFieldValue, locale));
	downloadGroupJSONObject.put("downloads", downloadsJSONArray);

	downloadGroupsJSONArray.put(downloadGroupJSONObject);
}

JSONObject requiredAgreementJSONObject = JSONFactoryUtil.createJSONObject();

if (Validator.isNotNull(requiredAgreement)) {
	PortletPreferences downloadsPortletPreferences = PortletPreferencesLocalServiceUtil.getPreferences(company.getCompanyId(), company.getCompanyId(), PortletKeys.PREFS_OWNER_TYPE_COMPANY, PortletKeys.PREFS_PLID_SHARED, "3_WAR_osbportlet", null);

	String languageId = LocaleUtil.toLanguageId(LocaleUtil.US);

	String agreementURL = GetterUtil.getString(downloadsPortletPreferences.getValue(requiredAgreement + "Url_" + languageId, StringPool.BLANK));
	String agreementVersion = GetterUtil.getString(downloadsPortletPreferences.getValue(requiredAgreement + "Version_" + languageId, StringPool.BLANK));

	String acceptAgreementURL = downloadsDisplayContext.getAcceptAgreementURL(requiredAgreement, agreementVersion);
	String agreementContentURL = agreementURL + "&agreementVersion=" + agreementVersion;
	String verifyAgreementURL = downloadsDisplayContext.getVerifyAgreementURL(requiredAgreement, agreementVersion);

	requiredAgreementJSONObject.put("acceptAgreementURL", acceptAgreementURL);
	requiredAgreementJSONObject.put("agreementContentURL", agreementContentURL);
	requiredAgreementJSONObject.put("verifyAgreementURL", verifyAgreementURL);
}
%>

<aui:script>
	Downloads.render(
		Downloads.FileDownloads,
		{
			downloadGroups: <%= downloadGroupsJSONArray %>,
			journalArticleId: <%= journalArticle.getResourcePrimKey() %>,
			requiredAgreement: <%= requiredAgreementJSONObject %>
		},
		document.getElementById('<portlet:namespace />downloads<%= journalArticle.getResourcePrimKey() %>')
	);
</aui:script>

<%!
private String _getStringValue(DDMFormFieldValue ddmFormFieldValue, Locale locale) {
	Value value = ddmFormFieldValue.getValue();

	String stringValue = value.getString(locale);

	String type = ddmFormFieldValue.getType();

	if (type.equals(DDMFormFieldType.SELECT)) {
		try {
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray(stringValue);

			if (jsonArray.length() > 0) {
				return jsonArray.getString(0);
			}
		}
		catch (Exception e) {
		}
	}

	return stringValue;
}

private String _getStringValue(Map<String, List<DDMFormFieldValue>> ddmFormFieldValuesMap, String name, Locale locale) {
	List<DDMFormFieldValue> ddmFormFieldValues = ddmFormFieldValuesMap.get(name);

	if ((ddmFormFieldValues == null) || ddmFormFieldValues.isEmpty()) {
		return StringPool.BLANK;
	}

	DDMFormFieldValue ddmFormFieldValue = ddmFormFieldValues.get(0);

	return _getStringValue(ddmFormFieldValue, locale);
}
%>