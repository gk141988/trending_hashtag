import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tester {
    public static void main(String[] args) throws IOException {
        final TrendingHashTagObservable observable = new TrendingHashTagObservable();
        final TrendingHashTagObserver observer = new TrendingHashTagObserver();
        observable.addObserver(observer);
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("Enter your tweet here : \n");
            String tweet = br.readLine();
            observable.setHashTagFromTweet(tweet);
        }
    }
}
