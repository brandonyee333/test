/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.journal.service;

import com.liferay.journal.model.JournalArticleImage;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for JournalArticleImage. This utility wraps
 * <code>com.liferay.journal.service.impl.JournalArticleImageLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see JournalArticleImageLocalService
 * @generated
 */
public class JournalArticleImageLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.journal.service.impl.JournalArticleImageLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static void addArticleImageId(
			long articleImageId, long groupId, String articleId, double version,
			String elInstanceId, String elName, String languageId)
		throws PortalException {

		getService().addArticleImageId(
			articleImageId, groupId, articleId, version, elInstanceId, elName,
			languageId);
	}

	/**
	 * Adds the journal article image to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect JournalArticleImageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param journalArticleImage the journal article image
	 * @return the journal article image that was added
	 */
	public static JournalArticleImage addJournalArticleImage(
		JournalArticleImage journalArticleImage) {

		return getService().addJournalArticleImage(journalArticleImage);
	}

	/**
	 * Creates a new journal article image with the primary key. Does not add the journal article image to the database.
	 *
	 * @param articleImageId the primary key for the new journal article image
	 * @return the new journal article image
	 */
	public static JournalArticleImage createJournalArticleImage(
		long articleImageId) {

		return getService().createJournalArticleImage(articleImageId);
	}

	public static void deleteArticleImage(JournalArticleImage articleImage) {
		getService().deleteArticleImage(articleImage);
	}

	public static void deleteArticleImage(long articleImageId) {
		getService().deleteArticleImage(articleImageId);
	}

	public static void deleteArticleImage(
		long groupId, String articleId, double version, String elInstanceId,
		String elName, String languageId) {

		getService().deleteArticleImage(
			groupId, articleId, version, elInstanceId, elName, languageId);
	}

	public static void deleteImages(
		long groupId, String articleId, double version) {

		getService().deleteImages(groupId, articleId, version);
	}

	/**
	 * Deletes the journal article image from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect JournalArticleImageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param journalArticleImage the journal article image
	 * @return the journal article image that was removed
	 */
	public static JournalArticleImage deleteJournalArticleImage(
		JournalArticleImage journalArticleImage) {

		return getService().deleteJournalArticleImage(journalArticleImage);
	}

	/**
	 * Deletes the journal article image with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect JournalArticleImageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param articleImageId the primary key of the journal article image
	 * @return the journal article image that was removed
	 * @throws PortalException if a journal article image with the primary key could not be found
	 */
	public static JournalArticleImage deleteJournalArticleImage(
			long articleImageId)
		throws PortalException {

		return getService().deleteJournalArticleImage(articleImageId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.journal.model.impl.JournalArticleImageModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.journal.model.impl.JournalArticleImageModelImpl</code>.
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

	public static JournalArticleImage fetchJournalArticleImage(
		long articleImageId) {

		return getService().fetchJournalArticleImage(articleImageId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static JournalArticleImage getArticleImage(long articleImageId)
		throws PortalException {

		return getService().getArticleImage(articleImageId);
	}

	public static long getArticleImageId(
		long groupId, String articleId, double version, String elInstanceId,
		String elName, String languageId) {

		return getService().getArticleImageId(
			groupId, articleId, version, elInstanceId, elName, languageId);
	}

	public static long getArticleImageId(
		long groupId, String articleId, double version, String elInstanceId,
		String elName, String languageId, boolean tempImage) {

		return getService().getArticleImageId(
			groupId, articleId, version, elInstanceId, elName, languageId,
			tempImage);
	}

	public static List<JournalArticleImage> getArticleImages(long groupId) {
		return getService().getArticleImages(groupId);
	}

	public static List<JournalArticleImage> getArticleImages(
		long groupId, String articleId, double version) {

		return getService().getArticleImages(groupId, articleId, version);
	}

	public static int getArticleImagesCount(long groupId) {
		return getService().getArticleImagesCount(groupId);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the journal article image with the primary key.
	 *
	 * @param articleImageId the primary key of the journal article image
	 * @return the journal article image
	 * @throws PortalException if a journal article image with the primary key could not be found
	 */
	public static JournalArticleImage getJournalArticleImage(
			long articleImageId)
		throws PortalException {

		return getService().getJournalArticleImage(articleImageId);
	}

	/**
	 * Returns a range of all the journal article images.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.journal.model.impl.JournalArticleImageModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of journal article images
	 * @param end the upper bound of the range of journal article images (not inclusive)
	 * @return the range of journal article images
	 */
	public static List<JournalArticleImage> getJournalArticleImages(
		int start, int end) {

		return getService().getJournalArticleImages(start, end);
	}

	/**
	 * Returns the number of journal article images.
	 *
	 * @return the number of journal article images
	 */
	public static int getJournalArticleImagesCount() {
		return getService().getJournalArticleImagesCount();
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
	 * Updates the journal article image in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect JournalArticleImageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param journalArticleImage the journal article image
	 * @return the journal article image that was updated
	 */
	public static JournalArticleImage updateJournalArticleImage(
		JournalArticleImage journalArticleImage) {

		return getService().updateJournalArticleImage(journalArticleImage);
	}

	public static JournalArticleImageLocalService getService() {
		return _service;
	}

	public static void setService(JournalArticleImageLocalService service) {
		_service = service;
	}

	private static volatile JournalArticleImageLocalService _service;

}