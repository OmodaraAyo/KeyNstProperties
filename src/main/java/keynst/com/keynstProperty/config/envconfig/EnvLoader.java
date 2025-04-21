//package keynst.com.keynstProperty.config.envconfig;
//
//import io.github.cdimascio.dotenv.Dotenv;
//
//public class EnvLoader {
//    public static void load(){
//        if(System.getenv("ENVIRONMENT") != null || System.getenv("ENVIRONMENT").equals("development")){
//            Dotenv dotenv = Dotenv.configure().load();
//            dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
//        }
//    }
//}
