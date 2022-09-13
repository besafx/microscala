// Cassandra Module
lazy val cassandra = (project in file("modules/cassandra"))
  .disablePlugins(PlayLayoutPlugin)
  .enablePlugins(PlayScala)
  .enablePlugins(JavaAppPackaging)

// Redis Module
lazy val redis = (project in file("modules/redis"))
  .disablePlugins(PlayLayoutPlugin)
  .enablePlugins(PlayScala)
  .enablePlugins(JavaAppPackaging)

// kafka Module
lazy val kafka = (project in file("modules/kafka"))
  .disablePlugins(PlayLayoutPlugin)
  .enablePlugins(PlayScala)
  .enablePlugins(JavaAppPackaging)

// Tutorial Module
lazy val tutorial = (project in file("modules/tutorial"))
  .disablePlugins(PlayLayoutPlugin)
  .enablePlugins(PlayScala)
  .enablePlugins(JavaAppPackaging)

// Database Module
lazy val database = (project in file("modules/database"))
  .disablePlugins(PlayLayoutPlugin)
  .enablePlugins(PlayScala)
  .enablePlugins(JavaAppPackaging)

addCommandAlias("cassandra", ";clean ;cassandra/compile ;cassandra/run")
addCommandAlias("redis", ";clean ;redis/compile ;redis/run")
addCommandAlias("kafka", ";clean ;kafka/compile ;kafka/run")
addCommandAlias("tutorial", ";clean ;tutorial/compile ;tutorial/run")
addCommandAlias("database", ";clean ;database/compile ;database/run")
