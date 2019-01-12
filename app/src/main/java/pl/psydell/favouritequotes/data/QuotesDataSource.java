package pl.psydell.favouritequotes.data;

public interface QuotesDataSource {

    interface OnQuoteResponseFetchedCallback {

        void randomQuoteFetched(QuotesNetworkDataSource.Quotes quote);
    }
}
