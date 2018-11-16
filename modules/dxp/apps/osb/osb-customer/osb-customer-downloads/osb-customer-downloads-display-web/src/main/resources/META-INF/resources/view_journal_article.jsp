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
String fileType = _getStringValue(ddmFormFieldValuesMap, "fileType", locale);
String product = _getStringValue(ddmFormFieldValuesMap, "product", locale);
String requiredAgreement = _getStringValue(ddmFormFieldValuesMap, "requiredAgreement", locale);
%>

<portlet:renderURL var="journalArticleURL">
	<portlet:param name="mvcRenderCommandName" value="/view" />
	<portlet:param name="journalArticleResourcePrimKey" value="<%= String.valueOf(journalArticle.getResourcePrimKey()) %>" />
</portlet:renderURL>

<h3 class="section-title">
	<aui:a href="<%= journalArticleURL.toString() %>" label="<%= journalArticle.getTitle(locale) %>" />
</h3>

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
		<svg class="lexicon-icon lexicon-icon-info-circle">
			<use xlink:href="#info-circle" />
		</svg>

		<span class="alert-header"><liferay-ui:message key="info" />:</span>

		<span class="alert-message"><%= alertMessage %></span>
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
%>

<aui:script>
	Downloads.render(
		Downloads.FileDownloads,
		{
			downloadGroups: <%= downloadGroupsJSONArray %>,
			journalArticleId: <%= journalArticle.getResourcePrimKey() %>
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