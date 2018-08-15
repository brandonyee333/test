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

package com.liferay.osb.customer.zendesk.documentation.service.impl;

import com.liferay.osb.customer.zendesk.connector.util.ZendeskHttp;
import com.liferay.osb.customer.zendesk.documentation.model.ZendeskArticle;
import com.liferay.osb.customer.zendesk.documentation.model.ZendeskArticleAttachment;
import com.liferay.osb.customer.zendesk.documentation.service.base.ZendeskArticleAttachmentLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.File;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Amos Fong
 */
public class ZendeskArticleAttachmentLocalServiceImpl
	extends ZendeskArticleAttachmentLocalServiceBaseImpl {

	public ZendeskArticleAttachment addZendeskArticleAttachment(
			long zendeskArticleId, String filePath, File file)
		throws Exception {

		ZendeskArticle zendeskArticle =
			zendeskArticlePersistence.findByPrimaryKey(zendeskArticleId);

		JSONObject jsonObject = addRemoteZendeskArticleAttachment(
			zendeskArticle.getRemoteId(), file);

		JSONObject articleAttachmentJSONObject = jsonObject.getJSONObject(
			"article_attachment");

		long zendeskArticleAttachmentId = counterLocalService.increment();

		ZendeskArticleAttachment zendeskArticleAttachment =
			zendeskArticleAttachmentPersistence.create(
				zendeskArticleAttachmentId);

		zendeskArticleAttachment.setZendeskArticleId(zendeskArticleId);
		zendeskArticleAttachment.setFilePath(filePath);

		String checksum = FileUtil.getMD5Checksum(file);

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

		_zendeskHttp.delete(
			"help_center/articles/attachments/" +
				zendeskArticleAttachment.getRemoteId() + ".json",
			JSONFactoryUtil.createJSONObject());

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
			long zendeskArticleId, String filePath, File file)
		throws Exception {

		ZendeskArticleAttachment zendeskArticleAttachment =
			zendeskArticleAttachmentPersistence.fetchByZAI_FP(
				zendeskArticleId, filePath);

		if (zendeskArticleAttachment == null) {
			return addZendeskArticleAttachment(
				zendeskArticleId, filePath, file);
		}

		String checksum = FileUtil.getMD5Checksum(file);

		if (checksum.equals(zendeskArticleAttachment.getChecksum())) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					buildMessage(
						"Attachment not modified", zendeskArticleAttachment));
			}

			return zendeskArticleAttachment;
		}

		deleteZendeskArticleAttachment(zendeskArticleAttachment);

		return addZendeskArticleAttachment(zendeskArticleId, filePath, file);
	}

	protected JSONObject addRemoteZendeskArticleAttachment(
			long remoteArticleId, File file)
		throws Exception {

		Map<String, String> params = new HashMap<>();

		params.put("inline", Boolean.TRUE.toString());

		return _zendeskHttp.post(
			"help_center/articles/" + remoteArticleId + "/attachments.json",
			params, file);
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

	@ServiceReference(type = ZendeskHttp.class)
	private ZendeskHttp _zendeskHttp;

}