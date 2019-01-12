package pl.psydell.favouritequotes.quotes.random;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import pl.psydell.favouritequotes.data.QuotesDataSource;
import pl.psydell.favouritequotes.data.QuotesNetworkDataSource;
import pl.psydell.favouritequotes.data.QuotesRepository;

public class RandomQuoteViewModel extends ViewModel {

    private MutableLiveData<QuotesNetworkDataSource.Quotes> mRandomQuotes;
    private QuotesRepository mQuotesRepository;

    @Inject
    public RandomQuoteViewModel(QuotesRepository quotesRepository) {
        mRandomQuotes = new MutableLiveData<>();
        mQuotesRepository = quotesRepository;
    }

    public MutableLiveData<QuotesNetworkDataSource.Quotes> getmRandomQuotes() {
        return mRandomQuotes;
    }

    public void loadRandomQuote() {
        mQuotesRepository.getRandomQuote(new QuotesDataSource.OnQuoteResponseFetchedCallback() {
            @Override
            public void randomQuoteFetched(QuotesNetworkDataSource.Quotes quotes) {
                mRandomQuotes.postValue(quotes);
            }
        });
    }
}
