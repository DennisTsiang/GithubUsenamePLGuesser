import Output.View;
import org.kohsuke.github.GHPerson;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class GithubClient {

    public static GHPerson findUser(String username, View view) throws IOException {

        GitHub github = GitHub.connectAnonymously();
        GHPerson user;
        try {
            user = github.getUser(username);
        } catch (FileNotFoundException e) {
            view.display("Could not find user");
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

}
