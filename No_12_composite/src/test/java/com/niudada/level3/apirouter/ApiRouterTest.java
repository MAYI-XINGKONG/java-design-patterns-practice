package com.niudada.level3.apirouter;

import com.niudada.constants.GlobalConstants;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class ApiRouterTest {

    @Test
    public void testApiStructure() {
        ApiComponent api = ApiRouter.buildApiStructure();
        log.info("API 网关路由结构：");
        api.print("");
    }

    @Test
    public void testRoute() {
        ApiComponent api = ApiRouter.buildApiStructure();
        String[] paths = {
                "/user-service/create",
                "/delete",
                "/order-service/create",
                "/cancel",
                "/order-service/cancel",
                "/notfound"
        };

        for (String path : paths) {
            boolean found = ApiRouter.route(api, path);
            log.info("路径 {} 是否匹配: {}", path, found ? GlobalConstants.SUCEESS : GlobalConstants.FAIL);
        }
    }
}
