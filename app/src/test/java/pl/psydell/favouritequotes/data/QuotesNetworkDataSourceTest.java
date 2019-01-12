package pl.psydell.favouritequotes.data;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.assertFalse;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class QuotesNetworkDataSourceTest {

    private QuotesNetworkDataSource mQuotesNetworkDataSource;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private QuotesDataSource.OnQuoteResponseFetchedCallback callback;

    @Before
    public void setupQuotesNetworkDataSource() {
        MockitoAnnotations.initMocks(this);

        mQuotesNetworkDataSource = new QuotesNetworkDataSource();
    }

    @Test
    public void fetchRandomResponse_postsQuoteResponseWithNotEmptyMedia() throws InterruptedException {
        mQuotesNetworkDataSource.fetchRandomQuote(callback);

        mQuotesNetworkDataSource.countDownLatch.await();

        verify(callback).randomQuoteFetched(any(QuotesNetworkDataSource.Quotes.class));

    }
}
