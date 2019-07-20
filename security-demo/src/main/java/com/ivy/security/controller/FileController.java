/**
 * Copyright
 */
package com.ivy.security.controller;

import com.ivy.security.dto.FileInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author ivy on 2019-07-18.
 */
@Slf4j
@RestController
@RequestMapping
public class FileController {

    private static final String folder = "/Users/ivy/temp";

    @PostMapping("/file")
    public FileInfo upload(MultipartFile file) throws IOException {
        log.info(file.getName());
        log.info(file.getOriginalFilename());
        log.info(String.valueOf(file.getSize()));
        File localFile = new File(folder, System.currentTimeMillis() + ".txt");
        file.transferTo(localFile);
        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/file/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
        try (InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));
             OutputStream outputStream = response.getOutputStream()) {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=imooc-test-downloan.txt");

            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
