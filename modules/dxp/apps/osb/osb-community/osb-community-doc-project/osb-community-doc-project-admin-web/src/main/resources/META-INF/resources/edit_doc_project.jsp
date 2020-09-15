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
String redirect = ParamUtil.getString(request, "redirect");

long docProjectId = ParamUtil.getLong(request, "docProjectId");

DocProject docProject = DocProjectLocalServiceUtil.fetchDocProject(docProjectId);

String headerTitle = (docProjectId > 0) ? LanguageUtil.format(request, "edit-x", docProject.getName(), false) : LanguageUtil.get(request, "add-open-source-project");

String docProjectType = DocProjectConstants.TYPE_SITE;
String gitHubRepositoryName = StringPool.BLANK;
String gitHubRepositoryOwner = StringPool.BLANK;
String headerGradientEndColor = "#ffffff";
String headerGradientStartColor = "#ffffff";
String url = StringPool.BLANK;

if (docProject != null) {
	docProjectType = docProject.getType();

	if (docProjectType.equals(DocProjectConstants.TYPE_URL)) {
		DocProjectURLTypeSettings docProjectURLTypeSettings = (DocProjectURLTypeSettings)DocProjectTypeSettingsFactoryUtil.create(docProject);

		url = docProjectURLTypeSettings.getURL();
	}
	else {
		DocProjectSiteTypeSettings docProjectSiteTypeSettings = (DocProjectSiteTypeSettings)DocProjectTypeSettingsFactoryUtil.create(docProject);

		gitHubRepositoryName = docProjectSiteTypeSettings.getGitHubRepositoryName();
		gitHubRepositoryOwner = docProjectSiteTypeSettings.getGitHubRepositoryOwner();
		headerGradientEndColor = docProjectSiteTypeSettings.getHeaderGradientEndColor();
		headerGradientStartColor = docProjectSiteTypeSettings.getHeaderGradientStartColor();
	}
}

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle(headerTitle);
%>

<div class="container-fluid-1280 edit-doc-project">
	<portlet:actionURL name="/edit_doc_project" var="editDocProjectURL">
		<portlet:param name="mvcRenderCommandName" value="/edit_doc_project" />
	</portlet:actionURL>

	<aui:form action="<%= editDocProjectURL %>" method="post" name="fm">
		<liferay-ui:error exception="<%= DocProjectHeaderGradientColorException.class %>" message="please-enter-a-correct-color-format" />
		<liferay-ui:error exception="<%= DocProjectIconException.class %>" message="please-provide-a-valid-icon" />

		<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
		<aui:input name="docProjectId" type="hidden" value="<%= docProjectId %>" />

		<div class="lfr-form-content">
			<aui:model-context bean="<%= docProject %>" model="<%= DocProject.class %>" />

			<aui:fieldset-group markupView="lexicon">
				<aui:fieldset label="details">
					<aui:input autoFocus="<%= true %>" name="name">
						<aui:validator name="required" />
					</aui:input>

					<aui:input name="description">
						<aui:validator name="required" />
					</aui:input>

					<div class="control-label">
						<liferay-ui:message key="icon" />
					</div>

					<c:if test="<%= docProject != null %>">
						<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/serve_doc_project_icon" var="iconURL">
							<portlet:param name="docProjectId" value="<%= String.valueOf(docProject.getDocProjectId()) %>" />
						</liferay-portlet:resourceURL>

						<div class="icon">
							<img class="xsmall" src="<%= HtmlUtil.escapeAttribute(iconURL) %>" />
						</div>
					</c:if>

					<aui:input accept="image/*" label="" name="icon" type="file" />
				</aui:fieldset>

				<aui:fieldset label="categorization">
					<aui:input name="tags" type="assetTags" />
				</aui:fieldset>

				<aui:fieldset label="status">
					<aui:select label="site-status" name="status">
						<aui:option label="offline" value="<%= DocProjectConstants.STATUS_OFFLINE %>" />
						<aui:option label="live" value="<%= DocProjectConstants.STATUS_LIVE %>" />
					</aui:select>

					<aui:input name="unlisted" />
				</aui:fieldset>

				<div class="doc-project-type">
					<aui:fieldset label="type">
						<aui:select label="type" name="type">
							<aui:option label="site" value="<%= DocProjectConstants.TYPE_SITE %>" />
							<aui:option label="url" value="<%= DocProjectConstants.TYPE_URL %>" />
						</aui:select>
					</aui:fieldset>
				</div>

				<aui:fieldset label="options">
					<div class="doc-project-type-site">
						<aui:input label="gradient-start-color" name="headerGradientStartColor" type="text" value="<%= headerGradientStartColor %>">
							<aui:validator name="required" />

							<aui:validator errorMessage="please-enter-a-correct-color-format" name="custom">
								function(val, fieldNode, ruleValue) {
									var result = false;
									var pattern = /#[a-zA-Z0-9]{6}/;

									if (pattern.test(val)) {
										result = true;
									}

									return result;
								}
							</aui:validator>
						</aui:input>

						<aui:input label="gradient-end-color" name="headerGradientEndColor" type="text" value="<%= headerGradientEndColor %>">
							<aui:validator name="required" />

							<aui:validator errorMessage="please-enter-a-correct-color-format" name="custom">
								function(val, fieldNode, ruleValue) {
									var result = false;
									var pattern = /#[a-zA-Z0-9]{6}/;

									if (pattern.test(val)) {
										result = true;
									}

									return result;
								}
							</aui:validator>
						</aui:input>

						<aui:input label="github-repository-owner" name="gitHubRepositoryOwner" type="text" value="<%= gitHubRepositoryOwner %>" />

						<aui:input label="github-repository-name" name="gitHubRepositoryName" type="text" value="<%= gitHubRepositoryName %>" />
					</div>

					<div class="doc-project-type-url">
						<aui:input label="url" name="url" type="text" value="<%= url %>" />
					</div>
				</aui:fieldset>
			</aui:fieldset-group>
		</div>

		<aui:button-row>
			<aui:button cssClass="btn-lg" type="submit" />

			<aui:button cssClass="btn-lg" href="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</aui:form>
</div>

<aui:script use="aui-base">
	var A = AUI();

	<c:choose>
		<c:when test="<%= docProjectType.equals(DocProjectConstants.TYPE_SITE) %>">
			A.one('.doc-project-type-url').hide();
		</c:when>
		<c:otherwise>
			A.one('.doc-project-type-site').hide();
		</c:otherwise>
	</c:choose>

	A.one('.doc-project-type select').on(
		'change',
		function(event) {
			if (event.currentTarget.val() === "site") {
				A.one('.doc-project-type-site').show();
				A.one('.doc-project-type-url').hide();
			}
			else if (event.currentTarget.val() === "url") {
				A.one('#<portlet:namespace />headerGradientEndColor').val('#ffffff');
				A.one('#<portlet:namespace />headerGradientStartColor').val('#ffffff');

				A.one('.doc-project-type-site').hide();
				A.one('.doc-project-type-url').show();
			}
		}
	);
</aui:script>