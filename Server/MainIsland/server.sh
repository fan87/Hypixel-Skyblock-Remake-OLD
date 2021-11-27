clear
cp -f ../../IntellijWorkspace/SkyBlock-Plugin/target/SkyBlock-Plugin-v1.jar plugins/SkyBlock-Plugin-v1.jar
java -Xms128M -Xmx4G -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar server.jar
