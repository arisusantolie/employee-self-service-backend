package com.project.ess.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ess.dto.FileUploadDTO;
import com.project.ess.execptions.CustomGenericException;
import com.project.ess.model.UploadFileResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadFileService {


    @Value("${path.upload}")
    public String UPLOAD_DIR;

    private Path fileStorageLocation;
    private static final List<String> contentTypes = Arrays.asList("image/png", "image/jpeg", "image/jpg","application/pdf");
    public UploadFileResponse storeFile(MultipartFile file){
        String fileName= StringUtils.cleanPath(file.getOriginalFilename());
        fileName=fileName.toLowerCase().replace(" ","-");
        String contentType=file.getContentType();
        fileStorageLocation= Paths.get(UPLOAD_DIR)
                .toAbsolutePath().normalize();
        try{
            if(fileName.contains("..")){
                throw new CustomGenericException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            String fileContentType = file.getContentType();
           if(contentTypes.contains(fileContentType)){
               System.out.println(fileContentType);
               Path targetLocation = fileStorageLocation.resolve(fileName);
               Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

               String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                       .path("api/v1/downloadFile/")
                       .path(fileName)
                       .toUriString();

//            FileUploadDTO newData=new FileUploadDTO();
               return new UploadFileResponse(UPLOAD_DIR+fileName,contentType,fileName);
//            userProfileUploadDummyControllerRepository.save(newData);
//
//            return new UserProfileUploadDummyDTO(fileName,contentType,fileDownloadUri);
           }else{
               throw new CustomGenericException("Sorry! Format File Was Not Accepted " + fileName);
           }

        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomGenericException("Could not store file " + fileName + ". Please try again!");
        }
    }

    public Resource loadFileAsResource(String fileName) {
        fileStorageLocation = Paths.get(UPLOAD_DIR)
                .toAbsolutePath().normalize();
        try {
            Path filePath = fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new CustomGenericException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new CustomGenericException("File not found " + fileName);
        }
    }
}
