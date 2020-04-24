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

package com.liferay.osb.loop.web.internal.image;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.osb.loop.asset.entry.set.util.AssetEntrySetImageUtil;
import com.liferay.osb.loop.constants.LoopConstants;
import com.liferay.osb.loop.constants.LoopPortletKeys;
import com.liferay.osb.loop.web.internal.configuration.LoopWebConfigurationUtil;
import com.liferay.osb.loop.web.internal.configuration.LoopWebConfigurationValues;
import com.liferay.osb.loop.web.internal.constants.LoopRoleConstants;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.image.ImageBag;
import com.liferay.portal.kernel.image.ImageToolUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ResourceActionLocalServiceUtil;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

import java.io.File;

import java.util.Iterator;

/**
 * @author Matthew Kong
 */
public class LoopImageUtil {

	public static void addResourcePermission(
			long companyId, long fileEntryId, String roleName)
		throws Exception {

		Role role = RoleLocalServiceUtil.getRole(companyId, roleName);

		if (ResourcePermissionLocalServiceUtil.hasResourcePermission(
				companyId, DLFileEntry.class.getName(),
				ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(fileEntryId),
				role.getRoleId(), ActionKeys.VIEW)) {

			return;
		}

		long resourcePermissionId = CounterLocalServiceUtil.increment();

		ResourcePermission resourcePermission =
			ResourcePermissionLocalServiceUtil.createResourcePermission(
				resourcePermissionId);

		resourcePermission.setCompanyId(companyId);
		resourcePermission.setName(DLFileEntry.class.getName());
		resourcePermission.setScope(ResourceConstants.SCOPE_INDIVIDUAL);
		resourcePermission.setPrimKey(String.valueOf(fileEntryId));
		resourcePermission.setRoleId(role.getRoleId());

		ResourceAction resourceAction =
			ResourceActionLocalServiceUtil.getResourceAction(
				DLFileEntry.class.getName(), ActionKeys.VIEW);

		resourcePermission.setActionIds(resourceAction.getBitwiseValue());

		ResourcePermissionLocalServiceUtil.addResourcePermission(
			resourcePermission);
	}

	public static void addResourcePermissions(JSONObject jsonObject)
		throws Exception {

		JSONObject fileEntryIdsJSONObject = jsonObject.getJSONObject(
			"fileEntryIds");

		Iterator<String> iterator = fileEntryIdsJSONObject.keys();

		while (iterator.hasNext()) {
			String key = iterator.next();

			DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.getFileEntry(
				fileEntryIdsJSONObject.getLong(key));

			addResourcePermission(
				dlFileEntry.getCompanyId(), dlFileEntry.getFileEntryId(),
				getRoleName(key));
		}
	}

	public static void deleteDLFileEntries(JSONObject jsonObject)
		throws Exception {

		JSONObject fileEntryIdsJSONObject = jsonObject.getJSONObject(
			"fileEntryIds");

		if (fileEntryIdsJSONObject == null) {
			return;
		}

		Iterator<String> iterator = fileEntryIdsJSONObject.keys();

		while (iterator.hasNext()) {
			long fileEntryId = fileEntryIdsJSONObject.getLong(iterator.next());

			DLFileEntry dlFileEntry =
				DLFileEntryLocalServiceUtil.fetchDLFileEntry(fileEntryId);

			if (dlFileEntry != null) {
				DLAppLocalServiceUtil.deleteFileEntry(fileEntryId);
			}
		}
	}

	public static JSONObject getKeywordPayloadJSONObject(
			String keywordPayload, long classNameId, long classPK,
			String[] imageTypes, String imageTypeKey)
		throws Exception {

		JSONObject processedKeywordPayloadJSONObject =
			JSONFactoryUtil.createJSONObject();

		JSONObject keywordPayloadJSONObject = JSONFactoryUtil.createJSONObject(
			keywordPayload);

		JSONObject cropRegionJSONObject =
			keywordPayloadJSONObject.getJSONObject("cropRegion");

		JSONObject fileEntryIdsJSONObject =
			keywordPayloadJSONObject.getJSONObject("fileEntryIds");

		long fileEntryId = fileEntryIdsJSONObject.getLong(
			LoopConstants.IMAGE_TYPE_RAW);

		FileEntry rawFileEntry = PortletFileRepositoryUtil.getPortletFileEntry(
			fileEntryId);

		ImageBag imageBag = ImageToolUtil.read(
			DLFileEntryLocalServiceUtil.getFileAsStream(
				fileEntryId, rawFileEntry.getVersion()));

		imageBag = cropImage(cropRegionJSONObject, imageBag);

		for (String imageType : imageTypes) {
			FileEntry fileEntry = null;

			if (imageType.equals(LoopConstants.IMAGE_TYPE_RAW)) {
				fileEntry = rawFileEntry;
			}
			else {
				String imageMaxSize = LoopWebConfigurationUtil.get(
					imageTypeKey, new Filter(imageType));

				if (imageType.equals("email")) {
					fileEntry = AssetEntrySetImageUtil.addScaledImageFileEntry(
						rawFileEntry.getUserId(), classNameId, classPK,
						LoopPortletKeys.LOOP, circleCropImage(imageBag),
						imageType, rawFileEntry.getDescription(), imageMaxSize);
				}
				else {
					fileEntry = AssetEntrySetImageUtil.addScaledImageFileEntry(
						rawFileEntry.getUserId(), classNameId, classPK,
						LoopPortletKeys.LOOP, imageBag, imageType,
						rawFileEntry.getDescription(), imageMaxSize);
				}
			}

			processedKeywordPayloadJSONObject =
				AssetEntrySetImageUtil.getImageJSONObject(
					processedKeywordPayloadJSONObject, fileEntry, imageType);
		}

		return processedKeywordPayloadJSONObject;
	}

	public static String getRoleName(String key) {
		if (key.equals("email")) {
			return RoleConstants.GUEST;
		}

		return LoopRoleConstants.LOOP_PERSON;
	}

	public static void updateDLFileEntry(
			String keywordPayload, long classPK, String imageType)
		throws Exception {

		JSONObject keywordPayloadJSONObject = JSONFactoryUtil.createJSONObject(
			keywordPayload);

		JSONObject fileEntryIdsJSONObject =
			keywordPayloadJSONObject.getJSONObject("fileEntryIds");

		long fileEntryId = fileEntryIdsJSONObject.getLong(imageType);

		DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.getFileEntry(
			fileEntryId);

		dlFileEntry.setClassPK(classPK);

		DLFileEntryLocalServiceUtil.updateDLFileEntry(dlFileEntry);
	}

	public static JSONObject uploadImagePayload(
			long userId, long classNameId, long classPK, String fileName,
			byte[] bytes)
		throws Exception {

		if (isNotValidFileExtension(fileName)) {
			return JSONFactoryUtil.createJSONObject();
		}

		File file = FileUtil.createTempFile(bytes);

		try {
			return AssetEntrySetImageUtil.addImageFile(
				userId, classNameId, classPK, LoopPortletKeys.LOOP, file);
		}
		finally {
			FileUtil.delete(file);
		}
	}

	public static JSONObject uploadTempImagePayload(
			long userId, long classNameId, File file)
		throws Exception {

		if (isNotValidFileExtension(file.getName())) {
			return JSONFactoryUtil.createJSONObject();
		}

		return AssetEntrySetImageUtil.addImageFile(
			userId, classNameId, 0L, LoopPortletKeys.LOOP, file);
	}

	protected static ImageBag circleCropImage(ImageBag imageBag) {
		RenderedImage renderedImage = imageBag.getRenderedImage();

		BufferedImage bufferedImage = ImageToolUtil.getBufferedImage(
			renderedImage);

		int diameter = renderedImage.getWidth();

		TexturePaint texturePaint = new TexturePaint(
			bufferedImage, new Rectangle(0, 0, diameter, diameter));

		bufferedImage = new BufferedImage(
			diameter, diameter, BufferedImage.TYPE_INT_RGB);

		Graphics2D graphics2D = (Graphics2D)bufferedImage.getGraphics();

		graphics2D.setRenderingHint(
			RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2D.setRenderingHint(
			RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		graphics2D.setBackground(Color.WHITE);

		graphics2D.fillRect(0, 0, diameter, diameter);

		graphics2D.setPaint(texturePaint);

		graphics2D.fill(new Ellipse2D.Double(0, 0, diameter, diameter));

		graphics2D.drawImage(bufferedImage, 0, 0, diameter, diameter, null);

		graphics2D.dispose();

		return new ImageBag(bufferedImage, imageBag.getType());
	}

	protected static ImageBag cropImage(
		JSONObject cropRegionJSONObject, ImageBag imageBag) {

		if (cropRegionJSONObject == null) {
			return imageBag;
		}

		int height = cropRegionJSONObject.getInt("height");
		int width = cropRegionJSONObject.getInt("width");

		if ((height == 0) || (width == 0)) {
			return imageBag;
		}

		RenderedImage renderedImage = imageBag.getRenderedImage();

		BufferedImage bufferedImage = ImageToolUtil.getBufferedImage(
			renderedImage);

		int y = cropRegionJSONObject.getInt("y");

		if ((height + y) > renderedImage.getHeight()) {
			height = renderedImage.getHeight() - y;
		}

		int x = cropRegionJSONObject.getInt("x");

		if ((width + x) > renderedImage.getWidth()) {
			width = renderedImage.getWidth() - x;
		}

		Rectangle rectangle = new Rectangle(width, height);

		Rectangle croppedRectangle = rectangle.intersection(
			new Rectangle(renderedImage.getWidth(), renderedImage.getHeight()));

		return new ImageBag(
			bufferedImage.getSubimage(
				x, y, croppedRectangle.width, croppedRectangle.height),
			imageBag.getType());
	}

	protected static boolean isNotValidFileExtension(String fileName) {
		String extension = StringPool.PERIOD + FileUtil.getExtension(fileName);

		return !ArrayUtil.contains(
			LoopWebConfigurationValues.IMAGE_EXTENSIONS, extension);
	}

}