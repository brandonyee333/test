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

<%@ include file="/asset_publisher_navigation/init.jsp" %>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

	<div class="portlet-configuration-body-content">
		<div class="container-fluid-1280">
			<aui:fieldset-group markupView="lexicon">
				<aui:fieldset>
					<label>
						<liferay-ui:message key="filter-by-the-following-vocabularies" />
					</label>

					<aui:input name="preferences--vocabularyNames--" type="hidden" value="<%= StringUtil.merge(vocabularyNames) %>" />

					<%
					List<AssetVocabulary> assetVocabularies = AssetVocabularyLocalServiceUtil.getGroupVocabularies(scopeGroupId);

					for (AssetVocabulary assetVocabulary : assetVocabularies) {
					%>

						<br />

						<input <%= ArrayUtil.contains(vocabularyNames, assetVocabulary.getName()) ? "checked=''" : "" %> onClick="<portlet:namespace />updateVocabularyNames('<%= assetVocabulary.getName() %>', this.checked);" type="checkbox" /><%= HtmlUtil.escape(assetVocabulary.getName()) %>

					<%
					}
					%>

				</aui:fieldset>
			</aui:fieldset-group>
		</div>
	</div>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />
	</aui:button-row>
</aui:form>

<aui:script>
	function <portlet:namespace />updateVocabularyNames(name, checked) {
		var vocabularyNames = document.getElementById('<portlet:namespace />vocabularyNames');

		var value = vocabularyNames.value;

		var pos = value.indexOf(name);

		if (checked && (pos == -1)) {
			if (value == '') {
				vocabularyNames.value = name;
			}
			else {
				vocabularyNames.value += ',' + name;
			}
		}
		else if (!checked && (pos != -1)) {
			if (pos == 0) {
				vocabularyNames.value = value.substring(name.length + 1);
			}
			else {
				vocabularyNames.value = value.substring(0, pos - 1) + value.substring(pos + name.length);
			}
		}
	}
</aui:script>