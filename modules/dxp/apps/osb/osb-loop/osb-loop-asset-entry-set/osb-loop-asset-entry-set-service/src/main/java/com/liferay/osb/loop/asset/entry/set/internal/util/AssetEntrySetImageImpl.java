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

package com.liferay.osb.loop.asset.entry.set.internal.util;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.dynamic.data.mapping.kernel.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.kernel.DDMFormValues;
import com.liferay.dynamic.data.mapping.kernel.Value;
import com.liferay.osb.loop.asset.entry.set.constants.AssetEntrySetConstants;
import com.liferay.osb.loop.asset.entry.set.util.AssetEntrySetImage;
import com.liferay.portal.kernel.exception.ImageResolutionException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.image.ImageBag;
import com.liferay.portal.kernel.image.ImageToolUtil;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.metadata.RawMetadataProcessor;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Image;
import com.liferay.portal.kernel.model.Repository;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepository;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MimeTypes;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

import java.io.File;
import java.io.IOException;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.imageio.ImageIO;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Matthew Kong
 * @author Timothy Bell
 */
@Component(immediate = true, service = AssetEntrySetImage.class)
public class AssetEntrySetImageImpl implements AssetEntrySetImage {

	public JSONObject addImageFile(
			long userId, long classNameId, long classPK, String portletId,
			File file)
		throws PortalException {

		try {
			rotateImage(file);
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}

		FileEntry fileEntry = addFileEntry(
			userId, classNameId, classPK, portletId, file,
			AssetEntrySetConstants.IMAGE_TYPE_RAW, file.getName());

		return getImageJSONObject(
			_jsonFactory.createJSONObject(), fileEntry,
			AssetEntrySetConstants.IMAGE_TYPE_RAW);
	}

	public JSONObject addImageFile(
			long userId, long classNameId, long classPK, String portletId,
			File file, Map<String, String> imageMaxSizes)
		throws PortalException {

		FileEntry rawFileEntry = null;

		ImageBag imageBag = null;

		try {
			rotateImage(file);

			rawFileEntry = addFileEntry(
				userId, classNameId, classPK, portletId, file,
				AssetEntrySetConstants.IMAGE_TYPE_RAW, file.getName());

			imageBag = ImageToolUtil.read(rawFileEntry.getContentStream());
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}

		JSONObject imageJSONObject = _jsonFactory.createJSONObject();

		for (Map.Entry<String, String> entry : imageMaxSizes.entrySet()) {
			FileEntry fileEntry = rawFileEntry;

			String imageType = entry.getKey();

			if (imageType.equals(AssetEntrySetConstants.IMAGE_TYPE_FULL) &&
				Objects.equals(
					_mimeTypes.getContentType(file), ContentTypes.IMAGE_GIF)) {

				fileEntry = addFileEntry(
					userId, classNameId, classPK, portletId, file, imageType,
					file.getName());
			}
			else if (!imageType.equals(AssetEntrySetConstants.IMAGE_TYPE_RAW)) {
				fileEntry = addScaledImageFileEntry(
					userId, classNameId, 0L, portletId, imageBag, imageType,
					file.getName(), entry.getValue());
			}

			imageJSONObject = getImageJSONObject(
				imageJSONObject, fileEntry, imageType);
		}

		return imageJSONObject;
	}

	public FileEntry addScaledImageFileEntry(
			long userId, long classNameId, long classPK, String portletId,
			ImageBag imageBag, String imageType, String originalFileName,
			String imageMaxSize)
		throws PortalException {

		File scaledFile = null;

		String[] maxDimensions = imageMaxSize.split("x");

		RenderedImage scaledRenderedImage = ImageToolUtil.scale(
			imageBag.getRenderedImage(),
			GetterUtil.getInteger(maxDimensions[0]),
			GetterUtil.getInteger(maxDimensions[1]));

		try {
			scaledFile = _file.createTempFile(
				ImageToolUtil.getBytes(
					scaledRenderedImage, imageBag.getType()));

			return addFileEntry(
				userId, classNameId, classPK, portletId, scaledFile, imageType,
				originalFileName);
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}
		finally {
			_file.delete(scaledFile);
		}
	}

	public JSONObject getImageJSONObject(
			JSONObject imageJSONObject, FileEntry fileEntry, String imageType)
		throws PortalException {

		JSONObject fileEntryIdsJSONObject = imageJSONObject.getJSONObject(
			"fileEntryIds");

		if (fileEntryIdsJSONObject == null) {
			fileEntryIdsJSONObject = _jsonFactory.createJSONObject();
		}

		fileEntryIdsJSONObject.put(imageType, fileEntry.getFileEntryId());

		imageJSONObject.put("fileEntryIds", fileEntryIdsJSONObject);

		DLFileEntry dlFileEntry = _dlFileEntryLocalService.getDLFileEntry(
			fileEntry.getFileEntryId());

		UnicodeProperties extraSettingsProperties =
			dlFileEntry.getExtraSettingsProperties();

		JSONObject fileEntryImageJSONObject = _jsonFactory.createJSONObject(
			extraSettingsProperties.getProperty("fileEntryImageJSONObject"));

		Iterator<String> iterator = fileEntryImageJSONObject.keys();

		while (iterator.hasNext()) {
			String key = iterator.next();

			imageJSONObject.put(key, fileEntryImageJSONObject.getString(key));
		}

		return imageJSONObject;
	}

	protected FileEntry addFileEntry(
			long userId, long classNameId, long classPK, String portletId,
			File file, String imageType, String originalFileName)
		throws PortalException {

		long groupId = 0;

		User user = _userLocalService.getUser(userId);

		if (user.isDefaultUser()) {
			Group group = _groupLocalService.getGroup(
				user.getCompanyId(), GroupConstants.GUEST);

			groupId = group.getGroupId();
		}
		else {
			groupId = user.getGroupId();
		}

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(false);
		serviceContext.setAddGuestPermissions(false);

		Repository repository = _portletFileRepository.addPortletRepository(
			groupId, portletId, serviceContext);

		serviceContext.setAttribute(
			"className", _portal.getClassName(classNameId));
		serviceContext.setAttribute("classPK", String.valueOf(classPK));

		String fileName =
			System.currentTimeMillis() + imageType + originalFileName;

		String contentType = _mimeTypes.getContentType(fileName);

		FileEntry fileEntry = _dlAppLocalService.addFileEntry(
			userId, repository.getRepositoryId(), 0L, fileName, contentType,
			fileName, originalFileName, StringPool.BLANK, file, serviceContext);

		DLFileEntry dlFileEntry = _dlFileEntryLocalService.getDLFileEntry(
			fileEntry.getFileEntryId());

		UnicodeProperties extraSettingsProperties =
			dlFileEntry.getExtraSettingsProperties();

		JSONObject fileEntryImageJSONObject = _jsonFactory.createJSONObject();

		try {
			Image image = ImageToolUtil.getImage(
				_dlFileEntryLocalService.getFileAsStream(
					fileEntry.getFileEntryId(), fileEntry.getVersion(), false));

			fileEntryImageJSONObject.put(
				"height_" + imageType, image.getHeight());
			fileEntryImageJSONObject.put(
				"width_" + imageType, image.getWidth());
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}

		fileEntryImageJSONObject.put(
			"imageURL_" + imageType,
			DLUtil.getPreviewURL(
				fileEntry, fileEntry.getFileVersion(), null, StringPool.BLANK,
				false, true));
		fileEntryImageJSONObject.put("mimeType", fileEntry.getMimeType());
		fileEntryImageJSONObject.put("name", fileEntry.getTitle());

		extraSettingsProperties.put(
			"fileEntryImageJSONObject", fileEntryImageJSONObject.toString());

		_dlFileEntryLocalService.updateDLFileEntry(dlFileEntry);

		return fileEntry;
	}

	protected int getOrientation(File file) {
		Map<String, DDMFormValues> ddmFormValuesMap = null;

		try {
			ddmFormValuesMap = _rawMetadataProcessor.getRawMetadataMap(
				_file.getExtension(file.getName()),
				_mimeTypes.getContentType(file), file);
		}
		catch (Exception e) {
			return _ORIENTATION_NORMAL;
		}

		DDMFormValues rawMetadataDDMFormValues = ddmFormValuesMap.get(
			RawMetadataProcessor.TIKA_RAW_METADATA);

		if (rawMetadataDDMFormValues == null) {
			return _ORIENTATION_NORMAL;
		}

		List<DDMFormFieldValue> ddmFormFieldValues =
			rawMetadataDDMFormValues.getDDMFormFieldValues();

		if (ddmFormFieldValues.isEmpty()) {
			return _ORIENTATION_NORMAL;
		}

		for (DDMFormFieldValue ddmFormFieldValue : ddmFormFieldValues) {
			String ddmFormFieldValueName = ddmFormFieldValue.getName();

			if (ddmFormFieldValueName.equals("TIFF_ORIENTATION")) {
				Value orientationValue = ddmFormFieldValue.getValue();

				return GetterUtil.getInteger(
					orientationValue.getString(
						orientationValue.getDefaultLocale()));
			}
		}

		return _ORIENTATION_NORMAL;
	}

	protected void rotateImage(File file)
		throws ImageResolutionException, IOException {

		AffineTransform affineTransform = new AffineTransform();

		ImageBag imageBag = ImageToolUtil.read(file);

		RenderedImage renderedImage = imageBag.getRenderedImage();

		boolean reverseDimensions = false;

		int orientation = getOrientation(file);

		if (orientation == _ORIENTATION_MIRROR_HORIZONTAL) {
			affineTransform.scale(-1.0, 1.0);

			affineTransform.translate(-renderedImage.getWidth(), 0);
		}
		else if (orientation == _ORIENTATION_MIRROR_HORIZONTAL_ROTATE_90_CW) {
			affineTransform.scale(-1.0, 1.0);

			affineTransform.translate(-renderedImage.getHeight(), 0);

			affineTransform.translate(0, renderedImage.getWidth());

			affineTransform.rotate(Math.toRadians(270));

			reverseDimensions = true;
		}
		else if (orientation == _ORIENTATION_MIRROR_HORIZONTAL_ROTATE_270_CW) {
			affineTransform.rotate(-Math.toRadians(90));

			affineTransform.scale(-1.0, 1.0);

			reverseDimensions = true;
		}
		else if (orientation == _ORIENTATION_MIRROR_VERTICAL) {
			affineTransform.scale(1.0, -1.0);

			affineTransform.translate(0, -renderedImage.getHeight());
		}
		else if (orientation == _ORIENTATION_NORMAL) {
			return;
		}
		else if (orientation == _ORIENTATION_ROTATE_90_CW) {
			affineTransform.translate(renderedImage.getHeight(), 0);

			affineTransform.rotate(Math.toRadians(90));

			reverseDimensions = true;
		}
		else if (orientation == _ORIENTATION_ROTATE_180) {
			affineTransform.translate(
				renderedImage.getWidth(), renderedImage.getHeight());

			affineTransform.rotate(Math.toRadians(180));
		}
		else if (orientation == _ORIENTATION_ROTATE_270_CW) {
			affineTransform.translate(0, renderedImage.getWidth());

			affineTransform.rotate(Math.toRadians(270));

			reverseDimensions = true;
		}
		else {
			return;
		}

		AffineTransformOp affineTransformOp = new AffineTransformOp(
			affineTransform, AffineTransformOp.TYPE_BILINEAR);

		BufferedImage oldBufferedImage = ImageToolUtil.getBufferedImage(
			renderedImage);

		int height = oldBufferedImage.getHeight();
		int width = oldBufferedImage.getWidth();

		if (reverseDimensions) {
			height = oldBufferedImage.getWidth();
			width = oldBufferedImage.getHeight();
		}

		BufferedImage newBufferedImage = affineTransformOp.filter(
			oldBufferedImage,
			new BufferedImage(width, height, oldBufferedImage.getType()));

		ImageIO.write(newBufferedImage, imageBag.getType(), file);
	}

	private static final int _ORIENTATION_MIRROR_HORIZONTAL = 2;

	private static final int _ORIENTATION_MIRROR_HORIZONTAL_ROTATE_90_CW = 7;

	private static final int _ORIENTATION_MIRROR_HORIZONTAL_ROTATE_270_CW = 5;

	private static final int _ORIENTATION_MIRROR_VERTICAL = 4;

	private static final int _ORIENTATION_NORMAL = 1;

	private static final int _ORIENTATION_ROTATE_90_CW = 6;

	private static final int _ORIENTATION_ROTATE_180 = 3;

	private static final int _ORIENTATION_ROTATE_270_CW = 8;

	@Reference
	private DLAppLocalService _dlAppLocalService;

	@Reference
	private DLFileEntryLocalService _dlFileEntryLocalService;

	@Reference
	private com.liferay.portal.kernel.util.File _file;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private MimeTypes _mimeTypes;

	@Reference
	private Portal _portal;

	@Reference
	private PortletFileRepository _portletFileRepository;

	@Reference
	private RawMetadataProcessor _rawMetadataProcessor;

	@Reference
	private UserLocalService _userLocalService;

}