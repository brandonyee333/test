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

package com.liferay.osb.service.impl;

import com.liferay.osb.exception.DuplicateProductEntryException;
import com.liferay.osb.exception.ProductEntryEnvironmentException;
import com.liferay.osb.exception.ProductEntryNameException;
import com.liferay.osb.exception.RequiredProductEntryException;
import com.liferay.osb.model.ExternalIdMapper;
import com.liferay.osb.model.ExternalIdMapperConstants;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.service.base.ProductEntryLocalServiceBaseImpl;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class ProductEntryLocalServiceImpl
	extends ProductEntryLocalServiceBaseImpl {

	public ProductEntry addProductEntry(
			long userId, String name, int type, int environment,
			String versionsListType, String[] dossieraIdMappings)
		throws PortalException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		validate(0, name, environment);

		long productEntryId = counterLocalService.increment();

		ProductEntry productEntry = productEntryPersistence.create(
			productEntryId);

		productEntry.setUserId(user.getUserId());
		productEntry.setUserName(user.getFullName());
		productEntry.setCreateDate(now);
		productEntry.setModifiedDate(now);
		productEntry.setName(name);
		productEntry.setType(type);
		productEntry.setEnvironment(environment);
		productEntry.setVersionsListType(versionsListType);

		//TODO implement serviceContext how needed

		ServiceContext serviceContext = new ServiceContext();

		productEntryPersistence.update(productEntry, serviceContext);

		long classNameId = classNameLocalService.getClassNameId(
			ProductEntry.class);

		for (String dossieraIdMapping : dossieraIdMappings) {
			externalIdMapperLocalService.addExternalIdMapper(
				classNameId, productEntryId,
				ExternalIdMapperConstants.TYPE_DOSSIERA, dossieraIdMapping);
		}

		return productEntry;
	}

	@Override
	public ProductEntry deleteProductEntry(long productEntryId)
		throws PortalException {

		if (licenseEntryPersistence.countByProductEntryId(productEntryId) > 0) {
			throw new RequiredProductEntryException();
		}

		if (offeringDefinitionPersistence.countByProductEntryId(
				productEntryId) > 0) {

			throw new RequiredProductEntryException();
		}

		// Product entry

		ProductEntry productEntry = productEntryPersistence.remove(
			productEntryId);

		// External ids

		long classNameId = classNameLocalService.getClassNameId(
			ProductEntry.class.getName());

		externalIdMapperPersistence.removeByC_C(classNameId, productEntryId);

		return productEntry;
	}

	public ProductEntry fetchProductEntryByName(String name) {
		return productEntryPersistence.fetchByName(name);
	}

	public List<ProductEntry> getProductEntries(long accountEntryId) {
		return productEntryFinder.findByAccountEntry(
			accountEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public ProductEntry getProductEntryByName(String name)
		throws PortalException {

		return productEntryPersistence.findByName(name);
	}

	public List<ProductEntry> search(
		String name, LinkedHashMap<String, Object> params, int start, int end) {

		return productEntryFinder.findByName(name, params, start, end);
	}

	public int searchCount(String name, LinkedHashMap<String, Object> params) {
		return productEntryFinder.countByName(name, params);
	}

	public ProductEntry updateProductEntry(
			long productEntryId, String name, int type, int environment,
			String versionsListType, String[] dossieraIdMappings)
		throws PortalException {

		validate(productEntryId, name, environment);

		ProductEntry productEntry = productEntryPersistence.findByPrimaryKey(
			productEntryId);

		productEntry.setModifiedDate(new Date());
		productEntry.setName(name);
		productEntry.setType(type);
		productEntry.setEnvironment(environment);
		productEntry.setVersionsListType(versionsListType);

		//TODO implement serviceContext how needed

		ServiceContext serviceContext = new ServiceContext();

		productEntryPersistence.update(productEntry, serviceContext);

		long classNameId = classNameLocalService.getClassNameId(
			ProductEntry.class);

		List<ExternalIdMapper> externalIdMappers =
			externalIdMapperLocalService.getExternalIdMappers(
				classNameId, productEntryId,
				ExternalIdMapperConstants.TYPE_DOSSIERA);

		for (ExternalIdMapper externalIdMapper : externalIdMappers) {
			String externalId = externalIdMapper.getExternalId();

			if (!ArrayUtil.contains(dossieraIdMappings, externalId)) {
				externalIdMapperLocalService.deleteExternalIdMapper(
					externalIdMapper.getExternalIdMapperId());
			}
			else {
				dossieraIdMappings = ArrayUtil.remove(
					dossieraIdMappings, externalId);
			}
		}

		for (String dossieraIdMapping : dossieraIdMappings) {
			externalIdMapperLocalService.addExternalIdMapper(
				classNameId, productEntryId,
				ExternalIdMapperConstants.TYPE_DOSSIERA, dossieraIdMapping);
		}

		return productEntry;
	}

	protected void validate(long productEntryId, String name, int environment)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new ProductEntryNameException();
		}

		ProductEntry productEntry = productEntryPersistence.fetchByName(name);

		if ((productEntry != null) &&
			(productEntry.getProductEntryId() != productEntryId)) {

			throw new DuplicateProductEntryException();
		}

		if (environment <= 0) {
			throw new ProductEntryEnvironmentException();
		}
	}

}