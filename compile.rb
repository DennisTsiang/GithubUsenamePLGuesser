execdir = File.expand_path(File.dirname(__FILE__))
puts execdir
system("cd /")
system("cd #{execdir}")
if not File.directory?("out") then
	`mkdir out`
else
	`rm -rf out/*`
end

`javac -cp src:lib/* -d out src/Main.java`
