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
Map<Integer, Long> randomNinePositionMap = new HashMap<>();

for (int i = 0; i < 9; i++) {
	long docProjectId = GetterUtil.getLong(portletPreferences.getValue("randomNinePosition" + i, StringPool.BLANK));

	randomNinePositionMap.put(i, docProjectId);
}
%>

<div class="configuration">
	<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationURL" />

	<aui:form action="<%= configurationURL %>" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

		<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="redirect" />

		<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

		<div class="doc-project-random-nine-position-configuration">
			<p class="title"><liferay-ui:message key="random-nine-position-configuration" /></p>

			<div class="doc-project-random-nine-position-selection">
				<ul>

					<%
					List<DocProject> docProjects = DocProjectLocalServiceUtil.getDocProjects(false, DocProjectConstants.STATUS_LIVE, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

					for (int i = 0; i < 9; i++) {
						String name = "preferences--randomNinePosition" + i + "--";

						boolean disabled = false;

						if (i >= docProjects.size()) {
							disabled = true;
						}
					%>

						<li>
							<aui:select disabled="<%= disabled %>" id="randomNinePositionSelector" label="<%= StringPool.BLANK %>" name="<%= name %>" onChange='<%= renderResponse.getNamespace() + "checkSelectedOption(this);" %>' showEmptyOption="<%= true %>">

								<%
								for (DocProject docProject : docProjects) {
									boolean selected = false;

									if (docProject.getDocProjectId() == randomNinePositionMap.get(i)) {
										selected = true;
									}
								%>

									<aui:option label="<%= docProject.getName() %>" selected="<%= selected %>" value="<%= docProject.getDocProjectId() %>" />

								<%
								}
								%>

							</aui:select>
						</li>

					<%
					}
					%>

				</ul>
			</div>

			<p class="error-message" hidden id='<%= renderResponse.getNamespace() + "errorMessage" %>'>
				<liferay-ui:message key="please-assign-one-project-to-one-position" />
			</p>
		</div>

		<aui:button-row>
			<aui:button id="submitButton" type="submit" />
		</aui:button-row>
	</aui:form>
</div>

<aui:script>
	function <portlet:namespace />checkSelectedOption(selectedRandomNinePositionSelector) {
		var A = AUI();

		var allRandomNinePositionSelectors = A.all('#<portlet:namespace />randomNinePositionSelector');

		var disabled = false;

		var docProjectIndexSet = new Set();

		allRandomNinePositionSelectors.each(
			function(item, index) {
				var randomNinePositionSelector = item._node;

				if (randomNinePositionSelector.name != selectedRandomNinePositionSelector.name) {
					if (docProjectIndexSet.has(randomNinePositionSelector.selectedIndex)) {
						disabled = true;
					}
					else if (randomNinePositionSelector.selectedIndex != 0) {
						docProjectIndexSet.add(randomNinePositionSelector.selectedIndex);
					}
				}
			}
		);

		if (!disabled && docProjectIndexSet.has(selectedRandomNinePositionSelector.selectedIndex)) {
			disabled = true;
		}

		docProjectIndexSet.clear();

		var errorMessageNode = A.one('#<portlet:namespace />errorMessage');

		var submitButton = A.one('#<portlet:namespace />submitButton');

		if (disabled) {
			errorMessageNode.removeAttribute('hidden');

			submitButton.setAttribute('disabled');
		}
		else if (submitButton.hasAttribute('disabled')) {
			errorMessageNode.setAttribute('hidden');

			submitButton.removeAttribute('disabled');
		}
	}
</aui:script>