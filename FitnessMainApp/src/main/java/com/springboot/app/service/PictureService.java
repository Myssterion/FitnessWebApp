package com.springboot.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.app.dto.PictureDto;
import com.springboot.app.model.Picture;
import com.springboot.app.model.Program;
import com.springboot.app.repository.PictureRepository;
import com.springboot.app.repository.ProgramRepository;
import com.springboot.app.utility.EntityToDto;
import com.springboot.app.utility.LoadData;

import static com.springboot.app.utility.EntityToDto.*;

@Service
@Transactional
public class PictureService {

	private final PictureRepository pictureRepository;
	private final ProgramRepository programRepository;

	public PictureService(PictureRepository pictureRepository, ProgramRepository programRepository) {
		this.pictureRepository = pictureRepository;
		this.programRepository = programRepository;
	}

	public List<PictureDto> findAllPictures(Integer id) {
		Program program = new Program();
		program.setId(id);
		List<Picture> pictures = pictureRepository.findByProgram(program);
		return pictures.stream().map(EntityToDto::ConvertToDto).collect(Collectors.toList());
	}

	public PictureDto findById(Integer id) {
		Picture picture = pictureRepository.findById(id).orElse(null);
		if (picture == null)
			return null;
		return ConvertToDto(picture);
	}

	public boolean uploadPictures(Integer programId, MultipartFile[] pictures) {
		Program program = programRepository.findById(programId).orElse(null);
		boolean success = false;
	
		if (program != null) {
			for (var pic : pictures) {
				String fileName = LoadData.UploadFile(pic, "PICTURE");
				if (fileName != null) {
					Picture newPicture = new Picture();
					newPicture.setFileName(fileName);
					newPicture.setMimeType(pic.getContentType());
					newPicture.setFileType(LoadData.PICTURE_PATH + fileName);
					newPicture.setProgram(program);
					
					if (pictureRepository.save(newPicture) != null) {
						 program.getPictures().add(newPicture);
						success = true;
					}
				}
			}
		}

		return success;
	}

	public void removePicture(Picture pic) {
		LoadData.DeletePicture(pic.getFileName());
		if(pic != null)
			pictureRepository.deleteById(pic.getId());
	}

}
