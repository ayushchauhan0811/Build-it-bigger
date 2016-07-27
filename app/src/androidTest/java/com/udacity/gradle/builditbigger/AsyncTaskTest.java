package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.test.UiThreadTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ayush on 27-07-2016.
 */
public class AsyncTaskTest extends AndroidTestCase implements DownloadListener {
    AsyncJokeDownloader downloader;
    CountDownLatch signal;
    String joke ="";

    protected void setUp() throws Exception {
        super.setUp();

        signal = new CountDownLatch(1);
        downloader = new AsyncJokeDownloader(this);
    }

    @UiThreadTest
    public void testDownload() throws InterruptedException
    {
        downloader.downloadJoke();
        signal.await(30, TimeUnit.SECONDS);

        assertTrue("Valid joke is returned", joke !=null);
    }

    @Override
    public void downloadCompleted(String j) {
        joke = j;
        signal.countDown();
    }


}
