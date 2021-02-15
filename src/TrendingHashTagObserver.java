import java.util.LinkedHashMap;
import java.util.Observable;
import java.util.Observer;

public class TrendingHashTagObserver implements Observer {

    @Override
    public void update(final Observable observable, Object objectToObserve) {
        final LinkedHashMap<String, Integer> trendingHashs = (LinkedHashMap<String, Integer>) objectToObserve;
        System.out.println("Top trending hashtags are.");
        trendingHashs.forEach((key, value) -> System.out.println("HashTag:" + key + " count:" + value));
    }
}
