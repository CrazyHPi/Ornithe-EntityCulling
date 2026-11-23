package dev.tr7zw.entityculling;

import net.ornithemc.osl.entrypoints.api.client.ClientModInitializer;
import net.ornithemc.osl.keybinds.api.KeybindEvents;
import net.ornithemc.osl.keybinds.api.KeybindRegistry;

public class EntityCullingMod extends EntityCullingModBase implements ClientModInitializer {

    public EntityCullingMod() {
        try {
            Class clientClass = net.minecraft.client.Minecraft.class;
        } catch (Throwable ex) {
            System.out.println("EntityCulling Mod installed on a Server. Going to sleep.");
            return;
        }
        onInitialize();
    }

    @Override
    public void initModloader() {
    }

    @Override
    public void initClient() {
        KeybindEvents.REGISTER_KEYBINDS.register(() -> KeybindRegistry.register(keybind));

    }
}
