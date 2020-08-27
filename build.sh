mkdir -p WebContent/WEB-INF/classes

javac -d WebContent/WEB-INF/classes src/me/ijpark/shop/utils/*.java

javac -classpath WebContent/WEB-INF/classes:WebContent/WEB-INF/lib/* -d WebContent/WEB-INF/classes src/me/ijpark/shop/model/*.java
javac -classpath WebContent/WEB-INF/classes:WebContent/WEB-INF/lib/* -d WebContent/WEB-INF/classes src/me/ijpark/shop/dao/*.java
javac -classpath WebContent/WEB-INF/classes:WebContent/WEB-INF/lib/* -d WebContent/WEB-INF/classes src/me/ijpark/shop/web/*.java
javac -classpath WebContent/WEB-INF/classes:WebContent/WEB-INF/lib/* -d WebContent/WEB-INF/classes src/me/ijpark/tomcat/*.java
pwd
cd WebContent
pwd
jar -cvf ./../shop.war ./*
cd ..
pwd
# zip shop.zip shop.war appspec.yml scripts/*.sh
