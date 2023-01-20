package com.glasses.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.glasses.entity.Glasses;
import com.glasses.repository.GlassesRepository;
import com.glasses.util.GlassesUtil;

@Service
public class GlassesService {

	@Autowired
	private GlassesRepository glassesRepository;

	public String uploadImage(MultipartFile file) throws IOException {
		Glasses glasses = glassesRepository
				.save(Glasses.builder().name(file.getOriginalFilename()).type(file.getContentType())
						.imageData(GlassesUtil.compressImage(file.getBytes())).build());

		if (glasses != null) {
			return "file uploaded successfully :" + file.getOriginalFilename();
		}
		return null;
	}

	public byte[] domnloadImage(String fileName) {
		Optional<Glasses> dbGlasses = glassesRepository.findByname(fileName);
		byte[] images = GlassesUtil.decompressImage(dbGlasses.get().getImageData());
		return images;
	}

}
