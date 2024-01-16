package ru.tnkv.dsrp.utils;

import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class Style {
    private static final LegacyComponentSerializer DESERIALIZER = LegacyComponentSerializer.legacyAmpersand();

    public static TextComponent ampersand(String input) {
        return DESERIALIZER.deserialize(input);
    }
}
