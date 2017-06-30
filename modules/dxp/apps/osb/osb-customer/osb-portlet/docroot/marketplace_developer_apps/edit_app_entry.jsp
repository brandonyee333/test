<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/marketplace_developer_apps/init.jsp" %>

<%
long appEntryId = ParamUtil.getLong(request, "appEntryId");

AppEntry appEntry = null;

try {
	appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);
}
catch (NoSuchAppEntryException nsaee) {
}

AppVersion appVersion = null;

if (appEntry != null) {
	appVersion = appEntry.getActionableAppVersion();
}

ContractEntry contractEntry = null;

if (appVersion != null) {
	if (appVersion.getContractEntryId() > 0) {
		contractEntry = ContractEntryLocalServiceUtil.getContractEntry(appVersion.getContractEntryId());
	}
}

boolean customEula = false;

if (contractEntry != null) {
	customEula = true;
}

portletPreferences = MarketplaceUtil.getPortletPreferences();

String eulaMinimumTermsArticleId = PrefsParamUtil.getString(portletPreferences, request, "eulaMinimumTermsArticleId", StringPool.BLANK);

boolean labs = BeanParamUtil.getBoolean(appVersion, request, "labs");
int productType = BeanParamUtil.getInteger(appVersion, request, "productType");
boolean paclEnabled = BeanParamUtil.getBoolean(appVersion, request, "paclEnabled", true);
int releaseType = ParamUtil.getInteger(request, "releaseType", AppVersionConstants.RELEASE_TYPE_NEW_VERSION);
boolean hidden = BeanParamUtil.getBoolean(appVersion, request, "hidden");
boolean portalRequired = BeanParamUtil.getBoolean(appVersion, request, "portalRequired");

long[] assetCategoryIds = ParamUtil.getLongValues(request, "assetCategoryIds");

if (appEntry != null) {
	assetCategoryIds = appEntry.getAssetCategoryIds();
}

AssetVocabulary assetVocabulary = AssetVocabularyLocalServiceUtil.getGroupVocabulary(OSBConstants.GROUP_GUEST_ID, "Marketplace");
%>

<div class="marketplace-developer-apps edit-app-entry">
	<liferay-ui:header
		title="app-details"
	/>

	<liferay-util:include page="/marketplace_developer_apps/app_breadcrumb.jsp" servletContext="<%= application %>">
		<liferay-util:param name="appEntryId" value="<%= String.valueOf(appEntryId) %>" />
	</liferay-util:include>

	<portlet:actionURL name="updateAppEntry" var="editAppEntryURL" />

	<aui:form action="<%= editAppEntryURL %>" cssClass="my-app" enctype="multipart/form-data" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveAppEntry(false);" %>'>
		<aui:input name="mvcPath" type="hidden" value="/marketplace_developer_apps/edit_app_entry.jsp" />
		<aui:input name="redirect" type="hidden" value="" />
		<aui:input name="appEntryId" type="hidden" value="<%= String.valueOf(appEntryId) %>" />
		<aui:input name="releaseType" type="hidden" value="<%= String.valueOf(releaseType) %>" />

		<liferay-ui:error exception="<%= AppEntryCategoryException.class %>" message="please-enter-a-valid-category" />
		<liferay-ui:error exception="<%= AppEntryDemoWebsiteException.class %>" message="please-enter-a-valid-demo-website" />
		<liferay-ui:error exception="<%= AppEntryDescriptionException.class %>" message="please-enter-a-valid-description" />
		<liferay-ui:error exception="<%= AppEntryDocumentationWebsiteException.class %>" message="please-enter-a-valid-documentation-website" />

		<liferay-ui:error exception="<%= AppEntryIconException.class %>">
			<liferay-ui:message arguments="<%= new String[] {String.valueOf(PortletPropsValues.APP_ENTRY_ICON_MAX_HEIGHT), String.valueOf(PortletPropsValues.APP_ENTRY_ICON_MAX_WIDTH)} %>" key="please-upload-a-x-by-x-png-icon" />
		</liferay-ui:error>

		<liferay-ui:error exception="<%= AppEntryLicenseTypeException.class %>" message="please-select-free-or-paid-licensing-for-app-pricing" />
		<liferay-ui:error exception="<%= AppEntryLicenseTypeException.class %>" message="this-developer-account-must-first-be-upgraded-before-creating-paid-apps" rowBreak='<%= _errorCss(renderResponse, "upgradeNote") %>' />
		<liferay-ui:error exception="<%= AppEntryOrderURLException.class %>" message="please-enter-a-valid-order-url" />
		<liferay-ui:error exception="<%= AppEntryPACLException.class %>" message="this-app-must-have-pacl-enabled" />
		<liferay-ui:error exception="<%= AppEntryReleaseTypeException.class %>" message="we-were-unable-to-process-this-update" />
		<liferay-ui:error exception="<%= AppEntrySourceCodeWebsiteException.class %>" message="please-enter-a-valid-source-code-website" />
		<liferay-ui:error exception="<%= AppEntrySupportWebsiteException.class %>" message="please-enter-a-valid-support-website" />
		<liferay-ui:error exception="<%= AppEntryTitleException.class %>" message="please-enter-a-valid-title" />
		<liferay-ui:error exception="<%= AppEntryWebsiteException.class %>" message="please-enter-a-valid-developer-website" />
		<liferay-ui:error exception="<%= AppPackageMarketplaceSupportException.class %>" message="marketplace-has-removed-pacl-support-for-previously-uploaded-plugins-please-select-add-new-version-and-upload-new-plugins" />
		<liferay-ui:error exception="<%= AssetTagException.class %>" message="one-or-more-tags-contains-invalid-characters" />
		<liferay-ui:error exception="<%= ContractEntryContentException.class %>" message="please-enter-a-valid-eula" />
		<liferay-ui:error exception="<%= DuplicateAppEntryException.class %>" message="please-enter-a-unique-title" />

		<aui:model-context bean="<%= appVersion %>" model="<%= AppVersion.class %>" />

		<aui:layout>
			<aui:fieldset>
				<div class="app-developer aui-selection-group">
					<div class="aui-field-label required">
						<liferay-ui:message key="app-owner" />
					</div>

					<c:if test="<%= developerEntry.isUser() %>">
						<div class="aui-field-help">

							<%
							long marketplaceRegistrationPlid = PortalUtil.getPlidFromPortletId(OSBConstants.GROUP_GUEST_ID, OSBPortletKeys.OSB_MARKETPLACE_REGISTRATION);

							PortletURL editCorpEntryURL = PortletURLFactoryUtil.create(request, OSBPortletKeys.OSB_MARKETPLACE_REGISTRATION, marketplaceRegistrationPlid, PortletRequest.RENDER_PHASE);

							String taglibEditCorpEntry = "<a href=\"" + editCorpEntryURL + "\">";
							%>

							<liferay-ui:message arguments='<%= new Object[] {taglibEditCorpEntry, "</a>"} %>' key="you-are-submitting-this-app-as-an-individual-or-as-a-sole-proprietor" />
						</div>
					</c:if>

					<%= HtmlUtil.escape(developerEntry.getName()) %>
				</div>

				<div class="app-pricing aui-selection-group">
					<div class="aui-field-label required">
						<liferay-ui:message key="app-pricing" />
					</div>

					<c:choose>
						<c:when test="<%= (appEntry != null) && (!appEntry.isFirstSubmission() || developerEntry.isUser()) %>">
							<aui:input name="licenseType" type="hidden" value="<%= appEntry.getLicenseType() %>" />

							<liferay-ui:message key="<%= AssetLicenseConstants.getLicenseTypeLabel(appEntry.getLicenseType()) %>" />
						</c:when>
						<c:otherwise>
							<span class="aui-field-help">
								<liferay-ui:message key="you-cannot-change-this-after-liferay-has-approved-your-app" />
							</span>

							<%
							int licenseType = BeanParamUtil.getInteger(appEntry, request, "licenseType", AssetLicenseConstants.LICENSE_TYPE_FREE);
							%>

							<aui:input checked="<%= licenseType == AssetLicenseConstants.LICENSE_TYPE_FREE %>" cssClass="free-radio" label="free" name="licenseType" type="radio" value="<%= AssetLicenseConstants.LICENSE_TYPE_FREE %>" />
							<aui:input checked="<%= licenseType == AssetLicenseConstants.LICENSE_TYPE_PRODUCTION %>" cssClass="paid-license-radio" disabled="<%= (developerEntry != null) && developerEntry.isSubscriptionExpired() %>" helpMessage="an-instance-unit-refers-to-a-single-installation-of-the-liferay-portal-which-corresponds-to-one-1-liferay-portal-war-file" label="paid-licensing-based-on-instance-units" name="licenseType" type="radio" value="<%= AssetLicenseConstants.LICENSE_TYPE_PRODUCTION %>" />

							<c:if test="<%= (developerEntry != null) && developerEntry.isSubscriptionExpired() %>">
								<span class="aui-select-note" id="<portlet:namespace />upgradeNote">

									<%
									long marketplaceRegistrationPlid = PortalUtil.getPlidFromPortletId(OSBConstants.GROUP_GUEST_ID, OSBPortletKeys.OSB_MARKETPLACE_REGISTRATION);
									%>

									<liferay-portlet:renderURL plid="<%= marketplaceRegistrationPlid %>" portletName="<%= OSBPortletKeys.OSB_MARKETPLACE_REGISTRATION %>" var="upgradeDeveloperEntryURL">
										<portlet:param name="mvcPath" value="/marketplace_registration/upgrade_developer_entry.jsp" />
										<portlet:param name="redirect" value="<%= currentURL %>" />
										<portlet:param name="developerEntryId" value="<%= String.valueOf(developerEntry.getDeveloperEntryId()) %>" />
									</liferay-portlet:renderURL>

									<%
									String taglibUpgradeDeveloperEntry = "<a href=\"" + upgradeDeveloperEntryURL + "\">";
									%>

									<liferay-ui:message arguments='<%= new String[] {taglibUpgradeDeveloperEntry, "</a>"} %>' key="note-in-order-to-price-and-sell-apps-in-the-liferay-marketplace-you-must-first-upgrade-your-developer-account" />
								</span>
							</c:if>
						</c:otherwise>
					</c:choose>
				</div>
			</aui:fieldset>

			<aui:fieldset label="app-information">
				<aui:input cssClass="required" name="title" />

				<liferay-util:include page="/common/localized_input.jsp" servletContext="<%= application %>">
					<liferay-util:param name="defaultLanguageId" value="en_US" />
					<liferay-util:param name="label" value='<%= LanguageUtil.get(pageContext, "description") %>' />
					<liferay-util:param name="name" value="description" />
					<liferay-util:param name="required" value="<%= String.valueOf(true) %>" />
					<liferay-util:param name="value" value="<%= (appVersion != null) ? appVersion.getDescription() : StringPool.BLANK %>" />
				</liferay-util:include>

				<div class="edit-icon">
					<span class="aui-field-label required">
						<liferay-ui:message key='<%= appVersion == null ? "icon" : "new-icon" %>' />
					</span>

					<span class="aui-field-help">
						<liferay-ui:message arguments='<%= new Object[] {"90 x 90", "512"} %>' key="icons-must-be-a-x-png-and-cannot-exceed-x-k" />
					</span>

					<c:if test="<%= appVersion != null %>">
						<div class="container">
							<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="serveIcon" var="iconURL">
								<portlet:param name="assetAttachmentId" value="<%= String.valueOf(appVersion.getIconImageId()) %>" />
							</liferay-portlet:resourceURL>

							<div class="icon">
								<img class="xsmall" src="<%= HtmlUtil.escapeAttribute(iconURL) %>" />
							</div>
						</div>
					</c:if>

					<aui:input accept="image/*" label="" name="icon" type="file" />
				</div>
			</aui:fieldset>

			<aui:fieldset>
				<div class="marketplace-uploader" id="<portlet:namespace />screenUploader">
					<span class="aui-field-label">
						<liferay-ui:message key="screen-captures" />
					</span>

					<span class="aui-field-help">
						<liferay-ui:message arguments='<%= new Object[] {"1080 x 675", "384"} %>' key="each-screenshot-must-be-a-jpg-not-exceeding-x-pixels-and-x-k" />
					</span>

					<div class="aui-helper-hidden portlet-msg-error"></div>

					<aui:input name="assetAttachmentIds" type="hidden" />

					<div class="buttons">
						<div class="upload-area" id="<portlet:namespace />uploadArea">
							<liferay-ui:message key="drop-file-here-to-upload-or-click-to-browse-for-files" />
						</div>
					</div>

					<ul class="file-list" id="<portlet:namespace />fileList">

						<%
						List<AssetAttachment> assetAttachments = new ArrayList<AssetAttachment>();

						long[] assetAttachmentIds = ParamUtil.getLongValues(request, "assetAttachmentIds");

						if (assetAttachmentIds.length > 0) {
							for (long assetAttachmentId : assetAttachmentIds) {
								AssetAttachment assetAttachment = AssetAttachmentLocalServiceUtil.fetchAssetAttachment(assetAttachmentId);

								if (assetAttachment != null) {
									assetAttachments.add(assetAttachment);
								}
							}
						}
						else if (appVersion != null) {
							assetAttachments = AssetAttachmentLocalServiceUtil.getAssetAttachments(AppVersion.class.getName(), appVersion.getAppVersionId(), AssetAttachmentConstants.TYPE_MEDIA, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new AssetAttachmentRankComparator());

							if (appVersion.isApproved()) {
								assetAttachments = ListUtil.copy(assetAttachments);

								for (int i = 0; i < assetAttachments.size(); i++) {
									AssetAttachment assetAttachment = assetAttachments.get(i);

									assetAttachments.set(i, AssetAttachmentLocalServiceUtil.copyAssetAttachment(assetAttachment.getAssetAttachmentId(), AssetAttachmentConstants.CLASS_NAME_ID_DEFAULT, AssetAttachmentConstants.CLASS_PK_DEFAULT));
								}
							}
						}

						for (AssetAttachment assetAttachment : assetAttachments) {
						%>

							<li class="file" data-assetAttachmentId="<%= assetAttachment.getAssetAttachmentId() %>">
								<span class="title"><%= assetAttachment.getFileName() %></span>

								<span class="actions">
									<a class="action delete" href="javascript:;"><liferay-ui:message key="delete" /></a>
								</span>
							</li>

						<%
						}
						%>

					</ul>
				</div>
			</aui:fieldset>

			<aui:select cssClass="category required" label="category" name="assetCategoryIds">
				<aui:option />

				<%
				List<AssetCategory> assetCategories = AssetCategoryServiceUtil.getVocabularyRootCategories(assetVocabulary.getVocabularyId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, new AssetCategoryTreeComparator(true));

				for (AssetCategory assetCategory : assetCategories) {
					if (assetCategory.getCategoryId() == OSBConstants.ASSET_CATEGORY_EE_PLUGINS_ID) {
						continue;
					}

					boolean selected = false;

					if (assetCategoryIds.length == 1) {
						selected = ArrayUtil.contains(assetCategoryIds, assetCategory.getCategoryId());
					}
				%>

					<aui:option disabled="<%= MarketplaceUtil.isUnselectableCategory(assetCategory.getCategoryId()) %>" label="<%= assetCategory.getTitle(themeDisplay.getLanguageId(), true) %>" selected="<%= selected %>" value="<%= String.valueOf(assetCategory.getCategoryId()) %>" />

					<%
					List<AssetCategory> subassetCategories = AssetCategoryServiceUtil.getChildCategories(assetCategory.getCategoryId());

					for (AssetCategory subassetCategory : subassetCategories) {
					%>

						<aui:option label='<%= "&nbsp;&nbsp;&nbsp;" + subassetCategory.getTitle(themeDisplay.getLanguageId(), true) %>' selected="<%= ArrayUtil.contains(assetCategoryIds, subassetCategory.getCategoryId()) %>" value="<%= assetCategory.getCategoryId() + StringPool.COMMA + subassetCategory.getCategoryId() %>" />

				<%
					}
				}
				%>

			</aui:select>

			<aui:layout>
				<aui:column columnWidth="<%= 50 %>" first="<%= true %>">
					<aui:input cssClass="required" label="developer-website-url" name="website" />
				</aui:column>

				<aui:column columnWidth="<%= 50 %>" last="<%= true %>">
					<aui:input cssClass="required" label="support-url" name="supportWebsite" />
				</aui:column>
			</aui:layout>

			<aui:layout>
				<aui:column columnWidth="<%= 50 %>" first="<%= true %>">
					<aui:input label="documentation-url" name="documentationWebsite" />
				</aui:column>

				<aui:column columnWidth="<%= 50 %>" last="<%= true %>">
					<aui:input label="api-reference-url" name="referenceWebsite" />
				</aui:column>
			</aui:layout>

			<aui:layout>
				<aui:column columnWidth="<%= 50 %>" first="<%= true %>">
					<aui:input label="source-code-url" name="sourceCodeWebsite" />
				</aui:column>
			</aui:layout>

			<aui:layout>
				<aui:column columnWidth="<%= 50 %>" first="<%= true %>">
					<span class="aui-field-label labs">
						<liferay-ui:message key="labs" />
					</span>

					<aui:input checked="<%= labs %>" disabled="<%= (appEntry != null) && !appEntry.isFree() %>" label="denotes-an-app-as-experimental" name="labs" type="checkbox" />
				</aui:column>

				<aui:column columnWidth="<%= 50 %>" last="<%= true %>">
					<span class="aui-field-label">
						<liferay-ui:message key="security" />
					</span>

					<%
					String securityLabel = LanguageUtil.format(pageContext, "this-app-does-not-use-liferay's-pacl-security-manager", "/documentation/liferay-portal/6.1/development/-/ai/lp-6-1-dgen11-plugin-security-management-0");
					%>

					<aui:input checked="<%= !paclEnabled %>" disabled="<%= (appEntry != null) && !appEntry.isFree() && !developerEntry.isLiferayInc() %>" label="<%= securityLabel %>" name="paclDisabled" type="checkbox" />
				</aui:column>
			</aui:layout>

			<aui:fieldset>
				<aui:input cssClass="tags" name="tags" type="assetTags" />
			</aui:fieldset>

			<aui:fieldset cssClass="eula">
				<span class="aui-field-label">
					<liferay-ui:message key="eula" />
				</span>

				<%
				Object[] arguments = new Object[] {"<a class=\"default-eula display-dialog\" href=\"javascript:;\">", "</a>"};

				String taglibDefaultEULALabel = LanguageUtil.format(pageContext, "use-the-default-end-user-license-agreement", arguments);
				%>

				<aui:input checked="<%= !customEula %>" label="<%= taglibDefaultEULALabel %>" name="customEula" type="radio" value="false" />

				<%
				arguments = new Object[] {"<a class=\"display-dialog minimum-terms\" href=\"javascript:;\">", "</a>"};

				String taglibMinimumTermsLabel = LanguageUtil.format(pageContext, "provide-your-own-eula.-it-must-meet-the-minimum-terms", arguments);
				%>

				<aui:input checked="<%= customEula %>" label="<%= taglibMinimumTermsLabel %>" name="customEula" type="radio" value="true" />

				<div class="custom-eula i-17<%= customEula ? StringPool.BLANK : " aui-helper-hidden" %>" id="<portlet:namespace />customEulaContent">
					<c:choose>
						<c:when test="<%= PortletPropsValues.CONTRACT_ENTRY_LOCALIZED_ENABLED %>">
							<liferay-util:include page="/common/localized_input.jsp" servletContext="<%= application %>">
								<liferay-util:param name="defaultLanguageId" value="en_US" />
								<liferay-util:param name="label" value='<%= LanguageUtil.get(pageContext, "custom-eula") %>' />
								<liferay-util:param name="name" value="eulaContent" />
								<liferay-util:param name="required" value="<%= String.valueOf(false) %>" />
								<liferay-util:param name="value" value="<%= (contractEntry != null) ? contractEntry.getContent() : StringPool.BLANK %>" />
							</liferay-util:include>
						</c:when>
						<c:otherwise>
							<aui:input label="custom-eula" name="eulaContent" type="textarea" value="<%= (contractEntry != null) ? contractEntry.getContent(locale, true) : StringPool.BLANK %>" />
						</c:otherwise>
					</c:choose>
				</div>
			</aui:fieldset>

			<c:if test="<%= developerEntry.isLiferayInc() %>">
				<div class="liferay-only">
					<h3>
						<liferay-ui:message key="liferay-only" />
					</h3>

					<aui:fieldset>

						<%
						AppFlag appFlag = null;

						if (appVersion != null) {
							appFlag = AppFlagLocalServiceUtil.fetchAppFlag(appVersion.getAppVersionId(), AppFlagConstants.TYPE_DEPRECATED);
						}
						%>

						<aui:input label="deprecated" name="deprecated" type="checkbox" value="<%= appFlag == null ? false : true %>" />

						<aui:input label="liferay-ee-plugin" name="liferayEEPlugin" type="checkbox" value="<%= ArrayUtil.contains(assetCategoryIds, OSBConstants.ASSET_CATEGORY_EE_PLUGINS_ID) %>" />

						<aui:input checked="<%= hidden %>" label="private-app-listing" name="hidden" type="checkbox" />

						<aui:input checked="<%= portalRequired %>" label="required-portal-app" name="portalRequired" type="checkbox" />
					</aui:fieldset>

					<aui:fieldset>
						<aui:select name="productType">
							<aui:option />

							<%
							for (int i = 1; i < 3; i++) {
							%>

								<aui:option label="<%= AppVersionConstants.getProductTypeLabel(i) %>" selected="<%= i == productType %>" value="<%= i %>" />

							<%
							}
							%>

						</aui:select>

						<%
						String supersedesAppEntryIds = StringPool.BLANK;

						if (appEntry != null) {
							List<AppEntryRel> appEntryRels = AppEntryRelLocalServiceUtil.getAppEntryRels(appEntry.getAppEntryId(), AppEntryRelConstants.TYPE_SUPERSEDES);

							for (AppEntryRel appEntryRel : appEntryRels) {
								supersedesAppEntryIds = StringUtil.add(supersedesAppEntryIds, String.valueOf(appEntryRel.getAppEntryId2()));
							}
						}
						%>

						<aui:input name="supersedesAppEntryIds" type="text" value="<%= supersedesAppEntryIds %>" />

						<aui:input label="order-url" name="orderURL" />
					</aui:fieldset>

					<c:if test="<%= (appEntry != null) && (releaseType == AppVersionConstants.RELEASE_TYPE_NEW_VERSION) %>">
						<aui:fieldset>
							<aui:model-context bean="<%= null %>" model="<%= AppVersion.class %>" />

							<aui:select label="new-version-comes-after" name="versionOrder">

								<%
								List<AppVersion> appVersions = AppVersionLocalServiceUtil.getAppVersions(appEntry.getAppEntryId(), AppVersionConstants.STATUSES_APPROVED, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

								for (AppVersion curAppVersion : appVersions) {
								%>

									<aui:option label="<%= curAppVersion.getVersion() %>" value="<%= curAppVersion.getVersionOrder() + 1 %>" />

								<%
								}
								%>

							</aui:select>

							<aui:model-context bean="<%= appVersion %>" model="<%= AppVersion.class %>" />
						</aui:fieldset>
					</c:if>
				</div>
			</c:if>
		</aui:layout>

		<aui:button-row>

			<%
			PortletURL cancelURL = renderResponse.createRenderURL();

			if (appEntry == null) {
				cancelURL.setParameter("mvcPath", "/marketplace_developer_apps/view.jsp");
			}
			else {
				cancelURL.setParameter("mvcPath", "/marketplace_developer_apps/view_app_entry.jsp");
				cancelURL.setParameter("appEntryId", String.valueOf(appEntryId));
			}
			%>

			<aui:button cssClass="btn" onClick="<%= cancelURL.toString() %>" value="cancel" />

			<aui:button cssClass="btn" onClick='<%= renderResponse.getNamespace() + "saveAppEntry(true);" %>' value="save-as-draft" />

			<aui:button cssClass="btn" type="submit" value="next" />
		</aui:button-row>
	</aui:form>
</div>

<aui:script>
	function <portlet:namespace />saveAppEntry(saveAsDraft) {
		var assetAttachmentIds = [];

		AUI().all('#<portlet:namespace />fileList li.file').each(
			function(file) {
				var assetAttachmentId = file.attr('data-assetAttachmentId');

				if (assetAttachmentId > 0) {
					assetAttachmentIds.push(assetAttachmentId);
				}
			}
		);

		document.<portlet:namespace />fm.<portlet:namespace />assetAttachmentIds.value = assetAttachmentIds.join(',');

		if (saveAsDraft) {
			document.<portlet:namespace />fm.<portlet:namespace />redirect.value = "<portlet:renderURL><portlet:param name="mvcPath" value="/marketplace_developer_apps/view.jsp" /><portlet:param name="tabs1" value="apps" /></portlet:renderURL>";
		}
		else {
			<c:if test="<%= appEntry != null %>">
				document.<portlet:namespace />fm.<portlet:namespace />redirect.value = "<portlet:renderURL><portlet:param name="mvcPath" value="/marketplace_developer_apps/edit_app_version.jsp" /><portlet:param name="appEntryId" value="<%= String.valueOf(appEntryId) %>" /></portlet:renderURL>";
			</c:if>
		}

		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>

<%
Date expirationDate = new Date(System.currentTimeMillis() + GetterUtil.getLong(PropsUtil.get(PropsKeys.SESSION_TIMEOUT)) * Time.MINUTE);

Ticket ticket = TicketLocalServiceUtil.addTicket(user.getCompanyId(), User.class.getName(), user.getUserId(), TicketConstants.TYPE_IMPERSONATE, null, expirationDate, new ServiceContext());
%>

<aui:script use="attachment-upload-html5,aui-base,aui-io,sortable">
	var fileList = A.one('.marketplace-developer-apps.edit-app-entry .file-list');

	var fileSortableList = new A.Sortable(
		{
			container: fileList,
			nodes: 'li',
			opacity: .5
		}
	);

	fileList.on(
		'mouseenter',
		function() {
			fileSortableList.sync();
		}
	);

	A.one('#<portlet:namespace />screenUploader').delegate(
		'click',
		function(event) {
			var target = event.currentTarget;

			var file = target.ancestor('.file');

			A.io.request(
				'<liferay-portlet:actionURL doAsUserId="<%= user.getUserId() %>" name="deleteAppEntryMedia" />',
				{
					data: {
						<portlet:namespace />appEntryId: <%= (appEntry == null) ? 0 : appEntry.getAppEntryId() %>,
						<portlet:namespace />assetAttachmentId: file.attr('data-assetAttachmentId')
					},
					dataType: 'json',
					method: 'post',
					on: {
						success: function(event, id, obj) {
							var response = this.get('responseData');

							if (response.message == 'success') {
								file.remove(true);
							}
							else {
								var msg = response.message || '<liferay-ui:message key="an-unexpected-error-has-occurred" />';

								A.one('#<portlet:namespace />screenUploader .portlet-msg-error').setContent(msg).show();
							}
						}
					}
				}
			);
		},
		'.actions .delete'
	);

	A.all('.osb-portlet-marketplace-developer-apps .app-pricing input[type=radio]').on(
		'change',
		function(event) {
			var targetRadio = event.currentTarget;

			var labsCheckbox = A.one('#<portlet:namespace />labsCheckbox');
			var paclDisabledCheckbox = A.one('#<portlet:namespace />paclDisabledCheckbox');

			if (targetRadio.val() == '<%= AssetLicenseConstants.LICENSE_TYPE_PRODUCTION %>') {
				labsCheckbox.attr('checked', false);
				labsCheckbox.attr('disabled', true);

				<c:if test="<%= !developerEntry.isLiferayInc() %>">
					paclDisabledCheckbox.attr('checked', false);
					paclDisabledCheckbox.attr('disabled', true);
				</c:if>
			}
			else {
				labsCheckbox.removeAttribute('disabled');

				<c:if test="<%= !developerEntry.isLiferayInc() %>">
					paclDisabledCheckbox.removeAttribute('disabled');
				</c:if>
			}
		}
	);

	A.all('.osb-portlet-marketplace-developer-apps .eula input[type=radio]').on(
		'change',
		function(event) {
			var targetRadio = event.currentTarget;

			var eulaContainer = A.one('#<portlet:namespace />customEulaContent');

			if (targetRadio.val() == 'true') {
				eulaContainer.show();
			}
			else {
				eulaContainer.hide();
			}
		}
	);

	A.one('.osb-portlet-marketplace-developer-apps .eula').delegate(
		'click',
		function(event) {
			var node = event.currentTarget;

			var title = null;

			var url = null;

			if (node.hasClass('minimum-terms')) {
				title = '<liferay-ui:message key="end-user-license-agreement-minimum-terms" unicode="<%= true %>" />';

				url = '<liferay-portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/marketplace_developer_apps/view_web_content.jsp" /><portlet:param name="articleId" value="<%= String.valueOf(eulaMinimumTermsArticleId) %>" /></liferay-portlet:renderURL>';
			}
			else {
				title = '<liferay-ui:message key="default-app-eula" unicode="<%= true %>" />';

				<%
				ContractEntry eulaContractEntry = ContractEntryLocalServiceUtil.getLatestContractEntry(ContractEntryConstants.DEFAULT_CLASS_NAME_ID, ContractEntryConstants.DEFAULT_CLASS_PK, ContractEntryConstants.TYPE_APP_EULA);
				%>

				url = '<liferay-portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/marketplace_developer_apps/view_contract_entry.jsp" /><portlet:param name="contractEntryId" value="<%= String.valueOf(eulaContractEntry.getContractEntryId()) %>" /></liferay-portlet:renderURL>';
			}

			var width = 600;

			var viewport = A.getBody().get('viewportRegion');

			var dialog = new A.Dialog(
				{
					destroyOnClose: true,
					draggable: true,
					modal: true,
					height: 500,
					resizable: false,
					stack: true,
					title: title,
					width: width,
					x: document.documentElement.clientWidth/2 - width/2,
					y: viewport.top + 100
				}
			).plug(
				A.Plugin.IO,
				{
					uri: url
				}
			).render();
		},
		'.display-dialog'
	);

	A.one('#<portlet:namespace />icon').on(
		'change',
		function(event) {
			var icon = event.currentTarget.ancestor('.edit-icon').one('.icon');

			if (icon != null) {
				icon.hide();
			}
		}
	);

	var resumableUploader = new Resumable(
		{
			chunkSize: 100 * 1024 * 1024,
			simultaneousUploads: 1,
			target: '<liferay-portlet:actionURL doAsUserId="<%= user.getUserId() %>" name="addAppEntryMedia"><portlet:param name="actionName" value="addFile" /><portlet:param name="appEntryId" value="<%= String.valueOf(appEntryId) %>" /></liferay-portlet:actionURL>&ticketKey=<%= ticket.getKey() %>',
			testChunks: false,
			throttleProgressCallbacks: 1
		}
	);

	new Liferay.AttachmentUploadHTML5(
		{
			dragAndDropNode: '#<portlet:namespace />uploadArea',
			fileList: '#<portlet:namespace />fileList',
			fileType: ['gif', 'jpg', 'jpeg', 'png'],
			fileTypeErrorMessage: '<liferay-ui:message arguments='<%= new String[] {"[$FILE_NAME$]", "[$EXTENSIONS$]"} %>' key="x-is-not-a-valid-file-type.-valid-extensions-are-x" unicode="<%= true %>" />',
			fileTypeErrorMessageExtensionPlaceholder: '<%= UnicodeFormatter.toString("[$EXTENSIONS$]") %>',
			fileTypeErrorMessageFileNamePlaceholder: '<%= UnicodeFormatter.toString("[$FILE_NAME$]") %>',
			resumableUploader: resumableUploader,
			selectButton: '#<portlet:namespace />uploadArea',
			shortFileName: true
		}
	);
</aui:script>

<%!
private String _errorCss(PortletResponse renderResponse, String fieldIds) {
	StringBuilder sb = new StringBuilder();

	sb.append("<style type=\"text/css\">");

	String[] fieldIdsArray = StringUtil.split(fieldIds);

	for (int i = 0; i < fieldIdsArray.length; i++) {
		String fieldId = fieldIdsArray[i];

		if (i > 0) {
			sb.append(",");
		}

		sb.append("#");
		sb.append(renderResponse.getNamespace());
		sb.append(fieldId);
	}

	sb.append(" { color: red; } </style>");

	return sb.toString();
}
%>