/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2012 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * ===========================================================================*/
package eu.ibacz.cachemanifest.manifest;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.LayoutSet;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utils.
 *
 * @author Tomáš Král (tomas.kral@ibacz.eu)
 */
public class Util {

    /**
     * Initializes manifest for the given layout set.
     *
     * @param layoutSet the layout set to generate manifest for
     */
    public static void initializeManifest(LayoutSet layoutSet) {
        try {
            ManifestInitializer.getInstance().initializeManifest(layoutSet);
        } catch (SystemException ex) {
            throw new RuntimeException(ex);
        } catch (PortalException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Initializes all manifests for all layout sets.
     */
    public static void initializeAllManifests() {
        try {
            ManifestInitializer.getInstance().initializeAllManifests();
        } catch (SystemException ex) {
            throw new RuntimeException(ex);
        } catch (PortalException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Computes MD5 hash of the provided data and returns it in hexadecimal string representation.
     *
     * @param data the byte array for hash computation
     * @return hexadecimal string representation of MD5 hash of the data
     */
    public static String md5String(byte[] data) {
        byte[] md5Bytes = md5Bytes(data);

        return toHexString(md5Bytes);
    }

    /**
     * Computes MD5 hash of the provided data.
     *
     * @param data the byte array for hash computation
     * @return MD5 hash of the data
     */
    public static byte[] md5Bytes(byte[] data) {
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            return md.digest(data);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Encodes the provided byte array to a hexadecimal encoded string representation.
     *
     * @param data the byte array to be encoded
     * @return hexadecimal encoded string
     */
    public static String toHexString(byte[] data) {
        StringBuilder sb = new StringBuilder();

        for (byte b : data) {
            String hex = Integer.toHexString(0xFF & b);
            if (hex.length() == 1) {
                sb.append("0");
            }
            sb.append(hex);
        }

        return sb.toString();
    }

    /**
     * Generates Application Cache Manifest name for the given layout set.
     *
     * @param layoutSet the layout set to generate manifest name for
     * @return
     */
    public static String generateManifestName(LayoutSet layoutSet) {
        try {
            return layoutSet.getGroup().getFriendlyURL().substring(1) + "-" + (layoutSet.isPrivateLayout() ? "private" : "public");
        } catch (PortalException e) {
            throw new RuntimeException(e);
        } catch (SystemException e) {
            throw new RuntimeException(e);
        }
    }
}
