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

package com.liferay.osb.loop.web.internal.hook.service;

import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetTagLocalService;
import com.liferay.asset.kernel.service.AssetTagLocalServiceWrapper;
import com.liferay.osb.loop.model.LoopTopic;
import com.liferay.osb.loop.service.LoopTopicLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Calvin Keum
 * @author Timothy Bell
 */
@Component(immediate = true, service = ServiceWrapper.class)
public class LoopAssetTagLocalServiceWrapper
	extends AssetTagLocalServiceWrapper {

	public LoopAssetTagLocalServiceWrapper() {
		super(null);
	}

	public LoopAssetTagLocalServiceWrapper(
		AssetTagLocalService assetTagLocalService) {

		super(assetTagLocalService);
	}

	@Override
	public AssetTag addTag(
			long userId, long groupId, String name,
			ServiceContext serviceContext)
		throws PortalException {

		LoopTopic loopTopic = _loopTopicLocalService.fetchLoopTopic(
			serviceContext.getCompanyId(), name);

		if (loopTopic == null) {
			_loopTopicLocalService.addLoopTopic(userId, name, null);
		}

		return super.addTag(userId, groupId, name, serviceContext);
	}

	@Override
	public List<AssetTag> checkTags(long userId, Group group, String[] names)
		throws PortalException {

		for (String name : names) {
			if (!_loopTopicLocalService.isLoopTopic(
					group.getCompanyId(), name)) {

				_loopTopicLocalService.addLoopTopic(
					userId, name, StringPool.BLANK);
			}
		}

		return super.checkTags(userId, group, names);
	}

	@Override
	public void deleteTag(AssetTag tag) throws PortalException {
		if (_loopTopicLocalService.isLoopTopic(
				tag.getCompanyId(), tag.getName())) {

			throw new SystemException(
				tag.getName() + " cannot be removed because it is a Loop " +
					"topic");
		}

		super.deleteTag(tag);
	}

	@Override
	public void mergeTags(long fromTagId, long toTagId) throws PortalException {
		AssetTag assetTag = getAssetTag(fromTagId);

		if (_loopTopicLocalService.isLoopTopic(
				assetTag.getCompanyId(), assetTag.getName())) {

			throw new SystemException(
				assetTag.getName() + " cannot be merged because it is a Loop " +
					"topic");
		}
	}

	@Override
	public AssetTag updateTag(
			long userId, long tagId, String name, ServiceContext serviceContext)
		throws PortalException {

		AssetTag tag = getTag(tagId);

		String oldName = tag.getName();

		name = name.trim();

		boolean loopTopic = _loopTopicLocalService.isLoopTopic(
			tag.getCompanyId(), oldName);

		if (loopTopic && !StringUtil.equalsIgnoreCase(oldName, name)) {
			throw new SystemException(
				tag.getName() + " cannot be updated because it is a Loop " +
					"topic");
		}
		else if (!loopTopic) {
			_loopTopicLocalService.addLoopTopic(userId, name, null);
		}
		else {
			_loopTopicLocalService.updateLoopTopic(userId, name, null);
		}

		return super.updateTag(userId, tagId, name, serviceContext);
	}

	@Reference
	private LoopTopicLocalService _loopTopicLocalService;

}