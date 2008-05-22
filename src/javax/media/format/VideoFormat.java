package javax.media.format;

import java.awt.Dimension;
import javax.media.Format;

public class VideoFormat extends Format
{

    protected Dimension size;
    protected int maxDataLength;
    protected float frameRate;
    public static final String CINEPAK = "cvid";
    public static final String JPEG = "jpeg";
    public static final String JPEG_RTP = "jpeg/rtp";
    public static final String MPEG = "mpeg";
    public static final String MPEG_RTP = "mpeg/rtp";
    public static final String H261 = "h261";
    public static final String H261_RTP = "h261/rtp";
    public static final String H263 = "h263";
    public static final String H263_RTP = "h263/rtp";
    public static final String H263_1998_RTP = "h263-1998/rtp";
    public static final String RGB = "rgb";
    public static final String YUV = "yuv";
    public static final String IRGB = "irgb";
    public static final String SMC = "smc";
    public static final String RLE = "rle";
    public static final String RPZA = "rpza";
    public static final String MJPG = "mjpg";
    public static final String MJPEGA = "mjpa";
    public static final String MJPEGB = "mjpb";
    public static final String INDEO32 = "iv32";
    public static final String INDEO41 = "iv41";
    public static final String INDEO50 = "iv50";

    public VideoFormat(String encoding)
    {
        super(encoding);
        size = null;
        maxDataLength = -1;
        frameRate = -1F;
    }

    public VideoFormat(String encoding, Dimension size, int maxDataLength, Class dataType, float frameRate)
    {
        this(encoding);
        if(size != null)
        {
            this.size = new Dimension(size);
        }
        this.maxDataLength = maxDataLength;
        super.dataType = dataType;
        this.frameRate = frameRate;
    }

    public Dimension getSize()
    {
        return size;
    }

    public int getMaxDataLength()
    {
        return maxDataLength;
    }

    public Object clone()
    {
        VideoFormat f = new VideoFormat(super.encoding, size, maxDataLength, super.dataType, frameRate);
        f.copy(this);
        return f;
    }

    public float getFrameRate()
    {
        return frameRate;
    }

    protected void copy(Format f)
    {
        super.copy(f);
        VideoFormat vf = (VideoFormat)f;
        if(vf.size != null)
        {
            size = new Dimension(vf.size);
        }
        maxDataLength = vf.maxDataLength;
        frameRate = vf.frameRate;
    }

    public String toString()
    {
        String s = "";
        if(getEncoding() != null)
        {
            s = s + getEncoding().toUpperCase();
        } else
        {
            s = s + "N/A";
        }
        if(size != null)
        {
            s = s + ", " + size.width + "x" + size.height;
        }
        if(frameRate != -1F)
        {
            s = s + ", FrameRate=" + (float)(int)(frameRate * 10F) / 10F;
        }
        if(maxDataLength != -1)
        {
            s = s + ", Length=" + maxDataLength;
        }
        if(super.dataType != null && super.dataType != Format.byteArray)
        {
            s = s + ", " + super.dataType;
        }
        return s;
    }

    public boolean equals(Object format)
    {
        if(format instanceof VideoFormat)
        {
            VideoFormat vf = (VideoFormat)format;
            if(size == null || vf.size == null)
            {
                if(size != vf.size)
                {
                    return false;
                }
            } else
            if(!size.equals(vf.size))
            {
                return false;
            }
            return super.equals(format) && maxDataLength == vf.maxDataLength && frameRate == vf.frameRate;
        } else
        {
            return false;
        }
    }

    public boolean matches(Format format)
    {
        if(!super.matches(format))
        {
            return false;
        }
        if(!(format instanceof VideoFormat))
        {
            return true;
        } else
        {
            VideoFormat vf = (VideoFormat)format;
            return (size == null || vf.size == null || size.equals(vf.size)) && (frameRate == -1F || vf.frameRate == -1F || frameRate == vf.frameRate);
        }
    }

    public Format intersects(Format format)
    {
        Format fmt;
        if((fmt = super.intersects(format)) == null)
        {
            return null;
        }
        if(!(format instanceof VideoFormat))
        {
            return fmt;
        } else
        {
            VideoFormat other = (VideoFormat)format;
            VideoFormat res = (VideoFormat)fmt;
            res.size = size == null ? other.size : size;
            res.maxDataLength = maxDataLength == -1 ? other.maxDataLength : maxDataLength;
            res.frameRate = frameRate == -1F ? other.frameRate : frameRate;
            return res;
        }
    }

    public Format relax()
    {
        VideoFormat fmt;
        if((fmt = (VideoFormat)super.relax()) == null)
        {
            return null;
        } else
        {
            fmt.size = null;
            fmt.maxDataLength = -1;
            fmt.frameRate = -1F;
            return fmt;
        }
    }
}