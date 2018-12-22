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
			java -cp .:UnoS1.jar uno.UnoSimulation 100 quiet
			java -cp .:UnoS1.jar uno.UnoSimulation 1000 verbose

4/ JAVADOC
	javadoc -cp .:UnoS1.jar -d ./javadoc  lbertra2UnoPlayer.java

5/ SONARQUBE

	5.1/ Connect to iut via ssh :
		ssh -D 9000 gate-info.iut-bm.univ-fcomte.fr -X

	5.2/ change firefox parameters and browse to sonarqube page to get a token
		firefox: Hote SOCKS: 127.0.0.1:9000
		browser to : http://gitlab.iut-bm.univ-fcomte.fr:9000/

	5.4/ follow instructions in manual

	5.5/ run sonarscanner via
		/home/loic/program_files/sonarqube/SonnarRunner/bin/sonar-scanner

		/home/lbertra2/Bureau/SonnarScanner/bin/sonar-scanner
