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

package com.liferay.osb.customer.zendesk.documentation.sync.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ZendeskSectionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ZendeskSectionLocalService
 * @generated
 */
@ProviderType
public class ZendeskSectionLocalServiceWrapper
	implements ZendeskSectionLocalService,
			   ServiceWrapper<ZendeskSectionLocalService> {

	public ZendeskSectionLocalServiceWrapper(
		ZendeskSectionLocalService zendeskSectionLocalService) {

		_zendeskSectionLocalService = zendeskSectionLocalService;
	}

	@Override
	public
		com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskSection
				addZendeskSection(
					long zendeskCategoryId, String documentationKey,
					java.util.Map<String, String> nameMap,
					java.util.Map<String, String> descriptionMap, int position)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _zendeskSectionLocalService.addZendeskSection(
			zendeskCategoryId, documentationKey, nameMap, descriptionMap,
			position);
	}

	/**
	 * Adds the zendesk section to the database. Also notifies the appropriate model listeners.
	 *
	 * @param zendeskSection the zendesk section
	 * @return the zendesk section that was added
	 */
	@Override
	public
		com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskSection
			addZendeskSection(
				com.liferay.osb.customer.zendesk.documentation.sync.model.
					ZendeskSection zendeskSection) {

		return _zendeskSectionLocalService.addZendeskSection(zendeskSection);
	}

	/**
	 * Creates a new zendesk section with the primary key. Does not add the zendesk section to the database.
	 *
	 * @param zendeskSectionId the primary key for the new zendesk section
	 * @return the new zendesk section
	 */
	@Override
	public
		com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskSection
			createZendeskSection(long zendeskSectionId) {

		return _zendeskSectionLocalService.createZendeskSection(
			zendeskSectionId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _zendeskSectionLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the zendesk section with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param zendeskSectionId the primary key of the zendesk section
	 * @return the zendesk section that was removed
	 * @throws PortalException if a zendesk section with the primary key could not be found
	 */
	@Override
	public
		com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskSection
				deleteZendeskSection(long zendeskSectionId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _zendeskSectionLocalService.deleteZendeskSection(
			zendeskSectionId);
	}

	/**
	 * Deletes the zendesk section from the database. Also notifies the appropriate model listeners.
	 *
	 * @param zendeskSection the zendesk section
	 * @return the zendesk section that was removed
	 * @throws PortalException
	 */
	@Override
	public
		com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskSection
				deleteZendeskSection(
					com.liferay.osb.customer.zendesk.documentation.sync.model.
						ZendeskSection zendeskSection)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _zendeskSectionLocalService.deleteZendeskSection(zendeskSection);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _zendeskSectionLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _zendeskSectionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskSectionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _zendeskSectionLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskSectionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _zendeskSectionLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _zendeskSectionLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _zendeskSectionLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public
		com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskSection
			fetchZendeskSection(long zendeskSectionId) {

		return _zendeskSectionLocalService.fetchZendeskSection(
			zendeskSectionId);
	}

	@Override
	public
		com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskSection
			fetchZendeskSection(
				long zendeskCategoryId, String documentationKey) {

		return _zendeskSectionLocalService.fetchZendeskSection(
			zendeskCategoryId, documentationKey);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _zendeskSectionLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _zendeskSectionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _zendeskSectionLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _zendeskSectionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the zendesk section with the primary key.
	 *
	 * @param zendeskSectionId the primary key of the zendesk section
	 * @return the zendesk section
	 * @throws PortalException if a zendesk section with the primary key could not be found
	 */
	@Override
	public
		com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskSection
				getZendeskSection(long zendeskSectionId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _zendeskSectionLocalService.getZendeskSection(zendeskSectionId);
	}

	/**
	 * Returns a range of all the zendesk sections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskSectionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of zendesk sections
	 * @param end the upper bound of the range of zendesk sections (not inclusive)
	 * @return the range of zendesk sections
	 */
	@Override
	public java.util.List
		<com.liferay.osb.customer.zendesk.documentation.sync.model.
			ZendeskSection> getZendeskSections(int start, int end) {

		return _zendeskSectionLocalService.getZendeskSections(start, end);
	}

	@Override
	public java.util.List
		<com.liferay.osb.customer.zendesk.documentation.sync.model.
			ZendeskSection> getZendeskSections(long zendeskCategoryId) {

		return _zendeskSectionLocalService.getZendeskSections(
			zendeskCategoryId);
	}

	/**
	 * Returns the number of zendesk sections.
	 *
	 * @return the number of zendesk sections
	 */
	@Override
	public int getZendeskSectionsCount() {
		return _zendeskSectionLocalService.getZendeskSectionsCount();
	}

	@Override
	public int getZendeskSectionsCount(long zendeskCategoryId) {
		return _zendeskSectionLocalService.getZendeskSectionsCount(
			zendeskCategoryId);
	}

	@Override
	public
		com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskSection
				updateZendeskSection(
					long zendeskSectionId, long zendeskCategoryId,
					String documentationKey,
					java.util.Map<String, String> nameMap,
					java.util.Map<String, String> descriptionMap, int position)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _zendeskSectionLocalService.updateZendeskSection(
			zendeskSectionId, zendeskCategoryId, documentationKey, nameMap,
			descriptionMap, position);
	}

	/**
	 * Updates the zendesk section in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param zendeskSection the zendesk section
	 * @return the zendesk section that was updated
	 */
	@Override
	public
		com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskSection
			updateZendeskSection(
				com.liferay.osb.customer.zendesk.documentation.sync.model.
					ZendeskSection zendeskSection) {

		return _zendeskSectionLocalService.updateZendeskSection(zendeskSection);
	}

	@Override
	public ZendeskSectionLocalService getWrappedService() {
		return _zendeskSectionLocalService;
	}

	@Override
	public void setWrappedService(
		ZendeskSectionLocalService zendeskSectionLocalService) {

		_zendeskSectionLocalService = zendeskSectionLocalService;
	}

	private ZendeskSectionLocalService _zendeskSectionLocalService;

}