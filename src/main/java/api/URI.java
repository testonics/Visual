package api;

/**
 *
 * Swagger URI --> https://petstore.swagger.io
 * config.Routes class allow the testers to design the end-points for all services that are  available
 */
public class URI {
        /**
         * The base uri is the resource where all the <b>services</b> are running.
         */
    public static String base_uri  ="https://petstore.swagger.io/v2";
        /**
         * The post uri route the request to a service to create an user.
         */
    public static String post_uri  ="/user";
        /**
         * The get,put,delete uri routes the request to respective services
         to read,update,delete an user using username.
         */
    public static String get_put_delete_uri="/user/{username}";
}