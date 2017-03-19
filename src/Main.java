import Output.StdOutput;
import Output.View;
import org.kohsuke.github.GHPerson;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        View view = new StdOutput();
        BufferedReader input = new BufferedReader(new InputStreamReader(System
                .in));

        Map<String, GHRepository> repositories;
        boolean repeat = true;

        while (repeat) {
            repositories = null;
            do {
                //Ask for username
                view.display("Please enter a Github username:");
                String username = input.readLine();

                //Grab user repositories

                GHPerson user = GithubClient.findUser(username, view);
                if (user != null) {
                    view.display("Found Github user. Grabbing repositories...");
                    repositories = GithubClient.grabUserRepos(user, view);
                }
            } while (repositories == null);
            Deque<LangCount> languages = GithubClient.tabulateLanguages
                    (repositories);
            printMostUsedLanguage(languages, view);
            repeat = askForRepeat(view, input);
        }
    }

    public static boolean askForRepeat(View view, BufferedReader input) throws IOException {
        view.display("Try again? [y/n]");
        String answer = null;
        while (answer == null) {
            answer = input.readLine();
            if (answer.startsWith("y") || answer.startsWith("Y")) {
                return true;
            } else if (answer.startsWith("n") || answer.startsWith("N")) {
                return false;
            } else {
                view.display("Please enter y or n");
                answer = null;
            }
        }
        return false;
    }
    /*Prints out programming languages that have the highest number of git
      repositories
     */
    public static void printMostUsedLanguage (Deque<LangCount> repos,
                                              View view) {
        int maxCount = 0;
        if (repos.size() == 0) {
            view.display("Could not guess what the user's favourite " +
                    "programming language is");
            return;
        }

        LangCount currentRepo = repos.poll();
        maxCount = currentRepo.getCount();
        view.display("Based on the git repositories this user has created " +
                "I think this user's favourite programming language(s) are:");

        while (currentRepo.getCount() == maxCount) {
            view.display(String.format("%s (%d)", currentRepo.getLanguage(),
                    currentRepo.getCount()));
            if (repos.size() == 0) {
                break;
            }
            currentRepo = repos.poll();
        }
    }

}
