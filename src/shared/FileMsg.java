package shared;

import java.io.File;

public class FileMsg extends Message {

    File file;

    public FileMsg(User from, MessageReceiver to, File file) {
        super(from, to);
        this.file=file;
    }


    public File getFile() {
        return file;
    }
}
