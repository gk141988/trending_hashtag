import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TrendingHashTagObservable extends Observable {
    private final HashMap<String, Integer> allHashTagsCounts = new HashMap<>();

    public void setHashTagFromTweet(final String tweet) {
        final List<String> hashTags = extractHashTag(tweet);
        hashTags.forEach(hashTag ->
        {
            Integer hashTagCount = allHashTagsCounts.get(hashTag);
            hashTagCount = allHashTagsCounts.get(hashTag) != null ? hashTagCount + 1 : 1;
            allHashTagsCounts.put(hashTag, hashTagCount);
        });
        setChanged();
        notifyObservers(getTrendingHashTag());
    }

    public Map<String, Integer> getTrendingHashTag() {
        return allHashTagsCounts.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    private List<String> extractHashTag(final String tweet) {
        final Pattern findHashTagPattern = Pattern.compile("#(\\w+)");
        final Matcher mat = findHashTagPattern.matcher(tweet);
        final List<String> hashTags = new ArrayList<>();
        while (mat.find()) {
            hashTags.add(mat.group(1));
        }
        return hashTags;
    }
}
