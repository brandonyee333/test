/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2012 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * ===========================================================================*/
package com.liferay.portal.cachemanifest.manifest;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Represents Application Cache Manifest.
 *
 * @author Jan Andrýsek <jan.andrysek at ibacz.eu>
 */
public class Manifest {

    public Manifest(String name, String cachePart, String networkPart, String fallbackPart) {
        this._name = name;
        this._dateModified = new Date();
        this._manifest = generateManifest(cachePart, networkPart, fallbackPart);
    }

    private String generateManifest(String cachePart, String networkPart, String fallbackPart) {
        StringBuilder sb = new StringBuilder(cachePart.length() + networkPart.length() + fallbackPart.length() + 512);
        sb.append("CACHE MANIFEST\n");
        sb.append("# modified: ");
        sb.append(_dateModified.toString());
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
        return _name;
    }

    /**
     * Returns timestamp of generation.
     *
     * @return the timestamp of generation
     */
    public long getDateModifiedInMillis() {
        return _dateModified.getTime();
    }

    /**
     * Returns content of the manifest.
     *
     * @return the content of the manifest
     */
    public String getManifest() {
        return _manifest;
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
        if (_md5Hex == null) {
            _md5Hex = computeMd5();
        }
        return _md5Hex;
    }

    private String computeMd5() {
        return Util.md5String(getManifestBytes());
    }



    private static final String OUTPUT_CHARSET = "UTF-8";

    private String _name;
    private String _manifest;
    private Date _dateModified;
    private String _md5Hex;
}
