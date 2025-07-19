package com.niudada.level3.apirouter;

public class ApiRouter {
    public static ApiComponent buildApiStructure() {
        return new ApiGroup("API 网关")
                .add(new ApiService("/user-service", "User API")
                        .add(new ApiEndpoint("/create", "POST", "创建用户"))
                        .add(new ApiEndpoint("/delete", "DELETE", "删除用户")))
                .add(new ApiService("/order-service", "Order API")
                        .add(new ApiGroup("订单管理")
                                .add(new ApiEndpoint("/create", "POST", "创建订单"))
                                .add(new ApiEndpoint("/query", "GET", "查询订单")))
                        .add(new ApiEndpoint("/cancel", "PUT", "取消订单")));
    }

    public static boolean route(ApiComponent root, String path) {
        return traverse(root, path);
    }

    private static boolean traverse(ApiComponent component, String path) {
        if (component.getFullPath().equals(path)) {
            return true;
        }
        if (component instanceof ApiGroup group || component instanceof ApiService service) {
            for (ApiComponent child : component.getChildren()) {
                if (traverse(child, path)) {
                    return true;
                }
            }
        }
        return false;
    }

}
