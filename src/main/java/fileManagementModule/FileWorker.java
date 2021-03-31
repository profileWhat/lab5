package fileManagementModule;

import java.io.*;

import clientManagementModule.OutputDeviceWorker;

/**
 * Class for work with file
 */
public class FileWorker {
    private String fileName;
    private static FileWorker fileWorker;
    private boolean isNotWorkedFile;
    private FileWorker() {
        this.isNotWorkedFile = false;
    }

    public static FileWorker getFileWorker() {
        if (FileWorker.fileWorker == null) FileWorker.fileWorker = new FileWorker();
        return FileWorker.fileWorker;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
        if (fileName.contains("\\dev\\") || fileName.contains("/dev/")) {
            isNotWorkedFile = true;
            OutputDeviceWorker.getDescriber().describeString("Incorrect File, collection will not be load");
        }
    }

    public void write(String s) {
        if (!isNotWorkedFile) {
            try (OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(fileName))) {
                out.write(s);
            } catch (IOException e) {
                isNotWorkedFile = true;
                OutputDeviceWorker.getDescriber().describeException(e);
            } catch (NullPointerException e) {
                isNotWorkedFile = true;
                OutputDeviceWorker.getDescriber().describeFileNotSpecified();
            }
        }
    }

    public String read() {
        StringBuilder data = new StringBuilder();
        if (!isNotWorkedFile) {
            try (FileReader in = new FileReader(fileName)) {
                char[] cBuf = new char[1];
                while (in.read(cBuf) != -1) {
                    data.append(cBuf);
                }
            } catch (IOException e) {
                isNotWorkedFile = true;
                OutputDeviceWorker.getDescriber().describeException(e);
            } catch (NullPointerException e) {
                isNotWorkedFile = true;
                OutputDeviceWorker.getDescriber().describeFileNotSpecified();
            }
        }
        return data.toString();

    }

    public boolean isNotWorkedFile() {
        return isNotWorkedFile;
    }
}
