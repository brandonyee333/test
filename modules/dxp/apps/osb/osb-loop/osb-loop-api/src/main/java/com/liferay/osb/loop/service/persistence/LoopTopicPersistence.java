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

package com.liferay.osb.loop.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.loop.exception.NoSuchLoopTopicException;
import com.liferay.osb.loop.model.LoopTopic;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the loop topic service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopTopicUtil
 * @generated
 */
@ProviderType
public interface LoopTopicPersistence extends BasePersistence<LoopTopic> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LoopTopicUtil} to access the loop topic persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, LoopTopic> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns the loop topic where companyId = &#63; and name = &#63; or throws a <code>NoSuchLoopTopicException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the matching loop topic
	 * @throws NoSuchLoopTopicException if a matching loop topic could not be found
	 */
	public LoopTopic findByC_N(long companyId, String name)
		throws NoSuchLoopTopicException;

	/**
	 * Returns the loop topic where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the matching loop topic, or <code>null</code> if a matching loop topic could not be found
	 */
	public LoopTopic fetchByC_N(long companyId, String name);

	/**
	 * Returns the loop topic where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching loop topic, or <code>null</code> if a matching loop topic could not be found
	 */
	public LoopTopic fetchByC_N(
		long companyId, String name, boolean useFinderCache);

	/**
	 * Removes the loop topic where companyId = &#63; and name = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the loop topic that was removed
	 */
	public LoopTopic removeByC_N(long companyId, String name)
		throws NoSuchLoopTopicException;

	/**
	 * Returns the number of loop topics where companyId = &#63; and name = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the number of matching loop topics
	 */
	public int countByC_N(long companyId, String name);

	/**
	 * Caches the loop topic in the entity cache if it is enabled.
	 *
	 * @param loopTopic the loop topic
	 */
	public void cacheResult(LoopTopic loopTopic);

	/**
	 * Caches the loop topics in the entity cache if it is enabled.
	 *
	 * @param loopTopics the loop topics
	 */
	public void cacheResult(java.util.List<LoopTopic> loopTopics);

	/**
	 * Creates a new loop topic with the primary key. Does not add the loop topic to the database.
	 *
	 * @param loopTopicId the primary key for the new loop topic
	 * @return the new loop topic
	 */
	public LoopTopic create(long loopTopicId);

	/**
	 * Removes the loop topic with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopTopicId the primary key of the loop topic
	 * @return the loop topic that was removed
	 * @throws NoSuchLoopTopicException if a loop topic with the primary key could not be found
	 */
	public LoopTopic remove(long loopTopicId) throws NoSuchLoopTopicException;

	public LoopTopic updateImpl(LoopTopic loopTopic);

	/**
	 * Returns the loop topic with the primary key or throws a <code>NoSuchLoopTopicException</code> if it could not be found.
	 *
	 * @param loopTopicId the primary key of the loop topic
	 * @return the loop topic
	 * @throws NoSuchLoopTopicException if a loop topic with the primary key could not be found
	 */
	public LoopTopic findByPrimaryKey(long loopTopicId)
		throws NoSuchLoopTopicException;

	/**
	 * Returns the loop topic with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param loopTopicId the primary key of the loop topic
	 * @return the loop topic, or <code>null</code> if a loop topic with the primary key could not be found
	 */
	public LoopTopic fetchByPrimaryKey(long loopTopicId);

	/**
	 * Returns all the loop topics.
	 *
	 * @return the loop topics
	 */
	public java.util.List<LoopTopic> findAll();

	/**
	 * Returns a range of all the loop topics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopTopicModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop topics
	 * @param end the upper bound of the range of loop topics (not inclusive)
	 * @return the range of loop topics
	 */
	public java.util.List<LoopTopic> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the loop topics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopTopicModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop topics
	 * @param end the upper bound of the range of loop topics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of loop topics
	 */
	public java.util.List<LoopTopic> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopTopic>
			orderByComparator);

	/**
	 * Returns an ordered range of all the loop topics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopTopicModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop topics
	 * @param end the upper bound of the range of loop topics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of loop topics
	 */
	public java.util.List<LoopTopic> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopTopic>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the loop topics from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of loop topics.
	 *
	 * @return the number of loop topics
	 */
	public int countAll();

}