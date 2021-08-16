// Cassandra Module
lazy val cassandra = (project in file("modules/cassandra"))
  .disablePlugins(PlayLayoutPlugin)
  .enablePlugins(PlayScala)
  .enablePlugins(JavaAppPackaging)

// kafka Module
lazy val kafka = (project in file("modules/kafka"))
  .disablePlugins(PlayLayoutPlugin)
  .enablePlugins(PlayScala)
  .enablePlugins(JavaAppPackaging)

addCommandAlias("cassandra", ";clean ;cassandra/compile ;cassandra/run")
addCommandAlias("kafka", ";clean ;kafka/compile ;kafka/run")
