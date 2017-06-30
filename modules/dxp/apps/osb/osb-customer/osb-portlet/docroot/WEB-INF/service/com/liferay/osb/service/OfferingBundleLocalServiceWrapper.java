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

package com.liferay.osb.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link OfferingBundleLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       OfferingBundleLocalService
 * @generated
 */
public class OfferingBundleLocalServiceWrapper
	implements OfferingBundleLocalService,
		ServiceWrapper<OfferingBundleLocalService> {
	public OfferingBundleLocalServiceWrapper(
		OfferingBundleLocalService offeringBundleLocalService) {
		_offeringBundleLocalService = offeringBundleLocalService;
	}

	/**
	* Adds the offering bundle to the database. Also notifies the appropriate model listeners.
	*
	* @param offeringBundle the offering bundle
	* @return the offering bundle that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingBundle addOfferingBundle(
		com.liferay.osb.model.OfferingBundle offeringBundle)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringBundleLocalService.addOfferingBundle(offeringBundle);
	}

	/**
	* Creates a new offering bundle with the primary key. Does not add the offering bundle to the database.
	*
	* @param offeringBundleId the primary key for the new offering bundle
	* @return the new offering bundle
	*/
	public com.liferay.osb.model.OfferingBundle createOfferingBundle(
		long offeringBundleId) {
		return _offeringBundleLocalService.createOfferingBundle(offeringBundleId);
	}

	/**
	* Deletes the offering bundle with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringBundleId the primary key of the offering bundle
	* @return the offering bundle that was removed
	* @throws PortalException if a offering bundle with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingBundle deleteOfferingBundle(
		long offeringBundleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _offeringBundleLocalService.deleteOfferingBundle(offeringBundleId);
	}

	/**
	* Deletes the offering bundle from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringBundle the offering bundle
	* @return the offering bundle that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingBundle deleteOfferingBundle(
		com.liferay.osb.model.OfferingBundle offeringBundle)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringBundleLocalService.deleteOfferingBundle(offeringBundle);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _offeringBundleLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringBundleLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringBundleLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringBundleLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringBundleLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.OfferingBundle fetchOfferingBundle(
		long offeringBundleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringBundleLocalService.fetchOfferingBundle(offeringBundleId);
	}

	/**
	* Returns the offering bundle with the primary key.
	*
	* @param offeringBundleId the primary key of the offering bundle
	* @return the offering bundle
	* @throws PortalException if a offering bundle with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingBundle getOfferingBundle(
		long offeringBundleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _offeringBundleLocalService.getOfferingBundle(offeringBundleId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _offeringBundleLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the offering bundles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of offering bundles
	* @param end the upper bound of the range of offering bundles (not inclusive)
	* @return the range of offering bundles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OfferingBundle> getOfferingBundles(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringBundleLocalService.getOfferingBundles(start, end);
	}

	/**
	* Returns the number of offering bundles.
	*
	* @return the number of offering bundles
	* @throws SystemException if a system exception occurred
	*/
	public int getOfferingBundlesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringBundleLocalService.getOfferingBundlesCount();
	}

	/**
	* Updates the offering bundle in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param offeringBundle the offering bundle
	* @return the offering bundle that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingBundle updateOfferingBundle(
		com.liferay.osb.model.OfferingBundle offeringBundle)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringBundleLocalService.updateOfferingBundle(offeringBundle);
	}

	/**
	* Updates the offering bundle in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param offeringBundle the offering bundle
	* @param merge whether to merge the offering bundle with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the offering bundle that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingBundle updateOfferingBundle(
		com.liferay.osb.model.OfferingBundle offeringBundle, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringBundleLocalService.updateOfferingBundle(offeringBundle,
			merge);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addOfferingDefinitionOfferingBundle(long offeringDefinitionId,
		long offeringBundleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_offeringBundleLocalService.addOfferingDefinitionOfferingBundle(offeringDefinitionId,
			offeringBundleId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addOfferingDefinitionOfferingBundle(long offeringDefinitionId,
		com.liferay.osb.model.OfferingBundle offeringBundle)
		throws com.liferay.portal.kernel.exception.SystemException {
		_offeringBundleLocalService.addOfferingDefinitionOfferingBundle(offeringDefinitionId,
			offeringBundle);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addOfferingDefinitionOfferingBundles(
		long offeringDefinitionId, long[] offeringBundleIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_offeringBundleLocalService.addOfferingDefinitionOfferingBundles(offeringDefinitionId,
			offeringBundleIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addOfferingDefinitionOfferingBundles(
		long offeringDefinitionId,
		java.util.List<com.liferay.osb.model.OfferingBundle> OfferingBundles)
		throws com.liferay.portal.kernel.exception.SystemException {
		_offeringBundleLocalService.addOfferingDefinitionOfferingBundles(offeringDefinitionId,
			OfferingBundles);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void clearOfferingDefinitionOfferingBundles(
		long offeringDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_offeringBundleLocalService.clearOfferingDefinitionOfferingBundles(offeringDefinitionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteOfferingDefinitionOfferingBundle(
		long offeringDefinitionId, long offeringBundleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_offeringBundleLocalService.deleteOfferingDefinitionOfferingBundle(offeringDefinitionId,
			offeringBundleId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteOfferingDefinitionOfferingBundle(
		long offeringDefinitionId,
		com.liferay.osb.model.OfferingBundle offeringBundle)
		throws com.liferay.portal.kernel.exception.SystemException {
		_offeringBundleLocalService.deleteOfferingDefinitionOfferingBundle(offeringDefinitionId,
			offeringBundle);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteOfferingDefinitionOfferingBundles(
		long offeringDefinitionId, long[] offeringBundleIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_offeringBundleLocalService.deleteOfferingDefinitionOfferingBundles(offeringDefinitionId,
			offeringBundleIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteOfferingDefinitionOfferingBundles(
		long offeringDefinitionId,
		java.util.List<com.liferay.osb.model.OfferingBundle> OfferingBundles)
		throws com.liferay.portal.kernel.exception.SystemException {
		_offeringBundleLocalService.deleteOfferingDefinitionOfferingBundles(offeringDefinitionId,
			OfferingBundles);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OfferingBundle> getOfferingDefinitionOfferingBundles(
		long offeringDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringBundleLocalService.getOfferingDefinitionOfferingBundles(offeringDefinitionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OfferingBundle> getOfferingDefinitionOfferingBundles(
		long offeringDefinitionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringBundleLocalService.getOfferingDefinitionOfferingBundles(offeringDefinitionId,
			start, end);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OfferingBundle> getOfferingDefinitionOfferingBundles(
		long offeringDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringBundleLocalService.getOfferingDefinitionOfferingBundles(offeringDefinitionId,
			start, end, orderByComparator);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public int getOfferingDefinitionOfferingBundlesCount(
		long offeringDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringBundleLocalService.getOfferingDefinitionOfferingBundlesCount(offeringDefinitionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public boolean hasOfferingDefinitionOfferingBundle(
		long offeringDefinitionId, long offeringBundleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringBundleLocalService.hasOfferingDefinitionOfferingBundle(offeringDefinitionId,
			offeringBundleId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public boolean hasOfferingDefinitionOfferingBundles(
		long offeringDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringBundleLocalService.hasOfferingDefinitionOfferingBundles(offeringDefinitionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void setOfferingDefinitionOfferingBundles(
		long offeringDefinitionId, long[] offeringBundleIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_offeringBundleLocalService.setOfferingDefinitionOfferingBundles(offeringDefinitionId,
			offeringBundleIds);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _offeringBundleLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_offeringBundleLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _offeringBundleLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.OfferingBundle addOfferingBundle(long userId,
		java.lang.String name, long[] offeringDefinitionIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _offeringBundleLocalService.addOfferingBundle(userId, name,
			offeringDefinitionIds);
	}

	public com.liferay.osb.model.OfferingBundle updateOfferingBundle(
		long offeringBundleId, java.lang.String name,
		long[] offeringDefinitionIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _offeringBundleLocalService.updateOfferingBundle(offeringBundleId,
			name, offeringDefinitionIds);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public OfferingBundleLocalService getWrappedOfferingBundleLocalService() {
		return _offeringBundleLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedOfferingBundleLocalService(
		OfferingBundleLocalService offeringBundleLocalService) {
		_offeringBundleLocalService = offeringBundleLocalService;
	}

	public OfferingBundleLocalService getWrappedService() {
		return _offeringBundleLocalService;
	}

	public void setWrappedService(
		OfferingBundleLocalService offeringBundleLocalService) {
		_offeringBundleLocalService = offeringBundleLocalService;
	}

	private OfferingBundleLocalService _offeringBundleLocalService;
}