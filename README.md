1/ INSTALLATION
	1.1/ PERMANENT LOGIN
			$ git config --global --edit

			[user]
			      name = yourname
			      email = yourEmail
			      pass = yourPassword
			      password = yourPassword

	1.2/ INIT PROJECT FOLDER
		- Go into your project folder
		- Open a terminal

		- $ git init
		- $ git remote add origin https://mon.url.git

2/ HOW TO USE IT
	2.1/ UPLOAD
		- Modify / create a file into your project folder

		- $ git add README.md (to select only one file)
			or
		- $ git add -A	(to select all the files in the project)

		- $ git commit -am "commit message"

		- $ git push -u origin master

	2.2/ DOWNLOAD
		- $ git pull -u origin master

3/ COMMENT COMPILER LE CODE ET L'EXECUTER (+autres instructions):

		3.1/ Compilation :
			javac -cp .:UnoS1.jar -d . lbertra2UnoPlayer.java

		3.2/ Vérifier que votre joueur virtuel sait jouer au Uno :
			java -cp .:UnoS1.jar uno.TestCaseProcessor lbertra2

		3.3/ Simuler une partie :
			java -cp .:Unos1.jar uno.UnoSimulation 100 quiet
			java -cp .:UnoS1.jar uno.UnoSimulation 1000 verbose

4/ JAVADOC
	javadoc -cp .:UnoS1.jar -d ./javadoc  Lbertra2UnoPlayer.java
