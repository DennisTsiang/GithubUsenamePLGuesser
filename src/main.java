import Input.StdIn;
import Input.Input;
import Output.StdOutput;
import Output.View;
import org.json.*;

import java.io.IOException;
import java.util.Deque;

public class Main {

    public static void main(String[] args) throws IOException {

        View view = new StdOutput();
        Input input = new StdIn(view);

        JSONArray repositories = null;

        do {
            //Ask for username
            view.display("Please enter a Github username:");
            String username = input.readLine();

            //Grab user repositories
            view.display("Searching Github for user");
            repositories = GithubClient.grabUserRepos(username, view);
        } while (repositories == null);

        Deque<Repository> languages = GithubClient.tabulateLanguages
                (repositories);
        printMostUsedLanguage(languages, view);
    }

    /*Prints out programming languages that have the highest number of git
      repositories
     */
    public static void printMostUsedLanguage (Deque<Repository> repos,
                                              View view) {
        int maxCount = 0;
        if (repos.size() == 0) {
            view.display("Could not guess what the user's favourite " +
                    "programming language is");
            return;
        }

        Repository currentRepo = repos.poll();
        maxCount = currentRepo.getCount();
        view.display("Based on the git repositories this user has created " +
                "I think this user's favourite programming language(s) are:");

        while (currentRepo.getCount() == maxCount) {
            view.display(currentRepo.getLanguage());
            if (repos.size() == 0) {
                break;
            }
            currentRepo = repos.poll();
        }
    }

}
