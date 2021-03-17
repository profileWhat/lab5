package fileManagementModule;

import java.io.*;

import clientManagementModule.OutputDeviceWorker;

public class FileWorker {
    private final String fileName;

    public FileWorker(String fileName) {
        this.fileName = fileName;
    }

    public void write(String s) {
        try (OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(fileName))) {
            out.write(s);
        } catch (IOException e) {
            OutputDeviceWorker.getDescriber().describeException(e);
        }
    }

    public String read() {
        StringBuilder data = new StringBuilder();
        try (FileReader in = new FileReader(fileName)) {
            char[] cBuf = new char[1];
            while (in.read(cBuf) != -1) {
                data.append(cBuf);
            }
        } catch (IOException e) {
            OutputDeviceWorker.getDescriber().describeException(e);
        }
        return data.toString();
    }

}
