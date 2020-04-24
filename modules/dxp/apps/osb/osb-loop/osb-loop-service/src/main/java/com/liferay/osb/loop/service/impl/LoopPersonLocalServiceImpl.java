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

package com.liferay.osb.loop.service.impl;

import com.liferay.osb.loop.asset.entry.set.constants.AssetEntrySetPortletKeys;
import com.liferay.osb.loop.constants.LoopPortletKeys;
import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.osb.loop.service.base.LoopPersonLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepository;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Ethan Bustad
 * @author Timothy Bell
 */
public class LoopPersonLocalServiceImpl extends LoopPersonLocalServiceBaseImpl {

	public LoopPerson addLoopPerson(long userId, long personUserId)
		throws PortalException {

		long loopPersonId = counterLocalService.increment();

		LoopPerson loopPerson = loopPersonLocalService.createLoopPerson(
			loopPersonId);

		if (userId <= 0) {
			userId = personUserId;
		}

		User user = userLocalService.getUser(userId);

		loopPerson.setCompanyId(user.getCompanyId());
		loopPerson.setUserId(user.getUserId());
		loopPerson.setUserName(user.getFullName());

		Date date = new Date();

		loopPerson.setCreateDate(date);
		loopPerson.setModifiedDate(date);

		loopPerson.setPersonUserId(personUserId);

		loopPerson.setImagePayload(getImagePayload());

		loopPerson = loopPersonPersistence.update(loopPerson);

		Indexer indexer = indexerRegistry.nullSafeGetIndexer(LoopPerson.class);

		indexer.reindex(loopPerson);

		User personUser = userLocalService.getUser(personUserId);

		ServiceContext serviceContext = new ServiceContext();

		portletFileRepository.addPortletRepository(
			personUser.getGroupId(), AssetEntrySetPortletKeys.ASSET_ENTRY_SET,
			serviceContext);

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		portletFileRepository.addPortletRepository(
			personUser.getGroupId(), LoopPortletKeys.LOOP, serviceContext);

		portletPreferencesLocalService.getPreferences(
			personUser.getCompanyId(), personUser.getGroupId(),
			PortletKeys.PREFS_OWNER_TYPE_GROUP, PortletKeys.PREFS_PLID_SHARED,
			PortletKeys.DOCUMENT_LIBRARY, null);

		return loopPerson;
	}

	public LoopPerson fetchLoopPersonByPersonUserId(long personUserId) {
		return loopPersonPersistence.fetchByPersonUserId(personUserId);
	}

	public LoopPerson getLoopPersonByPersonUserId(long personUserId)
		throws PortalException {

		return loopPersonPersistence.findByPersonUserId(personUserId);
	}

	public boolean hasLoopPerson(long personUserId) {
		LoopPerson loopPerson = loopPersonPersistence.fetchByPersonUserId(
			personUserId);

		if (loopPerson != null) {
			return true;
		}

		return false;
	}

	protected String getImagePayload() {
		JSONObject imagePayloadJSONObject = JSONFactoryUtil.createJSONObject();

		JSONObject defaultCoverImageJSONObject =
			JSONFactoryUtil.createJSONObject();

		ThreadLocalRandom current = ThreadLocalRandom.current();

		int offset = 1;

		defaultCoverImageJSONObject.put(
			"defaultCoverImageId",
			current.nextInt(offset, _MAX_DEFAULT_COVER_IMAGES + offset));

		imagePayloadJSONObject.put(
			"coverImagePayload", defaultCoverImageJSONObject);

		return imagePayloadJSONObject.toString();
	}

	@ServiceReference(type = IndexerRegistry.class)
	protected IndexerRegistry indexerRegistry;

	@ServiceReference(type = PortletFileRepository.class)
	protected PortletFileRepository portletFileRepository;

	private static final int _MAX_DEFAULT_COVER_IMAGES = 4;

}