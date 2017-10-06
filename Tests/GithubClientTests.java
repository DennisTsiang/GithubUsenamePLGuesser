import Interfaces.Person;
import Interfaces.Repository;
import Output.StdOutput;
import Output.View;
import TestAdapters.GithubServiceTestAdapter;
import org.junit.Test;

import java.io.IOException;
import java.util.Deque;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class GithubClientTests {

    private GithubClient githubClient = new GithubClient(new
            GithubServiceTestAdapter());
    @Test
    public void canFindValidUser() throws IOException {
        View view = new StdOutput();
        Person result = githubClient.findUser("octocat", view);
        assertNotEquals(null, result);
    }

    @Test
    public void returnsNullsForInvalidUsername() throws IOException {
        View view = new StdOutput();
        Person result = githubClient.findUser("safdge", view);
        assertEquals(null, result);
    }

    @Test
    public void grabUserReposReturnsHashMapOfUserRepos () throws IOException {
        View view = new StdOutput();
        Person user = githubClient.findUser("octocat", view);
        Map<String, Repository> repos = githubClient.grabUserRepos(user,
                view);
        assertEquals(7, repos.size());
    }

    @Test
    public void tabulateLanguagesReturnsSortedQueue() throws IOException {
        View view = new StdOutput();
        Person user = githubClient.findUser("DennisTsiang", view);
        assertNotEquals(null, user);
        Map<String, Repository> repos = githubClient.grabUserRepos(user,
                view);
        assertEquals(4, repos.size());
        Deque<LangCount> queue = githubClient.tabulateLanguages(repos);
        LangCount frontRepo = queue.poll();
        assertEquals(2, frontRepo.getCount());
        assertEquals("Java", frontRepo.getLanguage());
    }
}
