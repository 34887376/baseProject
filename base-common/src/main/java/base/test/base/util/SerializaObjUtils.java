package base.test.base.util;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.log4j.Logger;

/**
 * Base class for any transcoders that may want to work with serialized or
 * compressed data.
 */
public class SerializaObjUtils {
	
	static Logger logger = Logger.getLogger("SerializaObjUtils");

	/**
	 * Default compression threshold value.
	 */
	public static final int DEFAULT_COMPRESSION_THRESHOLD = 16384;

	private static final String DEFAULT_CHARSET = "UTF-8";

	protected int compressionThreshold = DEFAULT_COMPRESSION_THRESHOLD;
	public static String charset = DEFAULT_CHARSET;

	/**
	 * Set the compression threshold to the given number of bytes. This
	 * transcoder will attempt to compress any data being stored that's larger
	 * than this.
	 * 
	 * @param to
	 *            the number of bytes
	 */
	public void setCompressionThreshold(int to) {
		compressionThreshold = to;
	}

	/**
	 * Set the character set for string value transcoding (defaults to UTF-8).
	 */
	public static void setCharset(String to) {
		// Validate the character set.
		try {
			new String(new byte[97], to);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		charset = to;
	}

	/**
	 * Get the bytes representing the given serialized object.
	 */
	public static byte[] serialize(Object o) {
		if (o == null) {
			throw new NullPointerException("Can't serialize null");
		}
		byte[] rv = null;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream os = null;
		try {
			bos = new ByteArrayOutputStream();
			os = new ObjectOutputStream(bos);
			os.writeObject(o);
			os.close();
			bos.close();
			rv = bos.toByteArray();
		} catch (IOException e) {
			throw new IllegalArgumentException("Non-serializable object", e);
		} finally {
			close(os);
			close(bos);
		}
		return rv;
	}

	/**
	 * Get the object represented by the given serialized bytes.
	 */
	public static Object deserialize(byte[] in) {
		Object rv = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream is = null;
		try {
			if (in != null) {
				bis = new ByteArrayInputStream(in);
				is = new ObjectInputStream(bis);
				rv = is.readObject();
				is.close();
				bis.close();
			}
		} catch (IOException e) {
			logger.warn("Caught IOException decoding %d bytes of data",e);
		} catch (ClassNotFoundException e) {
			logger.warn("Caught CNFE decoding %d bytes of data", e);
		} finally {
			close(is);
			close(bis);
		}
		return rv;
	}

	/**
	 * Compress the given array of bytes.
	 */
	public static byte[] compress(byte[] in) {
		if (in == null) {
			throw new NullPointerException("Can't compress null");
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		GZIPOutputStream gz = null;
		try {
			gz = new GZIPOutputStream(bos);
			gz.write(in);
		} catch (IOException e) {
			throw new RuntimeException("IO exception compressing data", e);
		} finally {
			close(gz);
			close(bos);
		}
		byte[] rv = bos.toByteArray();
		return rv;
	}

	/**
	 * Decompress the given array of bytes.
	 * 
	 * @return null if the bytes cannot be decompressed
	 */
	public static byte[] decompress(byte[] in) {
		ByteArrayOutputStream bos = null;
		if (in != null) {
			ByteArrayInputStream bis = new ByteArrayInputStream(in);
			bos = new ByteArrayOutputStream();
			GZIPInputStream gis = null;
			try {
				gis = new GZIPInputStream(bis);

				byte[] buf = new byte[8192];
				int r = -1;
				while ((r = gis.read(buf)) > 0) {
					bos.write(buf, 0, r);
				}
			} catch (IOException e) {
				logger.warn("Failed to decompress data", e);
				bos = null;
			} finally {
				close(gis);
				close(bis);
				close(bos);
			}
		}
		return bos == null ? null : bos.toByteArray();
	}

	/**
	 * Decode the string with the current character set.
	 */
	public static String decodeString(byte[] data) {
		String rv = null;
		try {
			if (data != null) {
				rv = new String(data, charset);
			}
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		return rv;
	}

	/**
	 * Encode a string into the current character set.
	 */
	public static byte[] encodeString(String in) {
		byte[] rv = null;
		try {
			rv = in.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		return rv;
	}

	/**
	 * Close a closeable.
	 */
	public static void close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (Exception e) {
				logger.info("Unable to close",  e);
			}
		}
	}
}
