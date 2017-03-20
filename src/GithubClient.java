import Output.View;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.kohsuke.github.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class GithubClient {

    public static GHPerson findUser(String username, View view) throws IOException {

        GitHub github = new GitHubBuilder().withRateLimitHandler
                (RateLimitHandler.FAIL).build();

        view.display("Remaining calls left: " + github.rateLimit().remaining);

        GHPerson user;
        try {
            view.display("Searching Github for user");
            user = github.getUser(username);
        } catch (FileNotFoundException e) {
            view.display("Could not find user");
            return null;
        } catch (org.kohsuke.github.HttpException e) {
            view.display(e.getResponseMessage());
            return null;
        }
        return user;
    }

    /* Uses Github API to return user's git repositories */
    public static Map<String, GHRepository> grabUserRepos (GHPerson user,
                                                           View view) throws IOException {

        view.display("Found " + user.getPublicRepoCount() + " repositories");
        return user.getRepositories();
    }

    /* Returns a sorted queue of LangCount objects from a JSONArray
    representation */
    public static Deque<LangCount> tabulateLanguages (Map<String, GHRepository> repos) {

        List<LangCount> repoList = new ArrayList<>();
        for (Map.Entry<String, GHRepository> repoEntry : repos.entrySet()) {
            GHRepository ghrepo = repoEntry.getValue();
            String language = ghrepo.getLanguage();
            if (language == null) continue;

            LangCount repo = new LangCount(language);
            if (repoList.contains(repo)) {
                repo = repoList.get(repoList.indexOf(repo));
                repo.incrementCount();
            } else {
                repoList.add(repo);
            }
        }
        //Sorts in ascending order and then reverses list
        Collections.sort (repoList);
        Collections.reverse(repoList);
        return new ArrayDeque<>(repoList);
    }

    //check if github is offline
    public static boolean checkGithubAPIStatus(View view) throws IOException, JSONException {

        view.display("Connecting to Github API");
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://status.github.com/api/status.json")
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code"
                + response);

        JSONObject jsonResponse = new JSONObject(response.body().string());
        response.close();
        String status = jsonResponse.getString("status");
        if (status.equals("minor")) {
            view.display("Github API has minor problems right now");
            return false;
        } else if (status.equals("major")) {
            view.display("Github API has major problems right now");
            return false;
        }
        return true;
    }

}
