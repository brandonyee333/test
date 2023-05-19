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

package com.liferay.osb.email.blacklist.service;

import com.liferay.osb.email.blacklist.model.BounceEntry;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for BounceEntry. This utility wraps
 * <code>com.liferay.osb.email.blacklist.service.impl.BounceEntryLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Jamie Sammons
 * @see BounceEntryLocalService
 * @generated
 */
public class BounceEntryLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.email.blacklist.service.impl.BounceEntryLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the bounce entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BounceEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param bounceEntry the bounce entry
	 * @return the bounce entry that was added
	 */
	public static BounceEntry addBounceEntry(BounceEntry bounceEntry) {
		return getService().addBounceEntry(bounceEntry);
	}

	public static BounceEntry addBounceEntry(
			String emailAddress, java.util.Date bounceDate, String bounceType,
			String bounceSubtype)
		throws PortalException {

		return getService().addBounceEntry(
			emailAddress, bounceDate, bounceType, bounceSubtype);
	}

	/**
	 * Creates a new bounce entry with the primary key. Does not add the bounce entry to the database.
	 *
	 * @param bounceEntryId the primary key for the new bounce entry
	 * @return the new bounce entry
	 */
	public static BounceEntry createBounceEntry(long bounceEntryId) {
		return getService().createBounceEntry(bounceEntryId);
	}

	/**
	 * Deletes the bounce entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BounceEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param bounceEntry the bounce entry
	 * @return the bounce entry that was removed
	 */
	public static BounceEntry deleteBounceEntry(BounceEntry bounceEntry) {
		return getService().deleteBounceEntry(bounceEntry);
	}

	/**
	 * Deletes the bounce entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BounceEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param bounceEntryId the primary key of the bounce entry
	 * @return the bounce entry that was removed
	 * @throws PortalException if a bounce entry with the primary key could not be found
	 */
	public static BounceEntry deleteBounceEntry(long bounceEntryId)
		throws PortalException {

		return getService().deleteBounceEntry(bounceEntryId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.email.blacklist.model.impl.BounceEntryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.email.blacklist.model.impl.BounceEntryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static BounceEntry fetchBounceEntry(long bounceEntryId) {
		return getService().fetchBounceEntry(bounceEntryId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static List<BounceEntry> getBounceEntries(
		java.util.Date bounceDateLT, int start, int end,
		OrderByComparator<BounceEntry> obc) {

		return getService().getBounceEntries(bounceDateLT, start, end, obc);
	}

	/**
	 * Returns a range of all the bounce entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.email.blacklist.model.impl.BounceEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of bounce entries
	 * @param end the upper bound of the range of bounce entries (not inclusive)
	 * @return the range of bounce entries
	 */
	public static List<BounceEntry> getBounceEntries(int start, int end) {
		return getService().getBounceEntries(start, end);
	}

	/**
	 * Returns the number of bounce entries.
	 *
	 * @return the number of bounce entries
	 */
	public static int getBounceEntriesCount() {
		return getService().getBounceEntriesCount();
	}

	/**
	 * Returns the bounce entry with the primary key.
	 *
	 * @param bounceEntryId the primary key of the bounce entry
	 * @return the bounce entry
	 * @throws PortalException if a bounce entry with the primary key could not be found
	 */
	public static BounceEntry getBounceEntry(long bounceEntryId)
		throws PortalException {

		return getService().getBounceEntry(bounceEntryId);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the bounce entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BounceEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param bounceEntry the bounce entry
	 * @return the bounce entry that was updated
	 */
	public static BounceEntry updateBounceEntry(BounceEntry bounceEntry) {
		return getService().updateBounceEntry(bounceEntry);
	}

	public static BounceEntryLocalService getService() {
		return _service;
	}

	public static void setService(BounceEntryLocalService service) {
		_service = service;
	}

	private static volatile BounceEntryLocalService _service;

}