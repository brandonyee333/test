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

package com.liferay.watson.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link WatsonReportLocalService}.
 *
 * @author Steven Smith
 * @see WatsonReportLocalService
 * @generated
 */
public class WatsonReportLocalServiceWrapper
	implements ServiceWrapper<WatsonReportLocalService>,
			   WatsonReportLocalService {

	public WatsonReportLocalServiceWrapper(
		WatsonReportLocalService watsonReportLocalService) {

		_watsonReportLocalService = watsonReportLocalService;
	}

	/**
	 * Adds the watson report to the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonReport the watson report
	 * @return the watson report that was added
	 */
	@Override
	public com.liferay.watson.model.WatsonReport addWatsonReport(
		com.liferay.watson.model.WatsonReport watsonReport) {

		return _watsonReportLocalService.addWatsonReport(watsonReport);
	}

	/**
	 * Creates a new watson report with the primary key. Does not add the watson report to the database.
	 *
	 * @param watsonReportId the primary key for the new watson report
	 * @return the new watson report
	 */
	@Override
	public com.liferay.watson.model.WatsonReport createWatsonReport(
		long watsonReportId) {

		return _watsonReportLocalService.createWatsonReport(watsonReportId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonReportLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the watson report with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonReportId the primary key of the watson report
	 * @return the watson report that was removed
	 * @throws PortalException if a watson report with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonReport deleteWatsonReport(
			long watsonReportId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonReportLocalService.deleteWatsonReport(watsonReportId);
	}

	/**
	 * Deletes the watson report from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonReport the watson report
	 * @return the watson report that was removed
	 */
	@Override
	public com.liferay.watson.model.WatsonReport deleteWatsonReport(
		com.liferay.watson.model.WatsonReport watsonReport) {

		return _watsonReportLocalService.deleteWatsonReport(watsonReport);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _watsonReportLocalService.dynamicQuery();
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

		return _watsonReportLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonReportModelImpl</code>.
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

		return _watsonReportLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonReportModelImpl</code>.
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

		return _watsonReportLocalService.dynamicQuery(
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

		return _watsonReportLocalService.dynamicQueryCount(dynamicQuery);
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

		return _watsonReportLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.watson.model.WatsonReport fetchWatsonReport(
		long watsonReportId) {

		return _watsonReportLocalService.fetchWatsonReport(watsonReportId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _watsonReportLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _watsonReportLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _watsonReportLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonReportLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the watson report with the primary key.
	 *
	 * @param watsonReportId the primary key of the watson report
	 * @return the watson report
	 * @throws PortalException if a watson report with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonReport getWatsonReport(
			long watsonReportId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonReportLocalService.getWatsonReport(watsonReportId);
	}

	/**
	 * Returns a range of all the watson reports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonReportModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson reports
	 * @param end the upper bound of the range of watson reports (not inclusive)
	 * @return the range of watson reports
	 */
	@Override
	public java.util.List<com.liferay.watson.model.WatsonReport>
		getWatsonReports(int start, int end) {

		return _watsonReportLocalService.getWatsonReports(start, end);
	}

	/**
	 * Returns the number of watson reports.
	 *
	 * @return the number of watson reports
	 */
	@Override
	public int getWatsonReportsCount() {
		return _watsonReportLocalService.getWatsonReportsCount();
	}

	/**
	 * Updates the watson report in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param watsonReport the watson report
	 * @return the watson report that was updated
	 */
	@Override
	public com.liferay.watson.model.WatsonReport updateWatsonReport(
		com.liferay.watson.model.WatsonReport watsonReport) {

		return _watsonReportLocalService.updateWatsonReport(watsonReport);
	}

	@Override
	public WatsonReportLocalService getWrappedService() {
		return _watsonReportLocalService;
	}

	@Override
	public void setWrappedService(
		WatsonReportLocalService watsonReportLocalService) {

		_watsonReportLocalService = watsonReportLocalService;
	}

	private WatsonReportLocalService _watsonReportLocalService;

}