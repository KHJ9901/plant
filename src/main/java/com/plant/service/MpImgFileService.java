package com.plant.service;

import java.io.File;

import org.apache.commons.fileupload.FileItem;

import com.plant.dto.*;

public interface MpImgFileService {

	public MplantImg mpImgUpload(FileItem item);
	
	public int mpImgDelete(String no, String savefilename, String filepath, String thumb_filename);
	
	MplantThumb setThumbnail(File file, String saveFileName);
	
}
