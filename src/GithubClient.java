import Output.View;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class GithubClient {

    /* Uses Github API to return a JSON array of the user's git repositories */
    public static JSONArray grabUserRepos (String username, View view) throws
            IOException {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder()
                .url("https://api.github.com/users/" + username + "/repos")
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            view.display("STATUS: " + response.code());
            view.display("Could not find user");
            return null;
        }
        String jsonresponse = response.body().string();
        response.close();
        client.dispatcher().executorService().shutdown();
        return new JSONArray(jsonresponse);
    }

    /* Returns a sorted queue of Repository objects from a JSONArray
    representation */
    public static Deque<Repository> tabulateLanguages (JSONArray repos) {

        List<Repository> repoList = new ArrayList<>();
        for (int i = 0; i < repos.length(); i++) {
            JSONObject jo = repos.getJSONObject(i);
            String language = String.valueOf(jo.get("language"));
            if (language.equals("null")) {
                continue;
            }

            Repository repo = new Repository(language);
            if (repoList.contains(repo)) {
                repo = repoList.get(repoList.indexOf(repo));
                repo.incrementCount();
            } else {
                repoList.add(repo);
            }
        }
        Collections.sort (repoList);
        return new ArrayDeque<>(repoList);
    }

}
