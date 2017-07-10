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

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.osb.exception.DuplicateTicketAttachmentException;
import com.liferay.osb.exception.TicketAttachmentAvailableFileRepositoryIdsException;
import com.liferay.osb.exception.TicketAttachmentVisibilityException;
import com.liferay.osb.exception.TicketEntryAttachmentSizeException;
import com.liferay.osb.model.AuditEntryConstants;
import com.liferay.osb.model.FileRepository;
import com.liferay.osb.model.SupportResponse;
import com.liferay.osb.model.SupportResponseConstants;
import com.liferay.osb.model.TicketAttachment;
import com.liferay.osb.model.TicketAttachmentConstants;
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.service.base.TicketAttachmentLocalServiceBaseImpl;
import com.liferay.osb.support.util.FileRepositoryUtil;
import com.liferay.osb.support.util.SupportUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.VisibilityConstants;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.osb.util.comparator.TicketAttachmentCreateDateComparator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.kernel.zip.ZipReader;
import com.liferay.portal.kernel.zip.ZipReaderFactoryUtil;
import com.liferay.portal.kernel.zip.ZipWriter;
import com.liferay.portal.kernel.zip.ZipWriterFactoryUtil;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.document.library.kernel.exception.DuplicateDirectoryException;
import com.liferay.document.library.kernel.exception.DuplicateFileException;
import com.liferay.document.library.kernel.exception.NoSuchFileException;
import com.liferay.document.library.kernel.store.DLStoreUtil;
import com.liferay.document.library.kernel.store.Store;
import com.liferay.releasenotes.model.ReleaseNotes;
import com.liferay.releasenotes.service.ReleaseNotesLocalServiceUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author Amos Fong
 */
public class TicketAttachmentLocalServiceImpl
	extends TicketAttachmentLocalServiceBaseImpl {

	public TicketAttachment addTicketAttachment(
			long userId, long ticketEntryId, long ticketSolutionId,
			String fileName, long fileSize, int type, int visibility,
			String fileRepositoryId, int status)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		validate(ticketEntryId, fileName, fileSize, type, visibility);

		boolean replicate = false;

		TicketEntry ticketEntry = ticketEntryPersistence.findByPrimaryKey(
			ticketEntryId);

		if (ticketEntry.getSeverity() ==
				SupportResponseConstants.SEVERITY_CRITICAL) {

			SupportResponse supportResponse = ticketEntry.getSupportResponse();

			if (supportResponse.isPlatinumLevel()) {
				replicate = true;
			}
		}

		long ticketAttachmentId = counterLocalService.increment();

		TicketAttachment ticketAttachment = ticketAttachmentPersistence.create(
			ticketAttachmentId);

		ticketAttachment.setUserId(user.getUserId());
		ticketAttachment.setUserName(user.getFullName());
		ticketAttachment.setCreateDate(new Date());
		ticketAttachment.setTicketEntryId(ticketEntryId);
		ticketAttachment.setTicketSolutionId(ticketSolutionId);
		ticketAttachment.setFileName(fileName);
		ticketAttachment.setFileSize(fileSize);
		ticketAttachment.setType(type);
		ticketAttachment.setVisibility(visibility);
		ticketAttachment.setAvailableFileRepositoryIds(fileRepositoryId);
		ticketAttachment.setReplicate(replicate);
		
		//TODO implement serviceContext how needed
		
		ServiceContext serviceContext = new ServiceContext();

		ticketAttachmentPersistence.update(ticketAttachment, serviceContext);

		List<TicketAttachment> ticketAttachments =
			new ArrayList<TicketAttachment>();

		ticketAttachments.add(ticketAttachment);

		updateStatus(
			user, ticketAttachments, ticketEntryId, status,
			new ServiceContext());

		FileRepositoryUtil.updateFile(
			fileRepositoryId, ticketEntryId, fileName,
			ticketAttachment.getFilePath(), replicate);

		return ticketAttachment;
	}

	public List<TicketAttachment> addTicketAttachments(
			long userId, long ticketEntryId, long ticketSolutionId,
			List<ObjectValuePair<String, File>> files, List<Integer> types,
			int visibility, int status, ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = serviceContext.getCreateDate(new Date());

		List<TicketAttachment> ticketAttachments =
			new ArrayList<TicketAttachment>();

		for (int i = 0; i < files.size(); i++) {

			// Ticket attachment

			int type = TicketAttachmentConstants.TYPE_NONE;

			if ((types != null) && (types.size() >= i)) {
				type = types.get(i);
			}

			if (ticketEntryId !=
					TicketAttachmentConstants.TICKET_ENTRY_DEFAULT_ID) {

				if ((type == TicketAttachmentConstants.TYPE_PATCH_LEVEL) ||
					(type == TicketAttachmentConstants.TYPE_PORTAL_EXT) ||
					(type == TicketAttachmentConstants.TYPE_SCREEN_SHOT) ||
					(type == TicketAttachmentConstants.TYPE_SERVER_LOG) ||
					(type ==
						TicketAttachmentConstants.TYPE_SOCIAL_OFFICE_PLUGIN)) {

					deleteTicketAttachment(userId, ticketEntryId, type);
				}
			}

			ObjectValuePair<String, File> ovp = files.get(i);

			String fileName = ovp.getKey();
			File file = ovp.getValue();

			if (Validator.isNull(fileName)) {
				continue;
			}

			validate(ticketEntryId, fileName, file.length(), type, visibility);

			long ticketAttachmentId = counterLocalService.increment();

			TicketAttachment ticketAttachment =
				ticketAttachmentPersistence.create(ticketAttachmentId);

			ticketAttachment.setUserId(user.getUserId());
			ticketAttachment.setUserName(user.getFullName());
			ticketAttachment.setCreateDate(now);
			ticketAttachment.setTicketEntryId(ticketEntryId);
			ticketAttachment.setTicketSolutionId(ticketSolutionId);
			ticketAttachment.setFileName(fileName);
			ticketAttachment.setFileSize(file.length());
			ticketAttachment.setType(type);
			ticketAttachment.setVisibility(visibility);
			
			//TODO implement serviceContext how needed

			ticketAttachmentPersistence.update(ticketAttachment, serviceContext);

			ticketAttachments.add(ticketAttachment);

			// File

			long companyId = OSBConstants.COMPANY_ID;
			long repositoryId = CompanyConstants.SYSTEM;

			StringBundler sb = new StringBundler(4);

			sb.append(OSBConstants.ATTACHMENTS_DIR_SUPPORT);
			sb.append(ticketEntryId);
			sb.append(StringPool.SLASH);
			sb.append(ticketAttachmentId);

			String dirName = sb.toString();

			try {
				DLStoreUtil.addDirectory(companyId, repositoryId, dirName);
			}
			catch (DuplicateDirectoryException dde) {
			}

			try {
				DLStoreUtil.addFile(
					companyId, repositoryId, dirName + "/" + fileName, file);
			}
			catch (DuplicateFileException dfe) {
			}

			updateExtractedText(ticketAttachment);
		}

		updateStatus(
			user, ticketAttachments, ticketEntryId, status, serviceContext);

		return ticketAttachments;
	}

	public boolean checkAvailability(
			long ticketAttachmentId, String fileRepositoryId)
		throws PortalException, SystemException {

		TicketAttachment ticketAttachment =
			ticketAttachmentPersistence.fetchByPrimaryKey(ticketAttachmentId);

		String url = FileRepositoryUtil.getDownloadURL(
			fileRepositoryId, ticketAttachment.getFilePath());

		Set<String> availableFileRepositoryIds =
			ticketAttachment.getAvailableFileRepositoryIdsSet();
		
		//TODO implement serviceContext how needed
		
		ServiceContext serviceContext = new ServiceContext();

		if (url != null) {
			availableFileRepositoryIds.add(fileRepositoryId);

			ticketAttachment.setAvailableFileRepositoryIdsSet(
				availableFileRepositoryIds);

			ticketAttachmentPersistence.update(ticketAttachment, serviceContext);

			return true;
		}
		else {
			availableFileRepositoryIds.remove(fileRepositoryId);

			ticketAttachment.setAvailableFileRepositoryIdsSet(
				availableFileRepositoryIds);

			ticketAttachmentPersistence.update(ticketAttachment, serviceContext);

			return false;
		}
	}

	public void cleanTicketAttachments()
		throws PortalException, SystemException {

		deleteInactiveTicketAttachments();

		deleteOrphanTicketAttachments();
	}

	public TicketAttachment deleteTicketAttachment(
			long userId, long ticketAttachmentId)
		throws PortalException, SystemException {

		TicketAttachment ticketAttachment =
			ticketAttachmentPersistence.fetchByPrimaryKey(ticketAttachmentId);

		return deleteTicketAttachment(userId, ticketAttachment);
	}

	public void deleteTicketAttachment(
			long userId, long ticketEntryId, int type)
		throws PortalException, SystemException {

		List<TicketAttachment> ticketAttachments =
			ticketAttachmentPersistence.findByTEI_T_S(
				ticketEntryId, type, WorkflowConstants.STATUS_APPROVED);

		for (TicketAttachment ticketAttachment : ticketAttachments) {
			deleteTicketAttachment(userId, ticketAttachment);
		}
	}

	public TicketAttachment deleteTicketAttachment(
			long userId, TicketAttachment ticketAttachment)
		throws PortalException, SystemException {

		// Ticket attachment

		ticketAttachmentPersistence.remove(ticketAttachment);

		// File

		if (ArrayUtil.contains(
				TicketAttachmentConstants.TYPES_LARGE,
				ticketAttachment.getType())) {

			Set<String> availableFileRepositoryIds =
				ticketAttachment.getAvailableFileRepositoryIdsSet();

			for (String fileRepositoryId : availableFileRepositoryIds) {
				FileRepositoryUtil.deleteFile(
					fileRepositoryId, ticketAttachment.getFilePath(),
					ticketAttachment.getReplicate());
			}
		}
		else {
			long companyId = OSBConstants.COMPANY_ID;
			long repositoryId = CompanyConstants.SYSTEM;

			try {
				DLStoreUtil.deleteFile(
					companyId, repositoryId, ticketAttachment.getFilePath());
			}
			catch (NoSuchFileException nsfe) {
			}
		}

		if ((ticketAttachment.getTicketEntryId() ==
				TicketAttachmentConstants.TICKET_ENTRY_DEFAULT_ID) ||
			(ticketAttachment.getStatus() == WorkflowConstants.STATUS_DRAFT)) {

			return ticketAttachment;
		}

		// Release notes

		if ((ticketAttachment.getType() ==
				TicketAttachmentConstants.TYPE_HOTFIX) &&
			(ticketAttachment.getReleaseNotesId() > 0)) {

			try {
				ReleaseNotesLocalServiceUtil.deleteReleaseNotes(
					ticketAttachment.getReleaseNotesId());
			}
			catch (Exception e) {
			}
		}

		// Audit entry

		User user = userPersistence.findByPrimaryKey(userId);

		auditEntryLocalService.addAuditEntry(
			userId, user.getFullName(), new Date(),
			PortalUtil.getClassNameId(TicketEntry.class.getName()),
			ticketAttachment.getTicketEntryId(), 0,
			PortalUtil.getClassNameId(TicketAttachment.class.getName()),
			ticketAttachment.getTicketAttachmentId(),
			AuditEntryConstants.ACTION_DELETE, AuditEntryConstants.FIELD_FILE,
			VisibilityConstants.PUBLIC, StringPool.BLANK,
			ticketAttachment.getFileName(), StringPool.BLANK, StringPool.BLANK);

		// Indexer

		ticketEntryLocalService.reindexTicketEntry(
			ticketAttachment.getTicketEntryId());

		return ticketAttachment;
	}

	public TicketAttachment fetchTicketAttachment(long ticketEntryId, int type)
		throws SystemException {

		return ticketAttachmentPersistence.fetchByTEI_T_S_First(
			ticketEntryId, type, WorkflowConstants.STATUS_APPROVED,
			new TicketAttachmentCreateDateComparator());
	}

	public TicketAttachment fetchTicketAttachment(
			long ticketEntryId, String fileName, int visibility, int status)
		throws SystemException {

		return ticketAttachmentPersistence.fetchByTEI_FN_V_S(
			ticketEntryId, fileName, visibility, status);
	}

	public InputStream getFileAsStream(TicketAttachment ticketAttachment)
		throws PortalException, SystemException {

		return DLStoreUtil.getFileAsStream(
			OSBConstants.COMPANY_ID, CompanyConstants.SYSTEM,
			ticketAttachment.getFilePath());
	}

	public List<TicketAttachment> getTicketAttachments(
			Date createDate, int type)
		throws SystemException {

		return ticketAttachmentPersistence.findByCD_T(createDate, type);
	}

	public List<TicketAttachment> getTicketAttachments(int[] types)
		throws SystemException {

		return ticketAttachmentPersistence.findByType(types);
	}

	public List<TicketAttachment> getTicketAttachments(long ticketEntryId)
		throws SystemException {

		return ticketAttachmentPersistence.findByTEI_S(
			ticketEntryId, WorkflowConstants.STATUS_APPROVED);
	}

	public List<TicketAttachment> getTicketAttachments(
			long ticketEntryId, int[] visibilities, int status)
		throws SystemException {

		int[] types = new int[] {
			TicketAttachmentConstants.TYPE_HOTFIX,
			TicketAttachmentConstants.TYPE_LARGE_FILE,
			TicketAttachmentConstants.TYPE_LARGE_HOTFIX,
			TicketAttachmentConstants.TYPE_NONE
		};

		return getTicketAttachments(ticketEntryId, types, visibilities, status);
	}

	public List<TicketAttachment> getTicketAttachments(
			long ticketEntryId, int[] types, int[] visibilities)
		throws SystemException {

		return getTicketAttachments(
			ticketEntryId, types, visibilities,
			WorkflowConstants.STATUS_APPROVED);
	}

	public List<TicketAttachment> getTicketAttachments(
			long ticketEntryId, int[] types, int[] visibilities, int status)
		throws SystemException {

		return ticketAttachmentPersistence.findByTEI_T_V_S(
			ticketEntryId, types, visibilities, status);
	}

	public List<TicketAttachment> getTicketAttachments(
			long ticketEntryId, long ticketSolutionId)
		throws SystemException {

		return ticketAttachmentPersistence.findByTEI_TSI(
			ticketEntryId, ticketSolutionId);
	}

	public List<TicketAttachment> getTicketAttachments(
			long userId, long ticketEntryId, int visibility, int status)
		throws SystemException {

		return ticketAttachmentPersistence.findByU_TEI_V_S(
			userId, ticketEntryId, visibility, status);
	}

	public int getTicketAttachmentsCount(long ticketEntryId, int[] visibilities)
		throws SystemException {

		int[] types = new int[] {
			TicketAttachmentConstants.TYPE_HOTFIX,
			TicketAttachmentConstants.TYPE_LARGE_FILE,
			TicketAttachmentConstants.TYPE_LARGE_HOTFIX,
			TicketAttachmentConstants.TYPE_NONE
		};

		return getTicketAttachmentsCount(ticketEntryId, types, visibilities);
	}

	public int getTicketAttachmentsCount(
			long ticketEntryId, int[] types, int[] visibilities)
		throws SystemException {

		return ticketAttachmentPersistence.countByTEI_T_V_S(
			ticketEntryId, types, visibilities,
			WorkflowConstants.STATUS_APPROVED);
	}

	public File getTicketAttachmentsZipFile(
			long ticketEntryId, int[] visibilities)
		throws PortalException, SystemException {

		List<TicketAttachment> ticketAttachments = getTicketAttachments(
			ticketEntryId, TicketAttachmentConstants.TYPES, visibilities,
			WorkflowConstants.STATUS_APPROVED);

		ZipWriter zipWriter = ZipWriterFactoryUtil.getZipWriter();

		try {
			for (TicketAttachment ticketAttachment : ticketAttachments) {
				zipWriter.addEntry(
					ticketAttachment.getFileName(),
					getFileAsStream(ticketAttachment));
			}
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}

		return zipWriter.getFile();
	}

	public TicketAttachment replicateTicketAttachment(
			long userId, long ticketAttachmentId)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		TicketAttachment ticketAttachment =
			ticketAttachmentPersistence.fetchByPrimaryKey(ticketAttachmentId);

		Set<String> availableFileRepositoryIds =
			ticketAttachment.getAvailableFileRepositoryIdsSet();

		if (availableFileRepositoryIds.isEmpty()) {
			throw new TicketAttachmentAvailableFileRepositoryIdsException();
		}

		ticketAttachment.setReplicate(true);
		
		//TODO implement serviceContext how needed
		
		ServiceContext serviceContext = new ServiceContext();

		ticketAttachmentPersistence.update(ticketAttachment, serviceContext);

		FileRepository fileRepository =
			SupportUtil.getFirstActiveFileRepository(
				availableFileRepositoryIds);

		FileRepositoryUtil.replicateFile(
			fileRepository.getFileRepositoryId(),
			ticketAttachment.getFilePath());

		auditEntryLocalService.addAuditEntry(
			userId, user.getFullName(), new Date(),
			PortalUtil.getClassNameId(TicketEntry.class.getName()),
			ticketAttachment.getTicketEntryId(), 0,
			PortalUtil.getClassNameId(TicketAttachment.class.getName()),
			ticketAttachment.getTicketAttachmentId(),
			AuditEntryConstants.ACTION_UPDATE,
			AuditEntryConstants.FIELD_REPLICATE, VisibilityConstants.WORKERS,
			StringPool.BLANK, ticketAttachment.getFileName(), StringPool.BLANK,
			StringPool.BLANK);

		return ticketAttachment;
	}

	public TicketAttachment updateDeleteDate(
			long userId, long ticketAttachmentId, Date deleteDate)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		TicketAttachment ticketAttachment =
			ticketAttachmentPersistence.findByPrimaryKey(ticketAttachmentId);

		ticketAttachment.setDeleteDate(deleteDate);
		
		//TODO implement serviceContext how needed
		
		ServiceContext serviceContext = new ServiceContext();

		ticketAttachmentPersistence.update(ticketAttachment, serviceContext);

		auditEntryLocalService.addAuditEntry(
			userId, user.getFullName(), new Date(),
			PortalUtil.getClassNameId(TicketEntry.class.getName()),
			ticketAttachment.getTicketEntryId(), 0,
			PortalUtil.getClassNameId(TicketAttachment.class.getName()),
			ticketAttachment.getTicketAttachmentId(),
			AuditEntryConstants.ACTION_UPDATE,
			AuditEntryConstants.FIELD_DELETE_DATE, VisibilityConstants.WORKERS,
			StringPool.BLANK, ticketAttachment.getFileName(), StringPool.BLANK,
			StringPool.BLANK);

		return ticketAttachment;
	}

	public void updateExtractedText(TicketAttachment ticketAttachment)
		throws SystemException {

		try {
			String extractedText = extractText(ticketAttachment);

			ticketAttachment.setExtractedText(extractedText);
			
			//TODO implement serviceContext how needed
			
			ServiceContext serviceContext = new ServiceContext();

			ticketAttachmentPersistence.update(ticketAttachment, serviceContext);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public void updateStatus(
			User user, List<TicketAttachment> ticketAttachments,
			long ticketEntryId, int status, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Ticket attachment

		Date now = serviceContext.getCreateDate(new Date());
		
		//TODO implement serviceContext how needed

		for (TicketAttachment ticketAttachment : ticketAttachments) {
			if (status == WorkflowConstants.STATUS_APPROVED) {
				if (ticketAttachment.getStatus() ==
						WorkflowConstants.STATUS_DRAFT) {

					ticketAttachment.setCreateDate(now);
				}

				String fileName = ticketAttachment.getFileName();

				if ((ticketAttachment.getType() ==
						TicketAttachmentConstants.TYPE_HOTFIX) &&
					fileName.endsWith(".zip")) {

					ReleaseNotes releaseNotes = addReleaseNotes(
						ticketAttachment.getUserId(),
						ticketAttachment.getFilePath());

					if (releaseNotes != null) {
						ticketAttachment.setReleaseNotesId(
							releaseNotes.getReleaseNotesId());
					}
				}
			}

			ticketAttachment.setStatus(status);

			ticketAttachmentPersistence.update(ticketAttachment, serviceContext);
		}

		if ((status != WorkflowConstants.STATUS_APPROVED) ||
			(ticketEntryId ==
				TicketAttachmentConstants.TICKET_ENTRY_DEFAULT_ID)) {

			return;
		}

		// Ticket entry

		if (!ticketWorkerLocalService.hasTicketWorker(
				user.getUserId(), ticketEntryId) &&
			!organizationLocalService.hasUserOrganization(
				user.getUserId(), OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			ticketEntryLocalService.updateCustomerModifiedDate(
				user.getUserId(), ticketEntryId, new Date());
		}

		// Audit entry

		long auditSetId = GetterUtil.getInteger(
			serviceContext.getAttribute("auditSetId"));

		if (auditSetId <= 0) {
			auditSetId = auditEntryLocalService.getNextAuditSetId(
				TicketEntry.class.getName(), ticketEntryId);
		}

		int auditAction = GetterUtil.getInteger(
			serviceContext.getAttribute("auditAction"));

		if (auditAction <= 0) {
			auditAction = AuditEntryConstants.ACTION_ADD;
		}

		long classNameId = PortalUtil.getClassNameId(
			TicketEntry.class.getName());
		long fieldClassNameId = PortalUtil.getClassNameId(
			TicketAttachment.class.getName());

		for (TicketAttachment ticketAttachment : ticketAttachments) {
			auditEntryLocalService.addAuditEntry(
				user.getUserId(), user.getFullName(), now, classNameId,
				ticketEntryId, auditSetId, fieldClassNameId,
				ticketAttachment.getTicketAttachmentId(), auditAction,
				AuditEntryConstants.FIELD_FILE,
				ticketAttachment.getVisibility(), StringPool.BLANK,
				StringPool.BLANK, StringPool.BLANK,
				ticketAttachment.getFileName());
		}

		// Indexer

		ticketEntryLocalService.reindexTicketEntry(ticketEntryId);
	}

	public TicketAttachment updateTicketAttachment(
			long ticketAttachmentId, long ticketEntryId, int type,
			int visibility)
		throws PortalException, SystemException {

		TicketAttachment ticketAttachment =
			ticketAttachmentPersistence.findByPrimaryKey(ticketAttachmentId);

		return updateTicketAttachment(
			ticketAttachmentId, ticketEntryId,
			ticketAttachment.getTicketSolutionId(), type, visibility);
	}

	public TicketAttachment updateTicketAttachment(
			long ticketAttachmentId, long ticketEntryId, long ticketSolutionId,
			int type, int visibility)
		throws PortalException, SystemException {

		TicketAttachment ticketAttachment =
			ticketAttachmentPersistence.findByPrimaryKey(ticketAttachmentId);

		if (ticketAttachment.getTicketEntryId() == ticketEntryId) {
			return ticketAttachment;
		}

		validate(
			ticketEntryId, ticketAttachment.getFileName(),
			ticketAttachment.getFileSize(), type, visibility);

		String oldFilePath =
			ticketAttachment.getFilePath() + StringPool.SLASH +
				Store.VERSION_DEFAULT;

		ReleaseNotes releaseNotes = null;

		String fileName = ticketAttachment.getFileName();

		if ((type == TicketAttachmentConstants.TYPE_HOTFIX) &&
			fileName.endsWith(".zip")) {

			releaseNotes = addReleaseNotes(
				ticketAttachment.getUserId(), ticketAttachment.getFilePath());
		}

		ticketAttachment.setTicketEntryId(ticketEntryId);
		ticketAttachment.setTicketSolutionId(ticketSolutionId);
		ticketAttachment.setType(type);
		ticketAttachment.setVisibility(visibility);
		ticketAttachment.setStatus(WorkflowConstants.STATUS_APPROVED);

		if (releaseNotes != null) {
			ticketAttachment.setReleaseNotesId(
				releaseNotes.getReleaseNotesId());
		}
		
		//TODO implement serviceContext how needed
		
		ServiceContext serviceContext = new ServiceContext();

		ticketAttachmentPersistence.update(ticketAttachment, serviceContext);

		// File

		long companyId = OSBConstants.COMPANY_ID;
		long repositoryId = CompanyConstants.SYSTEM;
		String newDirName = ticketAttachment.getFilePath();

		try {
			DLStoreUtil.addDirectory(companyId, repositoryId, newDirName);
		}
		catch (PortalException e) {
		}

		String newFilePath =
			newDirName + StringPool.SLASH + Store.VERSION_DEFAULT;

		try {
			DLStoreUtil.updateFile(
				companyId, repositoryId, oldFilePath, newFilePath);
		}
		catch (Exception e) {
		}

		return ticketAttachment;
	}

	public List<TicketAttachment> updateTicketAttachments(
			List<Long> ticketAttachmentIds, long ticketEntryId,
			List<Integer> types, List<Integer> visibilities)
		throws PortalException, SystemException {

		List<TicketAttachment> ticketAttachments =
			new ArrayList<TicketAttachment>();

		for (int i = 0; i < ticketAttachmentIds.size(); i++) {
			TicketAttachment ticketAttachment = updateTicketAttachment(
				ticketAttachmentIds.get(i), ticketEntryId, types.get(i),
				visibilities.get(i));

			ticketAttachments.add(ticketAttachment);
		}

		return ticketAttachments;
	}

	protected ReleaseNotes addReleaseNotes(long userId, String fileName) {
		InputStream inputStream = null;

		try {
			inputStream = DLStoreUtil.getFileAsStream(
				OSBConstants.COMPANY_ID, CompanyConstants.SYSTEM, fileName);

			ZipReader zipReader = ZipReaderFactoryUtil.getZipReader(
				inputStream);

			String xml = zipReader.getEntryAsString(
				"fixpack_documentation.xml");

			Document document = SAXReaderUtil.read(xml);

			Element rootElement = document.getRootElement();

			String name = rootElement.elementText("name");
			String fixedIssues = rootElement.elementText("fixed-issues");

			ReleaseNotes releaseNotes =
				ReleaseNotesLocalServiceUtil.fetchReleaseNotes(name);

			if (releaseNotes != null) {
				return releaseNotes;
			}

			return ReleaseNotesLocalServiceUtil.addReleaseNotes(
				userId, name, fixedIssues);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}

		return null;
	}

	protected void deleteInactiveTicketAttachments()
		throws PortalException, SystemException {

		List<TicketAttachment> ticketAttachments =
			ticketAttachmentPersistence.findByT_DD(
				TicketAttachmentConstants.TYPES_LARGE, new Date());

		for (TicketAttachment ticketAttachment : ticketAttachments) {
			deleteTicketAttachment(
				OSBConstants.USER_DEFAULT_USER_ID, ticketAttachment);
		}
	}

	protected void deleteOrphanTicketAttachments()
		throws PortalException, SystemException {

		Calendar calendar = Calendar.getInstance();

		calendar.add(Calendar.DATE, -1);

		List<TicketAttachment> ticketAttachments =
			ticketAttachmentPersistence.findByCD_TEI(
				calendar.getTime(),
				TicketAttachmentConstants.TICKET_ENTRY_DEFAULT_ID);

		for (TicketAttachment ticketAttachment : ticketAttachments) {
			deleteTicketAttachment(
				OSBConstants.USER_DEFAULT_USER_ID, ticketAttachment);
		}
	}

	protected String extractText(TicketAttachment ticketAttachment)
		throws IOException {

		if ((ticketAttachment.getType() !=
				TicketAttachmentConstants.TYPE_NEW_PATCH_LEVEL) &&
			(ticketAttachment.getType() !=
				TicketAttachmentConstants.TYPE_NEW_PORTAL_EXT) &&
			(ticketAttachment.getType() !=
				TicketAttachmentConstants.TYPE_PATCH_LEVEL) &&
			(ticketAttachment.getType() !=
				TicketAttachmentConstants.TYPE_PORTAL_EXT)) {

			return StringPool.BLANK;
		}

		if (ticketAttachment.getFileSize() >
				TicketAttachmentConstants.EXTRACT_FILE_SIZE_LIMIT) {

			return StringPool.BLANK;
		}

		String extractedText = StringPool.BLANK;

		InputStream is = null;

		try {
			is = getFileAsStream(ticketAttachment);

			extractedText = FileUtil.extractText(
				is, ticketAttachment.getFileName());
		}
		catch (Exception e) {
		}
		finally {
			if (is != null) {
				is.close();
			}
		}

		if ((ticketAttachment.getType() ==
				TicketAttachmentConstants.TYPE_NEW_PATCH_LEVEL) ||
			(ticketAttachment.getType() ==
				TicketAttachmentConstants.TYPE_PATCH_LEVEL)) {

			if (!extractedText.contains(
					"Loading product and patch information...")) {

				return StringPool.BLANK;
			}
		}

		return extractedText;
	}

	protected void validate(
			long ticketEntryId, String fileName, long fileSize, int type,
			int visibility)
		throws PortalException, SystemException {

		if ((ticketEntryId > 0) &&
			ticketAttachmentPersistence.countByTEI_FN_V_S(
				ticketEntryId, fileName, visibility,
				WorkflowConstants.STATUS_APPROVED) > 0) {

			throw new DuplicateTicketAttachmentException();
		}

		if (!ArrayUtil.contains(TicketAttachmentConstants.TYPES_LARGE, type) &&
			(fileSize > 104857600)) {

			throw new TicketEntryAttachmentSizeException(
				TicketEntryAttachmentSizeException.EXCEEDS_LIMIT);
		}

		if (Validator.isNull(VisibilityConstants.toLabel(visibility))) {
			throw new TicketAttachmentVisibilityException();
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		TicketAttachmentLocalServiceImpl.class);

}