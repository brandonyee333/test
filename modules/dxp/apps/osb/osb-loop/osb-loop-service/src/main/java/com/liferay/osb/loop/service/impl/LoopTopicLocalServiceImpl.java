/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.service.impl;

import com.liferay.osb.loop.model.LoopTopic;
import com.liferay.osb.loop.service.base.LoopTopicLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistry;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.Date;

/**
 * @author Ethan Bustad
 */
public class LoopTopicLocalServiceImpl extends LoopTopicLocalServiceBaseImpl {

	public LoopTopic addLoopTopic(long userId, String name, String description)
		throws PortalException {

		long loopTopicId = counterLocalService.increment();

		LoopTopic loopTopic = loopTopicPersistence.create(loopTopicId);

		User user = userLocalService.getUser(userId);

		loopTopic.setCompanyId(user.getCompanyId());
		loopTopic.setUserId(user.getUserId());
		loopTopic.setUserName(user.getFullName());

		Date now = new Date();

		loopTopic.setCreateDate(now);
		loopTopic.setModifiedDate(now);

		loopTopic.setName(name);
		loopTopic.setDescription(description);

		loopTopic = loopTopicPersistence.update(loopTopic);

		indexModel(loopTopic);

		return loopTopic;
	}

	public LoopTopic fetchLoopTopic(long companyId, String name) {
		return loopTopicPersistence.fetchByC_N(companyId, name);
	}

	public LoopTopic getLoopTopic(long companyId, String name)
		throws PortalException {

		return loopTopicPersistence.findByC_N(companyId, name);
	}

	public boolean isLoopTopic(long companyId, String name) {
		LoopTopic loopTopic = loopTopicPersistence.fetchByC_N(companyId, name);

		if (loopTopic == null) {
			return false;
		}

		return true;
	}

	public void updateLoopTopic(long userId, String name, String description)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		LoopTopic loopTopic = loopTopicPersistence.findByC_N(
			user.getCompanyId(), name);

		loopTopic.setModifiedDate(new Date());
		loopTopic.setName(name);
		loopTopic.setDescription(description);

		loopTopic = loopTopicPersistence.update(loopTopic);

		indexModel(loopTopic);
	}

	protected void indexModel(LoopTopic loopTopic) throws PortalException {
		Indexer indexer = indexerRegistry.nullSafeGetIndexer(LoopTopic.class);

		indexer.reindex(loopTopic);
	}

	@ServiceReference(type = IndexerRegistry.class)
	protected IndexerRegistry indexerRegistry;

}