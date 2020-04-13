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

package com.liferay.osb.community.doc.project.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DocProjectLocalService}.
 *
 * @author Ryan Park
 * @see DocProjectLocalService
 * @generated
 */
public class DocProjectLocalServiceWrapper
	implements DocProjectLocalService, ServiceWrapper<DocProjectLocalService> {

	public DocProjectLocalServiceWrapper(
		DocProjectLocalService docProjectLocalService) {

		_docProjectLocalService = docProjectLocalService;
	}

	/**
	 * Adds the doc project to the database. Also notifies the appropriate model listeners.
	 *
	 * @param docProject the doc project
	 * @return the doc project that was added
	 */
	@Override
	public com.liferay.osb.community.doc.project.model.DocProject addDocProject(
		com.liferay.osb.community.doc.project.model.DocProject docProject) {

		return _docProjectLocalService.addDocProject(docProject);
	}

	@Override
	public com.liferay.osb.community.doc.project.model.DocProject addDocProject(
			long userId, String name, String description, String iconFileName,
			java.io.File iconFile, boolean unlisted, String type,
			String typeSettings, int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _docProjectLocalService.addDocProject(
			userId, name, description, iconFileName, iconFile, unlisted, type,
			typeSettings, status, serviceContext);
	}

	/**
	 * Creates a new doc project with the primary key. Does not add the doc project to the database.
	 *
	 * @param docProjectId the primary key for the new doc project
	 * @return the new doc project
	 */
	@Override
	public com.liferay.osb.community.doc.project.model.DocProject
		createDocProject(long docProjectId) {

		return _docProjectLocalService.createDocProject(docProjectId);
	}

	/**
	 * Deletes the doc project from the database. Also notifies the appropriate model listeners.
	 *
	 * @param docProject the doc project
	 * @return the doc project that was removed
	 * @throws PortalException
	 */
	@Override
	public com.liferay.osb.community.doc.project.model.DocProject
			deleteDocProject(
				com.liferay.osb.community.doc.project.model.DocProject
					docProject)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _docProjectLocalService.deleteDocProject(docProject);
	}

	/**
	 * Deletes the doc project with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param docProjectId the primary key of the doc project
	 * @return the doc project that was removed
	 * @throws PortalException if a doc project with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.community.doc.project.model.DocProject
			deleteDocProject(long docProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _docProjectLocalService.deleteDocProject(docProjectId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _docProjectLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _docProjectLocalService.dynamicQuery();
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

		return _docProjectLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.community.doc.project.model.impl.DocProjectModelImpl</code>.
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

		return _docProjectLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.community.doc.project.model.impl.DocProjectModelImpl</code>.
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

		return _docProjectLocalService.dynamicQuery(
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

		return _docProjectLocalService.dynamicQueryCount(dynamicQuery);
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

		return _docProjectLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.community.doc.project.model.DocProject
		fetchDocProject(long docProjectId) {

		return _docProjectLocalService.fetchDocProject(docProjectId);
	}

	/**
	 * Returns the doc project matching the UUID and group.
	 *
	 * @param uuid the doc project's UUID
	 * @param groupId the primary key of the group
	 * @return the matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	@Override
	public com.liferay.osb.community.doc.project.model.DocProject
		fetchDocProjectByUuidAndGroupId(String uuid, long groupId) {

		return _docProjectLocalService.fetchDocProjectByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _docProjectLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the doc project with the primary key.
	 *
	 * @param docProjectId the primary key of the doc project
	 * @return the doc project
	 * @throws PortalException if a doc project with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.community.doc.project.model.DocProject getDocProject(
			long docProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _docProjectLocalService.getDocProject(docProjectId);
	}

	@Override
	public com.liferay.osb.community.doc.project.model.DocProject
			getDocProjectByGroupId(long groupId)
		throws com.liferay.osb.community.doc.project.exception.
			NoSuchDocProjectException {

		return _docProjectLocalService.getDocProjectByGroupId(groupId);
	}

	/**
	 * Returns the doc project matching the UUID and group.
	 *
	 * @param uuid the doc project's UUID
	 * @param groupId the primary key of the group
	 * @return the matching doc project
	 * @throws PortalException if a matching doc project could not be found
	 */
	@Override
	public com.liferay.osb.community.doc.project.model.DocProject
			getDocProjectByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _docProjectLocalService.getDocProjectByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public java.util.List
		<com.liferay.osb.community.doc.project.model.DocProject> getDocProjects(
				boolean unlisted, int status, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator obc)
			throws com.liferay.osb.community.doc.project.exception.
				NoSuchDocProjectException {

		return _docProjectLocalService.getDocProjects(
			unlisted, status, start, end, obc);
	}

	/**
	 * Returns a range of all the doc projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.community.doc.project.model.impl.DocProjectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of doc projects
	 * @param end the upper bound of the range of doc projects (not inclusive)
	 * @return the range of doc projects
	 */
	@Override
	public java.util.List
		<com.liferay.osb.community.doc.project.model.DocProject> getDocProjects(
			int start, int end) {

		return _docProjectLocalService.getDocProjects(start, end);
	}

	/**
	 * Returns all the doc projects matching the UUID and company.
	 *
	 * @param uuid the UUID of the doc projects
	 * @param companyId the primary key of the company
	 * @return the matching doc projects, or an empty list if no matches were found
	 */
	@Override
	public java.util.List
		<com.liferay.osb.community.doc.project.model.DocProject>
			getDocProjectsByUuidAndCompanyId(String uuid, long companyId) {

		return _docProjectLocalService.getDocProjectsByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of doc projects matching the UUID and company.
	 *
	 * @param uuid the UUID of the doc projects
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of doc projects
	 * @param end the upper bound of the range of doc projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching doc projects, or an empty list if no matches were found
	 */
	@Override
	public java.util.List
		<com.liferay.osb.community.doc.project.model.DocProject>
			getDocProjectsByUuidAndCompanyId(
				String uuid, long companyId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.osb.community.doc.project.model.DocProject>
						orderByComparator) {

		return _docProjectLocalService.getDocProjectsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of doc projects.
	 *
	 * @return the number of doc projects
	 */
	@Override
	public int getDocProjectsCount() {
		return _docProjectLocalService.getDocProjectsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _docProjectLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _docProjectLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _docProjectLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _docProjectLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public void updateAsset(
			long userId, long docProjectId, long[] assetCategoryIds,
			String[] assetTagNames)
		throws com.liferay.portal.kernel.exception.PortalException {

		_docProjectLocalService.updateAsset(
			userId, docProjectId, assetCategoryIds, assetTagNames);
	}

	/**
	 * Updates the doc project in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param docProject the doc project
	 * @return the doc project that was updated
	 */
	@Override
	public com.liferay.osb.community.doc.project.model.DocProject
		updateDocProject(
			com.liferay.osb.community.doc.project.model.DocProject docProject) {

		return _docProjectLocalService.updateDocProject(docProject);
	}

	@Override
	public com.liferay.osb.community.doc.project.model.DocProject
			updateDocProject(
				long docProjectId, String name, String description,
				String iconFileName, java.io.File iconFile, boolean unlisted,
				String type, String typeSettings, int status,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _docProjectLocalService.updateDocProject(
			docProjectId, name, description, iconFileName, iconFile, unlisted,
			type, typeSettings, status, serviceContext);
	}

	@Override
	public DocProjectLocalService getWrappedService() {
		return _docProjectLocalService;
	}

	@Override
	public void setWrappedService(
		DocProjectLocalService docProjectLocalService) {

		_docProjectLocalService = docProjectLocalService;
	}

	private DocProjectLocalService _docProjectLocalService;

}