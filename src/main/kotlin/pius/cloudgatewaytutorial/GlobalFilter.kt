package pius.cloudgatewaytutorial

import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class GlobalFilter : AbstractGatewayFilterFactory<GlobalFilter.Config>(Config::class.java) {
    private val logger = LoggerFactory.getLogger(javaClass)

    class Config(val baseMessage: String,
                 val preLogger: Boolean,
                 val postLogger: Boolean) {

    }

    override fun apply(config: GlobalFilter.Config): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            val request = exchange.request
            val response = exchange.response

            logger.info("Global filter baseMessage ${config.baseMessage}")
            if (config.preLogger) {
                logger.info("Global filter start ${request.id}")

            }

            chain.filter(exchange)!!.then(Mono.fromRunnable({
                if (config.postLogger) {
                    logger.info("Global filter end ${response.statusCode?.value()}")

                }

//                logger.info("status code ${response.?.value()}")
            }))
        }
    }
}