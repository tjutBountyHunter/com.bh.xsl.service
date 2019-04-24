package service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 照片处理
 *
 * @author 高山潍
 */
public interface ImageSave {
    /**
     * 照片储存
     *
     * @param uploadFile
     * @return
     */
    Map uploadPicture(MultipartFile uploadFile);
}
