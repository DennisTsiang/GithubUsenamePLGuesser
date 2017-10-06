package TestAdapters;

import Interfaces.Person;
import Interfaces.Repository;

import java.io.IOException;
import java.util.Map;

public class PersonTestAdapter implements Person {

    private int repoCount;
    private Map<String, Repository> repos;

    public PersonTestAdapter(int repoCount, Map<String, Repository> repos) {
        this.repoCount = repoCount;
        this.repos = repos;
    }

    @Override
    public int getPublicRepoCount() throws IOException {
        return repoCount;
    }

    @Override
    public Map<String, Repository> getRepositories() throws IOException {
        return repos;
    }
}
