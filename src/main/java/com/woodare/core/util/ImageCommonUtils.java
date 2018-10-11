package com.woodare.core.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import com.woodare.framework.utils.Base64Utils;
import com.woodare.framework.utils.FileCommonUtils;
import com.woodare.framework.utils.StringUtils;
import com.woodare.framework.utils.SysProperties;

import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

/**
 * @author lu_feng
 */
public class ImageCommonUtils {
	private static final String EDITOR_PREFIX_MARKER = "___EDITOR_PREFIX___";

	/**
	 * @param relativePath
	 * @return
	 */
	public static String getImageUrl(String relativePath) {
		return getImageUrl(relativePath, null);
	}

	/**
	 * @param relativePath
	 * @param defaultImageKey
	 * @return
	 */
	public static String getImageUrl(String relativePath, String defaultImageKey) {
		String imageUrl = SysProperties.getInstance().getProperty("root.imagesources.url");
		if (relativePath != null) {
			imageUrl += "/" + relativePath;
		}
		else if (defaultImageKey != null) {
			imageUrl += SysProperties.getInstance().getProperty(defaultImageKey);
		}
		return imageUrl;
	}

	/**
	 * @param relativePath
	 * @param defaultImageKey
	 * @return
	 */
	public static String getDownloadUrl(String fileId) {
		String rootUrl = SysProperties.getInstance().getProperty("root.base.url");
		return rootUrl + "/file-download?fileId=" + fileId;
	}

	/**
	 * @param relativePath
	 * @param defaultImageKey
	 * @return
	 */
	public static String getBaseUrl(String uriPath) {
		String rootUrl = SysProperties.getInstance().getProperty("root.base.url");
		return rootUrl + uriPath;
	}

	/**
	 * @param url
	 * @param imagePath
	 * @return
	 */
	public static ImageSize downloadAndSaveImageWithUrl(String strUrl, String imagePath) {
		ImageSize ret = null;
		BufferedImage image = null;
		try {
			URL url = new URL(strUrl);
			image = ImageIO.read(url);

			File file = new File(imagePath);
			FileCommonUtils.makeDir(file.getParentFile());
			ImageIO.write(image, "png", file);

			ret = new ImageSize();

		} catch (IOException e) {
		}

		return ret;
	}

	/**
	 * @return
	 */
	public static String getImageUrlForBaseEditor() {
		String imageUrl = SysProperties.getInstance().getProperty("root.imagesources.default.url");
		imageUrl += "/" + SysProperties.getInstance().getProperty("root.uploadfiles.ueditor");
		return imageUrl;
	}

	/**
	 * @return
	 */
	public static String getUrlForMapData() {
		String mapDataUrl = SysProperties.getInstance().getProperty("root.imagesources.default.url");
		mapDataUrl += "/" + SysProperties.getInstance().getProperty("file.path.map.json", "uploads/map.json");
		return mapDataUrl;
	}

	/**
	 * @return
	 */
	public static String getImageUrlForEditor(String relativePath) {
		return getImageUrlForBaseEditor() + "/" + relativePath;
	}

	/**
	 * @return
	 */
	public static String getImageUrlForBaseQrcode() {
		String imageUrl = SysProperties.getInstance().getProperty("root.imagesources.default.url");
		imageUrl += "/" + SysProperties.getInstance().getProperty("root.files.qrcodes");
		return imageUrl;
	}

	/**
	 * @return
	 */
	public static String getImageUrlForQrcode(String relativePath) {
		return getImageUrlForBaseQrcode() + "/" + relativePath;
	}

	/**
	 * @return
	 */
	public static String getUrlForBaseZip() {
		String imageUrl = SysProperties.getInstance().getProperty("root.imagesources.default.url");
		imageUrl += "/" + SysProperties.getInstance().getProperty("root.files.zip.publish");
		return imageUrl;
	}

	/**
	 * @return
	 */
	public static String getUrlForBaseZip(String relativePath) {
		return getUrlForBaseZip() + "/" + relativePath;
	}

	/**
	 * @param relativePath
	 * @return
	 */
	public static String getCropImageUrl(String relativePath, Integer width, Integer height, String defaultImageKey) {
		String cropImagePath = getCropImageRelativePath(relativePath, width, height);
		return getImageUrl(cropImagePath, defaultImageKey);
	}

	/**
	 * @param relativePath
	 * @return
	 */
	public static String getCropImageUrl(String relativePath, Integer width, Integer height) {
		return getCropImageUrl(relativePath, width, height, null);
	}

	/**
	 * @param relativePath
	 * @return
	 */
	public static String getCropImageUrl(String relativePath) {
		return getCropImageUrl(relativePath, null, null, null);
	}

	/**
	 * @param imagePath
	 * @param isTempFlag
	 * @return
	 */
	/**
	 * @param imagePath
	 * @param isTempFlag
	 * @return
	 */
	public static String getAbsolutPathWithPrefix(String imagePath) {
		String prefixPath = SysProperties.getInstance().getProperty("root.uploadfiles.valid");
		String parentPath = SysProperties.getInstance().getProperty("root.uploadfiles.basedir");
		if (StringUtils.isNotEmpty(parentPath)) {
			prefixPath = FileCommonUtils.getFullPath(parentPath, prefixPath);
		}
		return FileCommonUtils.getFullPath(prefixPath, imagePath);
	}

	/**
	 * @return
	 */
	public static String getAbsolutPathForUeditor() {
		String prefixPath = SysProperties.getInstance().getProperty("root.uploadfiles.basedir");
		return FileCommonUtils.getFullPath(prefixPath, SysProperties.getInstance().getProperty("root.uploadfiles.ueditor"));
	}

	/**
	 * @return
	 */
	public static String getAbsolutPathForUeditor(String relativePath) {
		String prefixPath = SysProperties.getInstance().getProperty("root.uploadfiles.ueditor");
		String parentPath = SysProperties.getInstance().getProperty("root.uploadfiles.basedir");
		if (StringUtils.isNotEmpty(parentPath)) {
			prefixPath = FileCommonUtils.getFullPath(parentPath, prefixPath);
		}
		return FileCommonUtils.getFullPath(prefixPath, relativePath);
	}

	/**
	 * @return
	 */
	public static String getAbsolutPathForQrcode() {
		String prefixPath = SysProperties.getInstance().getProperty("root.uploadfiles.basedir");
		return FileCommonUtils.getFullPath(prefixPath, SysProperties.getInstance().getProperty("root.files.qrcodes"));
	}

	/**
	 * @return
	 */
	public static String getAbsolutPathForQrcode(String relativePath) {
		String prefixPath = SysProperties.getInstance().getProperty("root.files.qrcodes");
		String parentPath = SysProperties.getInstance().getProperty("root.uploadfiles.basedir");
		if (StringUtils.isNotEmpty(parentPath)) {
			prefixPath = FileCommonUtils.getFullPath(parentPath, prefixPath);
		}
		return FileCommonUtils.getFullPath(prefixPath, relativePath);
	}

	/**
	 * @param imagePath
	 * @return
	 */
	private static String getImagePathWithSize(String imagePath, Integer width, Integer height) {
		String absPath = imagePath;
		if (StringUtils.isNotEmpty(imagePath)) {
			if (width != null && height != null) {
				absPath = imagePath.substring(0, imagePath.lastIndexOf(".")) + "_" + width + "_" + height + imagePath.substring(imagePath.lastIndexOf("."));
			}
		}
		return absPath;
	}

	/**
	 * @param sourceFilePath
	 * @param targetDirectory
	 * @throws IOException
	 */
	public static String getCropImageRelativePath(String relativePath, final Integer width, final Integer height) {
		if (width == null || height == null) {
			return relativePath;
		}
		String sourceFilePath = getAbsolutPathWithPrefix(relativePath);
		String targetRelativePath = getImagePathWithSize(relativePath, width, height);
		String targetFilePath = getAbsolutPathWithPrefix(targetRelativePath);

		File targetFile = new File(targetFilePath);
		if (!targetFile.exists()) {
			try {
				Builder<File> thumbnail = Thumbnails.of(sourceFilePath);

				List<File> retLst = thumbnail.crop(Positions.CENTER).size(width, height).useOriginalFormat().asFiles(new Rename() {
					@Override
					public String apply(String fileName, ThumbnailParameter param) {
						return appendSuffix(fileName, "_" + width + "_" + height);
					}
				});

				if (!retLst.get(0).getAbsoluteFile().equals(targetFilePath)) {
					org.apache.commons.io.FileUtils.moveFile(retLst.get(0), targetFile);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return targetRelativePath;
	}

	public static String trimEditorPrefixUrl(String str) {
		if (StringUtils.isEmpty(str)) {
			return str;
		}
		String absBaseEditorUrl = getImageUrlForBaseEditor();
		int prefixLen = absBaseEditorUrl.length();
		Pattern p = Pattern.compile("(<img[^>]+src\\s*=\\s*['\"])([^'\"]+)(['\"][^>]*>)");
		Matcher m = p.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			String url = m.group(2);
			if (url.startsWith(absBaseEditorUrl)) {
				url = url.substring(prefixLen);
			}
			m.appendReplacement(sb, "$1" + EDITOR_PREFIX_MARKER + url + "$3");
		}
		m.appendTail(sb);
		return sb.toString();
	}

	public static String addEditorPrefixUrl(String str) {
		if (StringUtils.isEmpty(str)) {
			return str;
		}
		String absBaseEditorUrl = getImageUrlForBaseEditor();
		int prefixLen = EDITOR_PREFIX_MARKER.length();
		Pattern p = Pattern.compile("(<img[^>]+src\\s*=\\s*['\"])([^'\"]+)(['\"][^>]*>)");
		Matcher m = p.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			String url = m.group(2);
			if (url.startsWith(EDITOR_PREFIX_MARKER)) {
				url = url.substring(prefixLen);
			}
			m.appendReplacement(sb, "$1" + absBaseEditorUrl + url + "$3");
		}
		m.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
	 * 
	 * @param imgFile
	 * @return
	 */
	public static String getImageBase64String(String imgFile) {
		InputStream in = null;
		byte[] data = null;
		
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		// 返回Base64编码过的字节数组字符串
		return Base64Utils.encode(data);
	}

	/**
	 * @author lu_feng
	 */
	public static class ImageSize {
		private int w;
		private int h;

		/**
		 * @return the w
		 */
		public int getW() {
			return w;
		}

		/**
		 * @param w
		 *            the w to set
		 */
		public void setW(int w) {
			this.w = w;
		}

		/**
		 * @return the h
		 */
		public int getH() {
			return h;
		}

		/**
		 * @param h
		 *            the h to set
		 */
		public void setH(int h) {
			this.h = h;
		}
	}

	public static void main(String[] arg) throws IOException {
		String txt = "<body class=\"view\" contenteditable=\"true\" spellcheck=\"false\" style=\"overflow-y: hidden; height: 139px; cursor: text;\"><p>"
				+ "<img src=\"http://localhost:8080/city-logical-service/uploads/ueditor/20130814/99031376444647960.jpg\" title=\"20dceaf33f0e50acb294b0285a4a11ef.jpg\" >"
				+ "<img src=\"http://localhost:8080/city-logical-service/uploads/ueditor/20130814/60671376448273555.jpg\" title=\"iphone5-??.jpg\">&#8203;"
				+ "<img src=\"http://localhost:8080/city-logical-service/uploads/ueditor/20130814/94061376444751511.jpg\" title=\"aa042d2a2647f1ca3762b582c37091b0.jpg\" ></p></body>";

		System.out.println(trimEditorPrefixUrl(txt));
		;

		// ImageInputStream iis = ImageIO.createImageInputStream(new
		// File("C:\\Users\\lu_feng\\Desktop\\favicon.jpg"));
		// Iterator iter = ImageIO.getImageReaders(iis);
		// if (iter.hasNext()) {
		// ImageReader reader = (ImageReader)iter.next();
		// reader.setInput(iis);
		// BufferedImage image = reader.read(0);
		// String formatName = reader.getFormatName();
		// System.out.println(formatName);
		// }
		//
		// System.out.println(getCropImageRelativePath("C:\\Users\\lu_feng\\Desktop\\favicon.jpg",
		// 2, 2));
	}
}
