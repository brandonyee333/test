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

package com.liferay.osb.customer.release.tool.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.release.tool.exception.NoSuchModuleVersionException;
import com.liferay.osb.customer.release.tool.model.ModuleVersion;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the module version service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModuleVersionUtil
 * @generated
 */
@ProviderType
public interface ModuleVersionPersistence
	extends BasePersistence<ModuleVersion> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ModuleVersionUtil} to access the module version persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, ModuleVersion> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Caches the module version in the entity cache if it is enabled.
	 *
	 * @param moduleVersion the module version
	 */
	public void cacheResult(ModuleVersion moduleVersion);

	/**
	 * Caches the module versions in the entity cache if it is enabled.
	 *
	 * @param moduleVersions the module versions
	 */
	public void cacheResult(java.util.List<ModuleVersion> moduleVersions);

	/**
	 * Creates a new module version with the primary key. Does not add the module version to the database.
	 *
	 * @param moduleVersionId the primary key for the new module version
	 * @return the new module version
	 */
	public ModuleVersion create(long moduleVersionId);

	/**
	 * Removes the module version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param moduleVersionId the primary key of the module version
	 * @return the module version that was removed
	 * @throws NoSuchModuleVersionException if a module version with the primary key could not be found
	 */
	public ModuleVersion remove(long moduleVersionId)
		throws NoSuchModuleVersionException;

	public ModuleVersion updateImpl(ModuleVersion moduleVersion);

	/**
	 * Returns the module version with the primary key or throws a <code>NoSuchModuleVersionException</code> if it could not be found.
	 *
	 * @param moduleVersionId the primary key of the module version
	 * @return the module version
	 * @throws NoSuchModuleVersionException if a module version with the primary key could not be found
	 */
	public ModuleVersion findByPrimaryKey(long moduleVersionId)
		throws NoSuchModuleVersionException;

	/**
	 * Returns the module version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param moduleVersionId the primary key of the module version
	 * @return the module version, or <code>null</code> if a module version with the primary key could not be found
	 */
	public ModuleVersion fetchByPrimaryKey(long moduleVersionId);

	/**
	 * Returns all the module versions.
	 *
	 * @return the module versions
	 */
	public java.util.List<ModuleVersion> findAll();

	/**
	 * Returns a range of all the module versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of module versions
	 * @param end the upper bound of the range of module versions (not inclusive)
	 * @return the range of module versions
	 */
	public java.util.List<ModuleVersion> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the module versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of module versions
	 * @param end the upper bound of the range of module versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of module versions
	 */
	public java.util.List<ModuleVersion> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ModuleVersion>
			orderByComparator);

	/**
	 * Returns an ordered range of all the module versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of module versions
	 * @param end the upper bound of the range of module versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of module versions
	 */
	public java.util.List<ModuleVersion> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ModuleVersion>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the module versions from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of module versions.
	 *
	 * @return the number of module versions
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}