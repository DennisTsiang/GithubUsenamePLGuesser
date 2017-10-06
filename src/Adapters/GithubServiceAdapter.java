package Adapters;

import Interfaces.GithubService;
import Interfaces.Person;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.kohsuke.github.RateLimitHandler;

import java.io.IOException;

public class GithubServiceAdapter implements GithubService{

    private GitHub gitHub;

    public GithubServiceAdapter() throws IOException {
        gitHub = new GitHubBuilder().withRateLimitHandler
                (RateLimitHandler.FAIL).build();
    }
    @Override
    public Person getUser(String username) throws IOException {
        return new GHPersonAdapter(gitHub.getUser(username));
    }

    @Override
    public int rateLimitRemaining() throws IOException {
        return gitHub.rateLimit().remaining;
    }
}
