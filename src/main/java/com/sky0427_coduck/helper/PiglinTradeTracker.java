package com.sky0427_coduck.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PiglinTradeTracker {

    // 금 아이템 UUID -> 플레이어 UUID
    public static final Map<UUID, UUID> ITEM_OWNERS = new HashMap<>();

    // 피글린 UUID -> 플레이어 UUID
    public static final Map<UUID, UUID> PIGLIN_TRADERS = new HashMap<>();
}