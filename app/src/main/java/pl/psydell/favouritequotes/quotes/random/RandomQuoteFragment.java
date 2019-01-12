package pl.psydell.favouritequotes.quotes.random;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import pl.psydell.favouritequotes.R;
import pl.psydell.favouritequotes.data.QuotesNetworkDataSource;
import pl.psydell.favouritequotes.data.QuotesRepository;

public class RandomQuoteFragment extends Fragment {

    public RandomQuoteFragment() {
        // Requires empty public constructor
    }

    public static RandomQuoteFragment newInstance() {
        return new RandomQuoteFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_random, container, false);

        final ImageView imageView = (ImageView) root.findViewById(R.id.random_quote);

        RandomQuoteViewModel viewModel = ViewModelProviders.of(this, new RandomQuoteViewModelFactory(new QuotesRepository(new QuotesNetworkDataSource())))
                .get(RandomQuoteViewModel.class);
        viewModel.getmRandomQuotes().observe(this, new Observer<QuotesNetworkDataSource.Quotes>() {
            @Override
            public void onChanged(@Nullable QuotesNetworkDataSource.Quotes quotes) {
                Glide.with(getActivity()).load(quotes.media).into((imageView));
            }
        });

        viewModel.loadRandomQuote();
        return root;
    }
}
