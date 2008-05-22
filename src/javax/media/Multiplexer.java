package javax.media;

import javax.media.protocol.ContentDescriptor;
import javax.media.protocol.DataSource;

// Referenced classes of package javax.media:
//            PlugIn, Format, Buffer

public interface Multiplexer
    extends PlugIn
{

    public abstract ContentDescriptor[] getSupportedOutputContentDescriptors(Format aformat[]);

    public abstract Format[] getSupportedInputFormats();

    public abstract int setNumTracks(int i);

    public abstract Format setInputFormat(Format format, int i);

    public abstract int process(Buffer buffer, int i);

    public abstract DataSource getDataOutput();

    public abstract ContentDescriptor setContentDescriptor(ContentDescriptor contentdescriptor);
}