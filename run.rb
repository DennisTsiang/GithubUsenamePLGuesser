success = system ("ruby compile.rb")
if success then
	system("java -cp out:lib/* Main")
end
