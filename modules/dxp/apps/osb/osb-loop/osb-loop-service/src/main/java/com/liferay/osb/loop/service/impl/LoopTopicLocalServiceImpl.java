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