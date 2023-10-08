package pius.cloudgatewaytutorial

import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono.fromRunnable

@Component
class CustomFilter : AbstractGatewayFilterFactory<CustomFilter.Config>(Config::class.java) {

    private val logger = LoggerFactory.getLogger(javaClass)

    companion object Config {

    }

    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            val request = exchange.request
            val response = exchange.response

            logger.info("filter: request id : ${request.id}")
            request.mutate().headers {
                it.set("first-request", "first-request-header")
            }

//                ["first-request"] = "first-request-header"
            response.headers["first-response"] = "first-response-header"
//            response.headers[] =

            chain.filter(exchange)!!.then(fromRunnable({
                logger.info("status code ${response.statusCode?.value()}")
//                logger.info("status code ${response.?.value()}")
            }))
        }
    }
}