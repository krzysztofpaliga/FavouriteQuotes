package pl.psydell.favouritequotes.data;

import android.support.annotation.VisibleForTesting;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public class QuotesNetworkDataSource {

    private Gson gson = new GsonBuilder().create();
    private QuotesService quotesService;
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    public CountDownLatch countDownLatch = new CountDownLatch(1);

    public QuotesNetworkDataSource() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://healthruwords.p.mashape.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        quotesService = retrofit.create(QuotesService.class);

    }


    public void fetchRandomQuote(final QuotesDataSource.OnQuoteResponseFetchedCallback callback) {
        quotesService.getRandomResponse().enqueue(new Callback<List<Quotes>>() {
            @Override
            public void onResponse(Call<List<Quotes>> call, Response<List<Quotes>> response) {
                callback.randomQuoteFetched(response.body().get(0));
                countDownLatch.countDown();
            }

            @Override
            public void onFailure(Call<List<Quotes>> call, Throwable t) {
                Quotes quotes = new Quotes();
                quotes.failed = true;
                callback.randomQuoteFetched(quotes);
                countDownLatch.countDown();
            }
        });

    }

    public interface QuotesService {
        @Headers("X-Mashape-Key: nNi1erW3ITmsh7Egtody3rBrv5SNp1faxxdjsnPFsQ4pe25vex")
        @GET("v1/quotes/")
        Call<List<Quotes>> getRandomResponse();
    }

    public class Quotes {
        public String title;
        public String author;
        public String url;
        public String media;
        public String cat;
        public boolean failed = false;
    }
}
