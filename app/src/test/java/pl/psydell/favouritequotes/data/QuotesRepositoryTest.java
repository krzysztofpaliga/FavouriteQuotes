package pl.psydell.favouritequotes.data;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class QuotesRepositoryTest {

    @Mock
    QuotesNetworkDataSource mQuotesNetworkDataSource;

    QuotesRepository mQuotesRepository;

    @Before
    public void setupQuotesRepository() {
        MockitoAnnotations.initMocks(this);

        mQuotesRepository = new QuotesRepository(mQuotesNetworkDataSource);
    }

    @Test
    public void getRandomQuote_callsNetoworkDataSource() {
        QuotesDataSource.OnQuoteResponseFetchedCallback callback = new QuotesDataSource.OnQuoteResponseFetchedCallback() {
            @Override
            public void randomQuoteFetched(QuotesNetworkDataSource.Quotes quotesResponse) {

            }
        };

        mQuotesRepository.getRandomQuote(callback);

        verify(mQuotesNetworkDataSource).fetchRandomQuote(callback);
    }
}
