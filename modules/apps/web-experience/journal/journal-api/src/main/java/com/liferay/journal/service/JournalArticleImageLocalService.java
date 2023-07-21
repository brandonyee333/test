/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.journal.model.JournalArticleImage;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for JournalArticleImage. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see JournalArticleImageLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface JournalArticleImageLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.journal.service.impl.JournalArticleImageLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the journal article image local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link JournalArticleImageLocalServiceUtil} if injection and service tracking are not available.
	 */
	public void addArticleImageId(
			long articleImageId, long groupId, String articleId, double version,
			String elInstanceId, String elName, String languageId)
		throws PortalException;

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
	@Indexable(type = IndexableType.REINDEX)
	public JournalArticleImage addJournalArticleImage(
		JournalArticleImage journalArticleImage);

	/**
	 * Creates a new journal article image with the primary key. Does not add the journal article image to the database.
	 *
	 * @param articleImageId the primary key for the new journal article image
	 * @return the new journal article image
	 */
	@Transactional(enabled = false)
	public JournalArticleImage createJournalArticleImage(long articleImageId);

	public void deleteArticleImage(JournalArticleImage articleImage);

	public void deleteArticleImage(long articleImageId);

	public void deleteArticleImage(
		long groupId, String articleId, double version, String elInstanceId,
		String elName, String languageId);

	public void deleteImages(long groupId, String articleId, double version);

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
	@Indexable(type = IndexableType.DELETE)
	public JournalArticleImage deleteJournalArticleImage(
		JournalArticleImage journalArticleImage);

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
	@Indexable(type = IndexableType.DELETE)
	public JournalArticleImage deleteJournalArticleImage(long articleImageId)
		throws PortalException;

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JournalArticleImage fetchJournalArticleImage(long articleImageId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JournalArticleImage getArticleImage(long articleImageId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long getArticleImageId(
		long groupId, String articleId, double version, String elInstanceId,
		String elName, String languageId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long getArticleImageId(
		long groupId, String articleId, double version, String elInstanceId,
		String elName, String languageId, boolean tempImage);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JournalArticleImage> getArticleImages(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JournalArticleImage> getArticleImages(
		long groupId, String articleId, double version);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getArticleImagesCount(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * Returns the journal article image with the primary key.
	 *
	 * @param articleImageId the primary key of the journal article image
	 * @return the journal article image
	 * @throws PortalException if a journal article image with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JournalArticleImage getJournalArticleImage(long articleImageId)
		throws PortalException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JournalArticleImage> getJournalArticleImages(
		int start, int end);

	/**
	 * Returns the number of journal article images.
	 *
	 * @return the number of journal article images
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getJournalArticleImagesCount();

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	/**
	 * @throws PortalException
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

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
	@Indexable(type = IndexableType.REINDEX)
	public JournalArticleImage updateJournalArticleImage(
		JournalArticleImage journalArticleImage);

}