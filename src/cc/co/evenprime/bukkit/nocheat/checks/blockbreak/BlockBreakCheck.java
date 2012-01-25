package cc.co.evenprime.bukkit.nocheat.checks.blockbreak;

import cc.co.evenprime.bukkit.nocheat.NoCheat;
import cc.co.evenprime.bukkit.nocheat.NoCheatPlayer;
import cc.co.evenprime.bukkit.nocheat.checks.Check;
import cc.co.evenprime.bukkit.nocheat.config.ConfigurationCacheStore;
import cc.co.evenprime.bukkit.nocheat.data.DataStore;

public abstract class BlockBreakCheck extends Check {

    private static final String id = "blockbreak";

    public BlockBreakCheck(NoCheat plugin, String name, String permission) {
        super(plugin, id, name, permission);
    }

    public abstract boolean check(NoCheatPlayer player, BlockBreakData data, BlockBreakConfig cc);

    public abstract boolean isEnabled(BlockBreakConfig cc);

    public static BlockBreakData getData(DataStore base) {
        BlockBreakData data = base.get(id);
        if(data == null) {
            data = new BlockBreakData();
            base.set(id, data);
        }
        return data;
    }

    public static BlockBreakConfig getConfig(ConfigurationCacheStore cache) {
        BlockBreakConfig config = cache.get(id);
        if(config == null) {
            config = new BlockBreakConfig(cache.getConfiguration());
            cache.set(id, config);
        }
        return config;
    }
}
