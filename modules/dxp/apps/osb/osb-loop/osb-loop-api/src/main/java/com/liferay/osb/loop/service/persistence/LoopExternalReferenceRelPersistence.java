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

import com.liferay.osb.loop.exception.NoSuchLoopExternalReferenceRelException;
import com.liferay.osb.loop.model.LoopExternalReferenceRel;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the loop external reference rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopExternalReferenceRelUtil
 * @generated
 */
@ProviderType
public interface LoopExternalReferenceRelPersistence
	extends BasePersistence<LoopExternalReferenceRel> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LoopExternalReferenceRelUtil} to access the loop external reference rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, LoopExternalReferenceRel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns the loop external reference rel where externalReferenceName = &#63; and externalReferencePK = &#63; or throws a <code>NoSuchLoopExternalReferenceRelException</code> if it could not be found.
	 *
	 * @param externalReferenceName the external reference name
	 * @param externalReferencePK the external reference pk
	 * @return the matching loop external reference rel
	 * @throws NoSuchLoopExternalReferenceRelException if a matching loop external reference rel could not be found
	 */
	public LoopExternalReferenceRel findByERP_ESN(
			String externalReferenceName, String externalReferencePK)
		throws NoSuchLoopExternalReferenceRelException;

	/**
	 * Returns the loop external reference rel where externalReferenceName = &#63; and externalReferencePK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param externalReferenceName the external reference name
	 * @param externalReferencePK the external reference pk
	 * @return the matching loop external reference rel, or <code>null</code> if a matching loop external reference rel could not be found
	 */
	public LoopExternalReferenceRel fetchByERP_ESN(
		String externalReferenceName, String externalReferencePK);

	/**
	 * Returns the loop external reference rel where externalReferenceName = &#63; and externalReferencePK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param externalReferenceName the external reference name
	 * @param externalReferencePK the external reference pk
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching loop external reference rel, or <code>null</code> if a matching loop external reference rel could not be found
	 */
	public LoopExternalReferenceRel fetchByERP_ESN(
		String externalReferenceName, String externalReferencePK,
		boolean useFinderCache);

	/**
	 * Removes the loop external reference rel where externalReferenceName = &#63; and externalReferencePK = &#63; from the database.
	 *
	 * @param externalReferenceName the external reference name
	 * @param externalReferencePK the external reference pk
	 * @return the loop external reference rel that was removed
	 */
	public LoopExternalReferenceRel removeByERP_ESN(
			String externalReferenceName, String externalReferencePK)
		throws NoSuchLoopExternalReferenceRelException;

	/**
	 * Returns the number of loop external reference rels where externalReferenceName = &#63; and externalReferencePK = &#63;.
	 *
	 * @param externalReferenceName the external reference name
	 * @param externalReferencePK the external reference pk
	 * @return the number of matching loop external reference rels
	 */
	public int countByERP_ESN(
		String externalReferenceName, String externalReferencePK);

	/**
	 * Caches the loop external reference rel in the entity cache if it is enabled.
	 *
	 * @param loopExternalReferenceRel the loop external reference rel
	 */
	public void cacheResult(LoopExternalReferenceRel loopExternalReferenceRel);

	/**
	 * Caches the loop external reference rels in the entity cache if it is enabled.
	 *
	 * @param loopExternalReferenceRels the loop external reference rels
	 */
	public void cacheResult(
		java.util.List<LoopExternalReferenceRel> loopExternalReferenceRels);

	/**
	 * Creates a new loop external reference rel with the primary key. Does not add the loop external reference rel to the database.
	 *
	 * @param loopExternalReferenceRelId the primary key for the new loop external reference rel
	 * @return the new loop external reference rel
	 */
	public LoopExternalReferenceRel create(long loopExternalReferenceRelId);

	/**
	 * Removes the loop external reference rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopExternalReferenceRelId the primary key of the loop external reference rel
	 * @return the loop external reference rel that was removed
	 * @throws NoSuchLoopExternalReferenceRelException if a loop external reference rel with the primary key could not be found
	 */
	public LoopExternalReferenceRel remove(long loopExternalReferenceRelId)
		throws NoSuchLoopExternalReferenceRelException;

	public LoopExternalReferenceRel updateImpl(
		LoopExternalReferenceRel loopExternalReferenceRel);

	/**
	 * Returns the loop external reference rel with the primary key or throws a <code>NoSuchLoopExternalReferenceRelException</code> if it could not be found.
	 *
	 * @param loopExternalReferenceRelId the primary key of the loop external reference rel
	 * @return the loop external reference rel
	 * @throws NoSuchLoopExternalReferenceRelException if a loop external reference rel with the primary key could not be found
	 */
	public LoopExternalReferenceRel findByPrimaryKey(
			long loopExternalReferenceRelId)
		throws NoSuchLoopExternalReferenceRelException;

	/**
	 * Returns the loop external reference rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param loopExternalReferenceRelId the primary key of the loop external reference rel
	 * @return the loop external reference rel, or <code>null</code> if a loop external reference rel with the primary key could not be found
	 */
	public LoopExternalReferenceRel fetchByPrimaryKey(
		long loopExternalReferenceRelId);

	/**
	 * Returns all the loop external reference rels.
	 *
	 * @return the loop external reference rels
	 */
	public java.util.List<LoopExternalReferenceRel> findAll();

	/**
	 * Returns a range of all the loop external reference rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopExternalReferenceRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop external reference rels
	 * @param end the upper bound of the range of loop external reference rels (not inclusive)
	 * @return the range of loop external reference rels
	 */
	public java.util.List<LoopExternalReferenceRel> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the loop external reference rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopExternalReferenceRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop external reference rels
	 * @param end the upper bound of the range of loop external reference rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of loop external reference rels
	 */
	public java.util.List<LoopExternalReferenceRel> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<LoopExternalReferenceRel> orderByComparator);

	/**
	 * Returns an ordered range of all the loop external reference rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopExternalReferenceRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop external reference rels
	 * @param end the upper bound of the range of loop external reference rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of loop external reference rels
	 */
	public java.util.List<LoopExternalReferenceRel> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<LoopExternalReferenceRel> orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the loop external reference rels from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of loop external reference rels.
	 *
	 * @return the number of loop external reference rels
	 */
	public int countAll();

}