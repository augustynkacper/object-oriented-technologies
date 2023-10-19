package app;

import io.reactivex.rxjava3.core.Observable;
import model.Photo;
import util.PhotoDownloader;
import util.PhotoProcessor;
import util.PhotoSerializer;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class PhotoCrawler {

    private static final Logger log = Logger.getLogger(PhotoCrawler.class.getName());

    private final PhotoDownloader photoDownloader;

    private final PhotoSerializer photoSerializer;

    private final PhotoProcessor photoProcessor;

    public PhotoCrawler() throws IOException {
        this.photoDownloader = new PhotoDownloader();
        this.photoSerializer = new PhotoSerializer("./photos");
        this.photoProcessor = new PhotoProcessor();
    }

    Observable<Photo> processPhotos(Observable<Photo> photosToProcess) {
        return photosToProcess
                .filter(photoProcessor::isPhotoValid)
                .map(photoProcessor::convertToMiniature);
    }

    public void resetLibrary() throws IOException {
        photoSerializer.deleteLibraryContents();
    }

    public void downloadPhotoExamples() throws IOException{
        downloadPhotos(photoDownloader.getPhotoExamples());
    }

    public void downloadPhotosForQuery(String query) throws IOException, InterruptedException {
        downloadPhotos(photoDownloader.searchForPhotos(query));
    }

    public void downloadPhotosForMultipleQueries(List<String> queries) throws IOException {
        downloadPhotos(photoDownloader.searchForPhotos(queries));
    }

    public void downloadPhotos(Observable<Photo> examples) {
        processPhotos(examples)
                .subscribe(photoSerializer::savePhoto);
    }
}
