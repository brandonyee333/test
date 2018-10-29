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

package com.liferay.osb.customer.downloads.display.web.internal.portlet;

import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.UnlocalizedValue;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.storage.Fields;
import com.liferay.dynamic.data.mapping.util.DDM;
import com.liferay.exportimport.kernel.lar.ExportImportThreadLocal;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.util.JournalConverter;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.downloads.display.web.internal.constants.DDMStructureConstants;
import com.liferay.osb.customer.downloads.display.web.internal.constants.DownloadsDisplayPortletKeys;
import com.liferay.osb.customer.downloads.display.web.internal.util.DDMFieldsUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.text.DateFormat;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"com.liferay.portlet.css-class-wrapper=osb-downloads-migration-portlet",
		"com.liferay.portlet.display-category=category.hidden",
		"javax.portlet.display-name=OSB Downloads Migration",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.template-path=/migration/",
		"javax.portlet.init-param.view-template=/migration/view.jsp",
		"javax.portlet.name=" + DownloadsDisplayPortletKeys.DOWNLOADS_MIGRATION,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user"
	},
	service = Portlet.class
)
public class DownloadsMigrationPortlet extends MVCPortlet {

	public void migrateJournalArticles(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long groupId = ParamUtil.getLong(actionRequest, "groupId");
		long folderId = ParamUtil.getLong(actionRequest, "folderId");

		long classNameId = _portal.getClassNameId(JournalArticle.class);

		DDMStructure ddmStructure = _ddmStructureLocalService.getStructure(
			themeDisplay.getCompanyGroupId(), classNameId,
			DDMStructureConstants.KEY_DOWNLOAD);

		List<JournalArticle> journalArticles =
			_journalArticleLocalService.getStructureArticles(
				groupId, "KNOWLEDGE-BASE-THEME---DOWNLOAD-PATCH");

		for (JournalArticle journalArticle : journalArticles) {
			if (_log.isInfoEnabled()) {
				_log.info("Migrating article " + journalArticle.getArticleId());
			}

			migrateJournalArticle(journalArticle, ddmStructure, folderId);
		}
	}

	protected void addDDMFormFieldValue(
		DDMFormValues ddmFormValues, String name, String value) {

		DDMFormFieldValue ddmFormFieldValue = createDDMFormFieldValue(
			name, value);

		ddmFormValues.addDDMFormFieldValue(ddmFormFieldValue);
	}

	protected void addDownloadGroupDDMFormFieldValues(
		DDMFormValues ddmFormValues, String patchTitle, String downloadLink,
		String fileSize, String md5) {

		downloadLink = fixLink(downloadLink);

		DDMFormFieldValue downloadGroupDDMFormFieldValue =
			createDDMFormFieldValue("downloadGroup", StringPool.BLANK);

		DDMFormFieldValue downloadDDMFormFieldValue = createDDMFormFieldValue(
			"download", getDownloadName(patchTitle));

		downloadDDMFormFieldValue.addNestedDDMFormFieldValue(
			createDDMFormFieldValue("downloadUrl", downloadLink));
		downloadDDMFormFieldValue.addNestedDDMFormFieldValue(
			createDownloadDetailDDMFormFieldValue("File Size", fileSize));
		downloadDDMFormFieldValue.addNestedDDMFormFieldValue(
			createDownloadDetailDDMFormFieldValue("MD5", md5));

		downloadGroupDDMFormFieldValue.addNestedDDMFormFieldValue(
			downloadDDMFormFieldValue);

		ddmFormValues.addDDMFormFieldValue(downloadGroupDDMFormFieldValue);
	}

	protected void addLinkDDMFormFieldValues(
		DDMFormValues ddmFormValues, String value, String label) {

		value = fixLink(value);

		if (Validator.isNull(value)) {
			return;
		}

		DDMFormFieldValue ddmFormFieldValue = createDDMFormFieldValue(
			"link", label);

		ddmFormFieldValue.addNestedDDMFormFieldValue(
			createDDMFormFieldValue("linkUrl", value));

		ddmFormValues.addDDMFormFieldValue(ddmFormFieldValue);
	}

	protected DDMFormFieldValue createDDMFormFieldValue(
		String name, String value) {

		DDMFormFieldValue ddmFormFieldValue = new DDMFormFieldValue();

		ddmFormFieldValue.setInstanceId(StringUtil.randomString(4));
		ddmFormFieldValue.setName(name);
		ddmFormFieldValue.setValue(new UnlocalizedValue(value));

		return ddmFormFieldValue;
	}

	protected DDMFormFieldValue createDownloadDetailDDMFormFieldValue(
		String detailLabel, String detailValue) {

		DDMFormFieldValue ddmFormFieldValue = createDDMFormFieldValue(
			"downloadDetail", StringPool.BLANK);

		ddmFormFieldValue.addNestedDDMFormFieldValue(
			createDDMFormFieldValue("detailLabel", detailLabel));
		ddmFormFieldValue.addNestedDDMFormFieldValue(
			createDDMFormFieldValue("detailValue", detailValue));

		return ddmFormFieldValue;
	}

	protected String fixLink(String link) {
		if (Validator.isNull(link)) {
			return link;
		}

		if (ArrayUtil.contains(_BROKEN_LINKS, link)) {
			return StringPool.BLANK;
		}

		if (link.equals("https://www.liferay.com/security") ||
			link.equals("/security")) {

			return "https://www.liferay.com/security";
		}

		if (link.contains("-/release_notes")) {
			int pos = link.indexOf("-/release_notes");

			return "/group/customer/release-notes/" + link.substring(pos);
		}

		if (link.startsWith("http://www.liferay.com/group/customer") ||
			link.startsWith("http://www.liferay.com/documents")) {

			link = link.substring(22);
		}
		else if (link.startsWith("https://web.liferay.com/group/customer") ||
				 link.startsWith("https://web.liferay.com/documents") ||
				 link.startsWith("https://www.liferay.com/group/customer") ||
				 link.startsWith("https://www.liferay.com/documents")) {

			link = link.substring(23);
		}
		else if (link.startsWith("\"https://www.liferay.com")) {
			link = link.substring(24);
		}

		if (link.contains("/portal/all-portal")) {
			link = link.replace("portal/all-portal", StringPool.BLANK);
		}

		if (link.contains("/documents/3133562/")) {
			link.replace("/documents/3133562/", "/documents/2700986/");
		}

		return link;
	}

	protected String fixPatchDate(
		JournalArticle journalArticle, String patchDate) {

		DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd", LocaleUtil.US);

		try {
			dateFormat.parse(patchDate);

			return patchDate;
		}
		catch (Exception e) {
			String title = journalArticle.getTitle(LocaleUtil.US);

			return title.substring(0, 10);
		}
	}

	protected String getDownloadName(String patchTitle) {
		Matcher matcher = _pattern.matcher(patchTitle);

		if (matcher.find()) {
			return patchTitle.substring(matcher.end());
		}
		else if (patchTitle.startsWith("Liferay Cumulative Security")) {
			return patchTitle.substring(29);
		}
		else if (patchTitle.startsWith("Liferay Portal")) {
			return patchTitle.substring(15);
		}
		else {
			return patchTitle;
		}
	}

	protected String getFileType(String patchType, String affectedVersions) {
		if (patchType.equals("Security")) {
			return DDMStructureConstants.FILE_TYPE_SECURITY;
		}
		else if (patchType.equals("Update")) {
			return DDMStructureConstants.FILE_TYPE_FIX_PACKS;
		}
		else if (patchType.equals("Hook") || patchType.equals("Portlet") ||
				 patchType.equals("Template") || patchType.equals("Web")) {

			return DDMStructureConstants.FILE_TYPE_PLUGINS;
		}
		else {
			return DDMStructureConstants.FILE_TYPE_PRODUCT;
		}
	}

	protected String getPatchInstructions() {
		StringBundler sb = new StringBundler(5);

		sb.append("<p>For installation instructions, please refer to the ");
		sb.append("following Knowledge Base article:</p><div><p><a href=");
		sb.append("\"https://www.liferay.com/group/customer/knowledge/kb/-");
		sb.append("/knowledge_base/article/14991389\">Applying Fix Packs to ");
		sb.append("the Liferay Portal</a></p></div>");

		return sb.toString();
	}

	protected String getProduct(String patchType, String affectedVersions) {
		if (patchType.equals("Tool")) {
			return DDMStructureConstants.PRODUCT_PATCHING_TOOL;
		}

		if (affectedVersions.startsWith("5.2")) {
			return DDMStructureConstants.PRODUCT_PORTAL_52;
		}
		else if (affectedVersions.startsWith("6.0")) {
			return DDMStructureConstants.PRODUCT_PORTAL_60;
		}
		else if (affectedVersions.startsWith("6.1")) {
			return DDMStructureConstants.PRODUCT_PORTAL_61;
		}
		else if (affectedVersions.startsWith(" 6.2") ||
				 affectedVersions.startsWith("6.2")) {

			return DDMStructureConstants.PRODUCT_PORTAL_62;
		}

		return null;
	}

	protected boolean isSessionErrorException(Throwable cause) {
		_log.error(cause, cause);

		return super.isSessionErrorException(cause);
	}

	protected void migrateJournalArticle(
			JournalArticle journalArticle, DDMStructure ddmStructure,
			long folderId)
		throws Exception {

		Fields ddmFields = _journalConverter.getDDMFields(
			journalArticle.getDDMStructure(), journalArticle.getContent());

		String patchTitle = DDMFieldsUtil.getString(ddmFields, "patchTitle");
		String patchType = DDMFieldsUtil.getSelectOption(
			ddmFields, "patchType");
		String patchDate = DDMFieldsUtil.getString(ddmFields, "patchDate");
		String affectedVersions = DDMFieldsUtil.getSelectOption(
			ddmFields, "affectedVersions");
		String fileSize = DDMFieldsUtil.getString(ddmFields, "fileSize");
		String md5 = DDMFieldsUtil.getString(ddmFields, "md5");
		String downloadLink = DDMFieldsUtil.getString(
			ddmFields, "downloadLink");
		String linkLink = DDMFieldsUtil.getString(ddmFields, "linkLink");
		String learnLink = DDMFieldsUtil.getString(ddmFields, "learnLink");
		String sourceLink = DDMFieldsUtil.getString(ddmFields, "sourceLink");
		String releaseNotesLink = DDMFieldsUtil.getString(
			ddmFields, "releaseNotesLink");
		String releaseHighlightsLink = DDMFieldsUtil.getString(
			ddmFields, "releaseHighlightsLink");
		String knownIssuesLink = DDMFieldsUtil.getString(
			ddmFields, "knownIssuesLink");
		String alertContent = DDMFieldsUtil.getString(
			ddmFields, "alertContent");
		String readMoreContent = DDMFieldsUtil.getString(
			ddmFields, "readMoreContent");
		boolean displayFixpackLink = DDMFieldsUtil.getBoolean(
			ddmFields, "displayFixpackLink");
		boolean displayPatchLink = DDMFieldsUtil.getBoolean(
			ddmFields, "displayPatchLink");

		if (patchType.equals("Document")) {
			return;
		}

		if (patchType.equals("For Developers") &&
			(patchTitle.contains("API Changes") ||
			 patchTitle.contains("Documentation") ||
			 patchTitle.contains("JavaDocs"))) {

			return;
		}

		if (affectedVersions.startsWith("5.1")) {
			return;
		}

		String product = getProduct(patchType, affectedVersions);

		if (Validator.isNull(product)) {
			_log.error(
				"Unable to match product with patchType " + patchType +
					" and affectedVersions " + affectedVersions);

			return;
		}

		DDMFormValues ddmFormValues = new DDMFormValues(new DDMForm());

		ddmFormValues.addAvailableLocale(LocaleUtil.getDefault());
		ddmFormValues.setDefaultLocale(LocaleUtil.getDefault());

		if (displayFixpackLink || displayPatchLink) {
			readMoreContent += getPatchInstructions();
		}

		addDDMFormFieldValue(ddmFormValues, "additionalNotes", readMoreContent);

		addDDMFormFieldValue(ddmFormValues, "alertMessage", alertContent);

		String fileType = getFileType(patchType, affectedVersions);

		addDDMFormFieldValue(
			ddmFormValues, "fileType", "[\"" + fileType + "\"]");

		addDDMFormFieldValue(ddmFormValues, "product", "[\"" + product + "\"]");
		addDDMFormFieldValue(
			ddmFormValues, "releaseDate",
			fixPatchDate(journalArticle, patchDate));
		addDDMFormFieldValue(ddmFormValues, "requiredAgreement", "[\"\"]");

		addLinkDDMFormFieldValues(ddmFormValues, linkLink, "Link");

		addLinkDDMFormFieldValues(ddmFormValues, learnLink, "Learn More");

		addLinkDDMFormFieldValues(ddmFormValues, sourceLink, "Source");

		addLinkDDMFormFieldValues(
			ddmFormValues, releaseNotesLink, "Release Notes");

		addLinkDDMFormFieldValues(
			ddmFormValues, releaseHighlightsLink, "Release Highlights");

		addLinkDDMFormFieldValues(
			ddmFormValues, knownIssuesLink, "Known Issues");

		addDownloadGroupDDMFormFieldValues(
			ddmFormValues, patchTitle, downloadLink, fileSize, md5);

		Fields newDDMFields = _ddm.getFields(
			ddmStructure.getStructureId(), ddmFormValues);

		String content = _journalConverter.getContent(
			ddmStructure, newDDMFields);

		Map<Locale, String> titleMap = new HashMap<>();

		titleMap.put(LocaleUtil.getDefault(), patchTitle);

		Calendar cal = Calendar.getInstance();

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setScopeGroupId(journalArticle.getGroupId());

		try {
			ExportImportThreadLocal.setLayoutValidationInProcess(true);

			_journalArticleLocalService.addArticle(
				OSBCustomerConstants.USER_DEFAULT_USER_ID,
				journalArticle.getGroupId(), folderId, 0, 0, StringPool.BLANK,
				true, 1.0, titleMap, null, content,
				ddmStructure.getStructureKey(), "OSB-CUSTOMER-THEME---DOWNLOAD",
				null, cal.get(Calendar.MONTH), cal.get(Calendar.DATE),
				cal.get(Calendar.YEAR), 0, 0, 0, 0, 0, 0, 0, true, 0, 0, 0, 0,
				0, true, true, false, StringPool.BLANK, null, null,
				StringPool.BLANK, serviceContext);
		}
		finally {
			ExportImportThreadLocal.setLayoutValidationInProcess(false);
		}
	}

	private static final String[] _BROKEN_LINKS = {
		"https://www.liferay.com/documentation/liferay-portal/6.2" +
			"/release-notes",
		"https://www.liferay.com/group/customer/kbase/-/knowledge_base" +
			"/article/42430497",
		"http://www.liferay.com/group/customer/knowledge/kb/-/knowledge_base" +
			"/article/42196292"
	};

	private static final Log _log = LogFactoryUtil.getLog(
		DownloadsMigrationPortlet.class);

	private static final Pattern _pattern = Pattern.compile(
		"Liferay Portal [0-9].[0-9](.[0-9])? (EE )?(GA[0-9]* )?(SP[0-9]* )?",
		Pattern.CASE_INSENSITIVE);

	@Reference
	private DDM _ddm;

	@Reference
	private DDMStructureLocalService _ddmStructureLocalService;

	@Reference
	private JournalArticleLocalService _journalArticleLocalService;

	@Reference
	private JournalConverter _journalConverter;

	@Reference
	private Portal _portal;

}