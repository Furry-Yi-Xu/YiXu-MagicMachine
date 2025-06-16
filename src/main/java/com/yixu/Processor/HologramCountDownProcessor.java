package com.yixu.Processor;

import com.yixu.Util.Message.MessageUtil;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;

import java.util.Map;

public class HologramCountDownProcessor {

    public void runHologramCountDown(
            int duration,
            Hologram hologram
    ) {
        Map<String, String> placeholders = Map.of("remaining", String.valueOf(duration));
        DHAPI.setHologramLine(hologram, 0, MessageUtil.getMessage("farm.duration-hologram", placeholders));
    }
}
