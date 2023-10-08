package pius.cloudgatewaytutorial

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder

//@Configuration
// YAML 파일이 아닌 다른 코드 방식으로 만들 수 있음.
class FilterConfig {

    //    @Bean
    fun routes(builder: RouteLocatorBuilder): RouteLocator {


        return builder.routes().route {
            it.path("/first-service/**").filters {
                it.addRequestHeader("first-request", "first-request-header")
                it.addResponseHeader("first-response", "first-response-header")
            }.uri("http://localhost:8081")
        }.route {
            it.path("/second-service/**").filters {
                it.addRequestHeader("second-request", "second-request-header")
                it.addResponseHeader("second-request", "second-response-header")
            }.uri("http://localhost:8082")
        }.build()
    }
}