package Adapters;

import Interfaces.Person;
import Interfaces.Repository;
import org.kohsuke.github.GHPerson;
import org.kohsuke.github.GHRepository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GHPersonAdapter implements Person{

    private GHPerson ghPerson;

    public GHPersonAdapter(GHPerson ghPerson) {
        this.ghPerson = ghPerson;
    }

    @Override
    public int getPublicRepoCount() throws IOException {
        return ghPerson.getPublicRepoCount();
    }

    @Override
    public Map<String, Repository> getRepositories() throws IOException {
        Map<String, Repository> repoMapping = new HashMap<>();
        Map<String, GHRepository> gHrepoMapping = ghPerson.getRepositories();
        for (Map.Entry<String, GHRepository> entry : gHrepoMapping.entrySet()) {
            Repository repo = new GHRepoAdapter( entry.getValue());
            repoMapping.put(entry.getKey(), repo);
        }
        return repoMapping;
    }
}
