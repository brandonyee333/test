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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for ExternalIdMapper. This utility wraps
 * <code>com.liferay.osb.customer.admin.service.impl.ExternalIdMapperLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ExternalIdMapperLocalService
 * @generated
 */
public class ExternalIdMapperLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.customer.admin.service.impl.ExternalIdMapperLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the external ID mapper to the database. Also notifies the appropriate model listeners.
	 *
	 * @param externalIdMapper the external ID mapper
	 * @return the external ID mapper that was added
	 */
	public static com.liferay.osb.customer.admin.model.ExternalIdMapper
		addExternalIdMapper(
			com.liferay.osb.customer.admin.model.ExternalIdMapper
				externalIdMapper) {

		return getService().addExternalIdMapper(externalIdMapper);
	}

	public static com.liferay.osb.customer.admin.model.ExternalIdMapper
		addExternalIdMapper(
			long classNameId, long classPK, int type, String externalId) {

		return getService().addExternalIdMapper(
			classNameId, classPK, type, externalId);
	}

	/**
	 * Creates a new external ID mapper with the primary key. Does not add the external ID mapper to the database.
	 *
	 * @param externalIdMapperId the primary key for the new external ID mapper
	 * @return the new external ID mapper
	 */
	public static com.liferay.osb.customer.admin.model.ExternalIdMapper
		createExternalIdMapper(long externalIdMapperId) {

		return getService().createExternalIdMapper(externalIdMapperId);
	}

	/**
	 * Deletes the external ID mapper from the database. Also notifies the appropriate model listeners.
	 *
	 * @param externalIdMapper the external ID mapper
	 * @return the external ID mapper that was removed
	 */
	public static com.liferay.osb.customer.admin.model.ExternalIdMapper
		deleteExternalIdMapper(
			com.liferay.osb.customer.admin.model.ExternalIdMapper
				externalIdMapper) {

		return getService().deleteExternalIdMapper(externalIdMapper);
	}

	/**
	 * Deletes the external ID mapper with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param externalIdMapperId the primary key of the external ID mapper
	 * @return the external ID mapper that was removed
	 * @throws PortalException if a external ID mapper with the primary key could not be found
	 */
	public static com.liferay.osb.customer.admin.model.ExternalIdMapper
			deleteExternalIdMapper(long externalIdMapperId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteExternalIdMapper(externalIdMapperId);
	}

	public static void deleteExternalIdMapper(
		long classNameId, long classPK, int type) {

		getService().deleteExternalIdMapper(classNameId, classPK, type);
	}

	public static void deleteExternalIdMappers(long classNameId, long classPK) {
		getService().deleteExternalIdMappers(classNameId, classPK);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

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
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.osb.customer.admin.model.ExternalIdMapper
		fetchExternalIdMapper(long externalIdMapperId) {

		return getService().fetchExternalIdMapper(externalIdMapperId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the external ID mapper with the primary key.
	 *
	 * @param externalIdMapperId the primary key of the external ID mapper
	 * @return the external ID mapper
	 * @throws PortalException if a external ID mapper with the primary key could not be found
	 */
	public static com.liferay.osb.customer.admin.model.ExternalIdMapper
			getExternalIdMapper(long externalIdMapperId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getExternalIdMapper(externalIdMapperId);
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
	public static java.util.List
		<com.liferay.osb.customer.admin.model.ExternalIdMapper>
			getExternalIdMappers(int start, int end) {

		return getService().getExternalIdMappers(start, end);
	}

	public static java.util.List
		<com.liferay.osb.customer.admin.model.ExternalIdMapper>
			getExternalIdMappers(
				long classNameId, int type, String externalId) {

		return getService().getExternalIdMappers(classNameId, type, externalId);
	}

	public static java.util.List
		<com.liferay.osb.customer.admin.model.ExternalIdMapper>
			getExternalIdMappers(long classNameId, long classPK, int type) {

		return getService().getExternalIdMappers(classNameId, classPK, type);
	}

	/**
	 * Returns the number of external ID mappers.
	 *
	 * @return the number of external ID mappers
	 */
	public static int getExternalIdMappersCount() {
		return getService().getExternalIdMappersCount();
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
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static boolean hasExternalIdMappers(
		long classNameId, long classPK, int type) {

		return getService().hasExternalIdMappers(classNameId, classPK, type);
	}

	/**
	 * Updates the external ID mapper in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param externalIdMapper the external ID mapper
	 * @return the external ID mapper that was updated
	 */
	public static com.liferay.osb.customer.admin.model.ExternalIdMapper
		updateExternalIdMapper(
			com.liferay.osb.customer.admin.model.ExternalIdMapper
				externalIdMapper) {

		return getService().updateExternalIdMapper(externalIdMapper);
	}

	public static com.liferay.osb.customer.admin.model.ExternalIdMapper
			updateExternalIdMapper(
				long externalIdMapperId, long classNameId, long classPK,
				int type, String externalId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateExternalIdMapper(
			externalIdMapperId, classNameId, classPK, type, externalId);
	}

	public static ExternalIdMapperLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<ExternalIdMapperLocalService, ExternalIdMapperLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			ExternalIdMapperLocalService.class);

		ServiceTracker
			<ExternalIdMapperLocalService, ExternalIdMapperLocalService>
				serviceTracker =
					new ServiceTracker
						<ExternalIdMapperLocalService,
						 ExternalIdMapperLocalService>(
							 bundle.getBundleContext(),
							 ExternalIdMapperLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}