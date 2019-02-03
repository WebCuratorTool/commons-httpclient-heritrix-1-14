package org.apache.commons.httpclient.heritrix;

/**
 * A marker interface to denote a class with a gettable HttpRecorder.
 *
 * @author stack
 * @version $Id: HttpRecorderMarker.java 2168 2004-05-28 22:33:09Z stack-sf $
 */
public interface HttpRecorderMarker
{
    /**
     * @return An instance of HttpRecorder.
     */
    public HttpRecorder getHttpRecorder();
}
