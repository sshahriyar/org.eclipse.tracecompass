Instructions for Development and Testing

1.  First follow the instructions on the following URL to setup TMF on your system. While following the instructions on the URL, you may have to install new Eclipse plugins/dependencies. 
	You can install new dependencies in Eclipse either through "Help->Eclipse Marketplace" or "Help -> Install New Software". One of the options should work for installation of dependencies.
	Follow the first two steps on the URL and see below for the third step.

	URL: http://wiki.eclipse.org/Linux_Tools_Project/LTTng_Eclipse_Plug-in_Development_Environement_Setup

2.  In Step 3 of the above URL, "Get the source code for org.ec.lipse.linuxtools", clone and checkout the stable branch as follows:
	git clone git://git.eclipse.org/gitroot/linuxtools/org.eclipse.linuxtools.git
	cd org.eclipse.linuxtools/
	git checkout stable-2.2 
    Follow all the instructions there and then come back to the Step 3.

3.  Change your directory to the folder Lttng in Linuxtools you just cloned from the above URL in Step 1 and Step 2.
    After changing the directory, clone a copy of TotalADS:
    
    cd ...../org.eclipse.linuxtools/lttng
    git clone https://github.com/sshahriyar/org.eclipse.tracecompass.totalads.git 
    cd org.eclipse.tracecompass.totalads/
    git checkout develop

4.  You may have to install openjdk-6 or JDK-6 if you already do not have it installed. For Ubuntu, it is simple:
	sudo apt-get install openjdk-6-jdk

5.  Now import the cloned TotalADS in Eclipse as an existing project on hard disk. Right click in the Project Explorer in Eclipse
	and click import.

6. If JDK 7+ is not already in path of Eclipse, you may encounter errors. To add JDK 7+ to the path of Eclipse do the following:

	6.1. Go to Windows->Preference->Java->Installed JRES
	     Add JDK 7+ as an additional installed JRE
	
    6.2. If you don't know where JDK 7+ is on your Ubuntu system, then look at the following path:
	     /usr/lib/jvm/java-6-openjdk-amd64

	6.3. Now right click on org.eclipse.tracecompass.totalads and select project properties 
         Select Java Build Bath
         Select Libraries
         Select JRE System Library; (the one with the error status)
         If there is no error status just select the JRE System Library
         Click Edit and see if JRE is jdk 6 or openjdk 6; otherwise, click on Alternate JRE and select Openjdk 7+  

7.  Install MongoDB
	Follow the instructions here on how to install MongoDB based on your OS: http://docs.mongodb.org/manual/installation/
	Simple installation instructions for a Linux system without messing with many configuration files are as follows; you
    can skip the following and follow whichever way that is easier for you to install MongoDB on your OS from the above URL in this step:

	curl -O http://downloads.mongodb.org/linux/mongodb-linux-x86_64-2.4.9.tgz
	mkdir -p $HOME/mongodb
	cp -R -n mongodb-linux-x86_64-2.4.9/ mongodb
	mkdir -p data/db

	Type gedit ~/.bashrc 
	And append the following ":your_path"/mongodb/bin" to PATH. 
	Replace your_path with the actual path in the system and restart terminal 
	
8. Start MongoDB. If you installed it directly on your OS from the URL in 7 then it may have already been started or will start
   on a reboot automatically. An alternate way to start mongodb is as follows:
 	 cd mongodb
	 type mongod --dbpath data/db/ 
	 mongodb is running
	 
9. Now run TotalADS from Eclipse to use it.	 	 
	 

