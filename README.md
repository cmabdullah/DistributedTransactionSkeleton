
> ./gradlew build

> ./gradlew clean

> kafka-console-consumer --bootstrap-server localhost:9092 --topic payment_request --formatter kafka.tools.DefaultMessageFormatter --property print.timestamp=true --property print.key=true --property print.value=true --from-beginning
