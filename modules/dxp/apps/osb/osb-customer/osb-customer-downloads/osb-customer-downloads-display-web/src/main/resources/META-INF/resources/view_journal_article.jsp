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

String product = _getStringValue(ddmFormFieldValuesMap, "product", locale);
String fileType = _getStringValue(ddmFormFieldValuesMap, "fileType", locale);
String requiredAgreement = _getStringValue(ddmFormFieldValuesMap, "requiredAgreement", locale);
String alertMessage = _getStringValue(ddmFormFieldValuesMap, "alertMessage", locale);
String additionalNotes = _getStringValue(ddmFormFieldValuesMap, "additionalNotes", locale);

Map<String, Map<String, String>> downloadDetailsMap = new HashMap<>();
%>

<portlet:renderURL var="journalArticleURL">
	<portlet:param name="mvcRenderCommandName" value="/view" />
	<portlet:param name="journalArticleResourcePrimKey" value="<%= String.valueOf(journalArticle.getResourcePrimKey()) %>" />
</portlet:renderURL>

<div>
	<a href="<%= journalArticleURL.toString() %>"><%= journalArticle.getTitle(locale) %></a>
</div>

product: <%= product %><br />

filetype: <%= fileType %><br />

requiredAgreement: <%= requiredAgreement %><br />

<c:if test="<%= Validator.isNotNull(requiredAgreement) %>">

	<%
	String languageId = LocaleUtil.toLanguageId(LocaleUtil.US);

	PortletPreferences downloadsPortletPreferences = PortletPreferencesLocalServiceUtil.getPreferences(company.getCompanyId(), company.getCompanyId(), PortletKeys.PREFS_OWNER_TYPE_COMPANY, PortletKeys.PREFS_PLID_SHARED, "3_WAR_osbportlet", null);

	String agreementUrl = GetterUtil.getString(downloadsPortletPreferences.getValue(requiredAgreement + "Url_" + languageId, StringPool.BLANK));
	String agreementVersion = GetterUtil.getString(downloadsPortletPreferences.getValue(requiredAgreement + "Version_" + languageId, StringPool.BLANK));

	String agreementContentUrl = agreementUrl + "&agreementVersion=" + agreementVersion;
	String acceptAgreementURL = downloadsDisplayContext.getAcceptAgreementURL(requiredAgreement, agreementVersion);
	String verifyAgreementUrl = downloadsDisplayContext.getVerifyAgreementURL(requiredAgreement, agreementVersion);
	%>

	agreementContentUrl: <%= agreementContentUrl %><br />
	acceptAgreementURL <%= acceptAgreementURL %><br />
	verifyAgreementUrl <%= verifyAgreementUrl %><br />
</c:if>

alertMessage: <%= alertMessage %><br />

additionalNotes: <%= additionalNotes %><br />

links:

<%
List<DDMFormFieldValue> linkFieldValues = ddmFormFieldValuesMap.get("link");

for (DDMFormFieldValue linkFieldValue : linkFieldValues) {
%>

	<a href="<%= _getStringValue(linkFieldValue.getNestedDDMFormFieldValuesMap(), "linkUrl", locale) %>"><%= _getStringValue(linkFieldValue, locale) %></a>

<%
}
%>

<br />

<%
List<DDMFormFieldValue> downloadGroupFieldValues = ddmFormFieldValuesMap.get("downloadGroup");
%>

<c:if test="<%= (downloadGroupFieldValues != null) && !downloadGroupFieldValues.isEmpty() %>">
	<select>

		<%
		for (DDMFormFieldValue downloadGroupFieldValue : downloadGroupFieldValues) {
		%>

			<c:if test="<%= downloadGroupFieldValues.size() > 1 %>">
				<optgroup label="<%= _getStringValue(downloadGroupFieldValue, locale) %>">
			</c:if>

			<%
			Map<String, List<DDMFormFieldValue>> downloadGroupFieldValueMap = downloadGroupFieldValue.getNestedDDMFormFieldValuesMap();

			List<DDMFormFieldValue> downloadFieldValues = downloadGroupFieldValueMap.get("download");

			for (DDMFormFieldValue downloadFieldValue : downloadFieldValues) {
				Map<String, List<DDMFormFieldValue>> downloadFieldValueMap = downloadFieldValue.getNestedDDMFormFieldValuesMap();
			%>

				<option value="<%= _getStringValue(downloadFieldValueMap, "downloadUrl", locale) %>"><%= _getStringValue(downloadFieldValue, locale) %></option>

			<%
				List<DDMFormFieldValue> downloadDetailFieldValues = downloadFieldValueMap.get("downloadDetail");

				Map<String, String> curDownloadDetailsMap = new HashMap<>();

				for (DDMFormFieldValue downloadDetailDDMFormFieldValue : downloadDetailFieldValues) {
					Map<String, List<DDMFormFieldValue>> downloadDetailFieldValueMap = downloadDetailDDMFormFieldValue.getNestedDDMFormFieldValuesMap();

					curDownloadDetailsMap.put(_getStringValue(downloadDetailFieldValueMap, "detailLabel", locale), _getStringValue(downloadDetailFieldValueMap, "detailValue", locale));
				}

				downloadDetailsMap.put(_getStringValue(downloadFieldValue, locale), curDownloadDetailsMap);
			}
			%>

			<c:if test="<%= downloadGroupFieldValues.size() > 1 %>">
				</optgroup>
			</c:if>

		<%
		}
		%>

	</select>
</c:if>

<br />

downloadDetailsMap: <%= downloadDetailsMap.toString() %>

<%!
private String _getStringValue(Map<String, List<DDMFormFieldValue>> ddmFormFieldValuesMap, String name, Locale locale) {
	List<DDMFormFieldValue> ddmFormFieldValues = ddmFormFieldValuesMap.get(name);

	if ((ddmFormFieldValues == null) || ddmFormFieldValues.isEmpty()) {
		return StringPool.BLANK;
	}

	DDMFormFieldValue ddmFormFieldValue = ddmFormFieldValues.get(0);

	return _getStringValue(ddmFormFieldValue, locale);
}

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
%>