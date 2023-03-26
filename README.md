
> ./gradlew build

> ./gradlew clean

Check payment_request topic value

> kafka-console-consumer --bootstrap-server localhost:9092 --topic payment_request --formatter kafka.tools.DefaultMessageFormatter --property print.timestamp=true --property print.key=true --property print.value=true --from-beginning

Check payment-response topic value

> kafka-console-consumer --bootstrap-server localhost:9092 --topic payment-response --formatter kafka.tools.DefaultMessageFormatter --property print.timestamp=true --property print.key=true --property print.value=true --from-beginning

### Mock Restaurant commands
Check restaurant-approval-request topic value

> kafka-console-consumer --bootstrap-server localhost:9092 --topic restaurant-approval-request --formatter kafka.tools.DefaultMessageFormatter --property print.timestamp=true --property print.key=true --property print.value=true --from-beginning

Check restaurant-approval-response topic value

> kafka-console-consumer --bootstrap-server localhost:9092 --topic restaurant-approval-response --formatter kafka.tools.DefaultMessageFormatter --property print.timestamp=true --property print.key=true --property print.value=true --from-beginning


publish value to restaurant-approval-response topic

> kafka-console-producer --bootstrap-server localhost:9092 --topic restaurant-approval-response --property parse.key=true --property key.separator=:

>example key:example value

>name:Stephane

> -7986399233746393998:{"id":-7986399233746393998,"productName":"samsung1","orderStatus":"APPROVED","paymentStatus":"PAID","price":1000}

> -7948064357989701700:{"id":-7948064357989701700,"productName":"samsung1","orderStatus":"REJECTED","paymentStatus":"PAID","price":1000}

Misc

Create new topic

> kafka-topics --bootstrap-server localhost:9092 --topic restaurant-approval-response --create --partitions 3 --replication-factor 1
