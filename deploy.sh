rm -rf target/universal/benchmark-play-1.0-snapshot &&
rm target/universal/benchmark-play-1.0-snapshot.zip &&
sbt dist &&
cd target/universal/ &&
unzip benchmark-play-1.0-snapshot.zip
cd benchmark-play-1.0-SNAPSHOT/bin &&
./benchmark-play  -Dplay.http.secret.key=mysupersecret
