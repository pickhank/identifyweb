package com.woodare.core.web.common.controller;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.woodare.core.base.BaseController;
import com.woodare.core.jpa.model.Attachment;
import com.woodare.core.jpa.service.IAttachmentDAO;
import com.woodare.core.util.ImageCommonUtils;
import com.woodare.core.web.common.viewdata.AjaxResponseData;
import com.woodare.core.web.common.viewdata.UploadInfo;
import com.woodare.core.web.system.viewdata.attachment.AttachmentViewData;
import com.woodare.framework.data.EResponseState;
import com.woodare.framework.utils.FileCommonUtils;
import com.woodare.framework.utils.JsonUtils;
import com.woodare.framework.utils.SaftyBeanUtils;

/**
 * @author lu_feng
 */
@Controller
@RequestMapping("/common/upload")
public class UploadController extends BaseController {
	private static Logger log = Logger.getLogger(UploadController.class);

	@Autowired
	private IAttachmentDAO attachmentDAO;

	/**
	 * @param searchData
	 * @return
	 */
	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(UploadInfo item) {

		log.debug("Upload form init with: " + item);
		ModelAndView mav = new ModelAndView("/upload/index");
		mav.addObject("item", item);

		return mav;
	}

	/**
	 * @param searchData
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public @ResponseBody String doUploadFile(UploadInfo item) {
		log.debug("Upload form save with: " + item);

		AjaxResponseData<AttachmentViewData> respData = new AjaxResponseData<AttachmentViewData>();

		AttachmentViewData viewData = null;
		try {
			Attachment attachment = getAndSaveFileToDisk(item.getFiledata());
			attachmentDAO.save(attachment);

			viewData = SaftyBeanUtils.cloneTo(attachment, AttachmentViewData.class);
			viewData.setPathUrl(ImageCommonUtils.getImageUrl(attachment.getPath()));

		} catch (Exception e) {
			log.error(e, e);
		}

		if (viewData != null) {
			respData.setState(EResponseState.Successful);
			respData.setPayload(viewData);
		}

		return JsonUtils.toJson(respData);
	}

	/**
	 * @param file
	 * @return
	 * @throws Exception
	 */
	private Attachment getAndSaveFileToDisk(MultipartFile file) throws Exception {
		Attachment attachment = null;

		if (file.getSize() > 0) {
			attachment = new Attachment();

			String filePath = FileCommonUtils.generateFilename(file.getOriginalFilename());

			String absoluteFilePath = ImageCommonUtils.getAbsolutPathWithPrefix(filePath);

			FileCommonUtils.writeFile(absoluteFilePath, file.getInputStream());

			log.info("upload file type:" + file.getContentType());

			// if(file.getContentType().contains("image")){
			// // Resize the image into thumb
			// BufferedImage originalImage =
			// javax.imageio.ImageIO.read(file.getInputStream());
			// }

			attachment.setName(file.getOriginalFilename());
			attachment.setPath(filePath);
			attachment.setExt(FilenameUtils.getExtension(filePath));
			attachment.setValidFlag(true);
		}

		return attachment;
	}
}
