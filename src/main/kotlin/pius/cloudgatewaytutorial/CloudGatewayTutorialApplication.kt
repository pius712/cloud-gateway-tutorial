package pius.cloudgatewaytutorial

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
class CloudGatewayTutorialApplication

fun main(args: Array<String>) {
    runApplication<CloudGatewayTutorialApplication>(*args)
}
