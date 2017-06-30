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

package com.liferay.osb.service.persistence;

import com.liferay.osb.model.TrainingCertificateTemplate;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the training certificate template service. This utility wraps {@link TrainingCertificateTemplatePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingCertificateTemplatePersistence
 * @see TrainingCertificateTemplatePersistenceImpl
 * @generated
 */
public class TrainingCertificateTemplateUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(
		TrainingCertificateTemplate trainingCertificateTemplate) {
		getPersistence().clearCache(trainingCertificateTemplate);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TrainingCertificateTemplate> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TrainingCertificateTemplate> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TrainingCertificateTemplate> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static TrainingCertificateTemplate update(
		TrainingCertificateTemplate trainingCertificateTemplate, boolean merge)
		throws SystemException {
		return getPersistence().update(trainingCertificateTemplate, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static TrainingCertificateTemplate update(
		TrainingCertificateTemplate trainingCertificateTemplate, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(trainingCertificateTemplate, merge, serviceContext);
	}

	/**
	* Caches the training certificate template in the entity cache if it is enabled.
	*
	* @param trainingCertificateTemplate the training certificate template
	*/
	public static void cacheResult(
		com.liferay.osb.model.TrainingCertificateTemplate trainingCertificateTemplate) {
		getPersistence().cacheResult(trainingCertificateTemplate);
	}

	/**
	* Caches the training certificate templates in the entity cache if it is enabled.
	*
	* @param trainingCertificateTemplates the training certificate templates
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.TrainingCertificateTemplate> trainingCertificateTemplates) {
		getPersistence().cacheResult(trainingCertificateTemplates);
	}

	/**
	* Creates a new training certificate template with the primary key. Does not add the training certificate template to the database.
	*
	* @param trainingCertificateTemplateId the primary key for the new training certificate template
	* @return the new training certificate template
	*/
	public static com.liferay.osb.model.TrainingCertificateTemplate create(
		long trainingCertificateTemplateId) {
		return getPersistence().create(trainingCertificateTemplateId);
	}

	/**
	* Removes the training certificate template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingCertificateTemplateId the primary key of the training certificate template
	* @return the training certificate template that was removed
	* @throws com.liferay.osb.NoSuchTrainingCertificateTemplateException if a training certificate template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCertificateTemplate remove(
		long trainingCertificateTemplateId)
		throws com.liferay.osb.NoSuchTrainingCertificateTemplateException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(trainingCertificateTemplateId);
	}

	public static com.liferay.osb.model.TrainingCertificateTemplate updateImpl(
		com.liferay.osb.model.TrainingCertificateTemplate trainingCertificateTemplate,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(trainingCertificateTemplate, merge);
	}

	/**
	* Returns the training certificate template with the primary key or throws a {@link com.liferay.osb.NoSuchTrainingCertificateTemplateException} if it could not be found.
	*
	* @param trainingCertificateTemplateId the primary key of the training certificate template
	* @return the training certificate template
	* @throws com.liferay.osb.NoSuchTrainingCertificateTemplateException if a training certificate template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCertificateTemplate findByPrimaryKey(
		long trainingCertificateTemplateId)
		throws com.liferay.osb.NoSuchTrainingCertificateTemplateException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(trainingCertificateTemplateId);
	}

	/**
	* Returns the training certificate template with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param trainingCertificateTemplateId the primary key of the training certificate template
	* @return the training certificate template, or <code>null</code> if a training certificate template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCertificateTemplate fetchByPrimaryKey(
		long trainingCertificateTemplateId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(trainingCertificateTemplateId);
	}

	/**
	* Returns all the training certificate templates where type = &#63;.
	*
	* @param type the type
	* @return the matching training certificate templates
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingCertificateTemplate> findByType(
		int type) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType(type);
	}

	/**
	* Returns a range of all the training certificate templates where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of training certificate templates
	* @param end the upper bound of the range of training certificate templates (not inclusive)
	* @return the range of matching training certificate templates
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingCertificateTemplate> findByType(
		int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType(type, start, end);
	}

	/**
	* Returns an ordered range of all the training certificate templates where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of training certificate templates
	* @param end the upper bound of the range of training certificate templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching training certificate templates
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingCertificateTemplate> findByType(
		int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType(type, start, end, orderByComparator);
	}

	/**
	* Returns the first training certificate template in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training certificate template
	* @throws com.liferay.osb.NoSuchTrainingCertificateTemplateException if a matching training certificate template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCertificateTemplate findByType_First(
		int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingCertificateTemplateException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType_First(type, orderByComparator);
	}

	/**
	* Returns the first training certificate template in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training certificate template, or <code>null</code> if a matching training certificate template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCertificateTemplate fetchByType_First(
		int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByType_First(type, orderByComparator);
	}

	/**
	* Returns the last training certificate template in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training certificate template
	* @throws com.liferay.osb.NoSuchTrainingCertificateTemplateException if a matching training certificate template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCertificateTemplate findByType_Last(
		int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingCertificateTemplateException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType_Last(type, orderByComparator);
	}

	/**
	* Returns the last training certificate template in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training certificate template, or <code>null</code> if a matching training certificate template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCertificateTemplate fetchByType_Last(
		int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByType_Last(type, orderByComparator);
	}

	/**
	* Returns the training certificate templates before and after the current training certificate template in the ordered set where type = &#63;.
	*
	* @param trainingCertificateTemplateId the primary key of the current training certificate template
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next training certificate template
	* @throws com.liferay.osb.NoSuchTrainingCertificateTemplateException if a training certificate template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCertificateTemplate[] findByType_PrevAndNext(
		long trainingCertificateTemplateId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingCertificateTemplateException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByType_PrevAndNext(trainingCertificateTemplateId, type,
			orderByComparator);
	}

	/**
	* Returns all the training certificate templates.
	*
	* @return the training certificate templates
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingCertificateTemplate> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the training certificate templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training certificate templates
	* @param end the upper bound of the range of training certificate templates (not inclusive)
	* @return the range of training certificate templates
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingCertificateTemplate> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the training certificate templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training certificate templates
	* @param end the upper bound of the range of training certificate templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of training certificate templates
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingCertificateTemplate> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the training certificate templates where type = &#63; from the database.
	*
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByType(int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByType(type);
	}

	/**
	* Removes all the training certificate templates from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of training certificate templates where type = &#63;.
	*
	* @param type the type
	* @return the number of matching training certificate templates
	* @throws SystemException if a system exception occurred
	*/
	public static int countByType(int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByType(type);
	}

	/**
	* Returns the number of training certificate templates.
	*
	* @return the number of training certificate templates
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static TrainingCertificateTemplatePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (TrainingCertificateTemplatePersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					TrainingCertificateTemplatePersistence.class.getName());

			ReferenceRegistry.registerReference(TrainingCertificateTemplateUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(
		TrainingCertificateTemplatePersistence persistence) {
	}

	private static TrainingCertificateTemplatePersistence _persistence;
}