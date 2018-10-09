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
String product = ParamUtil.getString(request, "product");
String fileType = ParamUtil.getString(request, "fileType");

String ddmStructureKey = downloadsDisplayContext.getDDMStructureKey();
JSONArray productsJSONArray = downloadsDisplayContext.getProductsJSONArray();
%>

<h1>
	<liferay-ui:message key="downloads" />
</h1>

<c:if test="<%= ddmStructureKey.equals(DDMStructureConstants.KEY_DOWNLOAD) %>">
	<div>
		<liferay-ui:message key="find-the-downloads-you-need-by-filtering-the-results-with-the-drop-down-menus-below" />
	</div>

	<div class="filters">

		<%
		PortletURL portletURL = renderResponse.createRenderURL();
		%>

		<aui:form action="<%= portletURL.toString() %>" method="get" name="fm">
			<liferay-portlet:renderURLParams portletURL="<%= portletURL %>" />

			<%
			JSONArray fileTypesJSONArray = null;
			%>

			<aui:select name="product" onChange='<%= renderResponse.getNamespace() + "updateFileType(this.value);" %>'>
				<aui:option label="select-product" value="" />

				<%
				for (int i = 0; i < productsJSONArray.length(); i++) {
					JSONObject jsonObject = productsJSONArray.getJSONObject(i);

					String productName = jsonObject.getString("name");
					String productValue = jsonObject.getString("value");

					boolean selected = false;

					if (productValue.equals(product)) {
						selected = true;

						fileTypesJSONArray = jsonObject.getJSONArray("fileTypes");
					}
				%>

					<aui:option label="<%= productName %>" selected="<%= selected %>" value="<%= productValue %>" />

				<%
				}
				%>

			</aui:select>

			<aui:select name="fileType" onChange='<%= "if (this.value != '') {submitForm(document." + renderResponse.getNamespace() + "fm);}" %>'>
				<aui:option label="select-file-type" value="" />

				<c:if test="<%= fileTypesJSONArray != null %>">

					<%
					for (int j = 0; j < fileTypesJSONArray.length(); j++) {
						JSONObject jsonObject = fileTypesJSONArray.getJSONObject(j);

						String fileTypeName = jsonObject.getString("name");
						String fileTypeValue = jsonObject.getString("value");
					%>

						<aui:option label="<%= fileTypeName %>" selected="<%= fileTypeValue.equals(fileType) %>" value="<%= fileTypeValue %>" />

					<%
					}
					%>

				</c:if>
			</aui:select>
		</aui:form>
	</div>
</c:if>

<div class="results">

	<%
	request.setAttribute(DownloadsDisplayWebKeys.ACCEPT_AGREEMENT_URL, downloadsDisplayContext.getAcceptAgreementURL());
	request.setAttribute(DownloadsDisplayWebKeys.VERIFY_AGREEMENT_URL, downloadsDisplayContext.getVerifyAgreementURL());
	%>

	<liferay-ui:search-container
		emptyResultsMessage="no-downloads-were-found"
		searchContainer="<%= downloadsDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.journal.model.JournalArticle"
			keyProperty="resourcePrimKey"
			modelVar="journalArticle"
		>
			<liferay-ui:search-container-column-text
				name="released"
			>

				<%
				Fields ddmFields = journalConverter.getDDMFields(journalArticle.getDDMStructure(), journalArticle.getContent());
				%>

				<%= DDMFieldsUtil.getString(ddmFields, "releaseDate") %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="name"
			>
				<portlet:renderURL var="journalArticleURL">
					<portlet:param name="mvcRenderCommandName" value="/view" />
					<portlet:param name="journalArticleResourcePrimKey" value="<%= String.valueOf(journalArticle.getResourcePrimKey()) %>" />
				</portlet:renderURL>

				<div>
					<a href="<%= journalArticleURL.toString() %>"><%= journalArticle.getTitle(locale) %></a>
				</div>

				<%
				JournalArticleDisplay journalArticleDisplay = JournalArticleLocalServiceUtil.getArticleDisplay(journalArticle, null, null, themeDisplay.getLanguageId(), 0, new PortletRequestModel(renderRequest, renderResponse), themeDisplay);
				%>

				<%= journalArticleDisplay.getContent() %>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>

<c:if test="<%= ddmStructureKey.equals(DDMStructureConstants.KEY_DOWNLOAD) %>">
	<aui:script>
		function <portlet:namespace />appendOption(selectElement, text, value) {
			var option = document.createElement('option');

			option.text = text;
			option.value = value;

			selectElement.append(option);
		}

		function <portlet:namespace />updateFileType(product) {
			var A = AUI();

			var fileType = A.one('#<portlet:namespace />fileType');

			fileType.empty();

			<portlet:namespace />appendOption(fileType, '<liferay-ui:message key="select-file-type" />', '');

			if (product == '') {
				submitForm(document.<portlet:namespace />fm);

				return;
			}

			<%
			for (int i = 0; i < productsJSONArray.length(); i++) {
				JSONObject jsonObject = productsJSONArray.getJSONObject(i);

				JSONArray fileTypesJSONArray = jsonObject.getJSONArray("fileTypes");
			%>

				if (product == '<%= jsonObject.getString("value") %>') {

					<%
					for (int j = 0; j < fileTypesJSONArray.length(); j++) {
						JSONObject fileTypeJSONObject = fileTypesJSONArray.getJSONObject(j);
					%>

						<portlet:namespace />appendOption(fileType, '<%= fileTypeJSONObject.getString("name") %>', '<%= fileTypeJSONObject.getString("value") %>');

					<%
					}
					%>

					<c:if test="<%= fileTypesJSONArray.length() == 1 %>">

						<%
						JSONObject fileTypeJSONObject = fileTypesJSONArray.getJSONObject(0);
						%>

						fileType.val('<%= fileTypeJSONObject.getString("value") %>');

						submitForm(document.<portlet:namespace />fm);
					</c:if>
				}

			<%
			}
			%>

		}
	</aui:script>
</c:if>