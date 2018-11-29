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
	<h5 class="secondary-text-color section-subtitle">
		<liferay-ui:message key="use-the-dropdown-menus-below-to-find-the-downloads-you-need" />
	</h5>

	<%
	PortletURL portletURL = renderResponse.createRenderURL();
	%>

	<aui:form action="<%= portletURL.toString() %>" method="get" name="fm">
		<liferay-portlet:renderURLParams portletURL="<%= portletURL %>" />

		<%
		JSONArray fileTypesJSONArray = null;
		%>

		<div class="search-filters">
			<aui:select inlineField="<%= true %>" inlineLabel="left" name="product" onChange='<%= renderResponse.getNamespace() + "updateFileType(this.value);" %>' prefix="&#58;" wrapperCssClass="downloads-search search-filter-container">
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

			<aui:select inlineField="<%= true %>" inlineLabel="left" name="fileType" onChange='<%= "if (this.value != '') {submitForm(document." + renderResponse.getNamespace() + "fm);}" %>' prefix="&#58;" wrapperCssClass="downloads-search search-filter-container">
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
		</div>
	</aui:form>
</c:if>

<div class="results">
	<liferay-ui:search-container
		emptyResultsMessage="content-collection-is-empty-select-your-settings-above-to-show-details"
		searchContainer="<%= downloadsDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.journal.model.JournalArticle"
			cssClass="journal-article-row"
			keyProperty="resourcePrimKey"
			modelVar="journalArticle"
		>
			<liferay-ui:search-container-column-text
				name="released"
			>

				<%
				DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("yyyy-MM-dd");

				Fields ddmFields = journalConverter.getDDMFields(journalArticle.getDDMStructure(), journalArticle.getContent());

				Date releaseDate = dateFormat.parse(DDMFieldsUtil.getString(ddmFields, "releaseDate"));
				%>

				<%= mediumDateFormatDate.format(releaseDate) %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				cssClass="th-separator"
				value=""
			/>

			<liferay-ui:search-container-column-jsp
				name="name"
				path="/view_journal_article.jsp"
			/>
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