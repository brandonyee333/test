<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/init.jsp" %>

<div class="lfr-ddm-container" id="<%= randomNamespace %>">
	<c:if test="<%= ddmForm != null %>">

		<%
		request.setAttribute("checkRequired", checkRequired);

		DDMFormFieldRenderingContext ddmFormFieldRenderingContext = new DDMFormFieldRenderingContext();

		ddmFormFieldRenderingContext.setFields(fields);
		ddmFormFieldRenderingContext.setHttpServletRequest(request);
		ddmFormFieldRenderingContext.setHttpServletResponse(response);
		ddmFormFieldRenderingContext.setLocale(requestedLocale);
		ddmFormFieldRenderingContext.setMode(mode);
		ddmFormFieldRenderingContext.setNamespace(fieldsNamespace);
		ddmFormFieldRenderingContext.setPortletNamespace(portletResponse.getNamespace());
		ddmFormFieldRenderingContext.setReadOnly(readOnly);
		ddmFormFieldRenderingContext.setShowEmptyFieldLabel(showEmptyFieldLabel);
		%>

		<%= DDMFormRendererUtil.render(ddmForm, ddmFormFieldRenderingContext) %>

		<aui:input name="<%= HtmlUtil.getAUICompatibleId(ddmFormValuesInputName) %>" type="hidden" />

		<aui:script use="liferay-ddm-form">
			var liferayDDMForm = Liferay.component(
				'<portlet:namespace /><%= HtmlUtil.escapeJS(fieldsNamespace) %>ddmForm',
				new Liferay.DDM.Form(
					{
						container: '#<%= randomNamespace %>',
						ddmFormValuesInput: '#<portlet:namespace /><%= HtmlUtil.getAUICompatibleId(ddmFormValuesInputName) %>',
						definition: <%= DDMUtil.getDDMFormJSONString(ddmForm) %>,
						documentLibrarySelectorURL: '<%= documentLibrarySelectorURL %>',
						doAsGroupId: <%= scopeGroupId %>,
						fieldsNamespace: '<%= HtmlUtil.escapeJS(fieldsNamespace) %>',
						imageSelectorURL: '<%= imageSelectorURL %>',
						mode: '<%= HtmlUtil.escapeJS(mode) %>',
						p_l_id: <%= themeDisplay.getPlid() %>,
						portletNamespace: '<portlet:namespace />',
						repeatable: <%= repeatable %>

						<c:if test="<%= ddmFormValues != null %>">
							, values: <%= DDMUtil.getDDMFormValuesJSONString(ddmFormValues) %>
						</c:if>
					}
				)
			);

			var onDestroyPortlet = function(event) {
				if (event.portletId === '<%= portletDisplay.getId() %>') {
					liferayDDMForm.destroy();

					Liferay.detach('destroyPortlet', onDestroyPortlet);
				}
			};

			Liferay.on('destroyPortlet', onDestroyPortlet);
		</aui:script>
	</c:if>