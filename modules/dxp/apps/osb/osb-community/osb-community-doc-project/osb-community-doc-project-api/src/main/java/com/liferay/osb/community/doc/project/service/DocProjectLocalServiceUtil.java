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

import com.liferay.osb.community.doc.project.model.DocProject;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for DocProject. This utility wraps
 * <code>com.liferay.osb.community.doc.project.service.impl.DocProjectLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ryan Park
 * @see DocProjectLocalService
 * @generated
 */
public class DocProjectLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.community.doc.project.service.impl.DocProjectLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the doc project to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DocProjectLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param docProject the doc project
	 * @return the doc project that was added
	 */
	public static DocProject addDocProject(DocProject docProject) {
		return getService().addDocProject(docProject);
	}

	public static DocProject addDocProject(
			long userId, String name, String description, String iconFileName,
			java.io.File iconFile, boolean unlisted, String type,
			String typeSettings, int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addDocProject(
			userId, name, description, iconFileName, iconFile, unlisted, type,
			typeSettings, status, serviceContext);
	}

	/**
	 * Creates a new doc project with the primary key. Does not add the doc project to the database.
	 *
	 * @param docProjectId the primary key for the new doc project
	 * @return the new doc project
	 */
	public static DocProject createDocProject(long docProjectId) {
		return getService().createDocProject(docProjectId);
	}

	/**
	 * Deletes the doc project from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DocProjectLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param docProject the doc project
	 * @return the doc project that was removed
	 * @throws PortalException
	 */
	public static DocProject deleteDocProject(DocProject docProject)
		throws PortalException {

		return getService().deleteDocProject(docProject);
	}

	/**
	 * Deletes the doc project with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DocProjectLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param docProjectId the primary key of the doc project
	 * @return the doc project that was removed
	 * @throws PortalException if a doc project with the primary key could not be found
	 */
	public static DocProject deleteDocProject(long docProjectId)
		throws PortalException {

		return getService().deleteDocProject(docProjectId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
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
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static DocProject fetchDocProject(long docProjectId) {
		return getService().fetchDocProject(docProjectId);
	}

	/**
	 * Returns the doc project matching the UUID and group.
	 *
	 * @param uuid the doc project's UUID
	 * @param groupId the primary key of the group
	 * @return the matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	public static DocProject fetchDocProjectByUuidAndGroupId(
		String uuid, long groupId) {

		return getService().fetchDocProjectByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the doc project with the primary key.
	 *
	 * @param docProjectId the primary key of the doc project
	 * @return the doc project
	 * @throws PortalException if a doc project with the primary key could not be found
	 */
	public static DocProject getDocProject(long docProjectId)
		throws PortalException {

		return getService().getDocProject(docProjectId);
	}

	public static DocProject getDocProjectByGroupId(long groupId)
		throws com.liferay.osb.community.doc.project.exception.
			NoSuchDocProjectException {

		return getService().getDocProjectByGroupId(groupId);
	}

	/**
	 * Returns the doc project matching the UUID and group.
	 *
	 * @param uuid the doc project's UUID
	 * @param groupId the primary key of the group
	 * @return the matching doc project
	 * @throws PortalException if a matching doc project could not be found
	 */
	public static DocProject getDocProjectByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return getService().getDocProjectByUuidAndGroupId(uuid, groupId);
	}

	public static List<DocProject> getDocProjects(
			boolean unlisted, int status, int start, int end,
			OrderByComparator obc)
		throws com.liferay.osb.community.doc.project.exception.
			NoSuchDocProjectException {

		return getService().getDocProjects(unlisted, status, start, end, obc);
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
	public static List<DocProject> getDocProjects(int start, int end) {
		return getService().getDocProjects(start, end);
	}

	/**
	 * Returns all the doc projects matching the UUID and company.
	 *
	 * @param uuid the UUID of the doc projects
	 * @param companyId the primary key of the company
	 * @return the matching doc projects, or an empty list if no matches were found
	 */
	public static List<DocProject> getDocProjectsByUuidAndCompanyId(
		String uuid, long companyId) {

		return getService().getDocProjectsByUuidAndCompanyId(uuid, companyId);
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
	public static List<DocProject> getDocProjectsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<DocProject> orderByComparator) {

		return getService().getDocProjectsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of doc projects.
	 *
	 * @return the number of doc projects
	 */
	public static int getDocProjectsCount() {
		return getService().getDocProjectsCount();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
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
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static void updateAsset(
			long userId, long docProjectId, long[] assetCategoryIds,
			String[] assetTagNames)
		throws PortalException {

		getService().updateAsset(
			userId, docProjectId, assetCategoryIds, assetTagNames);
	}

	/**
	 * Updates the doc project in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DocProjectLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param docProject the doc project
	 * @return the doc project that was updated
	 */
	public static DocProject updateDocProject(DocProject docProject) {
		return getService().updateDocProject(docProject);
	}

	public static DocProject updateDocProject(
			long docProjectId, String name, String description,
			String iconFileName, java.io.File iconFile, boolean unlisted,
			String type, String typeSettings, int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateDocProject(
			docProjectId, name, description, iconFileName, iconFile, unlisted,
			type, typeSettings, status, serviceContext);
	}

	public static DocProjectLocalService getService() {
		return _service;
	}

	private static volatile DocProjectLocalService _service;

}