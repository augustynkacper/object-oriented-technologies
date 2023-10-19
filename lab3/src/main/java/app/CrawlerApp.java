package app;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.io.IOException;
import java.util.List;

public class CrawlerApp {

    public static final String SCRAPER_API_KEY = "ab9ed1362a374b519edcded78c1072ee";

    private static final List<String> TOPICS = List.of("Agent Cooper", "Sherlock", "Poirot", "Detective Monk");


    public static void main(String[] args) throws IOException, InterruptedException {

        Observable.just("io", "asfgd", "asdfdas")
                .subscribeOn(Schedulers.io())
                .subscribe(System.out::println).dispose();


        PhotoCrawler photoCrawler = new PhotoCrawler();
        photoCrawler.resetLibrary();
        //photoCrawler.downloadPhotoExamples();
       // photoCrawler.downloadPhotosForQuery(TOPICS.get(0));
        photoCrawler.downloadPhotosForMultipleQueries(TOPICS);
        Thread.sleep(100000);
    }
}