execdir = File.expand_path(File.dirname(__FILE__))
system("cd /")
system("cd #{execdir}")
if not File.directory?("out") then
	`mkdir out`
else
	`rm -rf out/*`
end

`javac -cp src:lib/* -d out src/Main.java`
`javac -cp src:Tests:lib/* -d out Tests/TestRunner.java`
