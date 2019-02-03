package org.apache.commons.httpclient.heritrix;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Provides the necessary interface to decouple {@code org.archive.util.HttpRecorder} in {@code heritrix-1.14.1} from
 * {@code commons-httpclient}.
 */
public interface HttpRecorder {
    /**
     * Wrap the provided stream with the internal RecordingInputStream
     *
     * open() throws an exception if RecordingInputStream is already open.
     *
     * @param is InputStream to wrap.
     *
     * @return The input stream wrapper which itself is an input stream.
     * Pass this in place of the passed stream so input can be recorded.
     *
     * @throws IOException
     */
    public InputStream inputWrap(InputStream is) throws IOException;

    /**
     * Wrap the provided stream with the internal RecordingOutputStream
     *
     * open() throws an exception if RecordingOutputStream is already open.
     *
     * @param os The output stream to wrap.
     *
     * @return The output stream wrapper which is itself an output stream.
     * Pass this in place of the passed stream so output can be recorded.
     *
     * @throws IOException
     */
    public OutputStream outputWrap(OutputStream os) throws IOException;

}
