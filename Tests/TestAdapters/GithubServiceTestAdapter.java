package TestAdapters;

import Interfaces.GithubService;
import Interfaces.Person;
import Interfaces.Repository;
import org.junit.Before;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GithubServiceTestAdapter implements GithubService {

    private Map<String, Person> dummyValues = new HashMap<>();

    @Before
    public void initializeDummyValues() {

        Map<String, Repository> dennisTsiangRepos = new HashMap<>();
        dennisTsiangRepos.put("one", new RepositoryTestAdapter("Java"));
        dennisTsiangRepos.put("two", new RepositoryTestAdapter("Java"));
        dennisTsiangRepos.put("three", new RepositoryTestAdapter("Java"));
        dennisTsiangRepos.put("four", new RepositoryTestAdapter("Java"));
        dummyValues.put("DennisTsiang", new PersonTestAdapter(4,
                dennisTsiangRepos));


        Map<String, Repository> kohsukeRepos = new HashMap<>();
        kohsukeRepos.put("one", new RepositoryTestAdapter("Java"));
        kohsukeRepos.put("two", new RepositoryTestAdapter("Java"));
        kohsukeRepos.put("three", new RepositoryTestAdapter("Java"));
        kohsukeRepos.put("four", new RepositoryTestAdapter("Java"));
        dummyValues.put("DennisTsiang", new PersonTestAdapter(4,
                kohsukeRepos));
    }

    @Override
    public Person getUser(String username) {
        return dummyValues.get(username);
    }

    @Override
    public int rateLimitRemaining() throws IOException {
        return 123;
    }
}
