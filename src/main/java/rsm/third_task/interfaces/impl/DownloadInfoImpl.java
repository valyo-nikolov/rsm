package rsm.third_task.interfaces.impl;

import rsm.third_task.interfaces.DownloadInfo;

public class DownloadInfoImpl implements DownloadInfo {
    private int size;
    private String originalFileName;
    private String fileKey;
    private String downloadURL;

    public DownloadInfoImpl(int size, String originalFileName, String fileKey, String downloadURL) {
        this.size = size;
        this.originalFileName = originalFileName;
        this.fileKey = fileKey;
        this.downloadURL = downloadURL;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getOriginalFileName() {
        return originalFileName;
    }

    @Override
    public String getFileKey() {
        return fileKey;
    }

    @Override
    public String getDownloadURL() {
        return downloadURL;
    }
}
