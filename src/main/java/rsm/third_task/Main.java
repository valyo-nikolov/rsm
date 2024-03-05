package rsm.third_task;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        Transfer transfer = new Transfer();
        ArrayList<Report> reportList;

        //Valid for upload files
        transfer.downloadFiles(1);
        reportList = transfer.uploadFiles();
        reportList.forEach(report -> {
            System.out.println(report.getName());
            System.out.println(report.getStatus());
            System.out.println(report.getTimeToUpload());
        });
        System.out.println(Report.getTotalTime());
        System.out.println(Report.getCounterSuccesses());
        System.out.println(Report.getCounterFailures());

        //Exceeds limit of 100MB total files size
        transfer.downloadFiles(2);
        try {
            reportList = transfer.uploadFiles();
            reportList.forEach(report -> {
                System.out.println(report.getName());
                System.out.println(report.getStatus());
                System.out.println(report.getTimeToUpload());
            });
            System.out.println(Report.getTotalTime());
            System.out.println(Report.getCounterSuccesses());
            System.out.println(Report.getCounterFailures());
        } catch (NoSuchFileException ignored) {
        }

        //Not allowed file formats
        transfer.downloadFiles(3);
        try {
            reportList = transfer.uploadFiles();
            reportList.forEach(report -> {
                System.out.println(report.getName());
                System.out.println(report.getStatus());
                System.out.println(report.getTimeToUpload());
            });
            System.out.println(Report.getTotalTime());
            System.out.println(Report.getCounterSuccesses());
            System.out.println(Report.getCounterFailures());
        } catch (NoSuchFileException ignored) {
        }

        //Valid single file
        transfer.downloadFiles(4);
        reportList = transfer.uploadFiles();
        reportList.forEach(report -> {
            System.out.println(report.getName());
            System.out.println(report.getStatus());
            System.out.println(report.getTimeToUpload());
        });
        System.out.println(Report.getTotalTime());
        System.out.println(Report.getCounterSuccesses());
        System.out.println(Report.getCounterFailures());

    }

}
