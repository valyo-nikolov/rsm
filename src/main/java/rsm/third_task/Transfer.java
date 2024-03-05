package rsm.third_task;

import org.apache.commons.io.FileUtils;
import rsm.third_task.enums.NotAllowedFileExtensions;
import rsm.third_task.interfaces.impl.DownloadInfoImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Transfer {
    static final int SIZE_THRESHOLD_100_MB = 104857600;

    File rootDirectory = new File("");

    List<File> getFilesList(long packageId) throws IOException {
        String dir = rootDirectory.getAbsolutePath() + "/target/classes/" + packageId;

        try (Stream<Path> stream = Files.list(Paths.get(dir))) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        }
    }

    List<DownloadInfoImpl> getDownloadInfos(long packageId) throws IOException {
        List<File> files = getFilesList(packageId);

        return files.stream()
                .map(file ->  new DownloadInfoImpl(
                        (int) file.length(),
                        file.getName(),
                        UUID.randomUUID().toString(),
                        file.getAbsolutePath()))
                .collect(Collectors.toList());
    }

    List<File> getFilesListFromDownload() throws IOException {
        String dir = rootDirectory.getAbsolutePath() + "/target/classes/download";

        try (Stream<Path> stream = Files.list(Paths.get(dir))) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        }
    }

    List<DownloadInfoImpl> getDownloadInfosFromDownload() throws IOException {
        List<File> files = getFilesListFromDownload();

        return files.stream()
                .map(file ->  new DownloadInfoImpl(
                        (int) file.length(),
                        file.getName(),
                        UUID.randomUUID().toString(),
                        file.getAbsolutePath()))
                .collect(Collectors.toList());
    }

    void downloadFiles(long packageId) throws IOException {
        List<DownloadInfoImpl> downloadInfos = getDownloadInfos(packageId);

        int totalSize = downloadInfos
                .stream()
                .mapToInt(downloadInfo -> downloadInfo.getSize())
                .sum();

        Set<DownloadInfoImpl> duplicates = downloadInfos.stream()
                .filter(downloadInfo -> Collections.frequency(downloadInfos, downloadInfo.getOriginalFileName()) > 1)
                .collect(Collectors.toSet());

        List<DownloadInfoImpl> notAllowedFiles = downloadInfos
                .stream()
                .filter(downloadInfo ->
                        downloadInfo.getOriginalFileName().contains("." + NotAllowedFileExtensions.cmd) ||
                        downloadInfo.getOriginalFileName().contains("." + NotAllowedFileExtensions.com) ||
                        downloadInfo.getOriginalFileName().contains("." + NotAllowedFileExtensions.dll) ||
                        downloadInfo.getOriginalFileName().contains("." + NotAllowedFileExtensions.dmg) ||
                        downloadInfo.getOriginalFileName().contains("." + NotAllowedFileExtensions.exe) ||
                        downloadInfo.getOriginalFileName().contains("." + NotAllowedFileExtensions.iso) ||
                        downloadInfo.getOriginalFileName().contains("." + NotAllowedFileExtensions.jar) ||
                        downloadInfo.getOriginalFileName().contains("." + NotAllowedFileExtensions.js))
                .toList();

        if(totalSize > SIZE_THRESHOLD_100_MB) {
            try {
                String dirDownload = rootDirectory.getAbsolutePath() + "/target/classes/download";
                deleteDirectory(new File(dirDownload));
                throw new Exception("Limit of 100MB total files size to upload is exceeded!");
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } else if (!duplicates.isEmpty()){
            try {
                String dirDownload = rootDirectory.getAbsolutePath() + "/target/classes/download";
                deleteDirectory(new File(dirDownload));
                throw new Exception("There are files with the same names:");
            } catch (Exception e) {
                System.err.println(e.getMessage());
                duplicates.forEach(el -> System.err.println(el.getOriginalFileName()));
            }
        }
        else if (!notAllowedFiles.isEmpty()) {
            try {
                String dirDownload = rootDirectory.getAbsolutePath() + "/target/classes/download";
                deleteDirectory(new File(dirDownload));
                throw new Exception("There are files with not allowed format:");
            } catch (Exception e) {
                System.err.println(e.getMessage());
                notAllowedFiles.forEach(el -> System.err.println(el.getOriginalFileName()));
            }
        } else {
            String dirDownload = rootDirectory.getAbsolutePath() + "/target/classes/download";
            deleteDirectory(new File(dirDownload));
            downloadInfos.stream().forEach(file -> {
                File source = new File(file.getDownloadURL());
                File dest = new File(dirDownload);
                try {
                    FileUtils.copyFileToDirectory(source, dest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    ArrayList<Report> uploadFiles() throws IOException {
        List<DownloadInfoImpl> downloadInfos = getDownloadInfosFromDownload();
        Upload upload = new Upload();
        String dirUpload = rootDirectory.getAbsolutePath() + "/target/classes/upload";
        deleteDirectory(new File(dirUpload));

        Duration totalTime;
        LocalDateTime startTime;
        LocalDateTime endTime;
        Report report = new Report();
        ArrayList<Report> reportList = new ArrayList<>();
        startTime = LocalDateTime.now();
        int[] counterSuccesses = new int[1];
        int[] counterFailures = new int[1];
        downloadInfos.stream().forEach(file -> {
            File source = new File(file.getDownloadURL());

//            InputStream targetStream;
//            try {
//                targetStream = new FileInputStream(source);
//            } catch (FileNotFoundException e) {
//                throw new RuntimeException(e);
//            }


            File dest = new File(dirUpload);
            try {
                report.setName(file.getOriginalFileName());
                report.setTimeToUpload(LocalDateTime.now());
                FileUtils.copyFileToDirectory(source, dest);
                report.setStatus(true);
                counterSuccesses[0]++;
              //  upload.doUpload(file.getFileKey(), targetStream, file.getSize());
            } catch (IOException e) {
                report.setStatus(false);
                counterFailures[0]++;
                throw new RuntimeException(e);
            } finally {
                reportList.add(report);
            }
        });
        endTime = LocalDateTime.now();
        totalTime = Duration.between(startTime, endTime);
        Report.setTotalTime(totalTime);
        Report.setCounterSuccesses(counterSuccesses[0]);
        Report.setCounterFailures(counterFailures[0]);

        return reportList;
    }

    boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }

}
