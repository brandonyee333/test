/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2012 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * ===========================================================================*/
package eu.ibacz.cachemanifest.manifest;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * Represents Application Cache Manifest.
 *
 * @author Jan Andrýsek <jan.andrysek at ibacz.eu>
 */
public class Manifest {

    private static final String OUTPUT_CHARSET = "UTF-8";
    private String name;
    private String manifest;
    private Date dateModified;
    private String md5Hex;

    public Manifest(String name, String cachePart, String networkPart, String fallbackPart) {
        this.name = name;
        this.dateModified = new Date();
        this.manifest = generateManifest(cachePart, networkPart, fallbackPart);
    }

    private String generateManifest(String cachePart, String networkPart, String fallbackPart) {
        StringBuilder sb = new StringBuilder(cachePart.length() + networkPart.length() + fallbackPart.length() + 512);
        sb.append("CACHE MANIFEST\n");
        sb.append("# modified: ");
        sb.append(dateModified.toString());
        sb.append("\n");
        sb.append(cachePart);
        sb.append("\nNETWORK:\n");
        sb.append(networkPart);
        sb.append("\nFALLBACK:\n");
        sb.append(fallbackPart);

        return sb.toString();
    }

    /**
     * Returns name of the manifest.
     *
     * @return the name of the manifest
     */
    public String getName() {
        return name;
    }

    /**
     * Returns timestamp of generation.
     *
     * @return the timestamp of generation
     */
    public long getDateModifiedInMillis() {
        return dateModified.getTime();
    }

    /**
     * Returns content of the manifest.
     *
     * @return the content of the manifest
     */
    public String getManifest() {
        return manifest;
    }

    /**
     * Returns content of the manifest in form of a byte array.
     *
     * @return the content of the manifest as a byte array
     */
    public byte[] getManifestBytes() {
        try {
            return getManifest().getBytes(OUTPUT_CHARSET);
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Returns MD5 hash of the content of the manifest.
     *
     * @return the MD5 hash of the content
     */
    public String getMd5Hex() {
        if (md5Hex == null) {
            md5Hex = computeMd5();
        }
        return md5Hex;
    }

    private String computeMd5() {
        return Util.md5String(getManifestBytes());
    }
}
