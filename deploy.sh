rm -rf target/universal/benchmark-play-1.0-SNAPSHOT
rm target/universal/benchmark-play-1.0-SNAPSHOT.zip
sbt dist &&
cd target/universal/ &&
unzip benchmark-play-1.0-SNAPSHOT.zip
cd benchmark-play-1.0-SNAPSHOT/bin &&
./benchmark-play  -Dplay.http.secret.key=mysupersecret -J-Xms5060M -J-Xmx5060m
