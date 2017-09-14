# GithubUsernamePLGuesser
Takes an arbitary Github username and attempts to guess the user's favourite programming language

## Usage
To run call "ruby run.rb" in the terminal. Running this will compile and run
the program.

## Testing
To run the tests call "ruby runTests.rb"

## Dependencies
Requires Ruby and Java to be installed.

## To Do
- Change GithubClient to use an adapter. 
    - This means creating an interface, along with two subclasses that implement that interface. 
One of the classes will act as an translator that delegates method calls to kohsuke's GithubApi package. The other we can use for tests.
This will allow us to create a test adapter, then in our tests we can use the test adapter to return values that we wish.
