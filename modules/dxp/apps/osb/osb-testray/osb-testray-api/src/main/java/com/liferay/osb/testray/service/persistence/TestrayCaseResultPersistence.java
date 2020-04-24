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

package com.liferay.osb.testray.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.testray.exception.NoSuchTestrayCaseResultException;
import com.liferay.osb.testray.model.TestrayCaseResult;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the testray case result service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayCaseResultUtil
 * @generated
 */
@ProviderType
public interface TestrayCaseResultPersistence
	extends BasePersistence<TestrayCaseResult> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestrayCaseResultUtil} to access the testray case result persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, TestrayCaseResult> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the testray case results where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @return the matching testray case results
	 */
	public java.util.List<TestrayCaseResult> findByCreateDate(Date createDate);

	/**
	 * Returns a range of all the testray case results where createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @return the range of matching testray case results
	 */
	public java.util.List<TestrayCaseResult> findByCreateDate(
		Date createDate, int start, int end);

	/**
	 * Returns an ordered range of all the testray case results where createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching testray case results
	 */
	public java.util.List<TestrayCaseResult> findByCreateDate(
		Date createDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
			orderByComparator);

	/**
	 * Returns an ordered range of all the testray case results where createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching testray case results
	 */
	public java.util.List<TestrayCaseResult> findByCreateDate(
		Date createDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first testray case result in the ordered set where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching testray case result
	 * @throws NoSuchTestrayCaseResultException if a matching testray case result could not be found
	 */
	public TestrayCaseResult findByCreateDate_First(
			Date createDate,
			com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
				orderByComparator)
		throws NoSuchTestrayCaseResultException;

	/**
	 * Returns the first testray case result in the ordered set where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching testray case result, or <code>null</code> if a matching testray case result could not be found
	 */
	public TestrayCaseResult fetchByCreateDate_First(
		Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
			orderByComparator);

	/**
	 * Returns the last testray case result in the ordered set where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching testray case result
	 * @throws NoSuchTestrayCaseResultException if a matching testray case result could not be found
	 */
	public TestrayCaseResult findByCreateDate_Last(
			Date createDate,
			com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
				orderByComparator)
		throws NoSuchTestrayCaseResultException;

	/**
	 * Returns the last testray case result in the ordered set where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching testray case result, or <code>null</code> if a matching testray case result could not be found
	 */
	public TestrayCaseResult fetchByCreateDate_Last(
		Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
			orderByComparator);

	/**
	 * Returns the testray case results before and after the current testray case result in the ordered set where createDate = &#63;.
	 *
	 * @param testrayCaseResultId the primary key of the current testray case result
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next testray case result
	 * @throws NoSuchTestrayCaseResultException if a testray case result with the primary key could not be found
	 */
	public TestrayCaseResult[] findByCreateDate_PrevAndNext(
			long testrayCaseResultId, Date createDate,
			com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
				orderByComparator)
		throws NoSuchTestrayCaseResultException;

	/**
	 * Removes all the testray case results where createDate = &#63; from the database.
	 *
	 * @param createDate the create date
	 */
	public void removeByCreateDate(Date createDate);

	/**
	 * Returns the number of testray case results where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @return the number of matching testray case results
	 */
	public int countByCreateDate(Date createDate);

	/**
	 * Returns all the testray case results where testrayBuildId = &#63;.
	 *
	 * @param testrayBuildId the testray build ID
	 * @return the matching testray case results
	 */
	public java.util.List<TestrayCaseResult> findByTestrayBuildId(
		long testrayBuildId);

	/**
	 * Returns a range of all the testray case results where testrayBuildId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param testrayBuildId the testray build ID
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @return the range of matching testray case results
	 */
	public java.util.List<TestrayCaseResult> findByTestrayBuildId(
		long testrayBuildId, int start, int end);

	/**
	 * Returns an ordered range of all the testray case results where testrayBuildId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param testrayBuildId the testray build ID
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching testray case results
	 */
	public java.util.List<TestrayCaseResult> findByTestrayBuildId(
		long testrayBuildId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
			orderByComparator);

	/**
	 * Returns an ordered range of all the testray case results where testrayBuildId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param testrayBuildId the testray build ID
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching testray case results
	 */
	public java.util.List<TestrayCaseResult> findByTestrayBuildId(
		long testrayBuildId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first testray case result in the ordered set where testrayBuildId = &#63;.
	 *
	 * @param testrayBuildId the testray build ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching testray case result
	 * @throws NoSuchTestrayCaseResultException if a matching testray case result could not be found
	 */
	public TestrayCaseResult findByTestrayBuildId_First(
			long testrayBuildId,
			com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
				orderByComparator)
		throws NoSuchTestrayCaseResultException;

	/**
	 * Returns the first testray case result in the ordered set where testrayBuildId = &#63;.
	 *
	 * @param testrayBuildId the testray build ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching testray case result, or <code>null</code> if a matching testray case result could not be found
	 */
	public TestrayCaseResult fetchByTestrayBuildId_First(
		long testrayBuildId,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
			orderByComparator);

	/**
	 * Returns the last testray case result in the ordered set where testrayBuildId = &#63;.
	 *
	 * @param testrayBuildId the testray build ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching testray case result
	 * @throws NoSuchTestrayCaseResultException if a matching testray case result could not be found
	 */
	public TestrayCaseResult findByTestrayBuildId_Last(
			long testrayBuildId,
			com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
				orderByComparator)
		throws NoSuchTestrayCaseResultException;

	/**
	 * Returns the last testray case result in the ordered set where testrayBuildId = &#63;.
	 *
	 * @param testrayBuildId the testray build ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching testray case result, or <code>null</code> if a matching testray case result could not be found
	 */
	public TestrayCaseResult fetchByTestrayBuildId_Last(
		long testrayBuildId,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
			orderByComparator);

	/**
	 * Returns the testray case results before and after the current testray case result in the ordered set where testrayBuildId = &#63;.
	 *
	 * @param testrayCaseResultId the primary key of the current testray case result
	 * @param testrayBuildId the testray build ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next testray case result
	 * @throws NoSuchTestrayCaseResultException if a testray case result with the primary key could not be found
	 */
	public TestrayCaseResult[] findByTestrayBuildId_PrevAndNext(
			long testrayCaseResultId, long testrayBuildId,
			com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
				orderByComparator)
		throws NoSuchTestrayCaseResultException;

	/**
	 * Removes all the testray case results where testrayBuildId = &#63; from the database.
	 *
	 * @param testrayBuildId the testray build ID
	 */
	public void removeByTestrayBuildId(long testrayBuildId);

	/**
	 * Returns the number of testray case results where testrayBuildId = &#63;.
	 *
	 * @param testrayBuildId the testray build ID
	 * @return the number of matching testray case results
	 */
	public int countByTestrayBuildId(long testrayBuildId);

	/**
	 * Returns all the testray case results where testrayCaseId = &#63;.
	 *
	 * @param testrayCaseId the testray case ID
	 * @return the matching testray case results
	 */
	public java.util.List<TestrayCaseResult> findByTestrayCaseId(
		long testrayCaseId);

	/**
	 * Returns a range of all the testray case results where testrayCaseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param testrayCaseId the testray case ID
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @return the range of matching testray case results
	 */
	public java.util.List<TestrayCaseResult> findByTestrayCaseId(
		long testrayCaseId, int start, int end);

	/**
	 * Returns an ordered range of all the testray case results where testrayCaseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param testrayCaseId the testray case ID
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching testray case results
	 */
	public java.util.List<TestrayCaseResult> findByTestrayCaseId(
		long testrayCaseId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
			orderByComparator);

	/**
	 * Returns an ordered range of all the testray case results where testrayCaseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param testrayCaseId the testray case ID
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching testray case results
	 */
	public java.util.List<TestrayCaseResult> findByTestrayCaseId(
		long testrayCaseId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first testray case result in the ordered set where testrayCaseId = &#63;.
	 *
	 * @param testrayCaseId the testray case ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching testray case result
	 * @throws NoSuchTestrayCaseResultException if a matching testray case result could not be found
	 */
	public TestrayCaseResult findByTestrayCaseId_First(
			long testrayCaseId,
			com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
				orderByComparator)
		throws NoSuchTestrayCaseResultException;

	/**
	 * Returns the first testray case result in the ordered set where testrayCaseId = &#63;.
	 *
	 * @param testrayCaseId the testray case ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching testray case result, or <code>null</code> if a matching testray case result could not be found
	 */
	public TestrayCaseResult fetchByTestrayCaseId_First(
		long testrayCaseId,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
			orderByComparator);

	/**
	 * Returns the last testray case result in the ordered set where testrayCaseId = &#63;.
	 *
	 * @param testrayCaseId the testray case ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching testray case result
	 * @throws NoSuchTestrayCaseResultException if a matching testray case result could not be found
	 */
	public TestrayCaseResult findByTestrayCaseId_Last(
			long testrayCaseId,
			com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
				orderByComparator)
		throws NoSuchTestrayCaseResultException;

	/**
	 * Returns the last testray case result in the ordered set where testrayCaseId = &#63;.
	 *
	 * @param testrayCaseId the testray case ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching testray case result, or <code>null</code> if a matching testray case result could not be found
	 */
	public TestrayCaseResult fetchByTestrayCaseId_Last(
		long testrayCaseId,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
			orderByComparator);

	/**
	 * Returns the testray case results before and after the current testray case result in the ordered set where testrayCaseId = &#63;.
	 *
	 * @param testrayCaseResultId the primary key of the current testray case result
	 * @param testrayCaseId the testray case ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next testray case result
	 * @throws NoSuchTestrayCaseResultException if a testray case result with the primary key could not be found
	 */
	public TestrayCaseResult[] findByTestrayCaseId_PrevAndNext(
			long testrayCaseResultId, long testrayCaseId,
			com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
				orderByComparator)
		throws NoSuchTestrayCaseResultException;

	/**
	 * Removes all the testray case results where testrayCaseId = &#63; from the database.
	 *
	 * @param testrayCaseId the testray case ID
	 */
	public void removeByTestrayCaseId(long testrayCaseId);

	/**
	 * Returns the number of testray case results where testrayCaseId = &#63;.
	 *
	 * @param testrayCaseId the testray case ID
	 * @return the number of matching testray case results
	 */
	public int countByTestrayCaseId(long testrayCaseId);

	/**
	 * Returns all the testray case results where testrayComponentId = &#63;.
	 *
	 * @param testrayComponentId the testray component ID
	 * @return the matching testray case results
	 */
	public java.util.List<TestrayCaseResult> findByTestrayComponentId(
		long testrayComponentId);

	/**
	 * Returns a range of all the testray case results where testrayComponentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param testrayComponentId the testray component ID
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @return the range of matching testray case results
	 */
	public java.util.List<TestrayCaseResult> findByTestrayComponentId(
		long testrayComponentId, int start, int end);

	/**
	 * Returns an ordered range of all the testray case results where testrayComponentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param testrayComponentId the testray component ID
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching testray case results
	 */
	public java.util.List<TestrayCaseResult> findByTestrayComponentId(
		long testrayComponentId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
			orderByComparator);

	/**
	 * Returns an ordered range of all the testray case results where testrayComponentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param testrayComponentId the testray component ID
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching testray case results
	 */
	public java.util.List<TestrayCaseResult> findByTestrayComponentId(
		long testrayComponentId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first testray case result in the ordered set where testrayComponentId = &#63;.
	 *
	 * @param testrayComponentId the testray component ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching testray case result
	 * @throws NoSuchTestrayCaseResultException if a matching testray case result could not be found
	 */
	public TestrayCaseResult findByTestrayComponentId_First(
			long testrayComponentId,
			com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
				orderByComparator)
		throws NoSuchTestrayCaseResultException;

	/**
	 * Returns the first testray case result in the ordered set where testrayComponentId = &#63;.
	 *
	 * @param testrayComponentId the testray component ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching testray case result, or <code>null</code> if a matching testray case result could not be found
	 */
	public TestrayCaseResult fetchByTestrayComponentId_First(
		long testrayComponentId,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
			orderByComparator);

	/**
	 * Returns the last testray case result in the ordered set where testrayComponentId = &#63;.
	 *
	 * @param testrayComponentId the testray component ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching testray case result
	 * @throws NoSuchTestrayCaseResultException if a matching testray case result could not be found
	 */
	public TestrayCaseResult findByTestrayComponentId_Last(
			long testrayComponentId,
			com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
				orderByComparator)
		throws NoSuchTestrayCaseResultException;

	/**
	 * Returns the last testray case result in the ordered set where testrayComponentId = &#63;.
	 *
	 * @param testrayComponentId the testray component ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching testray case result, or <code>null</code> if a matching testray case result could not be found
	 */
	public TestrayCaseResult fetchByTestrayComponentId_Last(
		long testrayComponentId,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
			orderByComparator);

	/**
	 * Returns the testray case results before and after the current testray case result in the ordered set where testrayComponentId = &#63;.
	 *
	 * @param testrayCaseResultId the primary key of the current testray case result
	 * @param testrayComponentId the testray component ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next testray case result
	 * @throws NoSuchTestrayCaseResultException if a testray case result with the primary key could not be found
	 */
	public TestrayCaseResult[] findByTestrayComponentId_PrevAndNext(
			long testrayCaseResultId, long testrayComponentId,
			com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
				orderByComparator)
		throws NoSuchTestrayCaseResultException;

	/**
	 * Removes all the testray case results where testrayComponentId = &#63; from the database.
	 *
	 * @param testrayComponentId the testray component ID
	 */
	public void removeByTestrayComponentId(long testrayComponentId);

	/**
	 * Returns the number of testray case results where testrayComponentId = &#63;.
	 *
	 * @param testrayComponentId the testray component ID
	 * @return the number of matching testray case results
	 */
	public int countByTestrayComponentId(long testrayComponentId);

	/**
	 * Returns all the testray case results where testrayRunId = &#63;.
	 *
	 * @param testrayRunId the testray run ID
	 * @return the matching testray case results
	 */
	public java.util.List<TestrayCaseResult> findByTestrayRunId(
		long testrayRunId);

	/**
	 * Returns a range of all the testray case results where testrayRunId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param testrayRunId the testray run ID
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @return the range of matching testray case results
	 */
	public java.util.List<TestrayCaseResult> findByTestrayRunId(
		long testrayRunId, int start, int end);

	/**
	 * Returns an ordered range of all the testray case results where testrayRunId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param testrayRunId the testray run ID
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching testray case results
	 */
	public java.util.List<TestrayCaseResult> findByTestrayRunId(
		long testrayRunId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
			orderByComparator);

	/**
	 * Returns an ordered range of all the testray case results where testrayRunId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param testrayRunId the testray run ID
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching testray case results
	 */
	public java.util.List<TestrayCaseResult> findByTestrayRunId(
		long testrayRunId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first testray case result in the ordered set where testrayRunId = &#63;.
	 *
	 * @param testrayRunId the testray run ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching testray case result
	 * @throws NoSuchTestrayCaseResultException if a matching testray case result could not be found
	 */
	public TestrayCaseResult findByTestrayRunId_First(
			long testrayRunId,
			com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
				orderByComparator)
		throws NoSuchTestrayCaseResultException;

	/**
	 * Returns the first testray case result in the ordered set where testrayRunId = &#63;.
	 *
	 * @param testrayRunId the testray run ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching testray case result, or <code>null</code> if a matching testray case result could not be found
	 */
	public TestrayCaseResult fetchByTestrayRunId_First(
		long testrayRunId,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
			orderByComparator);

	/**
	 * Returns the last testray case result in the ordered set where testrayRunId = &#63;.
	 *
	 * @param testrayRunId the testray run ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching testray case result
	 * @throws NoSuchTestrayCaseResultException if a matching testray case result could not be found
	 */
	public TestrayCaseResult findByTestrayRunId_Last(
			long testrayRunId,
			com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
				orderByComparator)
		throws NoSuchTestrayCaseResultException;

	/**
	 * Returns the last testray case result in the ordered set where testrayRunId = &#63;.
	 *
	 * @param testrayRunId the testray run ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching testray case result, or <code>null</code> if a matching testray case result could not be found
	 */
	public TestrayCaseResult fetchByTestrayRunId_Last(
		long testrayRunId,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
			orderByComparator);

	/**
	 * Returns the testray case results before and after the current testray case result in the ordered set where testrayRunId = &#63;.
	 *
	 * @param testrayCaseResultId the primary key of the current testray case result
	 * @param testrayRunId the testray run ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next testray case result
	 * @throws NoSuchTestrayCaseResultException if a testray case result with the primary key could not be found
	 */
	public TestrayCaseResult[] findByTestrayRunId_PrevAndNext(
			long testrayCaseResultId, long testrayRunId,
			com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
				orderByComparator)
		throws NoSuchTestrayCaseResultException;

	/**
	 * Removes all the testray case results where testrayRunId = &#63; from the database.
	 *
	 * @param testrayRunId the testray run ID
	 */
	public void removeByTestrayRunId(long testrayRunId);

	/**
	 * Returns the number of testray case results where testrayRunId = &#63;.
	 *
	 * @param testrayRunId the testray run ID
	 * @return the number of matching testray case results
	 */
	public int countByTestrayRunId(long testrayRunId);

	/**
	 * Returns all the testray case results where testrayComponentId = &#63; and testrayRunId = &#63;.
	 *
	 * @param testrayComponentId the testray component ID
	 * @param testrayRunId the testray run ID
	 * @return the matching testray case results
	 */
	public java.util.List<TestrayCaseResult> findByTCI_TRI(
		long testrayComponentId, long testrayRunId);

	/**
	 * Returns a range of all the testray case results where testrayComponentId = &#63; and testrayRunId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param testrayComponentId the testray component ID
	 * @param testrayRunId the testray run ID
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @return the range of matching testray case results
	 */
	public java.util.List<TestrayCaseResult> findByTCI_TRI(
		long testrayComponentId, long testrayRunId, int start, int end);

	/**
	 * Returns an ordered range of all the testray case results where testrayComponentId = &#63; and testrayRunId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param testrayComponentId the testray component ID
	 * @param testrayRunId the testray run ID
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching testray case results
	 */
	public java.util.List<TestrayCaseResult> findByTCI_TRI(
		long testrayComponentId, long testrayRunId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
			orderByComparator);

	/**
	 * Returns an ordered range of all the testray case results where testrayComponentId = &#63; and testrayRunId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param testrayComponentId the testray component ID
	 * @param testrayRunId the testray run ID
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching testray case results
	 */
	public java.util.List<TestrayCaseResult> findByTCI_TRI(
		long testrayComponentId, long testrayRunId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first testray case result in the ordered set where testrayComponentId = &#63; and testrayRunId = &#63;.
	 *
	 * @param testrayComponentId the testray component ID
	 * @param testrayRunId the testray run ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching testray case result
	 * @throws NoSuchTestrayCaseResultException if a matching testray case result could not be found
	 */
	public TestrayCaseResult findByTCI_TRI_First(
			long testrayComponentId, long testrayRunId,
			com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
				orderByComparator)
		throws NoSuchTestrayCaseResultException;

	/**
	 * Returns the first testray case result in the ordered set where testrayComponentId = &#63; and testrayRunId = &#63;.
	 *
	 * @param testrayComponentId the testray component ID
	 * @param testrayRunId the testray run ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching testray case result, or <code>null</code> if a matching testray case result could not be found
	 */
	public TestrayCaseResult fetchByTCI_TRI_First(
		long testrayComponentId, long testrayRunId,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
			orderByComparator);

	/**
	 * Returns the last testray case result in the ordered set where testrayComponentId = &#63; and testrayRunId = &#63;.
	 *
	 * @param testrayComponentId the testray component ID
	 * @param testrayRunId the testray run ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching testray case result
	 * @throws NoSuchTestrayCaseResultException if a matching testray case result could not be found
	 */
	public TestrayCaseResult findByTCI_TRI_Last(
			long testrayComponentId, long testrayRunId,
			com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
				orderByComparator)
		throws NoSuchTestrayCaseResultException;

	/**
	 * Returns the last testray case result in the ordered set where testrayComponentId = &#63; and testrayRunId = &#63;.
	 *
	 * @param testrayComponentId the testray component ID
	 * @param testrayRunId the testray run ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching testray case result, or <code>null</code> if a matching testray case result could not be found
	 */
	public TestrayCaseResult fetchByTCI_TRI_Last(
		long testrayComponentId, long testrayRunId,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
			orderByComparator);

	/**
	 * Returns the testray case results before and after the current testray case result in the ordered set where testrayComponentId = &#63; and testrayRunId = &#63;.
	 *
	 * @param testrayCaseResultId the primary key of the current testray case result
	 * @param testrayComponentId the testray component ID
	 * @param testrayRunId the testray run ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next testray case result
	 * @throws NoSuchTestrayCaseResultException if a testray case result with the primary key could not be found
	 */
	public TestrayCaseResult[] findByTCI_TRI_PrevAndNext(
			long testrayCaseResultId, long testrayComponentId,
			long testrayRunId,
			com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
				orderByComparator)
		throws NoSuchTestrayCaseResultException;

	/**
	 * Removes all the testray case results where testrayComponentId = &#63; and testrayRunId = &#63; from the database.
	 *
	 * @param testrayComponentId the testray component ID
	 * @param testrayRunId the testray run ID
	 */
	public void removeByTCI_TRI(long testrayComponentId, long testrayRunId);

	/**
	 * Returns the number of testray case results where testrayComponentId = &#63; and testrayRunId = &#63;.
	 *
	 * @param testrayComponentId the testray component ID
	 * @param testrayRunId the testray run ID
	 * @return the number of matching testray case results
	 */
	public int countByTCI_TRI(long testrayComponentId, long testrayRunId);

	/**
	 * Returns the testray case result where testrayCaseId = &#63; and testrayRunId = &#63; or throws a <code>NoSuchTestrayCaseResultException</code> if it could not be found.
	 *
	 * @param testrayCaseId the testray case ID
	 * @param testrayRunId the testray run ID
	 * @return the matching testray case result
	 * @throws NoSuchTestrayCaseResultException if a matching testray case result could not be found
	 */
	public TestrayCaseResult findByTCaI_TRI(
			long testrayCaseId, long testrayRunId)
		throws NoSuchTestrayCaseResultException;

	/**
	 * Returns the testray case result where testrayCaseId = &#63; and testrayRunId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param testrayCaseId the testray case ID
	 * @param testrayRunId the testray run ID
	 * @return the matching testray case result, or <code>null</code> if a matching testray case result could not be found
	 */
	public TestrayCaseResult fetchByTCaI_TRI(
		long testrayCaseId, long testrayRunId);

	/**
	 * Returns the testray case result where testrayCaseId = &#63; and testrayRunId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param testrayCaseId the testray case ID
	 * @param testrayRunId the testray run ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching testray case result, or <code>null</code> if a matching testray case result could not be found
	 */
	public TestrayCaseResult fetchByTCaI_TRI(
		long testrayCaseId, long testrayRunId, boolean retrieveFromCache);

	/**
	 * Removes the testray case result where testrayCaseId = &#63; and testrayRunId = &#63; from the database.
	 *
	 * @param testrayCaseId the testray case ID
	 * @param testrayRunId the testray run ID
	 * @return the testray case result that was removed
	 */
	public TestrayCaseResult removeByTCaI_TRI(
			long testrayCaseId, long testrayRunId)
		throws NoSuchTestrayCaseResultException;

	/**
	 * Returns the number of testray case results where testrayCaseId = &#63; and testrayRunId = &#63;.
	 *
	 * @param testrayCaseId the testray case ID
	 * @param testrayRunId the testray run ID
	 * @return the number of matching testray case results
	 */
	public int countByTCaI_TRI(long testrayCaseId, long testrayRunId);

	/**
	 * Caches the testray case result in the entity cache if it is enabled.
	 *
	 * @param testrayCaseResult the testray case result
	 */
	public void cacheResult(TestrayCaseResult testrayCaseResult);

	/**
	 * Caches the testray case results in the entity cache if it is enabled.
	 *
	 * @param testrayCaseResults the testray case results
	 */
	public void cacheResult(
		java.util.List<TestrayCaseResult> testrayCaseResults);

	/**
	 * Creates a new testray case result with the primary key. Does not add the testray case result to the database.
	 *
	 * @param testrayCaseResultId the primary key for the new testray case result
	 * @return the new testray case result
	 */
	public TestrayCaseResult create(long testrayCaseResultId);

	/**
	 * Removes the testray case result with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCaseResultId the primary key of the testray case result
	 * @return the testray case result that was removed
	 * @throws NoSuchTestrayCaseResultException if a testray case result with the primary key could not be found
	 */
	public TestrayCaseResult remove(long testrayCaseResultId)
		throws NoSuchTestrayCaseResultException;

	public TestrayCaseResult updateImpl(TestrayCaseResult testrayCaseResult);

	/**
	 * Returns the testray case result with the primary key or throws a <code>NoSuchTestrayCaseResultException</code> if it could not be found.
	 *
	 * @param testrayCaseResultId the primary key of the testray case result
	 * @return the testray case result
	 * @throws NoSuchTestrayCaseResultException if a testray case result with the primary key could not be found
	 */
	public TestrayCaseResult findByPrimaryKey(long testrayCaseResultId)
		throws NoSuchTestrayCaseResultException;

	/**
	 * Returns the testray case result with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testrayCaseResultId the primary key of the testray case result
	 * @return the testray case result, or <code>null</code> if a testray case result with the primary key could not be found
	 */
	public TestrayCaseResult fetchByPrimaryKey(long testrayCaseResultId);

	/**
	 * Returns all the testray case results.
	 *
	 * @return the testray case results
	 */
	public java.util.List<TestrayCaseResult> findAll();

	/**
	 * Returns a range of all the testray case results.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @return the range of testray case results
	 */
	public java.util.List<TestrayCaseResult> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the testray case results.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray case results
	 */
	public java.util.List<TestrayCaseResult> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
			orderByComparator);

	/**
	 * Returns an ordered range of all the testray case results.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of testray case results
	 */
	public java.util.List<TestrayCaseResult> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResult>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the testray case results from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of testray case results.
	 *
	 * @return the number of testray case results
	 */
	public int countAll();

	/**
	 * Returns the primaryKeys of testray issues associated with the testray case result.
	 *
	 * @param pk the primary key of the testray case result
	 * @return long[] of the primaryKeys of testray issues associated with the testray case result
	 */
	public long[] getTestrayIssuePrimaryKeys(long pk);

	/**
	 * Returns all the testray issues associated with the testray case result.
	 *
	 * @param pk the primary key of the testray case result
	 * @return the testray issues associated with the testray case result
	 */
	public java.util.List<com.liferay.osb.testray.model.TestrayIssue>
		getTestrayIssues(long pk);

	/**
	 * Returns a range of all the testray issues associated with the testray case result.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the testray case result
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @return the range of testray issues associated with the testray case result
	 */
	public java.util.List<com.liferay.osb.testray.model.TestrayIssue>
		getTestrayIssues(long pk, int start, int end);

	/**
	 * Returns an ordered range of all the testray issues associated with the testray case result.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the testray case result
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray issues associated with the testray case result
	 */
	public java.util.List<com.liferay.osb.testray.model.TestrayIssue>
		getTestrayIssues(
			long pk, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestrayIssue> orderByComparator);

	/**
	 * Returns the number of testray issues associated with the testray case result.
	 *
	 * @param pk the primary key of the testray case result
	 * @return the number of testray issues associated with the testray case result
	 */
	public int getTestrayIssuesSize(long pk);

	/**
	 * Returns <code>true</code> if the testray issue is associated with the testray case result.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testrayIssuePK the primary key of the testray issue
	 * @return <code>true</code> if the testray issue is associated with the testray case result; <code>false</code> otherwise
	 */
	public boolean containsTestrayIssue(long pk, long testrayIssuePK);

	/**
	 * Returns <code>true</code> if the testray case result has any testray issues associated with it.
	 *
	 * @param pk the primary key of the testray case result to check for associations with testray issues
	 * @return <code>true</code> if the testray case result has any testray issues associated with it; <code>false</code> otherwise
	 */
	public boolean containsTestrayIssues(long pk);

	/**
	 * Adds an association between the testray case result and the testray issue. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testrayIssuePK the primary key of the testray issue
	 */
	public void addTestrayIssue(long pk, long testrayIssuePK);

	/**
	 * Adds an association between the testray case result and the testray issue. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testrayIssue the testray issue
	 */
	public void addTestrayIssue(
		long pk, com.liferay.osb.testray.model.TestrayIssue testrayIssue);

	/**
	 * Adds an association between the testray case result and the testray issues. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testrayIssuePKs the primary keys of the testray issues
	 */
	public void addTestrayIssues(long pk, long[] testrayIssuePKs);

	/**
	 * Adds an association between the testray case result and the testray issues. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testrayIssues the testray issues
	 */
	public void addTestrayIssues(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayIssue>
			testrayIssues);

	/**
	 * Clears all associations between the testray case result and its testray issues. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result to clear the associated testray issues from
	 */
	public void clearTestrayIssues(long pk);

	/**
	 * Removes the association between the testray case result and the testray issue. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testrayIssuePK the primary key of the testray issue
	 */
	public void removeTestrayIssue(long pk, long testrayIssuePK);

	/**
	 * Removes the association between the testray case result and the testray issue. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testrayIssue the testray issue
	 */
	public void removeTestrayIssue(
		long pk, com.liferay.osb.testray.model.TestrayIssue testrayIssue);

	/**
	 * Removes the association between the testray case result and the testray issues. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testrayIssuePKs the primary keys of the testray issues
	 */
	public void removeTestrayIssues(long pk, long[] testrayIssuePKs);

	/**
	 * Removes the association between the testray case result and the testray issues. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testrayIssues the testray issues
	 */
	public void removeTestrayIssues(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayIssue>
			testrayIssues);

	/**
	 * Sets the testray issues associated with the testray case result, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testrayIssuePKs the primary keys of the testray issues to be associated with the testray case result
	 */
	public void setTestrayIssues(long pk, long[] testrayIssuePKs);

	/**
	 * Sets the testray issues associated with the testray case result, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testrayIssues the testray issues to be associated with the testray case result
	 */
	public void setTestrayIssues(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayIssue>
			testrayIssues);

	/**
	 * Returns the primaryKeys of testray subtasks associated with the testray case result.
	 *
	 * @param pk the primary key of the testray case result
	 * @return long[] of the primaryKeys of testray subtasks associated with the testray case result
	 */
	public long[] getTestraySubtaskPrimaryKeys(long pk);

	/**
	 * Returns all the testray subtasks associated with the testray case result.
	 *
	 * @param pk the primary key of the testray case result
	 * @return the testray subtasks associated with the testray case result
	 */
	public java.util.List<com.liferay.osb.testray.model.TestraySubtask>
		getTestraySubtasks(long pk);

	/**
	 * Returns a range of all the testray subtasks associated with the testray case result.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the testray case result
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @return the range of testray subtasks associated with the testray case result
	 */
	public java.util.List<com.liferay.osb.testray.model.TestraySubtask>
		getTestraySubtasks(long pk, int start, int end);

	/**
	 * Returns an ordered range of all the testray subtasks associated with the testray case result.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the testray case result
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray subtasks associated with the testray case result
	 */
	public java.util.List<com.liferay.osb.testray.model.TestraySubtask>
		getTestraySubtasks(
			long pk, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestraySubtask>
					orderByComparator);

	/**
	 * Returns the number of testray subtasks associated with the testray case result.
	 *
	 * @param pk the primary key of the testray case result
	 * @return the number of testray subtasks associated with the testray case result
	 */
	public int getTestraySubtasksSize(long pk);

	/**
	 * Returns <code>true</code> if the testray subtask is associated with the testray case result.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testraySubtaskPK the primary key of the testray subtask
	 * @return <code>true</code> if the testray subtask is associated with the testray case result; <code>false</code> otherwise
	 */
	public boolean containsTestraySubtask(long pk, long testraySubtaskPK);

	/**
	 * Returns <code>true</code> if the testray case result has any testray subtasks associated with it.
	 *
	 * @param pk the primary key of the testray case result to check for associations with testray subtasks
	 * @return <code>true</code> if the testray case result has any testray subtasks associated with it; <code>false</code> otherwise
	 */
	public boolean containsTestraySubtasks(long pk);

	/**
	 * Adds an association between the testray case result and the testray subtask. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testraySubtaskPK the primary key of the testray subtask
	 */
	public void addTestraySubtask(long pk, long testraySubtaskPK);

	/**
	 * Adds an association between the testray case result and the testray subtask. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testraySubtask the testray subtask
	 */
	public void addTestraySubtask(
		long pk, com.liferay.osb.testray.model.TestraySubtask testraySubtask);

	/**
	 * Adds an association between the testray case result and the testray subtasks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testraySubtaskPKs the primary keys of the testray subtasks
	 */
	public void addTestraySubtasks(long pk, long[] testraySubtaskPKs);

	/**
	 * Adds an association between the testray case result and the testray subtasks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testraySubtasks the testray subtasks
	 */
	public void addTestraySubtasks(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestraySubtask>
			testraySubtasks);

	/**
	 * Clears all associations between the testray case result and its testray subtasks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result to clear the associated testray subtasks from
	 */
	public void clearTestraySubtasks(long pk);

	/**
	 * Removes the association between the testray case result and the testray subtask. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testraySubtaskPK the primary key of the testray subtask
	 */
	public void removeTestraySubtask(long pk, long testraySubtaskPK);

	/**
	 * Removes the association between the testray case result and the testray subtask. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testraySubtask the testray subtask
	 */
	public void removeTestraySubtask(
		long pk, com.liferay.osb.testray.model.TestraySubtask testraySubtask);

	/**
	 * Removes the association between the testray case result and the testray subtasks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testraySubtaskPKs the primary keys of the testray subtasks
	 */
	public void removeTestraySubtasks(long pk, long[] testraySubtaskPKs);

	/**
	 * Removes the association between the testray case result and the testray subtasks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testraySubtasks the testray subtasks
	 */
	public void removeTestraySubtasks(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestraySubtask>
			testraySubtasks);

	/**
	 * Sets the testray subtasks associated with the testray case result, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testraySubtaskPKs the primary keys of the testray subtasks to be associated with the testray case result
	 */
	public void setTestraySubtasks(long pk, long[] testraySubtaskPKs);

	/**
	 * Sets the testray subtasks associated with the testray case result, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testraySubtasks the testray subtasks to be associated with the testray case result
	 */
	public void setTestraySubtasks(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestraySubtask>
			testraySubtasks);

}