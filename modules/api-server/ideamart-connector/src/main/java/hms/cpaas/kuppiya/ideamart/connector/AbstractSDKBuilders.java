package hms.cpaas.kuppiya.ideamart.connector;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigObject;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractSDKBuilders {

    protected static List<RemoteConfig> remoteConfigs;

    private static Gson gson;

    static {
        Config config = ConfigFactory.load("tap");
        Config remoteConfig = config.getConfig("remote");
        ConfigObject remotes = config.getObject("remote");
        remoteConfigs = remotes.entrySet().stream()
                .map(entry -> RemoteConfig.fromConfig(entry.getKey(), remoteConfig.getConfig(entry.getKey())))
                .collect(Collectors.toList());

        gson = new GsonBuilder().create();
    }

    public static <A> String toJson(A request) {
        return gson.toJson(request);
    }

    public static <A> A fromJson(Class<A> cls, String message) {
        return gson.fromJson(message, cls);
    }

    protected Optional<RemoteConfig> getRemoteConfig(String operator) {
        return remoteConfigs.stream().filter(a -> Objects.equals(a.operator, operator)).findFirst();
    }

    public static class RemoteConfig {
        private String applicationId;
        private String password;
        private String version;
        private String operator;
        private String sender;

        private RemoteConfig(String operator, String applicationId, String password, String version, String sender) {
            this.operator = operator;
            this.applicationId = applicationId;
            this.password = password;
            this.version = version;
            this.sender = sender;
        }

        public static RemoteConfig fromConfig(String operator, Config config) {
            return new RemoteConfig(operator,
                    config.getString("applicationId"),
                    config.getString("password"),
                    config.getString("version"),
                    config.getString("sender"));
        }

        public String getApplicationId() {
            return applicationId;
        }

        public String getPassword() {
            return password;
        }

        public String getVersion() {
            return version;
        }

        public String getOperator() {
            return operator;
        }

        public String getSender() {
            return sender;
        }
    }
}
