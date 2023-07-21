<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/aui/form/init.jsp" %>

	<c:if test="<%= (checkboxNames != null) && !checkboxNames.isEmpty() %>">
		<aui:input name="checkboxNames" type="hidden" value="<%= StringUtil.merge(checkboxNames) %>" />
	</c:if>

	<c:if test="<%= Validator.isNotNull(onSubmit) %>">
		</fieldset>
	</c:if>
</form>

<aui:script use="liferay-form">
	Liferay.Form.register(
		{
			id: '<%= namespace + HtmlUtil.escapeJS(name) %>'

			<c:if test="<%= validatorTagsMap != null %>">
				, fieldRules: [

					<%
					int i = 0;

					for (Map.Entry<String, List<ValidatorTag>> entry : validatorTagsMap.entrySet()) {
						String fieldName = entry.getKey();

						List<ValidatorTag> validatorTags = entry.getValue();

						for (ValidatorTag validatorTag : validatorTags) {
					%>

							<%= (i != 0) ? StringPool.COMMA : StringPool.BLANK %>

							{
								body: <%= validatorTag.getBody() %>,
								custom: <%= validatorTag.isCustom() %>,
								errorMessage: '<%= UnicodeLanguageUtil.get(resourceBundle, validatorTag.getErrorMessage()) %>',
								fieldName: '<%= namespace + HtmlUtil.escapeJS(fieldName) %>',
								validatorName: '<%= HtmlUtil.escapeJS(validatorTag.getName()) %>'
							}

					<%
							i++;
						}
					}
					%>

				]
			</c:if>

			<c:if test="<%= Validator.isNotNull(onSubmit) %>">
				, onSubmit: function(event) {
					<%= onSubmit %>
				}
			</c:if>

			, validateOnBlur: <%= validateOnBlur %>
		}
	);

	var onDestroyPortlet = function(event) {
		if (event.portletId === '<%= portletDisplay.getId() %>') {
			delete Liferay.Form._INSTANCES['<%= namespace + HtmlUtil.escapeJS(name) %>'];
		}
	};

	Liferay.on('destroyPortlet', onDestroyPortlet);

	<c:if test="<%= Validator.isNotNull(onSubmit) %>">
		A.all('#<%= namespace + HtmlUtil.escapeJS(name) %> .input-container').removeAttribute('disabled');
	</c:if>

	Liferay.fire('<portlet:namespace />formReady');
</aui:script>