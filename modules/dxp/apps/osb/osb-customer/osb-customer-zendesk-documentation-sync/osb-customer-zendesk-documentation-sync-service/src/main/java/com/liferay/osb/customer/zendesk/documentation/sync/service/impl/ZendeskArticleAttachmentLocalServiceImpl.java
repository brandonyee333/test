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

package com.liferay.osb.customer.zendesk.documentation.sync.service.impl;

import com.liferay.osb.customer.zendesk.connector.constants.ZendeskRESTEndpoints;
import com.liferay.osb.customer.zendesk.connector.util.ZendeskBaseWebService;
import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle;
import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticleAttachment;
import com.liferay.osb.customer.zendesk.documentation.sync.service.base.ZendeskArticleAttachmentLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Digester;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Amos Fong
 */
public class ZendeskArticleAttachmentLocalServiceImpl
	extends ZendeskArticleAttachmentLocalServiceBaseImpl {

	public ZendeskArticleAttachment addZendeskArticleAttachment(
			long zendeskArticleId, String filePath, byte[] bytes)
		throws Exception {

		ZendeskArticle zendeskArticle =
			zendeskArticlePersistence.findByPrimaryKey(zendeskArticleId);

		JSONObject jsonObject = addRemoteZendeskArticleAttachment(
			zendeskArticle.getRemoteId(), filePath, bytes);

		JSONObject articleAttachmentJSONObject = jsonObject.getJSONObject(
			"article_attachment");

		long zendeskArticleAttachmentId = counterLocalService.increment();

		ZendeskArticleAttachment zendeskArticleAttachment =
			zendeskArticleAttachmentPersistence.create(
				zendeskArticleAttachmentId);

		zendeskArticleAttachment.setZendeskArticleId(zendeskArticleId);
		zendeskArticleAttachment.setFilePath(filePath);

		String checksum = DigesterUtil.digestHex(
			Digester.MD5, new UnsyncByteArrayInputStream(bytes));

		zendeskArticleAttachment.setChecksum(checksum);

		zendeskArticleAttachment.setRemoteId(
			articleAttachmentJSONObject.getLong("id"));
		zendeskArticleAttachment.setRemoteContentURL(
			articleAttachmentJSONObject.getString("content_url"));

		zendeskArticleAttachmentPersistence.update(zendeskArticleAttachment);

		if (_log.isInfoEnabled()) {
			_log.info(
				buildMessage("Added attachment", zendeskArticleAttachment));
		}

		return zendeskArticleAttachment;
	}

	public ZendeskArticleAttachment deleteZendeskArticleAttachment(
			ZendeskArticleAttachment zendeskArticleAttachment)
		throws PortalException {

		try {
			_zendeskBaseWebService.delete(
				ZendeskRESTEndpoints.URL_API_V2 +
					"help_center/articles/attachments/" +
						zendeskArticleAttachment.getRemoteId() + ".json",
				StringPool.BLANK);
		}
		catch (Exception e) {
			throw new PortalException(e);
		}

		zendeskArticleAttachmentPersistence.remove(zendeskArticleAttachment);

		if (_log.isInfoEnabled()) {
			_log.info(
				buildMessage("Deleted attachment", zendeskArticleAttachment));
		}

		return zendeskArticleAttachment;
	}

	public List<ZendeskArticleAttachment> getZendeskArticleAttachments(
		long zendeskArticleId) {

		return zendeskArticleAttachmentPersistence.findByZendeskArticleId(
			zendeskArticleId);
	}

	public ZendeskArticleAttachment updateZendeskArticleAttachment(
			long zendeskArticleId, String filePath, byte[] bytes)
		throws Exception {

		ZendeskArticleAttachment zendeskArticleAttachment =
			zendeskArticleAttachmentPersistence.fetchByZAI_FP(
				zendeskArticleId, filePath);

		if (zendeskArticleAttachment == null) {
			return addZendeskArticleAttachment(
				zendeskArticleId, filePath, bytes);
		}

		String checksum = DigesterUtil.digestHex(
			Digester.MD5, new UnsyncByteArrayInputStream(bytes));

		if (checksum.equals(zendeskArticleAttachment.getChecksum())) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					buildMessage(
						"Attachment not modified", zendeskArticleAttachment));
			}

			return zendeskArticleAttachment;
		}

		deleteZendeskArticleAttachment(zendeskArticleAttachment);

		return addZendeskArticleAttachment(zendeskArticleId, filePath, bytes);
	}

	protected JSONObject addRemoteZendeskArticleAttachment(
			long remoteArticleId, String filePath, byte[] bytes)
		throws Exception {

		Map<String, String> params = new HashMap<>();

		params.put("inline", Boolean.TRUE.toString());

		Path path = Paths.get(filePath);

		Path fileNamePath = path.getFileName();

		return _zendeskBaseWebService.post(
			ZendeskRESTEndpoints.URL_API_V2 + "help_center/articles/" +
				remoteArticleId + "/attachments.json",
			params, fileNamePath.toString(), bytes);
	}

	protected String buildMessage(
			String prefix, ZendeskArticleAttachment zendeskArticleAttachment)
		throws PortalException {

		StringBundler sb = new StringBundler(5);

		sb.append(prefix);
		sb.append(StringPool.SPACE);

		ZendeskArticle zendeskArticle =
			zendeskArticlePersistence.findByPrimaryKey(
				zendeskArticleAttachment.getZendeskArticleId());

		sb.append(zendeskArticle.getDocumentationKey());

		sb.append(StringPool.POUND);
		sb.append(zendeskArticleAttachment.getFilePath());

		return sb.toString();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ZendeskArticleAttachmentLocalServiceImpl.class);

	@ServiceReference(type = ZendeskBaseWebService.class)
	private ZendeskBaseWebService _zendeskBaseWebService;

}