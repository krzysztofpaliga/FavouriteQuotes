package pl.psydell.favouritequotes.random;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import pl.psydell.favouritequotes.data.QuotesNetworkDataSource;
import pl.psydell.favouritequotes.data.QuotesRepository;
import pl.psydell.favouritequotes.quotes.random.RandomQuoteViewModel;

import static org.junit.Assert.assertFalse;

public class RandomQuoteViewModelTest {


    private QuotesRepository quotesRepository;

    private RandomQuoteViewModel mRandomQuoteViewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setupRandomQuoteViewModel() {
        quotesRepository = new QuotesRepository(new QuotesNetworkDataSource());
        mRandomQuoteViewModel = new RandomQuoteViewModel(quotesRepository);
    }

    @Test
    public void laodRandomQuote_postsLiveDataWithNotEmptyMediaUrl() {
        mRandomQuoteViewModel.loadRandomQuote();

        QuotesNetworkDataSource.Quotes quotes = null;
        while(quotes == null) {
            quotes = mRandomQuoteViewModel.getmRandomQuotes().getValue();
        }

        assertFalse(quotes.media.isEmpty());
    }
}
