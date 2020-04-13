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

package com.liferay.osb.community.generator.layout.helper;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryMetadataLocalService;
import com.liferay.dynamic.data.mapping.kernel.DDMFormValues;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleConstants;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.service.JournalContentSearchLocalService;
import com.liferay.journal.service.JournalFolderLocalService;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.ResourceLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.File;

import java.net.URL;

import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletPreferences;

import org.osgi.framework.Bundle;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ryan Park
 * @author Haote Chou
 */
@Component(service = LayoutWebContentHelper.class)
public class LayoutWebContentHelper {

	public void setWebContent(
			Layout layout, String portletId, String articleId, String title,
			String fileName, Bundle bundle)
		throws Exception {

		long userId = _userLocalService.getDefaultUserId(layout.getCompanyId());

		addWebContentMedia(
			layout.getCompanyId(), layout.getGroupId(), userId, fileName,
			bundle);

		String content = getContent(layout.getGroupId(), fileName, bundle);

		Locale locale = LocaleUtil.fromLanguageId(
			LocalizationUtil.getDefaultLanguageId(content));

		Map<Locale, String> titleMap = new HashMap<>();

		titleMap.put(locale, title);

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(new Date());

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setScopeGroupId(layout.getGroupId());
		serviceContext.setUserId(userId);

		JournalArticle journalArticle = _journalArticleLocalService.addArticle(
			userId, layout.getGroupId(), 0,
			JournalArticleConstants.CLASSNAME_ID_DEFAULT, 0, articleId, false,
			JournalArticleConstants.VERSION_DEFAULT, titleMap, null, content,
			_DDM_STRUCTURE_KEY, _DDM_TEMPLATE_KEY, null,
			calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE),
			calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR_OF_DAY),
			calendar.get(Calendar.MINUTE), 0, 0, 0, 0, 0, true, 0, 0, 0, 0, 0,
			true, true, false, StringPool.BLANK, new File(StringPool.BLANK),
			new HashMap<String, byte[]>(), StringPool.BLANK, serviceContext);

		_journalContentSearchLocalService.updateContentSearch(
			layout.getGroupId(), false, layout.getLayoutId(), portletId,
			journalArticle.getArticleId(), true);

		PortletPreferences portletPreferences =
			PortletPreferencesFactoryUtil.getStrictPortletSetup(
				layout, portletId);

		portletPreferences.setValue(
			"groupId", String.valueOf(journalArticle.getGroupId()));
		portletPreferences.setValue("articleId", journalArticle.getArticleId());

		portletPreferences.store();
	}

	protected void addWebContentMedia(
			long companyId, long groupId, long userId, String fileName,
			Bundle bundle)
		throws Exception {

		Enumeration<String> entryPaths = bundle.getEntryPaths(
			fileName + _MEDIA_FILE_NAME);

		if (entryPaths == null) {
			return;
		}

		while (entryPaths.hasMoreElements()) {
			File mediaFile = null;

			try {
				String entryPath = entryPaths.nextElement();

				URL url = bundle.getResource(entryPath);

				mediaFile = FileUtil.createTempFile(url.openStream());

				byte[] mediaByteArray = FileUtil.getBytes(url.openStream());
				String mediaFileName = getFileName(url.getPath());

				DLFileEntry dlFileEntry = _dlFileEntryLocalService.addFileEntry(
					userId, groupId, groupId, 0, mediaFileName,
					MimeTypesUtil.getContentType(mediaFileName), mediaFileName,
					StringPool.BLANK, StringPool.BLANK, 0,
					new HashMap<String, DDMFormValues>(), mediaFile, null,
					mediaByteArray.length, new ServiceContext());

				_resourceLocalService.addResources(
					companyId, groupId, userId, DLFileEntry.class.getName(),
					dlFileEntry.getFileEntryId(), false, false, true);
			}
			finally {
				FileUtil.delete(mediaFile);
			}
		}
	}

	protected String getContent(long groupId, String fileName, Bundle bundle)
		throws Exception {

		URL url = bundle.getResource(fileName + _CONTENT_FILE_NAME);

		String content = StringUtil.read(url.openStream());

		Enumeration<String> entryPaths = bundle.getEntryPaths(
			fileName + _MEDIA_FILE_NAME);

		if (entryPaths == null) {
			return content;
		}

		String urlPrefix = "/documents/" + groupId + "/0/";

		while (entryPaths.hasMoreElements()) {
			String mediaFileName = getFileName(entryPaths.nextElement());

			content = StringUtil.replace(
				content, mediaFileName, urlPrefix + mediaFileName);
		}

		return content;
	}

	protected String getFileName(String url) {
		int x = url.lastIndexOf(StringPool.FORWARD_SLASH);

		if (x > 0) {
			return url.substring(x + 1);
		}

		return url;
	}

	private static final String _CONTENT_FILE_NAME = "/content.xml";

	private static final String _DDM_STRUCTURE_KEY = "BASIC-WEB-CONTENT";

	private static final String _DDM_TEMPLATE_KEY = "BASIC-WEB-CONTENT";

	private static final String _MEDIA_FILE_NAME = "/media/";

	@Reference
	private DLFileEntryLocalService _dlFileEntryLocalService;

	@Reference
	private DLFileEntryMetadataLocalService _dlFileEntryMetadataLocalService;

	@Reference
	private JournalArticleLocalService _journalArticleLocalService;

	@Reference
	private JournalContentSearchLocalService _journalContentSearchLocalService;

	@Reference
	private JournalFolderLocalService _journalFolderLocalService;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private ResourceLocalService _resourceLocalService;

	@Reference
	private UserLocalService _userLocalService;

}