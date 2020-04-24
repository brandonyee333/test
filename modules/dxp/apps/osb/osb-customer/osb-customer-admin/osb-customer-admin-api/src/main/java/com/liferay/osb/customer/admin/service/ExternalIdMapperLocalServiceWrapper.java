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

package com.liferay.osb.customer.admin.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ExternalIdMapperLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ExternalIdMapperLocalService
 * @generated
 */
public class ExternalIdMapperLocalServiceWrapper
	implements ExternalIdMapperLocalService,
			   ServiceWrapper<ExternalIdMapperLocalService> {

	public ExternalIdMapperLocalServiceWrapper(
		ExternalIdMapperLocalService externalIdMapperLocalService) {

		_externalIdMapperLocalService = externalIdMapperLocalService;
	}

	/**
	 * Adds the external ID mapper to the database. Also notifies the appropriate model listeners.
	 *
	 * @param externalIdMapper the external ID mapper
	 * @return the external ID mapper that was added
	 */
	@Override
	public com.liferay.osb.customer.admin.model.ExternalIdMapper
		addExternalIdMapper(
			com.liferay.osb.customer.admin.model.ExternalIdMapper
				externalIdMapper) {

		return _externalIdMapperLocalService.addExternalIdMapper(
			externalIdMapper);
	}

	@Override
	public com.liferay.osb.customer.admin.model.ExternalIdMapper
		addExternalIdMapper(
			long classNameId, long classPK, int type, String externalId) {

		return _externalIdMapperLocalService.addExternalIdMapper(
			classNameId, classPK, type, externalId);
	}

	/**
	 * Creates a new external ID mapper with the primary key. Does not add the external ID mapper to the database.
	 *
	 * @param externalIdMapperId the primary key for the new external ID mapper
	 * @return the new external ID mapper
	 */
	@Override
	public com.liferay.osb.customer.admin.model.ExternalIdMapper
		createExternalIdMapper(long externalIdMapperId) {

		return _externalIdMapperLocalService.createExternalIdMapper(
			externalIdMapperId);
	}

	/**
	 * Deletes the external ID mapper from the database. Also notifies the appropriate model listeners.
	 *
	 * @param externalIdMapper the external ID mapper
	 * @return the external ID mapper that was removed
	 */
	@Override
	public com.liferay.osb.customer.admin.model.ExternalIdMapper
		deleteExternalIdMapper(
			com.liferay.osb.customer.admin.model.ExternalIdMapper
				externalIdMapper) {

		return _externalIdMapperLocalService.deleteExternalIdMapper(
			externalIdMapper);
	}

	/**
	 * Deletes the external ID mapper with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param externalIdMapperId the primary key of the external ID mapper
	 * @return the external ID mapper that was removed
	 * @throws PortalException if a external ID mapper with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.customer.admin.model.ExternalIdMapper
			deleteExternalIdMapper(long externalIdMapperId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _externalIdMapperLocalService.deleteExternalIdMapper(
			externalIdMapperId);
	}

	@Override
	public void deleteExternalIdMapper(
		long classNameId, long classPK, int type) {

		_externalIdMapperLocalService.deleteExternalIdMapper(
			classNameId, classPK, type);
	}

	@Override
	public void deleteExternalIdMappers(long classNameId, long classPK) {
		_externalIdMapperLocalService.deleteExternalIdMappers(
			classNameId, classPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _externalIdMapperLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _externalIdMapperLocalService.dynamicQuery();
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

		return _externalIdMapperLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.admin.model.impl.ExternalIdMapperModelImpl</code>.
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

		return _externalIdMapperLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.admin.model.impl.ExternalIdMapperModelImpl</code>.
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

		return _externalIdMapperLocalService.dynamicQuery(
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

		return _externalIdMapperLocalService.dynamicQueryCount(dynamicQuery);
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

		return _externalIdMapperLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.customer.admin.model.ExternalIdMapper
		fetchExternalIdMapper(long externalIdMapperId) {

		return _externalIdMapperLocalService.fetchExternalIdMapper(
			externalIdMapperId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _externalIdMapperLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the external ID mapper with the primary key.
	 *
	 * @param externalIdMapperId the primary key of the external ID mapper
	 * @return the external ID mapper
	 * @throws PortalException if a external ID mapper with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.customer.admin.model.ExternalIdMapper
			getExternalIdMapper(long externalIdMapperId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _externalIdMapperLocalService.getExternalIdMapper(
			externalIdMapperId);
	}

	/**
	 * Returns a range of all the external ID mappers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.admin.model.impl.ExternalIdMapperModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of external ID mappers
	 * @param end the upper bound of the range of external ID mappers (not inclusive)
	 * @return the range of external ID mappers
	 */
	@Override
	public java.util.List<com.liferay.osb.customer.admin.model.ExternalIdMapper>
		getExternalIdMappers(int start, int end) {

		return _externalIdMapperLocalService.getExternalIdMappers(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.admin.model.ExternalIdMapper>
		getExternalIdMappers(long classNameId, int type, String externalId) {

		return _externalIdMapperLocalService.getExternalIdMappers(
			classNameId, type, externalId);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.admin.model.ExternalIdMapper>
		getExternalIdMappers(long classNameId, long classPK, int type) {

		return _externalIdMapperLocalService.getExternalIdMappers(
			classNameId, classPK, type);
	}

	/**
	 * Returns the number of external ID mappers.
	 *
	 * @return the number of external ID mappers
	 */
	@Override
	public int getExternalIdMappersCount() {
		return _externalIdMapperLocalService.getExternalIdMappersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _externalIdMapperLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _externalIdMapperLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _externalIdMapperLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public boolean hasExternalIdMappers(
		long classNameId, long classPK, int type) {

		return _externalIdMapperLocalService.hasExternalIdMappers(
			classNameId, classPK, type);
	}

	/**
	 * Updates the external ID mapper in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param externalIdMapper the external ID mapper
	 * @return the external ID mapper that was updated
	 */
	@Override
	public com.liferay.osb.customer.admin.model.ExternalIdMapper
		updateExternalIdMapper(
			com.liferay.osb.customer.admin.model.ExternalIdMapper
				externalIdMapper) {

		return _externalIdMapperLocalService.updateExternalIdMapper(
			externalIdMapper);
	}

	@Override
	public com.liferay.osb.customer.admin.model.ExternalIdMapper
			updateExternalIdMapper(
				long externalIdMapperId, long classNameId, long classPK,
				int type, String externalId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _externalIdMapperLocalService.updateExternalIdMapper(
			externalIdMapperId, classNameId, classPK, type, externalId);
	}

	@Override
	public ExternalIdMapperLocalService getWrappedService() {
		return _externalIdMapperLocalService;
	}

	@Override
	public void setWrappedService(
		ExternalIdMapperLocalService externalIdMapperLocalService) {

		_externalIdMapperLocalService = externalIdMapperLocalService;
	}

	private ExternalIdMapperLocalService _externalIdMapperLocalService;

}