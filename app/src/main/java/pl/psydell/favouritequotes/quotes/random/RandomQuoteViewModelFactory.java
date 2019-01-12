package pl.psydell.favouritequotes.quotes.random;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import pl.psydell.favouritequotes.data.QuotesRepository;

public class RandomQuoteViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final QuotesRepository mQuotesRepository;

    @Inject
    public RandomQuoteViewModelFactory(QuotesRepository quotesRepository) {
        mQuotesRepository = quotesRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new RandomQuoteViewModel(mQuotesRepository);
    }
}
