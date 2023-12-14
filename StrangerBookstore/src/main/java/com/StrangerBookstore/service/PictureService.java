package com.StrangerBookstore.service;

import com.StrangerBookstore.model.Picture;
import com.StrangerBookstore.repository.pictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PictureService {
    @Autowired
    pictureRepository pictureRepository;

    public List<Picture> findAll() {
        return pictureRepository.findAll();
    }

    public Optional<Picture> findbyId(Integer id){
        return pictureRepository.findById(id);
    }

    public Picture create(Picture picture) {

        return pictureRepository.save(picture);
    }

    public Picture update(Integer id, Picture picture) {
        Picture model = pictureRepository.findById(id).get();
        model.setPictureFile(picture.getPictureFile());
        model.setPictureId(picture.getPictureId());
        return pictureRepository.save(model);
    }

    public void delete(Integer id) {
        pictureRepository.deleteById(id);
    }


}
