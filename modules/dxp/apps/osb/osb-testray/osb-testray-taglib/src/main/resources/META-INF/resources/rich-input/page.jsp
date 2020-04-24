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

<c:set value='${requestScope["testray:rich-input:label"]}' var="label" />
<c:set value='${requestScope["testray:rich-input:name"]}' var="name" />
<c:set value='${requestScope["testray:rich-input:selectedType"]}' var="selectedType" />
<c:set value='${requestScope["testray:rich-input:types"]}' var="types" />
<c:set value='${requestScope["testray:rich-input:value"]}' var="value" />

<div class="rich-editor rich-editor-${name}">
	<aui:row>
		<aui:col cssClass="testray-form-description" md="3">
			<h2>
				<liferay-ui:message key="${label}" />
			</h2>
		</aui:col>

		<aui:col cssClass="testray-form-content" md="9">
			<c:if test="${testrayRequirement.key == null}">
				<div hidden="true">
			</c:if>

			<aui:select cssClass="testray-input-right testray-input-small" label="" name="${name}Type" onChange="${htmlNamespace}updatePreview('#${htmlNamespace}${name}', '#${htmlNamespace}${name}Preview blockquote', '#${htmlNamespace}${name}Type');" showEmptyOption="${false}" wrapperCssClass="testray-input-right-wrapper">
				<c:forEach items="${types}" var="type">
					<aui:option label="${type}" selected="${type == selectedType}" value="${type}" />
				</c:forEach>
			</aui:select>

			<c:if test="${testrayRequirement.key == null}">
				</div>
			</c:if>
		</aui:col>
	</aui:row>

	<aui:row>
		<aui:col cssClass="testray-form-content" md="12">
			<aui:input label="" name="${name}" type="textarea" value="${value}" />
		</aui:col>
	</aui:row>

	<aui:row>
		<aui:col id="${name}Preview" md="12">
			<blockquote></blockquote>
		</aui:col>
	</aui:row>
</div>

<aui:script>
	Liferay.provide(
		window,
		'${htmlNamespace}previewMarkdown',
		function(content, previewContainer) {
			Liferay.Service(
				'/osb-testray-taglib.markdown/markdown-to-html',
				{
					markdown: content
				},
				function(obj) {
					previewContainer.html(obj);
				}
			);
		}
	);

	Liferay.provide(
		window,
		'${htmlNamespace}previewPlainText',
		function(content, previewContainer) {
			previewContainer.html('<pre>' + content + '</pre>');
		}
	);

	Liferay.provide(
		window,
		'${htmlNamespace}updatePreview',
		function(inputSelector, previewContainerSelector, typeSelectSelector) {
			var A = AUI();

			var input = A.one(inputSelector);
			var previewContainer = A.one(previewContainerSelector);
			var typeSelect = A.one(typeSelectSelector);

			if (input && previewContainer && typeSelect) {
				var type = typeSelect.val();

				if (type == 'markdown') {
					previewMarkdown(input.val(), previewContainer);
				}
				else {
					previewPlainText(input.val(), previewContainer);
				}
			}
		},
		['aui-base']
	);

	Liferay.on(
		'allPortletsReady',
		function(event) {
			AUI().use(
				'yui-throttle',
				function(A) {
					${htmlNamespace}updatePreview('#${htmlNamespace}${name}', '#${htmlNamespace}${name}Preview blockquote', '#${htmlNamespace}${name}Type');

					var input = A.one('#${htmlNamespace}${name}');

					input.on(
						'keyup',
						A.throttle(
							function() {
								${htmlNamespace}updatePreview('#${htmlNamespace}${name}', '#${htmlNamespace}${name}Preview blockquote', '#${htmlNamespace}${name}Type');
							}
						)
					);
				}
			);
		}
	);
</aui:script>