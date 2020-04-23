package com.sportsmax.swrvepushplugin_android_master

object PluginConfigurationHelper {
    private var pluginConfiguration: HashMap<String, String> = HashMap()

    fun setConfigurationMap(pluginConfiguration: Map<String, String>) {
        this.pluginConfiguration.putAll(pluginConfiguration)
    }

    @JvmStatic
    fun getConfigurationValue(key: String): String? {
        return this.pluginConfiguration[key]
    }
}