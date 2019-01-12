package pl.psydell.favouritequotes.data;

import javax.inject.Inject;

public class QuotesRepository {

    private QuotesNetworkDataSource mQuotesNetworkDataSource;

    @Inject
    public QuotesRepository(QuotesNetworkDataSource quotesNetworkDataSource) {
        mQuotesNetworkDataSource = quotesNetworkDataSource;
    }
    public void getRandomQuote(QuotesDataSource.OnQuoteResponseFetchedCallback callback) {
        mQuotesNetworkDataSource.fetchRandomQuote(callback);
    }
}
