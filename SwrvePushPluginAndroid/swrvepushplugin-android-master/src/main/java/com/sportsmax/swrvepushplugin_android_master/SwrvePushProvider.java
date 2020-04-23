package com.sportsmax.swrvepushplugin_android_master;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

import com.applicaster.plugin_manager.push_plugin.PushContract;
import com.applicaster.plugin_manager.push_plugin.helper.PushPluginsType;
import com.applicaster.plugin_manager.push_plugin.listeners.PushTagLoadedI;
import com.applicaster.plugin_manager.push_plugin.listeners.PushTagRegistrationI;
import com.swrve.sdk.SwrveNotificationConfig;
import com.swrve.sdk.SwrveSDK;
import com.swrve.sdk.config.SwrveConfig;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SwrvePushProvider implements PushContract {

    @Override
    public void registerPushProvider(Context context, String registerID) {
        SwrveConfig config = new SwrveConfig();
        NotificationChannel channel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            channel = new NotificationChannel("123", "swrve default channel", NotificationManager.IMPORTANCE_DEFAULT);
            if (context.getSystemService(Context.NOTIFICATION_SERVICE) != null) {
                NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.createNotificationChannel(channel);
            }
        }
        SwrveNotificationConfig.Builder notificationConfig = new SwrveNotificationConfig.Builder(R.drawable.notification_icon, R.drawable.notification_icon, channel);
        config.setNotificationConfig(notificationConfig.build());
        String api_key;
        if (BuildConfig.DEBUG) {
            String SWRVE_SANDBOX_KEY = "swrve_sandbox_key";
            api_key = PluginConfigurationHelper.getConfigurationValue(SWRVE_SANDBOX_KEY);
        }else{
            String SWRVE_PRODUCTION_KEY = "swrve_production_key";
            api_key = PluginConfigurationHelper.getConfigurationValue(SWRVE_PRODUCTION_KEY);
        }
        String SWRVE_ACCOUNT_ID = "swrve_account_id";
        int accountId = Integer.parseInt(Objects.requireNonNull(PluginConfigurationHelper.getConfigurationValue(SWRVE_ACCOUNT_ID)));
        SwrveSDK.createInstance((Application) context.getApplicationContext(), accountId, api_key, config);
    }

    @Override
    public void initPushProvider(Context context) {
    }

    @Override
    public void setPluginParams(Map params) {
    }

    @Override
    public void addTagToProvider(Context context, List<String> tag, PushTagRegistrationI pushTagRegistrationListener) {
    }

    @Override
    public void removeTagToProvider(Context context, List<String> tag, PushTagRegistrationI pushTagRegistrationListener) {
    }

    @Override
    public PushPluginsType getPluginType() {
        return PushPluginsType.applicaster;
    }

    @Override
    public void getTagList(Context context, PushTagLoadedI listener) {
    }
}
