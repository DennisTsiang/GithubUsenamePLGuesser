package TestAdapters;

import Interfaces.Repository;

public class RepositoryTestAdapter implements Repository {

    private String language;

    public RepositoryTestAdapter(String language) {
        this.language = language;
    }

    @Override
    public String getLanguage() {
        return language;
    }
}
