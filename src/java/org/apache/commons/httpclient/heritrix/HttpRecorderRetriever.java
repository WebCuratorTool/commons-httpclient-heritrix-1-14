package org.apache.commons.httpclient.heritrix;

/**
 * For retrieving the instance of {@link HttpRecorder} attached to the current thread (if that instance exists).
 */
public class HttpRecorderRetriever {
    /**
     * Get the current threads' HttpRecorder. Note that this returns the HttpRecorder interface
     * (org.apache.commons.httpclient.heritrix.HttpRecorder), not the Heritrix org.archive.crawler.HttpRecorder.
     * To access the non-interface methods on HttpRecorder, cast the returned HttpRecorder instance
     * to org.archive.crawler.HttpRecorder.
     *
     * @return This threads' HttpRecorder.  Returns null if can't find a
     * HttpRecorder in current instance.
     */
    public static HttpRecorder getHttpRecorder() {
        HttpRecorder recorder = null;
        Thread thread = Thread.currentThread();
        if (thread instanceof HttpRecorderMarker) {
            recorder = ((HttpRecorderMarker)thread).getHttpRecorder();
        }
        return recorder;
    }
}
