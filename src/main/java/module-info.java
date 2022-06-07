import ledza.plugin.Base64Plugin;
import ledza.plugin.BinaryPluginAdapter;
import ledza.plugin.HexPlugin;
import ledza.plugin.Plugin;

module plugin {
    requires org.apache.commons.codec;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    exports ledza.plugin;
    exports ledza.devices;

    uses Plugin;
    provides Plugin with HexPlugin, Base64Plugin, BinaryPluginAdapter;
}