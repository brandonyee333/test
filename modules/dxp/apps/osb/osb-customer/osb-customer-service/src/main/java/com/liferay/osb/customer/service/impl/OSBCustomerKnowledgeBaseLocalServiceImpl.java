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

package com.liferay.osb.customer.service.impl;

import com.liferay.knowledge.base.constants.KBArticleConstants;
import com.liferay.knowledge.base.model.KBArticle;
import com.liferay.knowledge.base.service.KBArticleLocalService;
import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailService;
import com.liferay.osb.customer.configuration.OSBCustomerConfigurationValues;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.importer.KBArticleInfo;
import com.liferay.osb.customer.service.base.OSBCustomerKnowledgeBaseLocalServiceBaseImpl;
import com.liferay.petra.content.ContentUtil;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.InternetAddress;

/**
 * @author Jenny Chen
 * @author Jeremy Fu
 */
public class OSBCustomerKnowledgeBaseLocalServiceImpl
	extends OSBCustomerKnowledgeBaseLocalServiceBaseImpl {

	@Transactional(rollbackFor = {Exception.class})
	public void updateOSBKnowledgeBase(
			long kbFolderId, File file, List<String> urlTitles)
		throws Exception {

		List<KBArticle> kbArticles =
			_kbArticleLocalService.getKBFolderKBArticles(
				OSBCustomerConstants.GROUP_KNOWLEDGE_ID, kbFolderId);

		Map<String, KBArticleInfo> oldKBArticleInfoMap = new HashMap<>();

		List<Long> resourcePrimKeys = new ArrayList<>();

		for (KBArticle kbArticle : kbArticles) {
			long[] assetCategoryIds = assetCategoryLocalService.getCategoryIds(
				KBArticleConstants.getClassName(), kbArticle.getClassPK());

			String[] assetTagNames = assetTagLocalService.getTagNames(
				KBArticleConstants.getClassName(), kbArticle.getClassPK());

			KBArticleInfo kbArticleInfo = new KBArticleInfo(
				assetCategoryIds, assetTagNames);

			oldKBArticleInfoMap.put(kbArticle.getUrlTitle(), kbArticleInfo);

			resourcePrimKeys.add(kbArticle.getResourcePrimKey());
		}

		_kbArticleLocalService.deleteKBArticles(
			ArrayUtil.toLongArray(resourcePrimKeys));

		if (_log.isInfoEnabled()) {
			_log.info(
				"Deleted KB articles in " +
					StringUtil.merge(urlTitles, StringPool.SLASH));
		}

		User user = userLocalService.getUser(
			OSBCustomerConstants.USER_DEFAULT_USER_ID);

		PermissionChecker permissionChecker =
			PermissionCheckerFactoryUtil.create(user);

		PermissionThreadLocal.setPermissionChecker(permissionChecker);

		InputStream inputStream = null;

		try {
			inputStream = new FileInputStream(file);

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setGuestPermissions(new String[] {ActionKeys.VIEW});
			serviceContext.setScopeGroupId(
				OSBCustomerConstants.GROUP_KNOWLEDGE_ID);

			_kbArticleLocalService.addKBArticlesMarkdown(
				user.getUserId(), OSBCustomerConstants.GROUP_KNOWLEDGE_ID,
				kbFolderId, file.getName(), true, inputStream, serviceContext);

			if (_log.isInfoEnabled()) {
				_log.info(
					"Imported KB articles to " +
						StringUtil.merge(urlTitles, StringPool.SLASH));
			}

			List<KBArticle> newKBArticles =
				_kbArticleLocalService.getKBFolderKBArticles(
					OSBCustomerConstants.GROUP_KNOWLEDGE_ID, kbFolderId);

			List<String> newKBArticleInfoList = new ArrayList<>();

			for (KBArticle kbArticle : newKBArticles) {
				KBArticleInfo kbArticleInfo = oldKBArticleInfoMap.remove(
					kbArticle.getUrlTitle());

				if (kbArticleInfo == null) {
					newKBArticleInfoList.add(kbArticle.getUrlTitle());

					if (_log.isInfoEnabled()) {
						_log.info(
							"No categories imported for: " +
								kbArticle.getUrlTitle());
					}

					continue;
				}

				_kbArticleLocalService.updateKBArticleAsset(
					kbArticle.getUserId(), kbArticle,
					kbArticleInfo.getAssetCategoryIds(),
					kbArticleInfo.getAssetTagNames(),
					kbArticleInfo.getAssetLinkEntryIds());

				if (_log.isInfoEnabled()) {
					_log.info(
						"Successfully updated: " + kbArticle.getUrlTitle());
				}
			}

			if (_log.isInfoEnabled()) {
				for (String urlTitle : oldKBArticleInfoMap.keySet()) {
					_log.info("Article was not found: " + urlTitle);
				}
			}

			if (!oldKBArticleInfoMap.isEmpty() ||
				!newKBArticleInfoList.isEmpty()) {

				String oldKnowledgeBaseArticles = null;

				if (oldKBArticleInfoMap.isEmpty()) {
					oldKnowledgeBaseArticles = LanguageUtil.get(
						LocaleUtil.US, "no-articles-were-found");
				}
				else {
					oldKnowledgeBaseArticles = StringUtil.merge(
						oldKBArticleInfoMap.keySet(), StringPool.NEW_LINE);
				}

				String newKnowledgeBaseArticles = null;

				if (newKBArticleInfoList.isEmpty()) {
					oldKnowledgeBaseArticles = LanguageUtil.get(
						LocaleUtil.US, "no-articles-were-found");
				}
				else {
					oldKnowledgeBaseArticles = StringUtil.merge(
						newKBArticleInfoList, StringPool.NEW_LINE);
				}

				sendEmail(
					user.getCompanyMx(), oldKnowledgeBaseArticles,
					newKnowledgeBaseArticles, file.getName());
			}
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}
	}

	protected void sendEmail(
			String companyMx, String oldKnowledgeBaseArticles,
			String newKnowledgeBaseArticles, String fileName)
		throws Exception {

		InternetAddress from = new InternetAddress(
			OSBCustomerConfigurationValues.AUTO_DEPLOYER_EMAIL_FROM_ADDRESS,
			"Liferay Knowledge Base");

		InternetAddress to = new InternetAddress(
			OSBCustomerConfigurationValues.AUTO_DEPLOYER_EMAIL_TO_ADDRESS,
			OSBCustomerConfigurationValues.AUTO_DEPLOYER_EMAIL_TO_NAME);

		String subject = ContentUtil.get(
			OSBCustomerKnowledgeBaseLocalServiceImpl.class.getClassLoader(),
			"com/liferay/osb/customer/service/configuration/dependencies" +
				"/email_knowledge_base_articles_subject.tmpl");

		subject = StringUtil.replace(
			subject, "[$KNOWLEDGE_BASE_FILE_NAME$]", fileName);

		String body = ContentUtil.get(
			OSBCustomerKnowledgeBaseLocalServiceImpl.class.getClassLoader(),
			"com/liferay/osb/customer/service/configuration/dependencies" +
				"/email_knowledge_base_articles_body.tmpl");

		body = StringUtil.replace(
			body,
			new String[] {
				"[$NEW_KNOWLEDGE_BASE_ARTICLES$]",
				"[$OLD_KNOWLEDGE_BASE_ARTICLES$]", "[$TO_NAME$]"
			},
			new String[] {
				newKnowledgeBaseArticles, oldKnowledgeBaseArticles,
				OSBCustomerConfigurationValues.AUTO_DEPLOYER_EMAIL_TO_NAME
			});

		MailMessage mailMessage = new MailMessage(
			from, to, subject, body, false);

		mailMessage.setMessageId(
			PortalUtil.getMailId(
				companyMx, fileName, PortalUUIDUtil.generate()));

		mailService.sendEmail(mailMessage);
	}

	@BeanReference(type = MailService.class)
	protected MailService mailService;

	private static final Log _log = LogFactoryUtil.getLog(
		OSBCustomerKnowledgeBaseLocalServiceImpl.class);

	@ServiceReference(type = KBArticleLocalService.class)
	private KBArticleLocalService _kbArticleLocalService;

}