package devnguyen.net.application.webapp;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StorageController {
    @Value("${hadoop.fs.defaultFS}")
    private String hadoopDefaultFS;

    @Value("${hadoop.home.path}")
    private String hadoopHomePath;

    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            Configuration configuration = new Configuration();
            configuration.set("fs.defaultFS", hadoopDefaultFS);
            System.setProperty("hadoop.home.dir", hadoopHomePath);

            FileSystem fs = FileSystem.get(configuration);
            Path path = new Path("/user/your_username/" + file.getOriginalFilename());

            try (OutputStream os = fs.create(path)) {
                os.write(file.getBytes());
            }

            fs.close();
            return ResponseEntity.ok("File uploaded successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
        }
    }

    @PostMapping("/download")
    @ResponseBody
    public ResponseEntity<byte[]> handleFileDownload(@RequestParam("filename") String filename) {
        try {
            Configuration configuration = new Configuration();
            configuration.set("fs.defaultFS", hadoopDefaultFS);
            System.setProperty("hadoop.home.dir", hadoopHomePath);

            FileSystem fs = FileSystem.get(configuration);
            Path path = new Path("/user/your_username/" + filename);

            try (InputStream is = fs.open(path)) {
                byte[] content = is.readAllBytes();

                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);

                fs.close();
                return new ResponseEntity<>(content, headers, HttpStatus.OK);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
