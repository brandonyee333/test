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
 * Provides a wrapper for {@link SupportRegionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SupportRegionLocalService
 * @generated
 */
public class SupportRegionLocalServiceWrapper
	implements ServiceWrapper<SupportRegionLocalService>,
			   SupportRegionLocalService {

	public SupportRegionLocalServiceWrapper(
		SupportRegionLocalService supportRegionLocalService) {

		_supportRegionLocalService = supportRegionLocalService;
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SupportRegionLocalServiceUtil} to access the support region local service. Add custom service methods to <code>com.liferay.osb.customer.admin.service.impl.SupportRegionLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Override
	public void addAccountEntrySupportRegion(
		long accountEntryId, long supportRegionId) {

		_supportRegionLocalService.addAccountEntrySupportRegion(
			accountEntryId, supportRegionId);
	}

	@Override
	public void addAccountEntrySupportRegion(
		long accountEntryId,
		com.liferay.osb.customer.admin.model.SupportRegion supportRegion) {

		_supportRegionLocalService.addAccountEntrySupportRegion(
			accountEntryId, supportRegion);
	}

	@Override
	public void addAccountEntrySupportRegions(
		long accountEntryId,
		java.util.List<com.liferay.osb.customer.admin.model.SupportRegion>
			supportRegions) {

		_supportRegionLocalService.addAccountEntrySupportRegions(
			accountEntryId, supportRegions);
	}

	@Override
	public void addAccountEntrySupportRegions(
		long accountEntryId, long[] supportRegionIds) {

		_supportRegionLocalService.addAccountEntrySupportRegions(
			accountEntryId, supportRegionIds);
	}

	@Override
	public com.liferay.osb.customer.admin.model.SupportRegion addSupportRegion(
			long userId, String name, String description, String timeZoneId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _supportRegionLocalService.addSupportRegion(
			userId, name, description, timeZoneId);
	}

	/**
	 * Adds the support region to the database. Also notifies the appropriate model listeners.
	 *
	 * @param supportRegion the support region
	 * @return the support region that was added
	 */
	@Override
	public com.liferay.osb.customer.admin.model.SupportRegion addSupportRegion(
		com.liferay.osb.customer.admin.model.SupportRegion supportRegion) {

		return _supportRegionLocalService.addSupportRegion(supportRegion);
	}

	@Override
	public void clearAccountEntrySupportRegions(long accountEntryId) {
		_supportRegionLocalService.clearAccountEntrySupportRegions(
			accountEntryId);
	}

	/**
	 * Creates a new support region with the primary key. Does not add the support region to the database.
	 *
	 * @param supportRegionId the primary key for the new support region
	 * @return the new support region
	 */
	@Override
	public com.liferay.osb.customer.admin.model.SupportRegion
		createSupportRegion(long supportRegionId) {

		return _supportRegionLocalService.createSupportRegion(supportRegionId);
	}

	@Override
	public void deleteAccountEntrySupportRegion(
		long accountEntryId, long supportRegionId) {

		_supportRegionLocalService.deleteAccountEntrySupportRegion(
			accountEntryId, supportRegionId);
	}

	@Override
	public void deleteAccountEntrySupportRegion(
		long accountEntryId,
		com.liferay.osb.customer.admin.model.SupportRegion supportRegion) {

		_supportRegionLocalService.deleteAccountEntrySupportRegion(
			accountEntryId, supportRegion);
	}

	@Override
	public void deleteAccountEntrySupportRegions(
		long accountEntryId,
		java.util.List<com.liferay.osb.customer.admin.model.SupportRegion>
			supportRegions) {

		_supportRegionLocalService.deleteAccountEntrySupportRegions(
			accountEntryId, supportRegions);
	}

	@Override
	public void deleteAccountEntrySupportRegions(
		long accountEntryId, long[] supportRegionIds) {

		_supportRegionLocalService.deleteAccountEntrySupportRegions(
			accountEntryId, supportRegionIds);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _supportRegionLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the support region with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param supportRegionId the primary key of the support region
	 * @return the support region that was removed
	 * @throws PortalException if a support region with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.customer.admin.model.SupportRegion
			deleteSupportRegion(long supportRegionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _supportRegionLocalService.deleteSupportRegion(supportRegionId);
	}

	/**
	 * Deletes the support region from the database. Also notifies the appropriate model listeners.
	 *
	 * @param supportRegion the support region
	 * @return the support region that was removed
	 */
	@Override
	public com.liferay.osb.customer.admin.model.SupportRegion
		deleteSupportRegion(
			com.liferay.osb.customer.admin.model.SupportRegion supportRegion) {

		return _supportRegionLocalService.deleteSupportRegion(supportRegion);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _supportRegionLocalService.dynamicQuery();
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

		return _supportRegionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.admin.model.impl.SupportRegionModelImpl</code>.
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

		return _supportRegionLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.admin.model.impl.SupportRegionModelImpl</code>.
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

		return _supportRegionLocalService.dynamicQuery(
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

		return _supportRegionLocalService.dynamicQueryCount(dynamicQuery);
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

		return _supportRegionLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.customer.admin.model.SupportRegion
		fetchSupportRegion(long supportRegionId) {

		return _supportRegionLocalService.fetchSupportRegion(supportRegionId);
	}

	@Override
	public com.liferay.osb.customer.admin.model.SupportRegion
		fetchSupportRegionByName(String name) {

		return _supportRegionLocalService.fetchSupportRegionByName(name);
	}

	/**
	 * Returns the accountEntryIds of the account entries associated with the support region.
	 *
	 * @param supportRegionId the supportRegionId of the support region
	 * @return long[] the accountEntryIds of account entries associated with the support region
	 */
	@Override
	public long[] getAccountEntryPrimaryKeys(long supportRegionId) {
		return _supportRegionLocalService.getAccountEntryPrimaryKeys(
			supportRegionId);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.admin.model.SupportRegion>
		getAccountEntrySupportRegions(long accountEntryId) {

		return _supportRegionLocalService.getAccountEntrySupportRegions(
			accountEntryId);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.admin.model.SupportRegion>
		getAccountEntrySupportRegions(long accountEntryId, int start, int end) {

		return _supportRegionLocalService.getAccountEntrySupportRegions(
			accountEntryId, start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.admin.model.SupportRegion>
		getAccountEntrySupportRegions(
			long accountEntryId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.customer.admin.model.SupportRegion>
					orderByComparator) {

		return _supportRegionLocalService.getAccountEntrySupportRegions(
			accountEntryId, start, end, orderByComparator);
	}

	@Override
	public int getAccountEntrySupportRegionsCount(long accountEntryId) {
		return _supportRegionLocalService.getAccountEntrySupportRegionsCount(
			accountEntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _supportRegionLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _supportRegionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _supportRegionLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _supportRegionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the support region with the primary key.
	 *
	 * @param supportRegionId the primary key of the support region
	 * @return the support region
	 * @throws PortalException if a support region with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.customer.admin.model.SupportRegion getSupportRegion(
			long supportRegionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _supportRegionLocalService.getSupportRegion(supportRegionId);
	}

	/**
	 * Returns a range of all the support regions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.admin.model.impl.SupportRegionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of support regions
	 * @param end the upper bound of the range of support regions (not inclusive)
	 * @return the range of support regions
	 */
	@Override
	public java.util.List<com.liferay.osb.customer.admin.model.SupportRegion>
		getSupportRegions(int start, int end) {

		return _supportRegionLocalService.getSupportRegions(start, end);
	}

	/**
	 * Returns the number of support regions.
	 *
	 * @return the number of support regions
	 */
	@Override
	public int getSupportRegionsCount() {
		return _supportRegionLocalService.getSupportRegionsCount();
	}

	@Override
	public boolean hasAccountEntrySupportRegion(
		long accountEntryId, long supportRegionId) {

		return _supportRegionLocalService.hasAccountEntrySupportRegion(
			accountEntryId, supportRegionId);
	}

	@Override
	public boolean hasAccountEntrySupportRegions(long accountEntryId) {
		return _supportRegionLocalService.hasAccountEntrySupportRegions(
			accountEntryId);
	}

	@Override
	public void setAccountEntrySupportRegions(
		long accountEntryId, long[] supportRegionIds) {

		_supportRegionLocalService.setAccountEntrySupportRegions(
			accountEntryId, supportRegionIds);
	}

	@Override
	public com.liferay.osb.customer.admin.model.SupportRegion
			updateSupportRegion(
				long supportRegionId, String name, String description,
				String timeZoneId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _supportRegionLocalService.updateSupportRegion(
			supportRegionId, name, description, timeZoneId);
	}

	/**
	 * Updates the support region in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param supportRegion the support region
	 * @return the support region that was updated
	 */
	@Override
	public com.liferay.osb.customer.admin.model.SupportRegion
		updateSupportRegion(
			com.liferay.osb.customer.admin.model.SupportRegion supportRegion) {

		return _supportRegionLocalService.updateSupportRegion(supportRegion);
	}

	@Override
	public SupportRegionLocalService getWrappedService() {
		return _supportRegionLocalService;
	}

	@Override
	public void setWrappedService(
		SupportRegionLocalService supportRegionLocalService) {

		_supportRegionLocalService = supportRegionLocalService;
	}

	private SupportRegionLocalService _supportRegionLocalService;

}